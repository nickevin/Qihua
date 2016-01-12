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
}
</style>

</head>

<body class="texture">
    <div id="cl-wrapper" class="login-container">
        <div class="middle-login">
            <div class="block-flat">
                <div class="header">
                    <h3 class="text-center">
                        <img class="logo-img" src="<%=basePath%>/resources/images/console-logo.png" alt="logo" /><%=Constants.SYSTEM_NAME%>
                    </h3>
                </div>
                <div>
                    <form style="margin-bottom: 0px !important;" class="form-horizontal" id="form-login">
                        <div class="content">
                            <h4 class="title">登录授权</h4>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input type="text" id="username" name="username" placeholder="用户名" class="form-control" data-rule-required="true" data-msg-required="请输入用户名" data-rule-rangelength="4,20">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input type="password" id="password" name="password" placeholder="密码" class="form-control" data-rule-required="true" data-msg-required="请输入密码" data-rule-rangelength="6,20" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                            <div class="checkbox"><label><input type="checkbox" name="remember">记住用户名</label></div>
                        </div>
                        <div class="foot">
                            <button class="btn btn-primary" type="submit" data-loading-text="登录中，请稍候...">登录系统</button>
                        </div>
                    </form>
                    
                    <form id="tmp-form" style="display: none"></form>
                </div>
            </div>
            <div class="text-center out-links">
                <a href="#"><%=Constants.COPYRIGHT_INFO + " " + Constants.COMPANY_NAME%></a>
            </div>
        </div>
    </div>

	<div class="modal fade" id="mod-error" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<div class="i-circle danger"><i class="fa fa-times"></i></div>
						<p>用户名或密码错误，请确认。</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">我知道了，重新输入</button>
				</div>
			</div>
		</div>
	</div>

    <!-- ================== JavaScript ================== -->
	<script src="<%=basePath%>/resources/lib/jquery/jquery.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/js/bootstrap.min.js"></script>

    <script src="<%=basePath%>/resources/js/common.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.cookie.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.md5.js"></script>
	<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.purl.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>

    <script type="text/javascript">
        $(function() {
        	'use strict';
        	
        	$('form :checkbox').prop('checked', true);
        	$('#username').val($.cookie('username') || '');
    
        	$('#form-login').validate({
        		focusInvalid : false,
        		errorPlacement: function(error, elmt) {
    				error.appendTo(elmt.closest('.col-sm-12'));
    			}, 
        		submitHandler : function(form) {
        			var $validator = this;
        			
        			var $btn = $('button[type=submit]').button('loading');
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/login',
        				data : {
        					username : $('#username').val(),
        					password : $.md5($('#password').val())
        				},
        				success : function(json) {
        					if (json.result) {
        						$.cookie('username', $('#username').val());
        						
        						var requestMethod = '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_TIMEOUT_REQUEST_METHOD)%>';
    							var requestParameters = '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_TIMEOUT_REQUEST_PARAMETERS)%>';
    							
    							var forwardURI = '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_TIMEOUT_URI)%>';
    							if (forwardURI == null || forwardURI == '' || forwardURI.toLowerCase() == 'null') {
    								forwardURI = '/console';
    							}

    							forwardURI = '<%=basePath%>' + forwardURI;

    							setTimeout(function() {
    								$('#tmp-form').attr('action', forwardURI);
    								$('#tmp-form').attr('method', requestMethod);

    								var params = $.url(forwardURI + requestParameters).param();
    								$.each(params, function(name, val) {
    									$('#tmp-form').append($('<input>').attr('type', 'hidden').attr('name', name).val(val));
    								});

    								$('#tmp-form').submit();
    							}, 1000);
        					} else {
        						$btn.button('reset');
        						
        						$('#mod-error').modal();
        					}
        				}
        			});
        			
        			$('#mod-error').on('hidden.bs.modal', function (e) {
        				$('#password').select();
        			});
        		}
        	});
        });
	</script>
</body>
</html>
