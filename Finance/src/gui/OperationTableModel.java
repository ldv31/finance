package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.finance.Console;

import model.AssociationMode;
import model.CategoriesList;
import model.Operation;

public class OperationTableModel extends AbstractTableModel {
	
	private ArrayList<Operation> opBookData;
	private ArrayList<String[]> assoBookData;
	private String[] columnNames = {"Date OP", "Date VAL", "Libellé", "Debit", "Credit", "Association", "Mode Association"};
	private Console consoleFrame;
	

	public OperationTableModel() {	
	}
	
	 
	
	
	@Override
	public boolean isCellEditable(int row, int col) {
			 	
		switch (col) {		
			case 3:
				return true;
			case 5:
				return true;
			default:
				return false;
		}
		
	}
	

	@Override
	public void setValueAt(Object aValue, int row, int col) {
		
		if (opBookData == null) return;
		
		Operation op = opBookData.get(row);
			
		switch (col) {		
		case 3:
			op.setDebit(Double.parseDouble((String)aValue));
			break;
		case 5:
			
			// Deplacer la section ci-dessou en début de  case avec un test sur amazon + automatique 
			
			if (!(op.getAssociation().equals(CategoriesList.AMAZON.getTxtType())) || (op.getaMode() != AssociationMode.AUTOMATIC)) {
			
				// mise à jour du fichier des association 
				// ajouter le libellé ddans le texte de la boite de dialog
				consoleFrame.getAssoDiag().setAssoText(op.getLibelle());
				consoleFrame.getAssoDiag().setData(op, assoBookData);				
							
				consoleFrame.getAssoDiag().setVisible(true);
			}
			
			// dans tous les cas mettre à jour  l'association dans le datbook
			op.setAssociation(aValue.toString());
			
			// cas des operatrion sans associations
			// mise à jour  du mode d'association		
			if (!aValue.toString().equals(CategoriesList.AUCUNE.toString())) {
				op.setaMode(AssociationMode.MANUAL);
			}
			else {
				op.setaMode(AssociationMode.NONE);
			}		
			break;
		default:
			return;
		}
		
	}




	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}



	public void setData(ArrayList<Operation> opBookData, ArrayList<String[]> assoBookData, Console consoleFrame) {
		this.opBookData = opBookData;
		this.assoBookData = assoBookData;
		this.consoleFrame = consoleFrame;
	}
	
	
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return opBookData.size();
		//return 50;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Operation op = opBookData.get(row); 
			
		switch (col) {
		case 0:
			return op.getDateOp();
		
		case 1:
			return op.getDateVal();
			
		case 2:
			return op.getLibelle();
			
		case 3:
			return op.getDebit();
			
		case 4:
			return op.getCredit();
			
		case 5:
			return op.getAssociation();
			
		case 6:
			return op.getaMode();
		default:	
			return null;
		}		
	}




	@Override
	public Class<?> getColumnClass(int col) {
		
		switch (col) {
		case 0:
			return String.class;
		
		case 1:
			return String.class;
			
		case 2:
			return String.class;
			
		case 3:
			return String.class;
			
		case 4:
			return String.class;
			
		case 5:
			return CategoriesList.class;
			
		case 6:
			return String.class;
		default:	
			return null;
		}		
		
	}

}
