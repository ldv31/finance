package gui;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.CategoriesList;

public class CategoriesListRenderer implements TableCellRenderer {
	
	private JComboBox comboCat;

	public CategoriesListRenderer() {
		comboCat = new JComboBox(CategoriesList.values());
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		comboCat.setSelectedItem(value);	
		
		return comboCat;
	}

}
