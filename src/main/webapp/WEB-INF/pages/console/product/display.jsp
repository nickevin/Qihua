<%@page import="com.qihua.console.product.ProductImg"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.product.Product"%>
<%@page import="com.qihua.console.product.Category"%>

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
<link href="<%=basePath%>/resources/lib/jquery/plugins/raty/jquery.raty.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/dropzone/css/dropzone.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/bootstrap/plugins/datepicker/bootstrap-datepicker.css" rel="stylesheet">
<link href="<%=basePath%>/resources/lib/bootstrap/plugins/switch/bootstrap-switch.css" rel="stylesheet">

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
    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
    List<Category> subcategoryList = (List<Category>) request.getAttribute("subcategoryList");
    Product product = (Product) request.getAttribute("product");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2>商品管理</h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li>产品管理</li>
                    <li class="active"><a href="<%=basePath%>/console/product">商品管理</a></li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-7 col-md-7">
                        <div class="block-flat">
                            <div class="content">
                                <form id="form" class="form-horizontal">
                                	<% if (product.getProductId() != null) { %><input type="hidden" name="productId" value="<%=product.getProductId()%>"/><% } %>
                                	
                                	<div class="form-group">
                                        <label class="col-sm-2 control-label">货号</label>
                                        <div class="col-sm-9">
											<input type="text" name="productCode" class="form-control" data-rule-required="true" value="<%=product.getProductCode()%>">
										</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">产品名称</label>
                                        <div class="col-sm-9">
											<input type="text" name="productName" class="form-control" data-rule-required="true" value="<%=product.getProductName()%>">
										</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">产品分类</label>
                                        <div class="col-sm-5">
	                                        <select class="form-control" id="categoryId" data-rule-required="true">
	                                        	<option value=""></option>
	                                            <%
	                                                for (Category item : categoryList) {
	                                            %>
	                                        	<option value="<%=item.getCategoryId()%>"><%=item.getCategoryName()%></option>
	                                            <%
	                                                }
	                                            %>
	                                        </select>
	                                	</div>
	                                	 <div class="col-sm-4">
	                                        <select class="form-control" id="subcategoryId" name="categoryId" data-rule-required="true">
	                                        	<option value=""></option>
	                                            <%
	                                                for (Category item : subcategoryList) {
	                                            %>
	                                        	<option value="<%=item.getSubcategoryId()%>" class="<%=item.getCategoryId()%>"><%=item.getSubcategoryName()%></option>
	                                            <%
	                                                }
	                                            %>
	                                        </select>
	                                	</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">礼券</label>
                                        <div class="col-sm-9">
                                        	<input type="text" name="score" class="form-control text-right" data-rule-required="true" data-rule-min="1" value="<%=product.getScore()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">评级</label>
                                        <div class="col-sm-9">
                                        	<div class="rate" data-rate="<%=product.getRate()%>"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">推荐商品</label>
                                        <div class="col-sm-4">
                                        	<input type="checkbox" name="isRecommend" class="switch" data-size="small" <% if (product.getIsRecommend() == Constants.ENABLE) { %>checked<% } %> value="<%=Constants.ENABLE%>">
                                        </div>
                                        <label class="col-sm-2 control-label">限时抢购</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="isFlashSale" class="switch" data-size="small" <% if (product.getIsFlashSale() == Constants.ENABLE) { %>checked<% } %> value="<%=Constants.ENABLE%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">限时起始日期</label>
                                        <div class="col-sm-4">
                                        	<input type="text" name="onSaleDate" class="form-control datepicker" value="<% if (product.getOnSaleDate() == null) { %><% } else { %><%=product.getOnSaleDate()%><% } %>">
                                        </div>
                                        <label class="col-sm-2 control-label">限时天数</label>
                                        <div class="col-sm-3">
                                        	<input type="text" name="countdown" class="form-control text-right" data-rule-min="1" data-rule-number="true" value="<%=product.getCountdown() == 0 ? "" : product.getCountdown()%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">添加时间</label>
                                        <div class="col-sm-9">
                                        	<p class="form-control-static"><%=product.getInsertTime()%></p>
                                        </div>
                                   	</div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">商品详情</label>
                                        <div class="col-sm-9">
                                        	<textarea class="form-control" name="description" id="description"><%=product.getDescription()%></textarea>
                                        </div>
                                   	</div>
                                    <div class="text-center">
                                        <button class="btn btn-primary" type="submit">保存</button>
                                        <a href="<%=basePath%>/console/product/" class="btn btn-default">返回</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                
                    <div class="col-sm-5 col-md-5">
                        <div class="block-flat">
                        	<div class="header">
								<h3>商品图片（用于展示商品详情）</h3>
							</div>
                            <div class="content">
                            	<p>建议大小：340 × 260</p>
								<form action="<%=basePath%>/console/product/img/upload" class="dropzone" id="imgDetail">
                                	<% if (product.getProductId() != null) { %><input type="hidden" name="productId" value="<%=product.getProductId()%>"/><% } %>
									<div class="fallback">
								    	<input name="file" type="file" multiple />
									</div>
	                           </form>
                            </div>
                        </div>
                        
                        <div class="block-flat">
                        	<div class="header">
								<h3>推荐商品（用于“兑换中心”的滑动图）</h3>
							</div>
                            <div class="content">
                            	<p>建议大小：855 × 329</p>
								<form action="<%=basePath%>/console/product/img/recommend/upload" class="dropzone" id="imgRecommend">
                                	<% if (product.getProductId() != null) { %><input type="hidden" name="productId" value="<%=product.getProductId()%>"/><% } %>
									
									<div class="fallback">
								    	<input name="file" type="file" multiple />
									</div>
	                           </form>
                            </div>
                        </div>
                        
                        <div class="block-flat">
                        	<div class="header">
								<h3>限时抢购（用于“限时抢购”）</h3>
							</div>
                            <div class="content">
                            	<p>建议大小：240 × 291</p>
								<form action="<%=basePath%>/console/product/img/flash-sale/upload" class="dropzone" id="imgFlashSale">
                                	<% if (product.getProductId() != null) { %><input type="hidden" name="productId" value="<%=product.getProductId()%>"/><% } %>
									
									<div class="fallback">
								    	<input name="file" type="file" multiple />
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
    <script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.mask.js"></script>
	<script src="<%=basePath%>/resources/lib/jquery/plugins/raty/jquery.raty.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/chained/jquery.chained.js"></script>
    <script src="<%=basePath%>/resources/lib/dropzone/dropzone.js"></script>
    <script src="<%=basePath%>/resources/lib/ckeditor/ckeditor.js"></script>
    <script src="<%=basePath%>/resources/lib/ckfinder/ckfinder.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/plugins/switch/bootstrap-switch.js"></script>
    

    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
    		function init() {
    			$('.datepicker').datepicker();
    			
	    		$('input[name=score]').mask('#999999.0');
	    		
	    		$('.switch').bootstrapSwitch({
	    			labelWidth: 5
	    		});
	    		
        		$('#categoryId').val('<%=(product.getCategoryId() + "").substring(0, 3)%>');
        		
        		$('#subcategoryId').chained('#categoryId');
        		$('#subcategoryId').val('<%=product.getCategoryId()%>');
        		
        		$.fn.raty.defaults.path = '<%=basePath%>/resources/lib/jquery/plugins/raty/images';
        		$('.rate').raty({
        			hints: ['没有销量', '销量差', '销量一般', '销量不错', '热卖商品'],
        			score : function() {
        			    return $(this).attr('data-rate');
        		    }
        		});
    		}
    		
    		// @see: http://stackoverflow.com/questions/24445724/add-existing-image-files-in-dropzone
    		
    		Dropzone.options.imgDetail = {
			    paramName : 'file',
			    addRemoveLinks : true,
			    dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#form input[name=productId]').val()) {
						done();
					} else {
						done('请先保存商品');
					}
				},
			    init : function() {
				    var _this = this;
				    var serverFile = {};

				    <%
				    	List<ProductImg> images = product.getImages(); 
				    	for (ProductImg img : images) {
				    %>
				    var files = [];
				    files.push({ name : '<%=img.getName()%>', size : <%=img.getSize()%>, thumbnail : '<%=img.getImgUrl()%>'});
				    for (var idx = 0; idx < files.length; idx++) { var file = files[idx]; _this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file); }
				    <% } %>
			
				    this.on('removedfile', function(file) {
				    	if ($('#form input[name=productId]').val()) {
					    	$.ajax({
			    				type : 'POST',
							    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			    				url : '<%=basePath%>/console/product/img/remove',
			    				data : {
			    					imgName : serverFile.name ? serverFile.name : file.name
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
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message; 
				    });
			    }
			};
    		
    		Dropzone.options.imgRecommend = {
				paramName : 'file',
				addRemoveLinks : true,
				dictRemoveFile : '删除',
				accept: function(file, done) {
					if ($('#form input[name=productId]').val()) {
						done();
					} else {
						done('请先保存商品');
					}
				},
				init : function() {
				    var _this = this;
				    var serverFile = {};
				    
				    <%
				    	List<ProductImg> recommendImages = product.getRecommendImages(); 
				    	for (ProductImg img : recommendImages) {
				    %>
				    var files = [];
				    files.push({ name : '<%=img.getName()%>', size : <%=img.getSize()%>, thumbnail : '<%=img.getImgUrl()%>' });
				    for (var idx = 0; idx < files.length; idx++) { var file = files[idx]; _this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file); }
				    <% } %>
				
				    this.on('removedfile', function(file) {
				    	if ($('#form input[name=productId]').val()) {
					    	$.ajax({
								type : 'POST',
							    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
								url : '<%=basePath%>/console/product/img/recommend/remove',
								data : {
									imgName : serverFile.name ? serverFile.name : file.name
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
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message; 
				    });
				}
    		};
    		
			Dropzone.options.imgFlashSale = {
			    paramName : 'file',
			    addRemoveLinks : true,
			    dictRemoveFile : '删除',
			    accept: function(file, done) {
					if ($('#form input[name=productId]').val()) {
						done();
					} else {
						done('请先保存商品');
					}
				},
			    init : function() {
				    var _this = this;
				    var serverFile = {};
				    
				    <%
				    	List<ProductImg> flashSaleImages = product.getFlashSaleImages(); 
				    	for (ProductImg img : flashSaleImages) {
				    %>
				    var files = [];
				    files.push({ name : '<%=img.getName()%>', size : <%=img.getSize()%>, thumbnail : '<%=img.getImgUrl()%>' });
				    for (var idx = 0; idx < files.length; idx++) { var file = files[idx]; _this.emit('addedfile', file); _this.emit('thumbnail', file, file.thumbnail); _this.emit('uploadprogress', file, '100'); _this.emit('complete', file); }
				    <% } %>
			
				    this.on('removedfile', function(file) {
				    	if ($('#form input[name=productId]').val()) {
					    	$.ajax({
								type : 'POST',
							    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
								url : '<%=basePath%>/console/product/img/flash-sale/remove',
								data : {
									imgName : serverFile.name ? serverFile.name : file.name
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
				    
				    this.on('success', function(file, response) {
				    	serverFile.name = response.message; 
				    });
			    }
			};
    		
    		var editor = CKEDITOR.replace('description', {
    			height: 1000
    		});
    		
   	     	CKFinder.setupCKEditor(editor, '<%=basePath%>/resources/lib/ckfinder');
   	     	
    		$('#form').validate({
        		focusInvalid : false,
        		submitHandler : function(form) {
        			var $validator = this;
        			
        			$('#description').val(editor.getData());
        			
        			var formData = $('#form').serializeObject();
        			//console.log(JSON.stringify(formData)); return;
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/product/save',
        				data : formData,
        				successCallback : function(json) {
                            if (json.result) {
                            	if (!$('input[name=productId]').val()) {
	        						$('#form').append('<input type="hidden" name="productId" value="' + json.data + '">');
	        						$('#imgDetail').append('<input type="hidden" name="productId" value="' + json.data + '">');
	        						$('#imgRecommend').append('<input type="hidden" name="productId" value="' + json.data + '">');
	        						$('#imgFlashSale').append('<input type="hidden" name="productId" value="' + json.data + '">');
                            	}
        						
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
    		
    		init();
    	});
    </script>
</body>
</html>
