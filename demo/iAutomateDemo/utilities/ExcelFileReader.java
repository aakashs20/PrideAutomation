package com.project.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelFileReader {
		
		public XSSFSheet sheet = null;
		public  XSSFWorkbook ExcelWBook = null;
		public XSSFSheet ExcelWSheet = null;
		public static DataFormatter formatter = new DataFormatter();
		

		/**
		 * This method is used to read data from a sheet of Excel file row by row excluding the top header row.
		 * @param filePath - Location of excel file in the system. 
		 * @param sheetName - Excel sheet name which has to be read, from the excel file.
		 * @return Data - 2D array of String containing the data read from the excel sheet.
		 * @throws IOException - Throws when unable to perform read/write operation on file.
		 */
		public String[][] readDataFromExcel(String filePath, String sheetName) throws IOException {
			
			FileInputStream fileInputStream = new FileInputStream(filePath); 
			Workbook workbook = new XSSFWorkbook(fileInputStream); 
			Sheet worksheet = workbook.getSheet(sheetName);
			System.out.println("Reading sheet :: " + sheetName);
			Row Row = worksheet.getRow(0); 

			int RowNum = worksheet.getPhysicalNumberOfRows();
			int ColNum = Row.getLastCellNum(); 

			String Data[][] = new String[RowNum - 1][ColNum]; 

			for (int i = 0; i < RowNum - 1; i++) 
			{Row row = worksheet.getRow(i+1);// Pass i+1 to exclude headers from the excel
			
			for (int j = 0; j < ColNum; j++) //
			{
				if (row != null) {
					Cell cell = row.getCell(j);
					if (cell != null) {
		
						String value =formatter.formatCellValue(cell);
						Data[i][j] = value;
					} 
					else {

						Data[i][j] = ""; // if it get Null value it pass no data
					}
				}			
				  else { Data[i][j] = ""; }
				 	}
			fileInputStream.close();
			workbook.close();
		}
   
		return Data;
	}
				

		}





