<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags-->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>轻井商城</title>
<meta name="keywords" content="填写描述语句，方便seo抓取">
<meta name="description" content="">
<%@ include file="include/css.jsp"%>
<%@ include file="include/openid.jsp"%>
</head>
<body>
	<!-- Views -->
	<div class="C_warp page M_orderCenter" id=''>
		<header class="bar bar-nav nav" id="nav_head">
			<a class="icon icon-left pull-left back"></a>
			<div class="pull-right">
				<span class="s1" id="user"><i class="C_icon C_icon12"></i></span> <span class="s2" id="shopCar"><i class="C_icon C_icon13"></i> <!--存在浮动溢出 所以分为2个控制  只支持个位数--> <label class="circle circle_white" id="circle"></label> <em class="num C_red" id="circle_num">0</em> </span>
			</div>
			<h1 class="C_tit">
				<i class="C_icon C_icon11 mid C_icon11_fix"></i>
			</h1>
		</header>
		<div class="content">
			<c:forEach var="orderList" items="${orderLists}">
				<div class="order C_bg_white">
					<p class="tit">
						普通订单：${orderList.id} <span class="sign">${orderList.status}</span>
					</p>
					<ul class="order_con">
						<li class="l1"><c:forEach var="orderItem" items="${orderList.orderItems}">
								<div class="oreder_img">
									<img src="${orderItem.productionType.production.thumbnailUri}" width="100%" />
								</div>
							</c:forEach></li>
						<li class="l2 C_font1">共${orderList.count}件商品 <span class="C_mar_lf"> 合计：<em class="C_red">${orderList.money}元</em></span>
						</li>
						<li class="l3"><c:if test="${orderList.status=='待支付' }">
								<div class="C_btn C_btn1">
									<a href="#">支付</a>
								</div>
							</c:if> <c:if test="${orderList.status=='支付成功' }">
								<div class="C_btn C_btn1 C_btn_gray">
									<a href="#">待发货</a>
								</div>
							</c:if> <c:if test="${orderList.status=='已发货' }">
								<div class="C_btn C_btn1" id="delivery1">
									<a href="#">快递查询</a>
								</div>
							</c:if> <c:if test="${orderList.status=='已完成' }">
								<div class="C_btn C_btn1"">
									<a href="#">售后</a>
								</div>
							</c:if></li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>
	<script type="text/javascript">
		$(function() {
			/*弹窗*/
			$("#delivery1").click(function() {
				$.confirm('请去快递官网查询', function() {
					$.alert('顺丰电话 400-313-20');
				});
			});
		});
	</script>
</body>
</html>