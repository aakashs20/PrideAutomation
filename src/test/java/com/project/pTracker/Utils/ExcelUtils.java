package com.project.pTracker.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.project.utilities.ExcelReader;

public class ExcelUtils extends ExcelReader{
	
	public String path = null;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	


	public static HashMap<String,String> getTestDataXls(String filePath, String sheetName, int headerRowNum, int tcRowNum) throws Exception
	{
	
 		HashMap<String,String> rowData = new HashMap<>();
		
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = null;
		HSSFRow  headerRow,tcRow;
		
		FileInputStream file = null;
 		try {
			file = new FileInputStream(new File(filePath));//D:\Selenium_Logs\TestData\Datapool.xls 
			workbook = new HSSFWorkbook(file);
	 	
		} catch (Exception e) {
 			e.printStackTrace();
		} 
 		
		sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(headerRowNum);
		tcRow = sheet.getRow(tcRowNum);
		
		Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = headerRow.cellIterator();
		
		int i = 0;
		while(cellIterator.hasNext()){
			org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
			rowData.put(cellValueStr(workbook,headerRow,i), cellValueStr(workbook,tcRow,i));
		i++;
		}
				
		workbook.close();
		file.close();
 		return rowData;
 	
	
}
	
	// return true if data is set successfully else false
		/**
		 * This method is used to Set data in the cell for mentioned row number and column name in the excel sheet.
		 * Change the color of the mentioned cell.
		 * @param sheetName - Sheet name
		 * @param colName -  Column name
		 * @param rowNum - Row number
		 * @param data - New Data for the cell
		 * @param color - Color of the cell
		 * @return True after successfully setting the data in the cell. False if fails to do so.
		 */
		public static boolean setCellData(String sheetName, String colName, int rowNum, String data, String color) {
			FileInputStream fis;
			FileOutputStream fileOut ;
			HSSFWorkbook workbook ;
		    HSSFSheet sheet;
		    HSSFRow row ;
		    HSSFCell cell ;
			try {
				fis = new FileInputStream(sheetName);
				workbook = new HSSFWorkbook(fis);

				if (rowNum <= 0)
					return false;

				int index = workbook.getSheetIndex("Automation");
				int colNum = -1;
				if (index == -1)
					return false;
				sheet = workbook.getSheetAt(index);
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();

				if (color.equalsIgnoreCase("GREEN")) {
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				} else if (color.equalsIgnoreCase("RED")) {
					style.setFillForegroundColor(IndexedColors.RED.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				} else {
					style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				}
				style.setFont(font);

				row = sheet.getRow(0);
				for (int i = 0; i < row.getLastCellNum(); i++) {
					if (row.getCell(i).getStringCellValue().trim().equals(colName))
						colNum = i;
				}
				if (colNum == -1)
					return false;

				sheet.autoSizeColumn(colNum);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					row = sheet.createRow(rowNum - 1);

				cell = row.getCell(colNum);
				if (cell == null)
					cell = row.createCell(colNum);

				cell.setCellValue(data);
				cell.setCellStyle(style);

				fileOut = new FileOutputStream(sheetName);
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}
			return true;
		}
		// return true if data is set successfully else false

		/**
		 * This method is used to Set data in the cell for mentioned row number and column number in the excel sheet.
		 * @param sheetName - Name of the sheet
		 * @param colNumber - Column Number
		 * @param rowNum - Row Number
		 * @param data - Data
		 * @return True after successfully setting the data in the cell. False if fails to do so.
		 */
		public boolean setCellDataInt(String filename, int colNum, int rowNum, String data) {
			HSSFSheet sheet = null;
			HSSFRow row = null;
	
			try {
				FileInputStream fis = new FileInputStream(new File(filename));
				//Access the workbook   

				HSSFWorkbook workbook = new HSSFWorkbook(fis);
			   //Access the worksheet, so that we can update / modify it. 
			   HSSFSheet worksheet = workbook.getSheetAt(0); 
			   // declare a Cell object
			   Cell cell = null; 
			   // Access the second cell in second row to update the value
			   cell = worksheet.getRow(rowNum).getCell(colNum); 

				if (rowNum <= 0)
					return false;

				int index = workbook.getSheetIndex("Automation");

				if (index == -1)
					return false;

				 sheet = workbook.getSheetAt(0);

				row = sheet.getRow(0);

				if (colNum == -1)
					return false;

				sheet.autoSizeColumn(colNum);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					row = sheet.createRow(rowNum - 1);

				cell = row.getCell(colNum);

				if (cell == null)
					cell = row.createCell(colNum);

				cell.setCellValue(data);

				fileOut = new FileOutputStream(path);

				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			return true;

		}
		// return true if data is set successfully else false

		// check if row is empty
		/**
		 * This method is used to check if row is empty or present in the excel file which is provided in the calling method.
		 * @param row - Row
		 * @return - True if row is empty.
		 */
		public boolean isRowEmpty(Row row) {
			if (row != null) {
				for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
					Cell cell = row.getCell(c);
					if (cell != null && cell.getCellTypeEnum() != CellType.BLANK) {
						return false;
					}
				}
			}
			return true;
		}	
		// return number of column in a sheet
		/**
		 * This method is used to get number of columns in a sheet.
		 * @param sheetName - Name of the Sheet
		 * @return The total number of column in the sheet.
		 */
		public int getColumnCount(String sheetName) {
			// check if sheet exist
			if (!isSheetExist(sheetName))
				return -1;

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);

			if (row == null)
				return -1;

			return row.getLastCellNum();

		}
		
