package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysGroup;
import com.peg.model.system.SysMesUser;
import com.peg.service.BaseService;

public interface SysMesUserServiceI extends BaseService<SysMesUser, Long>{
    
    List<SysMesUser> getAllByPage(PageParameter page,String userName, String name);
    
    SysMesUser selectByUsername(String username);
    
    boolean findUsersByGroupKey(List<SysGroup> list);
    List<SysMesUser> findUsersByGroupKey(long groupKey);
    void mergeUser();
}
