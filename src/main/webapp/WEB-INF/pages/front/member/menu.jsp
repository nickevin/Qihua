<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<style type="text/css">
.side-heading {
	margin-bottom: 0px;
}
</style>

<div class="col-md-3">
	<h3 class="side-heading">
		<i class="fa fa-cogs fa-fw"></i>&nbsp;帐号设置
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/member/profile"><i class="fa fa-chevron-right"></i> 个人资料</a> 
		<a class="list-group-item" href="<%=basePath%>/member/password"><i class="fa fa-chevron-right"></i> 修改密码</a>
	</div>
	<h3 class="side-heading">
		<i class="fa fa-yen"></i>&nbsp;交易管理
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/member/order"><i class="fa fa-chevron-right"></i> 我的订单</a> 
		<a class="list-group-item" href="<%=basePath%>/card"><i class="fa fa-chevron-right"></i> 礼券管理</a>
	</div>
</div>