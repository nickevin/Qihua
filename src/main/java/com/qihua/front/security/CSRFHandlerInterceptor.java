package com.qihua.front.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            return true;
        } else {
            String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
            String requestToken = CSRFTokenManager.getTokenFromRequest(request);

            // String referrer = request.getHeader("referer");

            if (isAjaxRequest(request)) {
                String requestHeader = request.getHeader(CSRFTokenManager.CSRF_PARAM_NAME);
                if (sessionToken.equals(requestHeader)) {
                    return true;
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");

                    return false;
                }
            }

            if (sessionToken.equals(requestToken)) {
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");

                return false;
            }
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");

        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

}
