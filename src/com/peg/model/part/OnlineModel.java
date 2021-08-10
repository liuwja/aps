package com.peg.model.part;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OnlineModel extends TestInstance {
	//在线批次tablb展示
	private Date record_date_t;//发生日期
	private String line_s;//产品线
	private String part_number;//物料编号
	private String description;//物料名称
	private String product_maturity_s;//产品成熟度
	private String account_name;//供应商编号
	private String description_2;//供应商名称
	private String defect_s;//不良现象
	private BigDecimal defect_qty_i;//不良数
	private BigDecimal total_qty_i;//总数
	private String startdate;//维度时间1
	private String enddate;//维度时间2
	private String date_type;//时间类型
	private String replaceType;//不良数/率的类型选择
	private String abroad;//国内/外
	private String productNO;//新品/老品
	private String gas;//气源
	private String defect_d;//不良现象代码
	private String defect_res;//不良原因
	private String pl_name;//产线
	private String defect_b;//不良部件
	private String verifyingCon;//验证结论
	private String text;//备注
	private String group_name;//班组名称
	private String isnew_end_time_s;//新品结束时间
	
	//在线不良数明细
	private String moldtype;//机型类别
	private String supcode;//供应商编号
	private String supname;//供应商名称
	private String category;//物料级别
	//private String part_number;//物料编号
	//private String description;//物料名称
	private Date date_TT;//日期
	private Integer badnum;//不良数
	
	//ERP在线数据明细
	private Date return_date;//回退日期
	//private String part_number;//物料编号
	private String numbers;//供应商编号
	private String part_name;//物料编号
	private String supplier_number;//供应商编号
	private String supplier_name;//供应商名称
	private Integer return_number;//回退数量
	private String ware_house;//仓库
	
	//来料入库table展示
	private Date arrival;//入库时间
	private String partnumber;//物料编号
	private String partname;//物料名称
	//private String supcode;//供应商编号
	//private String supname;//供应商名称
	private Integer tonum;//到货数量
	private String location;//仓库
	//private String moldtype;//机型类别
	private String partclass;//物料分类
	private Integer partrevision;//物料版本
	//private String category;//物料级别
	
	//后台处理需返回页面的（charthid）
	private String buttonId;//页面按钮的标示
	private String charthid;//图形id
	
	//图表需要的数
	private Double badcount;//数值
	private Double count;//总数
	//private String supplier;//供应商
	private String productname;//零部件名称
	private String productnumber;//零部件编号
	private String date_t;//日期
	private String account_key;//供应商id
	private String part_key;//物料id
	
	//用于查明细时隐藏的值
	private String hiddenId;//图序号
	private String strone;//隐藏值
	private String strtwo;//显示值
	private String spareparts;//零部件
	private String spareparts2;//隐藏值
	private String badphenomenon;//不良现象
	private String trendTime;//趋势时间
	
	//全局
	private String productTypes;  //机型
//	private List<OnlineModel> onlineList;  //
	private String items;
	private String serialNumber;    //主机条码
	private String materialOld;     //更换前的物料条码
	private String materialNew;     //更换后的物料条码
	
	private List<String> strList;
	private List<String> strList2;
	
	private String dateT_T;             //日期
	private String maintenanceMode;//维修方式
	
	
	public String getAbroad() {
		return abroad;
	}
	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}
	public String getProductNO() {
		return productNO;
	}
	public void setProductNO(String productNO) {
		this.productNO = productNO;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getDefect_d() {
		return defect_d;
	}
	public void setDefect_d(String defect_d) {
		this.defect_d = defect_d;
	}
	public String getDefect_res() {
		return defect_res;
	}
	public void setDefect_res(String defect_res) {
		this.defect_res = defect_res;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public String getDefect_b() {
		return defect_b;
	}
	public void setDefect_b(String defect_b) {
		this.defect_b = defect_b;
	}
	public String getVerifyingCon() {
		return verifyingCon;
	}
	public void setVerifyingCon(String verifyingCon) {
		this.verifyingCon = verifyingCon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getIsnew_end_time_s() {
		return isnew_end_time_s;
	}
	public void setIsnew_end_time_s(String isnew_end_time_s) {
		this.isnew_end_time_s = isnew_end_time_s;
	}
	public String getMaintenanceMode() {
		return maintenanceMode;
	}
	public void setMaintenanceMode(String maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}
	public Double getBadcount() {
		if(badcount==null){
			return 0.0;
		}
		return badcount;
	}
	public void setBadcount(Double badcount) {
		this.badcount = badcount;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductnumber() {
		return productnumber;
	}
	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}
	public String getDate_t() {
		return date_t;
	}
	public void setDate_t(String date_t) {
		this.date_t = date_t;
	}
	public String getAccount_key() {
		return account_key;
	}
	public void setAccount_key(String account_key) {
		this.account_key = account_key;
	}
	public String getPart_key() {
		return part_key;
	}
	public void setPart_key(String part_key) {
		this.part_key = part_key;
	}

	public String getDate_type() {
		return date_type;
	}
	public void setDate_type(String date_type) {
		this.date_type = date_type;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Date getRecord_date_t() {
		return record_date_t;
	}
	public void setRecord_date_t(Date record_date_t) {
		this.record_date_t = record_date_t;
	}
	public String getLine_s() {
		return line_s;
	}
	public void setLine_s(String line_s) {
		this.line_s = line_s;
	}
	public String getPart_number() {
		return part_number;
	}
	public void setPart_number(String part_number) {
		this.part_number = part_number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProduct_maturity_s() {
		return product_maturity_s;
	}
	public void setProduct_maturity_s(String product_maturity_s) {
		this.product_maturity_s = product_maturity_s;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getDescription_2() {
		return description_2;
	}
	public void setDescription_2(String description_2) {
		this.description_2 = description_2;
	}
	public String getDefect_s() {
		return defect_s;
	}
	public void setDefect_s(String defect_s) {
		this.defect_s = defect_s;
	}
	public BigDecimal getDefect_qty_i() {
		return defect_qty_i;
	}
	public void setDefect_qty_i(BigDecimal defect_qty_i) {
		this.defect_qty_i = defect_qty_i;
	}
	public BigDecimal getTotal_qty_i() {
		return total_qty_i;
	}
	public void setTotal_qty_i(BigDecimal total_qty_i) {
		this.total_qty_i = total_qty_i;
	}
	public String getReplaceType() {
		return replaceType;
	}
	public void setReplaceType(String replaceType) {
		this.replaceType = replaceType;
	}
	public String getMoldtype() {
		return moldtype;
	}
	public void setMoldtype(String moldtype) {
		this.moldtype = moldtype;
	}
	public String getSupcode() {
		return supcode;
	}
	public void setSupcode(String supcode) {
		this.supcode = supcode;
	}
	public String getSupname() {
		return supname;
	}
	public void setSupname(String supname) {
		this.supname = supname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate_TT() {
		return date_TT;
	}
	public void setDate_TT(Date date_TT) {
		this.date_TT = date_TT;
	}
	public Integer getBadnum() {
		return badnum;
	}
	public void setBadnum(Integer badnum) {
		this.badnum = badnum;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public String getSupplier_number() {
		return supplier_number;
	}
	public void setSupplier_number(String supplier_number) {
		this.supplier_number = supplier_number;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public Integer getReturn_number() {
		return return_number;
	}
	public void setReturn_number(Integer return_number) {
		this.return_number = return_number;
	}
	public String getWare_house() {
		return ware_house;
	}
	public void setWare_house(String ware_house) {
		this.ware_house = ware_house;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public String getPartname() {
		return partname;
	}
	public void setPartname(String partname) {
		this.partname = partname;
	}
	public Integer getTonum() {
		return tonum;
	}
	public void setTonum(Integer tonum) {
		this.tonum = tonum;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPartclass() {
		return partclass;
	}
	public void setPartclass(String partclass) {
		this.partclass = partclass;
	}
	public Integer getPartrevision() {
		return partrevision;
	}
	public void setPartrevision(Integer partrevision) {
		this.partrevision = partrevision;
	}
	public String getPartnumber() {
		return partnumber;
	}
	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}
	
	public String getCharthid() {
		return charthid;
	}
	public void setCharthid(String charthid) {
		this.charthid = charthid;
	}
	public String getButtonId() {
		return buttonId;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public String getHiddenId() {
		return hiddenId;
	}
	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
	}
	public String getStrone() {
		return strone;
	}
	public void setStrone(String strone) {
		this.strone = strone;
	}
	public String getStrtwo() {
		return strtwo;
	}
	public void setStrtwo(String strtwo) {
		this.strtwo = strtwo;
	}
	public String getSpareparts() {
		return spareparts;
	}
	public void setSpareparts(String spareparts) {
		this.spareparts = spareparts;
	}
	public String getSpareparts2() {
		return spareparts2;
	}
	public void setSpareparts2(String spareparts2) {
		this.spareparts2 = spareparts2;
	}
	public String getBadphenomenon() {
		return badphenomenon;
	}
	public void setBadphenomenon(String badphenomenon) {
		this.badphenomenon = badphenomenon;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getTrendTime() {
		return trendTime;
	}
	public void setTrendTime(String trendTime) {
		this.trendTime = trendTime;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public List<String> getStrList2() {
		return strList2;
	}
	public void setStrList2(List<String> strList2) {
		this.strList2 = strList2;
	}
	
	public String getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(String productTypes) {
		this.productTypes = productTypes;
	}
	
//	public List<OnlineModel> getOnlineList() {
//		return onlineList;
//	}
//	public void setOnlineList(List<OnlineModel> onlineList) {
//		this.onlineList = onlineList;
//	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getMaterialOld() {
		return materialOld;
	}
	public void setMaterialOld(String materialOld) {
		this.materialOld = materialOld;
	}
	public String getMaterialNew() {
		return materialNew;
	}
	public void setMaterialNew(String materialNew) {
		this.materialNew = materialNew;
	}
	public String getDateT_T() {
		if(dateT_T==null || dateT_T.equals("")){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM");//日期格式
	        Calendar curr = Calendar.getInstance();//获取一个日历对象
	        curr.add(Calendar.MONTH, -1);
	        dateT_T=sdf.format(curr.getTime());
		}
		return dateT_T;
	}
	
	public void setDateT_T(String dateT_T) {
		this.dateT_T = dateT_T;
	}
	
	
}
