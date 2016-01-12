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
    private HomeDAO homeDAO;

    public String uploadImg(FileMeta meta, String postion) throws Exception {
        FileCopyUtils.copy(meta.getBytes(),
                new FileOutputStream(meta.getUrl() + meta.getName() + '.' + meta.getExtension()));

        ProductImg img = new ProductImg();
        img.setName(meta.getName());
        img.setSize(meta.getSize());
        img.setExtension(meta.getExtension());
        img.setProductId(meta.getId());
        img.setPostion(postion);

        return homeDAO.insertImage(img);
    }

    public List<ProductImg> findImgList(String position) {
        return homeDAO.selectImg(position);
    }

    public void removeImg(String imgName) {
        homeDAO.deleteImg(imgName);
    }

}
