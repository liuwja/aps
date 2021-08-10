package com.peg.dao.part;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.WareHouseProductRef;
/**
 *  仓库与机型对应关系
 * @author Administrator
 *
 */
public interface WareHouseProductRefMapper extends BaseMapper<WareHouseProductRef, Long>{

	/**
	 * 根据仓库编号查找机型类别
	 * @return
	 */
	WareHouseProductRef selectByHouseNumber(@Param("houseNumber")String houseNumber);

	/**
	 * 分页查询
	 * @param bs
	 * @return
	 */
	List<WareHouseProductRef> findAllByPage(BaseSearch bs);

}