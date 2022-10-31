package model;

public enum CategoriesList {
	
	
	AUCUNE ("Aucune"),
	ALIMENTATION_HYGIENE ("Alimentation et Hygi�ne"),
	SANTE ("Sant�"),
	COIFFEUR ("Coiffeur"),
	EDF ("EDF"),
	ESSENCE ("Essence"),
	EPARGNE ("Epargne"),
	VETEMENTS ("V�tements"),
	LOISIR ("Loisir"),
	TRANSPORT ("Transport"),
	AMAZON ("Amazon"),
	INTERNET_TELEPHONE ("Internet / t�l�phonie"),
	BRICO_JARDIN ("Bricolage et Jardin"),
	EQUIP_MAISON ("Equipement maison"),
	RETRAIT_DAB ("Retrait DAB"),
	CADEAU_DEBIT ("Cadeau d�bit"),
	DEPL_PRO ("D�placement pro"),
	ECOLE_DEBIT ("Ecole Debit"),
	ASSURANCE_DEBIT ("Assurance"),
	IMPOTS_DEBIT ("Impots Debit"),
	CADEAU_CREDIT ("Cadeau cr�dit"),
	TRANSF_BANK ("Transfert entre banques"),
	FORMATION ("Formation"),
	INVEST_AGRI ("Investissement EARL"),
	SALAIRE ("Salaire"),
	CAF ("Caf"),
	CPAM_MUTUELLE ("CPAM + Mutuelle"),
	REMBOURSEMENT ("Remboursement"),
	AMAZON_CREDIT ("Amazon Cr�dit");
	
	private String txtType ="";
	
	CategoriesList (String txtType) {
		this.txtType = txtType;
	}
	
	public String getTxtType () {
		return txtType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return txtType;
	}
}
