package com.peg.qms.controller.bph;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
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
import com.peg.model.bph.Group;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Item;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.GroupCategorySerivceI;
import com.peg.service.bph.GroupServiceI;
import com.peg.service.bph.ItemServiceI;

@Controller
@RequestMapping("base/item")
public class ItemController extends BaseController{

	@Autowired
	private ItemServiceI itemService;
	@Autowired
    private GroupCategorySerivceI  groupCategoryService;	
	@Autowired
	private GroupServiceI groupService;
	
	@RequestMapping("/list")
	public String list(Model model,GroupCategory category,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("category", category.getBaseCategory());
		bs.put("factory", category.getBaseFactory());
		bs.put("area", category.getBaseArea());
		List<GroupCategory> list = groupCategoryService.getItemAllByPage(bs);
		LoadFAPG(model, category);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", category);
		return "qms/bph/base/item/list";
		
	}
	
	@RequestMapping("/add")
	public String addCheckItem(Model model, @RequestParam(value = "gcKey", required = false) Long gcKey)
	{
		GroupCategory cate = groupCategoryService.selectByPrimaryKey(gcKey);
		List<Item> list = itemService.getItemByGckey(gcKey);
		model.addAttribute("vo", cate);
		model.addAttribute("item", list);
		return "qms/bph/base/item/add";
	}
	
	@RequestMapping("/edit")
	public String editCheckItem( @RequestParam(value = "gcKey", required = false) Long gcKey,
			@RequestParam(value="itemKey",required=false) Long itemKey,
		Model model)
	{
		GroupCategory category = groupCategoryService.selectByPrimaryKey(gcKey);
		Item item = itemService.selectByPrimaryKey(itemKey);
		model.addAttribute("category", category);
		model.addAttribute("item", item);
		return "qms/bph/base/item/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(Item item)
	{
		try{
			item.setLastUpdateTime(new Date());
			item.setLastUpdateUser(getCurrentUserName());
			itemService.updateByPrimaryKeySelective(item);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(GroupCategory category)
	{
		try{
			Long gcKey = category.getGcKey();
			List<Item> list = category.getItem();
			for(Item item :list){
				item.setGcKey(gcKey);
				item.setCreateUser(getCurrentUserName());
				itemService.insert(item);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="itemKey",required=false) Long itemKey)
	{
		try{
			itemService.deleteByPrimaryKey(itemKey);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");	
	}
	
	
	@RequestMapping("/getItem")
	public void getItem(GroupCategory category,HttpServletResponse rs)
	{

		Map<String,List<Item>> map  = new HashMap<String, List<Item>>();
		List<Item> list = itemService.getAllItems(category.getFactory(), category.getArea(), category.getCategory());
		map.put("item", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	/**
	 * 初始化班组类别
	 * @param rs
	 */
	@RequestMapping("/initGroupCategory")
	public void initGroupCategory(HttpServletResponse rs){
		String msg = null;
		int result = 0;
		try{
			List<GroupCategory> localList = groupCategoryService.getAllGroupCategory();
			List<GroupCategory> mesList = groupCategoryService.getAllGroupCategoryFromMes();
			List<GroupCategory> list = compare(localList,new ArrayList<GroupCategory>(mesList) );
			List<GroupCategory> delList = compare(mesList,new ArrayList<GroupCategory>(localList));
			for(GroupCategory cate : list){
				groupCategoryService.insert(cate);
			}
			for(GroupCategory cate : delList){
			   groupCategoryService.delete(cate.getGcKey());
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			msg = e.getMessage();
			result = -1;
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
	}
	
	
     /**
      * 初始化班组
      * @param rs
      */
	@RequestMapping("/initGroup")
	public void initGroup(HttpServletResponse rs){
		String msg = null;
		int result = 0;
		try{
			List<GroupCategory> cateList = groupCategoryService.getAllGroupCategory();
			List<Group> localList = groupService.getAllGroup();
			List<Group> mesList = groupService.getAllGroupFromMes();
			List<Group> list = compareGroup(localList,new ArrayList<Group>(mesList),cateList);
			List<Group> delList = compareGroup(new ArrayList<Group>(mesList),new ArrayList<Group>(localList),cateList);
			compareGroupUpdate(localList,mesList,cateList);
			for(Group g : list){
				groupService.insert(g);
			}
			for(Group g : delList){
				groupService.deleteByPrimaryKey(g.getGroupKey());
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			msg = e.getMessage();
			result = -1;
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
	}
	
	private List<GroupCategory> compare(List<GroupCategory> list1,List<GroupCategory> list2){
		ListIterator<GroupCategory> iter = list2.listIterator();
		while(iter.hasNext()){
			GroupCategory vo = iter.next();
			for(GroupCategory cate : list1){
				if(cate.getFactory().equals(vo.getFactory())&&
						cate.getArea().equals(vo.getArea())&&
						cate.getCategory().equals(vo.getCategory())){
					iter.remove();
				}
			}
		}
	  return list2 ;
	}
	
	private List<Group> compareGroup(List<Group> list1,List<Group> list2,List<GroupCategory> cateList){
		ListIterator<Group> iter = list2.listIterator();
		while(iter.hasNext()){
			Group group = iter.next();
			for(GroupCategory cate : cateList){
				if(group.getFactory().equals(cate.getFactory())&&
						group.getArea().equals(cate.getArea())&&
						group.getGroupCategory().equals(cate.getCategory())){
					group.setCategoryKey(cate.getGcKey());
				}
			}
			for(Group g : list1){
				if(g.getOldKey().equals(group.getOldKey())){
					iter.remove();
				}
			}
		}
		return list2;
	}
	private void compareGroupUpdate(List<Group> list1,List<Group> list2,List<GroupCategory> cateList){
		ListIterator<Group> iter = list2.listIterator();
		while(iter.hasNext()){
			Group group = iter.next();
			for(GroupCategory cate : cateList){
				if(group.getFactory().equals(cate.getFactory())&&
						group.getArea().equals(cate.getArea())&&
						group.getGroupCategory().equals(cate.getCategory())){
					group.setCategoryKey(cate.getGcKey());
				}
			}
			for(Group g : list1){
				if(g.getOldKey().equals(group.getOldKey())){
					group.setGroupKey(g.getGroupKey());
					groupService.updateByPrimaryKey(group);
				}
			}
		}
	}
	
}
