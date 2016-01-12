<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.common.PageModel"%>
<%@page import="com.qihua.front.product.ProductQueryParameter"%>
<%@page import="com.qihua.front.product.Product"%>
<%@page import="com.qihua.front.product.Category"%>

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
.category, .subcategory {
	border-top: 3px solid #ccc; 
}

.category li {
	font-size: 14px;
}

.subcategory li {
	font-size: 14px;
	margin-right: 20px;
}

.subcategory>li+li:before {
	color: #878a8c;
	content: none;
}

.subcategory>li+li:before {
	color: #ccc;
	content: none;
	padding: 0 5px;
}

.product-col {
	text-align: center;
	margin-bottom: 10px;
}

</style>

<%
	ProductQueryParameter queryParam = (ProductQueryParameter) request.getAttribute("queryParam");

	Category category = (Category) request.getAttribute("category");
    List<Category> subcategoryList = (List<Category>) request.getAttribute("subcategoryList");
    
	PageModel<Product> pageModel = (PageModel<Product>) request.getAttribute("pageModel");
    List<Product> productList = pageModel.getResultSet();
%>

</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />
		
		<div id="main-container">
			<div class="row">
				<div class="col-md-12">
					<ol class="breadcrumb category">
						<li>当前所在分类：<a href="<%=basePath%>/product/category?categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%></a></li>
					</ol>
					<ol class="breadcrumb subcategory">
						<% for (Category item : subcategoryList) { %>
						<li><a href="<%=basePath%>/product/category?categoryId=<%=item.getSubcategoryId()%>"><%=item.getSubcategoryName()%></a></li>
						<% } %>
					</ol>
					<div class="product-filter">
						<form name="form-category" action="<%=basePath%>/product/category">
							<input name="categoryId" type="hidden" value="<%=queryParam.getCategoryId()%>">

							<div class="row">
								<div class="col-md-3">
									<div class="display">
										<a class="active" href="javascript:;"> <i title="" class="fa fa-th"></i></a>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<label class="control-label">排序方式</label>
								</div>
								<div class="col-md-2 text-right">
									<select class="form-control" name="order">
										<option value="lastest">最新</option>
										<option value="sales">销量</option>
									</select>
								</div>
								<div class="col-md-2 text-right">
									<label class="control-label">礼券区间</label>
								</div>
								<div class="col-md-2 text-right">
									<div class="row">
										<div class="col-md-5" style="padding-right: 0px; padding-left: 0px;"><input type="text" class="form-control" name="lowScore" value="<%=queryParam.getLowScore()%>"></div>
										<div class="col-md-2">-</div>
										<div class="col-md-5" style="padding-right: 0px; padding-left: 0px;"><input type="text" class="form-control" name="highScore" value="<%=queryParam.getHighScore()%>"></div>
									</div>
								</div>
								<div class="col-md-1 text-right">
									<button type="submit" class="btn btn-danger" id="btn-login">查询</button>
								</div>
							</div>
						</form>
					</div>
					<div class="row">
						<% for (Product item : productList) { %>
						<div class="col-md-3 col-sm-6"> <div class="product-col"> <div class="image"><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><img class="img-responsive lazy" src="<%=basePath%>/resources/images/product/placeholder.gif" data-original="<%=item.getImage().getImgUrl()%>" alt="<%=item.getProductName()%>"></a></div> <div class="caption"> <h4><a href="<%=basePath%>/product/display?productId=<%=item.getProductId()%>"><% if (item.getProductName().length() > 12) { %><%=item.getProductName().substring(0, 12) + "..."%><% } else { %><%=item.getProductName()%><% } %></a></h4> <div class="price"><span class="price-new"><%=item.getScore()%></span> 礼券</div> </div> </div> </div>
						<% } %>
					</div>
					<div class="row">
						<div class="col-sm-6 pagination-block">
							<ul class="pagination">
								<% if (pageModel.hasPrevious()) { %>
								<li class="prev"><a class="prev" href="<%=basePath%>/product/category?pageNo=<%=pageModel.getPrevious()%>&lowScore=<%=queryParam.getLowScore()%>&highScore=<%=queryParam.getHighScore()%>">上一页</a></li>
								<% } else { %>
								<li class="prev disabled"><a href="javascript:;"><span class="fa fa-angle-left"></span>&nbsp;上一页</a></li>
								<% } %>
								
								<%  if (pageModel.hasNext()) { %>
								<li class="next"><a href="<%=basePath%>/product/category?pageNo=<%=pageModel.getNext()%>&lowScore=<%=queryParam.getLowScore()%>&highScore=<%=queryParam.getHighScore()%>">下一页&nbsp;<span class="fa fa-angle-right"></span></a></li>
								<% } else { %>
								<li class="next disabled"><a href="javascript:;">下一页&nbsp;<span class="fa fa-angle-right"></span></a></li>
								<%} %>
							</ul>
						</div>
						<div class="col-sm-6 results"><%=pageModel.getTotalElements()%> 件商品，共 <%=pageModel.getTotalPages()%> 页</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />
	
	<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.lazyload.js"></script>
	<script src="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.js"></script>

	<!-- ================== JavaScript ================== -->

	<script type="text/javascript">
		$(function() {
	        'use strict';

	        $('img.lazy').lazyload();

        });
	</script>
</body>
</html>