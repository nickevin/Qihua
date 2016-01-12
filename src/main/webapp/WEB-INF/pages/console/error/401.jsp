<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" href="<%=basePath%>/resources/images/favicon.png">

<title><%=Constants.SYSTEM_NAME%></title>

<!-- libraries -->
<link href="<%=basePath%>/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath%>/resources/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="<%=basePath%>/resources/lib/html5shiv.js"></script>
    <script src="<%=basePath%>/resources/lib/respond.min.js"></script>
<![endif]-->

<!-- global styles -->
<link href="<%=basePath%>/resources/css/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
</style>

</head>

<body class="texture">
    <div id="cl-wrapper" class="error-container">
        <div class="page-error">
            <h1 class="number text-center">401</h1>
            <h2 class="description text-center">对不起, 此页面未授权!</h2>
            <h3 class="text-center"><a href="<%=basePath%>/console">回到首页</a></h3>
        </div>
        <div class="text-center copy">
            <a href="#"><%=Constants.COPYRIGHT_INFO + " " + Constants.COMPANY_NAME%></a>
        </div>
    </div>
    
    <script src="<%=basePath%>/resources/lib/jquery/jquery.js"></script>

    <script src="<%=basePath%>/resources/js/common.js"></script>
</body>
</html>
