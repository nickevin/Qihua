package com.qihua.front.security;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;

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
        String host = request.getHeader("host");
        if (isAjaxRequest(request) && host != null && !Constants.CONTEXT_URL.contains(host)) {
            throw new HttpRequest401Exception();
        }

        String servletPath = request.getServletPath();
        if (isExcludedUrl(servletPath)) {
            return true;
        }

        if (isSessionTimeout(request)) {
            saveTimeoutSession(request);

            throw new HttpRequest408Exception();
        }

        return true;
    }

    private void saveTimeoutSession(HttpServletRequest request) {
        StringBuilder params = new StringBuilder("?");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> item : parameterMap.entrySet()) {
            params.append(item.getKey() + "=" + item.getValue()[0] + "&");
        }

        WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_URI,
                request.getRequestURI().substring(request.getContextPath().length()));
        WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_REQUEST_METHOD, request.getMethod());
        WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_REQUEST_PARAMETERS, params);

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

    private boolean isSessionTimeout(HttpServletRequest request) {
        return WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER) == null;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");

        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

}
