package com.qihua.front.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.common.mail.MailService;
import com.qihua.exception.MultipleObjectException;
import com.qihua.front.order.OrderService;
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
@RequestMapping("/member")
public class MemberController {


  @Autowired
  private MemberService memberService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private MailService mailService;

  @RequestMapping(value = "/login-register", method = RequestMethod.GET)
  public String loginRegister() {
    return "/front/member/login-register";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "/front/member/login";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return "/front/member/register";
  }

  @RequestMapping(value = "/recharge", method = RequestMethod.GET)
  public String recharge() {
    return "/front/member/recharge";
  }

  @RequestMapping(value = "/authorize", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody login(HttpServletRequest request, Member member) {
    try {
      Member existed = memberService.login(member);
      if (existed == null) {
        return new JSONResponseBody(false, "手机号码或电子邮箱和密码不匹配，请重新确认。");
      }

      WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER, existed);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "系统异常，请联系管理员。");
    }

    return new JSONResponseBody("登录成功。");
  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody authenticate(HttpServletRequest request, Member member) {
    try {
      Member newMember = memberService.register(member);

      WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER, newMember);
    } catch (MultipleObjectException e) {
      return new JSONResponseBody(false, "此手机号或电子邮箱已经注册过，请确认。");
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, "注册失败，请联系网站管理员。");
    }

    return new JSONResponseBody("注册成功，将返回兑换中心。");
  }

  @RequestMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
    try {
      request.getSession().invalidate();
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return "redirect:/gift";
  }

  @RequestMapping(value = "/profile")
  public String profile(HttpServletRequest request) throws Exception {
    return "/front/member/profile";
  }

  @RequestMapping(value = "/password")
  public String password(HttpServletRequest request) throws Exception {
    return "/front/member/password";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(HttpServletRequest request, Member member) {
    try {
      memberService.save(member);

      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);
      member.setScore(session.getScore());

      WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER, member);
      // WebUtils.setSessionAttribute(request, "__changed__", "");
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, "保存失败，请联系网站管理员。");
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/exists-name", method = RequestMethod.GET)
  public @ResponseBody boolean exists(HttpServletRequest request, String memberName) {
    try {
      memberName = new String(memberName.getBytes("ISO-8859-1"), "UTF-8");

      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      Member existed = memberService.existsName(memberName);
      if (existed == null || existed.equals(session)) {
        return true;
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return false;
  }

  @RequestMapping(value = "/exists-email", method = RequestMethod.GET)
  public @ResponseBody boolean existsEmail(HttpServletRequest request, String email) {
    try {
      return memberService.existsEmail(email) != null;
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return false;
  }

  @RequestMapping(value = "/password-matches")
  public @ResponseBody boolean passwordMatches(HttpServletRequest request, String oldPassword) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      Member aMember = new Member();
      aMember.setMobile(session.getMobile());
      aMember.setPassword(DigestUtils.md5Hex(oldPassword));

      return memberService.passwordMatches(aMember);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return false;
  }

  @RequestMapping(value = "/order")
  public String list(HttpServletRequest request) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      request.setAttribute("orderList", orderService.findByMember(session));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "/front/member/order";
  }

  @RequestMapping(value = "/reset", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody reset(final HttpServletRequest request, String email) {
    try {
      final Member member = memberService.existsEmail(email);
      if (member != null) {
        final String password = RandomStringUtils.randomAlphanumeric(8);
        member.setPassword(DigestUtils.md5Hex(password));
        memberService.updatePassword(member);

        mailService.sendRestMail(new HashMap<String, Object>() {
          private static final long serialVersionUID = 1L;

          {
            put("templateName", "rest-template.ftl");
            put("subject", Constants.WEBSITE_NAME + "：" + "密码重置");
            put("recipient", member.getEmail());
            put("member", member.getMemberName() == null ? member.getEmail() : member.getMemberName());
            put("password", password);
            put("contextPath", Constants.CONTEXT_URL);
          }
        });

        return new JSONResponseBody(true, "您的新密码已发送至电子邮箱，请注意查收。");
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "发送邮件失败，请确认电子邮箱是否正确。");
    }

    return new JSONResponseBody(false, "电子邮件不存在，请确认。");
  }
}
