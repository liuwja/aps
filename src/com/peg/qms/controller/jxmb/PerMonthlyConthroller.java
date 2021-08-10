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
import com.peg.model.jxmb.PerMonth;
import com.peg.model.jxmb.PerSetup;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.MonthServicel;
import com.peg.service.jxmb.MonthlyServicel;
import com.peg.service.jxmb.PerGoupServicel;
import com.peg.service.jxmb.PerIndexServicel;
import com.peg.service.jxmb.PerItemServicel;
import com.peg.service.jxmb.PerMemberServicel;
import com.peg.service.jxmb.PerSetupServicel;

@Controller
@RequestMapping("per/monthly")
public class PerMonthlyConthroller extends BaseController {
	@Autowired
	private PerIndexServicel perindexService;	
	@Autowired
	private PerGoupServicel groupService;	
	@Autowired
	private PerItemServicel itemService;
	@Autowired
	private MonthServicel monthService;
	
	@Autowired
	private MonthlyServicel monthlyService;
	@Autowired
	private PerMemberServicel permemService;
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private PerSetupServicel persetupService;
	@RequestMapping("/list")
	public String list(Model model,PerMonth month,PerGroup pergroup,
			PerItem peritem,PageParameter page){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("chekyear", pergroup.getYear());
		bs.put("department", pergroup.getDepartment());
		bs.put("accumulatedmonth", month.getAccumulatedmonth());
		bs.put("lastmonthactual", month.getLastmonthactual());
		bs.put("monthreality", month.getMonthreality());
		bs.put("targetvaluemonth", month.getTargetvaluemonth());
		bs.put("accumumonth", month.getAccumumonth());
		bs.put("factoryNumber", pergroup.getFactoryNumber());
		List<PerGroup>list=groupService.getMonthAllByPage(bs);
		for (int i = 0; i < list.size(); i++) {
			PerGroup pg=list.get(i);
			List<PerItem> item=pg.getItem();
			System.out.println("item"+i+":"+item.size()+"num:"+pg.getMonthNum());
			for (int j = 0; j < item.size(); j++) {
				PerItem pi=item.get(j);
				List<PerSetup> uiindexs=pi.getUiindexs();
				System.out.println("uiindexs"+j+":"+uiindexs.size()+"num:"+pi.getMonthNum());		
				for (int k = 0; k < uiindexs.size(); k++) {
					PerSetup ps=uiindexs.get(k);
					System.out.println("ppp"+ps.getMonthNum());
					 List<PerMonth> uigroupCategory=ps.getUigroupCategory();
					System.out.println("uigroupCategory"+k+":"+uigroupCategory.size()+"num:"+ps.getMonthNum());	
				}
			}
			
		}
		bs=new BaseSearch();
		bs.setPage(page); 
		List<PerGroup>list2=groupService.getMonthAllByPage(bs);
		System.out.println("list.size:"+list.size());
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);

		model.addAttribute("page",page);
		model.addAttribute("vo",pergroup);		
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/jxmonth/list";
	}
	@RequestMapping("/add")
	public String add(Model model ,	@RequestParam(value="id",required=false)String id,
			Long itemKey,Long indexKey,Long monthKey){
		BaseSearch bs=new BaseSearch();		
		bs.put("itemKey",  itemKey);
		bs.put("indexKey", indexKey);

		PerGroup group=groupService.selectByPrimaryKey(Long.parseLong(id.split(";")[0]));
		PerItem item=itemService.selectByPrimaryKey(itemKey);
		PerSetup setup=persetupService.selectByPrimaryKey(indexKey);
		List<PerMonth>list=monthService.selectByGroupAndMonth(monthKey);
		model.addAttribute("vo",group);		
		model.addAttribute("item",item);
		model.addAttribute("list",list);
		model.addAttribute("setup",setup);
		return "qms/bph/base/jxmonth/add";
	}
	@RequestMapping("/save")
	public  ModelAndView save(PerMonth permonth ,PerSetup perset){
		try {
			Long indexKey =perset.getIndexKey();
			List<PerMonth> list=permonth.getMonList();
			for(PerMonth index :list){
				index.setIndexKey(indexKey);			
				monthService.insert(index);
			}
		} catch (Exception e) {
		logger.error(e.getMessage(),e);
		return ajaxDoneError(e.getMessage());
		}
		//monthService.insert(permonth);
		return ajaxDoneSuccess("保存成功");
	}
	@RequestMapping("/edit")
	public String editCheckItem(
			@RequestParam(value="monthKey",required=false)Long monthKey,Model model,
			Long id,Long itemKey,Long indexKey){
			PerMonth month=monthService.selectByPrimaryKey(monthKey);
			PerGroup group =groupService.selectByPrimaryKey(id);
			PerItem item=itemService.selectByPrimaryKey(itemKey);
			PerSetup index=persetupService.selectByPrimaryKey(indexKey);
			model.addAttribute("index",index);
			model.addAttribute("item",item);
			model.addAttribute("group",group);
			model.addAttribute("month",month);
		return "qms/bph/base/jxmonth/edit";
	}
	@RequestMapping("/update")
	public ModelAndView update(PerMonth month){
		try {
			month.setLastupdatetime(new Date());
			monthService.updateByPrimaryKeySelective(month);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="monthKey",required=false)Long monthKey){
		
		try {
			monthService.deleteByPrimaryKey(monthKey);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");
	}
}
