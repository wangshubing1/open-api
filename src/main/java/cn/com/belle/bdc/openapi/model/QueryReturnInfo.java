package cn.com.belle.bdc.openapi.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回值
* @ClassName: QueryReturnInfo 
* @Description: 查询返回对象
* @author
* @date
*
 */
public class QueryReturnInfo implements Serializable {

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
	private List<Map<String, Object>> datas;

	public QueryReturnInfo() {
		super();
	}
	
	public QueryReturnInfo(String returnCode, String message, List<Map<String, Object>> datas) {
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

	public List<Map<String, Object>> getDatas() {
		if(!datas.isEmpty()&&null==datas.get(0)){
			datas.clear();
		}
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
	
}
