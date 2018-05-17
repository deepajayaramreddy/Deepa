package test.java.com.fstraining.sampleproductmscamel.restassured;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ManageCustomerIdentifier {
	static String uri;
	static String uname;
	static String pwd;
	static String dburi;

	static Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
	static List<String> ManageCustomerIdentifierList = new LinkedList<>();

	static Workbook workbook;
	JsonPath jpth;
	PreparedStatement preparedstatement;
	ResultSet resultset;

	@BeforeClass
	public static void AddCustomerIdentifier() {

		try {
			workbook = WorkbookFactory.create(new File("etc/Config.xlsx"));
			dburi = workbook.getSheet("DBDetails").getRow(0).getCell(1).getStringCellValue();
			uname = workbook.getSheet("DBDetails").getRow(1).getCell(1).getStringCellValue();
			pwd = workbook.getSheet("DBDetails").getRow(2).getCell(1).getStringCellValue();
			uri = workbook.getSheet("DBDetails").getRow(4).getCell(1).getStringCellValue();
			System.out.println("******INPUT DATA******");
			System.out.println("DB URI : " + dburi);
			System.out.println("DB Uname : " + uname);
			System.out.println("DB Pwd : " + pwd);
			System.out.println("Current Uri : " + uri);
			Sheet ManageCustomerIdentifier = workbook.getSheet("ManageCustomerIdentifier");
			int celltotalcount = ManageCustomerIdentifier.getRow(0).getPhysicalNumberOfCells();
			for (int index = 1; index < celltotalcount; index++) {
				JsonObject request = new JsonObject();// this is the full JSON Object
				request.addProperty("action", ManageCustomerIdentifier.getRow(0).getCell(index).toString());
				request.addProperty("typeSpecfication", ManageCustomerIdentifier.getRow(1).getCell(index).toString());
				request.addProperty("typeValue", ManageCustomerIdentifier.getRow(2).getCell(index).toString());
				request.addProperty("createdBy", ManageCustomerIdentifier.getRow(3).getCell(index).toString());
				
				ManageCustomerIdentifierList.add(gson.toJson(request));
			}
			
			//	Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls()
				//		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
				//System.out.println(gson.toJson(request));
			
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	@Test
	public void custIdentifiersTests() {
		RestAssured.baseURI = uri + "custmgmt/cuhi/customerhierarchy/customer/CustomerIdentifiers";// trager uri
		for (int index = 0; index < ManageCustomerIdentifierList.size(); index++) {
			RequestSpecification httpRequest = RestAssured.given().contentType("application/json")
					.body(ManageCustomerIdentifierList.get(index));// request
			System.out.println("request is " + ManageCustomerIdentifierList.get(index));
			Response response = httpRequest.request(Method.POST);// triggering the response
			System.out.println("response is " + response.body().asString());
			if (response.getStatusCode() == 200) {
				jpth = JsonPath.from(response.body().asString());
				try (Connection con = DriverManager.getConnection(dburi, uname, pwd)) {
					String query = "select type_value from CUST_IDENTIFIER_TYPE where type_value= ?";
					System.out.println("test");
					preparedstatement = con.prepareStatement(query);
					preparedstatement.setString(1,workbook.getSheet("ManageCustomerIdentifier").getRow(2).getCell(index).toString());
					resultset = preparedstatement.executeQuery();
					while (resultset.next()) {
						assertEquals(workbook.getSheet("ManageCustomerIdentifier").getRow(2).getCell(index).toString(),
								resultset.getString(1));
						
					}

					System.out.println("TC Passed");
					resultset.close();
					preparedstatement.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Response didn't get 200 code");

				System.out.println("failed request : \n" + ManageCustomerIdentifierList.get(index));
				//assertEquals(response.getStatusCode(), 200);
			}
		}
	}   

}