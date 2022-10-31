package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Operation {
	
	// Dates
	private DateFormat df = new SimpleDateFormat("dd/MM/yy");
	private Date dateOp ;
	private Date dateVal ;
	
	// Libellé
	private String libelle;
	
	// valeurs
	private double credit = 0;
	private double debit = 0;
	
	// type d'opération
	private CategoryType lCategoryType;
	
	// association
	//private String association = "Aucune";
	private String association = CategoriesList.AUCUNE.toString();
	
	// mode association;
	private AssociationMode aMode =  AssociationMode.NONE;
	
	
	// Eléments de l'opération
	public Operation (String dateOp, String dateVal, String libelle, double debit, double credit, String pAssociation, AssociationMode lmode) {
		setDateOp (dateOp);
		setDateVal (dateVal);
		this.libelle = libelle;
		this.debit = debit;
		this.credit = credit;
		this.aMode = lmode;
		this.association = pAssociation;
		
		if (credit != 0)
			lCategoryType  = CategoryType.CREDIT;
		else
			lCategoryType  = CategoryType.DEBIT;	
	}
	
	
	public boolean equalLibDebCred(Operation op1) {
		boolean rValue = false;
		
		if ( (libelle.equals(op1.getLibelle())) && (debit == op1.getDebit()) &&  (credit == op1.getCredit()) ) {
			rValue = true;
		}
		
		return rValue;
	}
	
	private void setDateOp (String dateOp) {
		try {
		   this.dateOp = df.parse(dateOp);
		} catch(ParseException e) {
		   System.out.println("ERROR");
		}
	}
		
	private void setDateVal (String dateVal) {
		try{
			this.dateVal = df.parse(dateVal);
		} catch(ParseException e) {
			System.out.println("ERROR");
		}
	}
	
	public String toString () {
	
		return df.format(dateOp.getTime()) + "\t" + df.format(dateVal.getTime()) 
		+ "\t" + libelle +"\t\t"+ debit + "\t" + credit + "\t" + association + "\t" + aMode.getTxtMode();
		
	}
	
	public String [] toStringArray() {
	
		String [] rStringArray = {df.format(dateOp.getTime()), df.format(dateVal.getTime()), libelle, Double.toString(debit), Double.toString(credit), association, aMode.getTxtMode()};
		return rStringArray;
		
	}

	
	public double getOpValue () {
		if (credit != 0)
			return credit;
		else
			return debit;
					
	}
	
	public CategoryType getCategoryType () {
		return lCategoryType;			
	}
	

	public DateFormat getDf() {
		return df;
	}


	public void setDf(DateFormat df) {
		this.df = df;
	}


	public Date getDateOp() {
		return dateOp;
	}


	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}


	public Date getDateVal() {
		return dateVal;
	}


	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public double getCredit() {
		return credit;
	}


	public void setCredit(double credit) {
		this.credit = credit;
	}


	public double getDebit() {
		return debit;
	}


	public void setDebit(double debit) {
		this.debit = debit;
	}


	public String getAssociation() {
		return association;
	}


	public void setAssociation(String association) {
		this.association = association;
	}


	public AssociationMode getaMode() {
		return aMode;
	}


	public void setaMode(AssociationMode aMode) {
		this.aMode = aMode;
	}
	
	public Date getDateOpMonthandYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOp); // your date (java.util.Date)
		cal.add(Calendar.DAY_OF_MONTH, 0); // You can -/+ x months here to go back in history or move forward.
		return cal.getTime(); // New date	
	}
	
	public Date getDateValMonthandYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateVal); // your date (java.util.Date)
		cal.add(Calendar.DAY_OF_MONTH, 0); // You can -/+ x months here to go back in history or move forward.
		return cal.getTime(); // New date	
	}
	
}
