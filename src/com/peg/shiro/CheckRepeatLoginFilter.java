/*
 * @(#) CheckRepeatLoginFilter.java 2014-6-20 下午03:30:42
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

//import com.datasweep.compatibility.client.User;

/**
 * 检查用户是否已在其他客户端登陆
 * <p>
 * @author Lin, 2014-6-20 下午03:30:42
 */
@SuppressWarnings("unused")
public class CheckRepeatLoginFilter  implements Filter { 
	private static Logger logger = Logger.getLogger(CheckRepeatLoginFilter.class);
 
	public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException { 
        
        	HttpServletRequest req = (HttpServletRequest)request;
//        	User user = (User) req.getSession().getAttribute("user");
        	
//        	if(user != null && !user.getServerImpl().isLoggedIn())
//        	{
//        		HttpSession session = req.getSession(false);
//    	        if(session != null)
//    	        {
//    	        	session.removeAttribute("user");
//    	       	    session.invalidate();
//    	        }	 
//    	        
//    	        Subject currentUser = SecurityUtils.getSubject();
//        		currentUser.logout();
//        		req.getSession().setAttribute("loginByOther", "1");
//        		logger.info(user.getName() +"被强制退出。IP:" + request.getRemoteHost());
//        	}
        	chain.doFilter(request, response);//放行。让其走到下个链或目标资源中 
    } 
 
    public void init(FilterConfig filterConfig) throws ServletException { 
    } 
 
    public void destroy() { 
    	
    } 
}