package com.peg.qms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.peg.base.exception.QmsException;
import com.peg.beans.part.TestInstanceUtil;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonSelectedBox;
import com.peg.model.LaterSumtime;
import com.peg.model.MachineType;
import com.peg.model.baseCommonVo;
import com.peg.model.part.TestInstance;
import com.peg.model.system.SysMesUser;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.DateEditor;

@Component("baseController")
public class BaseController {	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;

	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String throwException(Exception e) {
		logger.error(e.getMessage(), e);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String msg = null;
		if (e.getMessage().length() > 100) {
			msg = "程序运行异常";
		} else {
			msg = e.getMessage();
		}
		request.setAttribute("ex", msg);
		return "error";
	}

	/**
	 * Ajax的异常处理
	 * 
	 * @param e
	 *            异常
	 */
	protected String throwAjaxException(Exception e) {
		logger.error(e);
		return "业务处理失败";
	}

	protected ModelAndView ajaxDone(int statusCode, String message,
			String forwardUrl) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("forwardUrl", forwardUrl);
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "");
	}

	protected ModelAndView ajaxDoneError(String message) {
		if (message == null) {
			message = "空指针异常";
		}
		message = replaceSpecialCharacter(message);

		return ajaxDone(300, message, "");
	}

	protected String replaceSpecialCharacter(String msg) {
		if (msg != null) {
			msg = msg.replace("\r\n", "<br>").replace("\n", "<br>").replace(
					"\t", " ");
		}
		return msg;
	}

	/**
	 * 转换乱码而用
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String decode(String value) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(value)) {
			if(value.equals(new String(value.getBytes("iso8859-1"), "iso8859-1")))
			{
				value = new String(value.getBytes("ISO-8859-1"),"utf-8");
			}			
		}
		return value;
	}

	protected void printResponContent(HttpServletResponse respon, String content) {
		PrintWriter out = null;
		try {
			respon.setContentType("text/json;charset=UTF-8");
			respon.setHeader("Cache-Control", "no-cache");
			out = respon.getWriter();
			out.write(content);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	protected String getCurrentUserName() {
		Subject subject = SecurityUtils.getSubject();
		return subject.getPrincipal().toString();
	}
	
	/**
	 * 从session中获取登陆用户信息
	 * @return
	 * @throws QmsException
	 */
	protected SysMesUser getCurrentUser() throws QmsException
	{
		Subject subject = SecurityUtils.getSubject();
		SysMesUser u = (SysMesUser)subject.getSession().getAttribute("user");
		if(u == null)
		{
			throw new QmsException("用户未登陆");
		}
		return u;
	}
	
	/**
	 * 加载机型类别
	 * @param model
	 */
	protected void setBaseData(Model model){
		BaseSearch bs = new BaseSearch();
		bs.put("machineType", null);
		List<MachineType> list = machineTypeService.getAll();
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		model.addAttribute("productTypes", list);
		model.addAttribute("laterSumtime", laterTime.getSumMonth());
	}
	
	public void setPartType(Model model) {
		List<TestInstance> parList = testInstanceService.getPartType();
		Map<String,String> partMap = TestInstanceUtil.getPartMap(parList);
		JSONObject partObject = JSONObject.fromObject( partMap );
		model.addAttribute("partMap", partObject);
	}
	
	/**
	 * 加载工厂，车间，班组
	 */
	@SuppressWarnings("unchecked")
	protected void LoadFAPG(Model model,baseCommonVo vo){
		//工厂
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<CommonSelectedBox> flist = (List<CommonSelectedBox>) request.getSession().getAttribute("lfactory");
	
		//车间
		Map<String,List<CommonSelectedBox>> areaMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("larea");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!=""){
			List<CommonSelectedBox> areaList = areaMap.get(vo.getBaseFactory());
			model.addAttribute("common_areaList", areaList);
		}
		
		//班组类别(工厂+车间)
		Map<String,List<CommonSelectedBox>> cateMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lcatMap");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!="" && vo.getBaseArea() != null && vo.getBaseArea() != ""){
		    List<CommonSelectedBox> cateList = cateMap.get(vo.getBaseFactory()+"-"+vo.getBaseArea());
		    model.addAttribute("common_cateList", cateList);
		}
		
		//班组类别(工厂)
		Map<String,List<CommonSelectedBox>> fcateMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lfcatMap");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!="" ){
		    List<CommonSelectedBox> fcateList = fcateMap.get(vo.getBaseFactory());
		    model.addAttribute("common_fcateList", fcateList);
		}
		//班组（根据工厂）
		Map<String,List<CommonSelectedBox>> fgroupMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lfgroupMap");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!=""){
		   List<CommonSelectedBox> fgroupList = fgroupMap.get(vo.getBaseFactory());
		   model.addAttribute("fgroupList", fgroupList);
		}
		//班组（根据工厂车间）
		Map<String,List<CommonSelectedBox>> fagroupMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lfagroupMap");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!="" && vo.getBaseArea() != null && vo.getBaseArea() != ""){
		     List<CommonSelectedBox> fagroupList = fagroupMap.get(vo.getBaseFactory()+"-"+vo.getBaseArea());
		     model.addAttribute("fagroupList", fagroupList);
		}
		//考核项目
		Map<String,List<CommonSelectedBox>> checkItemMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lcheckItemMap");
		if(vo.getBaseFactory()!=null && vo.getBaseFactory()!="" && vo.getBaseArea() != null 
				&& vo.getBaseArea() != "" && vo.getBaseCategory()!= null && vo.getBaseCategory() !=""){
		     List<CommonSelectedBox> checkItemList = checkItemMap.get(vo.getBaseFactory()+"-"+vo.getBaseArea()+"-"+vo.getBaseCategory());
		     model.addAttribute("checkItemList", checkItemList);
		}
		model.addAttribute("common_factoryList", flist);
        model.addAttribute("common_mapAreas", areaMap);
        model.addAttribute("common_mapCates", cateMap);
        model.addAttribute("common_fmapCates", fcateMap);
        model.addAttribute("common_fgroupMap", fgroupMap);
        model.addAttribute("common_fagroupMap", fagroupMap);
        model.addAttribute("common_checkItemMap", checkItemMap);
        model.addAttribute("id_end",System.currentTimeMillis());
	}
	
}
