package com.qihua.test;

import java.util.HashSet;
import java.util.Set;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Jan 16, 2016
 * @version 1.0
 * @see
 */
class KeyMaster {

  public int i;
  public String j;

  public KeyMaster(int i, String j) {
    this.i = i;
    this.j = j;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + i;
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
    KeyMaster other = (KeyMaster) obj;
    if (i != other.i) {
      return false;
    }
    return true;
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public String getJ() {
    return j;
  }

  public void setJ(String j) {
    this.j = j;
  }

}


public class TestHashSet {

  public static void main(String[] args) {
    Set<KeyMaster> set = new HashSet<KeyMaster>();
    KeyMaster k1 = new KeyMaster(1, "a");
    KeyMaster k2 = new KeyMaster(2, "b");
    set.add(k1);
    set.add(k1);
    set.add(k2);
    set.add(k2);
    System.out.println(set.size() + ":");

    k2.setJ("c");
    set.remove(k2);
    System.out.println(set.size() + ":");

    k1.setJ("d");
    set.remove(k1);
    System.out.print(set.size());

  }
}
