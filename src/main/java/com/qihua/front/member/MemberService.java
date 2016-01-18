package com.qihua.front.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.exception.MultipleObjectException;

@Service
public class MemberService {

  @Autowired
  private MemberDAO memberDAO;

  public List<Member> find() {
    return memberDAO.select();
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#memberId")},
      put = {@CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.email", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member find(String memberId) throws Exception {
    System.out.println("find: " + memberId);
    try {
      return memberDAO.select(memberId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(put = {@CachePut(value = "member", key = "#item.memberId")})
  public Member save(Member item) throws Exception {
    return memberDAO.update(item);
  }

  @Caching(put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null")})
  public Member register(Member item) throws Exception {
    if (findByMobile(item.getMobile()) == null && findByEmail(item.getEmail()) == null) {
      return memberDAO.insert(item);
    }

    throw new MultipleObjectException();
  }

  public Member login(Member item) throws Exception {
    try {
      return memberDAO.selectCredential(item);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#mobile")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.email", condition = "#result != null")})
  public Member findByMobile(String mobile) throws Exception {
    System.out.println("findByMobile: " + mobile);
    try {
      return memberDAO.selectByMobile(mobile);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#email")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member findByEmail(String email) throws Exception {
    System.out.println("findByEmail: " + email);
    try {
      return memberDAO.selectByEmail(email);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#memberName")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.email", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member existsName(String memberName) throws Exception {
    System.out.println("existsName: " + memberName);
    try {
      return memberDAO.selectByMemberName(memberName);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#email")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member existsEmail(String email) {
    System.out.println("existsEmail: " + email);
    try {
      return memberDAO.selectByEmail(email);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public boolean passwordMatches(Member member) {
    try {
      return memberDAO.selectCredential(member) != null;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Caching(put = {@CachePut(value = "member", key = "#member.memberId")})
  public Member updatePassword(Member member) {
    return memberDAO.updatePassword(member);
  }

  @Caching(put = {@CachePut(value = "member", key = "#member.memberId")})
  public Member updateScore(Member member) {
    return memberDAO.updateScore(member);
  }

}
