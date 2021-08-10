package com.peg.model;


/**
 * 
 * <p>Title: ClaimsSheet</p>
 * <p>Description: 索赔/处罚单</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-13 下午2:39:40
 */
public class ClaimsSheet {
    private String id;//序号
    private String claims_type;//类别
	private String rework_id;//返工/停线单ID
    private String rework_number;//返工/停线单号
    private String rework_creation_time;//返工/停线单开单日期
    private String duty_depart;//责任部门
    private String duty_proportion;//责任比例
    private String rework_reason;//原因
	private String rework_count;//实际返工数量
    private String product_category;//产品线
    private String claims_number;//索赔/处罚单编号
    private String claims_supplier;//索赔/处罚供应商
    private String claims_reason;//索赔/处罚原因说明
    private String claims_amount;//索赔/处罚金额
    private String claims_applicant;//索赔/处罚申请人
    private String creation_time;//开具日期
    private String registrar;//登记人
    private String is_response;//是否回签状态
    private String response_reason;//未签回原因
    private String is_sign_in;//是否采购签收
    private String actual_claim_amount;//实际索赔/处罚金额
    private String booked_month;//入账月份
    private String is_close;//关闭状态
    private String remark;//备注
    public String getRework_creation_time() {
		return rework_creation_time;
	}
	public void setRework_creation_time(String rework_creation_time) {
		this.rework_creation_time = rework_creation_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getClaims_type() {
		return claims_type;
	}
	public void setClaims_type(String claims_type) {
		this.claims_type = claims_type;
	}
	public String getRework_id() {
		return rework_id;
	}
	public void setRework_id(String rework_id) {
		this.rework_id = rework_id;
	}
	public String getRework_number() {
		return rework_number;
	}
	public void setRework_number(String rework_number) {
		this.rework_number = rework_number;
	}
	public String getDuty_depart() {
		return duty_depart;
	}
	public void setDuty_depart(String duty_depart) {
		this.duty_depart = duty_depart;
	}
	public String getDuty_proportion() {
		return duty_proportion;
	}
	public void setDuty_proportion(String duty_proportion) {
		this.duty_proportion = duty_proportion;
	}
	public String getRework_reason() {
		return rework_reason;
	}
	public void setRework_reason(String rework_reason) {
		this.rework_reason = rework_reason;
	}
	public String getRework_count() {
		return rework_count;
	}
	public void setRework_count(String rework_count) {
		this.rework_count = rework_count;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getClaims_number() {
		return claims_number;
	}
	public void setClaims_number(String claims_number) {
		this.claims_number = claims_number;
	}
	public String getClaims_supplier() {
		return claims_supplier;
	}
	public void setClaims_supplier(String claims_supplier) {
		this.claims_supplier = claims_supplier;
	}
	public String getClaims_reason() {
		return claims_reason;
	}
	public void setClaims_reason(String claims_reason) {
		this.claims_reason = claims_reason;
	}
	public String getClaims_amount() {
		return claims_amount;
	}
	public void setClaims_amount(String claims_amount) {
		this.claims_amount = claims_amount;
	}
	public String getClaims_applicant() {
		return claims_applicant;
	}
	public void setClaims_applicant(String claims_applicant) {
		this.claims_applicant = claims_applicant;
	}
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	public String getRegistrar() {
		return registrar;
	}
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}
	public String getIs_response() {
		return is_response;
	}
	public void setIs_response(String is_response) {
		this.is_response = is_response;
	}
	public String getResponse_reason() {
		return response_reason;
	}
	public void setResponse_reason(String response_reason) {
		this.response_reason = response_reason;
	}
	public String getIs_sign_in() {
		return is_sign_in;
	}
	public void setIs_sign_in(String is_sign_in) {
		this.is_sign_in = is_sign_in;
	}
	public String getActual_claim_amount() {
		return actual_claim_amount;
	}
	public void setActual_claim_amount(String actual_claim_amount) {
		this.actual_claim_amount = actual_claim_amount;
	}
	public String getBooked_month() {
		return booked_month;
	}
	public void setBooked_month(String booked_month) {
		this.booked_month = booked_month;
	}
	public String getIs_close() {
		return is_close;
	}
	public void setIs_close(String is_close) {
		this.is_close = is_close;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}