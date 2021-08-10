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
import com.peg.model.bph.ProcessScoreSetting;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.ProcessScoreSettingServiceI;
/**过程分数设定表
 * @Class: ProcessScoreSettingController @TODO:
 */
@Controller
@RequestMapping("system/processscore")
public class ProcessScoreSettingController extends BaseController{
	
	@Autowired
	private ProcessScoreSettingServiceI processScoreSettingService;
	
	@RequestMapping("/list")
	public String list(Model model,ProcessScoreSetting processScoreSetting,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory",  processScoreSetting.getBaseFactory());
		bs.put("indexCode",  processScoreSetting.getIndexCode());
		bs.put("indexContent", processScoreSetting.getIndexContent());
        LoadFAPG(model, processScoreSetting);
		List< ProcessScoreSetting> list =  processScoreSettingService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", processScoreSetting);
		return "qms/bph/base/processscore/list";
		
	}
	
	@RequestMapping("/add")
	public String add(Model model, ProcessScoreSetting processScoreSetting)
	{
		LoadFAPG(model, processScoreSetting);
		return "qms/bph/base/processscore/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		ProcessScoreSetting group = processScoreSettingService.selectByPrimaryKey(id);	
		group.setBaseFactory(group.getFactory());
		LoadFAPG(model, group);
		model.addAttribute("vo", group);
		return "qms/bph/base/processscore/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(ProcessScoreSetting processScoreSetting)
	{
		
		processScoreSetting.setLastUpdateUser(getCurrentUserName());
		processScoreSetting.setLastUpdateTime(new Date());
		processScoreSetting.setFactory(processScoreSetting.getBaseFactory());
		processScoreSettingService.updateByPrimaryKeySelective(processScoreSetting);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(ProcessScoreSetting processScoreSetting)
	{
		processScoreSetting.setCreateUser(getCurrentUserName());
		processScoreSetting.setFactory(processScoreSetting.getBaseFactory());
		processScoreSettingService.insert(processScoreSetting);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ProcessScoreSetting processScoreSetting)
	{
		processScoreSettingService.deleteByPrimaryKey(processScoreSetting.getId());
		return ajaxDoneSuccess("删除成功");	
	}

}
