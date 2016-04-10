package com.qihua.console.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.NullObjectException;

@Service
public class MemberService {

  @Autowired
  private MemberRepository memberRepository;

  public List<Member> find() {
    return memberRepository.selectAll();
  }

  public Member find(final String memberId) throws NullObjectException {
    try {
      return memberRepository.selectOne(memberId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public Member save(final Member item) throws Exception {
    if (item.getMemberId() == null) {
      return memberRepository.insert(item);
    }

    return memberRepository.update(item);
  }

  public List<Member> find(final MemberQueryParameter queryParam) {
    return memberRepository.select(queryParam);
  }
}
