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
			<section class="login-area">
				<div class="row">
					<div class="col-sm-6">
						<div class="panel panel-smart">
							<div class="panel-heading">
								<h3 class="panel-title">登录</h3>
							</div>
							<div class="panel-body">
								<form id="form-login-2" name="form" class="form-horizontal" method="post">
									<div class="alert alert-success hide" role="alert"></div>
									<div class="alert alert-danger hide" role="alert"></div>
							
									<div class="form-group">
										<label class="col-sm-4 control-label">手机号码或电子邮箱</label>
										<div class="col-sm-7">
											<input type="text" name="mobile" class="form-control" data-rule-required="true">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">密码</label>
										<div class="col-sm-7">
											<input type="password" name="password" class="form-control" data-rule-required="true">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button id="btn-login-2" class="btn btn-danger" type="submit" data-loading-text="登录中，请稍候...">登录</button>
										</div>
									</div>
								</form>
								<form id="redirect-form-2" style="display: none"></form>
							</div>
						</div>
						
						<div class="panel panel-smart">
							<div class="panel-heading">
								<h3 class="panel-title">忘记密码</h3>
							</div>
							<div class="panel-body">
								<form id="form-reset" name="form" class="form-horizontal" method="post">
									<div class="alert alert-success hide" role="alert"></div>
									<div class="alert alert-danger hide" role="alert"></div>
							
									<div class="form-group">
										<label class="col-sm-4 control-label">电子邮箱</label>
										<div class="col-sm-7">
											<input type="text" name="email" class="form-control" data-rule-required="true" data-rule-email="true" data-rule-remote="<%=basePath%>/member/exists-email" data-msg-remote="电子邮件不存在，请确认。">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button id="btn-reset" class="btn btn-danger" type="submit" data-loading-text="发送中，请稍候...">重置密码</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-6">
						<div class="panel panel-smart">
							<div class="panel-heading">
								<h3 class="panel-title">加入我们，成为新会员</h3>
							</div>
							<div class="panel-body">
								<form id="form-register-2" name="form" class="form-horizontal" method="post">
									<div class="alert alert-success hide" role="alert"></div>
									<div class="alert alert-danger hide" role="alert"></div>
									
									<div class="form-group">
										<label class="col-sm-4 control-label">手机号码</label>
										<div class="col-sm-7">
											<input type="text" name="mobile" class="form-control" data-rule-required="true" data-rule-mobile="true">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">电子邮件</label>
										<div class="col-sm-7">
											<input type="text" name="email" class="form-control" data-rule-required="true" data-rule-email="true">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">密码</label>
										<div class="col-sm-7">
											<input type="password" id="register-password-2" name="password" class="form-control" data-rule-required="true" data-rule-complexPassword="true">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">确认密码</label>
										<div class="col-sm-7">
											<input type="password" name="rePassword" class="form-control" data-rule-required="true" data-rule-equalto="#register-password-2">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button id="btn-register-2" class="btn btn-danger" type="submit" data-loading-text="注册中，请稍候...">注册</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />
	
	<!-- ================== JavaScript ================== -->
	<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.purl.js"></script>

	<script type="text/javascript">
		$(function() {
	        'use strict';
	        
	        $('#form-login-2').validate({
				focusInvalid : false,
				submitHandler : function(form) {
    		        var $btn = $('#btn-login-2').button('loading');

					var formId = $(form).attr('id');
					
					$('#' + formId + ' .alert').addClass('hide');
					
					// console.log($('#' + formId + ' input[name=password]').val()); return;
					
					$.ajax({
						type : 'POST',
						url : '<%=basePath%>/member/authorize',
						data : {
							mobile : $('#' + formId + ' input[name=mobile]').val(),
							password : $.md5($('#' + formId + ' input[name=password]').val())
						},
						success : function(json) {
	        		        if (json.result) {
	        			        setTimeout(function() {
	        				        $('#' + formId + ' .alert-success').html(json.data);
	        				        $('#' + formId + ' .alert-success').removeClass('hide');
	        				        $('#' + formId + ' .alert-success').css({
	        					        'display' : 'block'
	        				        });

	        				        $btn.button('reset');
	        			        }, 1000);
	        			        
    							var requestParameters = '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_REQUEST_PARAMETERS)%>';
								var forwardURI = '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_URI)%>';
								if (forwardURI == null || forwardURI == '' || forwardURI.toLowerCase() == 'null') {
									forwardURI = '/';
								}

								if (forwardURI.endsWith('.json')) {
									forwardURI = forwardURI.substring(0, forwardURI.length - 5);
								}
								
								forwardURI = '<%=basePath%>' + forwardURI;

								setTimeout(function() {
									$('#redirect-form-2').attr('action', forwardURI);
									$('#redirect-form-2').attr('method', '<%=WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER_TIMEOUT_REQUEST_METHOD)%>');
									var params = $.url(forwardURI + requestParameters).param();
    								$.each(params, function(name, val) {
    									$('#redirect-form-2').append($('<input>').attr('type', 'hidden').attr('name', name).val(val));
    								});
									$('#redirect-form-2').submit();
								}, 3000);
	        		        } else {
	        		        	setTimeout(function() {
	        			        $('#' + formId + ' .alert-danger').html(json.data);
		        			        $('#' + formId + ' .alert-danger').removeClass('hide');
		        			        $('#' + formId + ' .alert-danger').css({
		        				        'display' : 'block'
		        			        });
	
		        			        $btn.button('reset');
								}, 1000);
	        		        }
						}
					});
				}
			});
		
			$('#form-register-2').validate({
				focusInvalid : false,
				submitHandler : function(form) {
					var $btn = $('#btn-register-2').button('loading');
					
					var formId = $(form).attr('id');
					
					$('#' + formId + ' .alert').addClass('hide');
					
					$.ajax({
						type : 'POST',
						url : '<%=basePath%>/member/register',
						data : {
							mobile : $('#' + formId + ' input[name=mobile]').val(),
							email : $('#' + formId + ' input[name=email]').val(),
							password : $.md5($('#' + formId + ' input[name=password]').val())
						},
						success : function(json) {
	        		        if (json.result) {
	        			        setTimeout(function() {
	        				        $('#' + formId + ' .alert-success').html(json.data);
	        				        $('#' + formId + ' .alert-success').removeClass('hide');
	        				        $('#' + formId + ' .alert-success').css({
	        					        'display' : 'block'
	        				        });

	        				        $btn.button('reset');
	        			        }, 2000);

	        			        setTimeout(function() {
	        			        	$('#' + formId + ' .alert-success').addClass('hide');
	        			        	
	        			        	window.location = '<%=basePath%>/gift';
	        			        }, 4000);
	        		        } else {
	        		        	setTimeout(function() {
	        		        		$('#' + formId + ' .alert-danger').html(json.data);
	 	        			        $('#' + formId + ' .alert-danger').removeClass('hide');
	 	        			        $('#' + formId + ' .alert-danger').css({
	 	        				        'display' : 'block'
	 	        			        });

	 	        			        $btn.button('reset');
	        			        }, 1000);
	        		        }
						}
					});
				}
			});
			
			$('#form-reset').validate({
				focusInvalid : false,
				submitHandler : function(form) {
					var $btn = $('#btn-reset').button('loading');
					
					var formId = $(form).attr('id');
					
					$('#' + formId + ' .alert').addClass('hide');
					
					$.ajax({
						type : 'POST',
						url : '<%=basePath%>/member/reset',
						data : {
							email : $('#' + formId + ' input[name=email]').val()
						},
						success : function(json) {
	        		        if (json.result) {
	        		        	setTimeout(function() {
	        				        $('#' + formId + ' .alert-success').html(json.data);
	        				        $('#' + formId + ' .alert-success').removeClass('hide');
	        				        $('#' + formId + ' .alert-success').css({
	        					        'display' : 'block'
	        				        });

	        				        $btn.button('reset');
	        			        }, 2000);
	        		        } else {
	        		        	setTimeout(function() {
	        		        		$('#' + formId + ' .alert-danger').html(json.data);
	 	        			        $('#' + formId + ' .alert-danger').removeClass('hide');
	 	        			        $('#' + formId + ' .alert-danger').css({
	 	        				        'display' : 'block'
	 	        			        });

	 	        			        $btn.button('reset');
	        			        }, 1000);
	        		        }
						}
					});
				}
			});
			
        });
	</script>
</body>
</html>