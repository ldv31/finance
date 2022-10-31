package gui;

import java.util.Date;

public interface ComboSubCmdInterface {
	public void processSubCmd(String str1, String str2);
	public void processCmd(String str1);
	public void setCurrentMonthDate(Date dateCurrent);
}
