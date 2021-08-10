package com.peg.qms.controller.smt;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.SupplierRef;
import com.peg.model.part.OnlineModel;
import com.peg.model.smt.PFSAPANAMvData;
import com.peg.model.smt.PFSAPANAScrapData;
import com.peg.model.smt.PFSAPANATraceData;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.smt.SmtServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * SMT
 * @createTime 2019-06-11 16:03
 */
@Controller
@RequestMapping("smt")
public class SmtController extends BaseController {

    @Autowired
    private CommonServiceI commonService;

    @Autowired
    private SmtServiceI smtService;

    /**
     * 上料记录
     */
    @RequestMapping("/PFSAPANAMvData/list")
    public String list(Model model, CommonVo cvo, PageParameter page, PFSAPANAMvData mv) {
        ConditionUtil.loadPlineName(model, cvo, commonService);
        List<PFSAPANAMvData> list = smtService.getMvDataAllByPage(page, mv);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("mv", mv);
        model.addAttribute("cvo", cvo);
        return "qms/smt/PFSAPANAMvData/list";
    }

    /**
     * 物料追溯
     */
    @RequestMapping("/PFSAPANATraceData/list")
    public String list(Model model, CommonVo cvo, PageParameter page, PFSAPANATraceData trace) {
        ConditionUtil.loadPlineName(model, cvo, commonService);
        List<PFSAPANATraceData> list = smtService.getTraceDataAllByPage(page, trace);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("trace", trace);
        model.addAttribute("cvo",cvo);
        return "qms/smt/PFSAPANATraceData/list";
    }

    /**
     * 抛料率
     */
    @RequestMapping("/PFSAPANAScrapData/list")
    public String list(Model model, CommonVo cvo, PageParameter page, PFSAPANAScrapData scrap) {
        ConditionUtil.loadPlineName(model, cvo, commonService);
        List<PFSAPANAScrapData> list = smtService.getScrapDataAllByPage(model, page, scrap);
        model.addAttribute("list", list);
        model.addAttribute("scrap", scrap);
        model.addAttribute("cvo",cvo);
        return "qms/smt/PFSAPANAScrapData/list";
    }

    /**
     * 导出Excel上料记录
     */
    @RequestMapping("/exportExcelPFSAPANAMvData")
    public String exportExcelPFSAPANAMvData(HttpServletRequest request, HttpServletResponse response,
                                            PageParameter page, PFSAPANAMvData mv) {
        try {
            smtService.exportExcelPFSAPANAMvData(request, response, page, mv);
            return null;
        } catch (Exception e) {
            logger.error(e);
        }
        return "/error/err";
    }

    /**
     * 导出Excel物料追溯
     */
    @RequestMapping("/exportExcelPFSAPANATraceData")
    public String exportExcelPFSAPANATraceData(HttpServletRequest request, HttpServletResponse response,
                                               PageParameter page, PFSAPANATraceData trace) {
        try {
            smtService.exportExcelPFSAPANATraceData(request, response, page, trace);
            return null;
        } catch (Exception e) {
            logger.error(e);
        }
        return "/error/err";
    }

    /**
     * 导出Excel抛料率
     */
    @RequestMapping("/exportExcelPFSAPANAScrapData")
    public String exportExcelPFSAPANAScrapData(HttpServletRequest request, HttpServletResponse response,
                                               PageParameter page, PFSAPANAScrapData scrap) {
        try {
            smtService.exportExcelPFSAPANAScrapData(request, response, page, scrap);
            return null;
        } catch (Exception e) {
            logger.error(e);
        }
        return "/error/err";
    }
}
