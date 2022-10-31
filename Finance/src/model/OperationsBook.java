package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;
import org.finance.CsvFileHelper;
import org.finance.FileMgtInterface;
import org.finance.LogManager;

import gui.DialogMessage;

public class OperationsBook implements FileMgtInterface{
	
	// store the lsit of operation extracted from CVS file
	ArrayList<String[]> aString;
	// store the lsit of association extracted from CVS file
	ArrayList<String[]> bString;
	// store the lsit of categories extracted from CVS file
	ArrayList<String[]> categoriesString;
	ArrayList<Operation> opBookData;
	ArrayList<Operation> opBookDataWithoutAssociation;
	ArrayList<OperationCategory> categoriesList = new ArrayList<OperationCategory>();
	int countWithoutAssociation = 0;
	double sumValueCredit = 0;
	double sumValueDebit = 0;
	
	// used to backup and update fiance.csv file
	File source;
 	File dest;
	
 	// File location for save / backup / load 
 	String csvFileName;
 	String associationFileName;
 	String backupPath;
 	
 	// Dialog box to load new fiance data
 	private JFileChooser fileChooser;

	public OperationsBook (String csvFileName, String associationFileName, String backupPath, String categoriesFileName) {
		
		LogManager.LOGGER.log(Level.INFO,"Lecture du fichier des opérations");
		
		// create save/load file objects
		fileChooser = new JFileChooser();
		
		// Paramètres
		this.csvFileName = csvFileName;
		this.associationFileName = associationFileName;
		this.backupPath = backupPath;
		
		// livre de compte (base de donnée interne des opérations)
		opBookData = new ArrayList<Operation>();
		
		// 0uverture du fichier CSV (finance) des opérations à importer
		//final File file = CsvFileHelper.getResource(csvFileName);
		final File file = CsvFileHelper.getResourceAbs(csvFileName);
		if (!file.exists()) {
			LogManager.LOGGER.log(Level.SEVERE,"File does not exists : " + csvFileName);
			LogManager.LOGGER.log(Level.SEVERE,"Please provide the missing file");
			LogManager.LOGGER.log(Level.SEVERE,"End of program");
			DialogMessage.displayMessage("Finance file does not exists in : " + "\""+ csvFileName + "\"" + System.getProperty("line.separator") + "Exiting program");
			System.exit(1);
		}
		
		// extraction des données du fichier CSV (finance)
		aString =  CsvFileHelper.extractRawOperationData (file);
		
		// transformation des données CSV en livre de compte
	    fillOperationBookData (aString, opBookData);
	    
	    //ouverture du fichier des associations
	    //final File associationFile = CsvFileHelper.getResource(associationFileName);
	    final File associationFile = CsvFileHelper.getResourceAbs(associationFileName);
	    if (!associationFile.exists()) {
	    	LogManager.LOGGER.log(Level.SEVERE,"File does not exists : " + associationFileName);
	    	LogManager.LOGGER.log(Level.SEVERE,"Please provide the missing file");
	    	LogManager.LOGGER.log(Level.SEVERE,"End of program");
	    	DialogMessage.displayMessage("Association file does not exists in : " + "\""+ associationFileName + "\"" + System.getProperty("line.separator") + "Exiting program");
			System.exit(1);
		}
	    
		    
	    
	    // extraction des données du fichier des association
	 	bString =  CsvFileHelper.extractRawOperationData(associationFile);
	 	CsvFileHelper.printRawOperationData(bString);
	 	
	 	 //ouverture du fichier des categories
	    final File categoriesFile = CsvFileHelper.getResourceAbs(categoriesFileName);
	    if (!categoriesFile.exists()) {
	    	LogManager.LOGGER.log(Level.SEVERE,"File does not exists : " + categoriesFileName);
	    	LogManager.LOGGER.log(Level.SEVERE,"Please provide the missing file");
	    	LogManager.LOGGER.log(Level.SEVERE,"End of program");
	    	DialogMessage.displayMessage("Categories file does not exists in : " + "\""+ categoriesFileName + "\"" + System.getProperty("line.separator") + "Exiting program");
			System.exit(1);
		}
	 	
	    // extraction des données du fichier des association
	 	 categoriesString =  CsvFileHelper.extractRawOperationData(categoriesFile);
	 	 CsvFileHelper.printRawOperationData(bString);
	    
	    
	 	// Mise a jour des association dans le livre de compte
	 	fillAssociationBookData();
	 	
	 	// display operations without associations and set the corresponding values
	 	opBookDataWithoutAssociation = new ArrayList<Operation>();
	 	opWithoutAssociation();
	 	
	 	// generation de la liste des catégories
	 	generateCategoriesList();
	 		 	
	 	
	 	// Sauvegarde du fichier des operations après l'ajout des associations
	 	saveFinance();
	 	
	 		 	
	 	// backup du fichier d'entrée finance.csv et du fichier des associations
	 	backupFiles();
	 		 	
	 	
	    // affichage du livre de compte.
	 	LogManager.LOGGER.log(Level.INFO,toString());   	        
	}
	
	
	// Sauvegarde le fichier des associations
	public boolean saveAssociation() {
		
		ArrayList<String[]> associationString = new ArrayList<String[]>();
		
		// add the header for association file
 	 	//String [] lAssoHeader = {"Rubrique","Libellé","Opération"};
 	 	//associationString.add(lAssoHeader);
		
		// add the associations in association file
		for (String[] assoString : bString) {
			associationString.add(assoString);		
		}
		
		// Ecriture du fichier des associations		
		return (CsvFileHelper.writeToCsv(associationFileName, associationString));	
			
	}
	
	
	
