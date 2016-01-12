<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

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
</style>

<%
    List<ProductImg> centerImgList = (List<ProductImg>) request.getAttribute("centerImgList");
    List<ProductImg> bottomImgList = (List<ProductImg>) request.getAttribute("bottomImgList");
%>
</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2>首页管理</h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li class="active">首页管理</li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="block-flat">
                        	<div class="header">
								<h3>滑动大图</h3>
							</div>
                            <div class="content">
                            	<p>建议大小：1150 × 502</p>
								<form action="<%=basePath%>/console/home/img/upload" class="dropzone" id="imgCenter">
								    <input name="position" type="hidden" value="<%=Constants.HOME_IMG_POSISION_CENTER%>">
									<div class="fallback">
								    	<input name="file" type="file" multiple>
									</div>
	                           </form>
                            </div>
                        </div>
                    </div>
                
                    <div class="col-sm-6 col-md-6">
                        <div class="block-flat">
                        	<div class="header">
								<h3>底部图<h3>
							</div>
                            <div class="content">
                            	<p>建议大小：341 × 205</p>
								<form action="<%=basePath%>/console/home/img/upload" class="dropzone" id="imgBottom">
									<input name="position" type="hidden" value="<%=Constants.HOME_IMG_POSISION_BOTTOM%>">
									<div class="fallback">
								    	<input name="file" type="file" multiple>
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
    <script src="<%=basePath%>/resources/lib/dropzone/dropzone.js"></script>

    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
    		function init() {
    		}
    		
    		Dropzone.options.imgCenter = {
				paramName : 'file',
			    addRemoveLinks : true,
			    dictRemoveFile : '删除',
			    init : function() {
			    	var _this = this;
					
				    var files = [];
				    <%
				    	for (ProductImg img : centerImgList) {
				    %>
				    files.push({
				    	name : '<%=img.getName()%>',
				        size : <%=img.getSize()%>,
				        thumbnail : '<%=img.getImgUrl()%>'
				    });
				    <% } %>
				    
				    for (var idx = 0; idx < files.length; idx++) {
					    var file = files[idx];
					    _this.emit('addedfile', file);
					    _this.emit('thumbnail', file, file.thumbnail);
					    _this.emit('uploadprogress', file, '100')
					    _this.emit('complete', file)
				    }
			
				    this.on('removedfile', function(file) {
				    	removeFile(file);
				    });
				    
				    this.on('addedfile', function(file) {
				    });
				    
				    this.on('success', function(file, response) {
				    });
			    }
			};
    		
    		Dropzone.options.imgBottom = {
    				paramName : 'file',
    			    addRemoveLinks : true,
    			    dictRemoveFile : '删除',
    			    init : function() {
    			    	var _this = this;
    					
    				    var files = [];
    				    <%
    				    	for (ProductImg img : bottomImgList) {
    				    %>
    				    files.push({
    				    	name : '<%=img.getName()%>',
    				        size : <%=img.getSize()%>,
    				        thumbnail : '<%=img.getImgUrl()%>'
    				    });
    				    <% } %>
    				    
    				    for (var idx = 0; idx < files.length; idx++) {
    					    var file = files[idx];
    					    _this.emit('addedfile', file);
    					    _this.emit('thumbnail', file, file.thumbnail);
    					    _this.emit('uploadprogress', file, '100')
    					    _this.emit('complete', file)
    				    }
    			
    				    this.on('removedfile', function(file) {
    				    	removeFile(file);
    				    });
    				    
    				    this.on('addedfile', function(file) {
    				    });
    				    
    				    this.on('success', function(file, response) {
    				    });
    			    }
    			};
    		
    		function removeFile(file) {
    			$.ajax({
    				type : 'POST',
    				url : '<%=basePath%>/console/home/img/remove',
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
