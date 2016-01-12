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
    private CardDAO cardDAO;

    @Autowired
    private MemberService memberService;

    public List<MemberCard> findByMember(Member member) throws Exception {
        return cardDAO.select(member);
    }

    public Card find(String cardId) throws NullObjectException {
        try {
            return cardDAO.select(cardId);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    public Card find(String cardNo, String password) throws NullObjectException {
        try {
            return cardDAO.select(cardNo, password);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Card recharge(Member member, Card card) throws Exception {
        Card existed = find(card.getCardNo(), card.getPassword());
        if (existed.getStatus() == Constants.CARD_TYPE_DISABLE) {
            throw new ObjectExistsException();
        }

        existed.setStatus(Constants.CARD_TYPE_DISABLE);
        existed.setRechargeTime(new Timestamp(System.currentTimeMillis()));

        insertMemberCard(member, existed);

        member.setScore(member.getScore() + existed.getScore());
        memberService.updateScore(member);

        return cardDAO.update(existed);
    }

    private void insertMemberCard(Member member, Card card) {
        MemberCard newItem = new MemberCard();
        newItem.setMemberId(member.getMemberId());
        newItem.setMemberName(member.getMemberName());
        newItem.setCardNo(card.getCardNo());
        newItem.setCardPassword(card.getPassword());
        newItem.setScore(card.getScore());
        newItem.setStatus(card.getStatus());

        cardDAO.insertMemberCard(newItem);
    }

    public Card findByCardNo(String cardNo) throws Exception {
        try {
            return cardDAO.selectByCardNo(cardNo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new MultipleObjectException();
        }
    }

    public int calcScore(Member member) {
        try {
            return cardDAO.calcScore(member.getMemberId());
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
