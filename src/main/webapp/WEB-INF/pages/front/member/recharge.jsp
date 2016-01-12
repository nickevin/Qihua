<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<form id="form-recharge" method="post">
	<div class="modal-header">
		<button data-dismiss="modal" class="close" type="button">
			<span aria-hidden="true">×</span><span class="sr-only">Close</span>
		</button>
		<h4 class="modal-title">礼券充值</h4>
	</div>
	<div class="modal-body">
		<div>
			<div class="form-group">
				<div id="msg-success-recharge" class="alert alert-success hide"></div>
				<div id="msg-error-recharge" class="alert alert-danger hide"></div>
			</div>
			<div class="form-group">
				<label>充值卡号</label> <input type="text" placeholder="在此输入您的充值卡号" name="cardNo" id="recharge-card-no"
					class="form-control" data-rule-required="true">
			</div>
			<div class="form-group">
				<label>充值密码</label> <input type="text" placeholder="在此输入您的充值密码" name="password" id="recharge-password"
					class="form-control" data-rule-required="true">
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
		<button id="btn-recharge" class="btn btn-danger" type="submit" data-loading-text="充值中，请稍候...">充值</button>
	</div>
</form>

<script type="text/javascript">
	$(function() {
	    'use strict';
		
	    $('#form-recharge').validate({
	        focusInvalid : false,
	        submitHandler : function(form) {
	    	    var $btn = $('#btn-recharge').button('loading');

	    	    $.ajax({
	    	        type : 'POST',
	    	        url : '<%=basePath%>/card/recharge',
	    	        data : {
	    	            cardNo : $('#recharge-card-no').val(),
	    	            password : $('#recharge-password').val()
	    	        },
	    	        success : function(json) {
	    		        if (json.result == null) {
	    			        setTimeout(function() {
	    				        $('#msg-error-recharge').html('请登录会员后，继续充值。');
	    				        $('#msg-error-recharge').removeClass('hide');
	    				        $('#msg-error-recharge').css({
	    					        'display' : 'block'
	    				        });
	    			        }, 1000);
	    		        }

	    		        if (json.result) {
	    			        setTimeout(function() {
	    				        $('#msg-success-recharge').html('成功充值 <strong>' + json.data + '</strong> 礼券。');
	    				        $('#msg-success-recharge').removeClass('hide');
	    				        $('#msg-success-recharge').css({
	    					        'display' : 'block'
	    				        });

	    				        $btn.button('reset');
	    			        }, 1000);

	    			        setTimeout(function() {
	    				        $('#modal-recharge').modal('hide');
	    			        }, 3000);
	    		        } else {
	    			        setTimeout(function() {
	    				        $('#msg-error-recharge').html(json.data);
	    				        $('#msg-error-recharge').removeClass('hide');
	    				        $('#msg-error-recharge').css({
	    					        'display' : 'block'
	    				        });

	    				        $btn.button('reset');
	    			        }, 1000);
	    		        }
	    	        }
	    	    });
	        }
	    });
	
		$('#modal-recharge').on('hidden.bs.modal', function (e) {
			$('#msg-success-recharge, #msg-error-recharge').addClass('hide');
			$('#msg-success-recharge, #msg-error-recharge').html('');
			
			$('#recharge-password').val('');
			$('#recharge-card-no').val('');
		});
});
</script>
