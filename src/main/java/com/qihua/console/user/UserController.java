package com.qihua.console.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.util.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-18
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console/user")
public class UserController {


  @Autowired
  private UserService userService;

  @RequestMapping
  public String list(HttpServletRequest request, UserQueryParameter queryParam) {
    try {
      request.setAttribute("queryParam", queryParam);
      request.setAttribute("userList", userService.find(queryParam));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/user/list";
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) String userId) {
    User item = new User();

    try {
      if (userId == null) {
        // ...
      } else {
        item = userService.find(userId);
      }

      request.setAttribute("user", item);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/user/display";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(User user) {
    try {
      user = userService.save(user);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody(true, user.getUserId());
  }

  @RequestMapping(value = "/profile")
  public String profile(HttpServletRequest request) {
    try {
      User sessionUser = (User) WebUtils.getSessionAttribute(request, Constants.SESSION_USER);
      User user = userService.find(sessionUser.getUserId());
      request.setAttribute("user", user);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/user/profile";
  }

  @RequestMapping(value = "/save-profile", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody saveProfile(HttpServletRequest request, User user) {
    try {
      userService.saveProfile(user);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "系统异常，请联系管理员。");
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/password-matches")
  public @ResponseBody JSONResponseBody passwordMatches(User user) {
    try {
      if (!userService.passwordMatches(user)) {
        return new JSONResponseBody(false, "原密码不正确，请重新输入");
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "系统异常，请联系管理员。");
    }

    return new JSONResponseBody(true);
  }

}
