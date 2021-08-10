package com.peg.dao.smt;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.smt.PFSAPANAMvData;
import com.peg.model.smt.PFSAPANAScrapData;
import com.peg.model.smt.PFSAPANATraceData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SMT
 * @createTime 2019-06-04 15:08
 */
public interface SmtMapper {

    /**
     * 新增上料记录
     * @param mvs
     * @return
     */
    int insertMvData(List<PFSAPANAMvData> mvs);

    /**
     * 新增物料追溯
     * @param traces
     * @return
     */
    int insertTraceData(List<PFSAPANATraceData> traces);

    /**
     * 新增抛料率
     * @param scraps
     * @return
     */
    int insertScrapData(List<PFSAPANAScrapData> scraps);

    /**
     * 查询上料记录
     * @param page
     * @param mv
     * @return
     */
    List<PFSAPANAMvData> getMvDataAllByPage(@Param("page")PageParameter page, @Param("mv")PFSAPANAMvData mv);
    
    /**
     * 导出上料记录
     * @param page
     * @param mv
     * @return
     */
    List<PFSAPANAMvData> getMvDataAll(@Param("mv")PFSAPANAMvData mv);

    /**
     * 查询物料追溯
     * @param page
     * @param trace
     * @return
     */
    List<PFSAPANATraceData> getTraceDataAllByPage(@Param("page")PageParameter page, @Param("trace")PFSAPANATraceData trace);

    /**
     * 查询物料追溯
     * @param page
     * @param trace
     * @return
     */
    List<PFSAPANATraceData> getTraceDataAll(@Param("trace")PFSAPANATraceData trace);

    /**
     * 查询抛料率
     * @param page
     * @param scrap
     * @return
     */
    List<PFSAPANAScrapData> getScrapDataAllByPage(@Param("page")PageParameter page, @Param("scrap")PFSAPANAScrapData scrap);

    /**
     * 导出抛料率
     * @param page
     * @param scrap
     * @return
     */
    List<PFSAPANAScrapData> getScrapDataAll(@Param("scrap")PFSAPANAScrapData scrap);

}
