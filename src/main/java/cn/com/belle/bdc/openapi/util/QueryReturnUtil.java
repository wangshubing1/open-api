package cn.com.belle.bdc.openapi.util;

import cn.com.belle.bdc.openapi.common.Constants;
import cn.com.belle.bdc.openapi.model.QueryReturnInfo;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: QueryReturnUtil 
* @Description: 查询返回值工具类
* @author
* @date
*
 */
public class QueryReturnUtil {

	private QueryReturnUtil(){}
	
	/**
	 * 
	* @Title: success 
	* @Description: 查询成功返回 
	* @param @param data
	* @param @return    设定文件 
	* @return QueryReturnInfo    返回类型 
	* @throws
	 */
	public static QueryReturnInfo success(List<Map<String, Object>> data) {
		return new QueryReturnInfo(Constants.SUCCESS, Constants.SUCCESS_MSG, data);
	}
	
	/**
	 * 
	* @Title: fail 
	* @Description: 查询失败返回 
	* @param @param returnCode 返回码
	* @param @param message 返回信息
	* @param @return    设定文件 
	* @return QueryReturnInfo    返回类型 
	* @throws
	 */
	public static QueryReturnInfo fail(String returnCode, String message) {
		return new QueryReturnInfo(returnCode, message, null);
	}
	
	/**
	 * 
	* @Title: fail 
	* @Description: 查询失败返回 
	* @param @param message
	* @param @return    设定文件 
	* @return QueryReturnInfo    返回类型 
	* @throws
	 */
	public static QueryReturnInfo fail(String message) {
		return new QueryReturnInfo(Constants.FAIL, message, null);
	}
}
