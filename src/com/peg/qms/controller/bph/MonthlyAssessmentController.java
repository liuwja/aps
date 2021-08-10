package com.peg.qms.controller.bph;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonSelectedBox;
import com.peg.model.ShiftGroup;
import com.peg.model.bph.CheckIndex;
import com.peg.model.bph.MonthlyAssessment;
import com.peg.model.bph.MonthlyAssessmentExcel;
import com.peg.qms.controller.BaseController;
import com.peg.qms.controller.ExcelReaderJXL;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.ExcelReaderServiceI;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.CheckIndexServiceI;
import com.peg.service.bph.MonthlyAssessmentServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("system/monthlyassessment")
public class MonthlyAssessmentController extends BaseController{
	@Autowired
	private MonthlyAssessmentServiceI monthlyAssessmentService;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private CommonSelectedBoxService comSelBoxService;
	
	@Autowired 
	private CheckIndexServiceI checkIndexService;
	
	@Autowired
	private ExcelReaderServiceI excelReaderService;
	
	@RequestMapping("/list")
	public String list(Model model,MonthlyAssessment monthlyAssessment,PageParameter page){
		
		String factory = monthlyAssessment.getBaseFactory();
		String area = monthlyAssessment.getBaseArea();
		List<CommonSelectedBox> itemList = null;
		if(factory != null && !"".equals(factory)){
			if(area != null && !"".equals(area)){
				itemList = comSelBoxService.getCommonCheckItem(factory, area);			
			}			
		}
		
		
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", monthlyAssessment.getBaseFactory());
		bs.put("area",monthlyAssessment.getBaseArea());
		bs.put("checkItem", monthlyAssessment.getCheckItem());
		bs.put("col2", monthlyAssessment.getBaseGroup());
		bs.put("month", monthlyAssessment.getMonth());
		LoadFAPG(model, monthlyAssessment);
		List<MonthlyAssessment> list = monthlyAssessmentService.getAllByPage(bs);
		model.addAttribute("list",list);
		model.addAttribute("cheItemList", itemList);
		model.addAttribute("vo", monthlyAssessment);
		model.addAttribute("page", page);
		return "qms/system/monthlyassessment/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model,MonthlyAssessment monthlyAssessment){
		LoadFAPG(model, monthlyAssessment);
		return "qms/system/monthlyassessment/add";
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model){
		PageParameter page = new PageParameter();
		BaseSearch bs = new BaseSearch();		
		MonthlyAssessment group = monthlyAssessmentService.selectByPrimaryKey(id);
		bs.setPage(page);
		bs.put("shiftgroupCategory", group.getShiftgroupCategory());
		bs.put("factory", group.getFactory());
		bs.put("checkItem", group.getCheckItem());
		List<MonthlyAssessment> itemList = monthlyAssessmentService.getAssessment(bs);
		List<MonthlyAssessment> indexList = monthlyAssessmentService.getIndex(bs);
		group.setBaseFactory(group.getFactory());
		group.setBaseArea(group.getArea());
		group.setBaseCategory(group.getShiftgroupCategory());
		group.setBaseGroup(group.getCol2());
		LoadFAPG(model, group);
		
		model.addAttribute("vo",group);
		model.addAttribute("item", itemList);
		model.addAttribute("index", indexList);
		return "qms/system/monthlyassessment/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView update(MonthlyAssessment monthlyAssessment){	
		Date date = new Date();
		monthlyAssessment.setLastUpdateUser(getCurrentUserName());
		monthlyAssessment.setLastUpdateTime(date);
		monthlyAssessment.setFactory(monthlyAssessment.getBaseFactory());
		monthlyAssessment.setArea(monthlyAssessment.getBaseArea());
		monthlyAssessment.setShiftgroupCategory(monthlyAssessment.getBaseCategory());
		monthlyAssessment.setCol2(monthlyAssessment.getBaseGroup());
		monthlyAssessmentService.updateByPrimaryKeySelective(monthlyAssessment);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(MonthlyAssessment monthlyAssessment){
		try{
			monthlyAssessment.setCreateUser(getCurrentUserName());
			monthlyAssessment.setFactory(monthlyAssessment.getBaseFactory());
			monthlyAssessment.setArea(monthlyAssessment.getBaseArea());
			monthlyAssessment.setShiftgroupCategory(monthlyAssessment.getBaseCategory());
			monthlyAssessment.setCol2(monthlyAssessment.getBaseGroup());
			List<CheckIndex> list = monthlyAssessment.getCheckIndexs();
			if(!list.isEmpty()){
				for(CheckIndex idx : list){
					List<MonthlyAssessment> existList = monthlyAssessmentService.fingMonthIndex(monthlyAssessment.getMonth(), monthlyAssessment.getBaseFactory(), monthlyAssessment.getBaseGroup(), idx.getIndexCode());
					if(!existList.isEmpty()){
						return ajaxDoneError("该班组"+monthlyAssessment.getMonth()+"月份已存在考核指标："+idx.getCheckIndex());
					}	
				}
				for(CheckIndex idx : list){
					monthlyAssessment.setCheckIndex(idx.getCheckIndex());
					monthlyAssessment.setIndexId(idx.getId());
					monthlyAssessment.setScale(idx.getScale());
					monthlyAssessment.setMykey(monthlyAssessment.getMykey());
					monthlyAssessment.setTargetValue(idx.getTargetValue());
					monthlyAssessment.setBaseValue(idx.getBaseValue());
					monthlyAssessment.setIndexCode(idx.getIndexCode());
					monthlyAssessmentService.insert(monthlyAssessment);
				}
			}	
		}catch(Exception e){
			return ajaxDoneError("保存失败");
		}
		
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(MonthlyAssessment monthlyAssessment){
		monthlyAssessmentService.deleteByPrimaryKey(monthlyAssessment.getId());
		return ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping("/getItem")
	public void getItem(MonthlyAssessment monthAssessment,PageParameter page,HttpServletResponse rs)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("shiftgroupCategory", monthAssessment.getShiftgroupCategory());
		bs.put("factory", monthAssessment.getFactory());

		Map<String,List<MonthlyAssessment>> map  = new HashMap<String, List<MonthlyAssessment>>();
		List<MonthlyAssessment> list = monthlyAssessmentService.getAssessment(bs);
		map.put("vo", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	
	@RequestMapping("/getindex")
	public void getindex(Model model,MonthlyAssessment monthAssessment,PageParameter page,HttpServletResponse rs)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("checkItem", monthAssessment.getCheckItem());
		bs.put("shiftgroupCategory", monthAssessment.getShiftgroupCategory());
		bs.put("factory", monthAssessment.getFactory());
		Map<String,List<MonthlyAssessment>> map  = new HashMap<String, List<MonthlyAssessment>>();
		List<MonthlyAssessment> list = monthlyAssessmentService.getIndex(bs);
		map.put("vo", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	@RequestMapping("/getscale")
	public void getscale(Model model,MonthlyAssessment monthAssessment,PageParameter page,HttpServletResponse rs)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", monthAssessment.getFactory());
		bs.put("checkItem", monthAssessment.getCheckItem());
		bs.put("shiftgroupCategory", monthAssessment.getShiftgroupCategory());
		Map<String,List<MonthlyAssessment>> map  = new HashMap<String, List<MonthlyAssessment>>();
		List<MonthlyAssessment> list = monthlyAssessmentService.getScale(bs);
		map.put("vo", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	@RequestMapping("/getkey")
	public void getkey(Model model,MonthlyAssessment monthAssessment,HttpServletResponse rs)
	{
		
		Map<String,List<MonthlyAssessment>> map  = new HashMap<String, List<MonthlyAssessment>>();
		List<MonthlyAssessment> list = monthlyAssessmentService.getIndexCode(monthAssessment);
		map.put("vo", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	@RequestMapping("/getarea")
	public void getArea(MonthlyAssessment monthAssessment,HttpServletResponse rs){
		String factory = monthAssessment.getFactory();
		Map<String,List<ShiftGroup>> map = new HashMap<String, List<ShiftGroup>>();
		List<ShiftGroup> list = shiftGroupService.getArea(factory);
		map.put("vo", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(rs, jsonObject.toString());
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/uploadExcel")
	public ModelAndView uploadExcel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
//		System.out.println("OK");
		String result = "";
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
            		List<MonthlyAssessmentExcel> list = parseExcel(item);
            		if(list != null)
            		{
//                        System.out.println("insert");
//            			String sessionId = request.getSession().getId();
//            			String uuid = UUID.randomUUID().toString(); 
            			try{
            				insertData(list);
            			}catch(Exception e){
            				result = e.getMessage();
            			}
            			
            		}
            	}
            	catch (Exception e)
            	{
            		logger.error(e.getMessage(), e);
            		result = e.getMessage();
            	}
            }
        }
        if(result.isEmpty()){
        	return null;
        }else{
        	return ajaxDoneError(result);
        }
        
	}
	
	@RequestMapping("/toUpload")
	public String toUpload(MonthlyAssessment vo, Model model) throws Exception {
		
		return "qms/system/monthlyassessment/upload";
	}
	/**
	 * 解析上传的Excel文件
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private List<MonthlyAssessmentExcel> parseExcel(FileItem item) throws Exception 
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
    		List<MonthlyAssessmentExcel> dataList  =  ExcelReaderJXL.readMonthExcel(item.getInputStream());
    		
    		return dataList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		} 
		
        
	}
	
	/**
	 * 导出excel
	 * 
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/excelOutput")
	public void excelOutput(MonthlyAssessment monthlyAssessment, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			if(monthlyAssessment.getFactory().isEmpty()){
				monthlyAssessment.setFactory(null);				
			}
			if(monthlyAssessment.getArea().isEmpty()){
				monthlyAssessment.setArea(null);				
			}
			if(monthlyAssessment.getMonth().isEmpty()){
				monthlyAssessment.setMonth(null);				
			}
			if(monthlyAssessment.getCol2().isEmpty()){
				monthlyAssessment.setCol2(null);				
			}
			List<MonthlyAssessment> list = monthlyAssessmentService.getMonthAssemByExcel(monthlyAssessment);
			String [] CN = {"月份",	"工厂",	"车间",	"班组类型",	"班组名称",	"项目代码",	"考核项目",	"项目比例",	"指标代码",	"考核指标",	"指标比例",	"是否关键指标",	"基准",	"目标"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				MonthlyAssessment item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = item.getMonth();
				itemStr[1] = item.getFactory();
				itemStr[2] = item.getArea();
				itemStr[3] = item.getShiftgroupCategory();
				itemStr[4] = item.getCol2();
				itemStr[5] = item.getItemCode();
				itemStr[6] = item.getCheckItem();
				itemStr[7] = String.valueOf(item.getItemScale());
				itemStr[8] = item.getIndexCode();
				itemStr[9] = item.getCheckIndex();
				itemStr[10] = item.getMykey();
				itemStr[11] = String.valueOf(item.getBaseValue());
				itemStr[12] = String.valueOf(item.getTargetValue());
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "月度考核基准设定"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			
		}
	

	
	private void insertData(List<MonthlyAssessmentExcel> list) throws Exception
	{
		try{

			int i = 0;
//			int j = 0;
			String message = "";
			for(i=0;i<list.size();i++){
				MonthlyAssessment sr = new MonthlyAssessment();
				MonthlyAssessmentExcel tmp = list.get(i);
				String factory = tmp.getFactory();
				String month = tmp.getMonth();
				String groupName = tmp.getGroupName();
				String indexCode = tmp.getIndexCode();
				String ItemCode = tmp.getItemCode();
				String category = tmp.getCategory();
				List<MonthlyAssessment> existList = monthlyAssessmentService.fingMonthIndex(month, factory, groupName, indexCode);
				
				List<MonthlyAssessment> itemId = monthlyAssessmentService.findItemId(factory, category, ItemCode);
				List<MonthlyAssessment> indexId = monthlyAssessmentService.findIndexId(factory, category, indexCode);
                if(!itemId.isEmpty() && !indexId.isEmpty()){
    				if(existList.size()>0){
    					monthlyAssessmentService.deleteMonthIndex(month, factory, groupName, indexCode);
    				}
                	sr.setMonth(tmp.getMonth());
                    sr.setFactory(tmp.getFactory());
                    sr.setArea(tmp.getArea());
                    sr.setShiftgroupCategory(tmp.getCategory());
                    sr.setCol2(tmp.getGroupName());
                    sr.setCol1(String.valueOf(itemId.get(0).getId()));
                    sr.setCheckItem(tmp.getItemName());
                    sr.setItemScale(tmp.getItemScale());
                    sr.setIndexId(indexId.get(0).getIndexId());
                    sr.setCheckIndex(tmp.getIndexName());
                    sr.setIndexCode(tmp.getIndexCode());
                    sr.setScale(tmp.getIndexScale());
                    sr.setMykey(tmp.getWeithKey());
                    sr.setBaseValue(tmp.getBaseValue());
                    sr.setTargetValue(tmp.getTargetValue());
    	
    				int result = monthlyAssessmentService.insert(sr);
    				if(result <0){
    					message = "插入失败，请检查第"+i+"行";
    					throw new Exception(message); 
    				}
                }
				
			}
		}
       	catch (Exception e)
    	{
       		throw new Exception(e.getMessage());
    	}

	}
	
	
	
	public void getShiftGroup(Model model,String factory,String area){

		List<ShiftGroup> list = shiftGroupService.getShiftGroupByFoArea(factory, area);
		model.addAttribute("groupList", list);
	}
	
	/**
	 * 导入excel初始化
	 */	
//	@RequestMapping(params="method=assembleImportInit")
//	public String assembleImportInit(Model model){
//		return "mes/bph/assembleProduction/assembleExcelImport";
//	}
	
	
	/**
     * 获取excel数据
     * @param model
     * @return
     */
//	@RequestMapping(params="method=getExcelData")
	public void getExcelData(@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload,
			HttpServletResponse respon){
		respon.setContentType("text/html;charset=UTF-8"); 
		respon.setHeader("Cache-Control", "no-cache"); 
		PrintWriter out = null;
		String data="";
		if(fileUpload != null){
			try {
				InputStream inputStream= fileUpload.getInputStream();
				List<String[]> dataList =  ExcelReaderJXL.readExcel(inputStream);
				
				String[] res = excelReaderService.checkMonthlyImportExcel(dataList);
				if(res!=null && !res[1].equals("")){ //有错误提示
					data = "错误提示：\n"+res[1];
				}else{
					data = res[0];
				}
				out = respon.getWriter();
				out.write(data);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(out != null)
				{
					out.close();
				}
			}
		}
	}
	
	/**
	 * 写入excel数据
	 */
	@RequestMapping(params = "method=uploadData")
	public void uploadData(@RequestParam("uploadData")String uploadData,
			HttpServletRequest req, HttpServletResponse respon) {		
		PrintWriter out = null;
		String msg = "success";
		try {
			respon.setContentType("text/json;charset=UTF-8"); 
			respon.setHeader("Cache-Control", "no-cache"); 
			out = respon.getWriter();
			
			if(uploadData==null || uploadData.equals("")){
				throw new Exception("上传数据异常，请联系管理员");
			}

			excelReaderService.uploadMonthly(uploadData);


		} catch (Exception e) {			
			logger.error(e.getMessage(), e);
			msg = e.getMessage();
		}
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", msg);

		JSONObject jsonObject = JSONObject.fromObject( map );
		out.write(jsonObject.toString());
	}
	

}
