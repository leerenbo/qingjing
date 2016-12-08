package com.lrb.saas.core.message.request.query.filter;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.mongodb.core.query.Criteria;

import com.lrb.saas.core.util.GenericsUtils;

public class OperatorFilter {
	private String fieldName;
	private String operator;
	private String value;
	private String[] values;
	private List<OperatorFilter> orFilters = new ArrayList<>();
	private List<OperatorFilter> andFilters = new ArrayList<>();

	public OperatorFilter() {
		super();
	}

	public OperatorFilter(String fieldName, String operator, String value, String[] values) {
		super();
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
		this.values = values;
	}

	public OperatorFilter(String fieldName, String operator, String value, String[] values, List<OperatorFilter> orFilters, List<OperatorFilter> andFilters) {
		super();
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
		this.values = values;
		this.orFilters = orFilters;
		this.andFilters = andFilters;
	}

	public static void main(String[] args) {
//		Field field = FieldUtils.getField(OperatorFilter.class, "orFilters", true);
//		System.out.println(GenericsUtils.getFieldGenricType(field));
		List<String> ls=new ArrayList<>();
		
		System.out.println(List.class.isAssignableFrom(ls.getClass()));
		System.out.println(Object.class.isAssignableFrom(String.class));
	}

	private List valuesConverterTo(Class fieldClass, String[] values) {
		return Arrays.stream(values).map((value) -> valueConverterTo(fieldClass, value)).collect(Collectors.toList());
	}

	private Object valueConverterTo(Class fieldClass, String value) {
		if (fieldClass.equals(String.class)) {
			if (operator.equals("$regex")) {
				return "%" + value + "%";
			}
		}
		if (fieldClass.equals(Long.class)) {
			return Long.parseLong(value);
		}
		if (fieldClass.equals(Integer.class)) {
			return Integer.parseInt(value);
		}
		if (fieldClass.equals(Date.class)) {
			try {
				return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd", "HH:mm:ss" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (fieldClass.equals(Short.class)) {
			return Short.parseShort(value);
		}
		if (fieldClass.equals(BigDecimal.class)) {
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if (fieldClass.equals(Float.class)) {
			return Float.parseFloat(value);
		}
		if (fieldClass.equals(Double.class)) {
			return Double.parseDouble(value);
		}
		if (fieldClass.equals(Boolean.class)) {
			return Boolean.parseBoolean(value);
		}
		return value;
	}

	public Map<String, Object> toHqlParams(Class clazz) {
		Map<String, Object> re = new HashMap<>();
		if (value != null) {
			Class fieldClass = FieldUtils.getField(clazz, fieldName, true).getType();
			re.put(fieldName + operator, valueConverterTo(fieldClass, value));
		}
		if (values != null) {
			Class fieldClass = GenericsUtils.getFieldGenricType(FieldUtils.getField(clazz, fieldName, true));
			re.put(fieldName + operator, valuesConverterTo(fieldClass, values));
		}
		if (orFilters.size() > 0) {
			re.putAll(orFilters.stream().map((filter) -> filter.toHqlParams(clazz)).reduce((m1, m2) -> {
				m1.putAll(m2);
				return m1;
			}).get());
		}
		if (andFilters.size() > 0) {
			re.putAll(andFilters.stream().map((filter) -> filter.toHqlParams(clazz)).reduce((m1, m2) -> {
				m1.putAll(m2);
				return m1;
			}).get());
		}
		return re;
	}

	public String toHqlJoin(Class clazz){
		Class fieldClass = FieldUtils.getField(clazz, fieldName, true).getType();
		if (List.class.isAssignableFrom(fieldClass)) {
			return " join _t."+fieldName+" as "+fieldName+" ";
		}
		return null;
	}
	
	public String toHqlCondition() {
		if (orFilters.size() > 0) {
			return "( " + orFilters.stream().map((filter) -> {
				return filter.toHqlCondition();
			}).collect(Collectors.joining(" or ")) + ")";
		}
		if (andFilters.size() > 0) {
			return "( " + andFilters.stream().map((filter) -> {
				return filter.toHqlCondition();
			}).collect(Collectors.joining(" and ")) + ")";
		}
		switch (operator) {
		case "$eq":
			return fieldName + " = :" + fieldName + operator;
		case "$lt":
			return fieldName + " < :" + fieldName + operator;
		case "$lte":
			return fieldName + " <= :" + fieldName + operator;
		case "$gt":
			return fieldName + " > :" + fieldName + operator;
		case "$gte":
			return fieldName + " >= :" + fieldName + operator;
		case "$regex":
			return fieldName + " like :" + fieldName + operator;
		case "$ne":
			return fieldName + " != " + fieldName + operator;
		case "$in":
			return fieldName + " in :" + fieldName + operator;
		case "$nin":
			return fieldName + " not in :" + fieldName + operator;
		}
		return "";
	}

	public Criteria toMongoCriteria() {
		if (orFilters.size() > 0) {
			return orFilters.stream().map((filter) -> {
				return filter.toMongoCriteria();
			}).reduce((filter, filter2) -> {
				filter.orOperator(filter2);
				return filter;
			}).get();
		}
		if (andFilters.size() > 0) {
			return andFilters.stream().map((filter) -> {
				return filter.toMongoCriteria();
			}).reduce((filter, filter2) -> {
				filter.andOperator(filter2);
				return filter;
			}).get();
		}
		switch (operator) {
		case "$eq":
			return where(fieldName).is(value);
		case "$lt":
			return where(fieldName).lt(value);
		case "$lte":
			return where(fieldName).lte(value);
		case "$gt":
			return where(fieldName).gt(value);
		case "$gte":
			return where(fieldName).gte(value);
		case "$regex":
			return where(fieldName).regex(value);
		case "$ne":
			return where(fieldName).ne(value);
		case "$in":
			return where(fieldName).in(value);
		case "$nin":
			return where(fieldName).nin(value);
		}
		return null;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<OperatorFilter> getOrFilters() {
		return orFilters;
	}

	public void setOrFilters(List<OperatorFilter> orFilters) {
		this.orFilters = orFilters;
	}

	public List<OperatorFilter> getAndFilters() {
		return andFilters;
	}

	public void setAndFilters(List<OperatorFilter> andFilters) {
		this.andFilters = andFilters;
	}

}
