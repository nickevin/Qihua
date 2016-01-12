<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.card.CardUpload"%>

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
    List<CardUpload> uploadList = (List<CardUpload>) request.getAttribute("uploadList");
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
					<div class="col-md-12">
						<div class="block-flat">
							<div class="content">
								<form class="form-horizontal"
									action="<%=basePath%>/console/card/import"
									style="margin-bottom: 20px" method="post">
									<div class="form-group">
										<label class="col-sm-3 col-sm-offset-2 control-label">选择文件</label>
										<div class="col-sm-3">
											<input type="file" class="form-control" name="file" />
										</div>
									</div>
									<div class="text-center">
										<button type="submit" data-loading-text="上传中，请稍候..." class="btn btn-primary">上传</button>
										<a href="<%=basePath%>/console/card" class="btn btn-default">返回</a>
									</div>
								</form>
							</div>

							<div class="content">
								<div class="table-responsive">
									<table class="table no-border hover">
										<thead class="no-border">
											<tr>
												<th><strong>总量</strong></th>
												<th><strong>上传时间</strong></th>
												<th class="text-center" style="width: 15%;"><strong>操作</strong></th>
											</tr>
										</thead>
										<tbody class="no-border-y">
											<%
											    for (CardUpload item : uploadList) {
											%>
											<tr>
												<td><%=item.getTotal()%></td>
												<td><%=item.getInsertTime()%></td>
												<td class="text-center">
													<a href="<%=basePath%>/console/card/download?uploadId=<%=item.getUploadId()%>" class="label label-default"><i class="fa fa-download"></i></a>
												</td>
											</tr>
											<%
                                                }
                                            %>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="mod-succes" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="text-center">
							<div class="i-circle success">
								<i class="fa fa-check"></i>
							</div>
							<p id="success-msg"></p>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
					</div>
				</div>
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
						<p id="error-msg"></p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">重新上传</button>
				</div>
			</div>
		</div>
	</div>

    <!-- ================== JavaScript ================== -->
	<script src="<%=basePath%>/resources/lib/jquery/jquery.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/js/bootstrap.min.js"></script>

    <script src="<%=basePath%>/resources/js/common.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
    <script src="<%=basePath%>/resources/lib/moment/moment.js"></script>

    <script type="text/javascript">
	    $(function() {
	    	'use strict';
	    	
	    	$.validator.addMethod("extension", function(value, element, param) {
	    		param = typeof param === "string" ? param.replace(/,/g, "|") : "png|jpe?g|gif";
	    		return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
	    	}, $.validator.format("请上传 xls 或  xlsx 后缀的文件。"));
	
	    	$('form').validate({
				rules : {
				    file : {
				        required : true,
				        extension : 'xls|xlsx'
				    }
				},
				focusInvalid : false,
				errorPlacement : function(error, elmt) {
				    error.appendTo(elmt.closest('.col-sm-3'));
				},
				submitHandler : function(form) {
				    var $validator = this;
				    
				    var $btn = $('button[type=submit]').button('loading')
					
				    var startTime = moment();
					
				    $.ajax({
				        url : '<%=basePath%>/console/card/import',
				        type : 'POST',
				        data : new FormData($(form)[0]),
				        cache : false,
				        contentType : false,
				        processData : false,
				        successCallback : function(json) {
				            $btn.button('reset');
					
				            if (json.result) {
				            	$('#success-msg').html('上传成功，成功导入：' + json.data + ' 条，耗时：' + moment().subtract(startTime).format('s') + ' 秒。');
				                $('#mod-succes').modal();				                
				            } else {
				            	$('#error-msg').html('上传失败，失败原因：' + json.data);
				                $('#mod-error').modal();
				            }
				        }
				    });
				}
	    	 });
	
	    	$('#mod-succes').on('hidden.bs.modal', function(e) {
	    		window.location.href = '<%=basePath%>/console/card/upload';
	    	});
	    });
	</script>
</body>
</html>
