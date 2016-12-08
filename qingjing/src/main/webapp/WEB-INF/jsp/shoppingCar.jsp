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
	<div class="C_warp page M_shoppingCar" id='shoppingCar'>
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
			<c:forEach var="orderItem" items="${orderList.orderItems}">
				<div class="product" productionTypeId="${orderItem.productionType.id}">
					<div class="left_img">
						<img src="${orderItem.productionType.imgsUri[0]}" width="100%"> <i class="C_icon C_icon10 fixPos"></i>
					</div>
					<div class="ri_construction">
						<p class="t C_ellipsis">
							${orderItem.productionType.production.name}<em>${orderItem.productionType.production.subName}</em>
						</p>
						<p class="tra C_ellipsises">售价：${orderItem.productionType.production.price}元 合计：${orderItem.productionType.production.price*orderItem.count}元</p>
						<div>
							<p class="box"">
					<a class="minus" id="minus"> <i class="minus_icon"></i></a> <span class="nums" id="pro_num">${orderItem.count}</span> <a class="plus" id="plus"><i class="plus_icon"></i></a>
					</p>
				</div>
		</div>
		<i class="garbage" id="garbage1"></i>
				</div>
			</c:forEach>

		</div>

		<footer class="C_btn footer_btn2">
			<a href="" class="confirm C_font1">共${orderList.count}件 <em class="C_font2 C_mar_lf">合计：${orderList.money}元</em></a><a href="/shop/payMent?openid=${openid}" class="cancel">立即购买</a>
		</footer>
	</div>
	<!-- 你的html代码 -->
<%@ include file="include/js.jsp"%>

</body>
</html>