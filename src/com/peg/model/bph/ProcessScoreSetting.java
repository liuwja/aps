package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;

import com.peg.model.baseCommonVo;

public class ProcessScoreSetting extends baseCommonVo{
    private Long id;

    private String factory;

    private String indexContent;

    private String indexCode;

    private BigDecimal scoreType;

    private BigDecimal score;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getIndexContent() {
        return indexContent;
    }

    public void setIndexContent(String indexContent) {
        this.indexContent = indexContent == null ? null : indexContent.trim();
    }

   

    public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public BigDecimal getScoreType() {
        return scoreType;
    }

    public void setScoreType(BigDecimal scoreType) {
        this.scoreType = scoreType;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}