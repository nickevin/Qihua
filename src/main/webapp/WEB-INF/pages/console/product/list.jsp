<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.console.product.ProductQueryParameter"%>
<%@page import="com.qihua.console.product.Category"%>
<%@page import="com.qihua.console.product.Product"%>

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
<link href="<%=basePath%>/resources/lib/bootstrap/plugins/switch/bootstrap-switch.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="<%=basePath%>/resources/lib/html5shiv.js"></script>
    <script src="<%=basePath%>/resources/lib/respond.min.js"></script>
<![endif]-->

<!-- global styles -->
<link href="<%=basePath%>/resources/css/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
.form-group .row {
	margin-top: 0px;
}
</style>

<%
    ProductQueryParameter queryParam = (ProductQueryParameter) request.getAttribute("queryParam");

    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
    List<Product> productList = (List<Product>) request.getAttribute("productList");
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
                    <li class="active">商品管理</li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block-flat">
                            <div class="content">
                                <form class="form-horizontal" action="<%=basePath%>/console/product" style="margin-bottom: 20px" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-offset-2 control-label">产品名称</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="productName" value="<%=queryParam.getProductName()%>" />
                                        </div>
                                        <label class="col-sm-1 control-label">产品分类</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" name="categoryId">
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
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-offset-2 control-label">礼券区间</label>
                                        <div class="col-sm-3">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control text-right" name="lowScore" value="<%=queryParam.getLowScore()%>">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control text-right" name="highScore" value="<%=queryParam.getHighScore()%>">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">查询</button>
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
                                                <th><strong>产品大类</strong></th>
                                                <th><strong>产品小类</strong></th>
                                                <th><strong>评级</strong></th>
                                                <th class="text-center" style="width: 15%;"><strong>操作</strong></th>
                                            </tr>
                                        </thead>
                                        <tbody class="no-border-y">
                                            <%
                                                for (Product item : productList) {
                                            %>
                                             <tr id="<%=item.getProductId()%>">
                                                <td><%=item.getProductCode()%></td>
                                                <td><%=item.getProductName()%></td>
                                                <td><%=item.getScore()%></td>
                                                <td><%=item.getCategoryName()%></td>
                                                <td><%=item.getSubcategoryName()%></td>
                                                <td><div class="rate" data-rate="<%=item.getRate()%>"></div></td>
                                                <!-- td><input type="checkbox" name="isRecommend" class="switch" data-size="small" <% if (item.getIsRecommend() == 2) { %>checked<% } %>></td -->
                                                <td class="text-center">
                                                    <a href="<%=basePath%>/console/product/display?productId=<%=item.getProductId()%>" class="label label-default"><i class="fa fa-pencil"></i></a>
                                                    <a href="javascript:;" class="label label-danger btn-remove"><i class="fa fa-times"></i></a>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            
                            <div class="content">
                                <div class="text-center">
                                    <a href="<%=basePath%>/console/product/display" class="btn btn-primary"><i class="fa fa-plus"></i> 添加</a>
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
    <script src="<%=basePath%>/resources/lib/jquery/plugins/gritter/jquery.gritter.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/raty/jquery.raty.js"></script>
    <script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.mask.js"></script>
	<script src="<%=basePath%>/resources/lib/bootstrap/plugins/switch/bootstrap-switch.js"></script>
    
    <script type="text/javascript">
    	$(function() {
    		'use strict';
    		
    		$('.switch').bootstrapSwitch({
    			labelWidth: 5,
    			onSwitchChange: function(event, state) {
    				var $tr = $(this).closest('tr');
        			var id = $tr.attr('id');
        			
        			var data = {
        				productId : id
        			};
        			
    				var inputName = $(this).attr('name');
    				if (inputName == 'isRecommend') {
    					data['isRecommend'] = state == true ? 2 : 1;
    				} else if (inputName == 'isFlashSale') {
    					data['isFlashSale'] = state == true ? 2 : 1;
    				}
    				
    				// console.log(JSON.stringify(data)); return;
    				
    				$.ajax({
    				    type : 'POST',
    				    url : '<%=basePath%>/console/product/switchStatus',
    				    data : data,
    				    successCallback : function(json) {
    					    if (json.result) {
    					    	// ...
    					    } else {
    						    $.gritter.add({
    						        text : '操作失败，失败原因：' + json.data,
    						        position : 'top-right',
    						        class_name : 'danger'
    						    });
    					    }
    				    }
    				});
    			}
    		});
    		
    		$.fn.raty.defaults.path = '<%=basePath%>/resources/lib/jquery/plugins/raty/images';
    		$('.rate').raty({
    			hints: ['没有销量', '销量差', '销量一般', '销量不错', '热卖商品'],
    		    score : function() {
    			    return $(this).attr('data-rate');
    		    },
    		    click : function(score, evt) {
    		    	var $tr = $(this).closest('tr');
        			var id = $tr.attr('id');
        			
    		    	$.ajax({
    				    type : 'POST',
    				    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
    				    url : '<%=basePath%>/console/product/rating',
    				    data : {
    					    productId : id,
    					    rate : score
    				    },
    				    successCallback : function(json) {
    					    if (json.result) {
    					    	// ...
    					    } else {
    						    $.gritter.add({
    						        text : '操作失败，失败原因：' + json.data,
    						        position : 'top-right',
    						        class_name : 'danger'
    						    });
    					    }
    				    }
    				});
    		    }
    		});

    		$('input[name=lowScore]').mask('#999999.0');
    		$('input[name=highScore]').mask('#999999.0');

    		$('a.btn-remove').click(function() {
    			var $tr = $(this).closest('tr');
    			var id = $tr.attr('id');

    			if (confirm('此操作不可恢复！确定删除？')) {
    				$.ajax({
    				    type : 'POST',
    				    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
    				    url : '<%=basePath%>/console/product/remove',
    				    data : {
    					    productId : id
    				    },
    				    successCallback : function(json) {
    					    if (json.result) {
    						    $.gritter.add({
    						        text : '删除成功',
    						        position : 'top-right',
    						        class_name : 'success'
    						    });

    						    $tr.remove();
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
    		
    	});
    </script>
</body>
</html>
