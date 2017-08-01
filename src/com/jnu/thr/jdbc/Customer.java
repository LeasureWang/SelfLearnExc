package com.jnu.thr.jdbc;

public class Customer {

	private Integer customerKey;
	private String customerName;
	private String customerLocation;
	private Integer customerTel;

	public Integer getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(Integer customerKey) {
		this.customerKey = customerKey;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public Integer getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(Integer customerTel) {
		this.customerTel = customerTel;
	}

	public Customer() {
		super();
	}

	public Customer(Integer customerKey, String customerName,
			String customerLocation, Integer customerTel) {
		super();
		this.customerKey = customerKey;
		this.customerName = customerName;
		this.customerLocation = customerLocation;
		this.customerTel = customerTel;
	}

	@Override
	public String toString() {
		return "Customer [customerKey=" + customerKey + ", customerName="
				+ customerName + ", customerLocation=" + customerLocation
				+ ", customerTel=" + customerTel + "]";
	}
	
	public void showing(){
		System.out.println("it is a showing method!");
	}

}
