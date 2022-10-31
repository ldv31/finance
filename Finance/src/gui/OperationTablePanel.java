package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.finance.Console;

import model.CategoriesList;
import model.Operation;

public class OperationTablePanel extends JPanel implements ActionListener{
	
	
	private JTable operationTable;
	private OperationTableModel opTabMod;
	private JButton operationTableButton;
	

	public OperationTablePanel() {
		
		// button definition
		operationTableButton = new JButton("Refresh button");
		
		operationTableButton.addActionListener(this);
		
		// Set panel background color
		setBackground(Color.BLACK);
		
		opTabMod = new OperationTableModel();
		operationTable = new JTable(opTabMod);
		
		operationTable.setRowHeight(25);
		
		operationTable.setDefaultEditor(CategoriesList.class, new CategoriesListEditor());
		
		JScrollPane scrollPane = new JScrollPane(operationTable);
		Dimension dim = new Dimension(1600,800);
		scrollPane.setPreferredSize(dim);
		operationTable.setFillsViewportHeight(true);
		
		
		TableColumnModel columnModel = operationTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(400);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(50);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(150);			
		
		setLayout(new FlowLayout(FlowLayout.LEADING));

		add(scrollPane);
		add(operationTableButton);
	
	}
	
	public void setData(ArrayList<Operation> opBookData, ArrayList<String[]> assoBookData, Console consoleFrame) {
		opTabMod.setData(opBookData, assoBookData, consoleFrame);
	}
	
	public void refresh() {
		opTabMod.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		refresh();
	}
	
}
