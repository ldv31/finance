package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.finance.FileMgtInterface;
import org.finance.OpStatInterface;

public class FileMgtPanel extends JPanel implements ActionListener{

	JButton fileSaveButton;
	JButton fileLoadNewButton;
	FileMgtInterface cmdInt;
	OpStatInterface opStatInt;
	
	public FileMgtPanel(FileMgtInterface cmdInt, OpStatInterface opStatInt) {
		
		this.cmdInt = cmdInt;
		this.opStatInt = opStatInt;
		
		// Set panel background color
		setBackground(Color.BLACK);
		
		// set layout for multiple buttons
		setLayout(new GridLayout(1,2));
		
		// Save File
		fileSaveButton = new JButton("Sauvegarde");							
		fileSaveButton.addActionListener(this);	
		
		add(fileSaveButton);
		
		// load file for new data
		fileLoadNewButton = new JButton("Chargement nouvelles données");							
		fileLoadNewButton.addActionListener(this);	
				
		add(fileLoadNewButton);
		
			
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "File", TitledBorder.LEADING, TitledBorder.TOP, new Font("Default",Font.PLAIN,15), Color.white));
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//
        // Get the source of the component, which is our combo
        // box.
        //
		if (event.getSource() == fileSaveButton) {
			
			// update stats with new data
			opStatInt.updateStats();
			
			if(cmdInt.saveFinance() == true) {	
				DialogMessage.displayMessage("Finance file Saved and data updated");
			}

			if(cmdInt.saveAssociation() == true) {	
				DialogMessage.displayMessage("Association file Saved and data updated");
			}
			
		}
		else if (event.getSource() == fileLoadNewButton) {
			//integration dans le data book des nouvelles données
			cmdInt.loadNewFinanceData();
			
			// update stats with new data
			opStatInt.updateStats();
		}			
	}	
	
}
