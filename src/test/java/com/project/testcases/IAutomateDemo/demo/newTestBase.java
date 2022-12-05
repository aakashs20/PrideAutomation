package com.project.testcases.IAutomateDemo.demo;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.AfterClass;
import com.project.testbase.TestBase;

public class newTestBase extends TestBase {
    Set<Thread> threads = new HashSet<>();

    @AfterClass(alwaysRun=true)
    public void afterClass() {
        recordThread(getClass().getSimpleName() + ".afterClass()");
        System.out.println(getClass().getSimpleName() + " ran on threads: " + threads);
    }

    protected void recordThread(Method method) {
        recordThread(method.getDeclaringClass().getSimpleName() + '.' + method.getName() + "()");
    }

    private void recordThread(String name) {
        System.out.println(name + " running on thread " + Thread.currentThread());
        threads.add(Thread.currentThread());
    }
}