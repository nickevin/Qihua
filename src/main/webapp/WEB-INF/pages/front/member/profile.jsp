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

label.error {
	color: #cc0000;
	font-weight: normal;
	padding: 0;
	margin: 8px 0;
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
							<h4 class="panel-title">个人资料</h4>
						</div>
						<div class="panel-body">
							<form id="form" method="post" class="form-horizontal">
								<input type="hidden" name="memberId" class="form-control" value="<%=member.getMemberId()%>">
								
								<div id="msg-success" class="hide alert alert-success" role="alert"></div>
								<div id="msg-error" class="hide alert alert-danger" role="alert"></div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">会员名</label>
									<div class="col-sm-5">
										<input type="text" name="memberName" class="form-control" value="<%=member.getMemberName()%>" data-rule-required="true" data-rule-username="true" data-rule-notAllDigits="true" data-rule-rangelength="4,20" data-rule-remote="<%=basePath%>/member/exists-name" data-msg-remote="会员名已存在，请试试其他的。">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">手机号码</label>
									<div class="col-sm-5">
										<input type="text" name="mobile" class="form-control" value="<%=member.getMobile()%>" data-rule-required="true" data-rule-mobile="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电子邮箱</label>
									<div class="col-sm-5">
										<input type="text" name="email" class="form-control" value="<%=member.getEmail()%>" data-rule-required="true" data-rule-email="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">收货地址</label>
									<div class="col-sm-5">
										<input type="text" name="address" id="address" class="form-control" data-rule-required="true">
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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=<%=Constants.BAIDU_LBS_AK%>"></script>
	
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
	        	        data : $('#form').serializeObject(),
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

	        var addressValue;
        	var address = new BMap.Autocomplete({
        		'input' : 'address'
        	});
        	
        	setTimeout(function() {
		        $('#address').val('<%=member.getAddress()%>');
	        }, 100);
        	
        });
	</script>
</body>
</html>