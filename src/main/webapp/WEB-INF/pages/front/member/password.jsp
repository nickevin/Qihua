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
.side-heading {
	margin-bottom: 0px;
}

.tabs-panel {
    margin-top: 0px;
}
</style>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<jsp:include page="/WEB-INF/pages/front/member/menu.jsp" />
				
				<div class="col-md-9">
					<div class="panel panel-smart">
						<div class="panel-heading">
							<h4 class="panel-title">修改密码</h4>
						</div>
						<div class="panel-body">
							<form id="form" class="form-horizontal">
								<div id="msg-success" class="hide alert alert-success" role="alert"></div>
								<div id="msg-error" class="hide alert alert-danger" role="alert"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">老密码</label>
									<div class="col-sm-5">
										<input type="password" name="oldPassword" class="form-control" data-rule-required="true" data-rule-remote="<%=basePath%>/member/password-matches" data-msg-remote="密码错误，请确认">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">新密码</label>
									<div class="col-sm-5">
										<input type="password" name="newPassword" id="newPassword" class="form-control" data-rule-required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认新密码</label>
									<div class="col-sm-5">
										<input type="password" name="password" class="form-control" data-rule-required="true" data-rule-equalto="#newPassword">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<button class="btn btn-danger" type="submit" data-loading-text="保存中，请稍候...">保存</button>
									</div>
								</div>
							</form>
						</div>
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
	        
	        $('#form').validate({
	            focusInvalid : false,
	            onkeyup : false,
	            submitHandler : function(form) {
	        	    $.ajax({
	        	        type : 'POST',
	        	        url : '<%=basePath%>/member/save',
	        	        data : {
	        	        	memberId: '<%=member.getMemberId()%>',
	        	        	password: $.md5($('#form input[name=password]').val()) 
	        	        },
	        	        success : function(json) {
	        		        var $btn = $('button[type=submit]').button('loading');

	        		        if (json.result) {
	        			        setTimeout(function() {
	        				        $('#msg-success').html('保存成功');
	        				        $('#msg-success').removeClass('hide');
	        				        $('#msg-success').css({
	        					        'display' : 'block'
	        				        });

	        				        $btn.button('reset');
	        			        }, 2000);

	        			        setTimeout(function() {
	        			        	$('input[name=oldPassword]').val('');
	        			        	$('input[name=newPassword]').val('');
	        			        	$('#form input[name=password]').val('');
	        			        	
	        				        $('#msg-success').addClass('hide');
	        			        }, 5000);
	        		        } else {
	        			        $('#msg-error').html(json.data);
	        			        $('#msg-error').removeClass('hide');
	        			        $('#msg-error').css({
	        				        'display' : 'block'
	        			        });

	        			        $btn.button('reset');
	        		        }
	        	        }
	        	    });
	            }
	        });
        });
	</script>
</body>
</html>