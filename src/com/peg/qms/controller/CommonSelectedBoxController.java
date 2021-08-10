package com.peg.qms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.model.CommonSelectedBox;
import com.peg.service.CommonSelectedBoxService;


@Controller
@RequestMapping("commonSelected")
public class CommonSelectedBoxController extends BaseController{

	@Autowired
	private CommonSelectedBoxService comSelBoxService;
	
	@RequestMapping("/getArea")
	public String getArea(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		List<CommonSelectedBox> list = comSelBoxService.getCommonArea(factory);
		model.addAttribute("areaList", list);
		return "qms/commonselect/area";
	}
	
	@RequestMapping("/getShiftGroup")
	public String getShiftGourp(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		String area = comBox.getArea();
		List<CommonSelectedBox> list = comSelBoxService.getCommonShiftGroup(factory, area);
		model.addAttribute("shiList", list);
		return "qms/commonselect/shiftGroup";
	}
	
	@RequestMapping("/getShiftGroupByFactory")
	public String getShiftGroupByFactory(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		List<CommonSelectedBox> list = comSelBoxService.getShiftGroup(factory);
		model.addAttribute("shiList", list);
		return "qms/commonselect/shiftGroupByFac";
	}
	
	@RequestMapping("/getShiftCategory")
	public String getShiftCategory(Model model, CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		List<CommonSelectedBox> list = comSelBoxService.getCommonShiftGroupCategory(factory);
		model.addAttribute("shiCaList", list);
		return "qms/commonselect/shiftGroupCategory";
	}
	
	@RequestMapping("/getCheckIndex")
	public String getCheckIndex(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		String area = comBox.getArea();
		String shiftGroupCategory = comBox.getShiftGroupCategory();
		List<CommonSelectedBox> list = comSelBoxService.getCommonCheckIndex(area,factory,shiftGroupCategory);
		model.addAttribute("cheInList", list);
		return "qms/commonselect/checkIndex";
	}
	
	@RequestMapping("/getCheckIndexByFac")
	public String getCheckIndexByFac(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		String area = comBox.getArea();
		List<CommonSelectedBox> list  = comSelBoxService.getCommonCheckIndexByFac(factory, area);
		model.addAttribute("cheInList", list);
		return "qms/commonselect/checkIndexByFac";
	}
	
	@RequestMapping("/getCheckItem")
	public String getCheckItem(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		String area = comBox.getArea();
		List<CommonSelectedBox> list  = comSelBoxService.getCommonCheckItem(factory, area);
		model.addAttribute("cheItemList", list);
		return "qms/commonselect/checkItem";
	}
	
	@RequestMapping("/getGroupCategoryByFacAr")
	public String getgetGroupCategoryByFacAr(Model model,CommonSelectedBox comBox){
		String factory = comBox.getFactory();
		String area = comBox.getArea();
		List<CommonSelectedBox> list = comSelBoxService.getCommonGroupCategoryByFacAr(factory, area);
		model.addAttribute("category", list);
		return "qms/commonselect/GroupCategoryByFacAr";
	}
	
	
}
