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
    private MemberDAO memberDAO;

    public List<Member> find() {
        return memberDAO.select();
    }

    public Member find(String memberId) throws NullObjectException {
        try {
            return memberDAO.select(memberId);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Member save(Member item) throws Exception {
        if (item.getMemberId() == null) {
            return memberDAO.insert(item);
        }

        return memberDAO.update(item);
    }

    public List<Member> find(MemberQueryParameter queryParam) {
        return memberDAO.select(queryParam);
    }
}
