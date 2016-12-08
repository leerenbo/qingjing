package com.lrb.saas.core.model.auth;

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

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class SAASValidationInfo {

	private String id;

	private String decimalMax;
	private Boolean decimalMaxInclusive;

	public void initDecimalMax(DecimalMax decimalMax) {
		if (decimalMax != null) {
			this.decimalMax = decimalMax.value();
			this.decimalMaxInclusive = decimalMax.inclusive();
		}
	}

	private String decimalMin;
	private Boolean decimalMinInclusive;

	public void initDecimalMin(DecimalMin decimalMin) {
		if (decimalMin != null) {
			this.decimalMin = decimalMin.value();
			this.decimalMinInclusive = decimalMin.inclusive();
		}
	}

	private Integer digitsInteger;
	private Integer digitsFraction;

	public void initDigits(Digits digits) {
		if (digits != null) {
			this.digitsInteger = digits.integer();
			this.digitsFraction = digits.fraction();
		}
	}

	private Boolean future;

	public void initFuture(Future future) {
		if (future != null) {
			this.future = true;
		}
	}

	private Boolean past;

	public void initPast(Past past) {
		if (past != null) {
			this.past = true;
		}

	}

	private Long max;

	public void initMax(Max max) {
		if (max != null) {
			this.max = max.value();
		}
	}

	private Long min;

	public void initMin(Min min) {
		if (min != null) {
			this.min = min.value();
		}
	}

	private Boolean notBlank;

	public void initNotBlank(NotBlank notBlank) {
		if (notBlank != null) {
			this.notBlank = true;
		}
	}

	private Boolean notEmpty;

	public void initNotEmpty(NotEmpty notEmpty) {
		if (notEmpty != null) {
			this.notEmpty = true;
		}
	}

	private Boolean notNull;

	public void initNotNull(NotNull notNull) {
		if (notNull != null) {
			this.notNull = true;
		}
	}

	private Boolean isNull;

	public void initNull(Null nvll) {
		if (nvll != null) {
			isNull = true;
		}
	}

	private String patternRegex;

	public void initPattern(Pattern pattern) {
		if (pattern != null) {
			patternRegex = pattern.regexp();
		}
	}

	private Integer sizeMin;
	private Integer sizeMax;

	public void initSize(Size size) {
		if (size != null) {
			sizeMin = size.min();
			sizeMax = size.max();
		}
	}

	public String getId() {
		return id;
	}

	public String getDecimalMax() {
		return decimalMax;
	}

	public Boolean getDecimalMaxInclusive() {
		return decimalMaxInclusive;
	}

	public String getDecimalMin() {
		return decimalMin;
	}

	public Boolean getDecimalMinInclusive() {
		return decimalMinInclusive;
	}

	public Integer getDigitsInteger() {
		return digitsInteger;
	}

	public Integer getDigitsFraction() {
		return digitsFraction;
	}

	public Boolean getFuture() {
		return future;
	}

	public Boolean getPast() {
		return past;
	}

	public Long getMax() {
		return max;
	}

	public Long getMin() {
		return min;
	}

	public Boolean getNotNull() {
		return notNull;
	}

	public Boolean getIsNull() {
		return isNull;
	}

	public String getPatternRegex() {
		return patternRegex;
	}

	public Integer getSizeMin() {
		return sizeMin;
	}

	public Integer getSizeMax() {
		return sizeMax;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDecimalMax(String decimalMax) {
		this.decimalMax = decimalMax;
	}

	public void setDecimalMaxInclusive(Boolean decimalMaxInclusive) {
		this.decimalMaxInclusive = decimalMaxInclusive;
	}

	public void setDecimalMin(String decimalMin) {
		this.decimalMin = decimalMin;
	}

	public void setDecimalMinInclusive(Boolean decimalMinInclusive) {
		this.decimalMinInclusive = decimalMinInclusive;
	}

	public void setDigitsInteger(Integer digitsInteger) {
		this.digitsInteger = digitsInteger;
	}

	public void setDigitsFraction(Integer digitsFraction) {
		this.digitsFraction = digitsFraction;
	}

	public void setFuture(Boolean future) {
		this.future = future;
	}

	public void setPast(Boolean past) {
		this.past = past;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}

	public void setPatternRegex(String patternRegex) {
		this.patternRegex = patternRegex;
	}

	public void setSizeMin(Integer sizeMin) {
		this.sizeMin = sizeMin;
	}

	public void setSizeMax(Integer sizeMax) {
		this.sizeMax = sizeMax;
	}

	public Boolean getNotBlank() {
		return notBlank;
	}

	public void setNotBlank(Boolean notBlank) {
		this.notBlank = notBlank;
	}

	public Boolean getNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(Boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

}
