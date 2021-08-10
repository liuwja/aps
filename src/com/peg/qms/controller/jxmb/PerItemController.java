package com.peg.qms.controller.jxmb;

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
import com.peg.model.jxmb.PerGroup;
import com.peg.model.jxmb.PerItem;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.PerGoupServicel;
import com.peg.service.jxmb.PerItemServicel;

@Controller
@RequestMapping("per/item")
public class PerItemController extends BaseController {

	@Autowired
	private PerItemServicel perItemService;

	@Autowired
	private PerGoupServicel perGoupService;

	@Autowired
	private CommonServiceI commonService;
	
	@RequestMapping("/list")
	public String list(Model model, PerGroup perGroup, PageParameter page,PerItem peritem) {	
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("chekyear", perGroup.getYear());
		bs.put("department", perGroup.getDepartment());
		bs.put("indexcontent", perGroup.getIndexcontent());
		bs.put("factoryNumber", perGroup.getFactoryNumber());

		List<PerGroup> list = perGoupService.getItemAllByPage(bs);		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", perGroup);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performanceYear/list";
	}

	@RequestMapping("/add")
	public String addCheckItem(Model model, @RequestParam(value = "id", required = false) Long id) {
		PerGroup group = perGoupService.selectByPrimaryKey(id);
		List<PerItem> list = perItemService.getItemByGcKey(id);
		model.addAttribute("vo", group);
		model.addAttribute("item", list);
		return "qms/bph/base/performanceYear/add";
	}

	@RequestMapping("/edit")
	public String editCheckItem(@RequestParam(value="id",required=false)Long id,
			@RequestParam(value="itemKey",required=false)Long itemKey,Model model) {
		PerGroup group=perGoupService.selectByPrimaryKey(id);
		PerItem item= perItemService.selectByPrimaryKey(itemKey);
		model.addAttribute("group",group);
		model.addAttribute("item",item);
		return "qms/bph/base/performanceYear/edit";
	}
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(PerItem item){
		try {
			item.setLastupdateuser(getCurrentUserName());
			item.setLastupdatetime(new Date());
			perItemService.updateByPrimaryKeySelective(item);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(PerGroup group,PerItem peritem){
		Date date=new Date();
		try {
			Long id=group.getId();
			List<PerItem>list=group.getItem();
			for(PerItem item :list){
				item.setId(id);
				item.setRecord(getCurrentUserName());
				item.setRecordtime(date);
				perItemService.insert(item);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="itemKey",required=false)Long itemKey){
		try {
			perItemService.deleteByPrimaryKey(itemKey);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");
	}
}
