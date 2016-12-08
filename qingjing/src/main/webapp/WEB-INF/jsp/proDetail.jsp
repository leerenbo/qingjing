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
<%@ include file="include/openid.jsp" %>

</head>
<body>
	<!-- Views -->
	<div class="C_warp page  M_proDetail" id='proDetail'>
		<header class="bar bar-nav nav" id="nav_head">
			<a class="icon icon-left pull-left back" data-no-cache="true" href="/"></a>
			<div class="pull-right">
				<span class="s1" id="user"><i class="C_icon C_icon12"></i></span> <span class="s2" id="shopCar"><i class="C_icon C_icon13"></i> <!--存在浮动溢出 所以分为2个控制  只支持个位数--> <label class="circle circle_white" id="circle"></label> <em class="num C_red" id="circle_num">0</em> </span>
			</div>
			<h1 class="C_tit">
				<i class="C_icon C_icon11 mid C_icon11_fix"></i>
			</h1>
		</header>
		<div class="content">
			<p class="dis_title">
				<span class="C_lf">${production.name}${production.subName}</span><span class="C_ri C_red">${production.price }元</span>
			</p>

			<div id="detail" class="main_banner swiper-container" >
				<div id="selectedproductionimgs" class="swiper-wrapper">
					<c:forEach var="productionTypeUri" items="${production.productionTypes[0].imgsUri}" varStatus="status">
						<div class="swiper-slide">
							<img src="${productionTypeUri}" width="100%" />
						</div>
					</c:forEach>
				</div>
				<!-- 如果需要分页器 -->
				<div class="swiper-pagination"></div>
			</div>
			<p class="choose">选择外观花色</p>
			<!--超出四个字折行-->
			<ul class="chooseOne">
				<c:forEach var="productionType" items="${production.productionTypes}" varStatus="status">
					<li>
						<div class="btn <c:if test='${status.count==1}'>push</c:if>" productionTypeId="${productionType.id}"></div>
						<p class="productionTypeName">${productionType.name}</p>
					</li>
				</c:forEach>
			</ul>
			<div class="card C_clear">
				<div class="card-content">
					<div class="card-content-inner">
						<div class="product">
							<div class="left_img">
								<img src="${production.thumbnailUri}" width="100%">
								<c:if test="${production.tag=='新品'}">
									<i class="C_icon C_icon09 fixPos animated pulse infinite"></i>
								</c:if>
								<c:if test="${production.tag=='热销'}">
									<i class="C_icon C_icon10 fixPos animated pulse infinite"></i>
								</c:if>
							</div>
							<div class="ri_construction">
								<p class="t C_ellipsis">
									${production.name}<em>${production.subName}</em>
								</p>
								<p class="tra C_ellipsises">${production.shotDescrpiton}</p>
								<p class="money">
									<em>${production.price}</em> 元
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="chooseNum"></div>
			<p class="sevenDay C_bg_white">${production.promise}</p>
			<div class="detail_img">${production.descrption}</div>
		</div>
		<footer class="C_btn footer_btn2">
			<a class="confirm" id="addCars">加入购物车</a><a class="cancel external " id="buyNow" external>立即购买</a>
		</footer>
	</div>

	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>

</body>
</html>