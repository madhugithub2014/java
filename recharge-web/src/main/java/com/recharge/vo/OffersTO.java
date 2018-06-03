package com.recharge.vo;

import java.util.ArrayList;

import com.recharge.model.Pack;

public class OffersTO {
	private String id;

	private ArrayList<String> names;

	private String description;

	private ArrayList<Pack> pack;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Pack> getPack() {
		return pack;
	}

	public void setPack(ArrayList<Pack> pack) {
		this.pack = pack;
	}
	
}
