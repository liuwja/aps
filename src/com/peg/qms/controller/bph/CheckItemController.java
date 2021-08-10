package com.peg.qms.controller.bph;

import java.util.Date;
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
import com.peg.model.bph.CheckItem;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.bph.CheckItemServiceI;
/**考核项目设定控制器
 * @Class: CheckItemController @TODO:
 */
@Controller
@RequestMapping("system/item")
public class CheckItemController extends BaseController {

	@Autowired
	private CheckItemServiceI checkItemService;
	
	@Autowired
	private CommonSelectedBoxService comSelBoxService;
	
	@RequestMapping("/list")
	public String list(Model model,CheckItem checkItem,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("shiftGroupCategory", checkItem.getBaseCategory());
		bs.put("factory", checkItem.getBaseFactory());
		List<CheckItem> list = checkItemService.getAllByPage(bs);
		LoadFAPG(model, checkItem);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", checkItem);
		return "qms/system/item/list";
		
	}
	
	@RequestMapping("/add")
	public String addCheckItem(Model model,CheckItem checkItem)
	{
		LoadFAPG(model, checkItem);
		return "qms/system/item/add";
	}
	
	@RequestMapping("/edit")
	public String editCheckItem( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		CheckItem group = checkItemService.selectByPrimaryKey(id);
		group.setBaseFactory(group.getFactory());
		group.setBaseCategory(group.getShiftGroupCategory());
		LoadFAPG(model, group);
		model.addAttribute("vo", group);
		return "qms/system/item/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(CheckItem checkItem)
	{
		checkItem.setLastUpdateUser(getCurrentUserName());
		checkItem.setLastUpdateTime(new Date());
		checkItem.setFactory(checkItem.getBaseFactory());
		checkItem.setShiftGroupCategory(checkItem.getBaseCategory());
		checkItemService.updateByPrimaryKeySelective(checkItem);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(CheckItem checkItem)
	{
		checkItem.setFactory(checkItem.getBaseFactory());
		checkItem.setShiftGroupCategory(checkItem.getBaseCategory());
		checkItem.setCreateUser(getCurrentUserName());
		checkItemService.insert(checkItem);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(CheckItem checkItem)
	{
		checkItemService.deleteByPrimaryKey(checkItem.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/getCategory")
	public void getCategory(CheckItem checkItem,HttpServletResponse response){
		Map<String,List<CheckItem>> map = new HashMap<String, List<CheckItem>>();
    	List<CheckItem> list = checkItemService.getCategory(checkItem);
    	map.put("cateList", list);
        JSONObject categoryList = JSONObject.fromObject(map);
        printResponContent(response, categoryList.toString());
	}
	
	

}
