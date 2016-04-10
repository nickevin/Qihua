package com.qihua.front.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qihua.common.Constants;
import com.qihua.front.product.ProductImg;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    public List<ProductImg> findCenterImgList() {
        return homeRepository.selectImg(Constants.HOME_IMG_POSISION_CENTER);
    }

    public List<ProductImg> findBottomImgList() {
        return homeRepository.selectImg(Constants.HOME_IMG_POSISION_BOTTOM);
    }

}
