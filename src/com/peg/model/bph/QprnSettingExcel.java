package com.peg.model.bph;

import java.math.BigDecimal;

public class QprnSettingExcel {
	
	private String qprn;

    private Short scoreType;

    private BigDecimal weight;

    private BigDecimal score;

	public String getQprn() {
		return qprn;
	}

	public void setQprn(String qprn) {
		this.qprn = qprn;
	}

	public Short getScoreType() {
		return scoreType;
	}

	public void setScoreType(Short scoreType) {
		this.scoreType = scoreType;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
    
    

}
