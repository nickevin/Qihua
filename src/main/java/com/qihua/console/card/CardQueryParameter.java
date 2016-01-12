package com.qihua.console.card;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.qihua.console.QueryParameter;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
public class CardQueryParameter extends QueryParameter {

    private String cardId;
    private String cardNo = "";
    private String password = "";
    private String lowScore = "";
    private String highScore = "";
    private String startDate = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY).toString("yyyy-MM-dd"); // 默认：本周一
    private String endDate = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).toString("yyyy-MM-dd"); // 默认：本周日
    private String rangeDate = startDate + " - " + endDate;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    @Override
    public String toString() {
        return "CardQueryParameter [cardId=" + cardId + ", cardNo=" + cardNo + ", password=" + password + ", lowScore="
                + lowScore + ", highScore=" + highScore + ", startDate=" + startDate + ", endDate=" + endDate
                + ", rangeDate=" + rangeDate + "]" + super.toString();
    }

}
