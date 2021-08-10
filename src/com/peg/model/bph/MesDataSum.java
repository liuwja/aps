package com.peg.model.bph;

import java.util.Date;

import com.peg.model.baseCommonVo;
/**EMS数据总和表
 * @Class: MesDataSum @TODO:
 */
public class MesDataSum extends baseCommonVo{

    private String factory;

    private String areas;

    private String shiftGroup; //班组名称

    private String monthly;  //月份

    private Long returnNum;//月度生产退次数

    private Long commitNum;//月度生产数
    
    private String defectQty;//前工序Fqc不良数
    
    private Long totalQty;//前工序Fqc总数
    
    private Long simpleQty;//前工序Fqc抽样数
    
    private Long auditNum; //月度审核次数
    
    private Long totalScore;//月度最总评分
    
    private Long qualityQty; //合格数
    
    private String defectSource;//不良来源

    private Long defectNum ;//不良次数
    
    private String queryMonth; //查找月份
    
    private String queryDay; //查找日期
    
    private String startTime;
    
    private String endTime;
    

    
    
    private String groupType ;   //班组类别
    private String shiftGroupCode; //班组编号    
    private String shiftGroupName; //班组名称    
    private Date sumDate;  //总和日期    
    private Long  col1 =0L; // 月度责任班组的退次数（不良数）(组装退次表)
    private Long col2  =0L; // 月度组装投产数                              (组装退次表)
    private Long col3 =0L; //   月度责任班组的不良频次           (FQC抽检表)  
    private Long col4 =0L; //   月度责任班组的抽检频次            (FQC抽检表) 
    private Long col5 =0L; //   月度责任班组巡检不良批次数  （IPQC巡检表）
    private Long col6 =0L; //   月度责任班组巡检总批次数    （IPQC巡检表）
    private Double col7 =0D; //    发生流程节点为A/B/C的次数  （IPQC巡检表）    
    private Double col8 =0D; //  发生流程节点为D的次数      （IPQC巡检表）
    private Double  col9 =0D; // 月度责任班组附加分数（质量改善重要提案表）
    private Long col10 =0L; //  月度责任班组的不良数量之和
    private Long col11 =0L; //  交货日期是当月工单的冲压入库数之和（最后道工序）
    private Long col12 =0L; //    月度指定物料清单的下件合格数    （喷涂质量日报表）
    private Long col13 =0L; //  月度（指定物料清单的）挂件数    （喷涂质量日报表）
    private String col14 ; //  不良来源
    private Long col15 =0L; //   不良次数
    private Long col16 ; //不良台数                                       （OQC抽检表）
    private Long col17 ; //组装停线数
    private Long col18 =0L; //组装维修数                                   （组装维修日报表）
    private Long col19 =0L; //开箱不良次数        （市场开箱不良记录表）
    private Long col20 =0L; //流行性不良次数   （市场开箱不良记录表）
    private Long col21 =0L; //备用字段6
    private Long col22 =0L; //备用字段7
    private Long col23 =0L; //备用字段8
    private String col24; //审核内容
    private Double col25 =0D; //发生流程节点为A/B/C的质量风险系数之和   ( 5M1E不良记录表)
    private Double col26 =0D; //发生流程节点为D的质量风险系数之和 ( 5M1E不良记录表)
    private Long col27 =0L;   //公司外审不符合次数（过程审核记录表）
    private Long col28 =0L;   //公司内审不符合 次数（过程审核记录表）
    private Long col29 =0L;  //系统内审不符合次数（过程审核记录表）
    private Long col30 =0L;  //巡检5M1E次数（过程审核记录表）
    private Long col31 =0L;  //工艺纪律检查次数   （过程审核记录表）
    private Long col32 =0L;  //盲点测试 次数（过程审核记录表）
    

