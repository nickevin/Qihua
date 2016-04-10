package com.qihua.console.product;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.qihua.common.Constants;
import com.qihua.common.FileMeta;
import com.qihua.exception.NullObjectException;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> find() {
    return productRepository.selectAll();
  }

  public Product find(final String productId) throws NullObjectException {
    try {
      Product product = productRepository.selectOne(productId);
      product.setImages(productRepository.selectImg(productId));
      product.setRecommendImages(productRepository.selectRecommendImg(productId));
      product.setFlashSaleImages(productRepository.selectFlashSaleImg(productId));

      return product;
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public Product save(final Product item) throws Exception {
    if (item.getProductId() == null) {
      return productRepository.insert(item);
    }

    return productRepository.update(item);
  }

  public Product create(final Product item) throws Exception {
    return productRepository.insert(item);
  }

  public Product update(final Product item) throws Exception {
    return productRepository.update(item);
  }

  public List<Product> find(final ProductQueryParameter queryParam) {
    return productRepository.select(queryParam);
  }

  @Transactional(rollbackFor = Exception.class)
  public void remove(final String id) throws Exception {
    productRepository.delete(id);
  }

  public void rating(final String productId, final int rate) {
    productRepository.updateRate(productId, rate);
  }

  public String uploadImg(final FileMeta meta) throws Exception {
    FileCopyUtils.copy(meta.getBytes(),
        new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

    ProductImg img = new ProductImg();
    img.setName(meta.getName());
    img.setSize(meta.getSize());
    img.setExtension(meta.getExtension());
    img.setProductId(meta.getId());

    return productRepository.insertImage(img);
  }

  public String uploadRecommendImg(final FileMeta meta) throws Exception {
    FileCopyUtils.copy(meta.getBytes(),
        new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

    ProductImg img = new ProductImg();
    img.setName(meta.getName());
    img.setSize(meta.getSize());
    img.setExtension(meta.getExtension());
    img.setProductId(meta.getId());

    return productRepository.insertRecommendImg(img);
  }

  public String uploadFlashSaleImg(final FileMeta meta) throws Exception {
    FileCopyUtils.copy(meta.getBytes(),
        new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

    ProductImg img = new ProductImg();
    img.setName(meta.getName());
    img.setSize(meta.getSize());
    img.setExtension(meta.getExtension());
    img.setProductId(meta.getId());

    return productRepository.insertFlashSaleImage(img);
  }

  public void removeImg(final String imgName) throws Exception {
    clearImg(productRepository.selectImgByImgName(imgName));

    productRepository.deleteImg(imgName);
  }

  public void removeRecommendImg(final String imgName) throws Exception {
    productRepository.deleteRecommendImg(imgName);

    clearImg(productRepository.selectRecommendImgByImgName(imgName));
  }

  public void removeFlashSaleImg(final String imgName) throws Exception {
    clearImg(productRepository.selectFlashSaleImgByImgName(imgName));

    productRepository.deleteFlashSaleImg(imgName);
  }

  public void switchStatus(final Product product) throws Exception {
    productRepository.update(product);
  }

  public String uploadImgBlock(final FileMeta meta, final String position) throws Exception {
    FileCopyUtils.copy(meta.getBytes(),
        new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

    ProductImg img = new ProductImg();
    img.setName(meta.getName());
    img.setSize(meta.getSize());
    img.setExtension(meta.getExtension());
    img.setProductId(meta.getId());
    img.setPostion(position);

    return productRepository.insertImageBlock(img);
  }

  public ProductImg findImgBlockByPosition(final String position) {
    try {
      return productRepository.selectImgBlockByPosition(position);
    } catch (EmptyResultDataAccessException e) {
    }

    return null;
  }

  public void removeImgBlock(final String imgName) throws Exception {
    clearImg(productRepository.selectImgBlockByImgName(imgName));

    productRepository.deleteImgBlock(imgName);
  }

  public void updateProductId(final String imgName, final String productId) {
    productRepository.updateProductId(imgName, productId);
  }

  private void clearImg(final ProductImg img) throws Exception {
    new File(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR + img.getName() + "." + img.getExtension())
        .delete();
  }
}
