<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<style type="text/css">
.side-heading {
	margin-bottom: 0px;
}
</style>

<div class="col-md-3">
	<h3 class="side-heading">
		<i class="fa fa-users"></i>&nbsp;关于我们
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/guide/about"><i class="fa fa-chevron-right"></i> 企业简介</a>
		<a class="list-group-item" href="<%=basePath%>/guide/brand"><i class="fa fa-chevron-right"></i> 品牌文化</a> 
		<a class="list-group-item" href="<%=basePath%>/guide/intro"><i class="fa fa-chevron-right"></i> 公司介绍</a>
	</div>
	<h3 class="side-heading">
		<i class="fa fa-wheelchair"></i>&nbsp;帮助中心
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/guide/faq"><i class="fa fa-chevron-right"></i> 常见问题</a> 
		<a class="list-group-item" href="<%=basePath%>/guide/reset"><i class="fa fa-chevron-right"></i> 找回密码</a> 
		<a class="list-group-item" href="<%=basePath%>/guide/process"><i class="fa fa-chevron-right"></i> 兑换流程</a>
	</div>
	<h3 class="side-heading">
		<i class="fa fa-truck"></i>&nbsp;配送方式
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/guide/agreement"><i class="fa fa-chevron-right"></i> 交易条款</a> 
		<a class="list-group-item" href="<%=basePath%>/guide/distribution"><i class="fa fa-chevron-right"></i> 配送时间和范围</a> 
		<a class="list-group-item" href="<%=basePath%>/guide/logistics"><i class="fa fa-chevron-right"></i> 配送说明</a>
	</div>
	<h3 class="side-heading">
		<i class="fa fa-star"></i>&nbsp;战略伙伴
	</h3>
	<div class="list-group">
		<a class="list-group-item" href="<%=basePath%>/guide/advertisement"><i class="fa fa-chevron-right"></i> 广告合作</a>
		<a class="list-group-item" href="<%=basePath%>/guide/links"><i class="fa fa-chevron-right"></i> 合作网站</a>
	</div>
</div>
