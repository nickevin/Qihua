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
import com.qihua.console.security.SecurityRepository;
import com.qihua.exception.MultipleObjectException;
import com.qihua.exception.NullObjectException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SecurityRepository securityRepository;

  public List<User> find() {
    return userRepository.selectAll();
  }

  public User find(final String userId) throws NullObjectException {
    try {
      return userRepository.selectOne(userId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  public User login(final User user) throws Exception {
    try {
      return userRepository.selectCredential(user);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public User save(final User item) throws Exception {
    if (StringUtils.isEmpty(item.getUserId())) {
      if (StringUtils.isEmpty(item.getPassword())) {
        item.setPassword(DigestUtils.md5Hex(Constants.DEFAULT_PASSWORD));
      }

      User newUser = userRepository.insert(item);
      securityRepository.insertUserRole(newUser);

      return newUser;
    } else {
      User existed = userRepository.update(item);
      securityRepository.updateUserRole(existed);

      return existed;
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public void saveProfile(final User user) throws Exception {
    userRepository.update(user);
  }

  public boolean passwordMatches(final User user) throws Exception {
    try {
      return userRepository.selectCredential(user) != null;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  public User findByUsername(final String username) throws Exception {
    try {
      return userRepository.selectByUsername(username);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  public boolean existsUsername(final String username) throws Exception {
    try {
      return userRepository.selectByUsername(username) == null;
    } catch (EmptyResultDataAccessException e) {
      return false;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  public List<User> find(final UserQueryParameter queryParam) {
    return userRepository.select(queryParam);
  }

}
