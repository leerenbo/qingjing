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
	<div class="C_warp page M_creatAdr" id='creatAdr'>
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
			<c:forEach items='${addresses}' var='address'>
				<a href="/shop/payMent?addressid=${address.id}&openid=${openid}">
					<div class="adrlist">
						<div class="adr1">
							<p class="p1">
								${address.name} <span>${address.phoneNumber }</span>
							</p>
							<p class="p2">
								<span class="province ">${address.location }</span>
							</p>
						</div>
					</div>
				</a>
			</c:forEach>

			<!--新增按钮-->
			<div class="plus" id="plusAdr">
				<a class="plus_logo"></a>
			</div>

			<footer class="C_btn footer_btn2">
				<a href="#" class="choose">请选择地址</a>
			</footer>
		</div>

	</div>
	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>

</body>
</html>