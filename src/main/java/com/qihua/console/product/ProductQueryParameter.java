package com.qihua.console.product;

import com.qihua.console.QueryParameter;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
public class ProductQueryParameter extends QueryParameter {

    private String productName = "";
    private String lowScore = "";
    private String highScore = "";
    private String categoryId = "";
    private String insertTime = "";

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLowScore() {
        return lowScore;
    }

    public void setLowScore(String lowScore) {
        this.lowScore = lowScore;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "ProductQueryParameter [productName=" + productName + ", lowScore=" + lowScore + ", highScore="
                + highScore + ", categoryId=" + categoryId + ", insertTime=" + insertTime + "]";
    }

}
