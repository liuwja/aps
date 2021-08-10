package com.peg.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.CommonMapper;
import com.peg.dao.bph.IndexScroeMapper;
import com.peg.dao.bph.ItemScoreMapper;
import com.peg.dao.bph.MesDataSumMapper;
import com.peg.dao.bph.MonthlyAssessmentMapper;
import com.peg.dao.bph.OnlinelookupMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.ProductionLine;
import com.peg.model.bph.Onlinelookup;
import com.peg.service.CommonServiceI;
import com.peg.service.bph.ComputeScoreServiceI;
import com.peg.service.bph.CountPerformanceMonthServiceI;

/**
 * 用于公共查询或操作
 * @author song
 *
 */
@Service("commonService")
public class CommonServiceImpl implements CommonServiceI{

	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private MesDataSumMapper mesDataSumMapper;
	
	@Autowired
	private IndexScroeMapper indexScroeMapper;
	
	@Autowired 
	private ItemScoreMapper itemScoreMapper;
	
	@Autowired
	private ComputeScoreServiceI computeScoreService;
	
	@Autowired
	private MonthlyAssessmentMapper monthlyAssessmentMapper;
	
	@Autowired
	private CountPerformanceMonthServiceI countPerformanceMonthService;
	
	//@Autowired
	@Resource
	private OnlinelookupMapper online;

	@Override
	public List<CommonVo> getReCountGroupByRegion(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByRegion(vo);
	}

	@Override
	public List<CommonVo> getShipCountGroupByRegion(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getShipCountGroupByRegion(vo);
	}
	
	@Override
	public List<CommonVo> getReCountGroupByProvince(CommonVo vo) {
		return commonMapper.getReCountGroupByProvince(vo);
	}
	
	@Override
	public List<CommonVo> getShipCountGroupByProvince(CommonVo vo) {
		return commonMapper.getShipCountGroupByProvince(vo);
	}
	
	@Override
	public List<CommonVo> getReCountGroupByPartType(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByPartType(vo);
	}
	@Override
	public List<CommonVo> getReCountGroupByProdFamily(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByProdFamily(vo);
	}

