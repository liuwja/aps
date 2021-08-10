/*
 * @(#) ShiftGroupEnum.java 2015-7-23 下午07:48:04
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;



/**
 * 班组类型枚举
 * <p>
 * @author Lin, 2015-7-23 下午07:48:04
 */
public enum  ShiftGroupTypeEnum
{
	STAMPING("冲压班组"),ACCEMBLE("组装班组"),FASHIONING("精加工班组"),IQC("IQC"),OQC("OQC"),WELDING("点焊班组"),PAINTING("喷涂班组"),
	SUBASSEMBLY("部装班组"),COMPUTERBOARD("电脑板车间");
    
    private final String value;

    ShiftGroupTypeEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ShiftGroupTypeEnum getEnumByType(String type)
    {
    	for(ShiftGroupTypeEnum e : ShiftGroupTypeEnum.values())
    	{
    		if(e.getValue().equals(type))
    		{
    			return e;
    		}
    	}
		return null;
    }
}
