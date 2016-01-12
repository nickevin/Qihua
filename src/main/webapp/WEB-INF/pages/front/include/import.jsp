<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="java.util.*"%>

<%@page import="org.springframework.web.util.WebUtils"%>

<%@page import="com.qihua.common.Constants"%>
<%@page import="com.qihua.front.member.Member"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    
    Member member = null;
    Object _member = WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);
    if (_member != null) {
        member = (Member)_member;
    }
%>

