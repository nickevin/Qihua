<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/pages/front/include/import.jsp"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/pages/front/include/meta.jsp" />

<title>柒花 - 最天然的皮肤专家</title>

<!-- libraries -->
<jsp:include page="/WEB-INF/pages/front/include/style.jsp" />

<!-- global styles -->
<link href="<%=basePath%>/resources/css/front/style.css" rel="stylesheet">

<!-- this page specific styles -->
<style type="text/css">
</style>

</head>
<body>
	<div id="wrapper" class="container">
		<jsp:include page="/WEB-INF/pages/front/include/header.jsp" />

		<div id="main-container">
			<div class="row">
				<jsp:include page="/WEB-INF/pages/front/guide/menu.jsp" />	
				
				<div class="col-md-9">
					<ol class="breadcrumb">
						<li>配送方式 </li>
						<li class="active">交易条款</li>
					</ol>
					<div class="page-box">
						<p>您在柒花官网的兑换中心下订单或接受柒花官网送货之前，有义务遵守以下交易条款:</p>
						<ul>
							<li>1、“兑换”的商品价格以会员下订单时柒花官网上价格为准。
							<li>2、请准确填写会员的真实姓名、送货地址及联系方式。因如下情况造成订单延迟或无法配送等，柒花官网将不承担责任。</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A. 会员提供错误信息和不详细的地址。</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. 货物送达无人签收，由此造成的重复配送所产生的费用及相关的后果。</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. 不可抗力，例如：自然灾害、交通戒严、突发战争等。</li>
							<li>3、安全性：会员在线“兑换”商品，我们会保证交易信息的安全，并由柒花官网授权的员工处理会员的订单。</li>
							<li>4、隐私权：柒花官网尊重会员的隐私权，在任何情况下，我们都不会将会员的个人和订单信息出售或泄露给任何第三方（国家司法机关调取除外）。我们从网站中得到的所有客户信息仅用来处理会员的相关订单。</li>
							<li>5、免责：如因不可抗力或其它柒花官网无法控制的原因致使柒花官网系统损坏或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，柒花官网会尽可能合理地协助处理善后事宜，并努力使客户免受经济损失。</li>
							<li>6、会员监督：柒花官网希望通过不懈努力，为会员提供最佳服务，柒花官网在给会员提供服务的全过程中接受会员的监督。</li>
							<li>7、争议处理：如果会员与柒花官网之间发生任何争议，可依据当时双方所认定的协议及相关法律进行解决。</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/WEB-INF/pages/front/include/footer.jsp" />
	</div>

	<jsp:include page="/WEB-INF/pages/front/include/copyright.jsp" />

	<!-- ================== JavaScript ================== -->

	<script type="text/javascript">
		$(function() {
	        'use strict';
        });
	</script>
</body>
</html>