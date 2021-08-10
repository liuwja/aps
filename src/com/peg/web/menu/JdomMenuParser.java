/*
 * @(#) JdomMenuParser.java 2014-3-2 下午04:02:51
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.menu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 解析menu.xml
 * <p>
 * @author Lin, 2014-3-2 下午04:02:51
 */
@SuppressWarnings("unchecked")
public class JdomMenuParser
{
private static Logger logger = Logger.getLogger(JdomMenuParser.class);
	
	private static MesMenu mesMenu;
	
	private static String MENU_XML_PATH = "menu.xml";
	private static final int DISPLAY = 0;
	private static Map<String, String> urlMap = new HashMap<String, String>();
	public static void initMenu()
	{
		logger.info("start parse menu xml!!");
		mesMenu = readMenuContent();
		mesMenu.checkRepeatPermissionCode();
		logger.info("parse menu xml finish!!");
	}
	
    public static void main(String[] argv) {
//    	mesMenu = readMenuContent();
    	System.out.println(mesMenu.getAllMenus().size());
    	prtAllMenu(mesMenu.getAllMenus());
//    	logger.debug(mesMenu.getAccordionHtml());
    }
    
    private static void prtAllMenu(List<Menu> menus)
    {
    	for(Menu m : menus)
    	{
    		System.out.println("===========================================");
    		System.out.println(m.getName() + " ||  " + m.getPermissionCode());
    		for(Operation o : m.getOptList())
    		{
    			System.out.println(o.getName() + " ||  " + o.getPermissionCode());
    		}
    	}
    }
    
	private static Menu getMenu(Element e)
    {
    	Menu menu = new Menu();
    	menu.setName(e.getAttributeValue("name"));
    	menu.setUrl(e.getAttributeValue("url"));
    	menu.setTabId(e.getAttributeValue("tabId"));
    	menu.setPermissionCode(e.getAttributeValue("permissionCode"));
    	menu.setCommonOptions(e.getAttributeValue("commonOptions"));
    	menu.setTargetType(NumberUtils.toInt(e.getAttributeValue("targetType"), 0));
    	menu.setDisplay(NumberUtils.toInt(e.getAttributeValue("display"), 0));
    	
    	List<Element> optEleList = e.getChildren("operation");
    	if(null != optEleList && !optEleList.isEmpty())
    	{
    		List<Operation> optList = getOptionList(menu, optEleList);
    		
    		menu.getOptList().addAll(optList);
    	}
    	urlMap.put(menu.getUrl(), menu.getPermissionCode() + ":" + OperatorEnum.VIEW);
    	return menu;
    }
    
    private static Operation getOption(Menu menu, Element e)
    {
    	Operation opt = new Operation();
    	
    	opt.setName(e.getAttributeValue("name"));
    	opt.setUrl(e.getAttributeValue("url"));
    	opt.setPermissionCode(menu.getPermissionCode() + ":" + e.getAttributeValue("code"));
    	
    	List<Element> urlEleList = e.getChildren("url");
    	if(null != urlEleList && !urlEleList.isEmpty())
    	{
    		List<Url> list = getOptionUrlList(urlEleList);
    		
    		opt.setContainUrls(list);
    	}
    	
    	urlMap.put(opt.getUrl(), opt.getPermissionCode());
    	return opt;
    }
    
    private static Url getUrl(Element e)
    {
    	Url url = new Url();
    	
    	url.setName(e.getAttributeValue("name"));
    	url.setUrl(e.getAttributeValue("url"));
    	url.setCode(e.getAttributeValue("code"));
    	
    	urlMap.put(url.getUrl(), url.getUrl());
    	return url;
    }
    
    private static List<Url> getOptionUrlList(List<Element> list)
    {
    	List<Url> urlList = new ArrayList<Url>();
		for (Element e : list) 
		{
			urlList.add(getUrl(e));
		}
    	return urlList;
    }
    
    private static List<Operation> getOptionList(Menu menu, List<Element> list)
    {
    	List<Operation> optList = new ArrayList<Operation>();
		for (Element e : list) 
		{
			optList.add(getOption(menu, e));
		}
    	return optList;
    }
    private static List<Menu> getMenuList(List<Element> list)
    {
    	List<Menu> menuList = new ArrayList<Menu>();
    	int display = 0;
        for (Element e : list) {
        	display = NumberUtils.toInt(e.getAttributeValue("display"), 0);
        	if(display > DISPLAY)
        	{
        		continue;
        	}
        	menuList.add(getMenu(e));
        }
        return menuList;
    }
    
