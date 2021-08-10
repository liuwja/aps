package com.peg.qms.controller.jxmb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.peg.service.jxmb.MonthServicel;
import com.peg.service.jxmb.PerGoupServicel;
import com.peg.service.jxmb.PerIndexServicel;
import com.peg.service.jxmb.PerItemServicel;
import com.peg.service.jxmb.PerMemberServicel;
import com.peg.service.jxmb.PerSetupServicel;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("per/month")
public class MonthResultController extends BaseController {

	@Autowired
	private PerIndexServicel perindexService;	
	@Autowired
	private PerGoupServicel groupService;	
	@Autowired
	private PerItemServicel itemService;
	@Autowired
	private MonthServicel monthService;
	
	@Autowired
	private PerMemberServicel permemService;
	
	@Autowired
	private PerSetupServicel persetupService;
	
	@RequestMapping("/list")
	public String list(Model model,PerMonth permonth,PerGroup pergroup,PerItem peritem,PageParameter page){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("department", pergroup.getDepartment());
		bs.put("accumulatedmonth", permonth.getAccumulatedmonth());
		bs.put("lastmonthactual", permonth.getLastmonthactual());
		bs.put("monthreality", permonth.getMonthreality());
		bs.put("targetvaluemonth", permonth.getTargetvaluemonth());
		bs.put("accumumonth", permonth.getAccumumonth());
			
		List<PerMonth>list=monthService.getMonthAllByPage(bs);
		bs=new BaseSearch();
		bs.setPage(page); 
		List<PerMonth>list2=monthService.getMonthAllByPage(bs);
		System.out.println("list.size:"+list.size());
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("pergroup",pergroup);
		model.addAttribute("page",page);
		model.addAttribute("vo",permonth);		
		return "qms/bph/base/month/list";
	}
	@RequestMapping("/add")
	public String add(Model model,@RequestParam(value="id",required=false)String id,
			Long itemKey,Long indexKey ,Long monthKey){
		
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
		
		return "qms/bph/base/month/add";
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
			@RequestParam(value="monthKey",required=false)Long monthKey,Model model){
			PerMonth month=monthService.selectByPrimaryKey(monthKey);
			model.addAttribute("month",month);
		return "qms/bph/base/month/edit";
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
	/**
	 * 批量删除
	 * @param maKey
	 * @return
	 */
	@RequestMapping("/deleteBatch")
	public void deleteBatch(HttpServletResponse rs,@RequestParam(value="selectKey",required=false)String selectKey){
		int result = 0;
		String msg = "";
		Map<String ,Object> map = new HashMap<String, Object>();
		try{
            if(selectKey!=null)
            {
            	String[] keys = selectKey.split(",");
            	for(String key : keys)
            	{
            		monthService.deleteByPrimaryKey(Long.valueOf(key));
            	}
            }
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("result", result);
		map.put("msg", msg);
		JSONObject obj = JSONObject.fromObject(map);
		printResponContent(rs, obj.toString());
	}
	
	@RequestMapping("/getindex")
	public String getindex(Model model,PerGroup pergroup,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("department", pergroup.getDepartment());
		
		List<PerGroup> list = groupService.getIndexAllByPage(bs);
		for(PerGroup cate : list){
			int i=0;
			if(cate.getItem() != null){
				for(PerItem item : cate.getItem()){
					if(item.getUiindexs()!=null){
						for(PerSetup index : item.getUiindexs()){
							index.setMainkey(i+"");
							i++;
						}
					}
				}
			}
		}
		model.addAttribute("list", list);
		return "qms/bph/base/month/indexes";
	}
	
}
