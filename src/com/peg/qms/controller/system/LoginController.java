/*
 * @(#) LoginController.java 2014-8-8 下午04:21:37
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.qms.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.base.exception.QmsException;
import com.peg.model.CommonSelectedBox;
import com.peg.model.system.SysMesUser;
import com.peg.service.CommonSelectedBoxService;
import com.peg.shiro.QmsUserBindingListener;
import com.peg.web.menu.JdomMenuParser;
import com.peg.web.menu.MenuFilter;
import com.peg.web.menu.MesMenu;
import com.peg.web.util.EncryptionUtils;
import com.peg.web.util.WebUtil;


/**
 * TODO
 * <p>
 * @author Lin, 2014-8-8 下午04:21:37
 */

@Controller
public class LoginController
{
	@Autowired
	private CommonSelectedBoxService commonSelectedBoxService;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	@RequestMapping("/verifyLogin")
	public String verifyLogin(HttpServletRequest request,SysMesUser user, Model model)
	{	
		Subject currentUser = SecurityUtils.getSubject( );
		
		if(currentUser.isAuthenticated())
		{
			// 用户已经登录过
			MesMenu mesMenu = JdomMenuParser.getMesMenu(); 
			
			MenuFilter menuFilter = new MenuFilter();
			MesMenu newMenu = menuFilter.filterByPrivilege(mesMenu);
			model.addAttribute("mesMenu", newMenu);
			model.addAttribute("currentName", user.getUserName());
			
	        model.addAttribute("currentDate", DateFormatUtils.format(new Date(),"yyyy年M月d日  E"));
			return "/qms/index";			
		}
		
		// 用户输入不完整
		if(StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword()))
		{
			model.addAttribute("msg", "请输入用户和密码");
			return "/login";
		}
		String pwd = EncryptionUtils.getEncryptedPassword(user.getPassword().trim());	
		user.setPassword(pwd);
		// 下面开始处理用户登录过程
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		token.setRememberMe(false);
		
		try {
			currentUser.login(token); // 登录过程，成功就往下执行；否则就抛出异常。
			
			// return "redirect:cityController/showCity.do";
			SysMesUser u = (SysMesUser)currentUser.getSession().getAttribute("user");
			currentUser.getSession().setAttribute("qmsUserBindingListener", new QmsUserBindingListener(u, request.getRemoteHost()));
			int accessPrivilege = NumberUtils.toInt(u.getUda3());
			if(!WebUtil.canAccessQms(accessPrivilege)
				&& !WebUtil.canAccessAchievement(accessPrivilege))
			{
				currentUser.logout();
				throw new QmsException("您没有权限访问本系统");
			}
			if("1".equals(u.getCategory())){
				currentUser.logout();
				throw new QmsException("该用户已经被注销");
			}
			MesMenu mesMenu = JdomMenuParser.getMesMenu();
			
			MenuFilter menuFilter = new MenuFilter();
			MesMenu newMenu = menuFilter.filterByPrivilege(mesMenu);
			loadFAPG(request);
			model.addAttribute("mesMenu", newMenu);
			model.addAttribute("currentName", user.getUserName());
			
	        model.addAttribute("currentDate", DateFormatUtils.format(new Date(),"yyyy年M月d日  E"));
	        
	        currentUser.getSession().setAttribute("mesMenu", newMenu);
//	        loadFAPG(request);
			return "/qms/index";
		}
		catch (QmsException e)
		{
			logger.error(e.getMessage(),e);
			model.addAttribute("msg", e.getMessage());
			return "/login";
		}		
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			model.addAttribute("msg", "用户密码错误");
			return "/login";
		}
		
	}
	
	@RequestMapping("/login")
	public String login(Model model){	
		return "/login";
	}
	
	
	@RequestMapping("/loginDialog")
	public String toLoginDialog(Model model){	
		return "/loginDialog";
	}
	
	
	@RequestMapping("/logout")
	public String logout(Model model){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		logger.info("logout........... done");
		return "/login";
	}

	@RequestMapping("/underBuilding")
	public String underBuilding(Model model){
		return "/underConstruction";
	}
	
	protected void loadFAPG(HttpServletRequest request){
		
		//工厂
		List<CommonSelectedBox> flist = commonSelectedBoxService.getCommonFactory();  
		request.getSession().setAttribute("lfactory", flist);
		
		//车间
		Map<String,List<CommonSelectedBox>> amap = new HashMap<String, List<CommonSelectedBox>>();
		List<CommonSelectedBox> alist = commonSelectedBoxService.getAreaS();    
		Iterator<CommonSelectedBox> iter = alist.iterator();
		while(iter.hasNext()){
			CommonSelectedBox box = iter.next();
			String fac = box.getFactory();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!amap.containsKey(fac)){
				amap.put(fac, comList);
			}
			amap.get(fac).add(box);
		}
