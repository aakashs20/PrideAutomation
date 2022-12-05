package com.project.testcases.IAutomateDemo;

import org.testng.annotations.Test;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Retry;
import com.project.pTracker.Utils.RetryAnalyzer;
import com.project.testbase.TestBase;
import static org.testng.Assert.fail;
		public class retryOnlyFailedTests {
			private int retryTimes = 0;
		    @Test
		    public void testStep_1() {
		        System.out.println("Test 1 starting.");
		        System.out.println("Test 1 passed.");
		    }

		    @Test
		    public void testStep_2() {
		        System.out.println("Test 2 starting.");
		        System.out.println("Test 2 passed.");
		    }

		    @Test//(retryAnalyzer = RetryAnalyzer.class)
		    public void testStep_3() {
		        System.out.println("Test 3 starting.");
		        System.out.println("Test 3 failed.");
		        Assert.assertEquals(true, false);
		        //Assert.fail("Test 3 failed.");
		    }
	}
