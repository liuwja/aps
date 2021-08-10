package com.peg.qms.controller.bph;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.QprnSetting;
import com.peg.model.bph.QprnSettingExcel;
import com.peg.qms.controller.BaseController;
import com.peg.qms.controller.ExcelReaderJXL;
import com.peg.service.bph.QprnSettingServiceI;
/**
 * Qprn设定控制器
 * @author Administrator
 *
 */

@Controller
@RequestMapping("system/qprnsetting")
public class QprnSettingController extends BaseController{

	@Autowired
	private QprnSettingServiceI qprnSettingService;
	
	@RequestMapping("/list")
	public String list(Model model,QprnSetting qprnSetting,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("qprn", qprnSetting.getQprn());
		bs.put("scoreType", qprnSetting.getScoreType());

		List<QprnSetting> list = qprnSettingService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/bph/base/qprnsetting/list";
		
	}
	
	@RequestMapping("/add")
	public String add()
	{
		return "qms/bph/base/qprnsetting/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		QprnSetting group = qprnSettingService.selectByPrimaryKey(id);		
		model.addAttribute("group", group);
		return "qms/bph/base/qprnsetting/edit";
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/update")
	public ModelAndView update(QprnSetting qprnSetting)
	{
		Date date = new Date();
		date.getDate();
		qprnSetting.setLastUpdateUser(getCurrentUserName());
		qprnSetting.setLastUpdateTime(date);
		qprnSettingService.updateByPrimaryKeySelective(qprnSetting);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(QprnSetting qprnSetting)
	{
		qprnSetting.setCreateUser(getCurrentUserName());
		qprnSettingService.insert(qprnSetting);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(QprnSetting qprnSetting)
	{
		qprnSettingService.deleteByPrimaryKey(qprnSetting.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/toUpload")
	public String toUpload(QprnSetting vo, Model model) throws Exception {
		
		return "qms/bph/base/qprnsetting/upload";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/uploadExcel")
	public ModelAndView uploadExcel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
//		System.out.println("OK");
//		QprnSetting sr = new QprnSetting();
		DiskFileItemFactory  fac = new DiskFileItemFactory(); 
        ServletFileUpload upload = new ServletFileUpload(fac); 
        upload.setHeaderEncoding("utf-8"); 
        List fileList=null; 
        try { 
            fileList = upload.parseRequest(request); 
        } catch (FileUploadException e1) { 
            e1.printStackTrace(); 
        } 
        Iterator<FileItem> it = fileList.iterator(); 
        while (it.hasNext()) { 
        	FileItem item = it.next(); 
        	// 如果是文件类型字段 
            if (!item.isFormField()) { 
            	try
            	{
            		List<QprnSettingExcel> list = parseExcel(item);
            		if(list != null)
            		{
//                        System.out.println("insert");
//            			String sessionId = request.getSession().getId();
//            			String uuid = UUID.randomUUID().toString(); 
            			insertData(list);
            		}
            	}
            	catch (Exception e)
            	{
            		logger.error(e.getMessage(), e);
            		return ajaxDoneError(e.getMessage());
            	}
            }
        }
		return ajaxDoneSuccess("保存成功");
	}
	
	/**
	 * 解析上传的Excel文件
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private List<QprnSettingExcel> parseExcel(FileItem item) throws Exception 
	{
		//文件名
        String name = item.getName(); 
        if(StringUtils.isBlank(name))
        {
        	return null;
        }
        //扩展名 
        String extName = ""; 
        String message = ""; 
        long size = item.getSize(); 
        if(size > (2*1024*1024)){ 
            message = "文件"+item.getName()+"大超过了2M，上传失败！"; 
            throw new Exception(message); 
        } 

        // 扩展名格式： 
        extName = FilenameUtils.getExtension(name); 
        // 定义允许上传的文件类型 
        List<String> fileTypes = new ArrayList<String>(); 
        fileTypes.add("xls"); 
        if(!fileTypes.contains(extName.toLowerCase())){ 
            message = "只允许上传xls格式的文件"; 
            throw new Exception(message); 
        } 
        
    	try
		{
    		List<QprnSettingExcel> dataList  =  ExcelReaderJXL.readQprnExcel(item.getInputStream());
    		
    		return dataList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		} 
		
        
	}
	
	private void insertData(List<QprnSettingExcel> list) throws Exception
	{
		try{

			int i = 0;
//			int j = 0;
			
			for(i=0;i<list.size();i++){
				QprnSetting sr = new QprnSetting();
				QprnSettingExcel tmp = list.get(i);
//				System.out.println(list.get(i));
				sr.setQprn(tmp.getQprn());
				sr.setScoreType(tmp.getScoreType());
				sr.setWeight(tmp.getWeight());
				sr.setScore(tmp.getScore());
				sr.setCreateUser(getCurrentUserName());
	//			sr.setImportDate(now);
	
				qprnSettingService.insert(sr);
			}
		}
       	catch (Exception e)
    	{
       		throw new Exception(e.getMessage());
    	}

	}
	
	
	
}
