package com.peg.service;

import java.util.List;

import com.peg.model.Area;

public interface AreaServiceI {
	
	    Area selectByPrimaryKey(Area key);

	    List<Area> selectByFactory(String factory);

}
