package com.project.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.project.testbase.TestBase;

public class Retry implements IRetryAnalyzer {
  private int retryCount =0;
  private int maxRetryCount =1;
@Override
public boolean retry(ITestResult result) {
	
	if(retryCount < maxRetryCount){
		log("Retrying "+ result.getName()+ " test cases with status "+ getResultStatusName(result.getStatus()) + " for the "+ (retryCount + 1)+ " time"); 

		retryCount++;
		return true;
	}
	return false;
}

public  String getResultStatusName(int status) {

	String resultName=null;
	if(status==1)
	resultName="SUCCESS";
	if(status==2)
	resultName="FAILURE";
	if(status==3)
	resultName="SKIP";

	return resultName;
	
}
  
public  void log(String message) {
	
	Reporter.log(message);
    TestBase base = new TestBase();
    base.log(message);
}
  
}
