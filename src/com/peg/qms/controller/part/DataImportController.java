package com.peg.qms.controller.part;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.peg.dao.CommonSelectedBoxMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.ErpAssembleProductReturnMapper;
import com.peg.dao.part.ErpPartReturnMapper;
import com.peg.dao.part.NewPartRefMapper;
import com.peg.dao.part.WareHouseProductRefMapper;
import com.peg.model.CommonSelectedBox;
import com.peg.model.SupplierRef;
import com.peg.model.part.ErpAssembleProductReturn;
import com.peg.model.part.ErpPartReturn;
import com.peg.model.part.NewPartRef;
import com.peg.model.part.WareHouseProductRef;
import com.peg.qms.controller.BaseController;
import com.peg.service.SupplierRefServiceI;
import com.peg.service.part.IDataImportService;
import com.peg.web.util.DateEditor;
import com.peg.web.util.FileUtil;
import com.peg.web.util.TmStringUtils;

/**
 * 数据导入
 * <p>
 * @author Lin, 2014-8-8 下午04:21:37
 */
@Controller
@RequestMapping("base/part/import")
public class DataImportController extends BaseController
{

	@Autowired
	private IDataImportService dataImportService;
	
	@Autowired
	private ErpPartReturnMapper erpPartReturnMapper;
	
	@Autowired 
	private ErpAssembleProductReturnMapper erpAssembleProductReturnMapper;
	
	@Autowired
	private WareHouseProductRefMapper wareHouseProductRefMapper;
	
	@Autowired
	private CommonSelectedBoxMapper commonSelectedBoxMapper;
	
	@Autowired
	private NewPartRefMapper newPartRefMapper;
	
	@Autowired SupplierRefServiceI supplierRefService;
	
	@RequestMapping("/init")
	public String init(Model model, PageParameter page)
	{
		return "qms/base/import/init";
	}
	
	
	/**
	@RequestMapping("/upload")
	public ModelAndView uploadExcel(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		DiskFileItemFactory  fac = new DiskFileItemFactory(); 
        ServletFileUpload upload = new ServletFileUpload(fac); 
        upload.setHeaderEncoding("utf-8"); 
        StopWatch watch = new StopWatch();
        watch.start();
        List<FileItem> fileList=null; 
        try { 
            fileList = upload.parseRequest(request); 
        } catch (FileUploadException e1) { 
            logger.error(e1.getMessage(), e1); 
        } 
        
        Iterator<FileItem> it = fileList.iterator(); 
        long totalRows = 0;
        
        String partType = null;
        List<String[]> firstSheet = null;
        while (it.hasNext()) { 
            FileItem item = it.next(); 
            
            // 如果是文件类型字段 
            if (!item.isFormField()) { 
            	try
            	{
            		firstSheet = FileUtil.parseCsvExlFile(item);
            		totalRows = firstSheet.size();
//            		printList(firstSheet);
            	}
            	catch (Exception e)
            	{
            		logger.error(e.getMessage(), e);
            		return ajaxDoneError(e.getMessage());
            	}
            }
            try
    		{
    				insert(firstSheet);
    		}
    		catch (Exception e)
    		{
        		logger.error(e.getMessage(), e);
        		return ajaxDoneError(e.getMessage());
    		}
        } 
       
        
        watch.stop();
        double useTime = watch.getTime() / 1000d;
        return ajaxDoneSuccess("上传成功,总行数为：" + totalRows + "行, 总用时：" + useTime + "秒");
	}
	 * @throws IOException 
	*/
	@RequestMapping("/upload")
	public void uploadExcel(@RequestParam(value = "excelFile", required = false) MultipartFile fileUpload,
			@RequestParam(value = "importType", required = false) String importType,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			HttpServletResponse respon) throws IOException{
		respon.setContentType("text/html;charset=UTF-8"); 
		respon.setHeader("Cache-Control", "no-cache"); 
		PrintWriter out = null;
		String data="";
	    StopWatch watch = new StopWatch();
	    long totalRows = 0;
	    watch.start();
		if(fileUpload != null){
				try{
					CommonsMultipartFile cf= (CommonsMultipartFile) fileUpload; 
			        FileItem item = (FileItem)cf.getFileItem(); 
					List<String[]> dataList =  new ArrayList<String[]>();
					dataList = FileUtil.parseCsvExlFile(item);
					totalRows = dataList.size();
					List<String> exit = insert(dataList,importType,startTime,endTime);
					double useTime = watch.getTime() / 1000d;
					data = "插入"+(totalRows-exit.size())+" 条记录，耗时(s)"+useTime;
					if(!exit.isEmpty()){
						data += "；部分数据不存在："+exit.toString()+";请检查！";
					}
				}catch(Exception e){
					data = "错误提示：\n"+e.getMessage();
					e.printStackTrace();
				}
			    finally {
			    	out = respon.getWriter();
					out.write(data);		
				if(out != null)
				{
					out.close();
				}
			}
		}
		
		watch.stop();
	}
	
