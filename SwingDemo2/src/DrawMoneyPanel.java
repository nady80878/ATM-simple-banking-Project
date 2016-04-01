import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class DrawMoneyPanel extends JPanel {
	private JTable table;
	public static JTextField txtTotalMoney;
	private JTextField txtUsername;
	private JTextField txtDrawMoney;
	private JTextArea textArea;
	private DefaultTableModel tblModel;
	private DrawMoneyFrame f2;
	private String totalMoney;
	public DrawMoneyPanel(DrawMoneyFrame f1) {
		f2 = f1;
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(679, 11, 673, 475);
		add(scrollPane_1);
		table = new JTable(null,getHeaders());
		table.setEnabled(false);
		scrollPane_1.setViewportView(table);
		table.setBorder(null);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblModel = (DefaultTableModel) table.getModel();
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBackground(new Color(127, 255, 212));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Info", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 11, 669, 475);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Username : ");
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(27, 25, 88, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Drawn Money : ");
		label_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(27, 157, 102, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Notes : ");
		label_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(27, 233, 81, 25);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Total Money : ");
		label_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(27, 94, 102, 25);
		panel.add(label_3);
		
		txtTotalMoney = new JTextField();
		txtTotalMoney.setEnabled(false);
		txtTotalMoney.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtTotalMoney.setColumns(10);
		txtTotalMoney.setBounds(139, 91, 241, 33);
		txtTotalMoney.setText(LoginFrame.totalMoney);
		panel.add(txtTotalMoney);
		
		txtUsername = new JTextField(LoginFrame.username);
		txtUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtUsername.setEnabled(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(139, 22, 241, 33);
		panel.add(txtUsername);
		
		txtDrawMoney = new JTextField();
		txtDrawMoney.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtDrawMoney.setColumns(10);
		txtDrawMoney.setBounds(139, 154, 241, 33);
		panel.add(txtDrawMoney);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String drawMoney = txtDrawMoney.getText();
				int countNum = 0,countChar=0,length = drawMoney.length();
				for(int i = 0 ; i<length;++i){
					if(Character.isDigit(drawMoney.charAt(i)))
						countNum++;
					if(drawMoney.charAt(i)=='.')
						countChar++;
				}
				if(drawMoney.length()!=0&&(countNum>=length-1 && countChar<=1 && Double.parseDouble(drawMoney)<= Double.parseDouble(txtTotalMoney.getText())&&Double.parseDouble(drawMoney)>0)){
				Object [] row = new Object[5];
				row[0] = txtUsername.getText();
				row[1] = txtTotalMoney.getText();
				row[2] = drawMoney;				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				row[3] = dateFormat.format(date);
				row[4] = textArea.getText();			
				tblModel.addRow(row);
				txtTotalMoney.setText(""+(Double.parseDouble(txtTotalMoney.getText())-Double.parseDouble(drawMoney)));}
				else{
					JOptionPane.showMessageDialog(DrawMoneyPanel.this, "Please Make Sure That The Drawn Money is "
							+ "Correct and less or equal than The Total Money ","Warning",JOptionPane.WARNING_MESSAGE);
					txtDrawMoney.setText("");
				}
				}
		});
		button.setOpaque(false);
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.setIcon(new ImageIcon(getClass().getResource("Add.jpg")));
		button.setBounds(436, 130, 88, 89);
		panel.add(button);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblAdd.setBounds(534, 159, 46, 25);
		panel.add(lblAdd);
		
		JLabel lblClear = new JLabel("Clear");
		lblClear.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblClear.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblClear.setBounds(534, 49, 46, 25);
		panel.add(lblClear);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtDrawMoney.setText("");
				textArea.setText("");
			}
		});
		button_1.setOpaque(false);
		button_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button_1.setIcon(new ImageIcon(getClass().getResource("Clear.jpg")));
		button_1.setBounds(436, 17, 88, 89);
		panel.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 218, 241, 177);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setAlignmentX(1.0f);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getRowCount()>0){
				totalMoney = tblModel.getValueAt(table.getRowCount()-1, 1).toString();	
				if(totalMoney.length()!=0){	
					txtTotalMoney.setText(totalMoney);
				tblModel.removeRow(table.getRowCount()-1);
				}
			  }
			}
		});
		button_2.setOpaque(false);
		button_2.setAlignmentX(1.0f);
		button_2.setBounds(436, 246, 88, 89);
		button_2.setIcon(new ImageIcon(getClass().getResource("Remove.jpg")));
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f2.dispose();
			}
		});
		button_3.setOpaque(false);
		button_3.setAlignmentX(1.0f);
		button_3.setBounds(436, 362, 88, 89);
		button_3.setIcon(new ImageIcon(getClass().getResource("Cancel.jpg")));
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = tblModel.getRowCount();
		if(rowCount>0){
				for(int i = 0 ; i<rowCount  ;i++)
				{
					String row[] = new String[5];
					for (int j = 0; j < row.length; j++) {
						row[j] = tblModel.getValueAt(i, j).toString();		
					}			
					try {
						insertIntoDB(row);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(DrawMoneyPanel.this,e);
						return;
					}
				}
		    	JOptionPane.showMessageDialog(DrawMoneyPanel.this, "The Data Saved Successfully(^_^)!!! ","Info",JOptionPane.INFORMATION_MESSAGE);
		    	try {
					updateTotalMoney();
			    	f2.dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(DrawMoneyPanel.this,e);
				}
			}else
		    	JOptionPane.showMessageDialog(DrawMoneyPanel.this, "You Don't Make Any Operations to save ","Info",JOptionPane.WARNING_MESSAGE);

				}
		});
		button_4.setOpaque(false);
		button_4.setAlignmentX(1.0f);
		button_4.setBounds(14, 375, 88, 89);
		button_4.setIcon(new ImageIcon(getClass().getResource("Save.jpg")));
		panel.add(button_4);
		
		JLabel lblRemove = new JLabel("Remove Last");
		lblRemove.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblRemove.setAlignmentX(1.0f);
		lblRemove.setBounds(534, 272, 96, 33);
		panel.add(lblRemove);
		
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblCancel.setAlignmentX(1.0f);
		lblCancel.setBounds(534, 394, 46, 25);
		panel.add(lblCancel);
		JLabel lblSave = new JLabel("Save");
		lblSave.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblSave.setAlignmentX(1.0f);
		lblSave.setBounds(112, 406, 46, 25);
		panel.add(lblSave);

	}
	private void updateTotalMoney() throws SQLException {
		PreparedStatement stat = LoginFrame.conn.prepareStatement("update login set `total money` = ? where `username`=?");   
	    stat.setDouble(1, Double.parseDouble(txtTotalMoney.getText()));
	    stat.setString(2, txtUsername.getText());
	    LoginFrame.totalMoney = DrawMoneyPanel.txtTotalMoney.getText();
	    BankMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    DrawMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    CheckAccountPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    stat.execute();
	}
	private void insertIntoDB(String[] row) throws Exception {
			PreparedStatement stat = LoginFrame.conn.prepareStatement("INSERT INTO `atm_db`.`money` (`username`, `total money`, `drawn money`, `last date`, `notes`) VALUES (?,?,?,?,?);");
		    stat.setString(1, (String) row[0]);
		    stat.setDouble(2, Double.parseDouble(row[1]) );
		    stat.setDouble(3, Double.parseDouble(row[2]));
		    stat.setString(4, (String) row[3]);
		    stat.setString(5, (String) row[4]);
		    stat.execute();
	}
	Vector<String> getHeaders(){
		try {
			PreparedStatement stat = LoginFrame.conn.prepareStatement("Select * from money where `username` = ? ");
			stat.setString(1, LoginFrame.username);
			ResultSet res = stat.executeQuery();
			return DAL.getHeaders(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
