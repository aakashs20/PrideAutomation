package com.project.utilities;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SessionConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


import org.apache.http.HttpStatus;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;
import org.json.simple.JSONAware;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.project.testbase.TestBase;

@SuppressWarnings("deprecation")
public class ApiUtils extends TestBase {

	static String accessToken;

	// public static Logger logger = Logger.getLogger(RestApiTests.class);
	String jsonBody = "";

	// public static Properties prop = new Properties();
	//
	// public ApiUtils() throws IOException {
	// FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE);
	// prop.load(fis);
	// }

	/*********************** REST API Methods ***********************/

	/**
	 * Triggers a GET request for a REST API. (Needs Refactoring)
	 * 
	 * @param url      [String] : GET API URL.
	 * @param path     [String] : Path.
	 * @param headers  [Map of String, String] : headers.
	 * @param param    [Map of String, String] : Param.
	 * @param username [String] : Username
	 * @param password [String] : Password
	 * 
	 * @return [Response]
	 */

	public Response getRequestAuth(String url, String path, Map<String, String> headers, Map<String, String> param,
			String username, String password) {
		Response res = given().headers(headers).auth().preemptive().basic(username, password).queryParams(param)
				.get(url + path).then().assertThat().extract().response();
		return res;
	}

	/**
	 * Triggers a GET request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * @param param   [Map of String, String] : parameters.
	 * 
	 * @throws Exception
	 * @return [Response]
	 */

	public Response getRequest(String url, String path, Map<String, String> headers, Map<String, String> param)
			throws Exception {
		Response response = RestAssured.given().contentType("application/json").header("Authorization", accessToken)
				.when().relaxedHTTPSValidation().get(url);

		String responseBody = response.getBody().asString();
		System.out.println("getRequest Reponse : " + responseBody);
		JsonPath path1 = new JsonPath(response.getBody().asString());
		String AttachmentIds = path1.getString("id");
		System.out.println(AttachmentIds + " : File Attachments Ids");
		return response;
	}

	/**
	 * Reads all JSON Requests from JSON files in a particular folder.
	 * 
	 * @param path [String] : Folder path where all JSON files are stored
	 * 
	 * @throws Exception
	 * 
	 * @return [HashMap of String,String] : Key is the name of the file. Value is
	 *         the actual request JSON contained in the file.
	 */

	public HashMap<String, String> readAllJsonRequest(String path) throws Exception {

		HashMap<String, String> reqMap = new HashMap<String, String>();

		ExcelReader reader = new ExcelReader();
		List<String> requestJsonfiles = reader.getListOfFilesNames(path, "json");
		logInfo("Reading request JSONs from path : " + path);

		for (int i = 0; i < requestJsonfiles.size(); i++) {

			String jiraID = requestJsonfiles.get(i).substring(0, requestJsonfiles.get(i).length() - 5);

			logInfo("Reading request JSON file : " + requestJsonfiles.get(i).toString());

			String json = readRequestFromFile(path + "\\request\\" + requestJsonfiles.get(i).toString());
			reqMap.put(jiraID, json);
		}
		return reqMap;
	}

	/**
	 * Submits a REST API request
	 * 
	 * @param method      [String] : Can be 'POST'/'GET'/'PUT'/'PATCH'/'DELETE'
	 *                    depending on type of method.
	 * @param requestBody [String] : Request JSON.
	 * @param parameters  [Map of String, String] : Request URL parameters.
	 * @param headers     [Map of String, String] : Headers if applicable to the
	 *                    REST API.
	 * @param username    [String] : username for the API.
	 * @param password    [String] : password for the API.
	 * @param baseURI     [String] : baseURI for the API.
	 * 
	 * @return [Response]
	 */

