package com.peg.qms.controller.part;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.part.WareHouseProductRef;
import com.peg.qms.controller.BaseController;
import com.peg.service.part.WareHouseProductRefServiceI;
import com.peg.web.util.ExcelUtilities;

/**
 * 仓库与机型对应关系
 * @author Administrator
 *
 */
@Controller
@RequestMapping("quality/wareHouse")
public class WareHousePruductController extends BaseController{

	@Autowired 
	private WareHouseProductRefServiceI wareHouseProductRefService;
	
	@RequestMapping("/list")
	public String list(Model model,PageParameter page,WareHouseProductRef wareHouse){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("wareNumber", wareHouse.getWareNumber());
		bs.put("productName", wareHouse.getProductName());
		List<WareHouseProductRef> list = wareHouseProductRefService.findAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/part/wareHouse/list";
	}
	
	@RequestMapping("/add")
	public String add()
	{
		return "qms/part/wareHouse/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		WareHouseProductRef ware = wareHouseProductRefService.selectByPrimaryKey(id);	
		model.addAttribute("ware", ware);
		return "qms/part/wareHouse/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(WareHouseProductRef ware)
	{
		ware.setLastModifyTime(new Date());
		wareHouseProductRefService.updateByPrimaryKeySelective(ware);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(WareHouseProductRef ware)
	{
		ware.setCreateUser(getCurrentUserName());
		wareHouseProductRefService.insert(ware);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", required = false) Long id)
	{
		wareHouseProductRefService.deleteByPrimaryKey(id);
		return ajaxDoneSuccess("删除成功");	
	}
	
	  @RequestMapping("/exportExcel")
	    public String exportErpPartExcel(Model model,WareHouseProductRef ware,
	            HttpServletRequest request, HttpServletResponse response) {
	        String retView = "/error/err";
	        try {
	            BaseSearch bs = new BaseSearch();
	            PageParameter page = new PageParameter();
	            page.setNumPerPage(50000);
	            bs.setPage(page);
	            bs.put("wareNumber", ware.getWareNumber());
	            bs.put("productName", ware.getProductName());
	            List<WareHouseProductRef> list = wareHouseProductRefService.findAllByPage(bs);
	            String[] CN = { "仓库编号","仓库名称","机型类别"};
	            List<String[]> excelList = new ArrayList<String[]>();
	            int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	            	WareHouseProductRef tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getWareNumber();
	                tmpStr[index++] = tmpVO.getWareName();
	                tmpStr[index++] = tmpVO.getProductName();
	                index = 0;
	                excelList.add(tmpStr);
	            }

	            // 获取项目根目录
	            String rootPath = request.getSession().getServletContext()
	                    .getRealPath("/");
	            String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	            String filePath = rootPath + "/" + fname;
	            ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	            String contentType = "application/msexcel";
	            ExcelUtilities.downloadExcel(request, response, filePath,
	                    contentType, "仓库与机型对应关系" + fname);
	            return null;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return retView;
	    }
}
