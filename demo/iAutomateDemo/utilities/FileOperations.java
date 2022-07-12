package com.project.utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.project.testbase.TestBase;

public class FileOperations extends TestBase {

	public static double bytes, kilobytes, megabytes, gigabytes;
	public static String fileName;

	/**
	 * This method is used to get File size for a given file
	 * @author hingorani_a
	 * @param filepath The path along with filename
	 * @return long This returns file size as value
	 */
	public static long getFileSize(String filepath) {
		long fileSize = -1;
		try {
			File file = new File(filepath);
			if (!file.exists() || !file.isFile()) {
				System.out.println("File doesn\'t exist");
				return -1;
			}

			fileSize = file.length();
			logInfo("For file '" + filepath + "' size is :" + fileSize);
			return fileSize;
		}
		catch (Exception e) {
			logError("Failed to get size for file '" + filepath + "'. Size is - " + fileSize);
			return -1;
		}
	}

	/**
	 * This method is used to get File size for a given file based on size type
	 * @author hingorani_a
	 * @param filepath The path along with filename
	 * @param sizeType Use class SIZETYPE to set a type like Bytes, Kilobytes, Megabytes, etc
	 * @return double This returns file size as value
	 */
	public static double getFileSize(String filepath, String sizeType) {
		double fileSize = -1;
		try {
			File file = new File(filepath);
			if (!file.exists() || !file.isFile()) {
				System.out.println("File doesn\'t exist");
				return -1;
			}
			bytes = file.length();
			kilobytes = (bytes / 1024);
			megabytes = (kilobytes / 1024);
			gigabytes = (megabytes / 1024);

			switch(sizeType) {
			case SIZETYPE.BYTES:
				logInfo("For file '" + filepath + "' size is :" + bytes);
				return bytes;
			case SIZETYPE.KILOBYTES:	
				logInfo("For file '" + filepath + "' size is :" + kilobytes);
				return kilobytes;
			case SIZETYPE.MEGABYTES:
				logInfo("For file '" + filepath + "' size is :" + megabytes);
				return megabytes;
			case SIZETYPE.GIGABYTES:
				logInfo("For file '" + filepath + "' size is :" + gigabytes);
				return gigabytes;
			default:
				logError("Invalid Option");
				return -1;
			}	
		}
		catch (Exception e) {
			logError("Failed to get size for file '" + filepath + "'. Size is - " + fileSize);
			return -1;
		}
	}

	/**
	 * This method is used to verify if File is empty
	 * @author hingorani_a
	 * @param filepath The path along with filename
	 * @return boolean This returns boolean true after verifying if file is empty
	 */
	public static boolean isFileEmpty(String filepath) {
		try {
			File file = new File(filepath); 
			if (file.length() == 0) { 
				logInfo("File '" + filepath + "' is empty ..."); 
				return true;
			} 
			else { 
				logInfo("File '" + filepath + "' is not empty ..."); 
				return false;
			}
		}
		catch (Exception e) {
			logError("Failed to verify if file '" + filepath + "' is empty or not");
			return false;
		}
	}

	/**
	 * This method is used to rename File 
	 * @author pednekar_pr
	 * @param oldName The old name of file
	 * @param newName The new name of file
	 * @return boolean This returns boolean true after verifying if file is empty
	 */	
	public boolean renameFile(String oldName, String newName)
	{
		// Create an object of the File class
		// Replace the file path with path of the directory
		File file = new File(System.getProperty("user.dir")+"\\test-data-files\\UploadDocuments\\"+oldName);

		// Create an object of the File class
		// Replace the file path with path of the directory
		File rename = new File(System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+newName);

		// store the return value of renameTo() method in
		// flag
		boolean flag = file.renameTo(rename);

		// if renameTo() return true then if block is
		// executed
		if (flag == true) {
			System.out.println("File Successfully Rename");
			return true;
		}
		// if renameTo() return false then else block is
		// executed
		else {
			System.out.println("Operation Failed");
			return false;
		}
	}

	/**
	 * This method is used to rename File 
	 * @author hingorani_a
	 * @param oldPath The old path of file
	 * @param newPath The new path of file
	 * @return boolean This returns boolean true after renaming the file
	 */	
	public boolean renameFileViaPath(String oldPath, String newPath)
	{
		try {
			File file = new File(oldPath);
			File rename = new File(newPath);

			boolean flag = file.renameTo(rename);

			if (flag == true) {
				System.out.println("File Successfully Rename");
				return true;
			}
			else {
				System.out.println("Operation Failed");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to rename file - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to rename File 
	 * @author hingorani_a
	 * @param folderPath The path to a folder where all files are placed
	 * @param names Key is the original name and Value is the new name that will be use to rename
	 * @param extension The extension of file being replaced
	 * @return boolean This returns boolean true after renaming files
	 */	
	public boolean renameFilesInTheFolder(String folderPath, HashMap<String, String> names, String extension)
	{
		try {

			File directoryPath = new File(folderPath);

			//List of all files and directories
			File filesList[] = directoryPath.listFiles();
			System.out.println("List of files and directories in the specified directory:");

			for(File file : filesList) {

				for(Map.Entry<String, String> entry : names.entrySet()) {

					String originalName = entry.getKey();
					String newName = entry.getValue();

					if(file.getName().contains(originalName)) {

						System.out.println("File name: "+file.getName());
						System.out.println("File path: "+file.getAbsolutePath());

						String newFileName = newName + extension;
						String oldFilePath = file.getAbsolutePath();
						String newFilePath = folderPath + newFileName;

						if(!renameFileViaPath(oldFilePath, newFilePath))
							return false;
					}
				}
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to rename files - " + e.getMessage());
			return false;
		}
	}

	public static class SIZETYPE{
		public static final String BYTES = "bytes";
		public static final String KILOBYTES = "kilobytes";
		public static final String MEGABYTES = "megabytes";
		public static final String GIGABYTES = "gigabytes";
	}

}
