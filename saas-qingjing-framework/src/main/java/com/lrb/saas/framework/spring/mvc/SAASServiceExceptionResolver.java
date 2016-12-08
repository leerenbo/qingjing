package com.lrb.saas.framework.spring.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.lrb.saas.core.message.response.SAASResponse;
import com.lrb.saas.core.message.response.SAASResponse.Result;

public class SAASServiceExceptionResolver implements HandlerExceptionResolver {

	protected final Logger logger = LogManager.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error(ExceptionUtils.getStackTrace(ex));
		SAASResponse saasResponse = new SAASResponse();
		saasResponse.setResult(Result.fail);
		saasResponse.setMessage("系统繁忙，请稍后再试");
		saasResponse.setException(ex);
		String json = JSON.toJSONString(saasResponse);
		response.setContentType("application/json;UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return null;
	}
}
