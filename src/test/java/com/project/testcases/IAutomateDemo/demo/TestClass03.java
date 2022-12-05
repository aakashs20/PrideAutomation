package com.project.testcases.IAutomateDemo.demo;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class TestClass03 extends newTestBase {
    @Test
    public void freeMethod(Method method) {
        recordThread(method);
    }

    @Test(dependsOnMethods="freeMethod")
    public void dependentMethod(Method method) {
        recordThread(method);
    }
}
