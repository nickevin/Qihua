<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<form id="form-login" method="post">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
			<span aria-hidden="true">×</span><span class="sr-only">Close</span>
		</button>
		<h4 class="modal-title">登录</h4>
	</div>
	<div class="modal-body">
		<div>
			<div class="form-group">
				<div id="msg-success-login" class="alert alert-success hide"></div>
				<div id="msg-error-login" class="alert alert-danger hide"></div>
			</div>
			<div class="form-group">
				<label>手机号码或电子邮箱</label> <input type="text" data-rule-required="true" placeholder="在此输入您的手机号码或电子邮箱" name="mobile"
					id="login-mobile" class="form-control">
			</div>
			<div class="form-group">
				<label>密码</label> <input type="password" data-rule-required="true" placeholder="在此输入您的密码" name="password"
					id="login-password" class="form-control">
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a class="btn btn-danger pull-left" href="<%=basePath%>/member/login-register?p=reset">忘记密码</a>
		<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
		<button id="btn-login" class="btn btn-danger" type="submit" data-loading-text="登录中，请稍候...">登录</button>
	</div>
</form>

<form id="redirect-form" style="display: none"></form>

<script type="text/javascript">
$(function() {
	'use strict';

	$('#form-login').validate({
	    focusInvalid : false,
	    submitHandler : function(form) {
		    var $btn = $('#btn-login').button('loading');

		    $('#msg-success-login, #msg-error-login').addClass('hide');
		    $('#msg-success-login, #msg-error-login').html('');

		    $.ajax({
		        type : 'POST',
		        url : '<%=basePath%>/member/authorize',
		        data : {
		            mobile : $('#login-mobile').val(),
		            password : $.md5($('#login-password').val())
		        },
		        success : function(json) {
			        if (json.result) {
				        setTimeout(function() {
					        $('#msg-success-login').html(json.data);
					        $('#msg-success-login').removeClass('hide');
					        $('#msg-success-login').css({
						        'display' : 'block'
					        });

					        $btn.button('reset');
				        }, 1000);

				        setTimeout(function() {
					        $('#modal-login').modal('hide');
				        }, 2000);

			        } else {
				        setTimeout(function() {
					        $('#msg-error-login').html(json.data);
					        $('#msg-error-login').removeClass('hide');
					        $('#msg-error-login').css({
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
