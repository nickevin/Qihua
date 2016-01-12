<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.order.Order"%>
<%@page import="com.qihua.front.order.OrderItem"%>

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
td a, td a:hover {
	color: #333;
}

.color-red {
	font-size: 18px;
	font-weight: bold;
	color: #ef4135;
}
</style>

<%
    List<Order> orderList = (List<Order>) request.getAttribute("orderList");
%>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<jsp:include page="/WEB-INF/pages/front/member/menu.jsp" />

				<div class="col-md-9">
					<%
					    if (orderList.isEmpty()) {
					%>
					<%
					    } else {
					%>
					<%
					    for (Order item : orderList) {
					%>
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h4 class="panel-title">订单信息</h4>
						</div>
						<div class="panel-body">
							<dl class="dl-horizontal">
								<dt>订单号:</dt>
								<dd><%=item.getOrderNo()%></dd>
								<dt>订单状态:</dt>
								<dd>
									<%
									    if (item.getStatus() == Constants.ORDER_TYPE_PENDING) {
									%><%=Constants.ORDER_TYPE_PENDING_NAME%>
									<%
									    } else if (item.getStatus() == Constants.ORDER_TYPE_SHIPPED) {
									%><%=Constants.ORDER_TYPE_SHIPPED_NAME%>
									<%
									    } else if (item.getStatus() == Constants.ORDER_TYPE_FINISHED) {
									%><%=Constants.ORDER_TYPE_FINISHED_NAME%>
									<%
									    }
									%>
								</dd>
								<dt>收货人:</dt>
								<dd><%=item.getRecipient()%></dd>
								<dt>手机:</dt>
								<dd><%=item.getMobile()%></dd>
								<dt>邮编:</dt>
								<dd><%=item.getZip()%></dd>
								<dt>收货地址:</dt>
								<dd><%=item.getAddress()%></dd>
								<dt>备注:</dt>
								<dd><%=item.getRemarks()%></dd>
								<dt>下单时间:</dt>
								<dd><%=item.getInsertTime()%></dd>
							</dl>

							<div class="table-responsive table-modest">
								<table class="table table-bordered">
									<thead>
										<tr>
											<td class="text-center">商品名称</td>
											<td class="text-center">数量</td>
											<td class="text-center">礼券</td>
											<td class="text-center">小计</td>
										</tr>
									</thead>
									<tbody>
										<%
											long amount = 0;
										
										    for (OrderItem subItem : item.getItems()) {
										        amount += subItem.getQuantity() * subItem.getScore();
										%>
										<tr>
											<td class="text-center"><a href="<%=basePath%>/product/display?productId=<%=subItem.getProductId()%>"><%=subItem.getProductName()%></a></td>
											<td class="text-center"><%=subItem.getQuantity()%></td>
											<td class="text-center"><%=subItem.getScore()%></td>
											<td class="text-center"><%=subItem.getQuantity() * subItem.getScore()%></td>
										</tr>
										<%
										    }
										%>
									</tbody>
								</table>

								<dl class="dl-horizontal total">
									<dt>总计 :</dt>
									<dd>
										<span id="amount" class="price-amount color-red"><%=amount%></span>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<%
					    }
					%>
					<%
					    }
					%>
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