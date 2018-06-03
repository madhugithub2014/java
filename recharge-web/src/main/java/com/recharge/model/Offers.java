package com.recharge.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "offers")
public class Offers {
	private String id;

	@Field("name")
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
