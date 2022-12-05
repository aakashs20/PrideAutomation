package com.project.pTracker.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.testng.ISuiteResult;
import org.testng.ISuite;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.testng.IResultMap;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import org.testng.ITestContext;
import com.relevantcodes.extentreports.LogStatus;

import groovy.util.logging.Log4j;

import com.project.thread.constants.TC;
import org.testng.Reporter;
import org.testng.ITestResult;
import java.util.ArrayList;
import java.util.List;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import com.project.testbase.TestBase;

public class Listener extends TestBase implements ITestListener, IAnnotationTransformer
{
    private String env;
    int totalTestPassed;
    int totalTestFailed;
    int totalTestSkipped;
    int totalTestCount;
    List<String> failedtest;
    List<String> skippedTest;
    List<String> passedTest;
    
    public Listener() {
        this.env = "";
        this.totalTestPassed = 0;
        this.totalTestFailed = 0;
        this.totalTestSkipped = 0;
        this.failedtest = new ArrayList<String>();
        this.skippedTest = new ArrayList<String>();
        this.passedTest = new ArrayList<String>();
    }
    
    public void onTestStart(final ITestResult result) {
        Listener.methodName = result.getMethod().getMethodName();
        this.log("*****************Execution Started for" + Listener.methodName + "***********************");
    }
    
    public void onTestSuccess(final ITestResult result) {
        if (result.isSuccess()) {
            Reporter.log(String.valueOf(result.getMethod().getMethodName()) + "Test cases is Passed");
            try {
                TC.get().test.log(LogStatus.PASS, String.valueOf(result.getMethod().getMethodName()) + "Test Case is Passed");
                log4j("onTestSuccess").info((Object)(String.valueOf(result.getMethod().getMethodName()) + " Passed"));
            }
            catch (Exception ex) {}
        }
    }
    
    public void onTestFailure(final ITestResult result) {
        if (!result.isSuccess()) {
            Reporter.log(String.valueOf(result.getMethod().getMethodName()) + "Test cases is Failed");
            try {
                TC.get().test.log(LogStatus.FAIL, "Test step failed due to" + result.getThrowable());
                log4j("onTestFailure").info((Object)(String.valueOf(result.getMethod().getMethodName()) + " failed"));
                TestBase.takeScreenShot();
            }
            catch (Exception ex) {}
        }
    }
    
