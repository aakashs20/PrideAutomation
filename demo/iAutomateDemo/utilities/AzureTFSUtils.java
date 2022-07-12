package com.project.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.project.testbase.TestBase;

public class AzureTFSUtils extends TestBase {
	
	public String getWebPlanDetailsUrl="/SafetyChain%20Web/_apis/test/plans";
	public String getWebSuiteDetailsUrl="/SafetyChain%20Web/_apis/test/Plans/";
	public String getWebExecuteDetailsUrl="/SafetyChain%20Web/_apis/testplan/Plans/";
	
	public String getMobilePlanDetailsUrl="/SafetyChain%20Mobile/_apis/test/plans";
	public String getMobileSuiteDetailsUrl="/SafetyChain%20Mobile/_apis/test/Plans/";
	public String getMobileExecuteDetailsUrl="/SafetyChain%20Mobile/_apis/testplan/Plans/";
	
	public String getPCAppPlanDetailsUrl="/SafetyChain%20PC%20App/_apis/test/plans";
	public String getPCAppSuiteDetailsUrl="/SafetyChain%20PC%20App/_apis/test/Plans/";
	public String getPCAppExecuteDetailsUrl="/SafetyChain%20PC%20App/_apis/testplan/Plans/";
	
	public String getPlanDetailsUrl;
	public String getSuiteDetailsUrl;
	public String getExecuteDetailsUrl;
	
	public static  HashMap<String, String> genericParam = new HashMap<String, String>();
	static {
		genericParam.put("api-version", "5.0");
	}
	public static  HashMap<String, String> updateParam = new HashMap<String, String>();
	static {
		updateParam.put("api-version", "5.1");
	}
	
	
	ApiUtils apiutils=new ApiUtils();
	
	String jsonBody="";
	public HashMap<String, String> param =  new HashMap<String, String>();
	
	
	public static Properties prop = new Properties();
	
