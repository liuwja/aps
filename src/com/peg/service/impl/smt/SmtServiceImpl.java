package com.peg.service.impl.smt;

import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.smt.SmtMapper;
import com.peg.model.smt.PFSAPANAMvData;
import com.peg.model.smt.PFSAPANAScrapData;
import com.peg.model.smt.PFSAPANATraceData;
import com.peg.service.smt.SmtServiceI;
import com.peg.web.util.DBConnectionUtil;
import com.peg.web.util.ExcelUtilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * SMT
 * @createTime 2019-06-10 13:03
 */
@Service("smtService")
public class SmtServiceImpl implements SmtServiceI {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private SmtMapper smtMapper;

    @Override
    public int insertMvData(List<PFSAPANAMvData> mvs) {
        return smtMapper.insertMvData(mvs);
    }

    @Override
    public int insertTraceData(List<PFSAPANATraceData> traces) {
        return smtMapper.insertTraceData(traces);
    }

    @Override
    public int insertScrapData(List<PFSAPANAScrapData> scraps) {
        return smtMapper.insertScrapData(scraps);
    }

    @Override
    public void insertPFSAPANAMvData() throws Exception {
        String sql = "SELECT * FROM pfsa_pana_mv_data WHERE CONVERT(VARCHAR(20), DATEADD(SECOND, partTimeOn, '1970-01-01 08:00:00'), 120) "
                + ">= CONVERT(varchar, dateadd(hh, -2, getdate()), 120) "
                + "AND CONVERT(VARCHAR(20), DATEADD(SECOND, partTimeOn, '1970-01-01 08:00:00'), 120) < getdate() ";
        System.out.println(sql);
        List<PFSAPANAMvData> list = DBConnectionUtil.executeList(PFSAPANAMvData.class, sql);
        if (list != null && list.size() > 0) {
            int count = insertMvData(list);
            logger.info("pfsa_pana_mv_data(上料记录)表成功插入" + count + "条数据");
        }
    }

    @Override
    public void insertPFSAPANATraceData() throws Exception {
        String sql = "SELECT * FROM pfsa_pana_trace_data WHERE BoardEntryTime >= CONVERT(varchar, dateadd(hh, -2, getdate()), 120) "
                + "and boardEntryTime < getdate()";
        List<PFSAPANATraceData> list = DBConnectionUtil.executeList(PFSAPANATraceData.class, sql);
        if (list != null && list.size() > 0) {
            int batchNum = 500;
            while (list.size() > batchNum) {
                List<PFSAPANATraceData> subList = list.subList(0, batchNum);
                int count = insertTraceData(subList);
                logger.info("pfsa_pana_trace_data(物料追溯)表成功插入" + count + "条数据");
                list.subList(0, batchNum).clear();
            }
            if (list.size() > 0) {
                int count = insertTraceData(list);
                logger.info("pfsa_pana_trace_data(物料追溯)表成功插入" + count + "条数据");
            }
        }
    }

    @Override
    public void insertPFSAPANAScrapData() throws Exception {
        String sql = "SELECT * FROM pfsa_pana_scrap_data WHERE CONVERT(VARCHAR(20), DATEADD(SECOND, starttime, '1970-01-01 08:00:00'), 120) "
                + ">= CONVERT(varchar, dateadd(hh, -2, getdate()), 120) "
                + "AND CONVERT(VARCHAR(20), DATEADD(SECOND, starttime, '1970-01-01 08:00:00'), 120) < getdate() ";
        List<PFSAPANAScrapData> list = DBConnectionUtil.executeList(PFSAPANAScrapData.class, sql);
        if (list != null && list.size() > 0) {
            int count = insertScrapData(list);
            logger.info("pfsa_pana_scrap_data(抛料率)表成功插入" + count + "条数据");
        }
    }

    @Override
    public List<PFSAPANAMvData> getMvDataAllByPage(PageParameter page, PFSAPANAMvData mv) {
        return smtMapper.getMvDataAllByPage(page, mv);
    }

    @Override
    public List<PFSAPANATraceData> getTraceDataAllByPage(PageParameter page, PFSAPANATraceData trace) {
        return smtMapper.getTraceDataAllByPage(page, trace);
    }

    @Override
    public List<PFSAPANAScrapData> getScrapDataAllByPage(Model model, PageParameter page, PFSAPANAScrapData scrap) {
        List<PFSAPANAScrapData> list = smtMapper.getScrapDataAllByPage(page, scrap);
        int totalPickupCount = 0;   // 吸着总数
        int totalScrap = 0; // 抛料数量
        for (PFSAPANAScrapData scrapData : list) {
            totalPickupCount += scrapData.getPickupCount();
            totalScrap += scrapData.getScrap();
        }
        // 抛料率 = (抛料数量 + 吸着总数) / 抛料数量 * 100% (保留2位小数)
        float throwMaterialRate = 0f;
        if (totalScrap > 0) {
            BigDecimal bigDecimal = new BigDecimal((totalScrap + totalPickupCount) / totalScrap * 100);
            throwMaterialRate = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }

        model.addAttribute("totalPickupCount", totalPickupCount);
        model.addAttribute("totalScrap", totalScrap);
        model.addAttribute("throwMaterialRate", throwMaterialRate);
        model.addAttribute("page", page);
        return list;
    }

