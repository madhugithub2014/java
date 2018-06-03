package com.recharge.model;

public class Pack {	
	private int amount;
	private int code;
	private String size;
	private int days;
	private int data;
	private String currency;
	private String validity;
	private String currencyAmt;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCurrencyAmt() {
		return currencyAmt;
	}
	public void setCurrencyAmt(String currencyAmt) {
		this.currencyAmt = currencyAmt;
	}
		
}
