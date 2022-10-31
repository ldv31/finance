package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SplashScreen extends JFrame{

	// variable for windows size
	private int chartPanelwidth = 100;
	private int chartPanelHeight = 100;
	
	public SplashScreen() {
		
		splashDisplay();
		
	}
	
	
	private void splashDisplay() {
		// set panel default size;
		
		
		// taille de la frame
		setSize(chartPanelwidth, chartPanelHeight);
		
		
		// ajout de l'image
		ImageIcon icon = new ImageIcon("LittleWitch.png");
		JLabel label = new JLabel(icon);
		add(label);
		
		// suppression de la barre de titre
		setUndecorated(true);
		
		// centrage
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (int) ((screenSize.getWidth()/2 + getWidth()) / 2 );
	    int y = (int) ((screenSize.getHeight()/2 - getHeight()) / 2);
	    setLocation(x, y);
		
		// Display window
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		// Durée de l'affichage
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		setVisible(false);
		
	}	
}
