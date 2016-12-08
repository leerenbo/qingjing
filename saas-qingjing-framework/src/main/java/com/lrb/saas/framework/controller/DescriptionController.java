package com.lrb.saas.framework.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.tomcat.util.buf.MessageBytes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.WebUtils;

import com.lrb.saas.core.annotation.SAASInterface;
import com.lrb.saas.core.enums.ContentType;
import com.lrb.saas.core.model.auth.SAASFieldInfo;
import com.lrb.saas.core.model.auth.SAASInterfaceInfo;
import com.lrb.saas.framework.util.ScanUtil;

@RestController
public class DescriptionController {

	@Resource
	RequestMappingHandlerMapping handlerMapping;

	@RequestMapping(value = { "/*/{method:GET|POST|PUT|PATCH|DELETE}", "/*/*/{method:GET|POST|PUT|PATCH|DELETE}",
			"/*/*/*/{method:GET|POST|PUT|PATCH|DELETE}" })
	public SAASInterfaceInfo description(HttpServletRequest httpServletRequest) throws Exception {

		// 修改httpServletRequest
		RequestFacade requestFacade = (RequestFacade) httpServletRequest;
		Request request = (Request) FieldUtils.readDeclaredField(requestFacade, "request", true);
		org.apache.coyote.Request coyoteRequest = request.getCoyoteRequest();

		MessageBytes uriMB = (MessageBytes) FieldUtils.readDeclaredField(coyoteRequest, "uriMB", true);
		String uri = uriMB.getString();
		uriMB.setString(StringUtils.substringBeforeLast(uri, "/"));

		MessageBytes methodMB = (MessageBytes) FieldUtils.readDeclaredField(coyoteRequest, "methodMB", true);
		methodMB.setString(StringUtils.substringAfterLast(uri, "/"));

		MessageBytes decodedUriMB = (MessageBytes) FieldUtils.readDeclaredField(coyoteRequest, "decodedUriMB", true);
		decodedUriMB.setString(StringUtils.substringBeforeLast(decodedUriMB.getString(), "/"));

		request.removeAttribute(WebUtils.INCLUDE_SERVLET_PATH_ATTRIBUTE);

		request.getMappingData().wrapperPath
				.setString(StringUtils.substringBeforeLast(request.getMappingData().wrapperPath.getString(), "/"));

		// 获取对应controller
		HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
		if (handlerExecutionChain == null) {
			return null;
		}
		Method controller = ((HandlerMethod) handlerExecutionChain.getHandler()).getMethod();

//		System.out.println(((HandlerMethod) handlerExecutionChain.getHandler()).getBean());
//		System.out.println(((HandlerMethod) handlerExecutionChain.getHandler()).getBeanType());
		// 获取对应接口注释信息
		SAASInterface saasInterface = controller.getAnnotation(SAASInterface.class);
		SAASInterfaceInfo saasInterfaceInfo = new SAASInterfaceInfo(saasInterface, httpServletRequest.getRequestURI(),
				request.getMethod());

		// 获取对应请求参数属性注释信息
		List<SAASFieldInfo> requestFields = ScanUtil.scanParameterFields(controller.getParameters(),
				saasInterface.id());
		if (requestFields != null && requestFields.size() > 0) {
			saasInterfaceInfo.getRequestFields().addAll(requestFields);
		}
		saasInterfaceInfo.getResponseFields()
				.addAll(ScanUtil.scanReturnFields(controller.getGenericReturnType(), saasInterface.id()));
		if ("application/json".equals(httpServletRequest.getContentType())) {
			saasInterfaceInfo.setContentType(ContentType.APPLICATION_JSON);
		}
		return saasInterfaceInfo;
	}

}
