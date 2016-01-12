package com.qihua.console.user;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.common.Constants;
import com.qihua.console.security.SecurityDAO;
import com.qihua.exception.MultipleObjectException;
import com.qihua.exception.NullObjectException;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SecurityDAO securityDAO;

    public List<User> find() {
        return userDAO.select();
    }

    public User find(String userId) throws NullObjectException {
        try {
            return userDAO.select(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    public User login(User user) throws Exception {
        try {
            return userDAO.selectCredential(user);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(User item) throws Exception {
        if (StringUtils.isEmpty(item.getUserId())) {
            if (StringUtils.isEmpty(item.getPassword())) {
                item.setPassword(DigestUtils.md5Hex(Constants.DEFAULT_PASSWORD));
            }

            User newUser = userDAO.insert(item);
            securityDAO.insertUserRole(newUser);

            return newUser;
        } else {
            User existed = userDAO.update(item);
            securityDAO.updateUserRole(existed);

            return existed;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProfile(User user) throws Exception {
        userDAO.update(user);
    }

    public boolean passwordMatches(User user) throws Exception {
        try {
            return userDAO.selectCredential(user) != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public User findByUsername(String username) throws Exception {
        try {
            return userDAO.selectByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new MultipleObjectException();
        }
    }

    public boolean existsUsername(String username) throws Exception {
        try {
            return userDAO.selectByUsername(username) == null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new MultipleObjectException();
        }
    }

    public List<User> find(UserQueryParameter queryParam) {
        return userDAO.select(queryParam);
    }

}
