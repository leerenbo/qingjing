package com.lrb.saas.weixin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lrb.saas.weixin.controller.WeiXinController;

@Component
public class OpenidInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (((HandlerMethod) handler).getMethodAnnotation(NeedOpenid.class)!=null) {
			String openid = request.getParameter("openid");
			if (StringUtils.isNotBlank(openid)) {
				request.setAttribute("openid", openid);
			}
			openid = (String) request.getAttribute("openid");
			String url = request.getHeader("url");
			if (StringUtils.isBlank(url)) {
				 url = request.getRequestURL().toString();
			}

			if (StringUtils.isBlank(openid)) {
				openid = request.getParameter("openid");
				if (StringUtils.isBlank(openid)) {
					String code = request.getParameter("code");
					if (StringUtils.isBlank(code)) {
						response.sendRedirect(WeiXinController.toWeixinAuthorizeURL(url));
						response.getWriter().close();
						return false;
					} else {
						String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeiXinController.appID + "&secret=" + WeiXinController.appSecret + "&code=" + code + "&grant_type=authorization_code";
						HttpGet get = new HttpGet(accessTokenUrl);

						CloseableHttpResponse closeableHttpResponse = WeiXinController.client.execute(get);
						if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
							String closeableHttpResponseEntityString = EntityUtils.toString(closeableHttpResponse.getEntity());
							JSONObject json = JSON.parseObject(closeableHttpResponseEntityString);
							request.setAttribute("openid", json.getString("openid"));
						}
					}
				} else {
					request.setAttribute("openid", openid);
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

}