		// find whether sheet exists
		/**
		 * This method is used to check whether mentioned sheet name exists in the excel sheet
		 * @param sheetName - Name of the sheet to be checked
		 * @return True is the sheet exists in the excel file, else false
		 */
		public boolean isSheetExist(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				index = workbook.getSheetIndex(sheetName.toUpperCase());
				if (index == -1)
					return false;
				else
					return true;
			} else
				return true;

		}
		
		/**
		 * This method is used to Update an Excel Sheet from an excel file with new data for a column.
		 * @param sheetname - Excel sheet name 
		 * @param columnName - Column Name
		 * @param OldValue - Value to be updated/replaced
		 * @param NewValue - New value
		 * @param ExcelPathAndName - Location and name of the excel file.
		 * @throws FilloException - This occurs when there is problem in query operation or accessing the file for read/write.
		 */
		@SuppressWarnings("unused")
		public static void UpdateExcel(String sheetname, String columnName, String OldValue, String NewValue,
				String ExcelPathAndName) throws FilloException {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(ExcelPathAndName);
			String strQuery = "update " + sheetname + " set " + columnName + "='" + NewValue + "' where " + columnName
					+ "='" + OldValue + "'";
			int result = connection.executeUpdate(strQuery);
			connection.close();

		}
		
		/**
		 * This method is used to update excel sheet from excel file using Apache POI
		 * Library.
		 * 
		 * @param row              - Row Number where the update is to be made
		 * @param cell             - Cell Number which is to be updated
		 * @param NewValue         - New Value
		 * @param ExcelPathAndName -Location and name of the excel file.
		 * @throws Exception   - This occurs when there is problem in query operation or
		 *                     accessing the file for read/write.
		 * @throws IOException - Throws when unable to perform read/write operation on
		 *                     file.
		 */
		public static void UpdateExcelUsingApache(int row, int cell, String NewValue, String ExcelPathAndName)
				throws Exception, IOException {
			File f = new File(ExcelPathAndName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Cell cell2Update = sheet.getRow(row).getCell(cell);
			cell2Update.setCellValue(NewValue);
			ios.close();

			FileOutputStream outputStream = new FileOutputStream(ExcelPathAndName);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
		

		// returns true if data is set successfully else false
		public static void updateCellData(String filename,int rowNum, int colNum, String text){
		       try {
		    	 //Read the spreadsheet that needs to be updated
		    	   FileInputStream fsIP= new FileInputStream(new File(filename));  
		    	   //Access the workbook                  
		    	   HSSFWorkbook wb = new HSSFWorkbook(fsIP);
		    	   //Access the worksheet, so that we can update / modify it. 
		    	   HSSFSheet worksheet = wb.getSheetAt(0); 
		    	   // declare a Cell object
		    	   Cell cell = null; 
		    	   // Access the second cell in second row to update the value
		    	   cell = worksheet.getRow(rowNum).getCell(colNum);   
		    	   // Get current cell value value and overwrite the value
		    	   cell.setCellValue(text);
		    	   //Close the InputStream  
		    	   fsIP.close(); 
		    	   //Open FileOutputStream to write updates
		    	   FileOutputStream output_file =new FileOutputStream(new File(filename));  
		    	    //write changes
		    	   wb.write(output_file);
		    	   //close the stream
		    	   output_file.close();

		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		
		// return the row count in sheet
		/**
		 * This method is used to get row count from an excel sheet.
		 * @param sheetName - Name of the Excel Sheet
		 * @return Total no. of rows in the excel sheet
		 */
		public int getRowCount(String sheetName) {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}

		}
		
		// return the data from cell
		/**
		 * This method is used to get data from a cell from an excel sheet of excel file using column no. and row no.
		 * @param sheetName - Name of Excel Sheet 
		 * @param colNum - Column Number
		 * @param rowNum - Row Number
		 * @return Value of that cell
		 */
		@SuppressWarnings("deprecation")
		public String getCellData(String sheetName, int colNum, int rowNum) {
			try {
				if (rowNum <= 0)
					return "";
				int index = workbook.getSheetIndex(sheetName);

				if (index == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					return "";
				cell = row.getCell(colNum);
				if (cell == null)
					return "";

				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					return cell.getStringCellValue();
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					if (DateUtil.isCellDateFormatted(cell)) {
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(DateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
					}
					return cellText;
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());

			} catch (Exception e) {

				e.printStackTrace();
				return "row" + rowNum + " or column " + colNum + "does not exist in xls";
			}
		}

		/**
		 * This method is used to get data from a cell from an excel sheet of excel file using column name and row no.
		 * @param sheetName - Name of Excel Sheet 
		 * @param colName - Name of Column
		 * @param rowNum - Row Number
		 * @return Value of that cell
		 */
		@SuppressWarnings("deprecation")
		public String getCellData(String sheetName, String colName, int rowNum) {

			try {

				if (rowNum <= 0)
					return "";

				int index = workbook.getSheetIndex(sheetName);
				int col_Num = -1;
				if (index == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(0);

				for (int i = 0; i < row.getLastCellNum(); i++) {

					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						col_Num = i;
				}
				if (col_Num == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum - 1);

				if (row == null)
					return "";
				cell = row.getCell(col_Num);

				if (cell == null)
					return "";

				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					return cell.getStringCellValue();
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					if (DateUtil.isCellInternalDateFormatted(cell)) {
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(DateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
					}
					return cellText;
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());

			} catch (Exception e) {
				e.printStackTrace();
				return "row" + rowNum + "or column" + colName + "does not exist in xls";
			}

		}
		
		/**
		 * This method is used to get cell row number based on cell value
		 * @param sheetName - Sheet name
		 * @param colName - Column name
		 * @param cellValue - Cell Value
		 * @return Cell row number
		 */
		public int getCellRowNum(String sheetName, String colName, String cellValue) {

			for (int i = 2; i <= getRowCount(sheetName); i++) {
				if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;
		}
		
		
		/*
		 * Excel Reader Code starts here
		 * 
		 */


/*	public static HashMap<String,String> getTestDataXlsx(String filePath, String sheetName, int headerRowNum, String tcName) throws Exception
	{
 	 	
		HashMap<String,String> rowData = new HashMap<>();
		
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow  headerRow,tcRow;
		
		FileInputStream file = null;
		
 		try {
			file = new FileInputStream(new File(filePath));//D:\Selenium_Logs\TestData\Datapool.xls 
		workbook = new XSSFWorkbook(file);
	 	
		} catch (Exception e) {
 			e.printStackTrace();
		} 
		
		
		sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(headerRowNum);
		tcRow = sheet.getRow(findRow(sheet,tcName));
		
		Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = headerRow.cellIterator();
		
		int i = 0;
		while(cellIterator.hasNext()){
			
			org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
			
			rowData.put(cellValueStrX(workbook,headerRow,i), cellValueStrX(workbook,tcRow,i));
			
		i++;
			
		}
				
		workbook.close();
		file.close();
		
		
		return rowData;
		
		
	
}*/
	
	public static String cellValueStr (HSSFWorkbook workbook, HSSFRow row, int cellNum){
		DataFormatter format = new DataFormatter();
		FormulaEvaluator eval = new HSSFFormulaEvaluator(workbook);
		
		HSSFCell cellValue = row.getCell(cellNum);
		
		eval.evaluate(cellValue);
		
		String cellValueStr = format.formatCellValue(cellValue,eval);
		
		
		return cellValueStr;
	 	
		
	}
	
	public static String cellValueStrX (XSSFWorkbook workbook, XSSFRow row, int cellNum){
		DataFormatter format = new DataFormatter();
		FormulaEvaluator eval = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
		
		XSSFCell cellValue = row.getCell(cellNum);
		
		eval.evaluate(cellValue);
		
		String cellValueStr = format.formatCellValue(cellValue,eval);
		
		
		return cellValueStr;
	 	
		
	}
	
	
/*	@SuppressWarnings("deprecation")
	public static int findRow(XSSFSheet sheet, String cellContent){
		for(org.apache.poi.ss.usermodel.Row row: sheet){
			for(org.apache.poi.ss.usermodel.Cell cell: row){
				if(cell.getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING){
					if(cell.getRichStringCellValue().getString().trim().equals(cellContent)){
						return row.getRowNum();

					}
				}
			}
 
			
		}
		return 0;
	}*/
	
}
