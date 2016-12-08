<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<div class="C_warp page M_creatAdr2" id='creatAdr2'>
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
			<form id="address" action="/shop/addAddress?openid=${openid}">
				<div class="shuru C_clear">
					<div class="C_lf">收货人：</div>
					<div class="C_ri">
						<input name="name" type="text" placeholder="请输入姓名" />
					</div>
				</div>
				<div class="shuru C_clear">
					<div class="C_lf">手机号码：</div>
					<div class="C_ri">
						<input name="phoneNumber" type="tel" placeholder="请输入手机号码" />
					</div>
				</div>
				<div class="shuru C_clear">
					<div class="C_lf">所在地区：</div>
					<div class="C_ri">
						<input name="location" type="text" id='city-picker' value="辽宁 大连 沙河口区" />
					</div>
				</div>
				<div class="shuru C_clear">
					<div class="C_lf">街道地址：</div>
					<div class="C_ri">
						<input name="location2" type="text" placeholder="请输入详细地址" />
					</div>
				</div>
				<footer id="saveAddress" class="C_btn footer_btn2">
					<input type="submit" class="choose">保存并设置地址</input>
				</footer>
			</form>
		</div>
	</div>
	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>
</body>
</html>