package com.peg.qms.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.DataSource;
import com.peg.qms.controller.BaseController;
import com.peg.service.system.DataSourceServiceI;
import com.peg.web.menu.Accordion;
import com.peg.web.menu.Folder;
import com.peg.web.menu.Menu;
import com.peg.web.menu.MesMenu;

/**
 * 数据来源说明
 * @author sgh
 * */
@Controller
@RequestMapping("system/dataSource")
public class DataSourceController extends BaseController {

	@Autowired
	private DataSourceServiceI dataSourceService;
	
	@RequestMapping("/list")
	public String list(Model model, DataSource vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		List<DataSource> list = dataSourceService.findPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/system/dataSource/dataSourceList";
	}
	
	@RequestMapping("/addDataSource")
	public String addDataSource(Model model) {
		List<List<String>> menuNameList = getMenuNmae();
		model.addAttribute("accordionNameList", menuNameList.get(0));
		model.addAttribute("folderNameList", menuNameList.get(1));
		model.addAttribute("menuNameList", menuNameList.get(2));
		model.addAttribute("map", getMenuNameMap());
		return "/qms/system/dataSource/addDataSource";
	}
	
	@RequestMapping("/editDataSource")
	public String editDataSource(@RequestParam(value = "id", required = false) Long id, Model model) {
		DataSource vo = dataSourceService.selectByPrimaryKey(id);
		List<List<String>> menuNameList = getMenuNmae();
		model.addAttribute("accordionNameList", menuNameList.get(0));
		model.addAttribute("folderNameList", menuNameList.get(1));
		model.addAttribute("menuNameList", menuNameList.get(2));
		model.addAttribute("map", getMenuNameMap());
		model.addAttribute("vo", vo);
		return "/qms/system/dataSource/editDataSource";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(DataSource vo) {
		try {
			dataSourceService.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			if(e.getMessage().indexOf("ORA-00001") > 0) {
		        return ajaxDoneError("插入失败，此菜单数据来源说明已经存在");
			} else if (e.getMessage().indexOf("ORA-12899") > 0) {
				return ajaxDoneError("插入失败，数据说明文字超过1000字");
			} else {
				return ajaxDoneError("插入失败，请联系管理人员");
			}
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/update")
	public ModelAndView update(DataSource vo) {
		try {
			if (vo.getId() != null) {
				dataSourceService.update(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			if(e.getMessage().indexOf("ORA-00001") > 0) {
		        return ajaxDoneError("插入失败，此菜单数据来源说明已经存在");
			} else if (e.getMessage().indexOf("ORA-12899") > 0) {
				return ajaxDoneError("插入失败，数据说明文字超过1000字");
			} else {
				return ajaxDoneError("插入失败，请联系管理人员");
			}
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(DataSource vo) {
		dataSourceService.delete(vo);
		return ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping("/getDataSourceDescription")
	public void getDataSourceDescription(DataSource vo, HttpServletResponse respon) {
		Map<String,Object> map = new HashMap<String,Object>();
		String description = "";
		try {
			if (dataSourceService.findByName(vo) != null && dataSourceService.findByName(vo).size() > 0) {
				List<DataSource> list = dataSourceService.findByName(vo);
				if (list.size() > 1) {
					description = "此报表数据来源说明尚未维护或有重复现象，请联系相关人员进行相关维护";
				} else {
					description = dataSourceService.findByName(vo).get(0).getDescription();
				}
			} else {
				description = "此报表数据来源说明尚未维护，请联系相关人员进行相关维护";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		map.put("description", description);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 获取菜单栏
	 * @param mesMenu
	 * @return
	 */
	private List<List<String>> getMenuNmae() {
		Subject currentUser = SecurityUtils.getSubject();
		MesMenu mesMenu = (MesMenu) currentUser.getSession().getAttribute("mesMenu");
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<String> accordionNameList = new ArrayList<String>();
		List<String> folderNameList = new ArrayList<String>();
		List<String> menuNameList = new ArrayList<String>();
		Map<String, List<Map<String, List<String>>>> map = new HashMap<String, List<Map<String,List<String>>>>(); //第一级菜单与第二级菜单的Map
		List<Map<String, List<String>>> tempFolderList = null;
		for(Accordion a : mesMenu.getAccordionList()) {
			tempFolderList = new ArrayList<Map<String,List<String>>>(); //第二级菜单的List
			accordionNameList.add(a.getName());
			Map<String, List<String>> tempFolderMap = new HashMap<String, List<String>>(); //第二级菜单与第三级菜单的Map
			for (Folder folder : a.getFolderList()) {
				List<String> tempMenuList = new ArrayList<String>(); //第三级菜单的List
				folderNameList.add(folder.getName());
				for (Menu menu : folder.getMenuList()) {
					menuNameList.add(menu.getName());
					tempMenuList.add(menu.getName());
				}
				tempFolderMap.put(folder.getName(), tempMenuList);
				tempFolderList.add(tempFolderMap);
			}
			map.put(a.getName(), tempFolderList);
		}
		resultList.add(accordionNameList);
		resultList.add(folderNameList);
		resultList.add(menuNameList);
		return resultList;
	}
	
	private Map<String, List<Map<String, List<String>>>> getMenuNameMap() {
		Subject currentUser = SecurityUtils.getSubject();
		MesMenu mesMenu = (MesMenu) currentUser.getSession().getAttribute("mesMenu");
		Map<String, List<Map<String, List<String>>>> map = new HashMap<String, List<Map<String,List<String>>>>(); //第一级菜单与第二级菜单的Map
		List<Map<String, List<String>>> tempFolderList = null;
		for(Accordion a : mesMenu.getAccordionList()) {
			tempFolderList = new ArrayList<Map<String,List<String>>>(); //第二级菜单的List
			Map<String, List<String>> tempFolderMap = new HashMap<String, List<String>>(); //第二级菜单与第三级菜单的Map
			for (Folder folder : a.getFolderList()) {
				List<String> tempMenuList = new ArrayList<String>(); //第三级菜单的List
				for (Menu menu : folder.getMenuList()) {
					tempMenuList.add(menu.getName());
				}
				tempFolderMap.put(folder.getName(), new ArrayList<String>(new HashSet<String>(tempMenuList)));
			}
			tempFolderList.add(tempFolderMap);
			map.put(a.getName(), tempFolderList);
		}
		return map;
	}
}