    private static List<Folder> getFolderList(List<Element> list)
    {
    	List<Folder> folderList  = new ArrayList<Folder>();
    	Folder folder = null;
    	int display = 0;
        for (Element e : list) {
        	display = NumberUtils.toInt(e.getAttributeValue("display"), 0);
        	if(display > DISPLAY)
        	{
        		continue;
        	}
        	folder = new Folder();
        	folder.setName(e.getAttributeValue("name"));
        	folder.setDisplay(display);
        	folder.setSystemType(NumberUtils.toInt(e.getAttributeValue("systemType"), 6));
        	if(e.getChildren("menu").size() > 0)
        	{
        		List<Menu> menuList = getMenuList(e.getChildren("menu"));
        		folder.getMenuList().addAll(menuList);
        	}
        	if(e.getChildren("folder").size() > 0)
        	{
        		folder.getFolderList().addAll(getFolderList(e.getChildren("folder")));
        	}
        	folderList.add(folder);
        }
        return folderList;
    }
    
	private static MesMenu readMenuContent() {
	    SAXBuilder builder = new SAXBuilder();
	    MesMenu allMenu = MesMenu.getInstance();
	    try {
	    	String xmlFile = JdomMenuParser.class.getResource("/").getFile() + MENU_XML_PATH;  
	    	logger.info(xmlFile);
	    	InputStream is = JdomMenuParser.class.getResourceAsStream("/"+MENU_XML_PATH);
	    	Document doc = builder.build(is);
//	        Document doc = builder.build(new File("D:\\work\\workspace\\FotileMES\\src\\resources\\menu.xml"));
	        
	        Element root = doc.getRootElement();
	        List<Element> navList = root.getChildren("navMenu"); 
	        Accordion navMenu = new Accordion();
	        for (Element e : navList) {
	        	navMenu.setName(e.getAttributeValue("name"));
	        	List<Menu> menuList = getMenuList(e.getChildren());
	        	navMenu.getMenuList().addAll(menuList);
	        	break;
	        }
//	        logger.debug(navMenu);
	        allMenu.setNavMenu(navMenu);
	        
	        List<Element> accordionElementList = root.getChildren("accordion");
	        Accordion accd = null;
	        List<Accordion> accordionList = new ArrayList<Accordion>();
	        int display  = 0;
	        for (Element e : accordionElementList) {
	        	display = NumberUtils.toInt(e.getAttributeValue("display"), 0);
	        	if(display > DISPLAY)
	        	{
	        		continue;
	        	}
	        	accd = new Accordion();
	        	accd.setIconName(e.getAttributeValue("iconName"));
	        	accd.setPermissionCode(e.getAttributeValue("permissionCode"));
	        	accd.setName(e.getAttributeValue("name"));
	        	accd.setDisplay(display);
	        	accd.setSystemType(NumberUtils.toInt(e.getAttributeValue("systemType"), 6));
	        	if(e.getChildren("menu").size() > 0)
	        	{
	        		List<Menu> menuList = getMenuList(e.getChildren("menu"));
	        		accd.setMenuList(menuList);
	        	}
	        	if(e.getChildren("folder").size() > 0)
	        	{
	        		List<Folder> folderList = getFolderList(e.getChildren("folder"));
	        		accd.setFolderList(folderList);
	        	}
	        	accordionList.add(accd);
	        }
	        
	        allMenu.setAccordionList(accordionList);
	        
	    } catch (JDOMException e) {
	        logger.error("jdom解析错误", e);
	    } catch (IOException e) {
	    	logger.error("io异常", e);
	    }
	    
	    return allMenu;
	}

	public static MesMenu getMesMenu()
	{
		return mesMenu;
	}

	public static Map<String, String> getUrlMap()
	{
		return urlMap;
	}

	public static void setUrlMap(Map<String, String> urlMap)
	{
		JdomMenuParser.urlMap = urlMap;
	}
}
