package com.lrb.saas.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lrb.saas.core.model.qingjing.order.OrderList;
import com.lrb.saas.core.util.UUIDUtil;
import com.lrb.saas.framework.service.BaseService;
import com.lrb.saas.weixin.OpenidSession;
import com.lrb.saas.weixin.interceptor.NeedOpenid;
import com.lrb.saas.weixin.model.ChooseWXPay;
import com.lrb.saas.weixin.model.JsApiTicket;
import com.tencent.WXPay;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.PayReqData;
import com.tencent.protocol.pay_protocol.PayResData;

@RestController
@RequestMapping("weixin")
public class WeiXinController {

	private static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?";
	private static String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";

	public static String token;
	public static String encodingAESKey;
	public static String access_token;
	public static String appID;
	public static String appSecret;
	public static CloseableHttpClient client = HttpClients.createDefault();
	public static String jsapi_ticket;

	@Resource
	private Properties config;

	@Resource
	private BaseService<OrderList> orderListServer;

	@PostConstruct
	public void init() {
		token = config.getProperty("weixin.token");
		encodingAESKey = config.getProperty("weixin.encodingAESKey");
		appID = config.getProperty("weixin.AppID");
		appSecret = config.getProperty("weixin.AppSecret");
		getAccessToken();
		getJsapiTicke();

		Configure.setAppID(appID);
		Configure.setCertLocalPath("/Users/lirenbo/qingjing/apiclient_cert.p12");
		Configure.setCertPassword("1381227902");
		Configure.setIp("118.198.83.104");
		Configure.setKey("01c2mc8yy8n7v39mu0vtejmopfs908mu");
		Configure.setMchID("1381227902");

	}

	@ResponseBody
	@GetMapping("js/config")
	public JsApiTicket jsConfig(String url) {
		String noncestr = UUIDUtil.randomUUIDString();
		long timestamp = System.currentTimeMillis();
		JsApiTicket jsApiTicket = new JsApiTicket(noncestr, jsapi_ticket, timestamp, url, WeiXinController.appID);
		return jsApiTicket;
	}

	public static String toWeixinAuthorizeURL(String url) {
		String re = "";
		try {
			re = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + URLEncoder.encode(url, "utf-8") + "&response_type=code&scope=snsapi_base#wechat_redirect";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return re;
	}

	@GetMapping("connect")
	public String connect(String signature, String timestamp, String nonce, String echostr) {
		System.out.println("WeiXinController.connect()");
		ArrayList<String> paramsList = new ArrayList<String>();
		if (token != null) {
			paramsList.add(token);
		}
		if (timestamp != null) {
			paramsList.add(timestamp);
		}
		if (nonce != null) {
			paramsList.add(nonce);
		}
		Collections.sort(paramsList);
		String orderedParamsString = paramsList.stream().collect(Collectors.joining());
		if (DigestUtils.sha1Hex(orderedParamsString).equals(signature)) {
			return echostr;
		}
		return null;
	}

	@GetMapping("refreshToken")
	public String getAccessToken() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "client_credential"));
		params.add(new BasicNameValuePair("appid", appID));
		params.add(new BasicNameValuePair("secret", appSecret));

		// 要传递的参数.
		String uri = GET_ACCESS_TOKEN_URL + URLEncodedUtils.format(params, HTTP.UTF_8);
		String ret = "";
		try {
			HttpGet get = new HttpGet(uri);

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				ret = EntityUtils.toString(response.getEntity());
				JSONObject json = JSON.parseObject(ret);

				String errocode = null;
				if (json.get("errcode") != null) {
					errocode = String.valueOf(json.get("errcode"));
				}
				String errmsg = null;
				if (json.get("errmsg") != null) {
					errmsg = String.valueOf(json.get("errmsg"));
				}
				if (errocode != null && !"".equals(errocode)) {
					return "刷新失败";
				}
				access_token = (String) json.get("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}

	public static String getJsapiTicke() {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("type", "jsapi"));
		params.add(new BasicNameValuePair("access_token", access_token));

		// 要传递的参数.
		String uri = GET_JSAPI_TICKET_URL + URLEncodedUtils.format(params, HTTP.UTF_8);
		String ret = "";
		CloseableHttpClient client = HttpClients.createDefault();

		try {
			HttpGet get = new HttpGet(uri);

			CloseableHttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				ret = EntityUtils.toString(response.getEntity());

				JSONObject json = JSON.parseObject(ret);
				String errocode = null;
				if (json.get("errcode") != null) {
					errocode = String.valueOf(json.get("errcode"));
				}
				String errmsg = null;
				if (json.get("errmsg") != null) {
					errmsg = String.valueOf(json.get("errmsg"));
				}
				if (!"0".equals(errocode)) {
					return null;
				}
				jsapi_ticket = (String) json.get("ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsapi_ticket;
	}

	@RequestMapping("prePay")
	@NeedOpenid
	public ChooseWXPay prePay(@RequestAttribute String openid, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		String ip = httpServletRequest.getRemoteAddr();
		orderList.setStatus("待支付");
		orderList.setOpenid(openid);
		orderList.setSys_createDateTime(new Date());
		orderListServer.delete(orderList);
		orderList.setId(UUIDUtil.randomUUIDString());
		orderListServer.saveOrUpdate(orderList);
		PayReqData payReqData = new PayReqData("轻井株式会社-家用电器类", orderList.getId(), ((Double) (Double.valueOf(orderList.getMoney()) * 100)).intValue(), ip, "http://mdat.qinjuu.com/shop/notify", "JSAPI", openid, DateFormatUtils.format(DateUtils.addMinutes(new Date(), 15), "yyyyMMddHHmmss"));// ,
		// PayReqData payReqData = new PayReqData("轻井株式会社-家用电器类",
		// orderList.getId(), 1, ip, "http://mdat.qinjuu.com/shop/notify",
		// "JSAPI", openid,DateFormatUtils.format(DateUtils.addMinutes(new
		// Date(), 15), "yyyyMMddHHmmss"));
		String payServiceResponseString = WXPay.requestScanPayService(payReqData);
		System.out.println(payServiceResponseString);
		PayResData scanPayResData = (PayResData) Util.getObjectFromXML(payServiceResponseString, PayResData.class);
		ChooseWXPay chooseWXPay = new ChooseWXPay(appID, System.currentTimeMillis(), UUIDUtil.randomUUIDString(), scanPayResData.getPrepay_id());
		return chooseWXPay;
	}

	@RequestMapping("paySuccess")
	public ModelAndView paySuccess(@RequestAttribute String openid, HttpServletRequest httpServletRequest) {
		OrderList orderList = (OrderList) OpenidSession.get(openid, "orderList");
		ModelAndView modelAndView = new ModelAndView("redirect:/orderCenter");
		orderList.setStatus("支付成功");
		orderListServer.saveOrUpdate(orderList);
		OpenidSession.remove(openid, "orderList");
		modelAndView.addObject("openid", openid);return modelAndView;
	}

}
