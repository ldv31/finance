package org.finance;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import gui.DialogMessage;

 public class PropertiesManager {

	// List of properties
	private String associationPath;
	private String categoriesPath;
	private String financePath;
	private String exportfinancePath;
	private String backupPath;
	
	
	
	public PropertiesManager()
	{
		readProperties();
	}
	
	// Read properties from properties files
	private void readProperties () {
		
		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("./finance.properties");

			// load a properties file
			prop.load(input);

			// Retrieve  the association file path - mandatory to continue program execution
			associationPath = prop.getProperty("associationFile.Path");
			if (associationPath != null) {
				LogManager.LOGGER.log(Level.INFO,"Association file Path : " + associationPath);
			}
			else {
				LogManager.LOGGER.log(Level.SEVERE,"Association file path does not exists in properties file, exiting program");
				DialogMessage.displayMessage("Association file path does not exists in properties file, exiting program");
				System.exit(1);
			}
			
			
			// Retrieve  the categories file path - mandatory to continue program execution
			categoriesPath = prop.getProperty("categories.Path");
			if (categoriesPath != null) {
				LogManager.LOGGER.log(Level.INFO,"Categories file Path : " + categoriesPath);
			}
			else {
				LogManager.LOGGER.log(Level.SEVERE,"Categories file path does not exists in properties file, exiting program");
				DialogMessage.displayMessage("Categories file path does not exists in properties file, exiting program");
				System.exit(1);
			}
			
			
			// Retrieve  the operation book file path - mandatory to continue program execution
			financePath = prop.getProperty("financeFile.Path");
			if (financePath != null) {
				LogManager.LOGGER.log(Level.INFO,"finance file Path : " + financePath);
			}
			else {
				LogManager.LOGGER.log(Level.SEVERE,"finance file path not found in properties file, exiting program");
				DialogMessage.displayMessage("finance file path not found in properties file, exiting program");
				System.exit(1);
			}	
			
			
			exportfinancePath = prop.getProperty("exportfinanceFile.Path");
			if (exportfinancePath != null) {
				LogManager.LOGGER.log(Level.INFO,"exportfinance file Path : " + exportfinancePath);
			}
			else {
				LogManager.LOGGER.log(Level.SEVERE,"exportfinance file not found in properties file, exiting program");
				DialogMessage.displayMessage("exportfinance file not found in properties file, exiting program");
				System.exit(1);
			}	
			
			
			backupPath = prop.getProperty("backup.Path");
			if (backupPath != null) {
				LogManager.LOGGER.log(Level.INFO,"backup file Path : " + backupPath);
			}
			else {
				LogManager.LOGGER.log(Level.SEVERE,"backup path not found in properties file, exiting program");
				DialogMessage.displayMessage("backup path ot found in properties file, exiting program");
				System.exit(1);
			}	
			
			
		} catch (final IOException ex) {
			LogManager.LOGGER.log(Level.SEVERE,"finance.properties file not found " );
			ex.printStackTrace();
			LogManager.LOGGER.log(Level.SEVERE,"End of program");
			DialogMessage.displayMessage("Finance.properties file does not exists in : " + "\""+ "application root directory" + "\"" + System.getProperty("line.separator") + "Exiting program");
			System.exit(1);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	
	
	
	public String getAssociationFilePath ()
	{
		return associationPath;
	}
	
	public String getFinanceFilePath ()
	{
		return financePath;
	}
	
	public String getExportFinanceFilePath ()
	{
		return exportfinancePath;
	}
	
	public String getBackupFilePath ()
	{
		return backupPath;
	}

	public String getCategoriesPath() {
		return categoriesPath;
	}
	
	
}
