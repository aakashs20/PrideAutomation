package com.project.pTracker.Utils;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.project.testbase.TestBase;

public class Retry extends TestBase implements IRetryAnalyzer {

	private int retrycount = 0;
	private int maxretrycount = 3;
	String maxretrycount1 = TestBase.prop.getProperty("maxRetryCount");
	
	//Logic for retrying the test with a particular result status.
	public boolean retry(ITestResult result) {
		//int maxretrycount1 =  Integer.parseInt(prop.getProperty("maxRetryCount"));
		System.out.println("Max retry count is set to -- " + maxretrycount1);
	
		if (retrycount < maxretrycount) {
			logInfo("Retrying Class " + result.getTestClass().getName()+ " For Test " + result.getName() + " With status " + getResultStatusName(result.getStatus()) + " for the " + result.getMethod().getParameterInvocationCount() + " time(s).");
			logInfo("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retrycount + 1) + " time(s).");
			logInfo("Retrying -- " + result.getThrowable());

			retrycount++;
			return true;
		}
		return false;
	}

	private String getResultStatusName(int status) {
		String resultName=null;
		if (status==1)
			resultName="SUCCESS";
		if (status==2)
			resultName="FAILURE";
		if (status==3)
			resultName="SKIP";
		return resultName;
	}
	
	public void log(String data){
		logInfo(data);
		Reporter.log(data);
		TestBase base = new TestBase();
		base.log(data);
	}
}