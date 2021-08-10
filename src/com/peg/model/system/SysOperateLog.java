package com.peg.model.system;

import java.util.Date;

public class SysOperateLog {
    private Long id;

    private Short opType;

    private String content;

    private String operator;

    private Date operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getOpType() {
        return opType;
    }

    public void setOpType(Short opType) {
        this.opType = opType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public SysOperateLog() {
    	
	}
    
	public SysOperateLog(Short opType, String content,
			String operator, Date operateTime) {
		super();
		this.opType = opType;
		this.content = content;
		this.operator = operator;
		this.operateTime = operateTime;
	}
    
    
}