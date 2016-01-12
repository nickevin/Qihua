<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.cart.Cart"%>
<%@page import="com.qihua.front.order.Order"%>

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
.product {
	text-align: center;
}

.caption h5 {
	font-size: 14px;
}

.product a, .product a:hover {
	color: #333;
}

.dl-horizontal dt {
	line-height: 25px;
}

.price-amount {
	font-size: 18px;
	font-weight: bold;
	color: #e53f43;
}
</style>

<%
	Order lastOrder = (Order)request.getAttribute("lastOrder");
	List<Cart> cartList = (List<Cart>)request.getAttribute("cartList");

	int total = 0, amount = 0;
%>

</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="table-responsive table-modest">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td class="text-center">商品</td>
							<td class="text-center">数量</td>
							<td class="text-center">礼券</td>
							<td class="text-center">小计</td>
						</tr>
					</thead>
					<tbody>
						<% 
							for (Cart item : cartList) { 
							    total += item.getQuantity();
							    amount += item.getScore() * item.getQuantity();
						%>
						<tr id="<%=item.getCartId()%>">
							<td class="text-center">
								<div class="product"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><% if (item.getProductName().length() > 15) { %><%=item.getProductName().substring(0, 15) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></div>
							</td> 
							<td class="text-center"><%=item.getQuantity()%></td>
							<td class="text-center"><%=item.getScore()%></td>
							<td class="text-center"><%=item.getScore() * item.getQuantity()%></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			
			<form id="form-order" method="post">
			<div class="row" style="margin-top: 10px">
				<div class="col-sm-6">
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h3 class="panel-title">配送信息</h3>
						</div>
						<div class="panel-body">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">收货人 :</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="recipient" data-rule-required="true" value="<%=lastOrder.getRecipient()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">手机 :</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="mobile" value="<%=member.getMobile()%>" data-rule-required="true" data-rule-mobile="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">邮编 :</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="zip" data-rule-required="true" data-rule-number="true" data-msg-number="请输入正确的邮编" value="<%=lastOrder.getZip()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">收货地址 :</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" name="address" id="address" data-rule-required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">备注 :</label>
									<div class="col-sm-8">
										<textarea type="text" class="form-control" name="remarks"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h3 class="panel-title">商品信息</h3>
						</div>
						<div class="panel-body">
							<dl class="dl-horizontal">
								<dt>商品数量:</dt>
								<dd><%=total%> 件</dd>
								<dt>现金:</dt>
								<dd>0.0</dd>
								<dt>配送费用:</dt>
								<dd>0.0</dd>
							</dl>
							<hr>
							<dl class="dl-horizontal total">
								<dt>总计 :</dt>
								<dd><span class="price-amount"><%=amount%></span></dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-offset-6 col-sm-6">
					<div id="msg-success" class="alert alert-success hide"></div>
					<div id="msg-error" class="alert alert-danger hide"></div>
					
					<button id="btn-confirm" class="btn btn-danger pull-right" type="submit" data-loading-text="确认中，请稍候...">确认订单</button>
				</div>
			</div>
			</form>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />
	
	<!-- ================== JavaScript ================== -->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zOgd60lPC3YPC0grh9utxmiO"></script>
	
	<script type="text/javascript">
		$(function() {
	        'use strict';
	        
	        $('#form-order').validate({
				focusInvalid : false,
				submitHandler : function(form) {
			        var $btn = $('#btn-confirm').button('loading');

					$.ajax({
						type : 'POST',
						url : '<%=basePath%>/order/confirm',
						data : {
						    recipient: $('#form-order input[name=recipient]').val(),
						    mobile: $('#form-order input[name=mobile]').val(),
						    email: $('#form-order input[name=email]').val(),
						    zip: $('#form-order input[name=zip]').val(),
						    address: $('#form-order input[name=address]').val(),
						    remarks: $('#form-order textarea[name=remarks]').val()
						},
						success : function(json) {
							$('#msg-success, #msg-error').addClass('hide');
							
							if (json.result) {
								setTimeout(function() {
									$('#msg-success').html('订单确认成功，即将返回个人中心。');
									$('#msg-success').removeClass('hide');
		    				        $('#msg-success').css({
		    					        'display' : 'block'
		    				        });
		    				        
									$btn.button('reset');
								}, 1000);
								
								setTimeout(function() {
									window.location = '<%=basePath%>/member/order';
								}, 3000);
							} else {
								setTimeout(function() {
									$('#msg-error').html(json.data + '。马上去 <a href="#modal-recharge" data-toggle="modal">充值</a>。');
									$('#msg-error').removeClass('hide');
		    				        $('#msg-error').css({
		    					        'display' : 'block'
		    				        });
		    				        
									$btn.button('reset'); 
								}, 2000);
							}
						}
					});
				}
			});
	        
	        var addressValue;
        	var address = new BMap.Autocomplete({
        		'input' : 'address'
        	});
    
        	setTimeout(function() {
		        $('#address').val('<%=lastOrder.getAddress()%>');
	        }, 500);
	        
		});
	</script>
</body>
</html>