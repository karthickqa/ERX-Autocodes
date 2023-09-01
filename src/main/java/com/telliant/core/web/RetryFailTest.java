package com.telliant.core.web;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailTest implements IRetryAnalyzer {

    private int retryCount=2;
    private static final int maxCount=1;

    public boolean retry1(ITestResult result) {
        /*if(!result.isSuccess()){
            if(retryCount<=maxCount){
                retryCount++;
                //Test Mark as failed
                result.setStatus(ITestResult.FAILURE);
                //Tells TestNG to rerun the test
                return true;
            }else{
                //If max count reached, test marked as failed
                result.setStatus(ITestResult.FAILURE);
            }
        }else{
            //If pass, TestNG mark the test as passed
            result.setStatus(ITestResult.SUCCESS);
        }*/
        if(retryCount<= maxCount){
            retryCount++;
            return true;
        }
        return false;
    }

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		return false;
	}
}
 