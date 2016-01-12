<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.member.MemberCard"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<jsp:include page="/WEB-INF/pages/front/include/style.jsp" />

<!-- global styles -->
<link href="<%=basePath%>/resources/css/front/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
.color-red {
	font-weight: bold;
	color: #ef4135;
}
</style>

<%
    List<MemberCard> cardList = (List<MemberCard>) request.getAttribute("cardList");
%>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<jsp:include page="/WEB-INF/pages/front/member/menu.jsp" />
				
				<div class="col-md-9">
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h4 class="panel-title">礼券管理</h4>
						</div>
						<div class="panel-body">
							<dl class="dl-horizontal">
								<dt>我的礼券 :</dt>
								<dd>
									<span class="price-amount color-red"><%=member.getScore()%></span>
								</dd>
							</dl>
							
							<% if (!cardList.isEmpty()) { %>
							<table class="table table-bordered">
								<thead>
									<tr>
										<td class="text-center">卡号</td>
										<td class="text-center">充值密码</td>
										<td class="text-center">礼券</td>
										<td class="text-center">充值时间</td>
									</tr>
								</thead>
								<tbody>
									<%
										long amount = 0;
									
                                    	for (MemberCard item : cardList) {
                                    	    amount += item.getScore();
									%>
									<tr>
										<td class="text-center"><%=item.getCardNo()%></td>
										<td class="text-center"><%=item.getCardPassword()%></td>
										<td class="text-center"><%=item.getScore()%></td>
										<td class="text-center"><%=item.getInsertTime()%></td>
									</tr>
									<%
                                    	}
									%>
								</tbody>
							</table>
							
							<dl class="dl-horizontal total">
								<dt>充值总额 :</dt>
								<dd>
									<span id="amount" class="price-amount color-red"><%=amount%></span>
								</dd>
							</dl>
							<% } %>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />
	
	<!-- ================== JavaScript ================== -->

	<script type="text/javascript">
		$(function() {
	        'use strict';
	        
        });
	</script>
</body>
</html>