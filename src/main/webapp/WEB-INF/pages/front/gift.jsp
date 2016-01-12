<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.product.Category"%>
<%@page import="com.qihua.front.product.ProductImg"%>
<%@page import="com.qihua.front.product.Product"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<jsp:include page="/WEB-INF/pages/front/include/style.jsp" />
<link href="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.css" rel="stylesheet">

<!-- global styles -->
<link href="<%=basePath%>/resources/css/front/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
#slider {
	margin-bottom: 5px;
	overflow: hidden;
}

#query {
	margin-top: 0px;
	margin-bottom: 10px;
}

#query .input-group {
	width: 270px;
}

#query .input-group .form-control {
	height: 40px;
}

#query .input-group .btn {
	background: #ccc;
	height: 40px;
}

.category-menu {
	background: url("<%=basePath%>/resources/images/goods-menu-bg.png") no-repeat scroll;
	left: 15px;
	overflow: visible;
	padding: 18px 0 0;
	top: 44px;
	width: 270px;
	box-shadow: 4px 1px 5px #999999;
	height: 369px;
}

.category-menu .margin-top {
	border-top: medium none;
}

.category-menu .item {
	height: 30px;
	width: 270px;
}

.margin-top {
	margin-top: 33px;
}

.category-menu .item .product {
	width: 270px;
}

.category-menu .item .product h3 {
	margin-bottom: 16px;
	margin-top: 0px;
	border-width: 1px 0;
	font-size: 12px;
	font-weight: normal;
	height: 30px;
	line-height: 30px;
	margin-left: 4px;
	padding-left: 30px;
	width: 250px;
}

.category {
	color: #666666;
	font-size: 15px;
	font-weight: bold;
	margin-right: 17px;
}

.subcategory {
	color: #888888;
	line-height: 24px;
	margin-right: 7px;
}

.subcategory a {
	color: #888;
	font-size: 12px;
	font-weight: normal;
	margin-right: 0;
}

.side-heading {
	margin-top: 20px;
}

.tabs-panel .tab-content {
	padding: 0px;
}

.tab-content .col-md-3, .hottestList-product .col-md-2 {
	padding-left: 0px;
	padding-right: 0px;
}

.hottestList-product .row {
	margin: 0px;
}

.tab-content .product-col, .hottestList-product .product-col {
	text-align: center;
	background: #fff;
	margin-bottom: -1px;
	margin-right: -1px;
	border-bottom: 1px solid #d3d3d3;
    border-right: 1px solid #d3d3d3;
}

.declaration {
	text-align: center;
}

.declaration li {
	border-right-color: #111;
	border-right-width: 1px;
	border-right-style: solid;
}

.declaration li.last {
	border-right-width: 0px;
}
</style>

<%
    // List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
    List<ProductImg> recommendList = (List<ProductImg>) request.getAttribute("recommendList");
    Product flashSale = (Product) request.getAttribute("flashSale");
    List<Product> hottestList = (List<Product>) request.getAttribute("hottestList");
    List<Product> tabAList = (List<Product>) request.getAttribute("tabAList");
    List<Product> tabBList = (List<Product>) request.getAttribute("tabBList");
    List<Product> tabCList = (List<Product>) request.getAttribute("tabCList");
    
    ProductImg a = (ProductImg) request.getAttribute("a");
    ProductImg b = (ProductImg) request.getAttribute("b");
    ProductImg c = (ProductImg) request.getAttribute("c");
    ProductImg d = (ProductImg) request.getAttribute("d");
    ProductImg e = (ProductImg) request.getAttribute("e");
    ProductImg f = (ProductImg) request.getAttribute("f");
