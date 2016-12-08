package com.lrb.saas.weixin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("weixin")
public class WeiXinController {

	private static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?";
	private static String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";

	private String token;
	private String encodingAESKey;
	private String access_token;
	private String appID;
	private String appSecret;
	@Resource
	private Properties config;

	@PostConstruct
	public void init() {
		token = config.getProperty("weixin.token");
		encodingAESKey = config.getProperty("weixin.encodingAESKey");
		appID = config.getProperty("weixin.AppID");
		appSecret = config.getProperty("weixin.AppSecret");
		getAccessToken();
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
		String accessToken = null;
		String ret = "";
		CloseableHttpClient client = HttpClients.createDefault();
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
				accessToken = (String) json.get("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	public static String getJsapiTicke(String accessToken) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("type", "jsapi"));
		params.add(new BasicNameValuePair("access_token", accessToken));

		// 要传递的参数.
		String uri = GET_JSAPI_TICKET_URL + URLEncodedUtils.format(params, HTTP.UTF_8);
		String jsapiTicket = "";
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
				jsapiTicket = (String) json.get("ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsapiTicket;
	}

}