	/**
	 * 插入数据
	 * @param list
	 */
	private List<String> insert(List<String[]> list,String importType,String startTime,String endTime) throws Exception
	{
		List<String> exit = new ArrayList<String>();
		if(importType.equals("partReturn")){
			erpPartReturnMapper.deleteByPeriod(startTime,endTime);
		}else if(importType.equals("assembleReturn")){
			erpAssembleProductReturnMapper.deleteByPeriod(startTime,endTime);
		}
		for(String[] arr : list)
		{
			//excel格式：退次日期	物料编号	物料名称	供应商编号	供应商名称	退次数量	退次仓库
			if(importType.equals("partReturn")){
				if(StringUtils.isBlank(arr[0]))
				{
					logger.warn( "下方一行数据为空");
					continue;
				}
				ErpPartReturn part = new ErpPartReturn();
				//查找机型（根据仓库）
				if(TmStringUtils.isEmpty(arr[6])){
					exit.add(arr[6]);
					continue;
				}
				if(TmStringUtils.isEmpty(arr[1])){
					exit.add(arr[1]);
					continue;
				}
//				WareHouseProductRef house = wareHouseProductRefMapper.selectByHouseNumber(arr[6]);
				//物料类型，物料级别，产品成熟度
				CommonSelectedBox box = commonSelectedBoxMapper.getUdaPart(arr[1]);
				if(box==null){
					exit.add("物料-"+arr[1]);
					continue;
				}
//				if(house==null){
//					exit.add("仓库-"+arr[6]);
//					continue;
//				}
				part.setReturnDate(arr[0]);              //退次日期
//				part.setLotNumber(arr[1]);               //批次编号
				part.setPartType(box.getCol1());         //物料类型
				part.setPartClass(box.getCol2());        //物料级别
				part.setPartNumber(arr[1]);              //物料编号
				part.setPartName(arr[2]);                //物料名称
				part.setSupplierNumber(arr[3]);          //供应商编号
				part.setSupplierName(arr[4]);            //供应商名称
//				part.setTotalQty(new Long(arr[8]));      //总数
				part.setReturnNumber( Math.round(Double.valueOf(arr[5].replace(",", ""))));  //回退数量
				part.setWareHouse(arr[6]);              //仓库
				part.setProductMaturity(box.getCol3());        //产品成熟度
				part.setProductionType(box.getCol6());         //机型
				part.setCreationTime(new Date());         //创建时间
				erpPartReturnMapper.insert(part);
			}
			//excel格式：退次日期	物料编号	物料名称	供应商编号	供应商名称	退次数量	退次仓库
			if(importType.equals("assembleReturn")){
				if(StringUtils.isBlank(arr[0]))
				{
					logger.warn( "下方一行数据为空");
					continue;
				}
				//查找机型（根据仓库）
				if(TmStringUtils.isEmpty(arr[6])){
					exit.add(arr[6]);
					continue;
				}
				if(TmStringUtils.isEmpty(arr[1])){
					exit.add(arr[1]);
					continue;
				}
//				WareHouseProductRef house = wareHouseProductRefMapper.selectByHouseNumber(arr[6]);
				//物料类型，物料级别，产品成熟度
				CommonSelectedBox box = commonSelectedBoxMapper.getUdaPart(arr[1]);
				//供应商key，供应商编号
				CommonSelectedBox csb = commonSelectedBoxMapper.getAccount(arr[3]);
				if(box==null){
					exit.add("物料-"+arr[1]);
					continue;
				}
//				if(house==null){
//					exit.add("仓库-"+arr[6]);
//					continue;
//				}
				ErpAssembleProductReturn part = new ErpAssembleProductReturn();
				part.setReturnDate(DateEditor.parseDate(arr[0], "yyyy-MM-dd"));
//				part.setLotNumber(arr[1]);               //批次编号
				part.setPartType(box.getCol1());         //物料类型
				part.setPartClass(box.getCol2());        //物料级别
				part.setPartNumber(arr[1]);              //物料编号
				part.setPartName(arr[2]);                //物料名称
				part.setSupplierNumber(arr[3]);          //供应商编号
				part.setSupplierName(arr[4]);            //供应商名称
//				part.setTotalQty(new Long(arr[8]));      //总数
				part.setReturnNumber(new Long(arr[5]));  //回退数量
				part.setWareHouse(arr[6]);              //仓库
				part.setProductMaturity(box.getCol3());        //产品成熟度
//				part.setProductionType(house.getProductName());         //机型
				part.setProductionType(box.getCol6());
				part.setCreationTime(new Date());         //创建时间
				part.setPart_key(box.getCol4());			//物料key
				part.setConsumption_type(box.getCol5()); 	//是否关键件
				part.setAccount_key(csb == null ? null : csb.getCol1()); 		//供应商key
				erpAssembleProductReturnMapper.insert(part);
			}
			if(importType.equals("wareHouse")){
				if(StringUtils.isBlank(arr[0]))
				{
					logger.warn( "下方一行数据为空");
					continue;
				}
				WareHouseProductRef ware = new WareHouseProductRef();
				ware.setWareNumber(arr[0]);
				ware.setWareName(arr[1]);
				ware.setProductName(arr[2]);
				ware.setCreationTime(new Date());
				wareHouseProductRefMapper.insert(ware);
			}
			if(importType.equals("newPartRef")){
				if(arr.length<3){
					exit.add(arr[0]);
					continue;
				}
				if(StringUtils.isBlank(arr[0]))
				{
					logger.warn( "下方一行数据为空");
					continue;
				}
				NewPartRef ref = new NewPartRef();
				ref.setOldPartNumber(arr[0]);
				ref.setNewPartNumber(arr[1]);
				ref.setPartName(arr[2]);
				ref.setCreationTime(new Date());
				newPartRefMapper.insert(ref);
			}
			if(importType.equals("supplierRef")){
				if(arr.length<4){
					exit.add(arr[0]);
					continue;
				}
				if(StringUtils.isBlank(arr[0]))
				{
					logger.warn( "下方一行数据为空");
					continue;
				}
				SupplierRef supplierRef = new SupplierRef();
				supplierRef.setSupplierNumber(arr[0]);
				supplierRef.setSupplierName(arr[1]);
				supplierRef.setSupplierNumberN(arr[2]);
				supplierRef.setSupplierNameN(arr[3]);
//				supplierRef.setSupplierShortName(arr[4]);
				supplierRefService.insert(supplierRef);
			}
		}
		//刷新旧供应商，新旧物料
		if(importType.equals("partReturn")){
			erpPartReturnMapper.updateSupPartByPeriod(startTime,endTime);
		}else if(importType.equals("assembleReturn")){
			erpAssembleProductReturnMapper.updateSupPartByPeriod(startTime,endTime);
		}
		return exit;
	}

}


