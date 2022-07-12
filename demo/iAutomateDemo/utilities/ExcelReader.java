package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Recordset;
import com.project.testbase.TestBase;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//import org.apache.poi.ss.formula.functions.Rows;

public class ExcelReader extends TestBase {
	public String path = null;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;


	//Populate a Map <Key - String (testcaseName), Value - Map <String,String> (Param values)>
	/**
	 * This method is used to all data from excel file using Fillo Excel API for Java.
	 * @param FileNameLocation - Location of the file in the system.
	 * @param strSheetName - Name of sheet from which data has to be read.
	 * @return HashMap A HashMap of String Key and HashMap Values of all the data from the sheet.
	 * @throws FilloException - This occurs when there is problem in query operation for file.
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<String, HashMap> ReadAllDataFromExcelSheet(String FileNameLocation, String strSheetName )
			throws FilloException {

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(FileNameLocation);

		HashMap<String, HashMap> hashExcelDataList = new HashMap<String, HashMap>();
		String strQuery = "Select * from " + strSheetName;

		Recordset rs = connection.executeQuery(strQuery);

		List lstFieldNames = rs.getFieldNames();		

		while (rs.next()) {
			HashMap<String, String> hashExcelrow = new HashMap<String, String>();
			for (int i = 1; i<lstFieldNames.size();i++)
			{				
				String strFieldValue = rs.getField(lstFieldNames.get(i).toString());
				hashExcelrow.put(lstFieldNames.get(i).toString(), strFieldValue);				
			}			
			String str = rs.getField(lstFieldNames.get(0).toString());
			hashExcelDataList.put(str, hashExcelrow);
		}
		return hashExcelDataList;
	}

	/**
	 * Read all data of a sheet from an excel file using SQL Query. Populate HashMap inside HashMap using key value pair
	 * 
	 * @param strExcelPath [String] : File Path
	 * @param strSheetName [String] : Sheet name to get the test data
	 * @throws FilloException : No records found or Workbook not present
	 * 
	 * @return HashMap of Excel Data List [HashMap]
	 */

	//Populate a Map <Key - String (testcaseName), Value - Map <String,String> (Param values)>

	public static HashMap<String, HashMap<String, String>> ReadAllDataFromExcelSheets(String strExcelPath, String strSheetName) {

		Fillo fillo;
		Connection connection;
		HashMap<String, HashMap<String, String>> hashExcelDataList = null;
		String strSqlQuery;
		Recordset rs;
		ArrayList<String> lstFieldNames;

		try {
			fillo = new Fillo();
			connection = fillo.getConnection(strExcelPath);

			hashExcelDataList = new HashMap<String, HashMap<String, String>>();
			strSqlQuery = "Select * from " + strSheetName;

			logInfo("Selected all the values present in a sheet. Those present inside the excel and stored its output in a string variable");

			rs = connection.executeQuery(strSqlQuery);
			logInfo("Executed Select query and records retrieved");
			lstFieldNames = rs.getFieldNames();		

			while (rs.next()) {
				HashMap<String, String> hashExcelrow = new HashMap<String, String>();
				for (int i = 1; i<lstFieldNames.size();i++)
				{				
					String strFieldValue = rs.getField(lstFieldNames.get(i).toString());
					hashExcelrow.put(lstFieldNames.get(i).toString(), strFieldValue);				
				}			
				String str = rs.getField(lstFieldNames.get(0).toString());
				hashExcelDataList.put(str, hashExcelrow);
			}
			logInfo("Returned all datas from mentioned excel sheet "+hashExcelDataList);
		}
		catch(FilloException fe) {
			logError("Exception caught while retrieving values "+fe.getMessage());
		}
		catch(Exception e) {
			logError("Exception caught while retrieving values "+e.getMessage());
		}
		return hashExcelDataList;
	}

