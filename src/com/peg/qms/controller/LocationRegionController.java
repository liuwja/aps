package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.LocationRegion;
import com.peg.service.LocationRegionServiceI;
import com.peg.web.util.TmStringUtils;

@Controller
@RequestMapping("base/locationRegion")
public class LocationRegionController extends BaseController{

	@Autowired
	private LocationRegionServiceI locationRegionService;
	
	@RequestMapping("/locationRegionList")
	public String relation(Model model,LocationRegion locationRegion,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		
		bs.put("locationCode", locationRegion.getLocationCode());
		bs.put("location", locationRegion.getLocation());
		bs.put("region", locationRegion.getRegion());		
		
		logger.info("===="+bs.getHashMap());
		List<LocationRegion> list = locationRegionService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/base/locationRegion/locationRegionList";
		
	}
	
	@RequestMapping("/addLocationRegion")
	public String addLocationRegion()
	{
		return "qms/base/locationRegion/addLocationRegion";
	}
	
	@RequestMapping("/editLocationRegion")
	public String editMachine( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		LocationRegion locationRegion = locationRegionService.selectByPrimaryKey(id);		
		model.addAttribute("locationRegion", locationRegion);
		return "qms/base/locationRegion/editLocationRegion";
	}
	
	
	@RequestMapping("/updateLocationRegion")
	public ModelAndView updateLocationRegion(LocationRegion locationRegion)
	{
		locationRegion.setMergeRegion(locationRegion.getRegion());
		locationRegion.setLastUpdateUser(getCurrentUserName());
		locationRegionService.updateByPrimaryKeySelective(locationRegion);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveLocationRegion")
	public ModelAndView saveRelation(LocationRegion locationRegion)
	{
		locationRegion.setMergeRegion(locationRegion.getRegion());
		locationRegion.setCreateUser(getCurrentUserName());
		locationRegionService.insert(locationRegion);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/regionSelect")
	public String regionSelect(LocationRegion locationRegion, Model model,PageParameter page) {
		setBaseData(model);
		List<LocationRegion> list = locationRegionService.findAllByPage(locationRegion, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", locationRegion);
		return "qms/base/locationRegion/select";
	}
	
	@RequestMapping("/mesh")
	public ModelAndView mesh(LocationRegion locationRegion) {
		ModelAndView mav = new ModelAndView("/qms/base/locationRegion/mesh");	
		mav.addObject("keys", locationRegion.getKeys());
		return mav;
	} 
	
	@RequestMapping("/saveMesh")
	public void saveMesh(LocationRegion locationRegion, HttpServletResponse response) {
		try {
			if (TmStringUtils.isNotEmpty(locationRegion.getKeys())) {
				String[] meshkey = locationRegion.getKeys().split(",");
				List<String> regionList = new ArrayList<String>();
				for(int i=0;i<meshkey.length;i++){
					regionList.add(new String(meshkey[i].getBytes("ISO8859-1"), "UTF-8"));
				}
				if(regionList.size() != 0) {
					locationRegionService.saveMesh(regionList, locationRegion.getMergeRegion());
				}
			}
			response.getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/breakMesh")
	public void breakMesh(LocationRegion locationRegion, HttpServletResponse response) {
		try {
			if(TmStringUtils.isNotEmpty(locationRegion.getKeys())){
				String[] meshkey = locationRegion.getKeys().split(",");
				List<String> regionList = new ArrayList<String>();
				for(int i=0;i<meshkey.length;i++){
					regionList.add(meshkey[i]);
				}
				if(regionList.size()!=0){
					locationRegionService.breakMesh(regionList);
				}
			}
			response.getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(LocationRegion locationRegion)
	{
		locationRegionService.deleteByPrimaryKey(locationRegion.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	/**
	 *同步mes仓库
	 * @param locationRegion
	 * @return
	 * 
	 */

	@RequestMapping("/synchronous")
	public ModelAndView synchronous(LocationRegion locationRegion){
		
		List<LocationRegion> meslist = locationRegionService.getAllLocation();	//mes仓库
		List<LocationRegion> qmslist = locationRegionService.findAll();			//qms仓库
		Map<String,LocationRegion> map = new HashMap<String,LocationRegion>(); 
		List<LocationRegion> insertlist = new ArrayList<LocationRegion>();  
		List<LocationRegion> updatelist = new ArrayList<LocationRegion>();
		for(LocationRegion qms:qmslist){
			map.put(qms.getLocationCode(), qms);
		}
		for(LocationRegion mes:meslist){
			String code = mes.getLocationCode();
			if(map.containsKey(code)){
				LocationRegion updateVo = map.get(code);
				if(!updateVo.getLocation().equals(mes.getLocation())){
					updateVo.setLocation(mes.getLocation());
					updatelist.add(updateVo);
				}
			}else{
				insertlist.add(mes);
			}
		}
		for(int i=0;i<updatelist.size();i++){
			LocationRegion updatetmp = updatelist.get(i);
			locationRegionService.updateByPrimaryKeySelective(updatetmp);
		}
		
		for(int j=0;j<insertlist.size();j++){
			LocationRegion inserttmp = insertlist.get(j);
			inserttmp.setCreateUser(getCurrentUserName());
			locationRegionService.insert(inserttmp);
		}




		return ajaxDoneSuccess("同步成功");	
		
	}
}
