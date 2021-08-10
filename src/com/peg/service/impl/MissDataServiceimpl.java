package com.peg.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.MissDataMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MissData;
import com.peg.service.MissDataServiceI;

@Service("MissDataService")
public class MissDataServiceimpl implements MissDataServiceI {
	
	@Autowired
	private MissDataMapper missDataMapper;
	
	@Override
	public List<MissData> getMissShipDataPage(BaseSearch bs) {
		return missDataMapper.getMissShipDataPage(bs);
	}

	@Override
	public List<MissData> getMissInstallDataPage(BaseSearch bs) {
		return missDataMapper.getMissInstallDataPage(bs);
	}

	@Override
	public List<MissData> getMissRepairDataPage(BaseSearch bs) {
		return missDataMapper.getMissRepairDataPage(bs);
	}
	
	@Override
	public int getMissShipDataTotalNumber(MissData vo) {
		return missDataMapper.getMissShipDataTotalNumber(vo);
	}
	
	@Override
	public int getMissInstallDataTotalNumber(MissData vo) {
		return missDataMapper.getMissInstallDataTotalNumber(vo);
	}
	
	@Override
	public int getMissRepairDataTotalNumber(MissData vo) {
		return missDataMapper.getMissRepairDataTotalNumber(vo);
	}
	
	@Override
	public List<MissData> getAllMissShipData(MissData bs) {
		return missDataMapper.getAllMissShipData(bs);
	}
	
	@Override
	public List<MissData> getAllMissInstallData(MissData bs) {
		return missDataMapper.getAllMissInstallData(bs);
	}
	
	@Override
	public List<MissData> getAllMissRepairData(MissData bs) {
		return missDataMapper.getAllMissRepairData(bs);
	}
	
	@Override
	public List<MissData> getMissPartDataPage(BaseSearch bs) {
		return missDataMapper.getMissPartDataPage(bs);
	}
	
	@Override
	public List<MissData> getMissPartDataByProductPage(BaseSearch bs, MissData vo) {
		String sql = "";
		Integer type = 0;
		if(StringUtils.isNotEmpty(vo.getShowProductType()) && vo.getShowProductType().equals("1")) {
			sql = "and UP.MOLD_TYPE_S IS NULL";
			type = 1;
		}
		if (StringUtils.isNotEmpty(vo.getShowPartFamily()) && vo.getShowPartFamily().equals("1")) {
			if (type == 0) {
				sql = "and UP.PRODUCT_FAMILY_S IS NULL";
				type = 2;
			} else if (type == 1) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_FAMILY_S IS NULL)";
				type = 3;
			}
		}
		if (StringUtils.isNotEmpty(vo.getShowPartType()) && vo.getShowPartType().equals("1")) {
			if (type == 0) {
				sql = "and UP.PRODUCT_TYPE_S IS NULL";
			} else if (type == 1) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			} else if (type == 2) {
				sql = "and (UP.PRODUCT_FAMILY_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			} else if (type == 3) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_FAMILY_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			}
		}
		bs.put("sql", sql);
		return missDataMapper.getMissPartDataByProductPage(bs);
	}
	
	@Override
	public List<MissData> getMissPartData(MissData vo) {
		return missDataMapper.getMissPartData(vo);
	}
	
	@Override
	public List<MissData> getMissPartDataByProduct(MissData vo) {
		String sql = "";
		Integer type = 0;
		if(StringUtils.isNotEmpty(vo.getShowProductType()) && vo.getShowProductType().equals("1")) {
			sql = "and UP.MOLD_TYPE_S IS NULL";
			type = 1;
		}
		if (StringUtils.isNotEmpty(vo.getShowPartFamily()) && vo.getShowPartFamily().equals("1")) {
			if (type == 0) {
				sql = "and UP.PRODUCT_FAMILY_S IS NULL";
				type = 2;
			} else if (type == 1) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_FAMILY_S IS NULL)";
				type = 3;
			}
		}
		if (StringUtils.isNotEmpty(vo.getShowPartType()) && vo.getShowPartType().equals("1")) {
			if (type == 0) {
				sql = "and UP.PRODUCT_TYPE_S IS NULL";
			} else if (type == 1) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			} else if (type == 2) {
				sql = "and (UP.PRODUCT_FAMILY_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			} else if (type == 3) {
				sql = "and (UP.MOLD_TYPE_S IS NULL OR UP.PRODUCT_FAMILY_S IS NULL OR UP.PRODUCT_TYPE_S IS NULL)";
			}
		}
		vo.setSql(sql);
		return missDataMapper.getMissPartDataByProduct(vo);
	}
	
	@Override
	public int getMissPartDataTotalNumber(MissData vo) {
		return missDataMapper.getMissPartDataTotalNumber(vo);
	}

	@Override
	public List<MissData> getMissSupplierDataPage(BaseSearch bs) {
		return missDataMapper.getMissSupplierDataPage(bs);
	}
	
	@Override
	public List<MissData> getMissSupplierData(MissData bs) {
		return missDataMapper.getMissSupplierData(bs);
	}
	
	@Override
	public int getMissSupplierDataTotalNumber(MissData vo) {
		return missDataMapper.getMissSupplierDataTotalNumber(vo);
	}

	@Override
	public List<MissData> selectMissDatabyPart(String partNumber) {
		// TODO Auto-generated method stub
		return missDataMapper.selectMissDatabyPart(partNumber);
	}

	@Override
	public int updateMissDatabyPart(MissData vo) {
		// TODO Auto-generated method stub
		return missDataMapper.updateMissDatabyPart(vo);
	}
}
