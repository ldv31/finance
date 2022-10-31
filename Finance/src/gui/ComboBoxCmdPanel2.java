package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ComboBoxCmdPanel2 extends JPanel implements ActionListener  {
	
	JComboBox comboBox;
	ComboSubCmdInterface cmdInt;
	
	public ComboBoxCmdPanel2(String[] strForChoice, int initCmbTxtValue, ComboSubCmdInterface cmdInt) {
		
		// Set panel background color
		setBackground(Color.BLACK);
		
		comboBox = new JComboBox(strForChoice);						
		comboBox.setSelectedIndex(initCmbTxtValue);		
		comboBox.addActionListener(this);	
		
		setLayout(new GridLayout(1,1));
		
		this.cmdInt = cmdInt;
		
		add(comboBox);
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Type de Rapport", TitledBorder.LEADING, TitledBorder.TOP, new Font("Default",Font.PLAIN,15), Color.white));
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//
        // Get the source of the component, which is our combo
        // box.
        //
        Object selected = comboBox.getSelectedItem();
        cmdInt.processCmd(selected.toString());   
	}	
}
