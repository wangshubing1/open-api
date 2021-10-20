package cn.com.belle.bdc.openapi.model;

import java.io.Serializable;


public class ApiKeyMetadata implements Serializable {

	private String id;
	private ApiKey apiKey;
	private String metadataKey;
	private String metadataVal;
	private String metadataTag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public String getMetadataKey() {
		return metadataKey;
	}

	public void setMetadataKey(String metadataKey) {
		this.metadataKey = metadataKey;
	}

	public String getMetadataVal() {
		return metadataVal;
	}

	public void setMetadataVal(String metadataVal) {
		this.metadataVal = metadataVal;
	}

	public String getMetadataTag() {
		return metadataTag;
	}

	public void setMetadataTag(String metadataTag) {
		this.metadataTag = metadataTag;
	}

	public ApiKeyMetadata(String id, ApiKey apiKey, String metadataKey, String metadataVal, String metadataTag) {
		this.id = id;
		this.apiKey = apiKey;
		this.metadataKey = metadataKey;
		this.metadataVal = metadataVal;
		this.metadataTag = metadataTag;
	}
}
