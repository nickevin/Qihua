<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.product.ProductImg"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<jsp:include page="/WEB-INF/pages/front/include/style.jsp" />

<!-- global styles -->
<link href="<%=basePath%>/resources/css/front/style.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
#slider {
    margin-bottom: 15px;
    overflow: hidden;
}
</style>

<%
    List<ProductImg> centerImgList = (List<ProductImg>) request.getAttribute("centerImgList");
    List<ProductImg> bottomImgList = (List<ProductImg>) request.getAttribute("bottomImgList");
%>
</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="slider" class="flexslider">
			<ul class="slides">
				<%
				    for (ProductImg img : centerImgList) {
				%>
				<li><img src="<%=img.getImgUrl()%>" class="img-responsive" /></li>
				<%
				    }
				%>
			</ul>
		</div>

		<div class="col3-banners">
			<ul class="row list-unstyled">
				<%
				    for (ProductImg img : bottomImgList) {
				%>
				<li class="col-sm-4"><div class="featured-col"><img class="img-responsive" src="<%=img.getImgUrl()%>"></div></li>
				<%
				    }
				%>
				<li class="col-sm-4"><div class="featured-col"><iframe height="198" width="335" src="http://player.youku.com/embed/XNzcwMzc1NDky" frameborder=0 allowfullscreen></iframe></div></li>
			</ul>
		</div>
		
		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />

    <!-- ================== JavaScript ================== -->
	<script src="<%=basePath%>/resources/lib/jquery/plugins/flexslider/jquery.flexslider.js"></script>

    <script type="text/javascript">
        $(function() {
        	'use strict';
        	
        	$('#slider').flexslider({
    			animation : 'slide',
    			slideshowSpeed:5000
    		});
        });
	</script>
</body>
</html>