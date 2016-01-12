package com.qihua.console.product;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.qihua.common.Constants;
import com.qihua.common.FileMeta;
import com.qihua.exception.NullObjectException;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> find() {
        return productDAO.select();
    }

    public Product find(String productId) throws NullObjectException {
        try {
            Product product = productDAO.select(productId);
            product.setImages(productDAO.selectImg(productId));
            product.setRecommendImages(productDAO.selectRecommendImg(productId));
            product.setFlashSaleImages(productDAO.selectFlashSaleImg(productId));

            return product;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Product save(Product item) throws Exception {
        if (item.getProductId() == null) {
            return productDAO.insert(item);
        }

        return productDAO.update(item);
    }

    public List<Product> find(ProductQueryParameter queryParam) {
        return productDAO.select(queryParam);
    }

    public void remove(String id) throws Exception {
        productDAO.delete(id);
    }

    public void rating(String productId, int rate) {
        productDAO.updateRate(productId, rate);
    }

    public String uploadImg(FileMeta meta) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());

        return productDAO.insertImage(img);
    }

    public String uploadRecommendImg(FileMeta meta) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());

        return productDAO.insertRecommendImg(img);
    }

    public String uploadFlashSaleImg(FileMeta meta) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());

        return productDAO.insertFlashSaleImage(img);
    }

    public void removeImg(String imgName) throws Exception {
        clearImg(productDAO.selectImgByImgName(imgName));

        productDAO.deleteImg(imgName);
    }

    public void removeRecommendImg(String imgName) throws Exception {
        productDAO.deleteRecommendImg(imgName);

        clearImg(productDAO.selectRecommendImgByImgName(imgName));
    }

    public void removeFlashSaleImg(String imgName) throws Exception {
        clearImg(productDAO.selectFlashSaleImgByImgName(imgName));

        productDAO.deleteFlashSaleImg(imgName);
    }

    public void switchStatus(Product product) throws Exception {
        productDAO.update(product);
    }

    public String uploadImgBlock(FileMeta meta, String position) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());
        img.setPostion(position);

        return productDAO.insertImageBlock(img);
    }

    public ProductImg findImgBlockByPosition(String position) {
        try {
            return productDAO.selectImgBlockByPosition(position);
        } catch (EmptyResultDataAccessException e) {
        }

        return null;
    }

    public void removeImgBlock(String imgName) throws Exception {
        clearImg(productDAO.selectImgBlockByImgName(imgName));

        productDAO.deleteImgBlock(imgName);
    }

    public void updateProductId(String imgName, String productId) {
        productDAO.updateProductId(imgName, productId);
    }

    private void clearImg(ProductImg img) throws Exception {
        new File(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR + img.getName() + "." + img.getExtension())
        .delete();
    }
}
