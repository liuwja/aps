package com.peg.dao;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Relation;

public interface RelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

	List<Relation> getAllByPage(BaseSearch bs);

	List<Relation> getAll();
}