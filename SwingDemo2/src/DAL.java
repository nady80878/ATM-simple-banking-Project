import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;


public class DAL {
	
	public static DefaultTableModel getData(ResultSet res){
		DefaultTableModel model = null;
		try {
			ResultSetMetaData rsmd = res.getMetaData();
		int columnsNum = rsmd.getColumnCount();
		Vector<String> columnsVec= new Vector<String>();
		for(int i = 1;i<=columnsNum;++i){		
				columnsVec.addElement(rsmd.getColumnName(i));		
		}
		Vector<Vector<Object> > rowsVec= new Vector<Vector<Object>>();
		while(res.next()){
			Vector<Object> vec = new Vector<Object>();
			for(int i = 1;i<=columnsNum;++i){
				vec.add(res.getObject(i));
			}
			rowsVec.add(vec);
		}
		model = new DefaultTableModel(rowsVec,columnsVec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}	
		return model;
	}

	public static Vector<String> getHeaders(ResultSet res) {
		ResultSetMetaData rsmd;
		try {
			rsmd = res.getMetaData();	
			int columnsNum = rsmd.getColumnCount();
			Vector<String> columnsVec= new Vector<String>();
			for(int i = 1;i<=columnsNum;++i){		
					columnsVec.addElement(rsmd.getColumnName(i));						
			}
			return columnsVec;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
	}

}
