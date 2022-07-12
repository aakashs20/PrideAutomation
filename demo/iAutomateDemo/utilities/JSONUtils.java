package com.project.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.testbase.TestBase;

public class JSONUtils extends TestBase{		
	
	/**
	* Compares an attribute value in the response and the actual expected value.
	* 
	* @param strReqJSON [String] : Request JSON 
	* @param ExpectedKey [List of String] : Complete path of the attribute under consideration	*  
	* @param strExpectedVal [String] : Expected value of the attribute 
	* 
	* @return [Boolean] True if expected and actual values of the attribute match.  
	*/	
	
	public static Boolean CompareResponseJSON (String strReqJSON, List <String> ExpectedKey,String strExpectedVal) throws ParseException
	{
		JSONObject jsonObject = null;		
		JSONArray arrJSON = null;
		String strLastKey = null;
		String currKey = null;
		String strClassName = null;
		int iIndex = 0;
		int iArrayIndex = 0;
		String strIndexToUpdate = null;
		Boolean result = true;		
		JSONParser parser = new JSONParser(); 
				
		ExpectedKey.add(0, "Level1");
		Iterator<String> itrKeyList = ExpectedKey.iterator();
		
		strReqJSON = "{\"Level1\":" + strReqJSON + "}"; 
		jsonObject = (JSONObject)parser.parse(strReqJSON);		
		
		strLastKey = ExpectedKey.get(ExpectedKey.size()-1);
		if (strLastKey.contains("["))
		{		
			iArrayIndex = strLastKey.indexOf("[")+1;
			
			strIndexToUpdate = strLastKey.substring(iArrayIndex,iArrayIndex+1);
			iIndex = Integer.parseInt(strIndexToUpdate);
			iIndex--;			
							
			String newCurrKey = strLastKey.substring(0, iArrayIndex-1);				
			strLastKey = newCurrKey;
		}	
		
		while(itrKeyList.hasNext())
		{
			
			currKey = itrKeyList.next();
			if (currKey.contains("["))
			{
				//Attribute Name
				iArrayIndex = currKey.indexOf("[")+1;				
				String newCurrKey = currKey.substring(0, iArrayIndex-1);					
				
				// Index Id
				strIndexToUpdate = currKey.substring(iArrayIndex,iArrayIndex+1);
				iIndex = Integer.parseInt(strIndexToUpdate);
				iIndex--;
				
				currKey = newCurrKey;
			}	
			
			if (itrKeyList.hasNext())
			{		
				
				strClassName = jsonObject.get(currKey).getClass().toString();	
								
				if (strClassName.equals("class org.json.simple.JSONObject"))
				{
					jsonObject = recursiveConvertor(jsonObject,currKey,strLastKey);
				}
				if (strClassName.equals("class org.json.simple.JSONArray"))
				{
					arrJSON = getJSONArray(jsonObject,currKey);
					jsonObject = (JSONObject) arrJSON.get(iIndex);
				}
			}
			if (jsonObject.containsKey(strLastKey) && !itrKeyList.hasNext())
			{
				String strActualVal = null;
								
				try
				{
				strClassName = jsonObject.get(strLastKey).getClass().toString();
				}
				catch(Exception E)
				{
					if (jsonObject.get(strLastKey)==null)
					{
						strClassName = "";
					}
				}
				if (strClassName.equals("class org.json.simple.JSONArray"))
				{
					arrJSON = (JSONArray)jsonObject.get((strLastKey));
					strActualVal = arrJSON.get(iIndex).toString();					
				}
				else
				{
					if (jsonObject.get(strLastKey)!=null)
					{
						strActualVal = jsonObject.get(strLastKey).toString();
					}
					else
					{
						strActualVal = "";
					}
				}				
				
				if (strActualVal.equals(strExpectedVal))
				{
					result = true;
				}
				else
				{
					result = false;
				}				
			}			
		}		
		return(result);
	}	
	
	/**
	* Updates a Request JSON for the stated attribute with a different value 
	* 
	* @param strOrgParams [String] : Request JSON 
	* @param list [List of String] : Complete path of the attribute under consideration	*  
	* @param strValFromExcel [String] : Expected value of the attribute 
	* 
	* @return [String] Updated JSON in String format.  
	*/	
	