    private Date  insertTime ;
    private Date lastUpdateTime ; 
    private Long needSum ; //是否需要重新汇总

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas == null ? null : areas.trim();
    }

    public String getShiftGroup() {
        return shiftGroup;
    }

    public void setShiftGroup(String shiftGroup) {
        this.shiftGroup = shiftGroup == null ? null : shiftGroup.trim();
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly == null ? null : monthly.trim();
    }

    public Long getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Long returnNum) {
        this.returnNum = returnNum;
    }

    public Long getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(Long commitNum) {
        this.commitNum = commitNum;
    }

	public String getDefectQty() {
		return defectQty;
	}

	public void setDefectQty(String defectQty) {
		this.defectQty = defectQty;
	}

	public Long getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Long totalQty) {
		this.totalQty = totalQty;
	}

	public Long getSimpleQty() {
		return simpleQty;
	}

	public void setSimpleQty(Long simpleQty) {
		this.simpleQty = simpleQty;
	}

	public Long getAuditNum() {
		return auditNum;
	}

	public void setAuditNum(Long auditNum) {
		this.auditNum = auditNum;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	public Long getQualityQty() {
		return qualityQty;
	}

	public void setQualityQty(Long qualityQty) {
		this.qualityQty = qualityQty;
	}

	public String getDefectSource() {
		return defectSource;
	}

	public void setDefectSource(String defectSource) {
		this.defectSource = defectSource;
	}

	public Long getDefectNum() {
		return defectNum;
	}

	public void setDefectNum(Long defectNum) {
		this.defectNum = defectNum;
	}

	public String getShiftGroupCode() {
		return shiftGroupCode;
	}

	public void setShiftGroupCode(String shiftGroupCode) {
		this.shiftGroupCode = shiftGroupCode;
	}

	public String getShiftGroupName() {
		return shiftGroupName;
	}

	public void setShiftGroupName(String shiftGroupName) {
		this.shiftGroupName = shiftGroupName;
	}

	public Date getSumDate() {
		return sumDate;
	}

	public void setSumDate(Date sumDate) {
		this.sumDate = sumDate;
	}

	public Long getCol1() {
		return col1 ;
	}

	public void setCol1(Long col1) {
		this.col1 = col1;
	}

	public Long getCol2() {
		return col2;
	}

	public void setCol2(Long col2) {
		this.col2 = col2;
	}

	public Long getCol3() {
		return col3;
	}

	public void setCol3(Long col3) {
		this.col3 = col3;
	}

	public Long getCol4() {
		return col4;
	}

	public void setCol4(Long col4) {
		this.col4 = col4;
	}

	public Long getCol5() {
		return col5;
	}

	public void setCol5(Long col5) {
		this.col5 = col5;
	}

	public Long getCol6() {
		return col6;
	}

	public void setCol6(Long col6) {
		this.col6 = col6;
	}

	public Double getCol7() {
		return col7;
	}

	public void setCol7(Double col7) {
		this.col7 = col7;
	}

	public Double getCol8() {
		return col8;
	}

	public void setCol8(Double col8) {
		this.col8 = col8;
	}

	public double getCol9() {
		return col9;
	}

	public void setCol9(double col9) {
		this.col9 = col9;
	}

	public Long getCol10() {
		return col10;
	}

	public void setCol10(Long col10) {
		this.col10 = col10;
	}

	public Long getCol11() {
		return col11;
	}

	public void setCol11(Long col11) {
		this.col11 = col11;
	}

	public Long getCol12() {
		return col12;
	}

	public void setCol12(Long col12) {
		this.col12 = col12;
	}

	public Long getCol13() {
		return col13;
	}

	public void setCol13(Long col13) {
		this.col13 = col13;
	}



	public Long getCol15() {
		return col15;
	}

	public void setCol15(Long col15) {
		this.col15 = col15;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getNeedSum() {
		return needSum;
	}

	public void setNeedSum(Long needSum) {
		this.needSum = needSum;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public String getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(String queryDay) {
		this.queryDay = queryDay;
	}

	public String getCol14() {
		return col14;
	}

	public void setCol14(String col14) {
		this.col14 = col14;
	}

	public Long getCol16() {
		return col16;
	}

	public void setCol16(Long col16) {
		this.col16 = col16;
	}

	public Long getCol17() {
		return col17;
	}

	public void setCol17(Long col17) {
		this.col17 = col17;
	}

	public Long getCol18() {
		return col18;
	}

	public void setCol18(Long col18) {
		this.col18 = col18;
	}

	public Long getCol19() {
		return col19;
	}

	public void setCol19(Long col19) {
		this.col19 = col19;
	}

	public Long getCol20() {
		return col20;
	}

	public void setCol20(Long col20) {
		this.col20 = col20;
	}

	public Long getCol21() {
		return col21;
	}

	public void setCol21(Long col21) {
		this.col21 = col21;
	}

	public Long getCol22() {
		return col22;
	}

	public void setCol22(Long col22) {
		this.col22 = col22;
	}

	public Long getCol23() {
		return col23;
	}

	public void setCol23(Long col23) {
		this.col23 = col23;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCol24() {
		return col24;
	}

	public void setCol24(String col24) {
		this.col24 = col24;
	}

	public double getCol25() {
		return col25;
	}

	public void setCol25(double col25) {
		this.col25 = col25;
	}

	public double getCol26() {
		return col26;
	}

	public void setCol26(double col26) {
		this.col26 = col26;
	}

	public Long getCol27() {
		return col27;
	}

	public void setCol27(Long col27) {
		this.col27 = col27;
	}

	public Long getCol28() {
		return col28;
	}

	public void setCol28(Long col28) {
		this.col28 = col28;
	}

	public Long getCol29() {
		return col29;
	}

	public void setCol29(Long col29) {
		this.col29 = col29;
	}

	public Long getCol30() {
		return col30;
	}

	public void setCol30(Long col30) {
		this.col30 = col30;
	}

	public Long getCol31() {
		return col31;
	}

	public void setCol31(Long col31) {
		this.col31 = col31;
	}

	public Long getCol32() {
		return col32;
	}

	public void setCol32(Long col32) {
		this.col32 = col32;
	}
    
	
	
}