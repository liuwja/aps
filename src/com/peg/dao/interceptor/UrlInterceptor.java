package com.peg.dao.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.peg.model.system.SysOperateLog;
import com.peg.web.util.MapperUtils;
import com.peg.web.util.UrlIntercepterMap;

public class UrlInterceptor implements HandlerInterceptor{

	private final Logger logger = Logger.getLogger(UrlInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		logger.info(url);
		Map<String,String> urlMap = UrlIntercepterMap.getUrlMap();
		if(urlMap.containsKey(url))
		{
			Subject subject = SecurityUtils.getSubject();
			String userName = subject.getPrincipal().toString();
			//增删改查0,1,2,3
		    short optype = 3;
			SysOperateLog log = new SysOperateLog( optype,urlMap.get(url), userName, new Date());
			MapperUtils.getSysOperateLogMapper().insert(log);
		}
	}
}
