package com.peg.model;

import java.util.Date;
/**
 * 最新统计月份
 * @author Administrator
 *
 */
public class LaterSumtime {
    private Long id;

    private String sumMonth;

    private String createUser;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSumMonth() {
        return sumMonth;
    }

    public void setSumMonth(String sumMonth) {
        this.sumMonth = sumMonth == null ? null : sumMonth.trim();
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
}