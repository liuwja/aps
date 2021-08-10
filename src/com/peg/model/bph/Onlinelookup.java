package com.peg.model.bph;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Onlinelookup {
	//物料分类
	private String factoryS;//工厂
	private String uda_2;//物料大类
	private String uda_1;//物料小类
	private String uda_3;//绑定数量/物料类型
	private String uda_4;//编码规则
	//物料编号
	//private String uda_3;//物料类型
	//private String uda_2;//库存仓库
	private String partnumber;//物料编号
	private String description;//物料名称
	private String level;//物料级别
	//供应商
	//private String factoryS;//工厂
	private String productionLine;//产线
	private String numbers;//供应商编号
	private String name;//供应商名称
	private String abbreviation;//简称
	//查询条件
	//private String factoryS;//工厂
	private String type;//机型类别
	private String dimension;//时间维度//周月年
	private String endtime;//截止日期
	private Integer charNumber;//排列图数量
	private String numberstxt;//供应商编号
	private String numberstxtstr;//供应商名称
	private String classificationtxt;//物料分类
	//private String level;//级别
	private String partnumbertxt;//物料编号
	private String partnumbertxtstr;//物料名称
	private String iscrux;//是否关键件
	private String isEdition;//是否带版本
	private String maturity;//产品成熟度
	private Integer bs;//标示，1：供应商不良批次排列图
	private List<String> suplist;//供应商编号集合
	private List<String> partclasslist;//物料分类集合
	private List<String> partlist;//物料集合
	private String startdate;//维度时间1
	private String enddate;//维度时间2
	private String date_type;//时间类型
	private String batch;//供应商批次
	private String batchtime;//供应商供货时间
	private String trendTime;//趋势时间
	//图表需要的数
	private Double badcount;//数值
	private Double count;//总数
	private String supplier;//供应商
	private String productname;//零部件名称
	private String productnumber;//零部件编号
	//private String defect_s;//不良现象
	private String date_t;//日期
	private String account_key;//供应商id
	private String part_key;//物料id
	//在线批次tablb展示
	private Date record_date_t;//发生日期
	private String line_s;//产品线
	private String part_number;//物料编号
	//private String description;//物料名称
	private String product_maturity_s;//产品成熟度
	private String account_name;//供应商编号
	private String description_2;//供应商名称
	private String defect_s;//不良现象
	private BigDecimal defect_qty_i;//不良数
	private BigDecimal total_qty_i;//总数
	//ERP在线数据明细
	private Date return_date;//回退日期
	//private String part_number;//物料编号
	private String part_name;//物料编号
	private String supplier_number;//供应商编号
	private String supplier_name;//供应商名称
	private Integer return_number;//回退数量
	private String ware_house;//仓库
	
	//来料入库table展示
	private Date arrival;//入库时间
	//private String partnumber;//物料编号
	private String partname;//物料名称
	private String supcode;//供应商编号
	private String supname;//供应商名称
	private Integer tonum;//到货数量
	private String location;//仓库
	private String moldtype;//机型类别
	private String partclass;//物料分类
	private Integer partrevision;//物料版本
	private String category;//物料级别
	//用于查明细时隐藏的值
	private String hiddenId;//图序号
	private String strone;//隐藏值
	private String strtwo;//显示值
	private String spareparts;//零部件
	private String spareparts2;//隐藏值
	private String badphenomenon;//不良现象
	//在线不良数明细
	//private String moldtype;//机型类别
	//private String supcode;//供应商编号
	//private String supname;//供应商名称
	//private String category;//物料级别
	//private String part_number;//物料编号
	//private String description;//物料名称
	private Date date_TT;//日期
	private Integer badnum;//不良数
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	

	public Double getBadcount() {
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
	
	public Integer getBs() {
		return bs;
	}
	public void setBs(Integer bs) {
		this.bs = bs;
	}
	public String getFactoryS() {
		return factoryS;
	}
	public void setFactoryS(String factoryS) {
		this.factoryS = factoryS;
	}
	public String getUda_2() {
		return uda_2;
	}
	public void setUda_2(String uda_2) {
		this.uda_2 = uda_2;
	}
	public String getUda_1() {
		return uda_1;
	}
	public void setUda_1(String uda_1) {
		this.uda_1 = uda_1;
	}
	public String getUda_3() {
		return uda_3;
	}
	public void setUda_3(String uda_3) {
		this.uda_3 = uda_3;
	}
	public String getUda_4() {
		return uda_4;
	}
	public void setUda_4(String uda_4) {
		this.uda_4 = uda_4;
	}
	public String getPartnumber() {
		return partnumber;
	}
	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductionLine() {
		return productionLine;
	}
	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}

	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public Integer getCharNumber() {
		return charNumber;
	}
	public void setCharNumber(Integer charNumber) {
		this.charNumber = charNumber;
	}
	public String getNumberstxt() {
		return numberstxt;
	}
	public void setNumberstxt(String numberstxt) {
		this.numberstxt = numberstxt;
	}
	public String getClassificationtxt() {
		return classificationtxt;
	}
	public void setClassificationtxt(String classificationtxt) {
		this.classificationtxt = classificationtxt;
	}
	public String getIsEdition() {
		return isEdition;
	}
	public void setIsEdition(String isEdition) {
		this.isEdition = isEdition;
	}
	public String getMaturity() {
		return maturity;
	}
	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}
	public List<String> getSuplist() {
		return suplist;
	}
	public void setSuplist(List<String> suplist) {
		this.suplist = suplist;
	}
	public List<String> getPartclasslist() {
		return partclasslist;
	}
	public void setPartclasslist(List<String> partclasslist) {
		this.partclasslist = partclasslist;
	}
	public List<String> getPartlist() {
		return partlist;
	}
	public void setPartlist(List<String> partlist) {
		this.partlist = partlist;
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
	public String getProduct_maturity_s() {
		return product_maturity_s;
	}
	public void setProduct_maturity_s(String product_maturity_s) {
		this.product_maturity_s = product_maturity_s;
	}

	public String getPart_number() {
		return part_number;
	}
	public void setPart_number(String part_number) {
		this.part_number = part_number;
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
	public String getMoldtype() {
		return moldtype;
	}
	public void setMoldtype(String moldtype) {
		this.moldtype = moldtype;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPartnumbertxt() {
		return partnumbertxt;
	}
	public void setPartnumbertxt(String partnumbertxt) {
		this.partnumbertxt = partnumbertxt;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNumberstxtstr() {
		return numberstxtstr;
	}
	public void setNumberstxtstr(String numberstxtstr) {
		this.numberstxtstr = numberstxtstr;
	}
	public String getSpareparts() {
		return spareparts;
	}
	public void setSpareparts(String spareparts) {
		this.spareparts = spareparts;
	}
	public String getBadphenomenon() {
		return badphenomenon;
	}
	public void setBadphenomenon(String badphenomenon) {
		this.badphenomenon = badphenomenon;
	}
	
	public String getDate_t() {
		return date_t;
	}
	public void setDate_t(String date_t) {
		this.date_t = date_t;
	}
	public String getDate_type() {
		return date_type;
	}
	public void setDate_type(String date_type) {
		this.date_type = date_type;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getBatchtime() {
		return batchtime;
	}
	public void setBatchtime(String batchtime) {
		this.batchtime = batchtime;
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
	public Integer getBadnum() {
		return badnum;
	}
	public void setBadnum(Integer badnum) {
		this.badnum = badnum;
	}
	public Date getDate_TT() {
		return date_TT;
	}
	public void setDate_TT(Date date_TT) {
		this.date_TT = date_TT;
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
	public String getSpareparts2() {
		return spareparts2;
	}
	public void setSpareparts2(String spareparts2) {
		this.spareparts2 = spareparts2;
	}
	public String getTrendTime() {
		return trendTime;
	}
	public void setTrendTime(String trendTime) {
		this.trendTime = trendTime;
	}
	public String getPartnumbertxtstr() {
		return partnumbertxtstr;
	}
	public void setPartnumbertxtstr(String partnumbertxtstr) {
		this.partnumbertxtstr = partnumbertxtstr;
	}
	public String getIscrux() {
		return iscrux;
	}
	public void setIscrux(String iscrux) {
		this.iscrux = iscrux;
	}
	
}
