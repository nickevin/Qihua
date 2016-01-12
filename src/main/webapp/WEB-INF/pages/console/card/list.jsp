<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/console/include/import.jsp"%>

<%@page import="com.qihua.common.PageModel"%>
<%@page import="com.qihua.console.card.Card"%>
<%@page import="com.qihua.console.card.CardQueryParameter"%>

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
<link href="<%=basePath%>/resources/lib/bootstrap/plugins/datepicker/bootstrap-datepicker-range.css" rel="stylesheet">

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

div.dataTables_info {
	padding-top: 26px;
}
</style>

<%
    CardQueryParameter queryParam = (CardQueryParameter) request.getAttribute("queryParam");
    
	PageModel<Card> pageModel = (PageModel<Card>) request.getAttribute("pageModel");
    List<Card> cardList = pageModel.getResultSet();
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
                    <li class="active">礼券管理</li>
                </ol>
            </div>
            <div class="cl-mcont">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block-flat">
                            <div class="content">
                                <form class="form-horizontal" action="<%=basePath%>/console/card" style="margin-bottom: 20px" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-offset-2 control-label">充值卡号</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="cardNo" value="<%=queryParam.getCardNo()%>" />
                                        </div>
                                        <label class="col-sm-1 control-label">密码</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" name="password" value="<%=queryParam.getPassword()%>" />
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
                                        <label class="col-sm-1 control-label">充值日期</label>
										<div class="col-sm-3">
											<input readonly="readonly" type="text" class="form-control date-picker" id="rangeDate" name="rangeDate" value="<%=queryParam.getRangeDate()%>"/>
											<input type="hidden" class="form-control col-lg-1" id="startDate" name="startDate" value="<%=queryParam.getStartDate()%>" />
											<input type="hidden" class="form-control col-lg-1" id="endDate" name="endDate" value="<%=queryParam.getEndDate()%>"/>	
										</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-offset-2 control-label">状态</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" name="status">
												<option value=""></option>
												<option value="<%=Constants.CARD_TYPE_ENABLE%>"><%=Constants.CARD_TYPE_ENABLE_NAME%></option>
												<option value="<%=Constants.CARD_TYPE_DISABLE%>"><%=Constants.CARD_TYPE_DISABLE_NAME%></option>
											</select>
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
                                                <th><strong>状态</strong></th>
                                                <th><strong>充值卡号</strong></th>
                                                <th><strong>密码</strong></th>
                                                <th><strong>礼券</strong></th>
                                                <th><strong>充值时间</strong></th>
                                                <th><strong>添加时间</strong></th>
                                                <th class="text-center" style="width: 15%;"><strong>操作</strong></th>
                                            </tr>
                                        </thead>
                                        <tbody class="no-border-y">
                                            <%
                                                for (Card item : cardList) {
                                            %>
                                            <tr id="<%=item.getCardId()%>">
                                                <td><% if (item.getStatus() == Constants.CARD_TYPE_DISABLE) { %><span class="label label-success"><%=Constants.CARD_TYPE_DISABLE_NAME%></span><% } else { %><span class="label label-default"><%=Constants.CARD_TYPE_ENABLE_NAME%></span><% } %></td>
                                                <td><%=item.getCardNo()%></td>
                                                <td><%=item.getPassword()%></td>
                                                <td><%=item.getScore()%></td>
                                                <td><% if (item.getRechargeTime() != null) { %><%=item.getRechargeTime()%><% } %></td>
                                                <td><%=item.getInsertTime()%></td>
                                                <td class="text-center">
                                                	<a href="<%=basePath%>/console/card/display?cardId=<%=item.getCardId()%>" class="label label-default"><i class="fa fa-pencil"></i></a>
                                                	<% if (item.getStatus() == Constants.CARD_TYPE_ENABLE) { %> 
                                                    <a href="javascript:;" class="label label-danger btn-remove"><i class="fa fa-times"></i></a>
                                                    <% } %>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>

								<div class="row">
									<div class="col-sm-12">
										<div class="pull-left">
											<div class="dataTables_info" id="datatable-icons_info">第 <%=pageModel.getCurrent()%> 页，共 <%=pageModel.getTotalPages()%> 页，总计：<%=pageModel.getTotalElements()%> 条</div>
										</div>
										<div class="pull-right">
											<div class="dataTables_paginate paging_bs_normal">
												<ul class="pagination">
													<% if (pageModel.hasPrevious()) { %>
													<li class="prev"><a class="prev" href="<%=basePath%>/console/card?pageNo=<%=pageModel.getPrevious()%>&cardNo=<%=queryParam.getPassword()%>&lowScore=<%=queryParam.getLowScore()%>&highScore=<%=queryParam.getHighScore()%>&password=<%=queryParam.getPassword()%>&startDate=<%=queryParam.getStartDate()%>&endDate=<%=queryParam.getEndDate()%>&status=<%=queryParam.getStatus()%>">上一页</a></li>
													<% } else { %>
													<li class="prev disabled"><a href="javascript:;"><span class="fa fa-angle-left"></span>&nbsp;上一页</a></li>
													<% } %>
													
													<%  if (pageModel.hasNext()) { %>
													<li class="next"><a href="<%=basePath%>/console/card?pageNo=<%=pageModel.getNext()%>&cardNo=<%=queryParam.getPassword()%>&lowScore=<%=queryParam.getLowScore()%>&highScore=<%=queryParam.getHighScore()%>&password=<%=queryParam.getPassword()%>&startDate=<%=queryParam.getStartDate()%>&endDate=<%=queryParam.getEndDate()%>&status=<%=queryParam.getStatus()%>">下一页&nbsp;<span class="fa fa-angle-right"></span></a></li>
													<% } else { %>
													<li class="next disabled"><a href="javascript:;">下一页&nbsp;<span class="fa fa-angle-right"></span></a></li>
													<%} %>
												</ul>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
                            
                            <div class="content">
                                <div class="text-center">
                                    <a href="<%=basePath%>/console/card/display" class="btn btn-primary"><i class="fa fa-plus"></i> 添加</a>
                                    <a href="<%=basePath%>/console/card/upload" class="btn btn-primary"><i class="fa fa-plus"></i> 批量上传</a>
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
	<script src="<%=basePath%>/resources/lib/jquery/plugins/jquery.mask.js"></script>
    <script src="<%=basePath%>/resources/lib/moment/moment.js"></script>
    <script src="<%=basePath%>/resources/lib/bootstrap/plugins/datepicker/bootstrap-datepicker-range.js"></script>

    <script type="text/javascript">
		$(function() {
			'use strict';
			
			$('input[name=lowScore]').mask('#999999.0');
    		$('input[name=highScore]').mask('#999999.0');
			$('select[name=status]').val('<%=queryParam.getStatus()%>');
    		
			$('.date-picker').daterangepicker({
				ranges : {
					'今天': [moment(), moment()],
					'昨天': [moment().subtract(1, 'd'), moment().subtract(1, 'd')],
					'本周': [moment().startOf('week').add(1,'d'), moment().startOf('week').add(7,'d')],
					'本月': [moment().startOf('month'), moment().endOf('month')]
				},
				opens : 'left',
				format : 'YYYY-MM-DD'
			}).on('apply.daterangepicker', function(ev, picker) {
				$('#startDate').val(picker.startDate.format('YYYY-MM-DD'));
				$('#endDate').val(picker.endDate.format('YYYY-MM-DD'));
			}).on('cancel.daterangepicker', function(ev, picker) {
				$('#rangeDate').val('');
				$('#startDate').val('');
				$('#endDate').val('');
			});
			
			$('a.btn-remove').click(function() {
    			var $tr = $(this).closest('tr');
    			var id = $tr.attr('id');
    			
    			if (confirm('此操作不可恢复！确定删除？')) {
    				$.ajax({
    					type : 'POST',
    					url : '<%=basePath%>/console/card/remove',
    					data : {
    						cardId: id
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
