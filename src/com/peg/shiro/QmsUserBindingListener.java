package com.peg.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

import com.peg.model.system.SysLoginHistory;
import com.peg.model.system.SysMesUser;
import com.peg.web.util.MapperUtils;

/**
 * TODO
 * <p>
 * @author Lin, 2015-11-23 下午06:40:50
 */
@SuppressWarnings("unchecked")
public class QmsUserBindingListener implements HttpSessionBindingListener
{
	protected static Logger logger = Logger.getLogger(QmsUserBindingListener.class);
	
	SysMesUser user;
	String host;
	
	public QmsUserBindingListener(SysMesUser user, String host)
	{
		this.user = user;
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event)
	{
//		RouteStepMapper routeStepMapper = MapperUtil.getRouteStepMapper();
		HttpSession session = event.getSession();
		
        ServletContext application = session.getServletContext();
        
        // 把用户名放入在线列表
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
        // 第一次使用前，需要初始化
        if (onlineUserList == null) {
            onlineUserList = new ArrayList<String>();
            application.setAttribute("onlineUserList", onlineUserList);
        }
        onlineUserList.add(this.user.getUserName());
        SysLoginHistory record = new SysLoginHistory();
        record.setLoginIp(host);
        record.setSessionid(session.getId());
        record.setUserKey(Long.valueOf(user.getUserKey().toString()));
        record.setUserName(this.user.getUserName());
        record.setUserDescription(this.user.getDescription());
        MapperUtils.getSysLoginHistoryMapper().insert(record);
        logger.info("user: [" + this.user.getUserName() + "] Host: [" + this.host + "]  Login. current online: " + onlineUserList.size());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event)
	{
		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
//        AddScriptSessionListener.sessionMap.remove(session.getId());
        // 从在线列表中删除用户名
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
        onlineUserList.remove(this.user.getUserName());
        MapperUtils.getSysLoginHistoryMapper().updateLogoutTimeBySessionId(session.getId());
        logger.info("user: [" + this.user.getUserName() + "] Host: [" + this.host + "]  Logout.");
	}

}
