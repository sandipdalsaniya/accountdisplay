package pojo;

import java.io.Serializable;

public class WrapAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String market;
	private String phone;
	private String recordtype;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	@Override
	public String toString() {
		return "WrapAccount [id=" + id + ", name=" + name + ", market="
				+ market + ", phone=" + phone + ", recordtype=" + recordtype
				+ "]";
	}

	
	
}