	public Response submitRequest(String method, String requestBody, Map<String, String> parameters,
			Map<String, String> headers, String username, String password, String baseURI) {

		RequestSpecification requestSpecification;
		requestSpecification = RestAssured.given();
		Response response = null;
		String basePath = "";

		logInfo("Method :" + method);
		logInfo("Base URL :" + baseURI);
		logInfo("Request Parameters  :" + parameters);
		logInfo("Request Body :" + requestBody);

		try {
			RestAssuredConfig config = RestAssured.config().httpClient(
					HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000)
					.setParam(CoreConnectionPNames.SO_TIMEOUT, 30000));
			switch (method) {
			case "POST":
				response = requestSpecification.config(config).baseUri(baseURI).headers(headers)
				.contentType(ContentType.JSON).accept(ContentType.JSON).auth().preemptive()
				.basic(username, password).body(requestBody).params(parameters).basePath(basePath).when()
				.post();
				break;
			case "GET":
				response = requestSpecification.config(config).baseUri(baseURI).headers(headers)
				.contentType(ContentType.JSON).accept(ContentType.JSON).auth().preemptive()
				.basic(username, password).params(parameters).basePath(basePath).when().get();
				break;
			case "PUT":
				response = requestSpecification.config(config).baseUri(baseURI).headers(headers)
				.contentType(ContentType.JSON).accept(ContentType.JSON).auth().preemptive()
				.basic(username, password).body(requestBody).params(parameters).basePath(basePath).when().put();
				break;
			case "DELETE":
				response = requestSpecification.config(config).baseUri(baseURI).headers(headers)
				.contentType(ContentType.JSON).accept(ContentType.JSON).auth().preemptive()
				.basic(username, password).params(parameters).basePath(basePath).when().delete();
				break;
			case "PATCH":
				response = requestSpecification.config(config).baseUri(baseURI).headers(headers)
				.contentType(ContentType.JSON).accept(ContentType.JSON).auth().preemptive()
				.basic(username, password).body(requestBody).params(parameters).basePath(basePath).when()
				.patch();
				break;
			}

		} catch (Exception e) {
			logInfo(e.getMessage());
		}

