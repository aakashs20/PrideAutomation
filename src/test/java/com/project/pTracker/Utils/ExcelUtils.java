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
	private static HSSFWorkbook hssfWorkBook = null;
	private static HSSFSheet hssfSheet = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private static XSSFCell newCell = null;
	public int col_num;
	

	public static HashMap<String,String> getTestDataXls(String filePath, String sheetName, int headerRowNum, int tcRowNum) throws Exception
	{
 		HashMap<String,String> rowData = new HashMap<>();
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = null;
		HSSFRow  headerRow,tcRow;
		
		FileInputStream file = null;
 		try {
 			// it takes fully qualified filepath e.g. "D:\Selenium_Logs\TestData\Datapool.xls"
			file = new FileInputStream(new File(filePath));
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
				} else if (color.equalsIgnoreCase("YELLOW")) {
					style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				}
				else {
					style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
					style.setFillPattern(FillPatternType.SQUARES);
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
				//row = sheet.getRow(rowNum);
				if (row == null)
					//row = sheet.createRow(rowNum - 1);
					row = sheet.createRow(rowNum);

				cell = row.getCell(colNum);
				if (cell == null)
					cell = row.createCell(colNum);

				cell.setCellValue(data);
				cell.setCellStyle(style);

				fileOut = new FileOutputStream(sheetName);
				workbook.write(fileOut);
				workbook.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		// return true if data is set successfully else false

		public static boolean setCellData(String sheetName, String colName, int rowNum, String data, String color,String datapool) {
			FileInputStream fis;
			FileOutputStream fileOut ;
			HSSFWorkbook workbook ;
		    HSSFSheet sheet;
		    HSSFRow row ;
		    HSSFCell cell ;
			try {
				//System.out.println("----------------"+ sheetName);
				//System.out.println("----------------"+ datapool);
				fis = new FileInputStream(sheetName);
				workbook = new HSSFWorkbook(fis);

				if (rowNum <= 0)
					return false;
				int index = workbook.getSheetIndex(datapool);
				int colNum = -1;
				if (index == -1)
					return false;
				sheet = workbook.getSheetAt(index);
				
				
				if (rowNum <= 0)
					return false;

				
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();

				if (color.equalsIgnoreCase("GREEN")) {
					style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				} else if (color.equalsIgnoreCase("RED")) {
					style.setFillForegroundColor(IndexedColors.RED.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				} else if (color.equalsIgnoreCase("YELLOW")) {
					style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				}
				else {
					style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
					style.setFillPattern(FillPatternType.SQUARES);
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
				//row = sheet.getRow(rowNum);
				if (row == null)
					//row = sheet.createRow(rowNum - 1);
					row = sheet.createRow(rowNum);

				cell = row.getCell(colNum);
				if (cell == null)
					cell = row.createCell(colNum);

				cell.setCellValue(data);
				cell.setCellStyle(style);

				fileOut = new FileOutputStream(sheetName);
				workbook.write(fileOut);
				workbook.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
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
				workbook.close();
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
		
		
		/**
		 * This method is used to get cell row number based on cell value
		 * @param sheetName - Sheet name
		 * @param colName - Column name
		 * @param cellValue - Cell Value/Test Case name
		 * @return Cell row number
		 */
		public static int getRowCnt(HSSFWorkbook workbook, String sheetName) {
			HSSFSheet sheet;
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}

		}
		
		public static int getRowNum(String FilePath, String sheetName, String colName, String cellValue) throws Exception, IOException  {
			
	        HSSFCell     cell; //Excel cell
	        HSSFRow      row; 
	        
			File f = new File(FilePath);
			FileInputStream ios = new FileInputStream(f);
			HSSFWorkbook workbook = new HSSFWorkbook(ios);

			for (int i = 1; i <= getRowCnt(workbook,sheetName); i++) {
				if (getCellValue(workbook,sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;
		}
		
		public static String getRowValue(int row,int col) throws IOException {
		String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace + "\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		FileInputStream fileInputStream=new FileInputStream(datapoolPath);
		HSSFWorkbook workbook=new HSSFWorkbook(fileInputStream);
		HSSFSheet sheet;
		try {
			sheet = workbook.getSheetAt(0);//workbook.getSheet("Sheet1");
		    
		   String value=sheet.getRow(row).getCell(col-1).getStringCellValue();
		    return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
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
		    	   wb.close();
		    	   output_file.close();

		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		
		/**
		 * This method is used to get row count from an excel sheet.
		 * @param sheetName - Name of the Excel Sheet
		 * @return Total no. of rows in the excel sheet
		 */
		public int getRowCount(String sheetName,XSSFWorkbook workbook) {
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
		 * @param rowNum - Row Number
		 * @param colNum - Column Number
		 * @return Value of that cell
		 */
		@SuppressWarnings("deprecation")
		public String getCellData(String sheetName,int rowNum, int colNum) {
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

		@SuppressWarnings("deprecation")
		public static String getCellValue(HSSFWorkbook workbook,String sheetName, String colName, int rowNum) {
			HSSFRow row = null;
			HSSFCell cell = null;
			HSSFSheet sheet;
			
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
		

		
		/*
		 * Excel Reader Code starts here
		 * 
		 */


	public static HashMap<String,String> getTestDataXlsx(String filePath, String sheetName, int headerRowNum, String tcName) throws Exception
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
}
		
	
	public static HashMap<String, String> getTestDataXlsx(String filePath, String sheetName, int headerRowNum,
			int tcRowNum) throws Exception {
		HashMap<String, String> rowData = new HashMap<>();
		XSSFSheet sheet = null;
		XSSFWorkbook workbook = null;
		XSSFRow headerRow, tcRow;

		FileInputStream file = null;
		try {
			// it takes fully qualified filepath e.g.
			// "D:\Selenium_Logs\TestData\Datapool.xls"
			file = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(headerRowNum);
		tcRow = sheet.getRow(tcRowNum);

		Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = headerRow.cellIterator();

		int i = 0;
		while (cellIterator.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
			rowData.put(cellValueStrX(workbook, headerRow, i), cellValueStrX(workbook, tcRow, i));
			i++;
		}
		workbook.close();
		file.close();
		return rowData;
	}

    private static int findRow(XSSFSheet sheet, String cellContent){
        /*
         *  This is the method to find the row number
         */
        int rowNum = 0; 
        for(Row row : sheet) {
            for(Cell cell : row) {
                while(cell.getCellType() == Cell.CELL_TYPE_STRING){
                    if(cell.getRichStringCellValue().getString () == cellContent);{
                            rowNum = row.getRowNum();
                            return rowNum;  
                    }
                }
            }
        }               
        return rowNum;
    }

    private static int findRows(HSSFSheet sheet, String cellContent) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                        return row.getRowNum();  
                    }
                }
            }
        }               
        return 0;
    }
	
	    //This method takes row number as a parameter and returns the data of given row number.
	    public static XSSFRow getRowData(String fileLocation,int RowNum) throws Exception, IOException  {
	    	XSSFRow      row; 
	    	// Open the Excel file
	        FileInputStream fis = new FileInputStream(fileLocation);
	        //XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        //XSSFSheet sheet = workbook.getSheet("Sheet");
	        try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	        	XSSFSheet sheet = workbook.getSheetAt(0);
	        	row = sheet.getRow(RowNum);
	        	}
	        	return row;
	    }
	    
	    //This method reads the test data from the Excel cell.
	    //We are passing row number and column number as parameters.
	    public static String getCellData(int RowNum, int ColNum,String ExcelPathAndName) throws Exception, IOException {
	    	XSSFCell     cell; //Excel cell
			File f = new File(ExcelPathAndName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
	        cell = sheet.getRow(RowNum).getCell(ColNum);
	        DataFormatter formatter = new DataFormatter();
	        workbook.close();
	        return formatter.formatCellValue(cell);
	    }
	    
	    //This method gets excel file, row and column number and set a value to the that cell.
	    public static void setCellData(int RowNum, int ColNum, String value,String ExcelPathAndName)throws Exception, IOException {
	    	
	        XSSFCell     cell; //Excel cell
	        XSSFRow      row; 
	        
			File f = new File(ExcelPathAndName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
	        
	        row = sheet.getRow(RowNum);
	        cell = row.getCell(ColNum);
	        if (cell == null) {
	            cell = row.createCell(ColNum);
	            cell.setCellValue(value);
	        } else {
	            cell.setCellValue(value);
	        }
	        // Constant variables Test Data path and Test Data file name
	        FileOutputStream fileOut = new FileOutputStream(ExcelPathAndName);
	        workbook.write(fileOut);
	        workbook.close();
	        fileOut.flush();
	        fileOut.close();
	    }
	    
	 
	public static String cellValueStr(HSSFWorkbook workbook, HSSFRow row, int cellNum){
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
	
		public void logTestResult(String tcname,String tcStatus) throws Exception 
		{ //datapoolPath,tcID,tcStatus
		        int tcRowNum; 
				String workspace = System.getProperty("user.dir");
		        String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		        tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcname); 
		        logInfo("Updating Excel for the Test Result:   "+datapoolPath);
		        logInfo("Loging Test Result for the test cases: "+ tcname);
		        logInfo("Test Case Row No Is: " + tcRowNum);
		        if(tcStatus.equalsIgnoreCase("PASS")) 
		        {
		        	logInfo("Test Case "+ tcname + " is Passed" );
		            ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum,"PASS" , "GREEN");
		        } 
		        else 
		        { 
		        	logInfo("Test Case "+ tcname + " is Failed" );
		            ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "Fail", "RED");
			        } 
		   }
		
		
		public static void logTestResult(String datapoolPath,String worksheet, String statusColum,String tcname,String tcStatus, String colour) throws Exception 
		{ 
//				String workspace = System.getProperty("user.dir");
//		        String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		        int tcRowNum = ExcelUtils.getRowNum(datapoolPath,worksheet,"testCase",tcname); 
		        logInfo("Updating Excel for the Test Result:   "+datapoolPath);
		        logInfo("Loging Test Result for the test cases: "+ tcname);
		        logInfo("Test Case Row No Is: " + tcRowNum);
		        logInfo("Test Case Row No Is: " + statusColum);
		        if(tcStatus.equalsIgnoreCase("PASS")) 
		        {
		        	logInfo("Test Case "+ tcname + " is Passed" );
		            ExcelUtils.setCellData(datapoolPath, statusColum, tcRowNum,tcStatus , colour);
		        } 
		        else 
		        { 
		        	logInfo("Test Case "+ tcname + " is Failed" );
		            ExcelUtils.setCellData(datapoolPath, statusColum, tcRowNum, tcStatus, colour);
			        } 
		   }
		
		

		
		public void logTestResult(String datapoolPath,String worksheetName, String tcname,String tcStatus) throws Exception 
		{ //datapoolPath,tcID,tcStatus
		        int tcRowNum; 
		        tcRowNum = ExcelUtils.getRowNum(datapoolPath,worksheetName,"testCase",tcname); 
		        logInfo("Updating Excel for the Test Result:   "+datapoolPath);
		        logInfo("Loging Test Result for the test cases: "+ tcname + " at " + tcRowNum );
		        if(tcStatus.equalsIgnoreCase("PASS")) 
		        {
		        	threadsleep(1000);            
		        	logInfo("Test Case "+ tcname + " is Passed" );
		            ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum,"PASS" , "GREEN");
		        } 
		        else 
		        { 
		        	threadsleep(1000);
		        	logInfo("Test Case "+ tcname + " is Failed" );
		            ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "Fail", "RED");
			        } 
		   }
		
		public  static String getCellData(int RowNum, int ColNum) throws Exception {
			String CellData = null;
			try {
				newCell = sheet.getRow(RowNum).getCell(ColNum,org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

				try {
					newCell = sheet.getRow(RowNum).getCell(ColNum);
				} catch (NullPointerException npe) {
					CellData = " ";
				}

				if (newCell == null) {
					CellData = " ";
				} else {
					DataFormatter formatter = new DataFormatter();
					CellData = formatter.formatCellValue(newCell);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return CellData;

		}
		
		
		public static void logTestResult(String fileName, String sheetName, int rowNum, String tcStatus, String tcname)
				throws Exception { // datapoolPath,tcID,tcStatus
			logInfo("Updating Test Case Result in File : " + fileName);
			logInfo("Updating Test Case Result in Worksheet : " + sheetName);
			logInfo("Test Case " + tcname + " is at Row No : " + rowNum);
			if (tcStatus.equalsIgnoreCase("PASS")) {
				threadsleep(1000);
				logInfo("Test Case " + tcname + " is Passed");
				//ExcelUtils.setCellData(fileName, sheetName, rowNum, "Status", tcStatus, "GREEN");
				ExcelUtils.setCellData(fileName,  "Status", rowNum,tcStatus, "GREEN",sheetName);
			} else {
				threadsleep(1000);
				logInfo("Test Case " + tcname + " is Failed");
				ExcelUtils.setCellData(fileName,  "Status", rowNum,tcStatus, "RED",sheetName);
			}
		}


		public static int getRowNumXSSF(String FilePath, String sheetName, String colName, String cellValue)
				throws Exception, IOException {

			XSSFCell cell; // Excel cell
			XSSFRow row;

			File f = new File(FilePath);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);

			for (int i = 1; i <= getRowCnt(workbook, sheetName); i++) {
				if (getCellValue(workbook, sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;
		}
		
		public static int getRowNumHSSF(String FilePath, String sheetName, String colName, String cellValue)
				throws Exception, IOException {

			HSSFCell cell; // Excel cell
			HSSFRow row;

			File f = new File(FilePath);
			FileInputStream ios = new FileInputStream(f);
			HSSFWorkbook workbook = new HSSFWorkbook(ios);

			for (int i = 1; i <= getRowCnt(workbook, sheetName); i++) {
				if (getCellValue(workbook, sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;
		}

		/**
		 * This method is used to get cell row number based on cell value
		 * 
		 * @param sheetName - Sheet name
		 * @param colName   - Column name
		 * @param cellValue - Cell Value/Test Case name
		 * @return Cell row number
		 */
		public static int getRowCnt(XSSFWorkbook workbook, String sheetName) {
			XSSFSheet sheet;
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}

		}

		@SuppressWarnings("deprecation")
		public static String getCellValue(XSSFWorkbook workbook, String sheetName, String colName, int rowNum) {
			XSSFRow row = null;
			XSSFCell cell = null;
			XSSFSheet sheet;

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

		// This method reads the test data from the Excel cell.
		// We are passing row number and column number as parameters.
		public static String getCellData(int RowNum, int ColNum, String ExcelPathAndName, String sheetName)
				throws Exception, IOException {
			XSSFCell cell; // Excel cell
			File f = new File(ExcelPathAndName);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			cell = sheet.getRow(RowNum).getCell(ColNum);
			DataFormatter formatter = new DataFormatter();
			workbook.close();
			return formatter.formatCellValue(cell);
		}
		
		
		// This method reads the test data from the Excel cell.
		// We are passing row number and column number as parameters.
		public static String getCellDataX(int RowNum, int ColNum, String ExcelPathAndName, String sheetName)
				throws Exception, IOException {
			HSSFCell cell; // Excel cell
			File f = new File(ExcelPathAndName);
			FileInputStream ios = new FileInputStream(f);
			HSSFWorkbook workbook = new HSSFWorkbook(ios);
			HSSFSheet sheet = workbook.getSheet(sheetName);

			cell = sheet.getRow(RowNum).getCell(ColNum);
			DataFormatter formatter = new DataFormatter();
			workbook.close();
			return formatter.formatCellValue(cell);
		}
		

		/**
		 * This method is used to get data from a cell from an excel sheet of excel file
		 * using column name and row name.
		 * 
		 * @param sheetName - Name of Excel Sheet
		 * @param colName   - Name of Column
		 * @param rowNum    - Row Number
		 * @return Value of that cell
		 */
		@SuppressWarnings("deprecation")
		public static String getCellDataX(String excelPathAndName, int sheetIndex, String colName, String tcName) {
			HSSFCell cell; // Excel cell
			HSSFRow row = null;
			HSSFWorkbook workbook = null;
			File f = new File(excelPathAndName);
			//System.out.println("-----------------------------" + excelPathAndName);
			FileInputStream ios;

			try {
				ios = new FileInputStream(f);
				workbook = new HSSFWorkbook(ios);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			int expectedColNum = -1;
			int expectedRowNum = -1;
			try {

//				if (rowNum <= 0)
//					return "";

//				int index = workbook.getSheetIndex(sheetName);

//				if (index == -1)
//					return "";
	//
//				sheet = workbook.getSheetAt(index);
				try {
					row = sheet.getRow(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < row.getLastCellNum(); i++) {

					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						expectedColNum = i;
				}
				//System.out.println("No of Rows in Excel: " + sheet.getLastRowNum());
				if (expectedColNum == -1)
					return "";
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {

					if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(tcName.trim())) {
						expectedRowNum = i;
						break;
					}
				}
				if (expectedRowNum == -1)
					return "";

//				sheet = workbook.getSheetAt(index);
//				row = sheet.getRow(rowNum - 1);

				if (row == null)
					return "";
				cell = sheet.getRow(expectedRowNum).getCell(expectedColNum);

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
				return "row " + tcName + " or column " + colName + " does not exist in xls";
			}
		}

		/**
		 * This method is used to get data from a cell from an excel sheet of excel file
		 * using column name and row name.
		 * 
		 * @param sheetName - Name of Excel Sheet
		 * @param colName   - Name of Column
		 * @param rowNum    - Row Number
		 * @return Value of that cell
		 */
		@SuppressWarnings("deprecation")
		public static String getCellData(String excelPathAndName, int sheetIndex, String colName, String tcName) {
			XSSFCell cell; // Excel cell
			XSSFRow row = null;
			XSSFWorkbook workbook = null;
			File f = new File(excelPathAndName);

			FileInputStream ios;

			try {
				ios = new FileInputStream(f);
				workbook = new XSSFWorkbook(ios);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			int expectedColNum = -1;
			int expectedRowNum = -1;
			try {

//				if (rowNum <= 0)
//					return "";

//				int index = workbook.getSheetIndex(sheetName);

//				if (index == -1)
//					return "";
	//
//				sheet = workbook.getSheetAt(index);
				try {
					row = sheet.getRow(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < row.getLastCellNum(); i++) {

					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						expectedColNum = i;
				}
				//System.out.println("No of Rows in Excel: " + sheet.getLastRowNum());
				if (expectedColNum == -1)
					return "";
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {

					if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(tcName.trim())) {
						expectedRowNum = i;
						break;
					}
				}
				if (expectedRowNum == -1)
					return "";

//				sheet = workbook.getSheetAt(index);
//				row = sheet.getRow(rowNum - 1);

				if (row == null)
					return "";
				cell = sheet.getRow(expectedRowNum).getCell(expectedColNum);

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
				return "row " + tcName + " or column " + colName + "does not exist in xls";
			}
		}
		

	
	public static Object[][] readExcel(String file_location, String SheetName, int startRow) throws IOException
	 {
			FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
			hssfWorkBook = new HSSFWorkbook (fileInputStream); //get my workbook 
			hssfSheet=hssfWorkBook.getSheet(SheetName);// get my sheet from workbook
		    HSSFRow Row=hssfSheet.getRow(0);   //get my Row which start from 0   
	    	int RowNum = hssfSheet.getPhysicalNumberOfRows();// count my number of Rows
	    	int ColNum= Row.getLastCellNum(); // get last ColNum 
	    	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	    	
	     for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	     {  
		     HSSFRow row= hssfSheet.getRow(i+1);
		     for (int j=0; j<ColNum; j++) //Loop work for colNum
		     {
			     if(row==null)
			     Data[i][j]= "";
			     else 
			     {
				     HSSFCell cell= row.getCell(j);
				     if(cell==null)
				     {
				     Data[i][j]= ""; //if it get Null value it pass no data 
				     }
				     else
				     {
					     String value=formatter.formatCellValue(cell);
					     Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
					     System.out.print(row.getCell(j) + " || ");
					 }
			     }
		     }
	     }
	    	return Data;
	    }

	
	public void WriteResult(String file_location, String SheetName,String ColName, String Result, int rowNo) throws Exception
	 {
		 //public String ColName="Result";
		 FileInputStream file_input_stream= new FileInputStream(file_location);
		 hssfWorkBook=new HSSFWorkbook(file_input_stream);
		 hssfSheet=hssfWorkBook.getSheet(SheetName);
		 HSSFRow Row=hssfSheet.getRow(0);
	 
		 int sheetIndex=hssfWorkBook.getSheetIndex(SheetName);
		 DataFormatter formatter = new DataFormatter();
	 if(sheetIndex==-1)
	 {
	 System.out.println("No such sheet in file exists");
	 } else {
	 col_num=-1;
	 for(int i=0;i<Row.getLastCellNum();i++)
	 {
	 HSSFCell cols=Row.getCell(i);
	 String colsval=formatter.formatCellValue(cols);
	 if(colsval.trim().equalsIgnoreCase(ColName.trim()))
	 {
	 col_num=i;
	 break;
	 }
	 }
	// 
	 Row= hssfSheet.getRow(rowNo);
	 try
	 {
	 //get my Row which is equal to Data  Result and that colNum
	 HSSFCell cell=hssfSheet.getRow(rowNo).getCell(col_num);
	 // if no cell found then it create cell
	 if(cell==null) {
	 cell=Row.createCell(col_num); 
	 }
	 //Set Result is pass in that cell number
	 cell.setCellValue(Result);
	 
	 
	 }
	 catch (Exception e)
	 {
	     System.out.println(e.getMessage()); 
	 } 
	   
	 }
	 FileOutputStream file_output_stream=new FileOutputStream(file_location);
	 workbook.write(file_output_stream);
	 file_output_stream.close();
	 if(col_num==-1) {
	 System.out.println("Column you are searching for does not exist");
	 }
	 }
	

	public  static Object[][] getTableArray(String FilePath, String SheetName, int startRow) throws Exception {

		String[][] tabArray = null;

		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			workbook = new XSSFWorkbook(ExcelFile);
			sheet = workbook.getSheet(SheetName);
			int startCol = 1;
			int ci, cj;
			int totalRowsToRead = sheet.getLastRowNum() - startRow + 1;
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum() - 2;
			System.out.println("totalRows: " + totalRows);
			System.out.println("totalCols: " + totalCols);
			tabArray = new String[totalRowsToRead][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	

	
}// End of Class
