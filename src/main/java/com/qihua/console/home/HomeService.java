package com.qihua.console.home;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.qihua.common.FileMeta;
import com.qihua.console.product.ProductImg;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public String uploadImg(FileMeta meta, String postion) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());
        img.setPostion(postion);

        return homeRepository.insertImage(img);
    }

    public List<ProductImg> findImgList(String position) {
        return homeRepository.selectImg(position);
    }

    public void removeImg(String imgName) {
        homeRepository.deleteImg(imgName);
    }

}
