package com.peg.model;

import java.math.BigDecimal;

/**
 * 用于插入多行数据
 * @author jft
 *
 */
public class CommonForm {

	private String queryMonth;
	
	private BigDecimal targetValue;

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public BigDecimal getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}
	
	
}
