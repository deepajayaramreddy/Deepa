package com.ibm.fstraining.springgitproject.service.rs;

import org.springframework.web.client.RestTemplate;

public class SKUServiceImplementation implements SKUService {

	private RestTemplate restTemplate = new RestTemplate();

	private String externalURL;
	

	@Override
	public String getSku(int productId) {

		//externalURL = System.getenv().getOrDefault("SKU_MSURL","default");
		//externalURL = System.getenv("SKU_MSURL");
		//externalURL = "http://zlp22234.vci.att.com:32001/sampleskumscamel/service/getSku?productId=";
		externalURL = "http://zlt20080.vci.att.com:30642/restservices/echo/v3/testCXF/getSku?productId=";
		//externalURL = System.getenv("SKU_MSURL");
		/*Properties prop = new Properties();
		InputStream input = null;
		String filename = "config.properties";
		try {
			input = getClass().getClassLoader().getResourceAsStream(filename);
			prop.load(input);
			externalURL = prop.getProperty("externalURL");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		System.out.println("externalURL from enviornment variable:" + externalURL);
		System.out.println("Product Id============================="+productId);
		return restTemplate.getForObject(externalURL + productId, String.class);
	}

}
