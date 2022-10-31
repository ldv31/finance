package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JComboBox;

public class ComboBoxSubCmd extends JComboBox  {
	
	public ComboBoxSubCmd(String[] strForChoice, int initCmbTxtValue) {
		super(strForChoice);
		
		setSelectedIndex(initCmbTxtValue);
		
							
	}	
}
