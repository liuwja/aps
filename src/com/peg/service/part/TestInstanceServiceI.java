package com.peg.service.part;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.echarts.EChartObj;
import com.peg.model.part.TestInstance;

public interface TestInstanceServiceI {

	/**
	 * 获取机型类别
	 * @return
	 */
	List<TestInstance> getProductType();
	
	/**
	 * 获取供应商
	 */
    List<TestInstance> getSupplier();
    
    /**
     * 获取物料大类
     */
    List<TestInstance> getPartType();
    
    /**
     * 分页获取物料
     * @param bs
     * @return
     */
	List<TestInstance> getPartAllByPage(BaseSearch bs);
	
	/**
	 * 根据传入sql,获取查询结果
	 * @param sql
	 * @return
	 */
	List<TestInstance> getTestInstance(@Param("sql") String sql);
	
	/**
	 * 获取星期
	 * @return
	 */
	TestInstance getWeek(String date);

	/**
	 * 获取机型类别
	 * @return
	 */
	Map<String, List<TestInstance>> getMesProductType();

	/**
	 * 获取开始日期，结束日期
	 * @param dateType
	 * @param date
	 * @param bs
	 */
	void getDate(TestInstance test, BaseSearch bs);

	/**
	 * 分页查询
	 * @param page
	 * @param bs
	 * @return
	 */
	List<TestInstance> findAllByPage( BaseSearch bs);

	/**
	 * 转码
	 * @param testInstance
	 * @return
	 */
	TestInstance decode(TestInstance testInstance);

	/**
	 * erp原料退料
	 * @param testInstance
	 * @return
	 * @throws Exception 
	 */
	EChartObj getErpPartReturn(TestInstance testInstance) throws Exception;

	/**
	 * 加载查询条件
	 * @param model
	 */
	void loadData(Model model);

	/**
	 * 趋势图比较月份，如果该月份不存在则加上
	 * @param supList
	 * @param testInstance
	 */
	List<String> compareDate(List<TestInstance> supList, TestInstance testInstance)  throws Exception;

	/**
	 * 传入供应商和物料list
	 * @param supList
	 * @param map
	 */
	List<String> setSupplierAndPart(List<TestInstance> supList, int type);

	/**
	 * 更新testInstance表
	 * @param startTime
	 * @param endTime
	 */
	void updateTestInstance(String startTime, String endTime) throws Exception;

	/**
	 * 获取单个总数
	 * @param supList
	 * @return
	 */
	Map<String,Long> getTotal(List<TestInstance> supList,TestInstance test);

	/**
	 * 获取供应商map
	 * @param supList
	 * @param type
	 * @return
	 */
	Map<String, String> getScodeMap(List<TestInstance> supList, int type);
	
}
