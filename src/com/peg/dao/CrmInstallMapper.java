package com.peg.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CrmInstall;

public interface CrmInstallMapper {
	List<CrmInstall> findAllByPage(@Param("ins")CrmInstall crmInstall,@Param("page")PageParameter page);
	
	List<CrmInstall> findRecordAllByPage(@Param("ins")CrmInstall crmInstall,@Param("page")PageParameter page);
}
