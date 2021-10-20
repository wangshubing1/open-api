package cn.com.belle.bdc.openapi.model;

import java.io.Serializable;

/**
 * 返回值
* @ClassName: ReturnInfo 
* @Description: 查询返回对象
* @author
* @date
*
 */
public class ReturnInfo implements Serializable {

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields 返回码
	 */
	private String returnCode;
	
	/**
	 * @Field 返回信息
	 */
	private String message;
	

	/**
	 * @Field 查询数据集合
	 */
	private Object datas;

	public ReturnInfo() {
		super();
	}
	
	public ReturnInfo(String returnCode, String message, Object datas) {
		super();
		this.returnCode = returnCode;
		this.message = message;
		this.datas = datas;
	}
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object data) {
		this.datas = data;
	}
	
}
