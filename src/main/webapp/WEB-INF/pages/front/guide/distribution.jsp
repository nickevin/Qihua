<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

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
</style>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<jsp:include page="/WEB-INF/pages/front/guide/menu.jsp" />	
				
				<div class="col-md-9">
					<ol class="breadcrumb">
						<li><strong>配送方式 </strong></li>
						<li class="active">配送时间和范围</li>
					</ol>
					<div class="page-box">
						<p>下单后我们会在48小时内发货，节假日顺延，货品从出仓起，二到三天到货。（由于派送地域不同，最终以快递配送时间为主）。拍下商品后请耐心等待，有任何问题我们会与您及时联系，谢谢！</p>
						<ul>
							<li>提示：</li>
							<li>1、订购成功后，您可以登陆柒花官网网站在“用户中心”-“我的订单”中查看订单状况，</li>
							<li>2、为了确保商品及时准确的配送到您手中，请您在下订单时填写详细的收货人信息及联系方式。</li>
						</ul>
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