	// sauvegarde l'operation book dans le réperttoire courant (par exemple suite à la modification d'une association)
	public boolean saveFinance() {
		
		
		// create the table of string for CSV writer : finance file and association
	 	// *****************************************
	 	ArrayList<String[]> financeString = new ArrayList<String[]>(); 	
	 	
	 	
	 	// add the header for finance file
	 	String [] lheader = {"Date opération","Date valeur","libellé","Débit","Crédit","Association","Mode Association"};
	 	financeString.add(lheader);
	 	 	
	 	
	 	// add the operations infinance file
	 	for (Operation opLocal : opBookData) {
 			financeString.add(opLocal.toStringArray());		
	 	}
	 		 		 	
	 	// Ecriture du fichier d'export
	 	return (CsvFileHelper.writeToCsv(csvFileName, financeString));  	
	}
	
	
	
	public void loadNewFinanceData () {
		
		File loadFile;
		ArrayList<String[]> newDataOpString;
		ArrayList<Operation> opBookNewData = new ArrayList<Operation>();
		
		// retrieve the file selected by the user
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			loadFile = fileChooser.getSelectedFile();			
		
			if (!loadFile.exists()) {
				LogManager.LOGGER.log(Level.SEVERE,"File does not exists : " + loadFile.getAbsolutePath());
				LogManager.LOGGER.log(Level.SEVERE,"Please provide the missing file");
				LogManager.LOGGER.log(Level.SEVERE,"End of program");
				DialogMessage.displayMessage("File does not exists in : " + "\""+ loadFile.getAbsolutePath() + "\"" + System.getProperty("line.separator") + "Exiting program");
				System.exit(1);
			}
			
			// extraction des données du fichier CSV (finance)
			newDataOpString =  CsvFileHelper.extractRawOperationData (loadFile);
			
			// transformation des données CSV en livre de compte
		    fillOperationBookData (newDataOpString, opBookNewData);
		    
		    System.out.println("size of new data array :" + opBookNewData.size());
		
		
			//index of insertion
			int insertIndex = 0;
			
			// get the date (in millisecond) of the last operation in the main operation book 
			long OPBookTimeSec = opBookData.get(0).getDateOp().getTime();
			
			// get the last operation from the main operation book
			Operation opLastMain = opBookData.get(0);
			
			// last main operation reached => stop adding operation from new operation to the main operation
			boolean lastMainOperatioReached = false;
			
			// add new data operation only if the date if not already in the main operation book
			// Date of new operation is >= to the latest operation in the main databook
			// if Date of new operation = to the latest operation in the main databook then compare the opeeration and if iddrent add them			
			for (Operation newOpCandidate : opBookNewData) {
				long newTimeSec = newOpCandidate.getDateOp().getTime();
			        
				if (newTimeSec >= OPBookTimeSec) {
					if (newTimeSec > OPBookTimeSec) {
						opBookData.add(insertIndex, newOpCandidate);
						System.out.println("Adding new op date > , index :" + insertIndex);
						insertIndex ++;
					} else if (!newOpCandidate.equalLibDebCred(opLastMain) && (lastMainOperatioReached == false)) {
						opBookData.add(insertIndex, newOpCandidate);
						System.out.println("Adding new op date = , index :" + insertIndex);
						insertIndex ++;				
					} else {
						lastMainOperatioReached = true;
					}
				}				
			}

			// print to user the number of operation added
			DialogMessage.displayMessage("Number of operation added:  " + insertIndex);
			
			// Mise a jour des association dans le livre de compte
			fillAssociationBookData();
			System.out.println("fillAssociationBookData updated");
			
			// add here a request to update the stats
		}
	}
	
	
	
	// backup des fichiers finance et association pour la protection des données
	public void backupFiles() {
		
		// backup du fichier d'entrée finance.csv
	 	LocalDate todaysDate = LocalDate.now();
	 	source = new File(csvFileName);
	 	dest = new File(backupPath + "finance.csv" + "." + todaysDate);
	 	
	 	try {
	 	    FileUtils.copyFile(source, dest);
	 	} catch (IOException e) {
	 		LogManager.LOGGER.log(Level.SEVERE,"Finance.csv backup failed");
	 	    e.printStackTrace();
	 	}
	 	
	 	
	 	// Sauvegarde du fichier d'association 	
	 	source = new File(associationFileName);
	 	dest = new File(backupPath + "association.csv" + "." + todaysDate);
	 	
	 	try {
	 	    FileUtils.copyFile(source, dest);
	 	} catch (IOException e) {
	 		LogManager.LOGGER.log(Level.SEVERE,"Association.csv backup failed");
	 	    e.printStackTrace();
	 	}
	}
	
	
	
	public void opWithoutAssociation() {
		LogManager.LOGGER.log(Level.INFO,"********************************************************");
		LogManager.LOGGER.log(Level.INFO,"******** Liste des opérations sans associations ********");
		LogManager.LOGGER.log(Level.INFO,"********************************************************");
	 	for (Operation op : opBookData) {
	 		if (op.getaMode() == AssociationMode.NONE) {
	 			countWithoutAssociation++;
	 			sumValueCredit+= op.getCredit();
	 			sumValueDebit+= op.getDebit();
	 			LogManager.LOGGER.log(Level.INFO,op.toString());
	 			opBookDataWithoutAssociation.add(op);
	 		}
	 	}   
	 	LogManager.LOGGER.log(Level.INFO,"********************************************************\n");
	 	LogManager.LOGGER.log(Level.INFO,"Total crédit sans association : " + (int)sumValueCredit);
	 	LogManager.LOGGER.log(Level.INFO,"Total débit sans association : " + (int)sumValueDebit);	     
	}
	
	
	public ArrayList<Operation> getOpBookDataWithoutAssociation() {
		return opBookDataWithoutAssociation;
	}

	
	// inputString est le tableau des opérations extraites du fichier "finance.csv" et inputBookData est le livre de compte dans lequel les opération seront formalisées
	private void fillOperationBookData(ArrayList<String[]> inputString, ArrayList<Operation> inputBookData) {
		boolean csvHeader = true;
		double lCredit;
		AssociationMode localMode = AssociationMode.NONE;
		//String lassociation = "Aucune";
		String lassociation = CategoriesList.AUCUNE.toString();
		 
		
		for (String[] lString : inputString) {
			// Ignorer le header présent dans le fichier CSV 
			if (csvHeader == false) {
				
				// Le fichier CSV contient les élémnent suivants:
				// 1 - Date opération
				// 2 - Date valeure
				// 3 - Libellé
				// 4 - Débit
				// 5 - Crédit
				// 6 - association (optionnel)
				// 7 - mode d'association (optionnel)
				
				
				// Renseigner le Mode d'association si disponible
				if (lString.length >= 7)
					if (AssociationMode.fromString(lString[6]) != null)
						localMode = AssociationMode.fromString(lString[6]);
					else
						localMode = AssociationMode.NONE;
				else
					localMode = AssociationMode.NONE;
				
				
				// Renseigner l"association si disponible
				if (lString.length >= 6) {
					lassociation = lString[5];
				}
				else {
					//lassociation = "Aucune";
					lassociation = CategoriesList.AUCUNE.toString();
				}
						
				// Cas ou le credit / debit est vide dans le fichier CSV
				if (lString.length < 5) 
					lCredit = 0;	
				else
					lCredit = lString[4].isEmpty() ? 0 : Double.parseDouble(lString[4].replaceAll(",", "."));
				
				Operation op = new Operation (
						lString[0], 
						lString[1], 
						lString[2], 
						lString[3].isEmpty() ? 0 : Double.parseDouble(lString[3].replaceAll(",", ".")),
						lCredit,
						lassociation,
						localMode);
				inputBookData.add (op);
			}
			else
				csvHeader = false;
		}
	}	
	
	
	private void fillAssociationBookData() {
		boolean associationNotFound = true;
		int i = 1;
		String[] lbString;
		
		for (Operation opLocal : opBookData) {
			// check if an association already exist
			//if (opLocal.getAssociation().equals("Aucune")) {
			if (opLocal.getAssociation().equals(CategoriesList.AUCUNE.toString())) {
				while ((associationNotFound == true) && (i < bString.size())) {
					lbString = bString.get(i);
					// check if: 
					// 	- the libelle of the operation match the libelle of a category 
					//  - the libelle of the category is not empty
					//  - the operation type is the same
 					if (opLocal.getLibelle().contains(lbString[1]) && (!lbString[1].equals("")) && (opLocal.getCategoryType().getTxtType().equals(lbString[2]))) {
						associationNotFound = false;
						opLocal.setAssociation(lbString[0]);
						opLocal.setaMode(AssociationMode.AUTOMATIC);
					}   
					i++;
				}
				associationNotFound = true;
				i = 1;
			}
		}	
	}	

	
		
	private void generateCategoriesList () {	
		boolean csvHeader = true;
		boolean categoryIsNew = true;
		CategoryType opType;
		boolean constraint;
		
		for (String[] lString: categoriesString) {
			
			// Ignorer le header présent dans le fichier CSV 
			if (csvHeader == false) {
			
				for (OperationCategory opCat : categoriesList) {
					if (lString[0].equals(opCat.getName()))
						categoryIsNew = false;
				}
					
				if (categoryIsNew) {
					opType = lString[1].equals(CategoryType.CREDIT.getTxtType()) ? 
							CategoryType.CREDIT : CategoryType.DEBIT;								
					constraint = lString[2].equals("Oui") ? true : false;
								
					OperationCategory opNew = new OperationCategory(lString [0], opType, constraint);
					categoriesList.add(opNew);
				}
				categoryIsNew = true;
			}
			else
				csvHeader = false;
		}
		
		// affichage temporaire
		for (OperationCategory opCat : categoriesList) {
			LogManager.LOGGER.log(Level.INFO,"Catégorie : " + opCat.getName());
		}
	}
	
	
	public String toString () {
	
		LogManager.LOGGER.log(Level.INFO,"***********************************************************");
		LogManager.LOGGER.log(Level.INFO,"*************** Table des opérations (Data) ***************");
		LogManager.LOGGER.log(Level.INFO,"***********************************************************");
		
		String rString = "";
		for (Operation op : opBookData)
			rString += op.toString() + "\n";
		return rString;
	}


	public Date getOldestDate() {
		return opBookData.get(opBookData.size()-1).getDateOp();
	}
	
	public Date getLatestDate() {
		return opBookData.get(0).getDateOp();
	}
	
	public ArrayList<String[]> getaString() {
		return aString;
	}

	public int getCountWithoutAssociation() {
		return countWithoutAssociation;
	}
	
	public double getSumValueCredit() {
		return sumValueCredit;
	}
	
	public double getSumValueDebit() {
		return sumValueDebit;
	}

	public void setaString(ArrayList<String[]> aString) {
		this.aString = aString;
	}


	public ArrayList<String[]> getbString() {
		return bString;
	}


	public void setbString(ArrayList<String[]> bString) {
		this.bString = bString;
	}


	public ArrayList<Operation> getOpBookData() {
		return opBookData;
	}


	public void setOpBookData(ArrayList<Operation> opBookData) {
		this.opBookData = opBookData;
	}


	public ArrayList<OperationCategory> getCategoriesList() {
		return categoriesList;
	}


	public void setCategoriesList(ArrayList<OperationCategory> categoriesList) {
		this.categoriesList = categoriesList;
	}

}