		logInfo("Response recieved is : " + response.asString());
		return response;
	}

	/**
	 * Validates if the response from a REST API matches with the expected response.
	 * 
	 * @param respAttributesData [Map of String, HashMap] : Key is testcaseID
	 *                           (String). Value is the attributes and their values
	 *                           that need to be matched. (HashMap)
	 * @param strJiraId          [String] : testcaseId that needs to be
	 * @param response           [Response] : Response object received after API is
	 *                           triggered. Use apiUtils.submitRequest() method to
	 *                           get this response.
	 * 
	 * @return [Response]
	 */

	public boolean validateResponse(@SuppressWarnings("rawtypes") Map<String, HashMap> respAttributesData, String strJiraId, Response response)
			throws Exception {

		boolean flag = false;
		String resp = response.asString();

		@SuppressWarnings("rawtypes")
		Map map = respAttributesData.get(strJiraId);
		@SuppressWarnings("rawtypes")
		Iterator mapItr = map.entrySet().iterator();

		while (mapItr.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry attributes = (Map.Entry) mapItr.next();
			if (resp.contains(attributes.getValue().toString())) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		return flag;
	}

	/**
	 * Validates if the response from a REST API matches with the expected response.
	 * Use this if the response has nested JSONs or array elements
	 * 
	 * @param respAttributesData [Map of String, HashMap] : Key is testcaseID
	 *                           (String). Value is the attributes and their values
	 *                           that need to be matched. (HashMap)
	 * @param strJiraId          [String] : testcaseId that needs to be
	 * @param response           [Response] : Response object received after API is
	 *                           triggered. Use apiUtils.submitRequest() method to
	 *                           get this response.
	 * 
	 * @return [Response]
	 */

	@SuppressWarnings("static-access")
	public boolean validateResponseNestedJSON(@SuppressWarnings("rawtypes") Map<String, HashMap> respAttributesData, String strJiraId,
			Response response) throws Exception {

		boolean flag = false;
		JSONUtils jsonutils = new JSONUtils();

		logInfo("Working on testcase : " + strJiraId);

		@SuppressWarnings("rawtypes")
		Map map = respAttributesData.get(strJiraId);

		@SuppressWarnings("rawtypes")
		Iterator mapItr = map.entrySet().iterator();

		String strReqJSON = response.asString();
		logInfo("The response to be validated : " + strReqJSON);

		while (mapItr.hasNext()) {
			Boolean respVal = false;
			@SuppressWarnings("rawtypes")
			Map.Entry attributes = (Map.Entry) mapItr.next();
			String strExpectedKey = attributes.getKey().toString();
			String[] arrKeys = strExpectedKey.split("\\.");

			List<String> lstExpectedKey = new ArrayList<String>(arrKeys.length);
			for (String s : arrKeys) {
				lstExpectedKey.add(s);
			}

			String strExpectedVal = attributes.getValue().toString();

			logInfo("Comparing expected response for Key : " + strExpectedKey);
			if (strExpectedVal == "") {
				logInfo("Value for key : " + strExpectedKey + " is blank in data excel.");
			} else {
				logInfo("Comparing expected response for Value : " + strExpectedVal);
				respVal = jsonutils.CompareResponseJSON(strReqJSON, lstExpectedKey, strExpectedVal);

				if (respVal) {
					flag = true;
					logInfo("Response attrib value matched for key: " + strExpectedKey);
				} else {
					flag = false;
					logInfo("Response attrib value did not match for key: " + strExpectedKey);
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * Triggers a POST request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * @param payload [String] : payload
	 * 
	 * @return [JSONObject]
	 */

	public JSONObject postRequest(String url, String path, Map<String, String> headers, String payload)
			throws Exception {
		Response res = given().headers(headers).contentType(ContentType.JSON).body(payload).post(url + path).then()
				.assertThat().statusCode(HttpStatus.SC_OK).extract().response();
		return new JSONObject(res.asString());
	}

	/**
	 * Triggers a POST request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * @param param   [Map of String, String] : param.
	 * @param payload [String] : payload
	 * 
	 * @return [Response]
	 */

	public Response postRequest(String url, String path, Map<String, String> headers, Map<String, String> param,
			String payload) throws Exception {
		Response res = given().headers(headers).queryParams(param).contentType(ContentType.JSON).body(payload)
				.post(url + path).then().assertThat().statusCode(HttpStatus.SC_OK).extract().response();

		return res;
	}

	/**
	 * Converts contents of a file to a JSON. Use this to extract req JSON from a
	 * file.
	 * 
	 * @param file [String] : Path of attribute for which value is to be retrieved.
	 * 
	 * @return [String] : JSON converted to a string.
	 * 
	 */

	public String generateJsonStringFromFilePath(String file) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject data = (JSONObject) parser.parse(new FileReader(file));// path to the JSON file.

		String json = ((JSONAware) data).toJSONString();
		return json;

	}

	/**
	 * Triggers a PUT request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * @param payload [String] : payload
	 * 
	 * @return [JSONObject]
	 */

	public JSONObject putRequest(String url, String path, Map<String, String> headers, String payload)
			throws Exception {
		Response res = given().headers(headers).contentType(ContentType.JSON).body(payload).put(url + path).then()
				.assertThat().statusCode(HttpStatus.SC_OK).extract().response();
		return new JSONObject(res.asString());
	}

	/**
	 * Triggers a DELETE request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * 
	 * @return [Response]
	 */
	public Response deleteRequest(String url, String path, Map<String, String> headers) throws Exception {
		Response res = given().headers(headers).delete(url + path).then().assertThat().statusCode(HttpStatus.SC_OK)
				.extract().response();
		return res;
	}

	/**
	 * Triggers a DELETE request for a REST API. (Needs Refactoring)
	 * 
	 * @param url     [String] : GET API URL.
	 * @param path    [String] : Path.
	 * @param headers [Map of String, String] : headers.
	 * @param payload [String] : payload.
	 * 
	 * @return [Response]
	 */

	public Response deleteRequest(String url, String path, Map<String, String> headers, String payload)
			throws Exception {
		Response res = given().headers(headers).contentType(ContentType.JSON).body(payload).delete(url + path).then()
				.assertThat().extract().response();

		logInfo(url + path);
		return res;
	}

	/**
	 * Reads contents of a file. Use this to fetch a request from a txt / xml file.
	 * 
	 * @param jsonFilePath [String] : Path of file from which contents are to be
	 *                     read.
	 * 
	 * @return [String]
	 */
	public String readRequestFromFile(String jsonFilePath) throws Exception {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
			String json = "";
			try {
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();

				while (line != null) {
					stringBuilder.append(line);
					stringBuilder.append("\n");
					line = reader.readLine();
				}
				json = stringBuilder.toString();
				return json;
			} finally {
				reader.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Gets attribute value from a JSON. Use this to extract values from a response
	 * for a specific key.
	 * 
	 * @param JSONBody      [String] : The JSON.
	 * @param attributesKey [String] : Path of attribute for which value is to be
	 *                      retrieved.
	 * 
	 * @return [String]
	 */
	public String getAttributesFromJsonForSingleKey(String JSONBody, String attributesKey) throws Exception {
		JsonPath path = JsonPath.from(JSONBody);
		String keyValue = path.getString((attributesKey));
		return keyValue;
	}

	/**
	 * deprecated Gets attribute value from a JSON.
	 * 
	 * @param jsonObj        [String] : The JSON.
	 * @param attributesKeys [String] : Path of attribute for which value is to be
	 *                       retrieved.
	 * 
	 * @return [String]
	 */

	public String getAttribNestedJSON(JSONObject jsonObj, String attributesKeys) throws Exception {
		String strAttribVal = "";
		String[] attribKeyList = attributesKeys.split("[.]");
		String strLastKey = attribKeyList[attribKeyList.length - 1];
		for (String key : attribKeyList) {
			jsonObj = jsonObj.getJSONObject(key);
			if (jsonObj.has(strLastKey)) {
				strAttribVal = jsonObj.get(strLastKey).toString();
				break;
			}
		}
		return strAttribVal;
	}

	public static String ReadConfigFile(String key) throws IOException {
		String value = "";
		FileReader reader = new FileReader(Constants.CONFIG_FILE);
		Properties prop1 = new Properties();
		prop1.load(reader);
		value = prop1.getProperty(key);
		return value;
	}

	/**
	 * Generate random UUID.
	 * 
	 * @return [String] : UUID.
	 */

	public String getUUID() {
		String strUUID = "";
		try {
			UUID uuid = UUID.randomUUID();
			strUUID = uuid.toString();
			logInfo("Created UUID:  " + strUUID);
		} catch (Exception e) {
			logError("Failed while creating UUID - " + e.getMessage());
		}
		return strUUID;
	}

	/**
	 * Used to trigger REST call
	 * 
	 * @return [Response].
	 */
	public Response submitRequest(String method, String requestBody, Map<String, String> headers, String url) {

		RequestSpecification requestSpecification;
		Response response = null;

		try {
			requestSpecification = RestAssured.given();

			logInfo("*******************************************************************************************");
			logInfo("==================================== API Call - START =====================================");
			logInfo("*******************************************************************************************");
			logInfo("For URL : " + url + " Method to be used :" + method);

			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					String headerName = entry.getKey().toString();
					String headerOption = entry.getValue().toString();
					requestSpecification.header(headerName, headerOption);
				}
			}

			if (requestBody != null) {
				requestSpecification.cookie("ARRAffinity",affinityId).cookie("ASP.NET_SessionId", sessionId).body(requestBody);

				logInfo("================================== [Request Body - START] =================================");
				logInfo("Request Body :" + requestBody);
				logInfo("=================================== [Request Body - END] ==================================");

			}

			RestAssuredConfig config = RestAssured.config().httpClient(
					HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000)
					.setParam(CoreConnectionPNames.SO_TIMEOUT, 30000)).sessionConfig(new SessionConfig().sessionIdName("ASP.NET_SessionId"));

			if (method.equals(METHOD_TYPE.POST)) {
				response = requestSpecification.config(config).post(url);

			} else if (method.equals(METHOD_TYPE.GET)) {
				response = requestSpecification.config(config).get(url);
			}

			logInfo("================================ [Response Payload - START] ===============================");
			logInfo("Response recieved is : " + response.prettyPrint());
			logInfo("================================ [Response Payload - END] =================================");
			logInfo("*******************************************************************************************");
			logInfo("====================================  API Call - END  =====================================");
			logInfo("*******************************************************************************************");

			return response;
		} catch (Exception e) {
			logError("Failed to send request : " + response.prettyPrint());
			return response;
		}
	}

	/**
	 * This method is Used to trigger REST call
	 * 
	 * @param method   - Method type for api call
	 * @param formData - Form Data
	 * @param headers  - [Map of String, String] : header data
	 * @param url      - [String] : GET API URL.
	 * @return [Response] Object
	 */
	public Response submitRequestForImages(String method, Map<String, String> formData, Map<String, String> headers,
			String url) {

		RequestSpecification requestSpecification;
		Response response = null;

		try {
			requestSpecification = RestAssured.given();

			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					String headerName = entry.getKey().toString();
					String headerOption = entry.getValue().toString();
					requestSpecification.header(headerName, headerOption);

				}
				logInfo("Request Headers  :" + headers);
			}

			for (Map.Entry<String, String> entry : formData.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				requestSpecification.multiPart(key, value);
			}

			logInfo("formData  :" + formData);

			// if(requestBody != null) {
			// requestSpecification.body(requestBody);
			//
			// requestSpecification.multiPart(file)
			// logInfo("Request Body :" + requestBody );
			// }

			RestAssuredConfig config = RestAssured.config().httpClient(
					HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000)
					.setParam(CoreConnectionPNames.SO_TIMEOUT, 30000));

			logInfo("For URL : " + url + " Method to be used :" + method);
			if (method.equals(METHOD_TYPE.POST)) {
				response = requestSpecification.config(config).post(url);

			} else if (method.equals(METHOD_TYPE.GET)) {
				response = requestSpecification.config(config).get(url);
			}

			System.out.println(response.asString());

			logInfo("Response recieved is : " + response.asString());
			return response;
		} catch (Exception e) {
			logError("Failed to send request : " + response.asString());
			return response;
		}
	}

	public static class METHOD_TYPE {
		public static final String GET = "GET";
		public static final String POST = "POST";
	}

}