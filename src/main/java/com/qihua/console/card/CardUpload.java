package com.qihua.console.card;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.NotNull;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
public class CardUpload extends BaseEntity {

  @Id
  private Long uploadId;
  @NotNull
  private int total;
  @NotNull
  private String filename = "";

  public CardUpload() {}

  public CardUpload(final int total, final String filename) {
    this.total = total;
    this.filename = filename;
  }

  public Long getUploadId() {
    return uploadId;
  }

  public void setUploadId(final Long uploadId) {
    this.uploadId = uploadId;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(final String filename) {
    this.filename = filename;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(final int total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Upload [uploadId=" + uploadId + ", filename=" + filename + ", total=" + total + "]";
  }

}
