<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<div id="head-nav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="fa fa-gear"></span>
            </button>
            <a class="navbar-brand" href="<%=basePath%>/console"><span><%=Constants.SYSTEM_NAME%></span></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <%
                    for (Menu item : sessionMenu) {
                %>
                <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><%=item.getMenuName()%> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <%
                            for (Menu subItem : item.getItems()) {
                                    String menuUrl = "javascript:;";
                                    if (subItem.getMenuUrl() != null) {
                                        menuUrl = basePath + subItem.getMenuUrl();
                                    }
                        %>
                        <%
                            if (subItem.getItems().size() > 0) {
                        %>
                        <li class="dropdown-submenu"><a href="javascript:;"><%=subItem.getMenuName()%></a>
                            <ul class="dropdown-menu">
                                <%
                                    for (Menu subSubItem : subItem.getItems()) {
                                                    menuUrl = "javascript:;";
                                                    if (subSubItem.getMenuUrl() != null) {
                                                        menuUrl = basePath + subSubItem.getMenuUrl();
                                                    }
                                %>
                                <li><a href="<%=menuUrl%>"><%=subSubItem.getMenuName()%></a></li>
                                <%
                                    }
                                %>
                            </ul></li>
                        <%
                            } else {
                        %>
                        <li><a href="<%=menuUrl%>/?menuId=<%=subItem.getMenuId()%>"><%=subItem.getMenuName()%></a></li>
                        <%
                            }
                        %>
                        <%
                            }
                        %>
                    </ul></li>
                <%
                    }
                %>
            </ul>
            <ul class="nav navbar-nav navbar-right user-nav">
                <li class="dropdown profile_menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="Avatar" src="<%=basePath%>/resources/images/avatar-small.jpg" /><%=sessionUser.getRealName()%><b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=basePath%>/console/user/profile">个人信息</a></li>
                        <li class="divider"></li>
                        <li><a href="<%=basePath%>/console/logout">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>