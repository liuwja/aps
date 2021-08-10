package com.peg.model.smt;

import java.util.Date;

/**
 * SMT物料追溯
 */
public class PFSAPANATraceData {
    private Integer lineNum;    // 产线编号
    private String lineName;    // 产线名称
    private Integer cellNum;    // 设备编号
    private String cellName;    // 设备名称
    private String lane;    // 轨道No
    private String stage;   // 基台No
    private String pcbName; // 批量名
    private String setupName;   // 设置名
    private String prodId;  // 模型字符串
    private String panelBarCode;    // 基板条形码
    private String side;    // 基板面
    private String serialNum;   // 基板系列号
    private Date boardEntryTime;    // 基板搬入时间
    private Date releaseTime;   // 基板搬出时间
    private Integer zNum;   // Z号码
    private Integer slotNum;    // 插槽编号
    private Integer subSlotNum; // 子插槽编号
    private String partNum; // 料号(元件名)
    private String expectedPart;    // 主元件名
    private String lotNum;  // 批次号
    private String vendor;  // 制造商No
    private String feeder;  // 供料器ID
    private Double quantity;    // 材料数
    private Date partTimeOn;    // 元件安装时间
    private Date partTimeOff;   // 元件取下时间
    private String mountOperator;   // 操作员ID
    private String userData;    // 用户数据
    private String primaryProductId;    // 主要生产ID
    private String materialId;  // 材料ID
    private String mcid;    // MC ID
    private String partClass;   // 元件级别
    private String patternSerialNum;    // 模块序列号编号
    private String location;    // 位置
    private String boardEntryTimeStart; // 查询条件开始时间
    private String boardEntryTimeEnd;   // 查询条件结束时间

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getPcbName() {
        return pcbName;
    }

    public void setPcbName(String pcbName) {
        this.pcbName = pcbName;
    }

    public String getSetupName() {
        return setupName;
    }

    public void setSetupName(String setupName) {
        this.setupName = setupName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getPanelBarCode() {
        return panelBarCode;
    }

    public void setPanelBarCode(String panelBarCode) {
        this.panelBarCode = panelBarCode;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Date getBoardEntryTime() {
        return boardEntryTime;
    }

    public void setBoardEntryTime(Date boardEntryTime) {
        this.boardEntryTime = boardEntryTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getzNum() {
        return zNum;
    }

    public void setzNum(Integer zNum) {
        this.zNum = zNum;
    }

    public Integer getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(Integer slotNum) {
        this.slotNum = slotNum;
    }

    public Integer getSubSlotNum() {
        return subSlotNum;
    }

    public void setSubSlotNum(Integer subSlotNum) {
        this.subSlotNum = subSlotNum;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getExpectedPart() {
        return expectedPart;
    }

    public void setExpectedPart(String expectedPart) {
        this.expectedPart = expectedPart;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFeeder() {
        return feeder;
    }

    public void setFeeder(String feeder) {
        this.feeder = feeder;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Date getPartTimeOn() {
        return partTimeOn;
    }

    public void setPartTimeOn(Date partTimeOn) {
        this.partTimeOn = partTimeOn;
    }

    public Date getPartTimeOff() {
        return partTimeOff;
    }

    public void setPartTimeOff(Date partTimeOff) {
        this.partTimeOff = partTimeOff;
    }

    public String getMountOperator() {
        return mountOperator;
    }

    public void setMountOperator(String mountOperator) {
        this.mountOperator = mountOperator;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getPrimaryProductId() {
        return primaryProductId;
    }

    public void setPrimaryProductId(String primaryProductId) {
        this.primaryProductId = primaryProductId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMcid() {
        return mcid;
    }

    public void setMcid(String mcid) {
        this.mcid = mcid;
    }

    public String getPartClass() {
        return partClass;
    }

    public void setPartClass(String partClass) {
        this.partClass = partClass;
    }

    public String getPatternSerialNum() {
        return patternSerialNum;
    }

    public void setPatternSerialNum(String patternSerialNum) {
        this.patternSerialNum = patternSerialNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBoardEntryTimeStart() {
        return boardEntryTimeStart;
    }

    public void setBoardEntryTimeStart(String boardEntryTimeStart) {
        this.boardEntryTimeStart = boardEntryTimeStart;
    }

    public String getBoardEntryTimeEnd() {
        return boardEntryTimeEnd;
    }

    public void setBoardEntryTimeEnd(String boardEntryTimeEnd) {
        this.boardEntryTimeEnd = boardEntryTimeEnd;
    }
}
