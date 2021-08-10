package com.peg.qms.controller.bph;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Index;
import com.peg.model.bph.Item;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.GroupCategorySerivceI;
import com.peg.service.bph.IndexServiceI;
import com.peg.service.bph.ItemServiceI;

@Controller
@RequestMapping("base/index")
public class IndexController extends BaseController{

	@Autowired
	private IndexServiceI indexService;
	@Autowired
    private GroupCategorySerivceI  groupCategoryService;	
	@Autowired
	private ItemServiceI itemService;
	
	@RequestMapping("/list")
	public String list(Model model,GroupCategory category,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("category", category.getBaseCategory());
		bs.put("factory", category.getBaseFactory());
		bs.put("area", category.getBaseArea());
		List<GroupCategory> list = groupCategoryService.getIndexAllByPage(bs);
		LoadFAPG(model, category);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", category);
		return "qms/bph/base/index/list";
		
	}
	
	@RequestMapping("/add")
	public String addCheckItem(Model model, @RequestParam(value = "gcKey", required = false) Long gcKey,
			@RequestParam(value="itemKey",required=false) Long itemKey)
	{
		GroupCategory cate = groupCategoryService.selectByPrimaryKey(gcKey);
		Item item = itemService.selectByPrimaryKey(itemKey);
		List<Index> list = indexService.getIndexByItemKey(itemKey);
		model.addAttribute("vo", cate);
		model.addAttribute("item", item);
		model.addAttribute("index", list);
		return "qms/bph/base/index/add";
	}
	
	@RequestMapping("/edit")
	public String editCheckItem( @RequestParam(value = "gcKey", required = false) Long gcKey,
			@RequestParam(value="itemKey",required=false) Long itemKey,
			@RequestParam(value="indexKey",required=false) Long indexKey,
		Model model)
	{
		GroupCategory category = groupCategoryService.selectByPrimaryKey(gcKey);
		Item item = itemService.selectByPrimaryKey(itemKey);
		Index index = indexService.selectByPrimaryKey(indexKey);
		model.addAttribute("category", category);
		model.addAttribute("item", item);
		model.addAttribute("index", index);
		return "qms/bph/base/index/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(Index index)
	{
		try{
			index.setLastUpdateTime(new Date());
			index.setLastUpdateUser(getCurrentUserName());
			if(!index.getIndexCode().matches("\\w{1}\\d+")){
				throw(new Exception("指标代码不符合格式‘A1,即单字母加数字格式’"));
			}
			indexService.updateByPrimaryKeySelective(index);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(Item item)
	{
		try{
			Long itemKey = item.getItemKey();
			List<Index> list = item.getUiindexs();
			for(Index in : list){
				if(!in.getIndexCode().matches("\\w{1}\\d+")){
					throw(new Exception("指标代码不符合格式‘A1,即单字母加数字格式’"));
				}
			}
			for(Index index :list){
				index.setItemKey(itemKey);
				index.setCreateUser(getCurrentUserName());
				indexService.insert(index);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="indexKey",required=false) Long indexKey)
	{
		try{
			indexService.deleteByPrimaryKey(indexKey);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");	
	}
	

	
}
