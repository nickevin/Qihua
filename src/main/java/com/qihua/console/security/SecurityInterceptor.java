package com.qihua.console.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.front.security.HttpRequest401Exception;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private List<String> excludedUrls;
    private List<String> globalUrls;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("referer");
        if (isAjaxRequest(request) && referer != null && !referer.startsWith(Constants.CONTEXT_URL)) {
            throw new HttpRequest401Exception();
        }

        String servletPath = request.getServletPath();
        if (isExcludedUrl(servletPath)) {
            return true;
        }

        if (isSessionTimeout(request)) {
            saveTimeoutSession(request);

            throw new HttpRequest408Exception();
        } else if (containsGlobalUrls(servletPath)) {
            return true;
        } else {
            @SuppressWarnings("unchecked")
            List<Menu> sessionMenu = (ArrayList<Menu>) WebUtils.getSessionAttribute(request, Constants.SESSION_MENU);
            for (Menu item : sessionMenu) {
                // System.out.println(servletPath + "\t" + item.getMenuUrl());
                if (StringUtils.isNotEmpty(item.getMenuUrl()) && servletPath.startsWith(item.getMenuUrl())) {

                    return true;
                }

                for (Menu subItem : item.getItems()) {
                    // System.out.println(servletPath + "\t" + subItem.getMenuUrl());
                    if (!StringUtils.isNotEmpty(subItem.getMenuUrl()) && servletPath.startsWith(subItem.getMenuUrl())) {

                        return true;
                    }

                    for (Menu subSubItem : subItem.getItems()) {
                        // System.out.println(servletPath + "\t" + subSubItem.getMenuUrl());
                        if (StringUtils.isNotEmpty(subSubItem.getMenuUrl())
                                && servletPath.startsWith(subSubItem.getMenuUrl())) {

                            return true;
                        }
                    }
                }
            }

            throw new HttpRequest401Exception();
        }
    }

    private boolean isExcludedUrl(String servletPath) {
        for (String url : excludedUrls) {
            Pattern pattern = Pattern.compile(url);
            Matcher matcher = pattern.matcher(servletPath);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;
    }

    private boolean containsGlobalUrls(String servletPath) {
        for (String url : globalUrls) {
            if (servletPath.startsWith(url) && !url.equals("/")) {
                return true;
            }
        }

        return false;
    }

    private void saveTimeoutSession(HttpServletRequest request) {
        StringBuilder params = new StringBuilder("?");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> item : parameterMap.entrySet()) {
            params.append(item.getKey() + "=" + item.getValue()[0] + "&");
        }

        // request.getRequestURI(); // /dxbase/daily/display
        // request.getRequestURL(); //
        // http://10.7.210.7:8080/dxbase/daily/display

        WebUtils.setSessionAttribute(request, Constants.SESSION_TIMEOUT_URI,
                request.getRequestURI().substring(request.getContextPath().length()));
        WebUtils.setSessionAttribute(request, Constants.SESSION_TIMEOUT_REQUEST_PARAMETERS, params);
        WebUtils.setSessionAttribute(request, Constants.SESSION_TIMEOUT_REQUEST_METHOD, request.getMethod());
    }

    private boolean isSessionTimeout(HttpServletRequest request) {
        return WebUtils.getSessionAttribute(request, Constants.SESSION_USER) == null;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");

        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public void setGlobalUrls(List<String> globalUrls) {
        this.globalUrls = globalUrls;
    }
}
