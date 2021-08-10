package com.peg.qms.controller.bph;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.Group;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Index;
import com.peg.model.bph.Item;
import com.peg.model.bph.MonthAssessment;
import com.peg.qms.controller.BaseController;
import com.peg.qms.controller.ExcelReaderJXL;
import com.peg.service.ExcelImportService;
import com.peg.service.bph.GroupCategorySerivceI;
import com.peg.service.bph.GroupServiceI;
import com.peg.service.bph.IndexServiceI;
import com.peg.service.bph.ItemServiceI;
import com.peg.service.bph.MonthAssessmentServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("base/monthAssesment")
public class MonthAssesmentController extends BaseController{

	@Autowired
	private IndexServiceI indexService;
	@Autowired
    private GroupCategorySerivceI  groupCategoryService;	
	@Autowired
	private ItemServiceI itemService;
	@Autowired
	private MonthAssessmentServiceI monthAssessmentService;
	@Autowired
	private GroupServiceI groupService;
	@Autowired
	private ExcelImportService excelImportService;
	
	
	@RequestMapping("/list")
	public String list(Model model,MonthAssessment category,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("category", category.getBaseCategory());
		bs.put("factory", category.getBaseFactory());
		bs.put("area", category.getBaseArea());
		bs.put("monthly", category.getMonthly());
		bs.put("groupName", category.getBaseGroup());
		List<Group> list = groupService.getMonthAllByPage(bs);
		LoadFAPG(model, category);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", category);
		return "qms/bph/base/monthAssessment/list";
		
	}
	
	@RequestMapping("/add")
	public String add(Model model,GroupCategory category)
	{
		LoadFAPG(model, category);
		return "qms/bph/base/monthAssessment/add";
	}
	
