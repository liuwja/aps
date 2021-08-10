package com.peg.dao;

import java.util.List;

import com.peg.model.Area;


public interface AreaMapper {
 
    Area selectByPrimaryKey(Area key);

    List<Area> selectByFactory(String factory);
    
}