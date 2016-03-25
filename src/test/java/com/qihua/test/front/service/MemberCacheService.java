package com.qihua.test.front.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.qihua.front.member.Member;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Jan 15, 2016
 * @version 1.0
 * @see
 */
@Service
public class MemberCacheService {

  Set<Member> members = new HashSet<Member>();

  @Caching(put = {
      @CachePut(value = "member", key = "\"Member.memberId:\" + #result.memberId", condition = "#result != null")})
  public Member save(final Member member) {
    member.setMemberId("0001");
    members.add(member);

    return member;
  }

  @Caching(put = {@CachePut(value = "member", key = "\"Member.memberId:\" + #member.memberId")})
  public Member update(final Member member) {
    members.remove(member);

    members.add(member);

    return member;
  }

  @Caching(evict = {@CacheEvict(value = "member", key = "\"Member.memberId:\" + #member.memberId"),
      @CacheEvict(value = "member", key = "\"Member.memberId:\" + #member.memberName"),
      @CacheEvict(value = "member", key = "\"Member.memberId:\" + #member.email")})
  public Member delete(final Member member) {
    members.remove(member);

    return member;
  }

  @CacheEvict(value = "member", allEntries = true)
  public void deleteAll() {
    members.clear();
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "\"Member.memberId:\" + #member.memberId")},
      put = {
          @CachePut(value = "member", key = "\"Member.memberName:\" + #result.memberName",
              condition = "#result != null"),
          @CachePut(value = "member", key = "\"Member.email:\" + #result.email", condition = "#result != null")})
  // 将 memberName，email 作为 key 加入到缓存，以便于 findByMemberName，findByEmail 可以使用缓存
  public Member findByMemberId(final Member member) {
    System.out.println("cache miss, invoke find by memberId, memberId:" + member.getMemberId());
    for (Member item : members) {
      if (item.getMemberId().equals(member.getMemberId())) {
        return item;
      }
    }

    return null;
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "\"Member.memberName:\" + #member.memberName")},
      put = {
          @CachePut(value = "member", key = "\"Member.memberId:\" + #result.memberId", condition = "#result != null"),
          @CachePut(value = "member", key = "\"Member.email:\" + #result.email", condition = "#result != null")})
  public Member findByMemberName(final Member member) {
    System.out.println("cache miss, invoke find by memberName, memberName: " + member.getMemberName());
    for (Member item : members) {
      if (item.getMemberName().equals(member.getMemberName())) {
        return item;
      }
    }

    return null;
  }

  @Caching(cacheable = {@Cacheable(value = "member", key = "\"Member.email:\" + #member.email")}, put = {
      @CachePut(value = "member", key = "\"Member.memberId:\" + #result.memberId", condition = "#result != null"),
      @CachePut(value = "member", key = "\"Member.memberName:\" + #result.memberName", condition = "#result != null")})
  public Member findByEmail(final Member member) {
    System.out.println("cache miss, invoke find by email, email: " + member.getEmail());
    for (Member item : members) {
      if (item.getEmail().equals(member.getEmail())) {
        return item;
      }
    }

    return null;
  }


  @CacheEvict(value = "user", key = "\"Member.memberId:\" + #user.id",
      condition = "#root.target.canCache() and #root.caches[0].get(#user.id).get().username ne #user.username",
      beforeInvocation = true)
  public void conditionUpdate(final Member member) {
    members.remove(member);
    members.add(member);
  }


  public boolean canEvict(final Cache userCache, final Long memberId, final String memberName) {
    Member cacheUser = userCache.get(memberId, Member.class);
    if (cacheUser == null) {
      return false;
    }

    return !cacheUser.getMemberName().equals(memberName);
  }
}
