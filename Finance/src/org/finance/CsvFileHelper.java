package org.finance;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;


import com.opencsv.CSVWriter;
import gui.DialogMessage;

import java.io.FileWriter;


public class CsvFileHelper {

	public final static char SEPARATOR = ';';
	
	public static String getResourcePath(String fileName) {
	       final File f = new File("");
	       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
	       return dossierPath;
	   }

	public static File getResource(String fileName) {
	       final String completeFileName = getResourcePath(fileName);
	       LogManager.LOGGER.log(Level.SEVERE,"Ouverture du fichier : " + completeFileName);
	       File file = new File(completeFileName);
	       return file;
	   }
	
	public static File getResourceAbs(String fileName) {
	       LogManager.LOGGER.log(Level.SEVERE,"Ouverture du fichier : " + fileName);
	       File file = new File(fileName);
	       return file;
	   }
	   
	  
	public static boolean writeToCsv(String fileName, ArrayList<String[]> content){
        boolean rValue = true;
		
		String csv = fileName;
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(csv), ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(content);

            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
            DialogMessage.displayMessage("File Save failed : \n" + e.getMessage());
            rValue = false;
        }
        
        return rValue;
	}
	
	public static ArrayList<String> readFile(File file) {

	        ArrayList<String> result = new ArrayList<String>();

	        FileReader fr;
			try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line;
				boolean loopOK = true;
								
				do {
					try {
						line = br.readLine();
					
						if (line != null) {
							result.add(line);
						}
						else
							loopOK = false;
					
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						loopOK = false;
					}
				} while (loopOK == true);
				

		        try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println ("Le fichier demandé n'existe pas");
				System.exit(1);
			}
		
			return result;
	    }
	   
	
	public static  ArrayList<String[]> extractRawOperationData (File file) {
		ArrayList<String> lines = CsvFileHelper.readFile(file);

		ArrayList<String[] > data = new ArrayList<String[] >(lines.size());
        String sep = new Character(SEPARATOR).toString();
        for (String line : lines) {
            String[] oneData = line.split(sep);
            data.add(oneData);             
        }
        
        return data;
    }
	
	public static void printRawOperationData (ArrayList<String[]> array) {
		String forPrint;
		
		System.out.println("\n***********************************************************");
		System.out.println("******************** CVS File Content *********************");
		System.out.println("***********************************************************\n");
		
		for (String [] arrayStringElement: array) {
			forPrint = "";
			for (String string: arrayStringElement) {
				forPrint += " | " + string;
			}
			forPrint += " | ";
			System.out.println(forPrint);
		}
	}
	
}
