<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.product.ProductImg"%>
<%@page import="com.qihua.front.product.Product"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<link href="<%=basePath%>/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath%>/resources/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="<%=basePath%>/resources/lib/html5shiv.js"></script>
    <script src="<%=basePath%>/resources/lib/respond.min.js"></script>
<![endif]-->

<!-- global styles -->
<link href="<%=basePath%>/resources/css/front/style.css" rel="stylesheet">
<link href="<%=basePath%>/resources/css/front/responsive.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
#slider {
    overflow: hidden;
}

.manufacturer li {
	line-height: 30px;
}
</style>

<%
	Product product = (Product)request.getAttribute("product");
%>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<ol class="breadcrumb">
				<li><a href="<%=basePath%>/product/category?categoryId=<%=product.getCategory().getCategoryId()%>"><%=product.getCategory().getCategoryName()%></a></li>
				<li><a href="<%=basePath%>/product/category?categoryId=<%=product.getCategory().getSubcategoryId()%>"><%=product.getCategory().getSubcategoryName()%></a></li>
				<li class="active"><%=product.getProductName()%></li>
			</ol>
			<div class="row product-info full">
				<div class="col-sm-4 images-block">
					<div id="slider" class="flexslider">
						<ul class="slides">
							<% for (ProductImg img : product.getImages()) { %>
							<li data-thumb="<%=img.getImgUrl()%>"><img class="img-responsive thumbnail" alt="<%=product.getProductName()%>" src="<%=img.getImgUrl()%>"></li>
							<% } %>
						</ul>
					</div>
				</div>
				<div class="col-sm-8 product-details">
					<div class="panel-smart">
						<h2><%=product.getProductName()%></h2>
						<hr>
						<ul class="list-unstyled manufacturer">
							<li><span>货号:</span> <%=product.getProductCode()%></li>
							<li><span>物流运费：</span> 卖家承担邮费</li>
						</ul>
						<hr>
						<div class="price">
							<span class="price-head">礼券 :</span> <span class="price-new"><%=product.getScore()%></span>
						</div>
						<hr>

						<div id="msg-success" class="alert alert-success hide"></div>
						<div id="msg-error" class="alert alert-danger hide"></div>
						
						<div class="options">
							<div class="cart-button button-group">
								<button id="btn-buy" class="btn btn-default" type="button" data-loading-text="正在加入购物车，请稍候...">&nbsp;&nbsp;&nbsp;&nbsp;立即兑换&nbsp;&nbsp;&nbsp;&nbsp;</button>
								<button id="btn-cart" class="btn btn-danger" type="button" data-loading-text="正在加入购物车，请稍候..."><i class="fa fa-shopping-cart"></i>&nbsp;加入购物车</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="tabs-panel panel-smart">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab-a">商品详情</a></li>
				</ul>
				<div class="tab-content clearfix">
					<div id="tab-a" class="tab-pane active"><%=product.getDescription()%></div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />
	
	<script src="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.js"></script>

	<!-- ================== JavaScript ================== -->

	<script type="text/javascript">
		$(function() {
	        'use strict';

	        $('.gift').addClass('active');
	        
	        $('#slider').flexslider({
    			animation : 'slide',
    			slideshow: false,
    			controlNav: 'thumbnails'
    		});
	        
	        $('#btn-buy').click(function() {
	        	$(this).button('loading');
	        	
	        	$.ajax({
					type : 'POST',
					url : '<%=basePath%>/cart/add.json',
					data : {
						productId: '<%=product.getProductId()%>'
					},
					success : function(json) {
						if (json.result) {
				        } else {
					        window.location = '<%=basePath%>/member/login-register';
				        }
						
						$('#msg-success, #msg-error').addClass('hide');

						if (json.result) {
							setTimeout(function() {
								window.location = '<%=basePath%>/cart';
							}, 1000);
						} else {
							setTimeout(function() {
								$('#msg-error').html(json.data);
								$('#msg-error').removeClass('hide');
	    				        $('#msg-error').css({
	    					        'display' : 'block'
	    				        });
							}, 1000);
						}
					}
				});
	        });
	        
	        $('#btn-cart').click(function() {
	        	var $btn = $(this); 
	        	$btn.button('loading');
	        	
	        	$.ajax({
					type : 'POST',
					url : '<%=basePath%>/cart/add.json',
					data : {
						productId: '<%=product.getProductId()%>'
					},
					success : function(json) {
						$('#msg-success, #msg-error').addClass('hide');
						
						if (json.result) {
							setTimeout(function() {
								$('#msg-success').html('成功加入购物车。');
								$('#msg-success').removeClass('hide');
	    				        $('#msg-success').css({
	    					        'display' : 'block'
	    				        });
	    				        
								$btn.button('reset');
							}, 1000);
							
							setTimeout(function() {
								$('#msg-success').addClass('hide');
							}, 3000);
						} else {
							setTimeout(function() {
								$('#msg-error').html(json.data);
								$('#msg-error').removeClass('hide');
	    				        $('#msg-error').css({
	    					        'display' : 'block'
	    				        });
	    				        
	    				        $btn.button('reset'); 
							}, 1000);
						}
					}
				});
	        });
	        
        });
	</script>
</body>
</html>