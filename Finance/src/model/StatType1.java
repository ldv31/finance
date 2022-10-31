package model;

import java.util.ArrayList;
import java.util.Date;


// contain all information and data for one category, including the history of all operation for this category
public class StatType1 {

	private String opCategory = "";
	private double opValue = 0;
	private double opMoyenneMensuelle = 0;
	private CategoryType cType;
	private boolean constraint;
	final double nbMoyDayPerMonth = 365 / 12;
	private ArrayList<StatDataHistory> dataHistory = new ArrayList<StatDataHistory>();
		
	public StatType1 (String opCategory, double opValue, CategoryType cType, boolean constraint) {
		this.opCategory = opCategory;
		this.opValue = opValue;
		this.cType = cType;
		this.constraint = constraint;
	}
	
	public void incOpValue (double inc) {
		opValue += inc;
	}

	public String getOpCategory() {
		return opCategory;
	}

	public void setOpCategory(String opCategory) {
		this.opCategory = opCategory;
	}

	public double getOpValue() {
		return opValue;
	}

	public void setOpValue(double opValue) {
		this.opValue = opValue;
	}

	public CategoryType getcType() {
		return cType;
	}

	public void setcType(CategoryType cType) {
		this.cType = cType;
	}

	public double getOpMoyenneMensuelle() {
		return opMoyenneMensuelle;
	}

	
	public boolean isConstraint() {
		return constraint;
	}

	public void setOpMoyenneMensuelle(Date oldest, Date latest) {
		
		long diff = oldest.getTime() - latest.getTime();
		opMoyenneMensuelle = opValue / ((diff / (1000*60*60*24))/nbMoyDayPerMonth);
	}

	/**
	 * @return the dataHistory
	 */
	public ArrayList<StatDataHistory> getDataHistory() {
		return dataHistory;
	}

	/**
	 * @param dataHistory the dataHistory to set
	 */
	public void setDataHistory(ArrayList<StatDataHistory> dataHistory) {
		this.dataHistory = dataHistory;
	}

	/**
	 * @return the nbMoyDayPerMonth
	 */
	public double getNbMoyDayPerMonth() {
		return nbMoyDayPerMonth;
	}

	/**
	 * @param opMoyenneMensuelle the opMoyenneMensuelle to set
	 */
	public void setOpMoyenneMensuelle(double opMoyenneMensuelle) {
		this.opMoyenneMensuelle = opMoyenneMensuelle;
	}
	
	
}
