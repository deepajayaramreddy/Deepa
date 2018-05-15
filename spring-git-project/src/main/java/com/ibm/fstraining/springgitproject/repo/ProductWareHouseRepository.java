package com.ibm.fstraining.springgitproject.repo;

import com.ibm.fstraining.springgitproject.model.Product;

public interface ProductWareHouseRepository {
	
	boolean isProductAvailable(Product product);

}
