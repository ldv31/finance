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
		
		// Affichage des statistiques : p�riode d'analyse, nombre d'op�rations sans association, valeur des op�rations sans associations 
		JLabel durationLabel = new JLabel (" => Dur�e d'analyse : " + Integer.toString(opStat.getHistoryDaylength()) 
		+ " jours" + " ( " + String.format("%.1f", opStat.getHistoryMonthlength()) + " mois)    " + " => Op�rations sans association : " + opStat.getNumberOfOpWithoutAssociation()
		+ "  => D�bit sans association : " + (int)opStat.getSumValueDebit()
		+ "  => Cr�dit sans association : " + (int)opStat.getSumValueCredit());
		
		
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
