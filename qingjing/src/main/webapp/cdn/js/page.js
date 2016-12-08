function showShoppingCartCount(){
	$.getJSON('/shop/showShoppingCartCount', {
		'openid':openid
	}, function(data, status, xhr) {
		$('#circle_num').text(data);
	});
}

function initHeader(){
	$("#user").click(function() {
		$.router.load('/orderCenter.html?openid='+openid);
	});
	$("#shopCar").click(function() {
		$.router.load('/shoppingCar.html?openid='+openid);
	});
	showShoppingCartCount();
}

$(function() {
	
	$(document).on("pageInit", "#index", function(e, pageId, $page) {
		initHeader();
		var mySwiper = new Swiper('#main_banner', {
			direction : 'horizontal',
			autoplay : 2000,
			speed : 300,
			loop : false,
			pagination : '.swiper-pagination'
		});

		$('#a1').click(function() {
			var top1 = document.getElementById("A1").offsetTop;
			$(".content").scrollTop(top1);
		})

		$('#a2').click(function() {
			var top2 = document.getElementById("A2").offsetTop;
			$(".content").scrollTop(top2);
		})
		$('#a3').click(function() {
			var top3 = document.getElementById("A3").offsetTop;
			$(".content").scrollTop(top3);
		})

		$(".content").scroll(function() {
			var contentHeight = $(this).scrollTop();
			if (contentHeight != "0") {
				$("#nav_head").removeClass("nav-apl").addClass("nav");
				$('#circle').addClass("circle_white");
				$('#circle_num').addClass("C_red");
				$()
			} else {
				$("#nav_head").removeClass("nav").addClass("nav-apl");
				$('#circle').removeClass("circle_white");
				$('#circle_num').removeClass("C_red");
			}
		});

		/* 跳转页面绑定 */
		$("#privilege").click(function() {
			$.router.load('coupon.html?openid='+openid);
		});
	});

	$(document).on("pageInit", "#proDetail", function(e, pageId, $page) {
		initHeader();

		var mySwiper2 = new Swiper('#detail', {
			direction : 'horizontal',
			autoplay : 2000,
			speed : 300,
			loop : false,
			pagination : '.swiper-pagination'
		});

		/* 跳转页面绑定 */
		$("#addCars").click(function() {
			var num = $('#circle_num').text();
			num = parseInt(num) + 1;
			$('#circle_num').empty();
			$('#circle_num').text(num);
			$(this).children(".btn").addClass("push");
			$('#circle').addClass("animated pulse");
			$.ajax({
				url : 'shop/addShoppingCart',
				data : {
					'productionTypeId': $(".push").attr('productionTypeId'),
					'openid':openid
				},
				dataType : 'json',
				success : function(data) {
					$.toast('加入购物车成功', 2345);
				}
			})
		});

		$("#buyNow").click(function() {
//			$.router.load('shop/payMent?productionTypeId=' + $(".push").attr('productionTypeId'));
			$.router.load('shop/payMent?productionTypeId=' + $(".push").attr('productionTypeId')+'&openid='+openid);
		})

		$(".chooseOne li").click(function() {
			$(".chooseOne li").children(".btn").removeClass("push");
			$(this).children(".btn").addClass("push");
			$.ajax({
				url : 'productionType',
				data : {
					'id' : $(this).children("div").attr('productionTypeId'),
					'openid':openid
				},
				dataType : 'json',
				success : function(data) {
					console.info(data.body);
					var innerHtml = '';
					for (var i = 0; i < data.body.imgsUri.length; i++) {
						innerHtml = innerHtml + '<div class="swiper-slide"><img src="' + data.body.imgsUri[i] + '"  width="100%"/></div>'

					}
					$('#selectedproductionimgs').html(innerHtml);
				}
			})
		});
	});

	$(document).on("pageInit", "#payMent", function(e, pageId, $page) {
		initHeader();

		$.getJSON('/weixin/js/config', {
			url : location.href
		}, function(data) {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : data.appId, // 必填，公众号的唯一标识
				timestamp : data.timestamp, // 必填，生成签名的时间戳
				nonceStr : data.noncestr, // 必填，生成签名的随机串
				signature : data.signature,// 必填，签名，见附录1
				jsApiList : [ 'chooseWXPay' ]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {

				$("#pay").click(function() {
					if ($("#addressString").text() == '请选择邮寄地址') {
						$.router.load('/creatAdr.html?openid='+openid);
						return;
					}

					$.getJSON('/weixin/prePay?openid='+openid, function(data) {
						wx.chooseWXPay({
							appId : data.appId,
							timestamp : data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
							nonceStr : data.nonceStr, // 支付签名随机串，不长于 32 位
							package : data.prepay_id, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
							signType : data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
							paySign : data.paySign, // 支付签名
							success : function(res) {
								$.router.load('/weixin/paySuccess.html?openid='+openid);
							}
						});

					});
				});

			});
		})

		// creatAdr
		$("#address").click(function() {
			$.router.load('/creatAdr.html?openid='+openid);
		});
	});

	$(document).on("pageInit", "#shoppingCar", function(e, pageId, $page) {
		initHeader();

		/* 增加减 */
		$(".minus").click(function() {
			var num = $(this).parent().children("span").text();
			if (num == 0) {
				$.toast("已经没了");
			} else {
				var that = this;
				$.getJSON('/shop/reduceShoppingCart', {
					'productionTypeId' : $(this).parent().parent().parent().parent().attr("productionTypeId"),
					'openid':openid
				}, function(data, status, xhr) {
					location.reload();
				});
			}
		});

		$(".plus").click(function(o) {
			var that = this;
			$.getJSON('/shop/addShoppingCart', {
				'productionTypeId' : $(this).parent().parent().parent().parent().attr("productionTypeId"),
				'openid':openid
			}, function(data, status, xhr) {
				location.reload();
			});
		});

		$(".garbage").click(function() {
			var that = this;
			$.getJSON('/shop/delShoppingCart', {
				'productionTypeId' : $(this).parent().attr("productionTypeId"),
				'openid':openid
			}, function(data, status, xhr) {
				location.reload();
			});

		});
	});

	$(document).on("pageInit", "#creatAdr", function(e, pageId, $page) {
		initHeader();

		/* 跳转页面绑定 */
		$("#plusAdr").click(function() {
			$.router.load('creatAdr2.html?openid='+openid);
		});

	});

	$(document).on("pageInit", "#creatAdr2", function(e, pageId, $page) {
		initHeader();

		$("#city-picker").cityPicker({
			toolbarTemplate : '<header class="bar bar-nav">\
	    <button class="button button-link pull-right close-picker">确定</button>\
	    <h1 class="title">选择收货地址</h1>\
	    </header>'
		});
	});

	$.init();
});
