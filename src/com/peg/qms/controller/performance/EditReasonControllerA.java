package com.peg.qms.controller.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.performance.EditReason;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.EditReasonService;

/**
 * 绩效修改记录控制类
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/performanceEditRecord")
public class EditReasonControllerA extends BaseController {
	
	/**
	 * 声明一个  绩效修改日志记录管理 接口类型的对象，用来调用实现类的相关操作日志的增删查改方法
	 */
	@Autowired
	private EditReasonService editReasonService;
	
	/**
	 * 声名一个公共操作接口对象，这里用来获取工厂信息
	 */
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 绩效指标修改记录询
	 * @param model 数据模型，携带前台页面所需数据
	 * @param page 分页信息
	 * @param log 查询条件
	 * @return 结果视图
	 */
	@RequestMapping("/list")
	public String list(Model model,PageParameter page,EditReason log)
	{
		//创建基础查寻类对象，封装查询条件和分页信息
		BaseSearch bs=new BaseSearch();
		bs.setPage(page); //设置分页信息
		bs.put("factoryNumber", log.getFactoryNumber());//设置工厂编号
		bs.put("departmentNumber", log.getDepartmentNumber());//设置部门编号
		bs.put("lastUpdateUser", log.getUpdateUser());//设置修改人
		bs.put("updateReason", log.getUpdateReason());//设置修改原因
		bs.put("startTime", log.getStartTime());//设置查询起始时间点
		bs.put("endTime", log.getEndTime());//设置查询结束时间点
		bs.put("performanceTargetClass", log.getPerformanceTargetClass());//设置绩效目标大类
		bs.put("indexContent", log.getIndexContent());//设置指标内容
		bs.put("indexType", log.getIndexType());//设置指标类型
		bs.put("updateType", log.getUpdateType());//设置修改类型
		bs.put("operatorModule", log.getOperatorModule());//设置操作模块
    
	    //根据基础类封装的查询信息，调用  获取绩效管理修改日志  方法，查询符合条件的记录。如果条件都为空，表示进行全查询，即查询所有
		List<EditReason> list = editReasonService.getAllByPage(bs);
		model.addAttribute("list", list);//查询的结果集
		model.addAttribute("page", page);//分页相关信息
		model.addAttribute("log",log);//查询条件回传
		
		//设置工厂信息，自动填充页面中的工厂下拉列表。传入的参数为null，表示查询所有工厂
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
		//返回结果视图
		return "/qms/bph/base/performance/performanceEditRecord/list";
	}
}