%>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<div class="col-md-3">
					<div id="query">
						<div class="input-group">
							<input type="text" placeholder="搜索" class="form-control input-sm">
							<span class="input-group-btn">
								<button type="button" class="btn btn-default"><i class="fa fa-search"></i></button>
							</span>
						</div>
					</div>
					<div class="category-menu">
						<%--for (Category item : categoryList) {%>
						<% }--%>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=100">服装服饰</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=100100">品牌女装</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=100150">品牌男装</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=150">日化用品</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=150100">美妆用品</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=150150">基础护理</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=200">鞋类箱包</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=200100">时尚女鞋</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=200150">品牌男鞋</a></span></h3></div></div>
						<div class="item margin-top"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=250">居家生活</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=250100">工艺饰品</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=250150">家居用品</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=300">婴幼专区</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=300100">儿童用品</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=300150">洗护用品</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=350">电器专区</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=350100">家用电器</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=350150">数码通讯</a></span></h3></div></div>
						<div class="item margin-top"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=400">健康生活</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=400100">生态食品</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=400150">养生保健</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=450">休闲旅游</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=450100">景区门票</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=450150">自驾旅游</a></span></h3></div></div>
						<div class="item"><div class="product"><h3><a class="category" href="<%=basePath%>/product/category?categoryId=500">每周一品</a><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=500100">特价产品</a></span><span class="subcategory"><a href="<%=basePath%>/product/category?categoryId=500150">热门兑换</a></span></h3></div></div>
					</div>
				</div>
				<div class="col-md-9">
					<div id="slider" class="flexslider">
						<ul class="slides">
							<% for (ProductImg item : recommendList) { %>
							<li><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><img src="<%=item.getImgUrl()%>" class="img-responsive" /></a></li>
							<% } %>
						</ul>
					</div>
					<div class="guide-step">
						<a href=""><img src="<%=basePath%>/resources/images/guide-step.png"></a>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="hottestList-product">
						<h2 class="product-head">热门兑换</h2>
						<div class="row">
							<% for (Product item : hottestList) { %>
							<div class="col-md-2 col-sm-4"> <div class="product-col"> <div class="image"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><img class="img-responsive lazy" alt="<%=item.getProductName()%>" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=item.getImage().getImgUrl()%>"></a></div> <div class="caption"> <h4><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><% if (item.getProductName().length() > 8) { %><%=item.getProductName().substring(0, 8) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></h4> <div class="price"><span class="price-new"><%=item.getScore()%></span> 礼券</span></div> </div> </div> </div>
							<% } %>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-9">
					<div class="tabs-panel panel-smart">
						<ul class="nav nav-tabs nav-justified">
							<li class="active"><a href="#tab-a">0 ~ 199 礼券区</a></li>
							<li><a href="#tab-b">199 ~ 599 礼券区</a></li>
							<li><a href="#tab-c">599 ~ 999 礼券区</a></li>
						</ul>
						<div class="tab-content clearfix">
							<div id="tab-a" class="tab-pane active">
								<% for (Product item : tabAList) { %>
								<div class="col-md-3 col-sm-6"> <div class="product-col"> <div class="image"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><img class="img-responsive lazy" alt="<%=item.getProductName()%>" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=item.getImage().getImgUrl()%>"></a></div> <div class="caption"> <h4><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><% if (item.getProductName().length() > 8) { %><%=item.getProductName().substring(0, 8) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></h4> <div class="price"><span class="price-new"><%=item.getScore()%></span> 礼券</span></div> </div> </div> </div>
								<% } %>
							</div>
							<div id="tab-b" class="tab-pane">
								<% for (Product item : tabBList) { %>
								<div class="col-md-3 col-sm-6"> <div class="product-col"> <div class="image"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><img class="img-responsive lazy" alt="<%=item.getProductName()%>" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=item.getImage().getImgUrl()%>"></a></div> <div class="caption"> <h4><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><% if (item.getProductName().length() > 8) { %><%=item.getProductName().substring(0, 8) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></h4> <div class="price"><span class="price-new"><%=item.getScore()%></span> 礼券</span></div> </div> </div> </div>
								<% } %>
							</div>
							<div id="tab-c" class="tab-pane">
								<% for (Product item : tabCList) { %>
								<div class="col-md-3 col-sm-6"> <div class="product-col"> <div class="image"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>" target="_blank"><img class="img-responsive lazy" alt="<%=item.getProductName()%>" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=item.getImage().getImgUrl()%>"></a></div> <div class="caption"> <h4><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><% if (item.getProductName().length() > 8) { %><%=item.getProductName().substring(0, 8) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></h4> <div class="price"><span class="price-new"><%=item.getScore()%></span> 礼券</span></div> </div> </div> </div>
								<% } %>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<h4 class="side-heading">限时抢购</h4>
					<div class="product-col text-center">
						<div class="image">
							<a href="<%=basePath%>/product/display?productId=<%=flashSale.getProductId()%>" target="_blank"><img class="img-responsive lazy" alt="<%=flashSale.getProductName()%>" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=flashSale.getFlashSaleImage().getImgUrl()%>"></a>
						</div>
						<div class="caption">
							<h4>
								<a href="<%=basePath%>/product/display?productId=<%=flashSale.getProductId()%>" target="_blank"><% if (flashSale.getProductName().length() > 8) { %><%=flashSale.getProductName().substring(0, 8) + "..."%><% } else { %><%=flashSale.getProductName()%><% } %></a>
							</h4>
							<h3>限时  <%=flashSale.getCountdown()%> 天</h3>
							<div class="price">
								<span class="price-new"><%=flashSale.getScore()%></span> 礼券</span>
							</div>
							<div class="cart-button button-group">
								<button id="btn-buy" productId="<%=flashSale.getProductId()%>" class="btn btn-danger" type="button"><i class="fa fa-shopping-cart"></i>&nbsp;立即抢兑 </button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-5"><a href="<%=basePath%>/product/display?productId=<%=a.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=a.getImgUrl()%>"></a></div>
				<div class="col-md-5">
					<div class="row">
						<div class="col-md-6"><a href="<%=basePath%>/product/display?productId=<%=b.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=b.getImgUrl()%>"></a></div>
						<div class="col-md-6"><a href="<%=basePath%>/product/display?productId=<%=c.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=c.getImgUrl()%>"></a></div>
					</div>
					<div class="row" style="margin-top: 12px;">
						<div class="col-md-12"><a href="<%=basePath%>/product/display?productId=<%=d.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=d.getImgUrl()%>"></a></div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="row">
						<div class="col-md-12"><a href="<%=basePath%>/product/display?productId=<%=e.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=e.getImgUrl()%>"></a></div>
					</div>
					<div class="row" style="margin-top: 12px;">
						<div class="col-md-12"><a href="<%=basePath%>/product/display?productId=<%=f.getProductId()%>" target="_blank"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=f.getImgUrl()%>"></a></div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<ul class="list-unstyled list-inline declaration">
				<li><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=basePath%>/resources/images/declaration-1.png"></li>
			    <li><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=basePath%>/resources/images/declaration-2.png"></li>
			    <li><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=basePath%>/resources/images/declaration-3.png"></li>
			    <li><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=basePath%>/resources/images/declaration-4.png"></li>
			    <li class="last"><img class="lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=basePath%>/resources/images/declaration-5.png"></li>
			</ul>
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
	            slideshowSpeed : 5000
	        });

	        $('.nav-tabs a').hover(function(e) {
		        e.preventDefault();
		        
		        $(this).tab('show');
	        }).on('shown.bs.tab', function (e) {
	        	$(document).trigger('scroll');
	        });
	        
			$('#btn-buy').click(function() {
				$.ajax({
					type : 'POST',
					url : '<%=basePath%>/cart/add',
					data : {
						productId: $(this).attr('productId')
					},
					success : function(json) {
						if (json.result == null) {
							$('#modal-login').modal('show');
							
							return;
						}
						
						if (json.result) {
							window.location = '<%=basePath%>/cart';
						} else {
							setTimeout(function() {
								alert(json.data)
							}, 1000);
						}
					}
				});
			});
        });
	</script>
</body>
</html>