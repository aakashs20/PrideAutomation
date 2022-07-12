package com.project.utilities;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.project.testbase.TestBase;

public class PDFReader extends TestBase {

	/**
	 * This method is used to read and get text from a PDF file
	 * @author hingorani_a
	 * @param filePath The path along with filename
	 * @return String This returns string with complete PDF data 
	 */
	public static String getTextFromPDF(String filePath) {
		String pdfFileInText = null;
		try (PDDocument document = PDDocument.load(new File(filePath))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				pdfFileInText = tStripper.getText(document);
				document.close();
				
				return pdfFileInText;
			}
			return pdfFileInText;
		} 
		catch (Exception e) {
			logError("Failed to read data from PDF - "
					+ e.getMessage());
			return pdfFileInText;
		}
	}	

}