	@Override
	public List<CommonVo> getReCountGroupByReMon(BaseSearch bs) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByReMon(bs);
	}
	
	@Override
	public List<CommonVo> getShipCountGroupByPartType(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getShipCountGroupByPartType(vo);
	}
	
	@Override
	public List<CommonVo> getShipCountGroupByProdFamily(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getShipCountGroupByProdFamily(vo);
	}
	
	@Override
	public int getShipCount(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getShipCount(vo);
	}

	@Override
	public List<CommonVo> getReCountGroupByPline(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByPline(vo);
	}

	@Override
	public List<CommonVo> getShipCountGroupByPline(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getShipCountGroupByPline(vo);
	}

	@Override
	public List<CommonVo> getReCountGroupFaultType(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupFaultType(vo);
	}
	@Override
	public List<CommonVo> getReCountGroupFaultReason(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupFaultReason(vo);
	}
	@Override
	public List<CommonVo> getReCountGroupFaultsReason(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupFaultsReason(vo);
	}

	/* (non-Javadoc)
	 * @see com.peg.service.CommonServiceI#getReCountGroupByProdType(com.peg.model.CommonVo)
	 */
	@Override
	public List<CommonVo> getReCountGroupByProdType(CommonVo vo)
	{
		return commonMapper.getReCountGroupByProdType(vo);
	}

	/* (non-Javadoc)
	 * @see com.peg.service.CommonServiceI#getShipCountGroupByProdType(com.peg.model.CommonVo)
	 */
	@Override
	public List<CommonVo> getShipCountGroupByProdType(CommonVo vo)
	{
		return commonMapper.getShipCountGroupByProdType(vo);
	}

	@Override
	public List<CommonVo> getDownLineCountGroupByMonth(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getDownLineCountGroupByMonth(vo);
	}

	@Override
	public List<CommonVo> getReCountGroupByReDlMonth(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getReCountGroupByReDlMonth(vo);
	}
	
	@Override
	public List<CommonVo> getInsCountGroupByMonth(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getInsCountGroupByMonth(vo);
	}
	
	@Override
	public List<CommonVo> getInsCountGroupByReMonth(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getInsCountGroupByReMonth(vo);
	}


	@Override
	public List<CommonVo> getPartTypeFromMes(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getPartTypeFromMes(vo);
	}
	
	@Override
	public List<CommonVo> getProductFamilyFromMes(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getProductFamilyFromMes(vo);
	}

	@Override
	public List<CommonVo> getMergeRegion(CommonVo vo) {
		// TODO Auto-generated method stub
		return commonMapper.getMergeRegion(vo);
	}

	@Override
	public List<CommonVo> getRegion(CommonVo vo) {
		return commonMapper.getRegion(vo);
	}
	
	@Override
	public List<ProductionLine> getLines(ProductionLine vo) {
		// TODO Auto-generated method stub
		return commonMapper.getLines(vo);
	}

	@Override
	public List<ProductionLine> getFactorys(ProductionLine vo) {
		// TODO Auto-generated method stub
		return commonMapper.getFactorys(vo);
	}
	
	@Override
	public List<ProductionLine> getFactorysBySite(ProductionLine vo) {
		return commonMapper.getFactorysBySite(vo);
	}
	
	@Override
	public List<CommonVo> getFaultCodeTotal(BaseSearch bs) {
		// TODO Auto-generated method stub
		return commonMapper.getFaultCodeTotal(bs);
	}

	@Override
	public List<CommonVo> getRepairTotalByPages(BaseSearch bs) {
		// TODO Auto-generated method stub
		return commonMapper.getRepairTotalByPages(bs);
	}

	@Override
	public List<Onlinelookup> getPartpages(BaseSearch bs) {
		return online.getpartPage(bs);
	}

	@Override
	public List<Onlinelookup> getPartTwopages(BaseSearch bs) {
		return online.getparttwoPage(bs);
	}

	@Override
	public List<Onlinelookup> getAccountpages(BaseSearch bs) {
		return online.getAccountPage(bs);
	}

	@Override
	public List<String> getFactory() {
		return online.getFactoryDao();
	}

	@Override
	public List<String> gettype(String factory) {
		return online.getTypeDao(factory);
	}

	@Override
	public List<CommonVo> getPartTypeListFromMes(CommonVo vo) {
		return commonMapper.getPartTypeListFromMes(vo);
	}

	@Override
	public List<CommonVo> getInsCountGroupByPartType(CommonVo vo) {
		return commonMapper.getInsCountGroupByPartType(vo);
	}
	
	@Override
	public List<CommonVo> getInsCountGroupByRePartType(CommonVo vo) {
		return commonMapper.getInsCountGroupByRePartType(vo);
	}
	
	@Override
	public List<CommonVo> getInsCountGroupByRegion(CommonVo vo) {
		return commonMapper.getInsCountGroupByRegion(vo);
	}
	
	@Override
	public List<CommonVo> getInsCountGroupByReRegion(CommonVo vo) {
		return commonMapper.getInsCountGroupByReRegion(vo);
	}

	@Override
	public List<CommonVo> getInsCountGroupByMonthAndInterval(CommonVo vo) {
		return commonMapper.getInsCountGroupByMonthAndInterval(vo);
	}

	@Override
	public Long getAllInsCountGroup(CommonVo vo) {
		return commonMapper.getAllInsCountGroup(vo);
	}

	@Override
	public List<CommonVo> getVoiceCategory(CommonVo vo) {
		if(vo.getProductType().equals("洗碗机")) vo.setProductType("水槽洗碗机");
		return commonMapper.getVoiceCategory(vo);
	}
}
