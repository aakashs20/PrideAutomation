package com.project.utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.testbase.TestBase;

public class UpdateExecutionExcelFile extends TestBase {

	public XSSFSheet sheet = null;
	public  XSSFWorkbook ExcelWBook = null;
	public XSSFSheet ExcelWSheet = null;
	public static DataFormatter formatter = new DataFormatter();
	public ExcelReader excelReader = new ExcelReader();


	/**
	 * This method is used to Update the execution excel suite file such that it marks the 
	 * Passed TCs as 'N', that is, they don't need to be used for rerun
	 * @author hingorani_a
	 * @param suitename The name of the excel suite that needs to updated for Passed TCs
	 * @param listOfPassedIds The list of test-case Ids that passed 
	 * @return boolean This returns true if excel is updated successfully
	 */
	@SuppressWarnings("resource")
	public boolean updateExcelFileDynamically(String suitename, List<String> listOfPassedIds) throws IOException {
		InputStream fis;
		FileOutputStream fileOut;
		String filePath = null;

		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		int count = 0;

		// Retrieve values from Excel
		String workStream = null;
		int lastRowCount = 0;

		try {

			if(suitename.equals("")) {
					logError("Suite name is empty hence cannot update any excel");
					return false;
			}
			else {
				filePath = System.getProperty("user.dir") + "\\test-data-files\\"+suitename+".xlsx";
				fis = new FileInputStream(filePath);
				wb = new XSSFWorkbook(fis);  
				sheet = wb.getSheet("Config");

				// Read Config parameters
				row  = sheet.getRow(1);
				workStream = row.getCell(0).getStringCellValue();

				// 
				sheet = wb.getSheet(workStream);
				lastRowCount = excelReader.getLastRowCount(sheet, row);

				for(String t_id : listOfPassedIds) {
					for(int i = 0; i < lastRowCount; i++) {
						row = sheet.getRow(i);
						if(row.getCell(1).getStringCellValue().contains(t_id)) {
							row.getCell(3).setCellValue("N");
							count++;
						}
					}
				}

				logInfo("Marked " + count + " TCs as 'N'");
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Failed to Update Excel due to Exception : " + e.getMessage());
			return false;
		}
		finally {
			fileOut = new FileOutputStream(filePath);
			wb.write(fileOut); 
			fileOut.close(); 
		}
	}				

}