	@RequestMapping("/edit")
	public String editCheckItem( @RequestParam(value = "groupKey", required = false) Long groupKey,
			@RequestParam(value="itemKey",required=false) Long itemKey,
			@RequestParam(value="indexKey",required=false) Long indexKey,
			@RequestParam(value="maKey",required=false) Long maKey,
		Model model)
	{
		Group group = groupService.selectByPrimaryKey(groupKey);
		Item item = itemService.selectByPrimaryKey(itemKey);
		Index index = indexService.selectByPrimaryKey(indexKey);
		MonthAssessment month = monthAssessmentService.selectByPrimaryKey(maKey);
		model.addAttribute("group", group);
		model.addAttribute("item", item);
		model.addAttribute("index", index);
		model.addAttribute("month", month);
		return "qms/bph/base/monthAssessment/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(MonthAssessment month)
	{
		try{
			month.setLastUpdateTime(new Date());
			month.setLastUpdateUser(getCurrentUserName());
			monthAssessmentService.updateByPrimaryKeySelective(month);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(MonthAssessment monthAssessment)
	{
		try{
			Group gro = groupService.getGroupByFag(monthAssessment.getBaseFactory(),monthAssessment.getBaseArea(),monthAssessment.getBaseCategory(),monthAssessment.getBaseGroup());
			Long groupKey = gro.getGroupKey();
			String monthly = monthAssessment.getMonthly();
			List<MonthAssessment> nlist = monthAssessmentService.findeByGroupAndMonth(groupKey,monthly);
			if(!nlist.isEmpty()){
				throw new Exception("班组"+monthAssessment.getBaseGroup()+monthly+"已设定月度基准！");
			}
			List<MonthAssessment> list = monthAssessment.getMonList();
			for(MonthAssessment mon :list){
				mon.setMonthly(monthly);
				mon.setCreateUser(getCurrentUserName());
				mon.setGroupKey(groupKey);
				mon.setCreateTime(new Date());
				monthAssessmentService.insert(mon);
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="maKey",required=false) Long maKey)
	{
		try{
			monthAssessmentService.deleteByPrimaryKey(maKey);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");	
	}
	
	
	@RequestMapping("/getindex")
	public String getindex(Model model,GroupCategory category,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("category", category.getBaseCategory());
		bs.put("factory", category.getBaseFactory());
		bs.put("area", category.getBaseArea());
		List<GroupCategory> list = groupCategoryService.getIndexAllByPage(bs);
		for(GroupCategory cate : list){
			int i=0;
			if(cate.getItem() != null){
				for(Item item : cate.getItem()){
					if(item.getUiindexs()!=null){
						for(Index index : item.getUiindexs()){
							index.setMainKey(i+"");
							i++;
						}
					}
				}
			}
		}
		model.addAttribute("list", list);
		return "qms/bph/base/monthAssessment/indexes";
	}
	
	
	/**
	 * 导入excel初始化
	 */	
	@RequestMapping("/monthAssessmentImportInit")
	public String assembleImportInit(Model model){
		return "qms/bph/base/monthAssessment/monthImportInit";
	}
	
	
	/**
     * 获取excel数据
     * @param model
     * @return
     */
	@RequestMapping("/getExcelData")
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
				
				String[] res = excelImportService.checkSettingMonthlyImportExcel(dataList);
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
	@RequestMapping("/uploadData")
	public void uploadData(@RequestParam("uploadData")String uploadData,
			HttpServletRequest req, HttpServletResponse respon) throws Exception {
		PrintWriter out = null;
		String msg = "success";
		try {
			respon.setContentType("text/json;charset=UTF-8"); 
			respon.setHeader("Cache-Control", "no-cache"); 
			out = respon.getWriter();
			
			if(uploadData==null || uploadData.equals("")){
				throw new Exception("上传数据异常，请联系管理员");
			}
			excelImportService.uploadSettingMonthly(uploadData);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = e.getMessage();
		}
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", msg);

		JSONObject jsonObject = JSONObject.fromObject( map );
		out.write(jsonObject.toString());
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
	public void excelOutput(MonthAssessment category, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			BaseSearch bs = new BaseSearch();
			PageParameter page = new PageParameter();
			page.setNumPerPage(1000);
			bs.setPage(page);
			bs.put("category", category.getBaseCategory());
			bs.put("factory", category.getBaseFactory());
			bs.put("area", category.getBaseArea());
			bs.put("monthly", category.getMonthly());
			bs.put("groupName", category.getBaseGroup());
			List<Group> list = groupService.getMonthAllByPage(bs);
			String [] CN = {"月份",	"工厂",	"车间","班组类别", "班组名称",	"项目代码",	"考核项目",	"项目比例",	"指标代码",	"考核指标",	"指标比例",	"是否关键指标",	"基准",	"目标"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(Group group : list){
				
				GroupCategory cate = group.getUigroupCategory();
				for(Item it : cate.getItem()){
					for(Index in : it.getUiindexs()){
						for(MonthAssessment mon : in.getMonthAssessments()){
							String[] itemStr = new String[ CN.length];
							itemStr[0] = mon.getMonthly();
							itemStr[1] = cate.getFactory();
							itemStr[2] = cate.getArea();
							itemStr[3] = cate.getCategory();
							itemStr[4] = group.getGroupName();
							itemStr[5] = it.getItemCode();
							itemStr[6] = it.getItemName();
							itemStr[7] = String.valueOf(mon.getItemScale());
							itemStr[8] = in.getIndexCode();
							itemStr[9] = in.getIndexName();
							itemStr[10] = String.valueOf(mon.getIndexScale());
							itemStr[11] = mon.getIndexMainkey();
							itemStr[12] = String.valueOf(mon.getBaseValue());
							itemStr[13] = String.valueOf(mon.getTargetValue());
							excelList.add(itemStr);
						}
					}
				}
				
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, category.getBaseGroup()+"月度考核基准设定"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	/**
	 * 批量删除
	 * @param maKey
	 * @return
	 */
	@RequestMapping("/deleteBatch")
	public void  deleteBatch(HttpServletResponse rs,@RequestParam(value="selectKey",required=false) String selectKey)
	{
		int result = 0;
		String msg = "";
		Map<String ,Object> map = new HashMap<String, Object>();
		try{
            if(selectKey!=null)
            {
            	String[] keys = selectKey.split(",");
            	for(String key : keys)
            	{
            		monthAssessmentService.deleteByPrimaryKey(Long.valueOf(key));
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
}
