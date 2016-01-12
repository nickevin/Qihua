<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.article.Article"%>

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
    Article article = (Article) request.getAttribute("article");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div class="container-fluid" id="pcont">
            <div class="page-head">
                <h2><%=request.getAttribute("title")%></h2>
                <ol class="breadcrumb">
                    <li>业务数据</li>
                    <li>文章管理</li>
                    <li class="active"><%=request.getAttribute("title")%></li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-10 col-md-10">
                        <div class="block-flat">
                            <div class="content">
                                <form id="form" role="form" class="form-horizontal">
                                    <input type="hidden" name="articleId" value="<%=article.getArticleId()%>"/>
                                    <input type="hidden" name="articleType" value="<%=article.getArticleType()%>"/>
                                
                                   <div class="form-group">
                                       <div class="col-sm-12">
                                           <textarea class="form-control" name="content" id="content"><%=article.getContent()%></textarea>
                                       </div>
                                   </div>
                                    <div class="text-center">
                                        <button class="btn btn-primary" type="submit">保存</button>
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
    <script src="<%=basePath%>/resources/lib/ckeditor/ckeditor.js"></script>
    <script src="<%=basePath%>/resources/lib/ckfinder/ckfinder.js"></script>
    
    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
    		var editor;
    		
    		function initCKEditor() {
      		    CKEDITOR.disableAutoInline = true;
      		     
      		  	editor = CKEDITOR.inline(document.getElementById('content'), {
					customConfig: './inline-config.js'// customConfig: './config.js'
      		     });
     
      			CKFinder.setupCKEditor(editor, '<%=basePath%>/resources/lib/ckfinder');
      		}
    		
    		$('#form').validate({
        		submitHandler : function(form) {
        			var $validator = this;
    
        			$('#content').val(editor.getData());
        			
        			var formData = $('#form').serializeObject();
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/article/save',
        				data : formData,
        				successCallback : function(json) {
        					if (json.result) {
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
      		
      		initCKEditor();
    	});
    </script>
</body>
</html>
