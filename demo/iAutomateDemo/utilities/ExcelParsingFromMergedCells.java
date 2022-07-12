
package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParsingFromMergedCells {

	/**
	 * This method is used to parse data from a excel sheet of an Exported Excel file from DPT and 
	 * get the field detail (e.g compliance min, max, target) of a field type belonging to resource. It also prints the details of the file in the console. 
	 * @param filepath - Location of the Excel file in the system
	 * @param resourceName - Name of the resource
	 * @param fieldType1 - Type of the field
	 * @param fieldDetail1 - The field detail to be fetched
	 * @return String - The particular field detail of a field.
	 */
	@SuppressWarnings("deprecation")
	public String getFieldData(String filepath, String resourceName, String fieldType1, String fieldDetail1) {
		// TODO Auto-generated method stub
		FileInputStream file = null;
		// Boolean flag = true;
		Map<String, ArrayList<String>> formDetails = new LinkedHashMap<String, ArrayList<String>>();
		Map<String, ArrayList<Integer>> fieldTypeDetails = new LinkedHashMap<String, ArrayList<Integer>>();
		ArrayList<String> list = null;
		ArrayList<Integer> formTypeFieldDetails = null;
		// Map<String, LinkedHashMap<String, ArrayList<String>>> FieldTypeValueDetails =
		// new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		// LinkedHashMap<String, ArrayList<String>> FieldTypeDetails = null;
		// ArrayList<String> FieldTypeValues = null;
		Map<String, ArrayList<ArrayList<String>>> FieldTypeValueDetails = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> FieldTypeValues = null;
		ArrayList<String> FieldValues = null;
		Set<String> resource = new LinkedHashSet<>();
		String CellHeader = null;
		try {

			// here uploadFolder contains the path to the Login 3.xlsx file

			file = new FileInputStream(new File(filepath));

			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0; i < 10; i++) {

				Row row = sheet.getRow(i);
				list = new ArrayList<String>();
				outer: for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);

					// Print Excel data in console
					for (int k = 0; k < sheet.getNumMergedRegions(); k++) {
						CellRangeAddress region = sheet.getMergedRegion(k); // Region of merged cells

						int colIndex = region.getFirstColumn();
						int rowNum = region.getFirstRow();
						if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex()) {
							//					System.out.println("Num of cells merged: " + region.getNumberOfCells());
							if (j == 0) {
								CellHeader = sheet.getRow(rowNum).getCell(colIndex).getStringCellValue();
								System.out.println("Cell Header: " + CellHeader);
								if (CellHeader.equalsIgnoreCase("Field Type")) {
									formTypeFieldDetails = new ArrayList<>();
								}
							} else {
								String CellValue = sheet.getRow(rowNum).getCell(colIndex).getStringCellValue();
								System.out.println("Cell Value: " + CellValue);
								list.add(CellValue);
								if (CellHeader.equalsIgnoreCase("Field Type")) {

									formTypeFieldDetails = new ArrayList<>();
									formTypeFieldDetails.add(colIndex);
									formTypeFieldDetails.add(region.getNumberOfCells());
									fieldTypeDetails.put(CellValue, formTypeFieldDetails);

								}

							}
							formDetails.put(CellHeader, list);
							continue outer;
						}

					}
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
						continue;
					}
					System.out.print(row.getCell(j).getStringCellValue() + "|| ");

				}
				System.out.println("HashMap starts:-------------");
				for (Map.Entry<String, ArrayList<String>> h : formDetails.entrySet()) {

					System.out.println("Key: " + h.getKey());
					ArrayList<String> value = h.getValue();
					for (String aString : value) {
						System.out.println(" Value : " + aString);

					}
				}
				System.out.println("Form Type Detail HashMap starts:-------------");
				for (Map.Entry<String, ArrayList<Integer>> h : fieldTypeDetails.entrySet()) {

					System.out.println("Field Type Key: " + h.getKey());
					ArrayList<Integer> value = h.getValue();
					for (Integer aString : value) {
						System.out.println("Field Type Value : " + aString);

					}
				}

				String fieldType = null;
				for (Map.Entry<String, ArrayList<Integer>> h : fieldTypeDetails.entrySet()) {
					ArrayList<Integer> value = h.getValue();
					fieldType = h.getKey();
					System.out.println("Field Type: " + fieldType);
					int start = value.get(0);
					System.out.println("Start: " + start);
					int end = (start + value.get(1)) - 1;
					System.out.println("End: " + end);
					System.out.println("----Field details for field type: " + fieldType);
					@SuppressWarnings("unused")
					String header = null;
					FieldTypeValues = new ArrayList<ArrayList<String>>();

					for (int j = start; j <= end; j++) {
						FieldValues = new ArrayList<String>();
						int listIndex = 0;
						for (int i1 = 10; i1 < 13; i1++) {
							row = sheet.getRow(i1);
							FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
							Cell cell = row.getCell(0);
							resource.add(cell.getStringCellValue());
							DataFormatter formatter = new DataFormatter();
							String strValue = formatter.formatCellValue(row.getCell(j), evaluator);
							FieldValues.add(listIndex, strValue);
							System.out.println("List Index: " + listIndex);
							listIndex++;
						}
						FieldTypeValues.add(FieldValues);
					}
					FieldTypeValueDetails.put(fieldType, FieldTypeValues);
				}
			}
			Iterator<String> it = resource.iterator();
			System.out.println("Resource details:");
			while (it.hasNext())
				System.out.println(it.next());

			for (Map.Entry<String, ArrayList<ArrayList<String>>> h : FieldTypeValueDetails.entrySet()) {
				System.out.println("Main field type: " + h.getKey());
				ArrayList<ArrayList<String>> h1 = h.getValue();
				for (ArrayList<String> h2 : h1) {
					for (String h3 : h2)
						System.out.println("Sub fields: " + h3);
				}
			}

			Iterator<String> it1 = resource.iterator();
			System.out.println("Resource details:");
			int setIndex = 0;
			int flag1 = 0;
			while (it1.hasNext()) {
				if (it1.next().contains(resourceName)) {
					flag1 = 1;
					break;
				} else
					setIndex++;
			}
			if (flag1 == 0) {
				return null;
			}
			for (Map.Entry<String, ArrayList<ArrayList<String>>> h : FieldTypeValueDetails.entrySet()) {
				String mainField = h.getKey();
				if (mainField.equalsIgnoreCase(fieldType1)) {
					ArrayList<ArrayList<String>> h1 = h.getValue();
					for (ArrayList<String> h2 : h1) {
						if (h2.get(0).equalsIgnoreCase(fieldDetail1))
							return (h2.get(setIndex));

					}
				}
			}

		} catch (Exception e) {

			System.out.println("File closing operation failed");
			e.printStackTrace();
		}
		return CellHeader;
	}

	// ------------------------------------------

	/**
	 * This method is used to parse data from a excel sheet of an Exported Excel file from DPT and 
	 * get the field detail (e.g compliance min, max, target) of a field type belonging to resource. It also prints the details of the file in the console. 
	 * @param filepath - Location of the Excel file in the system
	 * @param sheetName - Name of the Sheet whose data you need
	 * @param resourceName - Name of the resource
	 * @param fieldType1 - Type of the field
	 * @param fieldDetail1 - The field detail to be fetched
	 * @return String - The particular field detail of a field.
	 */
	@SuppressWarnings("deprecation")
	public String getFieldData(String filepath, String sheetName, String resourceName, String fieldType1, String fieldDetail1) {
		// TODO Auto-generated method stub
		FileInputStream file = null;
		// Boolean flag = true;
		Map<String, ArrayList<String>> formDetails = new LinkedHashMap<String, ArrayList<String>>();
		Map<String, ArrayList<Integer>> fieldTypeDetails = new LinkedHashMap<String, ArrayList<Integer>>();
		ArrayList<String> list = null;
		ArrayList<Integer> formTypeFieldDetails = null;
		// Map<String, LinkedHashMap<String, ArrayList<String>>> FieldTypeValueDetails =
		// new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();
		// LinkedHashMap<String, ArrayList<String>> FieldTypeDetails = null;
		// ArrayList<String> FieldTypeValues = null;
		Map<String, ArrayList<ArrayList<String>>> FieldTypeValueDetails = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> FieldTypeValues = null;
		ArrayList<String> FieldValues = null;
		Set<String> resource = new LinkedHashSet<>();
		String CellHeader = null;
		try {

			// here uploadFolder contains the path to the Login 3.xlsx file

			file = new FileInputStream(new File(filepath));

			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";

			XSSFSheet sheet = workbook.getSheetAt(index);
			for (int i = 0; i < 10; i++) {

				Row row = sheet.getRow(i);
				list = new ArrayList<String>();
				outer: for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);

					// Print Excel data in console
					for (int k = 0; k < sheet.getNumMergedRegions(); k++) {
						CellRangeAddress region = sheet.getMergedRegion(k); // Region of merged cells

						int colIndex = region.getFirstColumn();
						int rowNum = region.getFirstRow();
						if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex()) {
							//					System.out.println("Num of cells merged: " + region.getNumberOfCells());
							if (j == 0) {
								CellHeader = sheet.getRow(rowNum).getCell(colIndex).getStringCellValue();
								System.out.println("Cell Header: " + CellHeader);
								if (CellHeader.equalsIgnoreCase("Field Type")) {
									formTypeFieldDetails = new ArrayList<>();
								}
							} else {
								String CellValue = sheet.getRow(rowNum).getCell(colIndex).getStringCellValue();
								System.out.println("Cell Value: " + CellValue);
								list.add(CellValue);
								if (CellHeader.equalsIgnoreCase("Field Type")) {

									formTypeFieldDetails = new ArrayList<>();
									formTypeFieldDetails.add(colIndex);
									formTypeFieldDetails.add(region.getNumberOfCells());
									fieldTypeDetails.put(CellValue, formTypeFieldDetails);

								}

							}
							formDetails.put(CellHeader, list);
							continue outer;
						}

					}
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
						continue;
					}
					System.out.print(row.getCell(j).getStringCellValue() + "|| ");

				}
				System.out.println("HashMap starts:-------------");
				for (Map.Entry<String, ArrayList<String>> h : formDetails.entrySet()) {

					System.out.println("Key: " + h.getKey());
					ArrayList<String> value = h.getValue();
					for (String aString : value) {
						System.out.println(" Value : " + aString);

					}
				}
				System.out.println("Form Type Detail HashMap starts:-------------");
				for (Map.Entry<String, ArrayList<Integer>> h : fieldTypeDetails.entrySet()) {

					System.out.println("Field Type Key: " + h.getKey());
					ArrayList<Integer> value = h.getValue();
					for (Integer aString : value) {
						System.out.println("Field Type Value : " + aString);

					}
				}

				String fieldType = null;
				for (Map.Entry<String, ArrayList<Integer>> h : fieldTypeDetails.entrySet()) {
					ArrayList<Integer> value = h.getValue();
					fieldType = h.getKey();
					System.out.println("Field Type: " + fieldType);
					int start = value.get(0);
					System.out.println("Start: " + start);
					int end = (start + value.get(1)) - 1;
					System.out.println("End: " + end);
					System.out.println("----Field details for field type: " + fieldType);
					@SuppressWarnings("unused")
					String header = null;
					FieldTypeValues = new ArrayList<ArrayList<String>>();

					for (int j = start; j <= end; j++) {
						FieldValues = new ArrayList<String>();
						int listIndex = 0;
						for (int i1 = 10; i1 < 13; i1++) {
							row = sheet.getRow(i1);
							FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
							Cell cell = row.getCell(0);
							resource.add(cell.getStringCellValue());
							DataFormatter formatter = new DataFormatter();
							String strValue = formatter.formatCellValue(row.getCell(j), evaluator);
							FieldValues.add(listIndex, strValue);
							System.out.println("List Index: " + listIndex);
							listIndex++;
						}
						FieldTypeValues.add(FieldValues);
					}
					FieldTypeValueDetails.put(fieldType, FieldTypeValues);
				}
			}
			Iterator<String> it = resource.iterator();
			System.out.println("Resource details:");
			while (it.hasNext())
				System.out.println(it.next());

			for (Map.Entry<String, ArrayList<ArrayList<String>>> h : FieldTypeValueDetails.entrySet()) {
				System.out.println("Main field type: " + h.getKey());
				ArrayList<ArrayList<String>> h1 = h.getValue();
				for (ArrayList<String> h2 : h1) {
					for (String h3 : h2)
						System.out.println("Sub fields: " + h3);
				}
			}

			Iterator<String> it1 = resource.iterator();
			System.out.println("Resource details:");
			int setIndex = 0;
			int flag1 = 0;
			while (it1.hasNext()) {
				if (it1.next().contains(resourceName)) {
					flag1 = 1;
					break;
				} else
					setIndex++;
			}
			if (flag1 == 0) {
				return null;
			}
			for (Map.Entry<String, ArrayList<ArrayList<String>>> h : FieldTypeValueDetails.entrySet()) {
				String mainField = h.getKey();
				if (mainField.equalsIgnoreCase(fieldType1)) {
					ArrayList<ArrayList<String>> h1 = h.getValue();
					for (ArrayList<String> h2 : h1) {
						if (h2.get(0).equalsIgnoreCase(fieldDetail1))
							return (h2.get(setIndex));

					}
				}
			}

		} catch (Exception e) {

			System.out.println("File closing operation failed");
			e.printStackTrace();
		}
		return CellHeader;
	}

}
