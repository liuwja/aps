package com.peg.model;
import java.util.Date;
public class VoiceOfCustomer{
	private Long id;
	private String typeAppeal; //诉求类型
	private String customer; //客户
	private String province; //省份
	private String city; //市
	private String county; //区县
	private String serviceCenter;//服务中心
	private String nameProduct;//产品名称
	private String modelProduct;//简化型号
	private String typeProduct;//产品类型
	private String serialNumber;//产品编号
	private String type1;//一级分类
	private String type2;//二级分类
	private String numberAppeal;//诉求编号
	private String detail;//诉求详细信息
	private String orderNumber;//派工单号
	private String serviceLocation;//服务网点
	private String technician;//服务技师
	private String serviceProject;//服务项目
	private String bfaultClass;//故障大类
	private String sfaultClass;//故障小类
	private Date creationTime;
	private String dateTime;//时间
	private Long rowId;
	
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getTypeAppeal() {
		return typeAppeal;
	}
	public void setTypeAppeal(String typeAppeal) {
		this.typeAppeal = typeAppeal;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getModelProduct() {
		return modelProduct;
	}
	public void setModelProduct(String modelProduct) {
		this.modelProduct = modelProduct;
	}
	public String getTypeProduct() {
		return typeProduct;
	}
	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getNumberAppeal() {
		return numberAppeal;
	}
	public void setNumberAppeal(String numberAppeal) {
		this.numberAppeal = numberAppeal;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getServiceLocation() {
		return serviceLocation;
	}
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getServiceProject() {
		return serviceProject;
	}
	public void setServiceProject(String serviceProject) {
		this.serviceProject = serviceProject;
	}
	public String getBfaultClass() {
		return bfaultClass;
	}
	public void setBfaultClass(String bfaultClass) {
		this.bfaultClass = bfaultClass;
	}
	public String getSfaultClass() {
		return sfaultClass;
	}
	public void setSfaultClass(String sfaultClass) {
		this.sfaultClass = sfaultClass;
	}
	
}