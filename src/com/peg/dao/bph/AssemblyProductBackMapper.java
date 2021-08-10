package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.AssemblyProductBack;


public interface AssemblyProductBackMapper {
   
	List<AssemblyProductBack> getAssemblyAllByPage(BaseSearch bs);
	
	
	List<AssemblyProductBack> getAssembleRepariedByPage(BaseSearch bs);


	List<AssemblyProductBack> getAssemblyList(
			AssemblyProductBack assemblyProductBack);


	List<AssemblyProductBack> getIpqcInspects(
			AssemblyProductBack assemblyProductBack);
	
	List<EntityClass> getEntityClassbad(@Param("entity")AssemblyProductBack assemblyProductBack);
	
	List<EntityClass> getEntityTypebad(@Param("entity")AssemblyProductBack assemblyProductBack);
	
	List<EntityClass> getEntityClassarrange(@Param("entity")AssemblyProductBack assemblyProductBack);
	
	List<EntityClass> getEntityClassarrangetwo(@Param("entity")AssemblyProductBack assemblyProductBack);
	
	
}