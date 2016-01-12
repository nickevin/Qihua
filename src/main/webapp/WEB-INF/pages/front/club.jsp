<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<%@page import="com.qihua.front.article.Article"%>

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

<%
	Article content =  (Article)request.getAttribute("content");
%>

</head>

<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />
        
        <div id="main-container">
            <div class="row">
                <div class="col-md-12 text-center">
                	<%=content.getContent()%>
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
        	
        	 $('.club').addClass('active');
        });
	</script>
</body>
</html>