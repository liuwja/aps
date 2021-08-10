package com.peg.qms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.Relation;
import com.peg.service.RelationServiceI;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("base/relation")
public class RelationController extends BaseController{
	
	@Autowired
	private RelationServiceI relationService;
	
	@RequestMapping("/relationList")
	public String relation(Model model,Relation relation,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		
		bs.put("crm", relation.getCrm());
		bs.put("mes", relation.getMes());
				
		
		
		List<Relation> list = relationService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/base/relation/relationList";
		
	}
	
	@RequestMapping("/addRelation")
	public String addRelation()
	{
		return "qms/base/relation/addRelation";
	}
	
	@RequestMapping("/editRelation")
	public String editMachine( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		Relation relation = relationService.selectByPrimaryKey(id);		
		model.addAttribute("relation", relation);
		return "qms/base/relation/editRelation";
	}
	
	
	@RequestMapping("/updateRelation")
	public ModelAndView updateMachine(Relation relation)
	{
		relationService.updateByPrimaryKeySelective(relation);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveRelation")
	public ModelAndView saveRelation(Relation relation)
	{
		relation.setEntryName(getCurrentUserName());
		relationService.insert(relation);
		return ajaxDoneSuccess("保存成功");
	}
	
	

	@RequestMapping("/delete")
	public ModelAndView delete(Relation relation)
	{
		relationService.deleteByPrimaryKey(relation.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
}
