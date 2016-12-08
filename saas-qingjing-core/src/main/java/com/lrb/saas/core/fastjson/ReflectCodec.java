package com.lrb.saas.core.fastjson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.IdentityHashMap;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Deprecated
public class ReflectCodec implements ObjectDeserializer, ObjectSerializer {

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
		try {
			SerializeWriter out = serializer.out;
			if (object == null) {
				out.writeNull();
				return;
			}

			if (writeReference(serializer, object, features)) {
				return;
			}
			SerialContext parent = serializer.getContext();
			serializer.setContext(parent, object, fieldName, 0, features);

			Field[] fields = FieldUtils.getAllFields(object.getClass());
			out.write('{');
			for (int i = 0; i < fields.length; i++) {
				if (i == 0) {
					out.writeFieldName(fields[i].getName());
					serializer.writeWithFieldName(FieldUtils.readField(fields[i], object, true), fields[i].getName(), fields[i].getType(), features);
					continue;
				}
				out.write(',');
				out.writeFieldName(fields[i].getName());
				serializer.writeWithFieldName(FieldUtils.readField(fields[i], object, true), fields[i].getName(), fields[i].getType(), features);
			}
			out.write('}');
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
		try {
			Class clazz = (Class) type;
			Object re = clazz.newInstance();
			Field[] fields = FieldUtils.getAllFields(clazz);
			for (int i = 0; i < fields.length; i++) {
				if (Modifier.isFinal(fields[i].getModifiers())) {
					continue;
				}
				FieldUtils.writeField(fields[i], re, parser.parseObject(fields[i].getType(), fields[i].getName()), true);
			}
			return (T) re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getFastMatchToken() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean writeReference(JSONSerializer serializer, Object object, int fieldFeatures) {
		SerialContext context = serializer.getContext();
		int mask = SerializerFeature.DisableCircularReferenceDetect.mask;
		if (context == null || (context.features & mask) != 0 || (fieldFeatures & mask) != 0) {
			return false;
		}

		IdentityHashMap<Object, SerialContext> references = null;
		try {
			references = (IdentityHashMap<Object, SerialContext>) FieldUtils.readField(serializer, "references", true);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (references != null && references.containsKey(object)) {
			serializer.writeReference(object);
			return true;
		} else {
			return false;
		}
	}

}
