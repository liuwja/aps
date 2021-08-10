package com.peg.service;

import com.peg.model.LaterSumtime;

public interface LaterSumtimeServiceI extends BaseService<LaterSumtime, Long>{
	
	public LaterSumtime getLaterDate();
	
	public void updateToLater(String sumMonth);
	
}
