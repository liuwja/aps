package com.peg.service.part.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.peg.service.part.IDataImportService;


@Service("dataImportService")
public class DataImportServiceImpl implements IDataImportService {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * 运算锁
	 */
	static boolean STAMPING_DISPATCH_LOCK = false;
	
	synchronized boolean isLock()
	{
		return STAMPING_DISPATCH_LOCK;
	}

	synchronized void lock()
	{
		STAMPING_DISPATCH_LOCK = true;
	}

	synchronized void unLock()
	{
		STAMPING_DISPATCH_LOCK = false;
	}

	@Override
	public int insertData(List<String[]> sheetData) throws Exception {
		
		return 0;
	}
	
	
	

	
}