	@SuppressWarnings("unchecked")
	public static String updateRequestJSON (String strOrgParams, List <String> list,String strValFromExcel) throws ParseException
	{
		JSONObject jsonObject = null;		
		JSONArray arrJSON = null;
		String strLastKey = null;
		String currKey = null;
		String strClassName = null;
		int iIndex = 0;
		int iArrayIndex = 0;
		String strIndexToUpdate = null;
		JSONParser parser = new JSONParser(); 
		
		
		strOrgParams = "{\"Level1\":" + strOrgParams + "}"; 
		jsonObject = (JSONObject)parser.parse(strOrgParams);		
		
		list.add(0, "Level1");
		Iterator<String> itrKeyList = list.iterator();
		
		HashMap<String, Object> mapAllElements = new HashMap<String, Object>();		
		
		strLastKey = list.get(list.size()-1);		
		
		if (strLastKey.contains("["))
		{		
			iArrayIndex = strLastKey.indexOf("[")+1;				
			String newCurrKey = strLastKey.substring(0, iArrayIndex-1);				
			strLastKey = newCurrKey;					
		}				
		
		while(itrKeyList.hasNext())
		{	
			currKey = itrKeyList.next();	
			
			if (currKey.contains("["))
			{
				//Attribute Name
				iArrayIndex = currKey.indexOf("[")+1;				
				String newCurrKey = currKey.substring(0, iArrayIndex-1);					
				
				// Index Id
				strIndexToUpdate = currKey.substring(iArrayIndex,iArrayIndex+1);
				iIndex = Integer.parseInt(strIndexToUpdate);
				iIndex--;
				
				currKey = newCurrKey;
			}			
		
			if (itrKeyList.hasNext())
			{
				strClassName = jsonObject.get((currKey)).getClass().toString();
				
				if (strClassName.equals("class org.json.simple.JSONObject"))
				{
					jsonObject = recursiveConvertor(jsonObject,currKey,strLastKey);
				}
				if (strClassName.equals("class org.json.simple.JSONArray"))
				{
					arrJSON = getJSONArray(jsonObject,currKey);
					jsonObject = (JSONObject) arrJSON.get(iIndex);
				}
			}
				if (jsonObject.containsKey(strLastKey) && !itrKeyList.hasNext())
				{
				
					if (jsonObject.get(strLastKey)==null)
					{		
						if (strLastKey.contains("Date") || strLastKey.contains("date")) 
						{
							if (strValFromExcel.contains("/"))
							{ 
								strValFromExcel = strValFromExcel.replace("/", "-"); 
							} 
						}	
						jsonObject.put(strLastKey,strValFromExcel);
						mapAllElements.put(currKey, jsonObject);
						break;
					}
					else
					{						
							strClassName = jsonObject.get(strLastKey).getClass().toString();
							if (strClassName.equals("class org.json.simple.JSONArray"))
							{
								if (itrKeyList.hasNext())
								{
									currKey = itrKeyList.next();
								}
								
								if (currKey.contains("["))
								{
									//Attribute Name
									iArrayIndex = currKey.indexOf("[")+1;				
									String newCurrKey = currKey.substring(0, iArrayIndex-1);					
									
									// Index Id
									strIndexToUpdate = currKey.substring(iArrayIndex,iArrayIndex+1);
									iIndex = Integer.parseInt(strIndexToUpdate);
									iIndex--;
									
									currKey = newCurrKey;
								}
								
								arrJSON = (JSONArray)jsonObject.get((currKey));
								arrJSON.remove(iIndex);
								arrJSON.add(iIndex,strValFromExcel);
								jsonObject.put(strLastKey, arrJSON);
								mapAllElements.put(currKey, jsonObject);
								break;
							}
						}
					if (strClassName.equals("class org.json.simple.JSONObject") || strClassName.equals("class java.lang.String") || strClassName.equals("class java.lang.Long") || strClassName.equals("class java.lang.Double"))
					{
						if (strLastKey.contains("Date") || strLastKey.contains("date")) 
						{
							if (strValFromExcel.contains("/"))
							{ 
								strValFromExcel = strValFromExcel.replace("/", "-"); 
							} 
						}						
						jsonObject.put(strLastKey,strValFromExcel);
						mapAllElements.put(currKey, jsonObject);
						break;
					}						
				}									
			
			mapAllElements.put(currKey, jsonObject);
		}		
		return (mapAllElements.get("Level1").toString());
	}
	
	
	/**
	* Extracts xPath attributes from a complete path separated by '.' [for eg: data.form.compliance}
	* 
	* @param strCompletexPath [String] : Header Cell value 
	* 
	* @return [List of String]  XPath attributes extracted into a list.  
	*/
	
	
	public List<String> ExtractxPathFromHeaderCellValue (String strCompletexPath) {
	       
	       List <String> lstXPathAttrib = new ArrayList<String>();
	       //lstHeaderXPath.add("Level1");
	       String[] strAttributes = strCompletexPath.split("\\.");
	       
	       for (int iCount=0; iCount<strAttributes.length;iCount++)
	       {
	              String val = strAttributes[iCount];
	              lstXPathAttrib.add(val);
	       }
	       
	       return (lstXPathAttrib);
	}
	
	/**
	 * This method is used to recursively convert and return a JSON object with specified attribute.
	 * @param jsonObject - JSON Object to be used
	 * @param attrbToFind - Attribute to find  [String]
	 * @param strLastKey - Last Key [String]
	 * @return Converted JSON Object
	 */
	public static JSONObject recursiveConvertor (JSONObject jsonObject,String attrbToFind,String strLastKey) 
	{        					
		JSONObject newJSONObject = null;		
		
		if (jsonObject.containsKey(attrbToFind))
		{			
				newJSONObject = (JSONObject)jsonObject.get((attrbToFind));
				return (newJSONObject);						
		}						
		return (jsonObject);		
	}
	
	/**
	 * This method is used to get JSONArray of attributes form a JSON Object.
	 * @param jsonObject - JSON Object 
	 * @param attrbToFind - Attribute to find [String]
	 * @return JSONArray of Attribute
	 */
	public static JSONArray getJSONArray (JSONObject jsonObject,String attrbToFind) 
	{        					
		JSONArray newJSONArray = null;
		String strClassName = jsonObject.get(attrbToFind).getClass().toString();
		if (strClassName.equals("class org.json.simple.JSONArray"))
		{
			newJSONArray = (JSONArray)jsonObject.get((attrbToFind));
			return (newJSONArray);
		}
		return null;				
	}
	
	
	/**
	 * This method is used to create dynamic Path for Json.
	 * @author choubey_a
	 * @param jsonPath - The path to be manipulated
	 * @param replaceWith String to be replaced
	 * @param replaceTo String to be replaced with
	 * @return String This returns jsonPath created dynamically
	 */
	public String perform_GetDynamicJsonPath(String jsonPath, String replaceWith, String replaceTo){
		String finalJsonPath = null;
		try{
			finalJsonPath = jsonPath.replaceAll(replaceWith, replaceTo);
		}
		catch(Exception e){
			logError("Failed to get dynamic xpath - " + finalJsonPath + " - "
					+ e.getMessage());
		}
		return finalJsonPath;
	}
	
}			
	

