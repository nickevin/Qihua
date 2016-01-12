package com.qihua.front.product;

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
    private String order = "";

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        result = prime * result + ((highScore == null) ? 0 : highScore.hashCode());
        result = prime * result + ((lowScore == null) ? 0 : lowScore.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProductQueryParameter other = (ProductQueryParameter) obj;
        if (categoryId == null) {
            if (other.categoryId != null) {
                return false;
            }
        } else if (!categoryId.equals(other.categoryId)) {
            return false;
        }
        if (highScore == null) {
            if (other.highScore != null) {
                return false;
            }
        } else if (!highScore.equals(other.highScore)) {
            return false;
        }
        if (lowScore == null) {
            if (other.lowScore != null) {
                return false;
            }
        } else if (!lowScore.equals(other.lowScore)) {
            return false;
        }
        if (order == null) {
            if (other.order != null) {
                return false;
            }
        } else if (!order.equals(other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductQueryParameter [productName=" + productName + ", lowScore=" + lowScore + ", highScore="
                + highScore + ", categoryId=" + categoryId + ", order=" + order + "]";
    }

}
