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

</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="content-box text-center">
				<h4 class="special-heading">401</h4>
				<h5>（未授权） 请求要求身份验证。</h5>
				<br>
				<p>
					<a class="btn btn-danger" href="<%=basePath%>/gift">继续购物</a>
				</p>
			</div>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />

</body>
</html>