    @Override
    public void exportExcelPFSAPANAMvData(HttpServletRequest request, HttpServletResponse response, PageParameter
            page, PFSAPANAMvData mv) throws Exception {

        List<String[]> excelList = new ArrayList<String[]>();
        List<PFSAPANAMvData> list = smtMapper.getMvDataAll(mv);
        String[] CN = new String[] {"lineName", "zoneName", "cellName", "lane", "stage", "zNum", "slotNum", "subSlotNum",
                "partNum", "lotNum", "vendor", "feeder", "partTimeOn"};

        for (PFSAPANAMvData temp : list) {
            String[] tempStr = new String[CN.length];
            int index = 0;
            tempStr[index++] = temp.getLineName();
            tempStr[index++] = temp.getZoneName();
            tempStr[index++] = temp.getCellName();
            tempStr[index++] = temp.getLane();
            tempStr[index++] = temp.getStage();
            tempStr[index++] = temp.getzNum().toString();
            tempStr[index++] = temp.getSlotNum().toString();
            tempStr[index++] = temp.getSubSlotNum().toString();
            tempStr[index++] = temp.getPartNum();
            tempStr[index++] = temp.getLotNum();
            tempStr[index++] = temp.getVendor();
            tempStr[index++] = temp.getFeeder();
            tempStr[index] = temp.getPartTimeOnStr();
            excelList.add(tempStr);
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
        String filePath = rootPath + "/" + fname;
        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
        String contentType = "application/msexcel";
        ExcelUtilities.downloadExcel(request, response, filePath, contentType, "上料记录" + fname);
    }

    @Override
    public void exportExcelPFSAPANATraceData(HttpServletRequest request, HttpServletResponse response, PageParameter
            page, PFSAPANATraceData trace) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String[]> excelList = new ArrayList<String[]>();
        List<PFSAPANATraceData> list = smtMapper.getTraceDataAll(trace);
        String[] CN = new String[] {"lineName", "cellName", "pcbName", "panelBarCode", "side", "zNum", "slotNum",
                "subSlotNum", "partNum", "lotNum", "vendor", "feeder", "quantity", "boardEntryTime", "releaseTime",
                "mountOperator", "userData"};

        for (PFSAPANATraceData temp : list) {
            String[] tempStr = new String[CN.length];
            int index = 0;
            tempStr[index++] = temp.getLineName();
            tempStr[index++] = temp.getCellName();
            tempStr[index++] = temp.getPcbName();
            tempStr[index++] = temp.getPanelBarCode();
            tempStr[index++] = temp.getSide();
            tempStr[index++] = temp.getzNum().toString();
            tempStr[index++] = temp.getSlotNum().toString();
            tempStr[index++] = temp.getSubSlotNum().toString();
            tempStr[index++] = temp.getPartNum();
            tempStr[index++] = temp.getLotNum();
            tempStr[index++] = temp.getVendor();
            tempStr[index++] = temp.getFeeder();
            tempStr[index++] = String.valueOf(temp.getQuantity());
            tempStr[index++] = sdf.format(temp.getBoardEntryTime());
            tempStr[index++] = sdf.format(temp.getReleaseTime());
            tempStr[index++] = temp.getMountOperator();
            tempStr[index] = temp.getUserData();
            excelList.add(tempStr);
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
        String filePath = rootPath + "/" + fname;
        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
        String contentType = "application/msexcel";
        ExcelUtilities.downloadExcel(request, response, filePath, contentType, "物料追溯" + fname);
    }

    @Override
    public void exportExcelPFSAPANAScrapData(HttpServletRequest request, HttpServletResponse response, PageParameter
            page, PFSAPANAScrapData scrap) throws Exception {

        List<String[]> excelList = new ArrayList<String[]>();
        List<PFSAPANAScrapData> list = smtMapper.getScrapDataAll(scrap);
        String[] CN = new String[] {"lineName", "cellName", "location", "partNum", "materialId", "pickupCount", "scrap",
                "starttime", "endtime"};

        for (PFSAPANAScrapData temp : list) {
            String[] tempStr = new String[CN.length];
            int index = 0;
            tempStr[index++] = temp.getLineName();
            tempStr[index++] = temp.getCellName();
            tempStr[index++] = temp.getLocation();
            tempStr[index++] = temp.getPartNum();
            tempStr[index++] = temp.getMaterialId();
            tempStr[index++] = String.valueOf(temp.getPickupCount());
            tempStr[index++] = String.valueOf(temp.getScrap());
            tempStr[index++] = temp.getStartTimeStamp();
            tempStr[index] = temp.getEndTimeStamp();
            excelList.add(tempStr);
        }
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
        String filePath = rootPath + "/" + fname;
        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
        String contentType = "application/msexcel";
        ExcelUtilities.downloadExcel(request, response, filePath, contentType, "抛料率" + fname);
    }

}
