package com.peg.service.smt;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.smt.PFSAPANAMvData;
import com.peg.model.smt.PFSAPANAScrapData;
import com.peg.model.smt.PFSAPANATraceData;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SMT
 * @createTime 2019-06-10 13:02
 */
public interface SmtServiceI {

    /**
     * 新增上料记录
     * @param mvs
     * @return
     */
    int insertMvData(List<PFSAPANAMvData> mvs);

    /**
     * 新增物料追溯
     * @param scraps
     * @return
     */
    int insertScrapData(List<PFSAPANAScrapData> scraps);

    /**
     * 新增抛料率
     * @param traces
     * @return
     */
    int insertTraceData(List<PFSAPANATraceData> traces);

    /**
     * 定时新增上料记录
     * @throws Exception
     */
    void insertPFSAPANAMvData() throws Exception;

    /**
     * 定时新增物料追溯
     * @throws Exception
     */
    void insertPFSAPANATraceData() throws Exception;

    /**
     * 定时新增抛料率
     * @throws Exception
     */
    void insertPFSAPANAScrapData() throws Exception;

    /**
     * 查询上料记录
     * @param page
     * @param mv
     * @return
     */
    List<PFSAPANAMvData> getMvDataAllByPage(PageParameter page, PFSAPANAMvData mv);

    /**
     * 查询物料追溯
     * @param page
     * @param trace
     * @return
     */
    List<PFSAPANATraceData> getTraceDataAllByPage(PageParameter page, PFSAPANATraceData trace);

    /**
     * 查询抛料率
     * @param page
     * @param scrap
     * @return
     */
    List<PFSAPANAScrapData> getScrapDataAllByPage(Model model, PageParameter page, PFSAPANAScrapData scrap);

    /**
     * 导出Excel上料记录
     */
    void exportExcelPFSAPANAMvData(HttpServletRequest request, HttpServletResponse response, PageParameter page,
                                     PFSAPANAMvData mv) throws Exception;

    /**
     * 导出Excel物料追溯
     */
    void exportExcelPFSAPANATraceData(HttpServletRequest request, HttpServletResponse response, PageParameter page,
                                        PFSAPANATraceData trace) throws Exception;

    /**
     * 导出Excel抛料率
     */
    void exportExcelPFSAPANAScrapData(HttpServletRequest request, HttpServletResponse response, PageParameter page,
                                        PFSAPANAScrapData scrap) throws Exception;
}
