package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.MarketRepairRecordMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MarketRepairRecord;
import com.peg.service.MarketRepairRecordServiceI;
import com.peg.web.util.ConstantInterface;
@Service("marketRepairRecordService")
public class MarketRepairRecordServiceImpl implements MarketRepairRecordServiceI{
	
	@Autowired
	private MarketRepairRecordMapper marketRepairRecordMapper;

	@Override
	public int insert(MarketRepairRecord record) {
		marketRepairRecordMapper.insert(record);
		return 0;
	}

	@Override
	public List<MarketRepairRecord> findAllRepair(PageParameter page,MarketRepairRecord repair) {
		long startRow = (page.getPageNum() - 1) * page.getNumPerPage();
		long endRow = page.getPageNum() * page.getNumPerPage();
		int total = marketRepairRecordMapper.getTotalNumber(repair);
        page.setTotalCount(total);
        int totalPage = total / page.getNumPerPage() + ((total % page.getNumPerPage() == 0) ? 0 : 1);
        page.setTotalPage(totalPage);
		return marketRepairRecordMapper.findAllRepair(page, repair, startRow, endRow);
	}

	@Override
	public List<MarketRepairRecord> findAll(MarketRepairRecord repair) {
		return marketRepairRecordMapper.findAll(repair);
	}

	@Override
	public int updateByPrimaryKeySelective(MarketRepairRecord record) {
		return marketRepairRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public MarketRepairRecord selectByPrimaryKey(Long id) {
		return marketRepairRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return marketRepairRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int getTotalNumber(MarketRepairRecord repair) {
		return marketRepairRecordMapper.getTotalNumber(repair);
	}

	@Override
	public List<MarketRepairRecord> findAllRepairIntermediate(PageParameter page, MarketRepairRecord repair, String isPage) {
		if (ConstantInterface.YES.equals(isPage)) {
	        return marketRepairRecordMapper.findAllRepairIntermediatePage(page, repair);
		} else {
			return marketRepairRecordMapper.findAllRepairIntermediate(repair);
		}
	}
}
