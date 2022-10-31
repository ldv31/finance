package model;

public enum CategoryType {

	DEBIT ("D�bit"),
	CREDIT ("Cr�dit"),
	TOUS ("toute op�ration");
	
	private String txtType ="";
	
	CategoryType (String txtType) {
		this.txtType = txtType;
	}
	
	public String getTxtType () {
		return txtType;
	}
}
