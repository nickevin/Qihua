package com.qihua.front.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Member find(String memberId) throws Exception {
        try {
            return memberDAO.select(memberId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Member save(Member item) throws Exception {
        return memberDAO.update(item);
    }

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

    public Member findByMobile(String mobile) throws Exception {
        try {
            return memberDAO.selectByMobile(mobile);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new MultipleObjectException();
        }
    }

    public Member findByEmail(String email) throws Exception {
        try {
            return memberDAO.selectByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new MultipleObjectException();
        }
    }

    public Member existsName(String memberName) throws Exception {
        try {
            return memberDAO.selectByMemberName(memberName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Member existsEmail(String email) {
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

    public void updatePassword(Member member) {
        memberDAO.updatePassword(member);
    }

    public void updateScore(Member member) {
        memberDAO.updateScore(member);
    }

}
