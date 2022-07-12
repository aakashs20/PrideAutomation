package com.project.utilities;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.project.testbase.TestBase;

public class DynamicTestNGFile {

	List<Map<String, String>> configrowList;
	List<String> workStream = new ArrayList<String>();
	List<String> runtype = new ArrayList<String>();
	String runType;

	/**
	 * This method is used to Update the testng.xml file with the values fed into an excel
	 * under test-data-files
	 * @author hingorani_a
	 * @param suitename The name of the excel suite that needs to picked for TCs to be 
	 * updated to testng.xml
	 * @param lstUISuites The list of XmlSuites that are part of the current execution.
	 * This value is handled/passed when execution starts.
	 */
	public void runTestNGTest(String suitename, List<XmlSuite> lstUISuites) {
		
		String fileName = System.getProperty("user.dir") + "\\test-data-files\\"+suitename+".xlsx";

		ExcelReader reader = new ExcelReader();
		
		// Create an instance of TestNG
		TestNG myTestNG = new TestNG();

		// Create an instance of XML Suite and assign a name for it.
		XmlSuite uiSuite = new XmlSuite();
		try {
			uiSuite.setName("UITests");
//			List<XmlSuite> lstUISuites = new ArrayList<XmlSuite>();
			uiSuite.addListener("com.project.utilities.Listener");
			uiSuite.addListener("com.project.utilities.AnnotationTransformer");

			// Create a list which can contain the classes that you want to run.
			XmlClass xmlClassName = null;

			// Read Excel sheet for getting list of classes and testcases
			List<String> lstSheetNames = reader.getListOfSheetNames(fileName);
			List<Map<String, String>> rowList;

			// Add a for loop here for creating tests, 1 per workstream.
			for (int iSheetNames = 0; iSheetNames <= lstSheetNames.size() - 1; iSheetNames++) {
				if (lstSheetNames.get(iSheetNames).equalsIgnoreCase("config")) {

					configrowList = reader.readSheetDataFromExcel(fileName, lstSheetNames.get(iSheetNames));

					for (int i = 0; i < configrowList.size(); i++) {

						if (!configrowList.get(i).get("Execute").equalsIgnoreCase("N")) {
							runtype.add(configrowList.get(i).get("Execute"));
							workStream.add(configrowList.get(i).get("WorkStream"));

						}
					}
				}

			}
			for (int j = 0; j < workStream.size(); j++) {
				runType = runtype.get(j);
				for (int iSheetNames = 0; iSheetNames <= lstSheetNames.size() - 1; iSheetNames++) {
					if (!lstSheetNames.get(iSheetNames).equalsIgnoreCase("config")
							& lstSheetNames.get(iSheetNames).equalsIgnoreCase(workStream.get(j))) {
						// Create an instance of XmlTest and create a list to
						// hold
						// all tests or workstream names
						XmlTest xmlTests = new XmlTest(uiSuite);

						List<XmlClass> lstClasses = new ArrayList<XmlClass>();
						ArrayList<String> classNames = new ArrayList<>();

						String strSheetName = lstSheetNames.get(iSheetNames);

						// Set Name for Tests section
						xmlTests.setName(strSheetName);

						xmlTests.setParameters(setParameter(strSheetName));

						rowList = reader.readSheetDataFromExcel(fileName, strSheetName);

						// Create list of classes in the sheet.
						for (int i = 0; i <= rowList.size() - 1; i++) {
							String strClassName = rowList.get(i).get("ClassName");
//							int size = runtype.size();

							if (runType.equalsIgnoreCase("All")) {
								classNames.add(strClassName);
							} else {
								if (rowList.get(i).get(runType).equalsIgnoreCase("Y")) {
									classNames.add(strClassName);
								}

							}
							
							// Get Module names for Testcase and put in a HashMap
							String moduleName = rowList.get(i).get("ModuleName");
							if(moduleName==null) {
								// do nothing
							}
							else if(moduleName!="") {
									TestBase.TC_Modules.put(rowList.get(i).get("TestcaseName"),rowList.get(i).get("ModuleName"));
							}
							
							/*
							 * if(rowList.get(i).get("Regression").
							 * equalsIgnoreCase(
							 * "Y")&rowList.get(i).get("Smoke").equalsIgnoreCase
							 * ("Y" )) {
							 * xmlTests.addIncludedGroup("Regression");
							 * xmlTests.addIncludedGroup("Smoke"); }else
							 * if(rowList.get(i).get("Regression").
							 * equalsIgnoreCase(
							 * "N")&rowList.get(i).get("Smoke").equalsIgnoreCase
							 * ("Y" )) { xmlTests.addIncludedGroup("Smoke");
							 * }else if(rowList.get(i).get("Regression").
							 * equalsIgnoreCase(
							 * "Y")&rowList.get(i).get("Smoke").equalsIgnoreCase
							 * ("N" )) {
							 * xmlTests.addIncludedGroup("Regression"); }
							 */

						}

						// Extract only unique classes from the excel sheet.
						Set<String> uniqueClasses = new HashSet<String>(classNames);

						// Add classes to Test
						for (String temp : uniqueClasses) {
							List<XmlInclude> lstMethods = new ArrayList<XmlInclude>();

							xmlClassName = new XmlClass(temp, false);

							// Add methods to the class
							for (int iMethodCount = 0; iMethodCount <= rowList.size() - 1; iMethodCount++) {
								if (runType.equalsIgnoreCase("All")) {
									if (temp.equals(rowList.get(iMethodCount).get("ClassName"))) {
										XmlInclude method = new XmlInclude(rowList.get(iMethodCount).get("TestcaseName"));
										if(rowList.get(iMethodCount).get("Jira Issue Key")!=null)
										{
											method.addParameter("Jira Issue Key", rowList.get(iMethodCount).get("Jira Issue Key"));
										}
										lstMethods.add(method);
										//lstMethods.add(new XmlInclude(rowList.get(iMethodCount).get("TestcaseName")));
									}
								} else {
									if (rowList.get(iMethodCount).get(runType).equalsIgnoreCase("Y")) {
										if (temp.equals(rowList.get(iMethodCount).get("ClassName"))) {
											XmlInclude method =new XmlInclude(rowList.get(iMethodCount).get("TestcaseName"));
											if(rowList.get(iMethodCount).get("Jira Issue Key")!=null)
											{
												method.addParameter("Jira Issue Key", rowList.get(iMethodCount).get("Jira Issue Key"));
											}
											lstMethods.add(method);
											//lstMethods.add(new XmlInclude(rowList.get(iMethodCount).get("TestcaseName")));
										}
									}
								}
							}
							xmlClassName.setIncludedMethods(lstMethods);
							lstClasses.add(xmlClassName);
						}
						classNames.clear();
						rowList.clear();

						// Assign this class to the XmlTest Object created
						// earlier.
						xmlTests.setXmlClasses(lstClasses);

					}
				}
			}
			if(workStream.size()>1)
			{
				uiSuite.setThreadCount(workStream.size());
				uiSuite.setParallel(XmlSuite.ParallelMode.TESTS);
			}

			// Add the suite to the list of suites.
			lstUISuites.add(uiSuite);

			// Set the list of Suites to the testNG object you created earlier.
			myTestNG.setXmlSuites(lstUISuites);
			uiSuite.setFileName("UICasesTestNG.xml");
			createXmlFile(uiSuite);
			System.out.println(uiSuite.toXml());
			
//			myTestNG.run();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to Update the testng.xml file with the values fed into an excel
	 * under test-data-files
	 * @author hingorani_a
	 * @param mSuite The list of Suites that is set to the testNG object created;
	 * the value of the same is flushed to the testng.xml 
	 */
	public void createXmlFile(XmlSuite mSuite) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File("testng.xml"));
			writer.write(mSuite.toXml());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to set Parameters listed in excel to the testng.xml file being updated
	 * @author hingorani_a
	 * @param strSheetName The excel sheet name from where parameters and their values are picked.
	 * Parameter like WorkStream, Env, SlackChannelName and ProjectId
	 * @return Map A Map of String Key and String Values will be returned with the set of Parameters
	 * where Parameter name would be key and the desired value set for it would be value
	 */
	public Map<String, String> setParameter(String strSheetName) {
		Map<String, String> mapConfig = new HashMap<String, String>();
		for (int n = 0; n < configrowList.size(); n++) {
			if (configrowList.get(n).get("WorkStream").equalsIgnoreCase(strSheetName)) {
//				int size = configrowList.get(n).size();

				if (configrowList.get(n).containsKey("Env")) {
					mapConfig.put("Env", configrowList.get(n).get("Env"));
				}
				if (configrowList.get(n).containsKey("SlackChannelName")) {
					mapConfig.put("SlackChannelName", configrowList.get(n).get("SlackChannelName"));
				}
				if (configrowList.get(n).containsKey("ProjectId")) {
					mapConfig.put("ProjectId", configrowList.get(n).get("ProjectId"));
				}

			}
		}
		return mapConfig;
	}


	//@Test public void dynamicTest() throws IOException, EncryptedDocumentException, InvalidFormatException
	//{
		//runTestNGTest();
	//}

}