package com.qihua.front.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

  public String processAction(HttpServletRequest request, String action, String httpMethod) {
    return action;
  }

  public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
    return value;
  }

  public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
    Map<String, String> hiddenFields = new HashMap<String, String>();
    hiddenFields.put(CSRFTokenManager.CSRF_PARAM_NAME, CSRFTokenManager.getTokenForSession(request.getSession()));

    return hiddenFields;
  }

  public String processUrl(HttpServletRequest request, String url) {
    return url;
  }



}
