/*
 * @(#) FotileUIException.java 2014-1-21 下午6:09:43
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.base.exception;

import org.apache.log4j.Logger;


public class QmsException extends Exception
{
	
	private static final long serialVersionUID = 6093991313478270794L;
	protected Logger logger = Logger.getLogger(this.getClass());

	public QmsException(String message)
	{
		super(message);
		logger.error(message);
	}
	
	public QmsException(Throwable e)
	{
		this(e.getMessage());
		logger.error(e.getMessage(),e);
		
		if(!(e instanceof QmsException))
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	public QmsException(Throwable e, String message)
	{
		super(message);
		logger.error(message, e);
	}
	
}
