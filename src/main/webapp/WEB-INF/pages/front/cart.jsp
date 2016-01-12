<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.cart.Cart"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<jsp:include page="/WEB-INF/pages/front/include/style.jsp" />
<link href="<%=basePath%>/resources/lib/jquery/plugins/stepper/jquery.stepper.css" rel="stylesheet">

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

.stepper  {
	width: 100px;
}

.price-amount {
	font-size: 20px;
	font-weight: bold;
	color: #e53f43;
}
</style>

<%
	List<Cart> cartList = (List<Cart>)request.getAttribute("cartList");

	int total = 0, amount = 0;
%>

</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<% if (!cartList.isEmpty()) { %>
			<div class="table-responsive table-modest">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td class="text-center">商品</td>
							<td class="text-center" width="10%">数量</td>
							<td class="text-center" width="15%">礼券</td>
							<td class="text-center" width="15%">小计</td>
							<td class="text-center" width="10%">操作</td>
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
								<div class="product">
									<a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank">
										<img class="img-thumbnail" alt="<%=item.getProductName()%>" src="<%=item.getProductImgUrl()%>" width="140" height="90">
									</a>
									<div class="caption">
										<h5>
											<a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><% if (item.getProductName().length() > 25) { %><%=item.getProductName().substring(0, 25) + "..."%><% } else { %><%=item.getProductName()%><% } %></a>
										</h5>
									</div>
								</div>
							</td> 
							<td class="text-center"><input type="number" min="1" max="10" name="quantity" value="<%=item.getQuantity()%>" class="form-control"/></td>
							<td class="text-center"><%=item.getScore()%></td>
							<td class="text-center subtotal"><%=item.getScore() * item.getQuantity()%></td>
							<td class="text-center"><button class="btn btn-default btn-cart-remove" type="button">删除</button></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-offset-6 col-sm-6">
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h3 class="panel-title">总计</h3>
						</div>
						<div class="panel-body">
							<dl class="dl-horizontal">
								<dt>商品数量:</dt>
								<dd><span class="total" id="total"><%=total%></span> 件</dd>
								<dt>现金:</dt>
								<dd>0.0</dd>
								<dt>配送费用:</dt>
								<dd>0.0</dd>
							</dl>
							<hr>
							<dl class="dl-horizontal total">
								<dt>总计 :</dt>
								<dd><span class="price-amount" id="amount"><%=amount%></span></dd>
							</dl>
							<hr>
							<div class="clearfix">
								<div id="msg-error" class="alert alert-danger hide"></div>
								
								<a class="btn btn-default pull-left" href="<%=basePath%>/gift"><spanc lass="visible-xs">继续兑换</span></a>
								<form id="form-cart">
								<button id="btn-generate" class="btn btn-danger pull-right" type="submit" data-loading-text="订单生成中，请稍候...">生成订单</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<% } else { %>
		<div class="content-box text-center">
			<h4 class="special-heading">哇噢!</h4>
			<h5>购物车空空如也，快去看看有啥好兑换的。</h5>
			<br>
			<p>
				<a class="btn btn-danger" href="<%=basePath%>/gift">继续购物</a>
			</p>
		</div>
		<%
		    }
		%>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />

	<!-- ================== JavaScript ================== -->
	<script type="text/javascript" src="<%=basePath%>/resources/lib/jquery/plugins/stepper/jquery.stepper.js"></script>

	<script type="text/javascript">
		$(function() {
	        'use strict';
	        
	        $('input[type="number"]').stepper().change(function(e) {
	        	var $this = $(this);
	        	var id = $this.closest('tr').attr('id');
	        	var quantity = $this.val();
	        	var score = $this.closest('td').next().html();
	        	var subtotal = $this.closest('td').next().next();
	        	subtotal.html(parseInt(quantity, 10) * parseInt(score, 10));
	        	
	        	clearTimeout(window.timer);
	        	window.timer = setTimeout(function() {
	        		$.ajax({
    					type : 'POST',
    					url : '<%=basePath%>/cart/update-quantity',
    					data : {
    						cartId: id,
    						quantity: quantity
    					},
    					success : function(json) {
    						if (json.result) {
    							var total = 0;
    							$('input[name=quantity]').each(function(idx, item) {
    								total += parseInt($(item).val(), 10);
    							});
    							$('#total').html(total);
    							
    							var amount = 0;
    							$('.subtotal').each(function(idx, item) {
    								amount += parseInt($(item).html(), 10);
    							});
    							$('#amount').html(amount);
    						} else {
    						}
    					}
    				});
	        	}, 800);
	        });
	        
	        $('.btn-cart-remove').click(function() {
	        	var $tr = $(this).closest('tr');
    			var id = $tr.attr('id');
    			
    			if (confirm('此操作不可恢复！确定删除？')) {
    				$.ajax({
    					type : 'POST',
    					url : '<%=basePath%>/cart/remove',
    					data : {
    						cartId: id
    					},
    					success : function(json) {
    						if (json.result) {
    							// $tr.remove();
    							window.location = window.location;
    						} else {
    						}
    					}
    				});
    			}
	        });
	        
	        $('#form-cart').submit(function(e) {
	        	e.preventDefault();
	        	
		        var $btn = $('#btn-generate').button('loading');
	        	 
				$.ajax({
					type : 'POST',
					url : '<%=basePath%>/order/generate',
					success : function(json) {
						if (json.result) {
							setTimeout(function() {
								window.location = '<%=basePath%>/order/display';
							}, 2000);
						} else {
							setTimeout(function() {
								$('#msg-error').html(json.data + '。马上去 <a href="<%=basePath%>/member/recharge" data-target="#modal-recharge" data-toggle="modal">充值</a>。');
								$('#msg-error').removeClass('hide');
	    				        $('#msg-error').css({
	    					        'display' : 'block'
	    				        });
	    				        
	    				        $btn.button('reset');
							}, 2000);
						}
					}
				});
	        });

		});
	</script>
</body>
</html>