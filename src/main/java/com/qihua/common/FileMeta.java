package com.qihua.common;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-11-5
 * @version 1.0
 * @see
 */
@JsonIgnoreProperties({"bytes"})
public class FileMeta {

  private String id;
  private String name;
  private long size;
  private String extension;
  private String type;
  private String url;
  private byte[] bytes;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  @Override
  public String toString() {
    return "FileMeta [id=" + id + ", name=" + name + ", size=" + size + ", type=" + type + ", url=" + url + ", bytes="
        + Arrays.toString(bytes) + "]";
  }

}