	public AzureTFSUtils() throws IOException {
		FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE);
		prop.load(fis);
	}
	
	/*********************** REST API Methods ***********************/ 

	public int getUniquePlanId(String url, String path, Map<String, String> param, String planName)
		 {
		int uniquePlanId =  0;
		try {
		RestAssured.baseURI = url+path;
		Response response = given()
				.log().all().auth().preemptive()
				.basic(azure_userEmail, azure_userPassword)
				.queryParams(param).get().then().extract().response();
//		logInfo("Response recieved is : " + response.prettyPrint());
		
		Gson gson = new Gson();
		SingleResponse sr = gson.fromJson(response.asString(), SingleResponse.class);
		for(int i=0; i<sr.value.size();i++)
		{
			if (sr.value.get(i).name.equalsIgnoreCase(planName))
			{
				
				uniquePlanId = sr.value.get(i).id;
				logInfo("The value for plan name "+planName+" is "+uniquePlanId);
				
			}
		}
		
		}
		catch(Exception e){
			logInfo("Failed to get value for plan name "+planName);
		}
		return uniquePlanId;
	}
	
	//Used to find suite id for Regression suite name of given plan id 
	public int getUniqueSuiteId(String url, String path, Map<String, String> param, String SuiteName)
	{
		int uniqueSuiteId =  0;
		try {
			RestAssured.baseURI = url+path;
			Response response = given()
					.log().all().auth().preemptive()
					.basic(azure_userEmail, azure_userPassword)
					.queryParams(param).get().then().extract().response();
			
			Gson gson = new Gson();
			SingleResponse sr = gson.fromJson(response.asString(), SingleResponse.class);
			for(int i=0; i<sr.value.size();i++)
			{
				if (sr.value.get(i).name.equalsIgnoreCase(SuiteName))
				{
					
					uniqueSuiteId = sr.value.get(i).id;
					logInfo("The value for plan name "+SuiteName+" is "+uniqueSuiteId);
				}
			}			
		}
		catch(Exception e){
			logInfo("Failed to get value for plan name "+SuiteName);
		}
		return uniqueSuiteId;
	}
	
	public int getUniqueTCExecutionId(String url, String path, int tcid)
	{
		int uniqueTestId =  0;
		try {
			RestAssured.baseURI = url+path;
			logInfo(url+path);
			Response response = given()
					.log().all().auth().preemptive()
					.basic(azure_userEmail, azure_userPassword).get().then().extract().response();
			
			
			Gson gson = new Gson();
			SingleResponse sr = gson.fromJson(response.asString(), SingleResponse.class);
			logInfo("size"+sr.value.size());
			for(int i=0; i<sr.value.size();i++)
			{
				if(sr.value.get(i).testCaseReference.id==tcid)
				{
					uniqueTestId=sr.value.get(i).id;
					logInfo("The value of execution id name "+tcid+" is "+uniqueTestId);
					break;
				}
				
			}			
		}
		catch(Exception e){
			logInfo("error"+e);
			logInfo("Failed to get value for test case id "+tcid);
		}
		return uniqueTestId;
	}
	
	public boolean updateTestCaseStatusAsPass(String url, String path, Map<String, String> param, int tcid)
	{
		String requestBody = "[\n{\n\"id\": "+tcid+",\n\"results\": {\n\"outcome\": 2\n}\n}\n]";
		int result=0;
		try {
			RestAssured.baseURI = url+path;
			Response response = given()
					.log().all().auth().preemptive()
					.basic(azure_userEmail, azure_userPassword)
					.header("content-type", "application/json")
					.body(requestBody)
					.queryParams(param).patch().then().extract().response();
			
			result =response.getStatusCode();
					
		}
		catch(Exception e){
			logInfo("Failed to get value for plan name "+result);
			return false;
		}
		if (result == 200)
			{
			logInfo("Successfully marked test case as Pass ");
			return true;
			}
		else
		{
			logInfo("Successfully marked test case as Pass ");
			return false;
		}
	}
	
	
	public boolean markAllPassTCSInTFS() { 
		String[] tID = null;
		
		switch(azure_project) {
		case "WEB"    :  getPlanDetailsUrl = getWebPlanDetailsUrl;
						 getSuiteDetailsUrl = getWebSuiteDetailsUrl;
						 getExecuteDetailsUrl = getWebExecuteDetailsUrl;
						 break;
		case "MOBILE" :  getPlanDetailsUrl = getMobilePlanDetailsUrl;
						 getSuiteDetailsUrl = getMobileSuiteDetailsUrl;
						 getExecuteDetailsUrl = getMobileExecuteDetailsUrl;
						 break;
		case "PCAPP" :   getPlanDetailsUrl = getPCAppPlanDetailsUrl;
					     getSuiteDetailsUrl = getPCAppSuiteDetailsUrl;
					     getExecuteDetailsUrl = getPCAppExecuteDetailsUrl;
					     break;
		default :		 logError(""
						 .concat("Value set for Plan Details Url - " + getPlanDetailsUrl + " is not proper\n")
						 .concat("Value set for Suite Details Url - " + getSuiteDetailsUrl + " is not proper\n")
						 .concat("Value set for Execution Details Url - " + getExecuteDetailsUrl + " is not proper")
						 .concat("Since 'azure_project' property is set to - " + azure_project)
						 );
						return false;
		}

		try {
			if(passedTestCaseIds.contains(currentTestcaseName)) {

				tID = CommonMethods.splitAndGetString(currentTestcaseName, "_");

				int planId = getUniquePlanId(azure_baseUrl, getPlanDetailsUrl, genericParam, azure_PlanName);
				if(planId == 0) 
					return false;

				// get module for testcase
				String getCurrentTCModule = TC_Modules.get(currentTestcaseName); 

				String getSuiteDetailsUrls= getSuiteDetailsUrl+planId+"/suites";
				int suiteId = getUniqueSuiteId(azure_baseUrl, getSuiteDetailsUrls, genericParam, getCurrentTCModule);

				if(suiteId == 0) 
					return false;
				
				for(int i = 1; i<tID.length;i++){
					String getTCDetailsUrl= getExecuteDetailsUrl+planId+"/Suites/"+suiteId+"/TestPoint";
					int executionTID = getUniqueTCExecutionId(azure_baseUrl, getTCDetailsUrl, Integer.parseInt(tID[i]));
					if(executionTID == 0) 
						return false;

					String patchTCDetailsUrl= getExecuteDetailsUrl+planId+"/Suites/"+suiteId+"/TestPoint";
					if(!updateTestCaseStatusAsPass(azure_baseUrl, patchTCDetailsUrl, updateParam, executionTID)) 
						return false;

					logInfo("[AZURE] " + tID[i] + " testcase has been marked as PASSED in TFS Successfully");
					counter++;
				}
				return true;
			}

			return false;

		}
		catch(Exception e) {
			logError("Failed to mark test case " + tID[1] +" as PASSED in TFS "+ " - "
					+ e.getMessage());
			return false;
		}
	}	

	
	public class MODULES{
		public static final String VERIFICATIONS ="Verifications";
		public static final String VALIDATIONS ="Validation";
		public static final String FSQA_DETAILS ="FSQA Browser > Details Tab";
		public static final String FSQA_RESOURCES ="FSQA Resource";
		public static final String RECORD_MANAGEMENT ="Record Management";
		public static final String FSQA_TASKS ="FSQA Browser > Tasks";
		public static final String INBOX ="Inbox";
		public static final String MANAGE_REQUIREMENTS ="Manage Requirement";
		public static final String SERVICE_TESTING ="Service Testing";
		public static final String FSQA_DOCUMENTS ="FSQA>Documents";
		public static final String PROGRAM_VIEWS ="Program Views";
		public static final String SUPPORT_PORTAL ="Supplier Portal";
		public static final String DYNAMIC_FLOW ="Dynamic Flow";
		public static final String DATA_SECURITY ="Data Security";
		public static final String WEBHOOKS ="WebHooks";
		public static final String NOTIFICATIONS ="Notification";
		public static final String FORM_MANAGER ="Form Manager";
		public static final String DPT ="DPT";
		public static final String FORM_DESIGNER ="Form Designer";
		public static final String SSO ="SSO";
		public static final String RULE_BUILDER ="Rule Builder";
		public static final String FSQA_FORMS ="FSQA Browser - Forms Tab";
		public static final String DASHBOARD ="Dashboard";
		public static final String DMS ="DMS";
		public static final String TASK_SCHEDULER ="Task Scheduler";
		public static final String ADMIN_TOOLS ="Admin Tools";
		public static final String DEVICES ="Devices";
		public static final String LINKS ="Links";
		public static final String TASK_EXPIRATION ="Task Expiration";
	}


	public class SingleResponse{
		public List<value> value;
	}
	
	public class value{
		public int id;
		public String name;
		public testCaseReference testCaseReference;
	}
	public class testCaseReference{
		public int id;
		public String name;
	}
	
}