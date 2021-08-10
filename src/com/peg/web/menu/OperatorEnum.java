/*
 * @(#) OperatorEnum.java 2014-3-4 上午09:10:00
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-4 上午09:10:00
 */
public enum OperatorEnum
{
	VIEW ("看", "1"), ADD ("增", "2"), EDIT ("改", "3"), DEL("删", "4");
	 
    // 成员变量  
    private String name;  
    
    private String code;  
    // 构造方法  
    private OperatorEnum(String name, String code) {  
        this.name = name;  
        this.code = code;  
    }  
    // 普通方法  
    public static String getName(String code) {  
        for (OperatorEnum c : OperatorEnum.values()) {  
            if (c.getCode().equals(code)) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    
    public static OperatorEnum getEnum(String code) {  
        for (OperatorEnum c : OperatorEnum.values()) {  
        	if (c.getCode().equals(code)) {  
                return c; 
            }  
        }  
        return null;  
    }  
    
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
 
   
}
