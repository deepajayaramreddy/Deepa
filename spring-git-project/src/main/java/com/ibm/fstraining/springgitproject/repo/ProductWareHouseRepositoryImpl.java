package com.ibm.fstraining.springgitproject.repo;

import com.ibm.fstraining.springgitproject.model.Product;

public class ProductWareHouseRepositoryImpl implements ProductWareHouseRepository {

	@Override
	public boolean isProductAvailable(Product product) {
		ExternalProductWareHouseRepository extProductWareHouseRep = new ExternalProductWareHouseRepository();
		return extProductWareHouseRep.isProductAvailable(product);

	}

}
