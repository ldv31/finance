package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Operation;

public class AssociationDialog extends JDialog {
	
	private JButton okButton;
	private JButton cancelButton;
	private JLabel assoTitle;
	private JTextField assoTxt;

	private Operation op;
	private ArrayList<String[]> assoBookData;
	
	public AssociationDialog (JFrame parent) {
		super(parent, "Texte de l'association", false);
	
		setSize (600,200);
		
		setLocationRelativeTo(parent);
		
		okButton = new JButton("OK");
		cancelButton= new JButton("Cancel");
		assoTitle = new JLabel("Libéllé de l'association : ");
		assoTxt = new JTextField();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
		
		// default values
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
			
		// association texte
		gc.gridx = 0;
		add(assoTitle, gc);
		
		gc.gridx++ ;
		add(assoTxt, gc);
		
		// OK and cancel button
		gc.gridy++;
		gc.gridx = 0;
		add(okButton, gc);
		
		gc.gridx++;
		add(cancelButton, gc);
		
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] addString = {op.getAssociation(),assoTxt.getText(), op.getCategoryType().getTxtType()};
				assoBookData.add(addString);
				
				System.out.println("Get txt : " + assoTxt.getText());
				
				setVisible(false);
				System.out.println("Dialog close");
				
			}
		});
		
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				setVisible(false);
				System.out.println("Dialog close");
			}
		});
	}
	
	
	public void setAssoText (String assoString) {
		assoTxt.setText(assoString);
		System.out.println(assoTxt.getText());
	}
	
	public void setData (Operation op, ArrayList<String[]> assoBookData) {
		this.op = op;
		this.assoBookData = assoBookData;
	}
	
	public JTextField getAssoTxt() {
		return assoTxt;
	}
}
