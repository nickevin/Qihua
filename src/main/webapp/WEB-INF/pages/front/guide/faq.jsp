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
						<li>帮助中心</li>
						<li class="active">常见问题</li>
					</ol>
					<div class="page-box">
						<h4>礼券类常见问题</h4>
						<ul>
							<li>问：什么是礼券？</li>
							<li>答：柒花礼券是柒花智购网（www.qihua.co）独家发行的礼品兑换券,可在柒花兑换中心按照1:1的比例免费兑换千种礼品。</li>
						</ul>
						<ul>
							<li>问：如何获得礼券？</li>
							<li>答：1、通过在联盟商家处消费可免费获得相应的礼券；
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、在柒花官网消费现金可免费获得相应的礼券；</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、注册成为柒花会员及邀请好友并成功绑定手机可免费获得相应的礼券。</li>
						</ul>
						<ul>
							<li>问：礼券的有效期是多久？</li>
							<li>答：半年。</li>
						</ul>
						<ul>
							<li>问：礼券的兑换值是多少？</li>
							<li>答：1礼券=1元钱</li>
						</ul>
						<ul>
							<li>问：怎么查询礼券余额？</li>
							<li>答：“登录”》“个人中心”》“礼券管理”，查询礼券余额。</li>
						</ul>
						<ul>
							<li>问：礼券可以抵作现金用吗？</li>
							<li>答：不可以的，礼券仅能在兑换中心免费换取千种超值商品。</li>
						</ul>
						<ul>
							<li>问：兑换的礼品是包邮的吗？</li>
							<li>答：是的。兑换中心换购的商品都是包邮的。</li>
						</ul>
						<ul>
							<li>问：如何充值礼券卡？</li>
							<li>答：“登录”》“柒花会员”》“礼券充值”。</li>
						</ul>
						<ul>
							<li>问：什么是柒花会员级别？</li>
							<li>答：按照会员在柒花累积的礼券金额的不同，分为普通会员和VIP会员；所有免费注册过的顾客在晋级为VIP会员之前，都是普通会员；VIP会员分为三个等级：黄金会员、白金会员、钻石会员。</li>
						</ul>
						<ul>
							<li>问：如何才能成为VIP会员？</li>
							<li>答：会员礼券累积到相应数量，系统即自动升级成为VIP会员：</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、礼券累计达1万的会员可升级为黄金会员； </li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、礼券累计达5万的会员可升级为白金会员；</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、礼券累计达10万的会员可升级为钻石会员。</li>
						</ul>
						
						<h4>账户类常见问题</h4>
						<ul>
							<li>问：我之前注册过柒花会员，忘记了用户名或密码，该怎么办？</li>
							<li>答：进入官网“登录”》“柒花会员”》“个人中心”》“忘记密码”。</li>
						</ul>
						<ul>
							<li>问：如何修改个人信息？</li>
							<li>答：进入官网“登录”》“个人中心”》对用户信息进行修改即可。</li>
						</ul>
						<ul>
							<li>问：会员个人信息是否会被外泄呢？</li>
							<li>答：不会。您的个人信息在柒花官方网站将通过系统受到标准安全措施的保护，任何未经授权的访问都将被拒绝。</li>
						</ul>
						
						<h4>兑换类常见问题</h4>
						<ul>
							<li>问：我提交订单成功了，为什么系统仍旧显示待审核？</li>
							<li>答：有可能您操作的时间正是非工作时间，所以导致没有及时通过审核。柒花客服值班时间：周一至周六9:00-18:00。</li>
						</ul>
						<ul>
							<li>问：在支付礼券时，会出现一些诸如链接、速度慢或根本无法进行链接、重复支付、不能输入银行卡密码等问题，这是什么原因？</li>
							<li>答：1、建议更换使用火狐、谷歌、IE8或以上版本的浏览器查看；  </li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、若不行，请进入IE删除浏览历史记录及COOKIES记录；</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、再若不行，请检查您当前网络的使用情况；</li>
							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、联系客服。</li>
						</ul>
						<ul>
							<li>问：退货时，我的礼券是否可以退还？</li>
							<li>答：如果您提交的退货申请已经通过了，那么您的礼券将会退到您的账户中去。</li>
						</ul>
						<ul>
							<li>问：退还的礼券什么时候到账？</li>
							<li>答：会即时退还到您的账户，最迟不会超过72小时。</li>
						</ul>
						
						<h4>配送类常见问题</h4>
						<ul>
							<li>问：我已经购买了商品，什么时候会发货呢？</li>
							<li>答：正常情况下您的订单将在48小时内发出。遇有订单高峰、节假日或者特殊物流情况，您的订单发货可能会稍有延迟，请谅解。</li>
						</ul>
						<ul>
							<li>问：我的订单已发货，但是为何还查看不到物流信息？</li>
							<li>答：您好，请您及时联系在线客服（客服热线：400-808-6569）。</li>
						</ul>
						<ul>
							<li>问：为什么我的订单下了很久都没有收到货？</li>
							<li>答：1、您可以点击“订单中心”查看该订单是否已经发出。如果订单未发出，请您及时联系在线客服（客服热线:400-808-6569）</li>
  							<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、如果订单已发出，则可能是由于快递的延误。</li>
						</ul>
						<ul>
							<li>问：我的包裹尚未发货，如何修改收货地址？</li>
							<li>答：请您联系客服帮您修改地址（客服热线:400-808-6569 ）。</li>
						</ul>
						
						<h4>退换货类常见问题</h4>
						<ul>
							<li>问：如果我要兑换的产品没货了怎么办？</li>
							<li>答：请您联系在线客服为您换货或者申请退还礼券（客服热线:400-808-6569）</li>
						</ul>
						<ul>
							<li>问：如果收到的货有质量问题怎么办？</li>
							<li>答：拍下照片，第一时间联系在线客服为您处理（客服热线:400-808-6569）。</li>
						</ul>
						<ul>
							<li>问：如果收到的货码数不合适怎么办？</li>
							<li>答：如果有库存，可以联系客服换货，但是来回运费由您来承担。</li>
						</ul>
						<ul>
							<li>问：我已经向客服提交退货/换货申请了，什么时候能给予答复？</li>
							<li>答：您好，在线客服会即时处理并给予答复（客服热线:400-808-6569）。</li>
						</ul>
						<ul>
							<li>问：退货/换货的运费怎么算的呢？</li>
							<li>答：如果是产品质量问题、产品损坏问题，我们会为您无偿退货/换货；但如果是产品大小、款式方面的问题，那么运费由您来承担。</li>
						</ul>
						
						<h4>售后服务类常见问题</h4>
						<ul>
							<li>退货流程图：</li>
							<li><img alt="退货流程图" src="<%=basePath%>/resources/images/guide/1.jpg"></li>
						</ul>
						<ul>
							<li>退换货服务标准：</li>
							<li>1、对全部商品；</li>
							<li>2、外包装完整，且不影响二次销售。一旦拆封无法复原商品不可退货（质量问题除外）；</li>
							<li>3、若交易已完成，发现质量问题，请在交易完成后2天内进行退款申请。</li>
						</ul>
						<ul>
							<li>不可退货特殊说明：</li>
							<li>1、无质量问题且外包装已拆无法复原，影响二次销售；</li>
							<li>2、认为刻意损害或不可抗力导致的损坏；</li>
							<li>3、非柒花官网出售的商品或已超出退换货期限的商品；</li>
							<li>4、商品的图片及信息仅供参考。因拍摄灯光及不同显示器色差等问题可能造成商品图片与实物有细微色差。</li>
						</ul>
						<ul>
							<li>退换货费用说明：</li>
							<li>1、如果是买家原因退货，由买家自行承担退货费用；</li>
							<li>2、如果是商品质量问题、描述不符而导致的退货，退货费用由柒花官网承担。</li>
						</ul>
						<ul>
							<li>退换货渠道：</li>
							<li>1、在线申请：请联系在线客服QQ申请退换货（周一至周六：9:00-18:00）；</li>
							<li>2、电话申请：请拨打客服电话 400-808-6569（周一至周六：9:00-18:00 ）申请退换货。</li>
							<li style="color: #e53f43">注：如遇国家法定节假日，则以新闻发布放假时间为准，请消费者届时关注。</li>
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