package com.peg.model.smt;

/**
 * SMT上料记录
 * @createTime 2019-06-11 11:34
 */
public class PFSAPANAMvData {
    private Integer lineNum;    // 产线编号
    private String lineName;    // 产线名称
    private Integer zoneNum;    // 区域编号
    private String zoneName;    // 区域名称
    private String cartId;  // 台车ID
    private Integer cellNum;    // 设备编号
    private String cellName;    // 设备名称
    private String lane;    // 轨道No
    private String stage;   // 基台No
    private Integer zNum;   // Z号码
    private Integer slotNum;    // 插槽编号
    private Integer subSlotNum; // 子插槽编号
    private String partNum; // 料号(元件名)
    private String expectedPart;    // 主元件名
    private String lotNum;  // 批次号
    private String vendor;  // 制造商No
    private String feeder;  // 供料器ID
    private Integer quantity;   // 初始数量
    private Integer currentQty; // 材料数
    private Integer partTimeOn; // 元件安装时间
    // 7942 10月发布：partTimeOn列显示为日期格式（yyyy/m/d h:mm:ss）
    private String partTimeOnStr; // 元件安装时间
    private String partTimeOff; // 元件取下数据
    private String mountOperator;   // 操作员ID
    private String operation;   // 操作内容
    private String userData;    // 用户数据
    private String materialId;  // 材料ID
    private String mcid;    // MC ID
    private String partClass;   // 元件级别
    private String comparisonId;    // 比较ID
    private String overrideReason;  // 材料ID
    private String pacpartno;   // 比较元件名
    private String pacextradata;    // 添加比较项目值
    private String pacevaluation;   // 比较结果

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

    public Integer getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(Integer zoneNum) {
        this.zoneNum = zoneNum;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Integer currentQty) {
        this.currentQty = currentQty;
    }

    public Integer getPartTimeOn() {
        return partTimeOn;
    }

    public void setPartTimeOn(Integer partTimeOn) {
        this.partTimeOn = partTimeOn;
    }

    public String getPartTimeOnStr() {
        return partTimeOnStr;
    }

    public void setPartTimeOnStr(String partTimeOnStr) {
        this.partTimeOnStr = partTimeOnStr;
    }

    public String getPartTimeOff() {
        return partTimeOff;
    }

    public void setPartTimeOff(String partTimeOff) {
        this.partTimeOff = partTimeOff;
    }

    public String getMountOperator() {
        return mountOperator;
    }

    public void setMountOperator(String mountOperator) {
        this.mountOperator = mountOperator;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
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

    public String getComparisonId() {
        return comparisonId;
    }

    public void setComparisonId(String comparisonId) {
        this.comparisonId = comparisonId;
    }

    public String getOverrideReason() {
        return overrideReason;
    }

    public void setOverrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
    }

    public String getPacpartno() {
        return pacpartno;
    }

    public void setPacpartno(String pacpartno) {
        this.pacpartno = pacpartno;
    }

    public String getPacextradata() {
        return pacextradata;
    }

    public void setPacextradata(String pacextradata) {
        this.pacextradata = pacextradata;
    }

    public String getPacevaluation() {
        return pacevaluation;
    }

    public void setPacevaluation(String pacevaluation) {
        this.pacevaluation = pacevaluation;
    }
}
