package com.qihua.console;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.console.security.Menu;
import com.qihua.console.security.SecurityService;
import com.qihua.console.user.User;
import com.qihua.console.user.UserService;
import com.qihua.exception.NullObjectException;
import com.qihua.util.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since 2014-09-20
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console")
public class SystemController {


  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @RequestMapping
  public String index(final HttpServletRequest request) throws Exception {
    if (WebUtils.getSessionAttribute(request, Constants.SESSION_USER) == null) {
      return "/console/index";
    }

    return "redirect:/console/dashboard";
  }

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public String dashboard() {
    return "/console/dashboard";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<HashMap<String, Object>> login(final HttpServletRequest request,
      final User user) {
    try {
      User existed = userService.login(user);

      List<Menu> sessionMenu = securityService.findMenuByRole(existed.getRole());

      WebUtils.setSessionAttribute(request, Constants.SESSION_USER, existed);
      WebUtils.setSessionAttribute(request, Constants.SESSION_MENU, sessionMenu);

      // 设置 CKFinder，否则无法访问上传文件夹。
      WebUtils.setSessionAttribute(request, Constants.SESSION_USER_ROLE, existed.getRole().getRoleId() + "");

    } catch (final NullObjectException e) {
      return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
        {
          put("result", false);
          put("data", e.getMessage());
        }
      }, HttpStatus.OK);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
        {
          put("result", false);
          put("data", "系统异常，请联系管理员。");
        }
      }, HttpStatus.OK);
    }

    return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
      {
        put("result", true);
      }
    }, HttpStatus.OK);
  }

  @RequestMapping(value = "/logout")
  public String logout(final HttpServletRequest request) {
    try {
      request.getSession().invalidate();
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return "redirect:/console";
  }

}
