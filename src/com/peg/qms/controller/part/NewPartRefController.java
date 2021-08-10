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
import com.peg.model.part.NewPartRef;
import com.peg.qms.controller.BaseController;
import com.peg.service.part.NewPartRefServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("quality/newPartRef")
public class NewPartRefController extends BaseController{

	@Autowired
	private NewPartRefServiceI newPartRefService;
	
	@RequestMapping("/list")
	public String list(Model model,PageParameter page,NewPartRef ref){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("oldPartNumber", ref.getOldPartNumber());
		bs.put("newPartNumber", ref.getNewPartNumber());
		List<NewPartRef> list = newPartRefService.findAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/part/newPartRef/list";
	}
	
	@RequestMapping("/add")
	public String add()
	{
		return "qms/part/newPartRef/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		NewPartRef ref = newPartRefService.selectByPrimaryKey(id);	
		model.addAttribute("ref", ref);
		return "qms/part/newPartRef/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(NewPartRef ref)
	{
		newPartRefService.updateByPrimaryKeySelective(ref);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(NewPartRef ref)
	{
		ref.setCreationTime(new Date());
		ref.setCreateUser(getCurrentUserName());
		newPartRefService.insert(ref);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", required = false) Long id)
	{
		newPartRefService.deleteByPrimaryKey(id);
		return ajaxDoneSuccess("删除成功");	
	}
	
	  @RequestMapping("/exportExcel")
	    public String exportErpPartExcel(Model model,NewPartRef ref,
	            HttpServletRequest request, HttpServletResponse response) {
	        String retView = "/error/err";
	        try {
	            BaseSearch bs = new BaseSearch();
	            PageParameter page = new PageParameter();
	            page.setNumPerPage(50000);
	            bs.setPage(page);
	            bs.put("oldPartNumber", ref.getOldPartNumber());
	            bs.put("newPartNumber", ref.getNewPartNumber());
	            List<NewPartRef> list = newPartRefService.findAllByPage(bs);
	            String[] CN = { "旧物料编码","新物料编码","物料名称"};
	            List<String[]> excelList = new ArrayList<String[]>();
	            int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	            	NewPartRef tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getOldPartNumber();
	                tmpStr[index++] = tmpVO.getNewPartNumber();
	                tmpStr[index++] = tmpVO.getPartName();
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
	                    contentType, "新旧物料对应关系" + fname);
	            return null;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return retView;
	    }
}
