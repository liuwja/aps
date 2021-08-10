package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.bph.IpqcInspects;


public interface IpqcInspectsMapper {
	List<IpqcInspects> getIpqcInspectsAllByPage(BaseSearch bs);

	List<IpqcInspects> getIpqcInspects(IpqcInspects ipqcInspects);

	/**
	 * 按班组查询IPQC不良现象
	 * @param vo
	 * @return 
	 */
	List<CommonVo> getIpqcDefetctType(CommonVo vo);
     
	/**
	 * 按班组查询巡检批次不良率得分情况
	 * @param vo
	 * @return
	 */
	List<CommonVo> getIpqcScore(CommonVo vo);
}