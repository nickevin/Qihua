<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>

<%@page import="org.springframework.web.util.WebUtils"%>

<%@page import="com.qihua.common.Constants"%>
<%@page import="com.qihua.console.user.User"%>
<%@page import="com.qihua.console.security.Menu"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<%
    User sessionUser = (User) WebUtils.getSessionAttribute(request, Constants.SESSION_USER);
    List<Menu> sessionMenu = (ArrayList<Menu>) WebUtils.getSessionAttribute(request, Constants.SESSION_MENU);
%>