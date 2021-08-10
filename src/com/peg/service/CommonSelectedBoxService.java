package com.peg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.CommonSelectedBox;

public interface CommonSelectedBoxService {
		
	List<CommonSelectedBox> getCommonArea(String factory);  //车间
	
	List<CommonSelectedBox> getCommonShiftGroup(String factory, String area); //班组
	
	List<CommonSelectedBox> getShiftGroup(String factory); //根据工厂获取班组
	
	List<CommonSelectedBox> getCommonShiftGroupCategory(String factory); //班组类别
	
	List<CommonSelectedBox> getCommonCheckIndex(String area, String factory, String shiftGroupCategory); //考核指标

	List<CommonSelectedBox> getCommonFactory();     //获取工厂
	
	List<CommonSelectedBox> getAreaS();             //获取所有车间
	
	List<CommonSelectedBox> getCategoryS();         //获取所有的班组类别
	
	List<CommonSelectedBox> getGroupS();            //获取所有班组
	
	List<CommonSelectedBox> getCheckItemS();        //获取考核项目
	
	List<CommonSelectedBox> getFacAreaByGroup(@Param("factory")String factory,@Param("group")String group);//跟据班组获取工厂，车间
	
	List<CommonSelectedBox> getCommonGroupCategoryByFacAr(String factory,String area);//根据工厂车间获取班组类别
	
	List<CommonSelectedBox> getCommonCheckIndexByFac(String factory, String area); //根据工厂,车间获得考核指标
	
	List<CommonSelectedBox> getCommonCheckItem(String factory, String area); //根据工厂，车间获取考核项目
	
	List<CommonSelectedBox> getGroupScore(String date, String group,String factory); //获取班组得分
	
	List<CommonSelectedBox> getFixGroupScore(String date,String group,String factory); //获取未设置当月班组基准的班组得分

}
