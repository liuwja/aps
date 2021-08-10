package com.peg.shiro;

/**
 * TODO
 * <p>
 * @author Lin, 2014-3-1 上午01:46:15
 */

 import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import com.peg.web.menu.JdomMenuParser;


 /**
  * 基于URL的权限判断过滤器<p>
  * 我们自动根据URL产生所谓的权限字符串，这一项在Shiro示例中是写在配置文件里面的，默认认为权限不可动态配置<p>
  * URL举例：/User/create.do?***=***  -->权限字符串：/User/create.do
  *
  */
 public class URLPermissionsFilter extends PermissionsAuthorizationFilter{
	 @SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(URLPermissionsFilter.class);
	 	/**
	 	 *@param mappedValue 指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].
	 	 *我们要动态产生这个权限字符串，所以这个配置对我们没用
	 	 */
	 	public boolean isAccessAllowed(ServletRequest request,
	 			ServletResponse response, Object mappedValue) throws IOException {
//	 		Subject subject = getSubject(request, response);

//	 		logger.debug("[" + subject.getPrincipal() + "] Access path: " + ((HttpServletRequest)request).getServletPath());
	 		String[] permissions = buildPermissions(request);
	 		
//	 		logger.debug("contain: "+JdomMenuParser.getUrlMap().containsKey(permissions[0]));
	 		//如果url在配置文件中存在，则需进行过滤，否则不过滤
			if(JdomMenuParser.getUrlMap().containsKey(permissions[0]))
			{
				permissions[0] = JdomMenuParser.getUrlMap().get(permissions[0]);
//				logger.debug("ispermissions: " + super.isAccessAllowed(request, response, permissions));
				return super.isAccessAllowed(request, response, permissions);
			}
			else
			{
				return true;
			}
	 	}
	 	
	 	
	 	/**
	 	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm
	 	 * @param request
	 	 * @return
	 	 */
	 	protected String[] buildPermissions(ServletRequest request) {
	 		String[] perms = new String[1];
	 		HttpServletRequest req = (HttpServletRequest) request;
	 		String path = req.getServletPath();
	 		
	 		if(StringUtils.isNotEmpty(path) && path.startsWith("/"))
	 		{
	 			path = path.substring(1) ;
	 		}
//	 		logger.debug("getQueryString: " + req.getQueryString());
//	 		logger.debug("Access path: " + path);
	 		//String methodName = req.getParameter("method");
	 		
	 		perms[0] = path;//path直接作为权限字符串
//	 		logger.debug("Access full path: " + path);
	 		return perms;
	 	}
 }
