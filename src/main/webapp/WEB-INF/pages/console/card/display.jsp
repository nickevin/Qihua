<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.card.Card"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" href="<%=basePath%>/resources/<%=basePath%>/resources/images/favicon.png">

<title><%=Constants.SYSTEM_NAME%></title>

<!-- libraries -->
<link href="<%=basePath%>/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath%>/resources/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="<%=basePath%>/resources/lib/html5shiv.js"></script>
    <script src="<%=basePath%>/resources/lib/respond.min.js"></script>
<![endif]-->

<!-- global styles -->
<link href="<%=basePath%>/resources/css/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
label.error {
    color: #cc0000;
    font-weight: normal;
    padding: 0;
    margin: 8px 0;
}
</style>

<%
    Card card = (Card) request.getAttribute("card");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2>礼券管理</h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li><a href="<%=basePath%>/console/card">礼券管理</a></li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-8 col-md-8">
                        <div class="block-flat">
                            <div class="content">
                                <form role="form" class="form-horizontal">
                                	<% if (card.getCardId() != null) { %><input type="hidden" name="cardId" value="<%=card.getCardId()%>"/><% } %>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">充值卡号</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="cardNo" class="form-control" data-rule-required="true" value="<%=card.getCardNo()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="password" class="form-control" data-rule-required="true" value="<%=card.getPassword()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">礼券</label>
                                        <div class="col-sm-7">
                                        	<input type="text" name="score" class="form-control text-right" data-rule-required="true" data-rule-min="1" value="<%=card.getScore()%>">
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button class="btn btn-primary" type="submit">保存</button>
                                        <a href="<%=basePath%>/console/card" class="btn btn-default">返回</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- ================== JavaScript ================== -->
    
    <script src="<%=basePath%>/resources/lib/jquery/jquery.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/js/bootstrap.min.js"></script>

    <script src="<%=basePath%>/resources/js/common.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.js"></script>
	<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.mask.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
    
    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
			$("input[name=score]").mask('#999999.0');
    		
    		$('form').validate({
        		focusInvalid : false,
        		errorPlacement: function(error, elmt) {
    				error.appendTo(elmt.closest('.col-sm-7'));
    			}, 
        		submitHandler : function(form) {
        			var $validator = this;
        			
        			var formData = $('form').serializeObject();
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/card/save',
        				data : JSON.stringify(formData),
        				successCallback : function(json) {
                            if (json.result) {
            					$('input[name=cardId]').val(json.data);

                            	$.gritter.add({
                            		text : '保存成功',
                            		position : 'top-right',
                            		class_name : 'success'
                            	});
                            } else {
                            	$.gritter.add({
                            		text : '保存失败，失败原因：' + json.data,
                            		position : 'top-right',
                            		class_name : 'danger'
                            	});
                            }
        				}
        			});
        		}
        	});
    		
    	});
    </script>
</body>
</html>
