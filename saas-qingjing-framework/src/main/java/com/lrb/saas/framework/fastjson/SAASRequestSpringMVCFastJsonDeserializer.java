package com.lrb.saas.framework.fastjson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.lrb.saas.core.message.request.SAASRequest;

public class SAASRequestSpringMVCFastJsonDeserializer implements ObjectDeserializer {

	static {
		ParserConfig.getGlobalInstance().putDeserializer(SAASRequest.class,
				new SAASRequestSpringMVCFastJsonDeserializer());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

		SAASRequest<?> saasRequest = new SAASRequest();
		JSONObject object = parser.parseObject();

		Type genericModel = JSONObject.class;
		if (type instanceof ParameterizedType) {
			genericModel = ((ParameterizedType) type).getActualTypeArguments()[0];
		}

		saasRequest.setUrl(object.getString("url"));
		saasRequest.setToken(object.getString("token"));
		saasRequest.setClient(object.getString("client"));
		saasRequest.setTenant(object.getString("tenant"));
		saasRequest.setChannel(object.getString("channel"));
		JSONObject body = (JSONObject) object.getJSONObject("body");
		saasRequest.setBody(JSON.parseObject(body.toJSONString(), genericModel));
		return (T) saasRequest;
	}

	@Override
	public int getFastMatchToken() {
		return 0;
	}

}
