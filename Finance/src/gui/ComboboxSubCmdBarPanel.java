package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ComboboxSubCmdBarPanel extends JPanel implements ActionListener {

	
	// Combo box for submenu
	ComboBoxSubCmd boxCmd1;
	ComboBoxSubCmd boxCmd2;
	ComboSubCmdInterface cmdInt;
	
	
	//public ComboboxSubCmdBar(String[] strForChoice, int initCmbTxtValue1, int initCmbTxtValue2, Console mainConsole) {
	public ComboboxSubCmdBarPanel(String[] strForChoice, int initCmbTxtValue1, int initCmbTxtValue2, ComboSubCmdInterface cmdInt) {
			
		// Set panel background color
		setBackground(Color.BLACK);
		
		boxCmd1 = new ComboBoxSubCmd(strForChoice, initCmbTxtValue1);
		boxCmd2 = new ComboBoxSubCmd(strForChoice, initCmbTxtValue2);
		
		
		boxCmd1.addActionListener(this);
		boxCmd2.addActionListener(this);
		
		setLayout(new GridLayout(1,2));
		
		add(boxCmd1);
		add(boxCmd2);
		
		this.cmdInt = cmdInt; 
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Categories", TitledBorder.LEADING, TitledBorder.TOP, new Font("Default",Font.PLAIN,15), Color.white));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("combo selected");
		
		/*
		ComboBoxSubCmd comboBox = (ComboBoxSubCmd) event.getSource();
		
        Object selected = comboBox.getSelectedItem();
        mainConsole.processSubCmd(selected.toString());
        */
		cmdInt.processSubCmd(boxCmd1.getSelectedItem().toString(), boxCmd2.getSelectedItem().toString());
		
	}

}
