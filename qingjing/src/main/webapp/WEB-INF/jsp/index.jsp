<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<div class="C_warp page M_mainPage" id='index'>
		<header class="bar bar-nav nav-apl" id="nav_head">
			<!--	<a class="icon icon-left pull-left">返回</a>-->
			<div class="pull-right">
				<span class="s1" id="user"><i class="C_icon C_icon12"></i></span> <span class="s2" id="shopCar"><i class="C_icon C_icon13"></i> <!--存在浮动溢出 所以分为2个控制  只支持个位数--> <label class="circle" id="circle"></label> <em class="num" id="circle_num">0</em> </span>
			</div>
			<h1 class="C_tit">
				<i class="C_icon C_icon11 animated bounce mid"></i>
			</h1>
		</header>
		<div class="content">
			<div class="fixHeight" style="height: 2.5rem;"></div>
			<div class="main_banner swiper-container" id="main_banner">
				<div class="swiper-wrapper">
					<c:forEach var="indexScrollPicture" items="${indexScrollPictures}">
						<div class="swiper-slide">
							<img src="${indexScrollPicture.imgUri}" width="100%">
						</div>
					</c:forEach>
				</div>
				<!-- 如果需要分页器 -->
				<div class="swiper-pagination"></div>
			</div>
			<ul class="firlist C_bg_white">
				<li><a  id='a1'  class="external"><i class="C_icon C_icon14"></i>
						<p>电热毯</p></a></li>
				<li><a  id='a2' class="external"><i class="C_icon C_icon15"></i>
						<p>电热坐垫</p></a></li>
				<li><a  id='a3' class="external"><i class="C_icon C_icon18"></i>
						<p>书桌加热器</p></a></li>
				<li><a href="QRCode.html"><i class="C_icon C_icon16"></i>
						<p>在线客服</p></a></li>
			</ul>
			<div class="dianReTan">
				<p class="titles" id="A1">
					<i class="C_icon C_icon07 logo"></i><i class="name font_orange">S-A.I电热毯</i>
				</p>

				<c:forEach var="production" items="${productions}">
					<c:if test="${production.category=='电热毯'}">
						<a href='proDetail.html?id=${production.id}&openid=${openid}'>
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
						</a>
					</c:if>
				</c:forEach>
			</div>

			<div class="dianReTan" id="A2">
				<p class="titles">
					<i class="C_icon C_icon08 logo"></i><i class="name">S-A.I电热坐垫</i>
				</p>
				<c:forEach var="production" items="${productions}">
					<c:if test="${production.category=='电热坐垫'}">
						<a href='proDetail.html?id=${production.id}&openid=${openid}'>
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
						</a>
					</c:if>
				</c:forEach>
			</div>
			<div class="dianReTan" id="A3">
				<p class="titles">
					<i class="C_icon C_icon08 logo"></i><i class="name">S-A.I书桌加热器</i>
				</p>

				<c:forEach var="production" items="${productions}">
					<c:if test="${production.category=='书桌加热器'}">
						<a href='proDetail.html?id=${production.id}&openid=${openid}'>
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
						</a>
					</c:if>
				</c:forEach>

			</div>
			<div class="footer">
				<img src="cdn/images/castle.png" width="100%" />
				<div class="f1">
					<img src="cdn/images/foot-1.png" width="100%" />
				</div>
			</div>
		</div>
		<!--此处设计最好重新兼容下  这样设计 无法转换成文字
		<div class="privilege" id="privilege" style="position: fixed; left: 0; bottom: 4rem; z-index: 100;">
			<img src="cdn/images/youhui.png" width="100%" />
		</div>-->
	</div>
	<!-- 你的html代码 -->
	<%@ include file="include/js.jsp"%>
</body>
</html>