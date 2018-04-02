package Team1;

import javax.swing.table.AbstractTableModel;


public class CustomTable extends AbstractTableModel {
	Object[][] data;
	String[]  c_name;
	public CustomTable(Object[][] data,String[]  c_name) {
		// TODO Auto-generated constructor stub
	this.c_name=c_name;
	this.data=data;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return c_name.length;
	}
	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		return data[r][c];
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return c_name[column];
	}
	
	
}
