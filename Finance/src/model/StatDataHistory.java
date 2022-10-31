package model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class StatDataHistory {

	private Date monthAndYear;
	private Double value = 0.0;
	
	public StatDataHistory (Date monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	
	public void incDataValue (double incValue) {
		value += incValue;
	}

	public Date getMonthAndYear() {
		return monthAndYear;
	}
	
	public String getMonthAndYearShort() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("MMM yyyy");
	    return formater.format(monthAndYear);	
	}

	public void setMonthAndYear(Date monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
	public boolean CompareYearAndMonth (StatDataHistory compareDate) {
		if ((this.monthAndYear.getYear() == compareDate.monthAndYear.getYear()) &&
				(this.monthAndYear.getMonth() == compareDate.monthAndYear.getMonth()))
			return true;
		else
			return false;
	}
	
	
	/*
	public boolean CompareYearAndMonth (StatDataHistory compareDate) {
		if (this.monthAndYear.equals(compareDate.getMonthAndYear()))
			return true;
		else
			return false;
	}
	*/
	
}
