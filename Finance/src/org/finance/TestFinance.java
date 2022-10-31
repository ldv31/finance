package org.finance;

import java.util.logging.Level;

import gui.SplashScreen;
import model.OperationsBook;

// Main Class for program execution
public class TestFinance {
		
	public static void main(String[] args) {
		    
		// set parameters for general logging
		LogManager.setupLogs();

	    // Log: "start of the program"
		LogManager.LOGGER.log(Level.INFO,"Debut du programme");
	    
	    // Afficher la class path
		LogManager.LOGGER.log(Level.INFO,"ClassPath : " + System.getProperty("java.classpath"));
	    
	    
	    //affichage splash screen
	    @SuppressWarnings("unused")
		SplashScreen splashFrame = new SplashScreen();
	    
	    //Lecture du fichier de configuration
	    PropertiesManager proManager = new PropertiesManager ();
	       
	    // Lecture du fichier CSV et son affichage
	    OperationsBook opBook = new OperationsBook (proManager.getFinanceFilePath(), proManager.getAssociationFilePath(), proManager.getBackupFilePath(), proManager.getCategoriesPath());
	
	    // Calcul des statistiques
	    OperationStatistics opStat = new OperationStatistics(opBook);    
	    
	    // affichage console
	    @SuppressWarnings("unused")
		Console console = new Console(opStat);
	}
}
