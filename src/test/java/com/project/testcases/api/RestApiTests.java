package com.project.testcases.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.project.testbase.TestBase;
import com.project.utilities.ExcelReader;
import com.project.utilities.Constants;
import com.project.utilities.ExcelReader;
import com.project.utilities.DBHelper;
import com.project.utilities.ApiUtils;

public class RestApiTests extends TestBase{	
	
	public static Boolean isHeaderPresent = true;
	private static String baseJsonRequest;
	public static Logger logger = Logger.getLogger(RestApiTests.class);		
	
	@Test
	public void currencyConversion() throws Exception {
		
		String apiName = "currency_Conversion";
		String apiPath =  System.getProperty("user.dir") + File.separator + "test-data-files" + File.separator + prop.getProperty("api_file_path") + File.separator+ apiName;
		String method = "GET";
		ApiUtils apiUtils = new ApiUtils();
		Map<String, HashMap> reqParamsData;
		Map<String, HashMap> respAttributesData;
		Map<String, String> reqJson = new HashMap<String,String>();
		Map<String,String> headers;
		String username = "";
		String password = "";		
		
		String baseURI = prop.getProperty("baseUrl");
		//commented ny nath_a
		
		/*FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop.load(fis);
		*/
		headers = new HashMap<String,String>();		
		headers.put("x-rapidapi-host", "currency-exchange.p.rapidapi.com");
		headers.put("x-rapidapi-key", "e354326776mshfc218d26a738590p1183afjsn08365f18fdb2");
		
		ExcelReader reader = new ExcelReader();				

		reqParamsData = reader.ReadAllDataFromExcelSheet(apiPath +File.separator+ apiName +".xlsx", "reqParams");
		respAttributesData = reader.ReadAllDataFromExcelSheet(apiPath +File.separator+ apiName +".xlsx", "respAttributes");
		
		reqJson = apiUtils.readAllJsonRequest(apiPath);		
		Iterator reqJsonItr = reqJson.entrySet().iterator(); 
		
		while(reqJsonItr.hasNext()) {	
			
			boolean testCaseFlag = false;
			Map.Entry requestFile = (Map.Entry) reqJsonItr.next();			
			String reqJSON = requestFile.getValue().toString();
			HashMap<String, String> reqParams = reqParamsData.get(requestFile.getKey());
			try
			{
			Response response = apiUtils.submitRequest(method, reqJSON, reqParams, headers, username, password, baseURI);
			
		    int statusCode = response.getStatusCode();
		    
		    if(statusCode == 200) {
		    	String testcaseId = requestFile.getKey().toString();
		    	
		    	testCaseFlag = apiUtils.validateResponse(respAttributesData,testcaseId , response);
		    	
		    	if(testCaseFlag == true) {
		    		passLog("Test Case : " +requestFile.getKey() + " Passed ");
		    		logger.info("");
		    		logger.info("Test Case : " +requestFile.getKey() + "\nPassed : Expected and Actual response values matched");					
			    } else {
			    	failLog("Test Case : " +requestFile.getKey() + " Failed ");
			    	logger.info("Test Case : " + requestFile.getKey() + "\nFailed : Expected and Actual response values not matched");			    	
			    }
			} else {
				failLog("Test Case : " +requestFile.getKey() + "Failed ");
				logger.info("Test Case : " +requestFile.getKey() + "\nFailed with status code  "+ statusCode);				
			}
		}
			catch(Exception e)
			{
				logger.info("Exception Occured in Test Case : " +requestFile.getKey());
				logger.info(e.toString());				
			}			
		}		
	}	
}
