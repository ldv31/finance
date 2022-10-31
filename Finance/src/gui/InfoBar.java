package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.finance.OperationStatistics;

public class InfoBar extends JPanel {
	
	public InfoBar(OperationStatistics opStat) {
		// Label description (en blanc)
		JLabel infoLabel = new JLabel ("Information : ");
		infoLabel.setForeground (Color.white);
		
		// Affichage des statistiques : période d'analyse, nombre d'opérations sans association, valeur des opérations sans associations 
		JLabel durationLabel = new JLabel (" => Durée d'analyse : " + Integer.toString(opStat.getHistoryDaylength()) 
		+ " jours" + " ( " + String.format("%.1f", opStat.getHistoryMonthlength()) + " mois)    " + " => Opérations sans association : " + opStat.getNumberOfOpWithoutAssociation()
		+ "  => Débit sans association : " + (int)opStat.getSumValueDebit()
		+ "  => Crédit sans association : " + (int)opStat.getSumValueCredit());
		
		
		// Set JLabel background color 
		durationLabel.setBackground(Color.BLACK);
		durationLabel.setForeground (Color.ORANGE);
		durationLabel.setOpaque(true);	
		durationLabel.
			
		setLayout(new GridLayout(1,2));
		
		// Set panel background color
		setBackground(Color.BLACK);
		
		add(infoLabel);
		add(durationLabel);
	}
}
