package com.project.pTracker.Utils;

import java.util.HashMap;

public class TestDataPool {
	public  static HashMap<String,String> rowData = new HashMap<String,String>();

	public TestDataPool(){
		try {
				rowData = ExcelUtils.getTestDataXls(Constants.datapoolPath, "Automation", 1, 2)	;
			} catch (Exception e) {
 				e.printStackTrace();
			}
}
	
	public TestDataPool(int tcNum){
		try {
			rowData = ExcelUtils.getTestDataXls(Constants.datapoolPath, Constants.sheetName, 0, tcNum)	;
		} catch (Exception e) {
				e.printStackTrace();
		}
}
	
	public TestDataPool(String tcName){
 		try {
			rowData = ExcelUtils.getTestDataXlsx(Constants.datapoolPath, "Automation", 0, tcName)	;
		} catch (Exception e) {
				e.printStackTrace();
		}
}

}
