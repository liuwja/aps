package com.peg.model;

import java.util.Date;

public class Relation {
    private Long id;

    private String crm;

    private String mes;

    private String entryName;

    private Date entryTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm == null ? null : crm.trim();
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes == null ? null : mes.trim();
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName == null ? null : entryName.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}