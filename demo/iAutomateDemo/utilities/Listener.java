package com.project.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;

import com.project.testbase.TestBase;
import com.project.thread.constants.TC;
import com.relevantcodes.extentreports.LogStatus;

public class Listener extends TestBase implements ITestListener, IAnnotationTransformer{



	private String env = "";

	int totalTestPassed = 0, totalTestFailed = 0, totalTestSkipped = 0, totalTestCount;
	List<String> failedtest = new ArrayList<String>();
	List<String> skippedTest = new ArrayList<String>();
	List<String> passedTest = new ArrayList<String>();
	public void onTestStart(ITestResult result) {
		methodName =result.getMethod().getMethodName();
		//test = extent.startTest(methodName);
		log("*****************Execution Started for '"+ result.getMethod().getDescription() +"' ***********************");


	}

	public void onTestSuccess(ITestResult result) {
		if(result.isSuccess()){
			Reporter.log(result.getMethod().getMethodName() + " - Test cases is Passed");
			try{
				TC.get().test.log(LogStatus.PASS, result.getMethod().getMethodName() + " - Test cases is Passed");
				log4j("onTestSuccess").info(result.getMethod().getMethodName() + " passed");

				if(passedTestCaseIds == null)
					passedTestCaseIds.add(result.getMethod().getMethodName());
				else if(passedTestCaseIds.contains(result.getMethod().getMethodName())) {
					logInfo("The testcase id " + result.getMethod().getMethodName() + " is already added to the list");
				}
				else 
					passedTestCaseIds.add(result.getMethod().getMethodName());
				
			}catch(Exception e){

			}
		}
	}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess()){
			Reporter.log(result.getMethod().getMethodName() + "Test cases is Failed");
			try{
				TC.get().test.log(LogStatus.FAIL, "Test step failed due to" + result.getThrowable());
				log4j("onTestFailure").info(result.getMethod().getMethodName() + " failed");
				TestBase.takeScreenShot();				

				//				//fetching projectid from Xml parameter and jira execution update
				//				if (prop.getProperty("enableJira").equalsIgnoreCase("true"))
				//				{
				//					if(result.getTestContext().getCurrentXmlTest().getParameter("ProjectId") != null)
				//					{
				//				String ProjectId = result.getTestContext().getCurrentXmlTest().getParameter("ProjectId");
				//				TC.get().issueKey = result.getMethod().findMethodParameters(result.getMethod().getXmlTest()).get("Jira Issue Key").toString();
				//				System.out.println(TC.get().issueKey);
				//				
				//				}
				//			}
			}catch(Exception e){

			}
		}		
	}


	public void onTestSkipped(ITestResult result) {

		//		Reporter.log(result.getMethod().getMethodName() + "Test cases skipped by Listeners due to :- " + result.getThrowable());
		Reporter.log(result.getMethod().getMethodName() + "Test cases is Skipped");
		if(skip==null){
			try {

				TC.get().test.log(LogStatus.SKIP, "Test step skipped due to" + result.getThrowable());	
				//			TC.get().test.log(LogStatus.SKIP, "Test step skipped due to" + TCGFailureMsg);	
				log4j("onTestSkipped").info(result.getMethod().getMethodName() + " skipped");	
				TestBase.takeScreenShot();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//		try {
		//			
		//			
		////			//fetching projectid from Xml parameter and jira execution update
		////			if (prop.getProperty("enableJira").equalsIgnoreCase("true"))
		////			{
		////				if(result.getTestContext().getCurrentXmlTest().getParameter("ProjectId") != null)
		////				{
		////			String ProjectId = result.getTestContext().getCurrentXmlTest().getParameter("ProjectId");
		////			TC.get().issueKey = result.getMethod().findMethodParameters(result.getMethod().getXmlTest()).get("Jira Issue Key").toString();
		////			
		////			}
		////			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		try{


			//			int is_testnotEmpty = context.getAllTestMethods().length;
			//			if(is_testnotEmpty>0)
			//			{
			//				FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE);
			//				prop.load(fis);
			//				
			//			}
			totalTestPassed = 0;
			totalTestFailed = 0;
			totalTestSkipped = 0;
			totalTestCount = 0;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void generateReportperTest(ITestContext context) {

		String testName = context.getCurrentXmlTest().getName();

		StringBuffer stringBuffer = new StringBuffer();

		try {

			// Create custom report title
			String customReportTitle = this.getCustomReportTitle("Test execution report");

			// Create test summary data.
			String customTestSummary = this.getTestSummary(testName);

			// Create test methods summary data.
			String customTestMethodSummary = this.getTestMethodSummary(context, testName);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@SuppressWarnings("finally")
	private String getTestSummary(String testName) {
		StringBuffer retBuf = new StringBuffer();

		try {
			TC.get().totalTestCount = TC.get().totalTestPassed + TC.get().totalTestSkipped + TC.get().totalTestFailed;

			/* Test name. */
			retBuf.append("*Test name:* " + testName);
			retBuf.append("\n\n");

			/* Total method count. */
			retBuf.append("Total tests:   *" + TC.get().totalTestCount + "*");
			retBuf.append("\n");

			/* Passed method count. */
			retBuf.append("Passed tests: *" + TC.get().totalTestPassed + "*");
			retBuf.append("\n");

			/* Skipped method count. */
			retBuf.append("Skipped tests: *" + TC.get().totalTestSkipped + "*");
			retBuf.append("\n");

			/* Failed method count. */
			retBuf.append("Failed tests: *" + TC.get().totalTestFailed + "*");
			retBuf.append("\n\n");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	@SuppressWarnings({ "unused", "finally" })
	private String getTestMethodSummary(ITestContext context, String testName) {

		IResultMap failedtestvalue = context.getFailedTests();
		IResultMap passedtestvalue = context.getPassedTests();
		IResultMap skippedtestvalue = context.getSkippedTests();		

		try {

			String failedTestMethodInfo = this.getTestReport(testName, failedtestvalue, false, false);

			String skippedTestMethodInfo = this.getTestReport(testName, skippedtestvalue, false, true);

			String passedTestMethodInfo = this.getTestReport(testName, passedtestvalue, true, false);


		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			return null;
		}
	}

	@SuppressWarnings("unused")
	private String getTestReport(String testName, IResultMap skippedtestvalue, boolean passedReault,
			boolean skippedResult) {
		StringBuffer retStrBuf = new StringBuffer();

		String resultTitle = testName;

		String testExecutionResult = ":heavy_check_mark:";

		if (skippedResult) {
			resultTitle += " - Skipped ";
			testExecutionResult = ":no_entry_sign:";
		} else {
			if (!passedReault) {
				resultTitle += " - Failed ";
				testExecutionResult = ":x:";
			} else {
				resultTitle += " - Passed ";
				testExecutionResult = ":white_check_mark:";
			}
		}

		retStrBuf.append("*Result title:* " + resultTitle + "\n");

		Set<ITestResult> testResultSet = skippedtestvalue.getAllResults();

		for (ITestResult testResult : testResultSet) {
			String testClassName = "";
			String testMethodName = "";
			String startDateStr = "";
			String executeTimeStr = "";
			String paramStr = "";
			String reporterMessage = "";
			String exceptionMessage = "";

			// Get testClassName
			testClassName = testResult.getTestClass().getName();
			String[] array = testClassName.split("\\.");
			if (array.length > 0) {
				testClassName = array[array.length - 1];
			}

			// Get testMethodName
			testMethodName = testResult.getMethod().getMethodName();	

			// Get Execute time.
			long deltaMillis = testResult.getEndMillis() - testResult.getStartMillis();
			executeTimeStr = this.convertDeltaTimeToString(deltaMillis);		

			// Get exception message.
			Throwable exception = testResult.getThrowable();
			if (exception != null) {

				exceptionMessage = exception.getMessage();
			}

			retStrBuf.append(testExecutionResult);

			/* Add test class name. */
			retStrBuf.append("  `Class name: ");
			retStrBuf.append(testClassName);
			retStrBuf.append("`");
			retStrBuf.append("\n");

			/* Add test method name. */
			retStrBuf.append("         `Test name: ");
			retStrBuf.append(testMethodName);
			retStrBuf.append("`");
			retStrBuf.append("    `in ");
			retStrBuf.append(executeTimeStr);
			retStrBuf.append(" sec");
			retStrBuf.append("`");
			retStrBuf.append("\n");

			/* Add exception message. */
			if (!exceptionMessage.equalsIgnoreCase("")) {
				retStrBuf.append("         `Exception message: `\n");
				retStrBuf.append("```" + exceptionMessage + "```");
				retStrBuf.append("\n");
				exceptionMessage = "";
			}

			retStrBuf.append("\n");
		}
		return retStrBuf.toString();
	}

	/* Build custom report title. */

	private String convertDeltaTimeToString(long deltaMillis) {
		float sec;

		sec = deltaMillis / 1000.0f;
		String timeInSeconds = Float.toString(sec);
		System.out.format("%.3f", sec);
		return timeInSeconds;
	}

	/* Build custom report title. */
	private String getCustomReportTitle(String title) {
		StringBuffer retBuf = new StringBuffer();
		retBuf.append("`env: " + env + "`");
		retBuf.append(" " + title + " " + this.getDateInStringFormat(new Date()));
		return retBuf.toString();
	}

	/* Build test suite summary data. */
	@SuppressWarnings({ "unused", "finally" })
	private String getTestSuiteSummary(List<ISuite> suites) {
		StringBuffer retBuf = new StringBuffer();

		try {
			int totalTestCount = 0;
			int totalTestPassed = 0;
			int totalTestFailed = 0;
			int totalTestSkipped = 0;

			for (ISuite tempSuite : suites) {
				retBuf.append("*Suit name:* " + tempSuite.getName() + "\n");

				Map<String, ISuiteResult> testResults = tempSuite.getResults();

				for (ISuiteResult result : testResults.values()) {

					ITestContext testObj = result.getTestContext();

					totalTestPassed = testObj.getPassedTests().getAllMethods().size();
					totalTestSkipped = testObj.getSkippedTests().getAllMethods().size();
					totalTestFailed = testObj.getFailedTests().getAllMethods().size();

					totalTestCount = totalTestPassed + totalTestSkipped + totalTestFailed;

					/* Test name. */
					retBuf.append("*Test name:* " + testObj.getName());
					retBuf.append("\n\n");

					/* Total method count. */
					retBuf.append("Total tests:   *" + totalTestCount + "*");
					retBuf.append("\n");

					/* Passed method count. */
					retBuf.append("Passed tests: *" + totalTestPassed + "*");
					retBuf.append("\n");

					/* Skipped method count. */
					retBuf.append("Skipped tests: *" + totalTestSkipped + "*");
					retBuf.append("\n");

					/* Failed method count. */
					retBuf.append("Failed tests: *" + totalTestFailed + "*");
					retBuf.append("\n\n");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	/* Get date string format value. */
	private String getDateInStringFormat(Date date) {
		StringBuffer retBuf = new StringBuffer();
		if (date == null) {
			date = new Date();
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		retBuf.append(df.format(date));
		return retBuf.toString();
	}

	/* Get test method summary info. */
	@SuppressWarnings({ "unused", "finally" })
	private String getTestMehodSummary(List<ISuite> suites) {
		//        StringBuffer retBuf = new StringBuffer();

		try {
			for (ISuite tempSuite : suites) {

				Map<String, ISuiteResult> testResults = tempSuite.getResults();

				for (ISuiteResult result : testResults.values()) {

					ITestContext testObj = result.getTestContext();

					String testName = testObj.getName();

					/* Get failed test method related data. */
					IResultMap testFailedResult = testObj.getFailedTests();
					String failedTestMethodInfo = this.getTestMethodReport(testName, testFailedResult, false, false);                  

					/* Get skipped test method related data. */
					IResultMap testSkippedResult = testObj.getSkippedTests();
					String skippedTestMethodInfo = this.getTestMethodReport(testName, testSkippedResult, false, true);

					/* Get passed test method related data. */
					IResultMap testPassedResult = testObj.getPassedTests();
					String passedTestMethodInfo = this.getTestMethodReport(testName, testPassedResult, true, false);

				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			return null;
		}
	}

	/* Get failed, passed or skipped test methods report. */
	@SuppressWarnings("unused")
	private String getTestMethodReport(String testName, IResultMap testResultMap, boolean passedReault, boolean skippedResult) {
		StringBuffer retStrBuf = new StringBuffer();

		String resultTitle = testName;

		String testExecutionResult = ":heavy_check_mark:";

		if (skippedResult) {
			resultTitle += " - Skipped ";
			testExecutionResult = ":no_entry_sign:";
		} else {
			if (!passedReault) {
				resultTitle += " - Failed ";
				testExecutionResult = ":x:";
			} else {
				resultTitle += " - Passed ";
				testExecutionResult = ":heavy_check_mark:";
			}
		}

		retStrBuf.append("*Result title:* " + resultTitle + "\n");

		Set<ITestResult> testResultSet = testResultMap.getAllResults();

		for (ITestResult testResult : testResultSet) {
			String testClassName = "";
			String testMethodName = "";
			String startDateStr = "";
			String executeTimeStr = "";
			String paramStr = "";
			String reporterMessage = "";
			String exceptionMessage = "";

			//Get testClassName
			testClassName = testResult.getTestClass().getName();
			String[] array = testClassName.split("\\.");
			if (array.length > 0) {
				testClassName = array[array.length - 1];
			}

			//Get testMethodName
			testMethodName = testResult.getMethod().getMethodName();

			//Get exception message.
			Throwable exception = testResult.getThrowable();
			if (exception != null) {

				exceptionMessage = exception.getMessage();
			}

			retStrBuf.append(testExecutionResult);

			/* Add test class name. */
			retStrBuf.append("  `Class name: ");
			retStrBuf.append(testClassName);
			retStrBuf.append("`");
			retStrBuf.append("\n");


			/* Add test method name. */
			retStrBuf.append("         `Test name: ");
			retStrBuf.append(testMethodName);
			retStrBuf.append("`");
			retStrBuf.append("\n");

			/* Add exception message. */
			if (!exceptionMessage.equalsIgnoreCase("")) {
				retStrBuf.append("         `Exception message: `\n");
				retStrBuf.append("```" + exceptionMessage + "```");
				retStrBuf.append("\n");
				exceptionMessage = "";
			}

			retStrBuf.append("\n");
		}
		return retStrBuf.toString();
	}

	//*********__///Retry Listener \\\_************
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub

		/*IRetryAnalyzer rety = annotation.getRetryAnalyzer();

   		if(rety==null){
   			annotation.setRetryAnalyzer(Retry.class);
   		}*/
	}
}
