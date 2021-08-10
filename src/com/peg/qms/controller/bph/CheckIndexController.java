package com.peg.qms.controller.bph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonSelectedBox;
import com.peg.model.bph.CheckIndex;
import com.peg.model.bph.CheckItem;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.CheckIndexServiceI;
import com.peg.service.bph.CheckItemServiceI;
/**考核指标控制器
 * @Class: CheckIndexController @TODO:
 */
@Controller
@RequestMapping("system/checkIndex")
public class CheckIndexController extends BaseController{
	@Autowired
	private CheckIndexServiceI checkIndexService;
	
	@Autowired ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private CommonSelectedBoxService comSelBoxService;
	
	@Autowired
	private CheckItemServiceI checkItemService;
	
	@RequestMapping("/list")
	public String list(Model model,CheckIndex checkIndex,PageParameter page)
	{
		String factory = checkIndex.getBaseFactory();
		String area = checkIndex.getBaseArea();
		List<CommonSelectedBox> cheInList = null;
		if(factory != null && !"".equals(factory)){
			if(area != null && !"".equals(area)){
				cheInList = comSelBoxService.getCommonCheckIndexByFac(factory, area);
			}
		}
		
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("shiftGroupCategory", checkIndex.getBaseCategory());
		bs.put("factory", checkIndex.getBaseFactory());
		bs.put("area", checkIndex.getBaseArea());
		bs.put("checkIndex", checkIndex.getCheckIndex());
		bs.put("indexCode", checkIndex.getIndexCode());
	    LoadFAPG(model, checkIndex);
		List<CheckIndex> list = checkIndexService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("cheInList", cheInList);
		model.addAttribute("vo", checkIndex);
		model.addAttribute("page", page);
		return "qms/system/checkIndex/list";
		
	}
	
	@RequestMapping("/add")
	public String add(Model model,CheckIndex checkIndex)
	{
		LoadFAPG(model, checkIndex);
		return "qms/system/checkIndex/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		CheckIndex group = checkIndexService.selectByPrimaryKey(id);
		List<CheckItem> list = checkItemService.getCheckItem(group.getFactory(),group.getShiftGroupCategory());
		group.setBaseFactory(group.getFactory());
		group.setBaseArea(group.getArea());
		group.setBaseCategory(group.getShiftGroupCategory());
		group.setBaseCheckItem(group.getCheckItem());
		LoadFAPG(model, group);
		model.addAttribute("vo", group);
		model.addAttribute("itemList", list);
		return "qms/system/checkIndex/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(CheckIndex checkIndex)
	{
		
		checkIndexService.updateByPrimaryKeySelective(checkIndex);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(CheckIndex checkIndex)
	{
		checkIndex.setCreateUser(getCurrentUserName());
		checkIndex.setFactory(checkIndex.getBaseFactory());
		checkIndex.setArea(checkIndex.getBaseArea());
		checkIndex.setShiftGroupCategory(checkIndex.getBaseCategory());
		checkIndex.setCheckItem(checkIndex.getBaseCheckItem());
		checkIndexService.insert(checkIndex);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(CheckIndex checkIndex)
	{
		checkIndexService.deleteByPrimaryKey(checkIndex.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
	
	

	@RequestMapping("/getCategory")
	public void getCategory(CheckIndex index,HttpServletResponse response){
		Map<String,List<CheckIndex>> map = new HashMap<String, List<CheckIndex>>();
		List<CheckIndex> list = checkIndexService.getCategory(index);
		map.put("cateList", list);
		JSONObject caList = JSONObject.fromObject(map);
		printResponContent(response, caList.toString());
	}
	
	@RequestMapping("/getCheckItem")
	public void getCheckItem(CheckIndex index,HttpServletResponse response){
		Map<String,List<CheckItem>> map = new HashMap<String, List<CheckItem>>();
		List<CheckItem> list = checkItemService.getCheckItem(index.getFactory(),index.getBaseCategory());
		map.put("cateList", list);
		JSONObject caList = JSONObject.fromObject(map);
		printResponContent(response, caList.toString());
	}
	
}
