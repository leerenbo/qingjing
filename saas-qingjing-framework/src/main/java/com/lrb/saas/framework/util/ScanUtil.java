package com.lrb.saas.framework.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.lrb.saas.core.annotation.SAASField;
import com.lrb.saas.core.enums.SAASInterfaceId;
import com.lrb.saas.core.message.request.SAASRequest;
import com.lrb.saas.core.model.auth.SAASFieldInfo;
import com.lrb.saas.core.model.auth.SAASValidationInfo;

public class ScanUtil {

	@SuppressWarnings("rawtypes")
	public static List<SAASFieldInfo> scanParameterFields(Parameter[] parameters, SAASInterfaceId saasInterfaceId) {

		List<SAASFieldInfo> re = new ArrayList<SAASFieldInfo>();

		// 检测@RequestBody
		Parameter parameter = null;
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i].isAnnotationPresent(RequestBody.class)) {
				parameter = parameters[i];
			}
		}

		if (parameter == null) {// 没有参数@RequestBody
			return null;
		} else {// 有参数
			Class[] groups = ((Validated) parameter.getAnnotation(Validated.class)).value();

			ParameterizedType parameterizedType = (ParameterizedType) parameter.getParameterizedType();
			Type rawType = parameterizedType.getRawType();
			if (SAASRequest.class.isAssignableFrom(((Class) rawType))) {
				re.addAll(scanClassFields((Class) rawType, saasInterfaceId, groups));
			}
			for (int i = 0; i < re.size(); i++) {
				if (re.get(i).getType().equals("T")) {
					Type[] type = parameterizedType.getActualTypeArguments();
					if (type.length > 0) {
						re.get(i).setType(type[0].getTypeName());
						List<SAASFieldInfo> innerField = scanClassFields((Class) type[0], saasInterfaceId, groups);
						re.get(i).getInnerSAASFieldInfos().addAll(innerField);
					}
				}
			}
		}

		return re;
	}

	@SuppressWarnings("rawtypes")
	public static List<SAASFieldInfo> scanClassFields(Class clazz, SAASInterfaceId saasInterfaceId, Class<?>[] groups) {
		List<SAASFieldInfo> re = new ArrayList<SAASFieldInfo>();

		Field[] fields = FieldUtils.getFieldsWithAnnotation(clazz, SAASField.class);
		for (int i = 0; i < fields.length; i++) {
			SAASField saasField = scanBestFitSAASField(fields[i], saasInterfaceId);
			if (saasField != null) {
				SAASFieldInfo saasFieldInfo = new SAASFieldInfo(saasField);
				saasFieldInfo.setType(fields[i].getGenericType().getTypeName());
				saasFieldInfo.setFieldName(fields[i].getName());
				saasFieldInfo.getInnerSAASFieldInfos().addAll(scanClassFields(fields[i].getType(), saasInterfaceId, groups));
				if (groups != null) {
					saasFieldInfo.setSaasValidationInfo(scanValidationByGroupsOnField(fields[i], groups));
				}
				re.add(saasFieldInfo);
			}
		}
		return re;
	}

	private static SAASField scanBestFitSAASField(Field field, SAASInterfaceId saasInterfaceId) {
		SAASField[] saasFields = field.getAnnotationsByType(SAASField.class);

		for (int j = 0; j < saasFields.length; j++) {
			if (ArrayUtils.contains(saasFields[j].saasInterfaceIds(), saasInterfaceId)) {
				return saasFields[j];
			}
		}

		for (int j = 0; j < saasFields.length; j++) {
			if (ArrayUtils.contains(saasFields[j].saasInterfaceIds(), SAASInterfaceId.ALL)) {
				return saasFields[j];
			}
		}

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T extends Annotation> T scanBestFitValidByGroupsOnField(Field field, Class[] groups, Class<T> validClass) {
		try {
			ArrayList<T> annotations = new ArrayList<T>();
			T annotation = field.getAnnotation(validClass);
			if (annotation != null) {
				annotations.add(annotation);
			}
			Class listClass = ClassUtil.getInnerClass(validClass, "List");

			Annotation list = field.getAnnotation(listClass);
			if (list != null) {
				Object insideAnnotations = MethodUtils.invokeMethod(list, "value");
				int sum = Array.getLength(insideAnnotations);
				for (int i = 0; i < sum; i++) {
					annotations.add((T) Array.get(insideAnnotations, i));
				}
			}

			for (T t : annotations) {
				Class[] fieldGroups = (Class[]) MethodUtils.invokeMethod(t, "groups");
				for (int i = 0; i < groups.length; i++) {
					if (ArrayUtils.contains(fieldGroups, groups[i])) {
						return t;
					}
				}
			}

			if (groups.length == 0 || (groups.length == 1 && groups[0] == Default.class)) {
				for (T t : annotations) {
					Class[] fieldGroups = null;
					fieldGroups = (Class[]) MethodUtils.invokeMethod(t, "groups");
					if (fieldGroups.length == 0 || (fieldGroups.length == 1 && fieldGroups[0] == Default.class))
						return t;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// Optional optional = annotations.stream().filter(decimalMax -> {
		// try {
		// return Arrays.stream((T[]) MethodUtils.invokeMethod(decimalMax, "groups")).anyMatch((T g) -> {
		// return ArrayUtils.contains(groups, g);
		// });
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return false;
		// }).findFirst();
		//

		return null;
	}

	@SuppressWarnings("rawtypes")
	private static SAASValidationInfo scanValidationByGroupsOnField(Field field, Class[] groups) {
		SAASValidationInfo saasValidationInfo = new SAASValidationInfo();

		saasValidationInfo.initDecimalMax(scanBestFitValidByGroupsOnField(field, groups, DecimalMax.class));

		saasValidationInfo.initDecimalMin(scanBestFitValidByGroupsOnField(field, groups, DecimalMin.class));

		saasValidationInfo.initDigits(scanBestFitValidByGroupsOnField(field, groups, Digits.class));

		saasValidationInfo.initFuture(scanBestFitValidByGroupsOnField(field, groups, Future.class));

		saasValidationInfo.initPast(scanBestFitValidByGroupsOnField(field, groups, Past.class));

		saasValidationInfo.initMax(scanBestFitValidByGroupsOnField(field, groups, Max.class));

		saasValidationInfo.initMin(scanBestFitValidByGroupsOnField(field, groups, Min.class));

		saasValidationInfo.initNull(scanBestFitValidByGroupsOnField(field, groups, Null.class));

		saasValidationInfo.initNotBlank(scanBestFitValidByGroupsOnField(field, groups, NotBlank.class));

		saasValidationInfo.initNotEmpty(scanBestFitValidByGroupsOnField(field, groups, NotEmpty.class));

		saasValidationInfo.initNotNull(scanBestFitValidByGroupsOnField(field, groups, NotNull.class));

		saasValidationInfo.initPattern(scanBestFitValidByGroupsOnField(field, groups, Pattern.class));

		saasValidationInfo.initSize(scanBestFitValidByGroupsOnField(field, groups, Size.class));

		return saasValidationInfo;
	}

	@SuppressWarnings("rawtypes")
	public static List<SAASFieldInfo> scanReturnFields(Type genericReturnType, SAASInterfaceId saasInterfaceId) {
		List<SAASFieldInfo> re = new ArrayList<>();
		if (genericReturnType instanceof ParameterizedType) {
			re.addAll(scanClassFields((Class) ((ParameterizedType) genericReturnType).getRawType(), saasInterfaceId, null));
			
			for (int i = 0; i < re.size(); i++) {
				if (re.get(i).getType().equals("T")) {
					Type[] type = ((ParameterizedType) genericReturnType).getActualTypeArguments();
					if (type.length > 0) {
						re.get(i).setType(type[0].getTypeName());
						List<SAASFieldInfo> innerField = scanClassFields((Class) ((ParameterizedType) genericReturnType).getActualTypeArguments()[0], saasInterfaceId, null);
						re.get(i).getInnerSAASFieldInfos().addAll(innerField);
					}
				}
			}

		} else if (genericReturnType instanceof Class) {
			re.addAll(scanClassFields((Class) genericReturnType, saasInterfaceId, null));

		}
		return re;
	}

	// public static void main(String[] args) {
	// // List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });
	// // list.forEach(System.out::println);
	// System.out.println(ArrayUtils.contains(new String[] {}, new String[] {}));
	// }

}
