package com.qihua.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Nov 28, 2015
 * @version 1.0
 * @see http://www.open-open.com/code/view/1429750067776
 *      http://blog.csdn.net/earbao/article/details/41483879
 */
public class TestJasypt {

  public static void main(String[] args) {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setPassword("root");
    encryptor.setConfig(config);
    String plainText = "admin123456";
    String ciphertext = encryptor.encrypt(plainText);
    System.out.println(plainText + ": " + ciphertext);

    System.out.println(encryptor.decrypt("fV9ZYcOCSSwxnzgvEmoP0OXO9aH17EKV"));
  }
}
