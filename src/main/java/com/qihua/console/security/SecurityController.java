package com.qihua.console.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qihua.common.JSONResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2013-10-18
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console/security")
public class SecurityController {


  @Autowired
  private SecurityService authService;

  @RequestMapping()
  public String list(HttpServletRequest request) {
    request.setAttribute("roleList", authService.findRoles());

    return "/console/security/list";
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) Long roleId) {
    try {
      if (roleId == null) {
        request.setAttribute("role", new Role());
        request.setAttribute("menuList", authService.findMenus());
        request.setAttribute("authList", Collections.emptyList());
      } else {
        request.setAttribute("role", authService.findRoleById(roleId));
        request.setAttribute("menuList", authService.findMenus());
        request.setAttribute("authList", authService.findMenuByRoleId(roleId));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "/console/security/display";
  }

  @RequestMapping(value = "/save")
  public @ResponseBody JSONResponseBody save(Role role) {
    try {
      authService.save(role);
    } catch (Exception e) {
      log.error(e.getMessage());

      return new JSONResponseBody(false);
    }

    return new JSONResponseBody();
  }

}
