package com.qihua.front.card;

import java.sql.Timestamp;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.GeneratedValue;
import com.qihua.common.annotation.NotNull;
import com.qihua.common.annotation.PrimaryKey;

import lombok.Data;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
@Data
public class Card extends BaseEntity {

  @PrimaryKey
  @GeneratedValue(generator = "uuid")
  private String cardId;
  private Long uploadId;
  @NotNull
  private String cardNo = "";
  @NotNull
  private String password = "";
  @NotNull
  private long score;
  private Timestamp rechargeTime;

  public Card() {}

  public Card(String cardNo, String password, long score) {
    this.cardNo = cardNo;
    this.password = password;
    this.score = score;
  }

}
