package com.recharge.vo;

public class Offers {
private String description;
private double amount;


public Offers(double amount,String description) {
	super();
	this.description = description;
	this.amount = amount;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}

}
