<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

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
    User user = (User) request.getAttribute("user");
%>

</head>

<body>
    <jsp:include page="/WEB-INF/pages/console/include/nav.jsp" flush="true" />

    <div id="cl-wrapper">
        <div id="pcont" class="container-fluid">
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="block-flat profile-info">
                            <div class="row">
                                <div class="col-sm-2">
                                    <div class="avatar">
                                        <img class="profile-avatar" src="<%=basePath%>/resources/images/avatar.jpg">
                                    </div>
                                </div>
                                <div class="col-sm-7">
                                    <div class="personal">
                                        <h3 class="name"><%=sessionUser.getRealName()%></h3>
                                        <p class="description"><%=user.getDescription()%></p>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-8">
                        <div class="tab-container">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#settings" data-toggle="tab">基本信息</a></li>
                                <li><a data-toggle="tab" href="#reset">修改密码</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active cont" id="settings">
                                    <form id="form-info" class="form-horizontal" role="form">
                                        <input type="hidden" name="userId" value="<%=sessionUser.getUserId()%>"/>
                                        
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">用户名</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" readonly="readonly" value="<%=sessionUser.getUsername()%>">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">姓名</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="realName" value="<%=sessionUser.getRealName()%>" data-rule-required="true" data-msg-required="请输入姓名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">电子邮箱</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="email" value="<%=sessionUser.getEmail()%>" data-rule-required="true" data-msg-required="请输入电子邮件" data-rule-email="true">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">手机</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="mobile" value="<%=sessionUser.getMobile()%>" data-rule-required="true" data-msg-required="请输入手机">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">个人介绍</label>
                                            <div class="col-sm-9">
                                                <textarea class="form-control" name="description" id="description"><%=user.getDescription()%></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-9">
                                                <button type="submit" class="btn btn-primary">保存</button>
                                                <button type="reset" class="btn btn-default">重置</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                
                                <div id="reset" class="tab-pane">
                                    <form id="form-reset" class="form-horizontal"  role="form">
                                        <input type="hidden" name="userId" value="<%=sessionUser.getUserId()%>"/>
                                        <input type="hidden" name="username" value="<%=sessionUser.getUsername()%>"/>
                                        
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">原密码</label>
                                            <div class="col-sm-9">
                                                <input type="password" name="oldPassword" class="form-control" data-rule-required="true" data-msg-required="请输入原密码" data-rule-rangelength="6,20" autocomplete="off">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">新密码</label>
                                            <div class="col-sm-9">
                                                <input type="password" name="password" id="password" class="form-control" data-rule-required="true" data-msg-required="请输入密新码" data-rule-rangelength="6,20" autocomplete="off">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">新密码</label>
                                            <div class="col-sm-9">
                                                <input type="password" name="rePassword" class="form-control" data-rule-required="true" data-msg-required="请再次输入新密码" data-rule-rangelength="6,20" data-rule-equalto="#password" data-msg-equalto="两次输入密码不一致" autocomplete="off">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-9">
                                                <button class="btn btn-primary" type="submit">保存</button>
                                                <button type="reset" class="btn btn-default">重置</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
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
    <script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.md5.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/validate/jquery.validate.messages_zh.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.js"></script>
    <script src="<%=basePath%>/resources/lib/ckeditor/ckeditor.js"></script>
    <script src="<%=basePath%>/resources/lib/ckfinder/ckfinder.js"></script>

    <script type="text/javascript">
        $(function() {
        	'use strict';
    
        	var editor = CKEDITOR.replace('description');
   	     	CKFinder.setupCKEditor(editor, '<%=basePath%>/resources/lib/ckfinder');
   	     	
       	     /*
       	     function initCKEditor() {
      		    CKEDITOR.disableAutoInline = true;
      		     
      			var description = CKEDITOR.inline(document.getElementById('description'), {
      		         customConfig: './inline-config.js'
      		     });
     
      			CKFinder.setupCKEditor(description, 'basePath/resources/lib/ckfinder');
      		}
      		
      		initCKEditor();
       	     */
    
        	$('#form-info').validate({
        		focusInvalid : false,
        		errorPlacement : function(error, elmt) {
        			error.appendTo(elmt.closest('.col-sm-9'));
        		},
        		submitHandler : function(form) {
        			var $validator = this;
    
        			$('#description').val(editor.getData());
        			
        			var formData = $('#form-info').serializeObject();
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/user/save-profile',
        				data : JSON.stringify(formData),
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
    
        	$('#form-reset').validate({
        		focusInvalid : false,
        		errorPlacement : function(error, elmt) {
        			error.appendTo(elmt.closest('.col-sm-9'));
        		},
        		submitHandler : function(form) {
        			var $validator = this;
        			
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/user/password-matches',
        				data : JSON.stringify({
        					username : $('input[name=username]').val(),
        					password : $.md5($('input[name=oldPassword]').val())
        				}),
        				successCallback : function(json) {
        					if (json.result) {
        						save();
        					} else {
        						$.gritter.add({
        							text : json.data,
        							position : 'top-right',
        							class_name : 'danger'
        						});
        					}
        				}
        			});
        		}
        	});
    
        	function save() {
        		$('#password').val($.md5($('#password').val()));
    
        		if (confirm('更新登录密码会强制重新登录！确定更新？')) {
        			var formData = $('#form-reset').serializeObject(
        					[ 'oldPassword', 'rePassword' ]);
    
        			// console.log(JSON.stringify(formData)); return;
    
        			$.ajax({
        				type : 'POST',
        				url : '<%=basePath%>/console/user/save-profile',
        				data : JSON.stringify(formData),
        				successCallback : function(json) {
        					setTimeout(function() {
        						if (json.result) {
        							window.location = '<%=basePath%>/console/logout';
        						} else {
        							$.message('操作失败：' + json.data, {
        								type : 'danger'
        							});
        						}
        					}, 2000);
        				}
        			});
        		}
        	}
        });				
	</script>
</body>
</html>
