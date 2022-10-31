package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import model.CategoriesList;

public class CategoriesListEditor extends AbstractCellEditor implements TableCellEditor{

	private JComboBox comboCat;
	
	public CategoriesListEditor() {
		comboCat = new JComboBox(CategoriesList.values());
		
	}
	
	@Override
	public Object getCellEditorValue() {
		return comboCat.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object value, boolean arg2, int arg3, int arg4) {
		
		
		comboCat.setSelectedItem(value);
		
		comboCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();	
			}
		});
				
		return comboCat;
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}

		
	
}
