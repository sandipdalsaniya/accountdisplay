package pojo;

import java.util.Arrays;

public class InputAccount {
	private String id;
	private String accountName;
	private String market;
	private String parentAccountId;
	private String brandId;
	private String productId;
	private String brandStatus;
	private String primaryChannelId;
	private String accountStatus;
	private String type;
	private int pageNo;
	private int noOfRecords;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getParentAccountId() {
		return parentAccountId;
	}
	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}
	public String getPrimaryChannelId() {
		return primaryChannelId;
	}
	public void setPrimaryChannelId(String primaryChannelId) {
		this.primaryChannelId = primaryChannelId;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	@Override
	public String toString() {
		return "InputAccount [id=" + id + ", accountName="
				+ accountName + ", market=" + market + ", parentAccountId="
				+ parentAccountId + ", brandId=" + brandId + ", productId="
				+ productId + ", brandStatus=" + brandStatus
				+ ", primaryChannelId=" + primaryChannelId + ", accountStatus="
				+ accountStatus + ", type=" + type + ", pageNo=" + pageNo
				+ ", noOfRecords=" + noOfRecords + "]";
	}
}
