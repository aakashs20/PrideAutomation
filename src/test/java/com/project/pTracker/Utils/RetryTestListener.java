package com.project.pTracker.Utils;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class RetryTestListener implements ITestListener  {
	 private static int MAX_RETRY_COUNT = 2;

	    AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

	    public boolean isRetryAvailable() {
	        return (count.intValue() > 0);
	    }

	    public boolean retry(ITestResult result) {
	        boolean retry = false;
	        if (isRetryAvailable()) {
	            System.out.println("Going to retry test case: " + result.getMethod() + ", " + (MAX_RETRY_COUNT - count.intValue() + 1) + " out of " + MAX_RETRY_COUNT);
	            retry = true;
	            count.decrementAndGet();
	        }
	        return retry;
	    }
	}