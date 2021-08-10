package com.peg.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.peg.service.system.SysPrivilegeServiceI;
import com.peg.web.menu.JdomMenuParser;
/**
 * 需要在系统启动完成后执行的代码
 * @author john
 *
 */
public class InstantiationProcessor implements ApplicationListener<ContextRefreshedEvent> {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SysPrivilegeServiceI sysPrivilegeService;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
    	
    	if(event.getApplicationContext().getParent() != null){
    		JdomMenuParser.initMenu();
    		System.out.println("service: " + sysPrivilegeService);
            //菜单权限更新,当spring容器初始化完成后就会执行该方法。
    		sysPrivilegeService.insertAndUpdateMenus(JdomMenuParser.getMesMenu().getAllMenus());
    		logger.info("init done.");
       }
 }

}
