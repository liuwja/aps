package com.peg.model.smt;

/**
 * SMT抛料率
 * @createTime 2019-06-05 17:09
 */
public class PFSAPANAScrapData {
    private String lineName;    // 产线名称
    private Integer cellNum;    // 设备编号
    private String cellName;    // 设备名称
    private String location;    // 位置
    private String partNum; // 料号(元件名)
    private String materialId;  // 材料ID
    private Integer pickupCount;    // 吸着数
    private Integer placeCount; // 贴装数
    private Integer scrap;  // 废弃数
    private Integer starttime;  // 测量开始时间
    private Integer endtime;    // 测量结束时间
    private String workOrderName;
    private String workOrderStatus;
    private String startTimeStamp;  // 查询条件开始时间
    private String endTimeStamp;    // 查询条件结束时间

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getPickupCount() {
        return pickupCount == null ? 0 : pickupCount;
    }

    public void setPickupCount(Integer pickupCount) {
        this.pickupCount = pickupCount;
    }

    public Integer getPlaceCount() {
        return placeCount == null ? 0 : placeCount;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public Integer getScrap() {
        return scrap == null ? 0 : scrap;
    }

    public void setScrap(Integer scrap) {
        this.scrap = scrap;
    }

    public Integer getStarttime() {
        return starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    public Integer getEndtime() {
        return endtime;
    }

    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public String getWorkOrderStatus() {
        return workOrderStatus;
    }

    public void setWorkOrderStatus(String workOrderStatus) {
        this.workOrderStatus = workOrderStatus;
    }

    public String getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public String getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(String endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }
}
