package cn.com.belle.bdc.openapi.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 
* @ClassName: QueryParams 
* @Description: 查询参数
* @author
* @date
*
 */
public class QueryParams implements Serializable{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * @Fields sign: 签名
	 */
	@NotBlank(message="sign不能为空")
	private String sign;
	
	/**
	 * @Fields app_key: 中台分配的app_key
	 */
	@NotBlank(message="{app_key不能为空}")
	private String app_key;

	/**
	 * @Fields method: 接口方法名
	 */
	@NotBlank(message="{method不能为空}")
	private String method;

	private String sign_method;

	private String app_version;

	private String format;

	private String start_date;

	private String end_date;

	private String real;

	private String store_no;

	private String region_no;

	private String brand_no;

	private String staff_id;

	private String s_year_month;

	private String e_year_month;

	private String split_result;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSign_method() {
		return sign_method;
	}

	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getReal() {
		return real;
	}

	public void setReal(String real) {
		this.real = real;
	}

	public String getStore_no() {
		return store_no;
	}

	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}

	public String getRegion_no() {
		return region_no;
	}

	public void setRegion_no(String region_no) {
		this.region_no = region_no;
	}

	public String getBrand_no() {
		return brand_no;
	}

	public void setBrand_no(String brand_no) {
		this.brand_no = brand_no;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getS_year_month() {
		return s_year_month;
	}

	public void setS_year_month(String s_year_month) {
		this.s_year_month = s_year_month;
	}

	public String getE_year_month() {
		return e_year_month;
	}

	public void setE_year_month(String e_year_month) {
		this.e_year_month = e_year_month;
	}

	public String getSplit_result() {
		return split_result;
	}

	public void setSplit_result(String split_result) {
		this.split_result = split_result;
	}
}