//		List<CommonSelectedBox> adlist = amap.get("电器工厂");
//		for(CommonSelectedBox box : adlist){
//			System.out.println(box.getArea());
//		}
		request.getSession().setAttribute("larea", amap);	
		
		//班组类别(工厂+车间)
		Map<String,List<CommonSelectedBox>> catMap= new HashMap<String, List<CommonSelectedBox>>();
		Map<String,List<CommonSelectedBox>> fcatMap= new HashMap<String, List<CommonSelectedBox>>();
		List<CommonSelectedBox> categorys = commonSelectedBoxService.getCategoryS();
		Iterator<CommonSelectedBox> cate = categorys.iterator();
		Iterator<CommonSelectedBox> fcate = categorys.iterator();
		while(cate.hasNext()){
			CommonSelectedBox box = cate.next();
			String facArea = box.getFactory()+"-"+box.getArea();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!catMap.containsKey(facArea)){
				catMap.put(facArea, comList);
			}
			catMap.get(facArea).add(box);
		}
		request.getSession().setAttribute("lcatMap",catMap);
		
		//班组类别（工厂）
		while(fcate.hasNext()){
			CommonSelectedBox box = fcate.next();
			String fac = box.getFactory();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!fcatMap.containsKey(fac)){
				fcatMap.put(fac, comList);
			}
			fcatMap.get(fac).add(box);
		}
		request.getSession().setAttribute("lfcatMap",fcatMap);
		
		//班组
		//根据工厂
		Map<String,List<CommonSelectedBox>> fgroupMap= new HashMap<String, List<CommonSelectedBox>>();
		//根据工厂，车间
		Map<String,List<CommonSelectedBox>> fagroupMap= new HashMap<String, List<CommonSelectedBox>>();
		
		List<CommonSelectedBox> glist = commonSelectedBoxService.getGroupS();
		Iterator<CommonSelectedBox> groups = glist.iterator();
		Iterator<CommonSelectedBox> fagroups = glist.iterator();
		while(groups.hasNext()){
			CommonSelectedBox box = groups.next();
			String fac = box.getFactory();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!fgroupMap.containsKey(fac)){
				fgroupMap.put(fac, comList);
			}
			fgroupMap.get(fac).add(box);
		}
		
		while(fagroups.hasNext()){
			CommonSelectedBox box = fagroups.next();
			String fac = box.getFactory()+"-"+box.getArea();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!fagroupMap.containsKey(fac)){
				fagroupMap.put(fac, comList);
			}
			fagroupMap.get(fac).add(box);
		}
		
		request.getSession().setAttribute("lfgroupMap",fgroupMap);
		request.getSession().setAttribute("lfagroupMap",fagroupMap);
		
		//考核项目
		Map<String,List<CommonSelectedBox>> checkItemMap= new HashMap<String, List<CommonSelectedBox>>();
		List<CommonSelectedBox> itemList = commonSelectedBoxService.getCheckItemS();
		Iterator<CommonSelectedBox> item = itemList.iterator();
		while(item.hasNext()){
			CommonSelectedBox box = item.next();
			String fac = box.getFactory()+"-"+box.getArea()+"-"+box.getShiftGroupCategory();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!checkItemMap.containsKey(fac)){
				checkItemMap.put(fac, comList);
			}
			checkItemMap.get(fac).add(box);
		}
		
		request.getSession().setAttribute("lcheckItemMap",checkItemMap);
	}
	
}
