package cn.com.belle.bdc.openapi.model;


import java.io.Serializable;
import java.util.Set;

public class ApiKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String appKey;
	private String appSecret;
	private String status;
	private String updateUser;
	private String updateTime;

	private Set<ApiKeyMetadata> apiKeyMetadatas;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Set<ApiKeyMetadata> getApiKeyMetadatas() {
		return apiKeyMetadatas;
	}

	public void setApiKeyMetadatas(Set<ApiKeyMetadata> apiKeyMetadatas) {
		this.apiKeyMetadatas = apiKeyMetadatas;
	}

}
