package com.lrb.saas.core.message.invoker;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.lrb.saas.core.message.request.SAASRequest;
import com.lrb.saas.core.message.request.query.SAASQueryRequest;
import com.lrb.saas.core.message.response.SAASQueryResponse;
import com.lrb.saas.core.message.response.SAASResponse;

public class SAASHttpInvoker {

	public static String baseUrl = "http://m.qinjuu.com/saas-qingjing-shop/";

	private static HttpClient httpClient;

	private static Map<String, String> urlMap = new HashMap<String, String>();

	static {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(50);// 连接池最大并发连接数
		connManager.setDefaultMaxPerRoute(50);// 单路由最大并发数
		httpClient = HttpClients.custom().setConnectionManager(connManager).build();
	}

	public static <T> SAASResponse<T> get(String url, String id) {
		HttpGet httpGet = new HttpGet(url + "?id=" + id);
		String re = null;
		try {
			re = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		SAASResponse response = JSON.parseObject(re, SAASResponse.class);
		return response;
	}

	public static <T> SAASResponse<T> put(SAASRequest<T> saasRequest) {
		HttpPut httpPut = new HttpPut(saasRequest.getUrl());
		String entityString = JSON.toJSONString(saasRequest);
		httpPut.setHeader(HttpHeaders.ACCEPT, "application/json;UTF-8");
		httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;UTF-8");
		httpPut.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		httpPut.setHeader(HttpHeaders.PRAGMA, "no-cache");

		StringEntity entity = new StringEntity(entityString, ContentType.create("application/json", "UTF-8"));
		httpPut.setEntity(entity);
		String re = null;
		try {
			re = EntityUtils.toString(httpClient.execute(httpPut).getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAASResponse response = JSON.parseObject(re, SAASResponse.class);
		return response;
	}

	public static <T> SAASResponse<T> post(SAASRequest<T> saasRequest) {
		HttpPost httpPost = new HttpPost(saasRequest.getUrl());
		httpPost.setHeader(HttpHeaders.ACCEPT, "application/json;UTF-8");
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;UTF-8");
		httpPost.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		httpPost.setHeader(HttpHeaders.PRAGMA, "no-cache");

		String entityString = JSON.toJSONString(saasRequest);
		StringEntity entity = new StringEntity(entityString, ContentType.create("application/json", "UTF-8"));
		httpPost.setEntity(entity);
		String re = null;
		try {
			re = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAASResponse response = JSON.parseObject(re, SAASResponse.class);
		return response;
	}

	public static <T> SAASQueryResponse<T> query(String url, SAASQueryRequest saasQueryRequest) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.ACCEPT, "application/json;UTF-8");
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;UTF-8");
		httpPost.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		httpPost.setHeader(HttpHeaders.PRAGMA, "no-cache");

		String entityString = JSON.toJSONString(saasQueryRequest);
		StringEntity entity = new StringEntity(entityString, ContentType.create("application/json", "UTF-8"));
		httpPost.setEntity(entity);
		String re = null;
		try {
			re = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAASQueryResponse response = JSON.parseObject(re, SAASQueryResponse.class);
		return response;
	}

	public static <T> SAASResponse<T> delete(String url, String... ids) {
		HttpDelete httpDelete = new HttpDelete(
				url + "?" + Arrays.stream(ids).map((id) -> "ids=" + id).collect(Collectors.joining("&")));
		httpDelete.setHeader(HttpHeaders.ACCEPT, "application/json;UTF-8");
		httpDelete.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		httpDelete.setHeader(HttpHeaders.PRAGMA, "no-cache");

		String re = null;
		try {
			re = EntityUtils.toString(httpClient.execute(httpDelete).getEntity(), "UTF-8");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SAASResponse response = JSON.parseObject(re, SAASResponse.class);
		return response;
	}

}
