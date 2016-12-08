package com.lrb.saas.core.message.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.lrb.saas.core.annotation.SAASField;

public class SAASResponse<T> {

	@JSONField(serialzeFeatures = { SerializerFeature.WriteEnumUsingName })
	@SAASField(name = "操作结果", remark = "invalid参数验证错误未操作，fail操作失败，success操作成功")
	protected Result result;

	public enum Result {
		invalid, fail, success
	}

	@SAASField(name = "参数验证错误信息", remark = "parameterName参数名,message错误原因。当result=invalid时有数据")
	protected List<InvalidInfo> invalidInfos;

	@SAASField(name = "操作信息")
	protected String message;
	@SAASField(name = "异常信息", remark = "用于异常传递，普通用户忽略此项。当result=fail时有数据")
	protected Exception exception;
	@SAASField(name = "返回消息体")
	protected T body;

	@JSONField(deserialize = false, serialize = false)
	protected FastJsonConfig fastJsonConfig;

	protected String bussinessCode;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<InvalidInfo> getInvalidInfos() {
		return invalidInfos;
	}

	public void setInvalidInfos(List<InvalidInfo> invalidInfos) {
		this.invalidInfos = invalidInfos;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public FastJsonConfig getFastJsonConfig() {
		return fastJsonConfig;
	}

	public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
		this.fastJsonConfig = fastJsonConfig;
	}

	public String getBussinessCode() {
		return bussinessCode;
	}

	public void setBussinessCode(String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

}
