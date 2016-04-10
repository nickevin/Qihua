package com.qihua.front.card;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.common.Constants;
import com.qihua.exception.MultipleObjectException;
import com.qihua.exception.NullObjectException;
import com.qihua.exception.ObjectExistsException;
import com.qihua.front.member.Member;
import com.qihua.front.member.MemberCard;
import com.qihua.front.member.MemberService;

@Service
public class CardService {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private MemberService memberService;

  public List<MemberCard> findByMember(final Member member) throws Exception {
    return cardRepository.select(member);
  }

  public Card find(final String cardId) throws NullObjectException {
    try {
      return cardRepository.selectOne(cardId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  public Card find(final String cardNo, final String password) throws NullObjectException {
    try {
      return cardRepository.select(cardNo, password);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public Card recharge(final Member member, final Card card) throws Exception {
    Card existed = find(card.getCardNo(), card.getPassword());
    if (existed.getStatus() == Constants.CARD_TYPE_DISABLE) {
      throw new ObjectExistsException();
    }

    existed.setStatus(Constants.CARD_TYPE_DISABLE);
    existed.setRechargeTime(new Timestamp(System.currentTimeMillis()));

    insertMemberCard(member, existed);

    member.setScore(member.getScore() + existed.getScore());
    memberService.updateScore(member);

    return cardRepository.update(existed);
  }

  private void insertMemberCard(final Member member, final Card card) {
    MemberCard newItem = new MemberCard();
    newItem.setMemberId(member.getMemberId());
    newItem.setMemberName(member.getMemberName());
    newItem.setCardNo(card.getCardNo());
    newItem.setCardPassword(card.getPassword());
    newItem.setScore(card.getScore());
    newItem.setStatus(card.getStatus());

    cardRepository.insertMemberCard(newItem);
  }

  public Card findByCardNo(final String cardNo) throws Exception {
    try {
      return cardRepository.selectByCardNo(cardNo);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  public int calcScore(final Member member) {
    try {
      return cardRepository.calcScore(member.getMemberId());
    } catch (EmptyResultDataAccessException e) {
      return 0;
    }
  }
}
