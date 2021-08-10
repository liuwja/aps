package com.peg.qms.controller.part;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.part.MCPart;
import com.peg.qms.controller.BaseController;
import com.peg.service.part.MCPartServiceI;
import com.peg.web.util.ConstantInterface;

@Controller
@RequestMapping("quality/MCPart")
public class MCPartController extends BaseController {

	@Autowired
	private MCPartServiceI MCPartService;
	
	/**
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, MCPart vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.put("isPage", ConstantInterface.YES);
		bs.setPage(page);
		bs.put("mes_part_number", vo.getMesPartNumber());
		bs.put("crm_part_number", vo.getCrmPartNumber());
		List<MCPart> list = MCPartService.findAll(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/part/mcPart/list";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/qms/part/mcPart/add";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
		MCPart mcPart = MCPartService.selectByPrimaryKey(id);
		model.addAttribute("vo", mcPart);
		return "/qms/part/mcPart/edit";
	}
	
	/**
	 * @param vo
	 * @return
	 */
	@RequestMapping("/save")
	public ModelAndView save(MCPart vo) {
		MCPartService.insert(vo);
		return ajaxDoneSuccess("保存成功");
	}
	
	/**
	 * @param vo
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(MCPart vo) {
		if (vo.getId() != null) {
			MCPartService.update(vo);
		}
		return ajaxDoneSuccess("更新成功");
	}
	
	/**
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(MCPart vo) {
		if (vo.getId() != null) {
			MCPartService.delete(vo);
		}
		return ajaxDoneSuccess("删除成功");
	}
}
