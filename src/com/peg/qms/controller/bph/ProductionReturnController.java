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
import com.peg.model.ShiftGroup;
import com.peg.model.bph.ProductionReturn;
import com.peg.qms.controller.BaseController;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.ProductionReturnServiceI;
/**生产退次控制器
 * @Class: ProductionReturnController @TODO:
 */
@Controller
@RequestMapping("system/productionreturn")
public class ProductionReturnController extends BaseController{

	@Autowired
	private ProductionReturnServiceI productionReturnService;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@RequestMapping("/list")
	public String list(Model model,ProductionReturn productionReturn,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("shiftGroupCategory", productionReturn.getShiftgroupCategory());
		bs.put("materialTag", productionReturn.getMaterialTag());
		bs.put("materialName", productionReturn.getMaterialName());

		List<ProductionReturn> list = productionReturnService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/bph/base/productionreturn/list";
		
	}
	
	@RequestMapping("/add")
	public String add()
	{
		return "qms/bph/base/productionreturn/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		ProductionReturn group = productionReturnService.selectByPrimaryKey(id);
		String factory = group.getFactory();
		List<ShiftGroup> list = shiftGroupService.getArea(factory);
		model.addAttribute("group", group);
		model.addAttribute("area", list);
		return "qms/bph/base/productionreturn/edit";
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/update")
	public ModelAndView update(ProductionReturn productionReturn)
	{
		Date date = new Date();
		date.getDate();
		productionReturn.setLastUpdateUser(getCurrentUserName());
		productionReturn.setLastUpdateTime(date);
		productionReturnService.updateByPrimaryKeySelective(productionReturn);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(ProductionReturn productionReturn)
	{
		productionReturn.setCreateUser(getCurrentUserName());
		productionReturnService.insert(productionReturn);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ProductionReturn productionReturn)
	{
		productionReturnService.deleteByPrimaryKey(productionReturn.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/getarea")
	public String getArea(Model model,ProductionReturn productionReturn){
		String factory = productionReturn.getFactory();
		List<ShiftGroup> list = shiftGroupService.getArea(factory);
		
		model.addAttribute("list",list);
		return "qms/bph/base/productionreturn/area";
	}
	
}
