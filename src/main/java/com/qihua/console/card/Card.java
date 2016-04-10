package com.qihua.console.card;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.NotNull;

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

  @Id
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

  public Card(final String cardNo, final String password, final long score) {
    this.cardNo = cardNo;
    this.password = password;
    this.score = score;
  }

}
