package com.qihua.test;

import com.qihua.front.member.Member;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Dec 1, 2015
 * @version 1.0
 * @see
 */
public class TestEquals {

  public static void main(String[] args) {
    Member a = new Member();
    a.setMemberId("abc");
    a.setMemberName("a");
    a.setEmail("@");
    a.setMobile("137");
    a.setPassword("pwd");
    a.setAddress("addr");
    a.setScore(100);

    Member b = new Member();
    b.setMemberId("abc");
    b.setMemberName("a");
    b.setEmail("@");
    b.setMobile("137");
    b.setPassword("pwd");
    b.setAddress("addr");
    b.setScore(100);

    System.out.println(a.hashCode());
    System.out.println(b.hashCode());

    System.out.println(a.equals(b));
    System.out.println(a.hashCode() == b.hashCode());

    System.out.println(a);
    System.out.println(b);
    System.out.println(a == b);

    b = a;
    System.out.println(a == b);

  }
}
