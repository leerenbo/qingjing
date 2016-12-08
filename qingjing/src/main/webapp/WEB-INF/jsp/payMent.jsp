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
	<div class="C_warp page M_payment" id='payMent'>
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
			<div class="address" id="address">
				<a class="adress_con" id="addressString"><c:if test="${empty orderList.location}">请选择邮寄地址</c:if>${orderList.location}</a> <i class="icon icon-right arrow"></i>
			</div>
			<div class="productList">
				<c:forEach var="orderItem" items="${orderList.orderItems}">
					<div class="pro1">
						<div class="C_lf info">
							<div class="oreder_img">
								<img src="${orderItem.productionType.imgsUri[0]} " width="100%" />
							</div>
							<span class="C_ellipsis"><em>${orderItem.productionType.production.name}</em><em>${orderItem.productionType.production.subName}</span>
						</div>
						<div class="C_ri money">
							<span>${orderItem.productionType.production.price}</span> x <span>${orderItem.count }</span> = ${orderItem.productionType.production.price*orderItem.count}元
						</div>
					</div>
				</c:forEach>
			</div>
			<!--                 <div class="youhuiQuan C_clear"><span class="C_lf" id="tip1">50元现金券</span><span class="C_ri" id="tip2">50元</span></div>
                <div class="youhui_con">
                <i class="arrow"></i>
                <ul class="youhui">
                	<li><i class="quan10"></i><i class="choose_on hidden"></i></li>
                	<li><i class="quan20"></i><i class="choose_on hidden"></i></li>
                	<li><i class="quan30"></i><i class="choose_on hidden"></i></li>
                	<li><i class="quan50"></i><i class="choose_on"></i></li>
                </ul>
                </div>
 -->
		</div>

		<footer class="C_btn footer_btn2">
			<a href="" class="confirm C_font1">共${orderList.count}件 <em class="C_font2 C_mar_lf">合计：${orderList.money}元</em></a><a id="pay" href="" class="cancel">立即支付</a>
		</footer>
	</div>
	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>
</body>
</html>