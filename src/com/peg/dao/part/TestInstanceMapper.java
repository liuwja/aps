package com.peg.dao.part;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.part.PartCategory;
import com.peg.model.part.TestInstance;

public interface TestInstanceMapper {
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
	 * 获取周
	 * @param date
	 * @return
	 */
	TestInstance getWeek(@Param("date")String date);
	
	/**
	 * 分页查询供应商
	 * @param bs
	 * @return
	 */
	List<TestInstance> getSupplierByPage(BaseSearch bs);
	
	/**
	 * 获取mes机型类别
	 * @return
	 */
	List<TestInstance> getMesProductType();
	
	/**
	 * 分页查询
	 * @param bs
	 * @return
	 */
	List<TestInstance> findAllByPage(BaseSearch bs);

	/**
	 * erp原料退料
	 * @param testInstance
	 * @return
	 */
	List<TestInstance> getErpPartReturn(@Param("test")TestInstance testInstance);

	/**
	 * 获取上下控制线不良数，总数
	 * @param testInstance
	 * @return
	 */
	TestInstance getTotalQty(@Param("test")TestInstance testInstance);

	/**
	 * 查询物料类别
	 * @param bs
	 * @return
	 */
	List<TestInstance> getPartTypeByPage(BaseSearch bs);

	/**
	 * 根据期间删除
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int deleteTestInstanceByPeriod(@Param("startTime")String startTime, @Param("endTime")String endTime);

	/**
	 * 根据期间插入
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int insertTestInstanceByPeriod(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	/**
	 * 获取期间不良总数，不良数
	 * @param testInstance
	 * @return
	 */
	TestInstance getPeriodTotalQty(@Param("test")TestInstance testInstance);

	/**
	 * 获取物料二级分类
	 * @param bs
	 * @return
	 */
	List<PartCategory> getPartCategoryByPage(BaseSearch bs);

	/**
	 * 获取物料一级分类
	 * @return
	 */
	List<PartCategory> getPartCategoryOne();

}
