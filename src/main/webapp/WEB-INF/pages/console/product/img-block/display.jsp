<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.product.Product"%>
<%@page import="com.qihua.console.product.Category"%>
<%@page import="com.qihua.console.product.ProductImg"%>

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
<link href="<%=basePath%>/resources/lib/dropzone/css/dropzone.css" rel="stylesheet">

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

.dropzone {
	min-height: 150px;
}
</style>

<%
	ProductImg a = (ProductImg) request.getAttribute("a");
	ProductImg b = (ProductImg) request.getAttribute("b");
	ProductImg c = (ProductImg) request.getAttribute("c");
	ProductImg d = (ProductImg) request.getAttribute("d");
	ProductImg e = (ProductImg) request.getAttribute("e");
	ProductImg f = (ProductImg) request.getAttribute("f");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2>图片块管理</h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li>产品管理</li>
                    <li class="active">图片块管理</li>
                </ol>
            </div>
            <div class="cl-mcont">
				<div class="row">
					<div class="col-sm-12">
						<div class="text-center">
							<img src="<%=basePath%>/resources/images/img-block-guide.png">
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>A 区: 482 × 520</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label">商品名称</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" value="" readonly>
											<span id="a" class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneA">
									<input type="hidden" name="position" value="a">
									<input type="hidden" name="productId" value="<%=a == null ? "" : a.getProductId()%>">
									<input type="hidden" name="name" value="<%=a == null ? "" : a.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>B 区: 224 × 316</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label">商品名称</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" value="" readonly>
											<span id="b" class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneB">
									<input type="hidden" name="position" value="b">
									<input type="hidden" name="productId" value="<%=b == null ? "" : b.getProductId()%>">
									<input type="hidden" name="name" value="<%=b == null ? "" : b.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>C 区: 224 × 316</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label">商品名称</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" value="" readonly>
											<span id="c" class="c input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneC">
									<input type="hidden" name="position" value="c">
									<input type="hidden" name="productId" value="<%=c == null ? "" : c.getProductId()%>">
									<input type="hidden" name="name" value="<%=c == null ? "" : c.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>D 区: 469 × 189</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label">商品名称</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" value="" readonly>
											<span id="d" class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneD">
									<input type="hidden" name="position" value="d">
									<input type="hidden" name="productId" value="<%=d == null ? "" : d.getProductId()%>">
									<input type="hidden" name="name" value="<%=d == null ? "" : d.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>E 区: 165 × 231</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label"></label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" value="" readonly>
											<span id="e" class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneE">
									<input type="hidden" name="position" value="e">
									<input type="hidden" name="productId" value="<%=e == null ? "" : e.getProductId()%>">
									<input type="hidden" name="name" value="<%=e == null ? "" : e.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="block-flat">
							<div class="header">
								<h3>F 区: 165 × 273</h3>
							</div>
							<div class="content">
								<div class="form-group" style="margin-bottom: 60px">
									<label class="col-sm-3 control-label">商品名称</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" type="text" name="productName" readonly>
											<span id="f" class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
										</div>
									</div>
								</div>
								<form action="<%=basePath%>/console/product/img-block/upload" class="dropzone" id="dropzoneF">
									<input type="hidden" name="position" value="f">
									<input type="hidden" name="productId" value="<%=f == null ? "" : f.getProductId()%>">
									<input type="hidden" name="name" value="<%=f == null ? "" : f.getName()%>">
									<div class="fallback">
										<input name="file" type="file">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
        </div>
    </div>

	<div class="modal fade" id="mod-grid" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="content">
						<form id="form-search" method="post" style="margin-bottom: 20px" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">商品名称</label>
								<div class="col-sm-6">
									<input type="text" name="productName" class="form-control" data-rule-required="true">
								</div>
							</div>
							<div class="text-center">
								<button class="btn btn-primary" type="submit">查询</button>
							</div>
						</form>
					</div>
					<div class="content">
						<div class="table-responsive">
							<table class="table no-border hover">
								<thead class="no-border">
									<tr>
										<th><strong>货号</strong></th>
										<th><strong>产品名称</strong></th>
										<th><strong>礼券</strong></th>
										<th><strong>操作</strong></th>
									</tr>
								</thead>
								<tbody class="no-border-y">
								</tbody>
							</table>
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
    <script src="<%=basePath%>/resources/lib/dropzone/dropzone.js"></script>
    <script src="<%=basePath%>/resources/lib/underscore/underscore.js"></script>

    <script type="text/javascript">
		$(function() {
		    'use strict';
		    
		    var currentId = '';
		
		    $('.input-group-addon').click(function() {
		    	var $this = $(this);
		    	var id = $this.attr('id');
		    	
		    	currentId = id;
		    	
		    	$('#mod-grid').modal('show');
		    });
		    
			_.templateSettings = {
			    interpolate : /\<\@\=(.+?)\@\>/gim,
			    evaluate : /\<\@([\s\S]+?)\@\>/gim,
			    escape : /\<\@\-(.+?)\@\>/gim
			};
		    
		    $('#form-search').validate({
        		focusInvalid : false,
        		submitHandler : function(form) {
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/product/search-json',
        				data : {
        					productName: $('#form-search input[name=productName]').val()
        				},
        				successCallback : function(json) {
                        	$('tbody').html(_.template('<@_.each(data, function(item) {@>' + '<tr id="<@=item.productId@>">' + '<td><@=item.productCode@></td>' + '<td><@=item.productName@></td>' + '<td><@=item.score@></td>' + '<td class="text-center"><a class="label label-success btn-confirm" href="javascript:;"><i class="fa fa-check"></i></a></td>' + '<td><@=item.memo@></td>' + '</tr>' + '<@})@>', json));
        				}
        			});
        		}
        	});
		    
			$(document).on('click', '.btn-confirm', function() {
				$('#mod-grid').modal('hide');
				
				var $tr = $(this).closest('tr');
				var productId = $tr.attr('id');
				var productName = $tr.find('td').eq(1).html();
					
				$('#' + currentId).prev().val(productName);
				$('#dropzone' + currentId.toUpperCase() + ' input[name=productId]').val(productId);
				
				var imgName = $('#dropzone' + currentId.toUpperCase() + ' input[name=name]').val();
				if (imgName) {
					updateProductId(imgName, productId);
				}
			});
			
			function updateProductId(imgName, productId) {
				$.ajax({
					type : 'POST',
					url : '<%=basePath%>/console/product/img-block/update',
					data : {
						productId : productId,
						imgName : imgName
					},
					successCallback : function(json) {
			            if (json.result) {
			            } else {
			            	$.gritter.add({
			            		text : '删除失败，失败原因：' + json.data,
			            		position : 'top-right',
			            		class_name : 'danger'
			            	});
			            }
					}
				});
			}
			
			Dropzone.options.dropzoneA = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneA input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (a != null) { %>
				    var file = { name : '<%=a.getName()%>', size : <%=a.getSize()%>, thumbnail : '<%=a.getImgUrl()%>' };
					_this.emit('addedfile', file);_this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file); 
					<% } %>
					
				    this.on('removedfile', function(file) {
				    	if ($('#dropzoneA input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message;
				    	
				    	$('#dropzoneA input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			Dropzone.options.dropzoneB = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneB input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (b != null) { %>
				    var file = { name : '<%=b.getName()%>', size : <%=b.getSize()%>, thumbnail : '<%=b.getImgUrl()%>' };
					_this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file);
					<% } %>
					
					this.on('removedfile', function(file) {
						if ($('#dropzoneB input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message; 
				    	$('#dropzoneB input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			Dropzone.options.dropzoneC = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneC input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (c != null) { %>
				    var file = { name : '<%=c.getName()%>',size : <%=c.getSize()%>,thumbnail : '<%=c.getImgUrl()%>' };
					_this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file);
					<% } %>
					
					this.on('removedfile', function(file) {
						if ($('#dropzoneC input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message;
				    	$('#dropzoneC input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			Dropzone.options.dropzoneD = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneD input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (d != null) { %>
				    var file = { name : '<%=d.getName()%>', size : <%=d.getSize()%>, thumbnail : '<%=d.getImgUrl()%>' };
					_this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file); 
					<% } %>
					
				    this.on('removedfile', function(file) {
				    	if ($('#dropzoneD input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message; 
					    $('#dropzoneD input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			Dropzone.options.dropzoneE = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneE input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (e != null) { %>
				    var file = { name : '<%=e.getName()%>', size : <%=e.getSize()%>, thumbnail : '<%=e.getImgUrl()%>' };
					_this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file);
					<% } %>
					
				    this.on('removedfile', function(file) {
				    	if ($('#dropzoneE input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message;
				    	$('#dropzoneE input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			Dropzone.options.dropzoneF = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#dropzoneF input[name=productId]').val() == '') {
						done('请先选择商品');
					} else {
						done();
					}
				},
				init : function() {
					var _this = this;
				    var serverFile = {};
				    
				    <% if (f != null) { %>
				    var file = {name : '<%=f.getName()%>', size : <%=f.getSize()%>, thumbnail : '<%=f.getImgUrl()%>'};
					_this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file);
					<% } %>
					
				    this.on('removedfile', function(file) {
				    	if ($('#dropzoneF input[name=productId]').val() != '') {
					    	removeFile(serverFile.name ? serverFile : file);
						}
				    });
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message;
				    	$('#dropzoneF input[name=name]').val(serverFile.name);
				    });
				}
			};
			
			function removeFile(file) {
				$.ajax({
					type : 'POST',
				    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
					url : '<%=basePath%>/console/product/img-block/remove',
					data : {
						imgName : file.name
					},
					successCallback : function(json) {
			            if (json.result) {
			            	$.gritter.add({
			            		text : '删除成功',
			            		position : 'top-right',
			            		class_name : 'success'
			            	});
			            } else {
			            	$.gritter.add({
			            		text : '删除失败，失败原因：' + json.data,
			            		position : 'top-right',
			            		class_name : 'danger'
			            	});
			            }
					}
				});
			}
	    		
		});
	</script>
</body>
</html>
