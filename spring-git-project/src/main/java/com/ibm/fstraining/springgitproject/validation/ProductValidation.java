
package com.ibm.fstraining.springgitproject.validation;

import com.ibm.fstraining.springgitproject.model.Product;

public class ProductValidation {

	public boolean validateProduct(Product product) {
		boolean valStatus = false;
		if (product != null) {

			if (product.getProductName() != null
					&& product.getProductName().length() > 5) {
				valStatus = true;
			}
			if (product.getMfgdate() != null) {
				valStatus = true;
			}
		}
		return valStatus;
	}

}
