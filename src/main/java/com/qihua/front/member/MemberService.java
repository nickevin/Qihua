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
  private MemberRepository memberRepository;

  public List<Member> find() {
    return memberRepository.selectAll();
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#memberId")},
      put = {@CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.email", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member find(final String memberId) throws Exception {
    System.out.println("find: " + memberId);
    try {
      return memberRepository.selectOne(memberId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(put = {@CachePut(value = "member", key = "#item.memberId")})
  public Member save(final Member item) throws Exception {
    return memberRepository.update(item);
  }

  @Caching(put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null")})
  public Member register(final Member item) throws Exception {
    if (findByMobile(item.getMobile()) == null && findByEmail(item.getEmail()) == null) {
      return memberRepository.insert(item);
    }

    throw new MultipleObjectException();
  }

  public Member login(final Member item) throws Exception {
    try {
      return memberRepository.selectCredential(item);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#mobile")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.email", condition = "#result != null")})
  public Member findByMobile(final String mobile) throws Exception {
    System.out.println("findByMobile: " + mobile);
    try {
      return memberRepository.selectByMobile(mobile);
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
  public Member findByEmail(final String email) throws Exception {
    System.out.println("findByEmail: " + email);
    try {
      return memberRepository.selectByEmail(email);
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
  public Member existsName(final String memberName) throws Exception {
    System.out.println("existsName: " + memberName);
    try {
      return memberRepository.selectByMemberName(memberName);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "#email")},
      put = {@CachePut(value = "member", key = "#result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.memberName", condition = "#result != null"),
          @CachePut(value = "member", key = "#result.mobile", condition = "#result != null")})
  public Member existsEmail(final String email) {
    System.out.println("existsEmail: " + email);
    try {
      return memberRepository.selectByEmail(email);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public boolean passwordMatches(final Member member) {
    try {
      return memberRepository.selectCredential(member) != null;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Caching(put = {@CachePut(value = "member", key = "#member.memberId")})
  public Member updatePassword(final Member member) {
    return memberRepository.updatePassword(member);
  }

  @Caching(put = {@CachePut(value = "member", key = "#member.memberId")})
  public Member updateScore(final Member member) {
    return memberRepository.updateScore(member);
  }

}
