<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<footer id="footer-area">
	<div class="footer-links row">
		<div class="col-md-2 col-md-offset-1 col-sm-6">
			<h5>
				<i class="fa fa-users"></i>&nbsp;关于我们
			</h5>
			<ul>
				<li><a href="<%=basePath%>/guide/about">企业简介</a></li>
				<li><a href="<%=basePath%>/guide/brand">品牌文化</a></li>
				<li><a href="<%=basePath%>/guide/intro">公司介绍</a></li>
			</ul>
		</div>
		<div class="col-md-2 col-md-offset-1 col-sm-6">
			<h5>
				<i class="fa fa-wheelchair"></i>&nbsp;帮助中心
			</h5>
			<ul>
				<li><a href="<%=basePath%>/guide/faq">常见问题</a></li>
				<li><a href="<%=basePath%>/guide/reset">找回密码</a></li>
				<li><a href="<%=basePath%>/guide/process">兑换流程</a></li>
			</ul>
		</div>
		<div class="col-md-2 col-md-offset-1 col-sm-6">
			<h5>
				<i class="fa fa-truck"></i>&nbsp;配送方式
			</h5>
			<ul>
				<li><a href="<%=basePath%>/guide/agreement">交易条款</a></li>
				<li><a href="<%=basePath%>/guide/distribution">配送时间和范围</a></li>
				<li><a href="<%=basePath%>/guide/logistics">配送说明</a></li>
			</ul>
		</div>
		<div class="col-md-2 col-md-offset-1 col-sm-6">
			<h5>
				<i class="fa fa-star"></i>&nbsp;战略伙伴
			</h5>
			<ul>
				<li><a href="<%=basePath%>/guide/advertisement">广告合作</a></li>
				<li><a href="<%=basePath%>/guide/links">合作网站</a></li>
			</ul>
		</div>
	</div>
</footer>

<jsp:include page="/WEB-INF/pages/front/include/script.jsp" />