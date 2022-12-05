package com.project.pTracker.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class RetryListener implements IAnnotationTransformer {

	//RetryListener Class that inturn calls the Retry class for managing the iterations
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		//Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
		//IRetryAnalyzer retry1 = testannotation.getRetryAnalyzer();
		
        Class retry = annotation.getRetryAnalyzerClass();
        if (retry != Retry.class) {
            annotation.setRetryAnalyzer(Retry.class);
        }


		if (retry == null) {
			annotation.setRetryAnalyzer(Retry.class);
		}

	}

}
