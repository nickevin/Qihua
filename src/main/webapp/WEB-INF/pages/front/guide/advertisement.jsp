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
						<li>战略伙伴 </li>
						<li class="active">广告合作</li>
					</ol>
					<div class="page-box">
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
							industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled
							it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic
							typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets
							containing Lorem Ipsum passages, and more recently with desktop publishing software.</p>
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