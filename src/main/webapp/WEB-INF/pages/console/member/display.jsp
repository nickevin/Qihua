<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.member.Member"%>

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
    Member member = (Member) request.getAttribute("member");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2>会员管理</h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li><a href="<%=basePath%>/console/member">会员管理</a></li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-8 col-md-8">
                        <div class="block-flat">
                            <div class="content">
                                <form role="form" class="form-horizontal">
                                    <input type="hidden" name="memberId" value="<%=member.getMemberId()%>"/>
                                
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">会员名称</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="memberName" class="form-control" data-rule-required="true" value="<%=member.getMemberName()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">手机</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="mobile" class="form-control" data-rule-required="true" value="<%=member.getMobile()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">电子邮箱</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="email" class="form-control" data-rule-required="true" data-msg-required="请输入电子邮件" data-rule-email="true" value="<%=member.getEmail()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">注册时间</label>
                                        <div class="col-sm-7">
                                            <input disabled="disabled" type="text" name="insertTime" class="form-control" value="<%=member.getInsertTime()%>">
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button class="btn btn-primary" type="submit">保存</button>
                                        <a href="<%=basePath%>/console/member/" class="btn btn-default">返回</a>
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
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.js"></script>

    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
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
        				url : '<%=basePath%>/console/member/save',
        				data : JSON.stringify(formData),
        				successCallback : function(json) {
                            if (json.result) {
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