    public void onTestSkipped(final ITestResult result) {
        Reporter.log(String.valueOf(result.getMethod().getMethodName()) + "Test cases skipped by Listeners due to :- " + result.getThrowable());
        if (Listener.skip == null) {
            try {
                TC.get().test.log(LogStatus.WARNING, "Test cases skipped by Listeners due to :- " + result.getThrowable());
            }
            catch (Exception e) {
            	System.out.println(TC.get().test+ "---" + e.getMessage());
            	//e.printStackTrace();

            }
        }
    }
    
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }
    
    public void onStart(final ITestContext context) {
    }
    
    public void onFinish(final ITestContext context) {
        try {
            final int is_testnotEmpty = context.getAllTestMethods().length;
            if (is_testnotEmpty > 0) {
                final FileInputStream fis = new FileInputStream(String.valueOf(System.getProperty("user.dir")) + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties");
                Listener.prop.load(fis);
            }
            this.totalTestPassed = 0;
            this.totalTestFailed = 0;
            this.totalTestSkipped = 0;
            this.totalTestCount = 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generateReportperTest(final ITestContext context) {
        final String testName = context.getCurrentXmlTest().getName();
        final StringBuffer stringBuffer = new StringBuffer();
        try {
            final String customReportTitle = this.getCustomReportTitle("Test execution report");
            final String customTestSummary = this.getTestSummary(testName);
            this.getTestMethodSummary(context, testName);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String getTestSummary(final String testName) {
        final StringBuffer retBuf = new StringBuffer();
        try {
            TC.get().totalTestCount = TC.get().totalTestPassed + TC.get().totalTestSkipped + TC.get().totalTestFailed;
            retBuf.append("*Test name:* " + testName);
            retBuf.append("\n\n");
            retBuf.append("Total tests:   *" + TC.get().totalTestCount + "*");
            retBuf.append("\n");
            retBuf.append("Passed tests: *" + TC.get().totalTestPassed + "*");
            retBuf.append("\n");
            retBuf.append("Skipped tests: *" + TC.get().totalTestSkipped + "*");
            retBuf.append("\n");
            retBuf.append("Failed tests: *" + TC.get().totalTestFailed + "*");
            retBuf.append("\n\n");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return retBuf.toString();
    }
    
    private String getTestMethodSummary(final ITestContext context, final String testName) {
        final IResultMap failedtestvalue = context.getFailedTests();
        final IResultMap passedtestvalue = context.getPassedTests();
        final IResultMap skippedtestvalue = context.getSkippedTests();
        try {
            final String failedTestMethodInfo = this.getTestReport(testName, failedtestvalue, false, false);
            final String skippedTestMethodInfo = this.getTestReport(testName, skippedtestvalue, false, true);
            this.getTestReport(testName, passedtestvalue, true, false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private String getTestReport(final String testName, final IResultMap skippedtestvalue, final boolean passedReault, final boolean skippedResult) {
        final StringBuffer retStrBuf = new StringBuffer();
        String resultTitle = testName;
        String testExecutionResult = ":heavy_check_mark:";
        if (skippedResult) {
            resultTitle = String.valueOf(resultTitle) + " - Skipped ";
            testExecutionResult = ":no_entry_sign:";
        }
        else if (!passedReault) {
            resultTitle = String.valueOf(resultTitle) + " - Failed ";
            testExecutionResult = ":x:";
        }
        else {
            resultTitle = String.valueOf(resultTitle) + " - Passed ";
            testExecutionResult = ":white_check_mark:";
        }
        retStrBuf.append("*Result title:* " + resultTitle + "\n");
        final Set<ITestResult> testResultSet = (Set<ITestResult>)skippedtestvalue.getAllResults();
        for (final ITestResult testResult : testResultSet) {
            String testClassName = "";
            String testMethodName = "";
            final String startDateStr = "";
            String executeTimeStr = "";
            final String paramStr = "";
            final String reporterMessage = "";
            String exceptionMessage = "";
            testClassName = testResult.getTestClass().getName();
            final String[] array = testClassName.split("\\.");
            if (array.length > 0) {
                testClassName = array[array.length - 1];
            }
            testMethodName = testResult.getMethod().getMethodName();
            final long deltaMillis = testResult.getEndMillis() - testResult.getStartMillis();
            executeTimeStr = this.convertDeltaTimeToString(deltaMillis);
            final Throwable exception = testResult.getThrowable();
            if (exception != null) {
                exceptionMessage = exception.getMessage();
            }
            retStrBuf.append(testExecutionResult);
            retStrBuf.append("  `Class name: ");
            retStrBuf.append(testClassName);
            retStrBuf.append("`");
            retStrBuf.append("\n");
            retStrBuf.append("         `Test name: ");
            retStrBuf.append(testMethodName);
            retStrBuf.append("`");
            retStrBuf.append("    `in ");
            retStrBuf.append(executeTimeStr);
            retStrBuf.append(" sec");
            retStrBuf.append("`");
            retStrBuf.append("\n");
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
    
    private String convertDeltaTimeToString(final long deltaMillis) {
        final float sec = deltaMillis / 1000.0f;
        final String timeInSeconds = Float.toString(sec);
        System.out.format("%.3f", sec);
        return timeInSeconds;
    }
    
    private String getCustomReportTitle(final String title) {
        final StringBuffer retBuf = new StringBuffer();
        retBuf.append("`env: " + this.env + "`");
        retBuf.append(" " + title + " " + this.getDateInStringFormat(new Date()));
        return retBuf.toString();
    }
    
    private String getTestSuiteSummary(final List<ISuite> suites) {
        final StringBuffer retBuf = new StringBuffer();
        try {
            int totalTestCount = 0;
            int totalTestPassed = 0;
            int totalTestFailed = 0;
            int totalTestSkipped = 0;
            for (final ISuite tempSuite : suites) {
                retBuf.append("*Suit name:* " + tempSuite.getName() + "\n");
                final Map<String, ISuiteResult> testResults = (Map<String, ISuiteResult>)tempSuite.getResults();
                for (final ISuiteResult result : testResults.values()) {
                    final ITestContext testObj = result.getTestContext();
                    totalTestPassed = testObj.getPassedTests().getAllMethods().size();
                    totalTestSkipped = testObj.getSkippedTests().getAllMethods().size();
                    totalTestFailed = testObj.getFailedTests().getAllMethods().size();
                    totalTestCount = totalTestPassed + totalTestSkipped + totalTestFailed;
                    retBuf.append("*Test name:* " + testObj.getName());
                    retBuf.append("\n\n");
                    retBuf.append("Total tests:   *" + totalTestCount + "*");
                    retBuf.append("\n");
                    retBuf.append("Passed tests: *" + totalTestPassed + "*");
                    retBuf.append("\n");
                    retBuf.append("Skipped tests: *" + totalTestSkipped + "*");
                    retBuf.append("\n");
                    retBuf.append("Failed tests: *" + totalTestFailed + "*");
                    retBuf.append("\n\n");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return retBuf.toString();
    }
    
    private String getDateInStringFormat(Date date) {
        final StringBuffer retBuf = new StringBuffer();
        if (date == null) {
            date = new Date();
        }
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        retBuf.append(df.format(date));
        return retBuf.toString();
    }
    
    private String getTestMehodSummary(final List<ISuite> suites) {
        try {
            for (final ISuite tempSuite : suites) {
                final Map<String, ISuiteResult> testResults = (Map<String, ISuiteResult>)tempSuite.getResults();
                for (final ISuiteResult result : testResults.values()) {
                    final ITestContext testObj = result.getTestContext();
                    final String testName = testObj.getName();
                    final IResultMap testFailedResult = testObj.getFailedTests();
                    final String failedTestMethodInfo = this.getTestMethodReport(testName, testFailedResult, false, false);
                    final IResultMap testSkippedResult = testObj.getSkippedTests();
                    final String skippedTestMethodInfo = this.getTestMethodReport(testName, testSkippedResult, false, true);
                    final IResultMap testPassedResult = testObj.getPassedTests();
                    this.getTestMethodReport(testName, testPassedResult, true, false);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private String getTestMethodReport(final String testName, final IResultMap testResultMap, final boolean passedReault, final boolean skippedResult) {
        final StringBuffer retStrBuf = new StringBuffer();
        String resultTitle = testName;
        String testExecutionResult = ":heavy_check_mark:";
        if (skippedResult) {
            resultTitle = String.valueOf(resultTitle) + " - Skipped ";
            testExecutionResult = ":no_entry_sign:";
        }
        else if (!passedReault) {
            resultTitle = String.valueOf(resultTitle) + " - Failed ";
            testExecutionResult = ":x:";
        }
        else {
            resultTitle = String.valueOf(resultTitle) + " - Passed ";
            testExecutionResult = ":heavy_check_mark:";
        }
        retStrBuf.append("*Result title:* " + resultTitle + "\n");
        final Set<ITestResult> testResultSet = (Set<ITestResult>)testResultMap.getAllResults();
        for (final ITestResult testResult : testResultSet) {
            String testClassName = "";
            String testMethodName = "";
            final String startDateStr = "";
            final String executeTimeStr = "";
            final String paramStr = "";
            final String reporterMessage = "";
            String exceptionMessage = "";
            testClassName = testResult.getTestClass().getName();
            final String[] array = testClassName.split("\\.");
            if (array.length > 0) {
                testClassName = array[array.length - 1];
            }
            testMethodName = testResult.getMethod().getMethodName();
            final Throwable exception = testResult.getThrowable();
            if (exception != null) {
                exceptionMessage = exception.getMessage();
            }
            retStrBuf.append(testExecutionResult);
            retStrBuf.append("  `Class name: ");
            retStrBuf.append(testClassName);
            retStrBuf.append("`");
            retStrBuf.append("\n");
            retStrBuf.append("         `Test name: ");
            retStrBuf.append(testMethodName);
            retStrBuf.append("`");
            retStrBuf.append("\n");
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
}