	/**
	 * This method is used to get the data from excel sheet corresponding to TestCase name.
	 * @param sheetname - Name of the sheet.
	 * @param TestcaseName - Name of the Test Case.
	 * @return HashMap A HashMap of String Key and String Values having the data.
	 * @throws FilloException - This occurs when there is problem in query operation or accessing the file for read/write.
	 */
	public static HashMap<String, String> getData(String sheetname, String TestcaseName) throws FilloException {

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(".//Data//TestData.xlsx");

		String strQuery = "Select * from ServiceInfo where APITestName =" + "'" + TestcaseName + "'" + "";
		Recordset rs = connection.executeQuery(strQuery);

		List<String> columns = new ArrayList<String>(rs.getCount());

		while (rs.next()) {

			rs.getFieldNames();

			columns = rs.getFieldNames();

			// System.out.println(rs.getFieldNames());

		}

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < columns.size(); i++) {

			String fieldname = columns.get(i);
			map.put(columns.get(i), rs.getField(fieldname));
		}

		for (@SuppressWarnings("unused") String i : map.keySet()) {
			// System.out.println("key: " + i + " value: " + map.get(i));
		}

		rs.close();
		connection.close();

		return map;

	}

	/**
	 * This method is used to get data using query "Select * from TestCycleNameAndTestCases" for Jira TestCase, form a excel file.
	 * @param FileNamenLocation - Location of the file in the system
	 * @return List of String after performing the query operation on the file data
	 * @throws FilloException - This occurs when there is problem in query operation or accessing the file for read/write.
	 */
	public static List<String> GetDataFromGivenQueryFORJiraTestCases(String FileNamenLocation) throws FilloException {
		// Sample Query is Select * from ExcelTabName;
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(FileNamenLocation);
		Recordset rs = connection.executeQuery("Select * from TestCycleNameAndTestCases");
		List<String> List = new ArrayList<String>();
		while (rs.next()) {
			ArrayList<String> dataColl = rs.getFieldNames();
			Iterator<String> dataIterator = dataColl.iterator();
			// System.out.println(dataColl.size());

			while (dataIterator.hasNext()) {
				for (int i = 0; i < dataColl.size(); i++) {
					// System.out.println(i);
					String data = dataIterator.next();
					String dataVal = rs.getField(data);
					List.add(dataVal);
				}
			}
		}
		return List;
	}

	/**
	 * This method is used to get Test Data from excel file for custom input.
	 * @param FileNamenLocation - Location of the file in the system
	 * @param coulmnName - Column Name 
	 * @return List of string with all the fetched data.
	 * @throws FilloException - This occurs when there is problem in query operation for file or accessing the file for read/write.
	 */
	public static List<String> GetDataFromExcelForCustomInput(String FileNamenLocation, String coulmnName)
			throws FilloException {
		List<String> List = new ArrayList<String>();
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(FileNamenLocation);
			Recordset rs = connection.executeQuery("Select * from TestData_Custom");

			while (rs.next()) {
				ArrayList<String> dataColl = rs.getFieldNames();
				@SuppressWarnings("unused")
				Iterator<String> dataIterator = dataColl.iterator();
				String dataVal = rs.getField(coulmnName);
				if (dataVal.trim() != null && !dataVal.trim().isEmpty()) {
					List.add(dataVal.trim());
				}
			}

		} catch (Exception e) {
			Assert.fail("Excel Sheet Issue", e.getCause());
		}
		return List;

	}
	//************************************//BIREPORT\\***************************\\
	/**
	 * This method is used to get data of a particular column from a sheet in Excel file. 
	 * @param FileNamenLocation - Location of the file in the system.
	 * @param coulmnName - Name of Column whose data is to be fetched.
	 * @param sheetName - Name of sheet in excel file.
	 * @return List of data fetched for that particular column.
	 * @throws FilloException - This occurs when there is problem in query operation or accessing the file for read/write.
	 */
	public static List<String> GetDataFromExcelColumn(String FileNamenLocation,String coulmnName, String sheetName) throws FilloException {
		List<String> List = new ArrayList<String>();
		try{
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(FileNamenLocation);
			Recordset rs = connection.executeQuery("Select * from "+sheetName);

			while (rs.next()) {
				ArrayList<String> dataColl = rs.getFieldNames();
				@SuppressWarnings("unused")
				Iterator<String> dataIterator = dataColl.iterator();
				String dataVal = rs.getField(coulmnName);
				if(dataVal.trim()!=null && !dataVal.trim().isEmpty()){
					List.add(dataVal.trim());}
			}

		}
		catch(Exception e){
			Assert.fail("Excel Sheet Issue", e.getCause());
		}
		return List;

	}

	//*******************************

	/**
	 * This method is used to read csv file and return in StringBuilder format.
	 * @param CsvPath - Location of csv file in the system.
	 * @return StringBuilder - Column Data
	 */
	@SuppressWarnings("deprecation")
	public static StringBuilder ControlAFromExcel(String CsvPath) {
		StringBuilder columndata = null;
		try {
			File f = new File(CsvPath);
			FileInputStream ios = new FileInputStream(f);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new StringBuilder();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						columndata.append(String.valueOf(cell.getNumericCellValue()).replaceFirst("\\.0+$", ""));
						break;
					case Cell.CELL_TYPE_STRING:
						columndata.append(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						columndata.append(cell.getBooleanCellValue());
						break;
					}
					if (cellIterator.hasNext())
						columndata.append("\t");// adding tab after each column
					// value
				}
				columndata.append("\n");// adding new line after each row
			}
			ios.close();
			System.out.println(columndata);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndata;

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
	 * This method is used to update excel sheet from excel file using Apache POI Library.
	 * @param row - Row Number where the update is to be made
	 * @param cell - Cell Number which is to be updated
	 * @param NewValue - New Value
	 * @param ExcelPathAndName -Location and name of the excel file.
	 * @throws FilloException - This occurs when there is problem in query operation or accessing the file for read/write.
	 * @throws IOException - Throws when unable to perform read/write operation on file.
	 */
	public static void UpdateExcelUsingApache(int row, int cell, String NewValue, String ExcelPathAndName)
			throws FilloException, IOException {
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

	/*
	 * Excel Reader Code starts here
	 * 
	 */

	/**
	 * This method is used to read excel sheet from excel file using Apache POI library
	 * @param path - Location of the excel file in the system
	 * @throws IOException - Throws when unable to perform read/write operation on file.
	 */
	public ExcelReader(String path) throws IOException {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

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

	// return a data from cell
	/*
	 * CELL_TYPE_BLANK: Represents a blank cell. CELL_TYPE_BOOLEAN: Represents a
	 * Boolean cell (true or false). CELL_TYPE_ERROR: Represents an error value
	 * in a cell. CELL_TYPE_FORMULA: Represents a formula result on a cell.
	 * CELL_TYPE_NUMERIC: Represents numeric data in a cell. CELL_TYPE_STRING:
	 * Represents string in a cell.
	 */

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
	public boolean setCellData(String sheetName, String colName, int rowNum, String data, String color) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
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
	/**
	 * This method is used to Set data in the cell for mentioned row number and column name in the excel sheet.
	 * Convert the added cell data into hyper link and navigate to added URL. 
	 * @param sheetName - Sheet name
	 * @param colName - Column name
	 * @param rowNum - Row number
	 * @param data - Data
	 * @param url - URL
	 * @return True after successfully setting the data in the cell and making into hyper link. False if fails to do so.
	 */
	public boolean setCellDataWithURL(String sheetName, String colName, int rowNum, String data, String url) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

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
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

			// Cell style for hyperlinks
			CellStyle hlink_style = workbook.createCellStyle();
			XSSFFont hlink_font = workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);

			// hlink_style.setWrapText(true);

			XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);

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
	/**
	 * This method is used to Set data in the cell for mentioned row number and column number in the excel sheet.
	 * @param sheetName - Name of the sheet
	 * @param colNumber - Column Number
	 * @param rowNum - Row Number
	 * @param data - Data
	 * @return True after successfully setting the data in the cell. False if fails to do so.
	 */
	public boolean setCellDataInt(String sheetName, int colNumber, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);

			if (colNumber == -1)
				return false;

			sheet.autoSizeColumn(colNumber);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNumber);

			if (cell == null)
				cell = row.createCell(colNumber);

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
	// return true if sheet is created successfully else false

	/**
	 * This method is used to add a new sheet to an existing excel file.
	 * @param sheetname - Name for the Sheet to be added in an excel file..
	 * @return True if sheet is added successfully.
	 */
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// return true of sheet is removed successfully else false if sheet does not
	// exist
	/**
	 * This method is used to remove a sheet from an excel file.
	 * @param sheetName - Name of the Sheet to be removed from an excel file.
	 * @return True if sheet is removed successfully.
	 */
	public boolean removeSheet(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1)
			return false;
		FileOutputStream fileOut;

		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

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
	// String sheetname ,string test case string keyword ,string url ,string
	/**
	 * This method is used to add Hyperlink in the mentioned sheet of an excel by passing URL as an argument.
	 * @param sheetName - Sheet name
	 * @param screenShotColName - Screenshot name
	 * @param testCaseName - Test case name
	 * @param index - Index
	 * @param url - URL
	 * @param message - Message
	 * @return True if Hyper link is added in the sheet, else false.
	 */
	public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url,
			String message) {

		url = url.replace("\\", "/");

		if (!isSheetExist(sheetName))
			return false;

		sheet = workbook.getSheet(sheetName);
		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {

				setCellData(sheetName, screenShotColName, i + index, message, url);
				break;
			}
		}
		return true;
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

	public String getNumberOfSheets(String value) {
		for (int i = 0; i < workbook.getNumberOfSheets(); i++)
			if (workbook.getSheetName(i).equals(value))
				return workbook.getSheetName(i);
		return null;
	}

	/**
	 * This method is used to get list of file names in a folder based on a particular extension.
	 * @param folderPath - Folder path in the system.
	 * @param fileExtension - File extension to be searched for. 
	 * @return List of file names
	 * @throws IOException - Error in input output operation.
	 */
	public List<String> getListOfFilesNames(String folderPath, String fileExtension) throws IOException {
		List<String> fileNameList = new ArrayList<String>();
		File dir = new File(folderPath);
		String[] extensions = new String[] { fileExtension };
		System.out
		.println("Getting all .xlsx files in " + dir.getCanonicalPath() + " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			// System.out.println("file: " + file.getCanonicalPath());
			fileNameList.add(file.getName().trim());
		}
		return fileNameList;
	}

	// Mehtod to get Name of all sheets in a excel file
	/**
	 * This method is used to get Name of all sheets present in the excel file.
	 * @param filePath - Location of the Excel file in system
	 * @return List of sheet names in that excel file.
	 * @throws EncryptedDocumentException - Document is encrypted while opening
	 * @throws IOException - Error in input output operation
	 * @throws InvalidFormatException -Invalid file format
	 */
	public List<String> getListOfSheetNames(String filePath)
			throws EncryptedDocumentException, IOException, InvalidFormatException {

		File myFile = new File(filePath);
		Workbook workbook = WorkbookFactory.create(myFile);

		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		//workbook.close();
		return sheetNames;
	}

	// This Method reads data from a sheet and populates into a list of
	// Map<String,String>
	/**
	 * This method  reads all data from a sheet of an excel file and populates into a list
	 * @param filePath - Location of the Excel file in system
	 * @param sheetName - Name of sheet in the excel file 
	 * @return List - list of data which has A HashMap of String Key and String Values
	 * @throws IOException - Error in input output operation
	 */
	public List<Map<String, String>> readSheetDataFromExcel(String filePath, String sheetName) throws IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream fileInputStream = new FileInputStream(filePath); // Excel
		// sheet
		// file
		// location
		// get
		// mentioned
		// here
		Workbook workbook = new XSSFWorkbook(fileInputStream); // get my
		// workbook
		Sheet worksheet = workbook.getSheet(sheetName);// get my sheet from
		// workbook
		System.out.println("Reading sheet :: " + sheetName);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (worksheet != null) {
			Row Row = worksheet.getRow(0); // get my Row which start from 0

			int RowNum = worksheet.getPhysicalNumberOfRows();// count my number
			// of Rows
			int ColNum = Row.getLastCellNum(); // get last ColNum

			for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
			{
				Row row = worksheet.getRow(i + 1);// Pass i+1 to exclude headers
				// from the excel
				if (row != null && !isRowEmpty(row))// if row is empty or not
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int j = 0; j < ColNum; j++) // Loop work for colNum
					{
						if (row != null) {

							Cell cell = row.getCell(j);
							if (cell != null) {
								String value = formatter.formatCellValue(cell); // This
								// formatter
								// get
								// my
								// all
								// values
								// as
								// string
								// i.e
								// integer,
								// float
								// all
								// type
								map.put(Row.getCell(j).toString(), value);

							} // data value

						}
					}
					list.add(map);

				}
			}

		}
		fileInputStream.close();
		workbook.close();
		return list;
	}

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

	// This method returns true if sheet is empty
	/**
	 * This method is used to check if sheet is not present or null in the existing excel sheet.
	 * @param filePath - Location of the Excel file in the system.
	 * @param sheetName - Name of the Excel Sheet
	 * @return True if sheet is empty.
	 * @throws IOException - Error in input output operation.
	 */
	@SuppressWarnings({ "resource", "rawtypes" })
	public boolean isSheetEmpty(String filePath, String sheetName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(filePath); // Excel
		// sheet
		// file
		// location
		// get
		// mentioned
		// here
		Workbook workbook = new XSSFWorkbook(fileInputStream); // get my
		// workbook
		Sheet worksheet = workbook.getSheet(sheetName);
		if (worksheet != null) {
			Iterator rows = worksheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					if (!cell.getStringCellValue().isEmpty()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public ExcelReader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is used to get list of headers of mentioned sheet from an excel file.
	 * @param sheet - Name of sheet in the excel file.
	 * @return List - List of headers of String values
	 */
	public List<String> getListOfHeadersFromExcelSheet(Sheet sheet) {

		List<String> headerList = new ArrayList<String>();
		if (sheet != null) {
			DataFormatter formatter = new DataFormatter();
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			if (row != null && !isRowEmpty(row))// if row is empty or not
			{
				for (int i = 0; i < colNum; i++) // Loop work for colNum
				{

					Cell cell = row.getCell(i);
					if (cell != null) {
						String value = formatter.formatCellValue(cell); // This
						// formatter
						// get
						// my
						// all
						// values
						// as
						// string
						// i.e
						// integer,
						// float
						// all
						// type
						headerList.add(value);

					} // data value
				}
			}
		}
		return headerList;
	}

	/**
	 * This method is used to get xPath from header cell.
	 * @param strHeaderCellValue - Header cell value
	 * @return List - List of Strings of xPaths from header cell.
	 */
	public List<String> GetxPathFromHeadercell (String strHeaderCellValue) {

		List <String> lstHeaderXPath = new ArrayList<String>();
		lstHeaderXPath.add("Level1");
		String[] strHeaders = strHeaderCellValue.split("\\.");

		for (int iCount=0; iCount<strHeaders.length;iCount++)
		{
			String val = strHeaders[iCount];
			lstHeaderXPath.add(val);
		}

		return (lstHeaderXPath);
	}

	/*
	 * This method is used to rename sheet based upon the new sheet name for an excel file
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void renameSheet() {
		String mappingFilePath = System.getProperty("user.dir")+"\\test-data-files\\MappingSheet.xlsx";
		String strFolderPath = System.getProperty("user.dir")+"\\test-data-files\\RulesDataSheets";		 

		//Read Mapping Sheet
		try {
			List <Map<String, String>> lstMappingData = ReadMappingSheet (mappingFilePath);

			List <String> lstStoryIds = new ArrayList <String>(); 
			List <String> lstTestIds = new ArrayList <String>();

			for (int rowCount = 0; rowCount < lstMappingData.size(); rowCount++) 
			{
				lstStoryIds.add(lstMappingData.get(rowCount).get("StoryID"));
				lstTestIds.add(lstMappingData.get(rowCount).get("TestID")); 
			}

			// Read Data Sheet
			ExcelReader reader=new ExcelReader();
			String dataFilePath = "";

			List lstFileNames = reader.getListOfFilesNames(strFolderPath,"xlsx");
			for (int ifilesCount=0; ifilesCount<lstFileNames.size();ifilesCount++)
			{
				dataFilePath = strFolderPath + "\\" + lstFileNames.get(ifilesCount);
				System.out.println(dataFilePath);
				FileInputStream fis = new FileInputStream(dataFilePath);
				Workbook workbook = new XSSFWorkbook(fis); 
				List <String> lstSheetNames = new ArrayList <String>();

				//Get list of sheets in the workbook
				lstSheetNames = reader.getListOfSheetNames(dataFilePath);					
				String strOldSheetName = "";

				for (int dataSheetCount = 0; dataSheetCount < lstSheetNames.size(); dataSheetCount++) 
				{			 
					strOldSheetName = lstSheetNames.get(dataSheetCount);

					if (lstStoryIds.contains(strOldSheetName)) 
					{ 				 		
						workbook.setSheetName(dataSheetCount, lstTestIds.get(lstStoryIds.indexOf(strOldSheetName)));
					} 
					else 
					{ System.out.println ("No mapping found for Sheet : " + workbook.getSheetName(dataSheetCount).toString()); 
					}
				}		

				File file = new File(dataFilePath); 
				FileOutputStream fout = new	FileOutputStream(dataFilePath); 
				workbook.write(fout); 
				fout.close();
				workbook.close();				
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}	


	}


	/**
	 * This method is used to read mapping sheet
	 * @param mappingFilePath - Location of mapping file
	 * @return List - List of Mapping data which has A HashMap of String Key and String Values
	 * @throws IOException
	 */
	public static List<Map<String, String>> ReadMappingSheet(String mappingFilePath) throws IOException
	{
		// Read Mapping Sheet 
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> lstMappingData = new ArrayList<Map<String,String>>(); 
		lstMappingData = reader.readSheetDataFromExcel(mappingFilePath,"MappingSheet");
		return lstMappingData;
	}

	/** This method is used to get the Last Row count given the sheet and row as input parameters
	 * @author hingorani_a
	 * @param sheet The sheet on which last row count you need information on
	 * @param row An object of Row type in order to iterate through rows of sheet
	 * @return int This returns the value of Last Row count in the sheet
	 */	
	public int getLastRowCount(Sheet sheet , Row row) {

		boolean lastRowFlag = true;
		int lastRow = 0;

		try {
			while(lastRowFlag) {
				row = sheet.getRow(lastRow);

				if(row!=null) {

					try {
						if(row.getCell(0).getStringCellValue() == null)
							lastRowFlag = false;
						else if(row.getCell(0).getStringCellValue()!="")
							lastRow++;
					}
					catch(NullPointerException npe) {
						logInfo("Null data found while finding last row" + npe.getMessage());
						row = sheet.getRow(lastRow-1);
						lastRowFlag = false;
					}
				}

				else
					lastRowFlag = false;
			}
			logInfo("Last row count found as : " + lastRow);
		}
		catch(Exception e) {
			logError("Failed to get Last row count - "+e.getMessage());
		}
		return lastRow;
	}
}
