package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.finance.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.PanelType;



public class DatePickerCmdPanel extends JPanel implements ActionListener {
	
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	ComboSubCmdInterface cmdInt;
	
	public DatePickerCmdPanel(ComboSubCmdInterface cmdInt) {
	
		// Set panel background color
		setBackground(Color.BLACK);

		// to trig the cmd
		this.cmdInt = cmdInt;
		
		
		// required to initiate datepcker
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		model = new UtilDateModel();
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	
		setLayout(new GridLayout(1,1));
			
		add(datePicker);
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), "Section Date", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Default",Font.PLAIN,15), Color.white));
		
		datePicker.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		//
        // Get the source of the component, which is our combo
        // box.
        //
		cmdInt.setCurrentMonthDate((Date) datePicker.getModel().getValue());
		cmdInt.processCmd(PanelType.CURRENT.getTxtType()); 
	}	
}







		
		
			
			
		
		
