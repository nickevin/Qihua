<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<form id="form-register" method="post">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
			<span aria-hidden="true">×</span><span class="sr-only">Close</span>
		</button>
		<h4 class="modal-title">注册</h4>
	</div>
	<div class="modal-body">
		<div>
			<div class="form-group">
				<div id="msg-success-register" class="alert alert-success hide"></div>
				<div id="msg-error-register" class="alert alert-danger hide"></div>
			</div>
			<div class="form-group">
				<label>手机号码</label> <input type="text" placeholder="在此输入您的手机号码" name="mobile" id="register-mobile"
					class="form-control" data-rule-required="true" data-rule-mobile="true">
			</div>
			<div class="form-group">
				<label>电子邮箱</label> <input type="text" placeholder="在此输入您的电子邮箱" name="email" id="register-email"
					class="form-control" data-rule-required="true" data-rule-email="true">
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" placeholder="在此输入您的密码" name="password" id="register-password"
					class="form-control" data-rule-required="true" data-rule-complexPassword="true">
			</div>
			<div class="form-group">
				<label>确认密码</label> <input type="password" placeholder="请再次输入您的密码" name="rePassword" id="register-rePassword"
					class="form-control" data-rule-required="true" data-rule-equalto="#register-password">
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
		<button id="btn-register" class="btn btn-danger" type="submit" data-loading-text="注册中，请稍候...">注册</button>
	</div>
</form>

<script type="text/javascript">
	$(function() {
	    'use strict';

	    $('#form-register').validate({
			focusInvalid : false,
			submitHandler : function(form) {
		        var $btn = $('#btn-register').button('loading');
		        
		        $('#msg-success-register, #msg-error-register').addClass('hide');
				$('#msg-success-register, #msg-error-register').html('');
		        
				$.ajax({
					type : 'POST',
					url : '<%=basePath%>/member/authenticate',
					data : {
						mobile : $('#register-mobile').val(),
						email : $('#register-email').val(),
						password : $.md5($('#register-password').val())
					},
					success : function(json) {
						if (json.result) {
							setTimeout(function() {
								$('#msg-success-register').html(json.data);
								$('#msg-success-register').removeClass('hide');
	    				        $('#msg-success-register').css({
	    					        'display' : 'block'
	    				        });
	    				        
								$btn.button('reset');
							}, 1000);
							
							setTimeout(function() {
								window.location = '<%=basePath%>/gift';
							}, 2000);
						} else {
							setTimeout(function() {
								$('#msg-error-register').html(json.data);
								$('#msg-error-register').removeClass('hide');
	    				        $('#msg-error-register').css({
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
