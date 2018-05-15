package com.ibm.fstraining.springgitproject.model;

import java.io.Serializable;

public class Product implements Serializable {

	private int id;

	private String productName;

	private String description;

	private String mfgdate;

	private String barCode;

	private String sku;
	private String category;
	
	private int amount;

	public Product() {

	}

	public Product(int id, String productName, String description,
			String mfgdate) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.mfgdate = mfgdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMfgdate() {
		return mfgdate;
	}

	public void setMfgdate(String mfgdate) {
		this.mfgdate = mfgdate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
