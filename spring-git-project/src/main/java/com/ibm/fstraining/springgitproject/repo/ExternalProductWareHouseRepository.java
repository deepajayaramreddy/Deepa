package com.ibm.fstraining.springgitproject.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.fstraining.springgitproject.model.Product;

public class ExternalProductWareHouseRepository {

	// External Product Ware House Database. In-Memory database created for
	// demo.

	private static List<Product> products = new ArrayList<Product>(Arrays.asList(
			new Product(1, "Product1", "product1", "10/11/2017"), new Product(2, "Product2", "product2", "10/12/2017"),
			new Product(3, "Product10", "product3", "11/02/2017"), new Product(4, "Product4", "product4", "3/09/2017"),
			new Product(5, "Product5", "product5", "3/09/2017"), new Product(6, "Product6", "product6", "3/09/2017"),
			new Product(7, "Product5", "product7", "3/09/2017"), new Product(8, "Product8", "product8", "3/09/2017"),
			new Product(9, "Product9", "product9", "3/09/2017"),
			new Product(10, "Product10", "product10", "3/09/2017")));

	public boolean isProductAvailable(Product product) {
		for (Product prod : products) {
			if (prod.getProductName().equals(product.getProductName())) {
				return true;
			}
		}
		return false;
	}

}
