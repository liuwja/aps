package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MissData;

public interface MissDataMapper {
	
	List<MissData> getMissShipDataPage(BaseSearch bs);
	
	List<MissData> getMissInstallDataPage(BaseSearch bs);
	
	List<MissData> getMissRepairDataPage(BaseSearch bs);
	
	int getMissShipDataTotalNumber(MissData vo);
	
	int getMissInstallDataTotalNumber(MissData vo);
	
	int getMissRepairDataTotalNumber(MissData vo);
	
	List<MissData> getAllMissShipData(MissData bs);
	
	List<MissData> getAllMissInstallData(MissData bs);
	
	List<MissData> getAllMissRepairData(MissData bs);
	
	List<MissData> getMissPartDataPage(BaseSearch bs);
	
	List<MissData> getMissPartDataByProductPage(BaseSearch bs);
	
	List<MissData> getMissPartData(MissData vo);
	
	List<MissData> getMissPartDataByProduct(MissData vo);
	
	int getMissPartDataTotalNumber(MissData vo);
	
	List<MissData> getMissSupplierDataPage(BaseSearch bs);
	
	List<MissData> getMissSupplierData(MissData bs);
	
	int getMissSupplierDataTotalNumber(MissData vo);

	List<MissData> selectMissDatabyPart(@Param("partNumber")String partNumber);

	int updateMissDatabyPart(MissData vo);
}
