package com.peg.shiro;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.peg.model.system.SysMesUser;
import com.peg.model.system.SysPrivilege;
import com.peg.service.system.SysMesUserServiceI;
import com.peg.service.system.SysPrivilegeServiceI;


/**
 * 自定义shiro AuthorizingRealm
 * 
 * @author Lu
 * 
 */
public class MESShiroRealm extends AuthorizingRealm {
	
	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = "not ";
	
	@Autowired
	private SysMesUserServiceI sysMesUserService;
	
	@Autowired
	private SysPrivilegeServiceI sysPrivilegeService;
	
	private static Logger logger = Logger.getLogger(MESShiroRealm.class);

	
	//扩展后可以这么使用
	//<shiro:hasPermission name="api:data or api:user"></shiro:hasPermission>
	//<shiro:hasPermission name="api:data and api:user"></shiro:hasPermission> 
	//<shiro:hasPermission name="not api:data"></shiro:hasPermission>
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if(permission.contains(OR_OPERATOR)) {
	        String[] permissions = permission.split(OR_OPERATOR);
	        for(String orPermission : permissions) {
	            if(isPermittedWithNotOperator(principals, orPermission)) {
	                return true;
	            }
	        }
	        return false;
	    } else if(permission.contains(AND_OPERATOR)) {
	        String[] permissions = permission.split(AND_OPERATOR);
	        for(String orPermission : permissions) {
	            if(!isPermittedWithNotOperator(principals, orPermission)) {
	                return false;
	            }
	        }
	        return true;
	    } else {
	        return isPermittedWithNotOperator(principals, permission);
	    }
	}
	
	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
	    if(permission.startsWith(NOT_OPERATOR)) {
	        return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
	    } else {
	        return super.isPermitted(principals, permission);
	    }
	}	
	//获取用户授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			  PrincipalCollection principals) {
		
		String username = (String) principals.fromRealm(getName()).iterator().next();
		
		if (username != null) {
			// 查询用户授权信息
				if("admin".equals(username))
				{
					SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
					info.addRole("admin");
					info.addStringPermission("*");
					return info;
				}
//				
//				//start
				List<SysPrivilege> privilegeList = sysPrivilegeService.findPrivilegeByUserName(username);
//				
				if(privilegeList != null)
				{
					SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
					logger.debug("privileges size: " + privilegeList.size());
					
					for(SysPrivilege sp : privilegeList)
					{
						info.addStringPermission(sp.getOperationCode());
//						info.addStringPermission(sp.getMenuUrl());
					}
					
//					logger.debug(info.getStringPermissions());
					return info;
				}
		}

		return null;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			                     AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String username = token.getUsername();

		if (username != null && !"".equals(username)) {
			SysMesUser user = sysMesUserService.selectByUsername(username);
//			SysMesUser  user = new SysMesUser();
//			user.setUserName(username);
//			user.setPassword("D1oWqkSUcwY5ICLN1WA3GM6UCLN1WA3GM6UCLN1WA3GM6U");//pwd:123
			
			if (user == null) 
			{ 
				 throw new UnknownAccountException("No account found for user [" + username + "]"); 
			} 
			
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			 		
			return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
		}
		return null;
	}
}
