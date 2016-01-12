<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<style type="text/css">
.modal-backdrop.in {
    opacity: 0.2;
}

.header-links ul.nav li>a {
	height: 20px;
	padding-top: 0px;
}

.search-user-area {
	margin-top: 40px;
}

#user-action {
	margin-top: 12px;
}

.featured-col {
	background: none repeat scroll 0 0 #fff;
	border: 1px solid #e8e8e8;
	margin-bottom: 10px;
	padding: 10px 10px 10px 10px;
}

label.error {
	color: #cc0000;
	font-weight: normal;
	padding: 0;
	margin: 8px 0;
}
</style>

<header id="header-area">
	<div class="header-top">
		<div class="row">
			<div class="col-sm-8 col-xs-12">
				<div class="header-info pull-left">
				<% if (member != null) { %>
				<%=member.getMemberName()%>
				<% } else { %>
				您好
				<% } %>
				，欢迎来到柒花网！<%=request.getRemoteAddr()%>
				</div>
			</div>
			<div class="col-sm-4 col-xs-12">
				<div class="header-links">
					<ul class="nav navbar-nav pull-right">
						<% if (member != null) { %>
						<li><a href="<%=basePath%>/member/logout">&nbsp;<i class="fa fa-arrow-right"></i> <span class="hidden-sm hidden-xs">退出</span></a></li>
						<% } else { %>
						<li><a href="<%=path%>/member/login" data-target="#modal-login" data-toggle="modal">&nbsp;<i title="" class="fa fa-user"></i> <span class="hidden-sm hidden-xs">登录</span></a></li>
						<li><a href="<%=path%>/member/register" data-target="#modal-register" data-toggle="modal">&nbsp;<i title="" class="fa fa-plus-circle"></i> <span class="hidden-sm hidden-xs">注册</span></a></li>
						<% } %>
						<li><i title="" class="fa fa-phone"></i>&nbsp;客服电话：<span class="hidden-sm hidden-xs">400-808-6569</span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
        <div class="col-md-4 col-xs-12">
            <div id="logo">
                <a href="<%=basePath%>"><img src="<%=basePath%>/resources/images/logo.png" title="柒花" alt="柒花" class="img-responsive" /></a>
            </div>
        </div>
        <div class="col-md-8 col-xs-12">
            <div class="row search-user-area">
                <div class="col-md-7 col-xs-12">
					<div id="search">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="">
							<span class="input-group-btn"><button class="btn btn-md btn-red" type="button"><i class="fa fa-search"></i> 搜索</button></span>
						</div>
					</div>
				</div>
                <div class="col-md-5 col-xs-12">
                    <div id="user-action">
						<div class="btn-group">
							<button data-toggle="dropdown" class="btn btn-default dropdown-toggle" aria-expanded="false">
								<i class="fa fa-user"></i>&nbsp;&nbsp;柒花会员&nbsp;<i class="fa fa-caret-down"></i>
							</button>
							<ul class="pull-right dropdown-menu">
								<li><a href="<%=basePath%>/member/profile">个人中心</a></li>
								<li><a href="<%=basePath%>/member/recharge" data-target="#modal-recharge" data-toggle="modal">礼券充值</a></li>
							</ul>
						</div>
						<a class="btn btn-default" href="<%=basePath%>/cart"><i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;我的购物车</a>
					</div>
                </div>
            </div>
        </div>
    </div>

	<div id="modal-login" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div id="modal-register" class="modal fade">
        <div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
    </div>
    
    <div class="modal fade" id="modal-recharge">
    	<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
    </div>
</header>

<nav id="main-menu" class="navbar" role="navigation">
    <div class="navbar-header">
        <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-cat-collapse">
            <span class="sr-only">导航栏</span> <i class="fa fa-bars"></i>
        </button>
    </div>
    <div class="collapse navbar-collapse navbar-cat-collapse">
        <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>" class="active home">首页</a></li>
            <li><a href="<%=basePath%>/gift" class="gift">兑换中心</a></li>
            <li><a href="<%=basePath%>/club" class="club">俱乐部</a></li>
            <li><a href="<%=basePath%>/partner" class="partner">伙伴入驻</a></li>
            <li><a href="<%=basePath%>/merchant" class="merchant">联盟商家</a></li>
        </ul>
    </div>
</nav>