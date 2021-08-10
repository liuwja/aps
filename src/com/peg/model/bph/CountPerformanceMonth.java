package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.peg.model.CommonForm;

public class CountPerformanceMonth {
    private Long id;

    private Date checkMonth;

    private String factory;

    private String checkIndexName;

    private BigDecimal targetValue;

    private BigDecimal actualValue;

    private BigDecimal totalValue;

    private Date createTime;
    
    private String queryMonth;
    
    private Long boxDefectQty;
    
    private Long oqcDefectQty;
    
    private Long qualityQty;
    
    private Long productQty;
    
    private String startTime;
    private String endTime;
    
    
    private String formJsonValue;
    private List<CommonForm> items; 
    private String values;
    private String startDate;
    private String endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(Date checkMonth) {
        this.checkMonth = checkMonth;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getCheckIndexName() {
        return checkIndexName;
    }

    public void setCheckIndexName(String checkIndexName) {
        this.checkIndexName = checkIndexName == null ? null : checkIndexName.trim();
    }

    public BigDecimal getTargetValue() {
        return targetValue ;
    }

    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }

    public BigDecimal getActualValue() {
        return actualValue   ;
    }

    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }

    public BigDecimal getTotalValue() {
        return totalValue ;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public Long getBoxDefectQty() {
		return boxDefectQty;
	}

	public void setBoxDefectQty(Long boxDefectQty) {
		this.boxDefectQty = boxDefectQty;
	}

	public Long getOqcDefectQty() {
		return oqcDefectQty;
	}

	public void setOqcDefectQty(Long oqcDefectQty) {
		this.oqcDefectQty = oqcDefectQty;
	}

	public Long getQualityQty() {
		return qualityQty;
	}

	public void setQualityQty(Long qualityQty) {
		this.qualityQty = qualityQty;
	}

	public Long getProductQty() {
		return productQty;
	}

	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}

	public String getFormJsonValue() {
		return formJsonValue;
	}

	public void setFormJsonValue(String formJsonValue) {
		this.formJsonValue = formJsonValue;
	}

	public List<CommonForm> getItems() {
		return items;
	}

	public void setItems(List<CommonForm> items) {
		this.items = items;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    
}