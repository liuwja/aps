/*
 * @(#) TestFilter.java 2014-3-26 下午01:43:30
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-26 下午01:43:30
 */
public class SessionFilter extends FormAuthenticationFilter
{
	private static Logger logger = Logger.getLogger(SessionFilter.class);
	public final static String X_R = "X-Requested-With";
	public final static String X_R_VALUE = "XMLHttpRequest";
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response)
		throws IOException
	{
//		logger.info("redirectToLogin");
    	HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String xrv = httpServletRequest.getHeader(X_R);
		
		String path = httpServletRequest.getServletPath();
		path = path + "?"+httpServletRequest.getQueryString();
//		logger.debug("request path: " + path);
		logger.debug("redirectToLogin time: " +  DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		
		if (xrv != null && xrv.equalsIgnoreCase(X_R_VALUE)) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setContentType("text/json;charset=UTF-8"); 
			httpResponse.setHeader("Cache-Control", "no-cache"); 
 			PrintWriter out = response.getWriter();
 			String isloginByOther = (String)httpServletRequest.getSession().getAttribute("loginByOther");
 			if(isloginByOther != null)
 			{
 				out.println("{\"statusCode\":\"301\", \"message\":\"此用户已在其他客户端登录，请重新登录!\"}");
 			}
 			else
 			{
 				out.println("{\"statusCode\":\"301\", \"message\":\"会话已过期，请重新登录!\"}");			
 			}
		} else {
			String loginUrl = null;
			if(path.startsWith("/wm"))
			{
				loginUrl = "/wm/login.sp?method=toLogin";
			}
			else if(path.startsWith("/pad"))
			{
				loginUrl = "/login.sp?method=padLogin";
			}
			else
			{
				loginUrl = "/";
			}
			super.setLoginUrl(loginUrl);
			super.redirectToLogin(request, response);
		}
	}
}
