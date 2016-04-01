import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class BankMoneyPanel extends JPanel {
	private JTextField txtUsername;
	public static JTextField txtTotalMoney;
	private JTextField txtBankedMoney;
	private JTable table;
	private JTextArea textArea;
	private BankMoneyFrame f2;
	private DefaultTableModel tblModel;
	private String totalMoney;

	public BankMoneyPanel(BankMoneyFrame f1) {
		 f2 = f1;
		setBackground(new Color(30, 144, 255));
		setLayout(null);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "User Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		scrollPane_1.setBounds(565, 11, 745, 580);
		add(scrollPane_1);
		table = new JTable(null,getHeaders());
		table.setEnabled(false);
		tblModel = (DefaultTableModel) table.getModel();
		scrollPane_1.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 224, 208));
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 0, 255), 3), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 0, 204))));
		panel.setBounds(10, 11, 545, 568);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username : ");
		lblNewLabel.setBounds(31, 42, 91, 37);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(204, 0, 0));
		
		JLabel lblTotalMoney = new JLabel("Total Money : ");
		lblTotalMoney.setBounds(31, 100, 101, 37);
		panel.add(lblTotalMoney);
		lblTotalMoney.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalMoney.setForeground(new Color(204, 0, 0));
		
		JLabel lblBankedMoney = new JLabel("Banked Money : ");
		lblBankedMoney.setBounds(31, 161, 115, 37);
		panel.add(lblBankedMoney);
		lblBankedMoney.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBankedMoney.setForeground(new Color(204, 0, 0));
		
		JLabel lblNotes = new JLabel("Notes : ");
		lblNotes.setBounds(31, 225, 91, 37);
		panel.add(lblNotes);
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNotes.setForeground(new Color(204, 0, 0));
		
		txtUsername = new JTextField();
		txtUsername.setEnabled(false);
		txtUsername.setBounds(142, 42, 203, 37);
		txtUsername.setText(LoginFrame.username);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtTotalMoney = new JTextField();
		txtTotalMoney.setEnabled(false);
		txtTotalMoney.setBounds(142, 99, 203, 37);
		panel.add(txtTotalMoney);
		txtTotalMoney.setColumns(10);
		txtTotalMoney.setText(LoginFrame.totalMoney);
		
		txtBankedMoney = new JTextField();
		txtBankedMoney.setBounds(142, 160, 203, 37);
		panel.add(txtBankedMoney);
		txtBankedMoney.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 216, 203, 202);
		panel.add(scrollPane);
		
		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(getClass().getResource("Add.jpg")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bankMoney = txtBankedMoney.getText();
				int countNum = 0,countChar=0,length = bankMoney.length();
				for(int i = 0 ; i<length;++i){
					if(Character.isDigit(bankMoney.charAt(i)))
						countNum++;
					if(bankMoney.charAt(i)=='.')
						countChar++;
				}
				if(bankMoney.length()!=0&&(countNum>=length-1 && countChar<=1 && Double.parseDouble(bankMoney)>0)){
				Object [] row = new Object[5];
				row[0] = txtUsername.getText();
				row[1] = txtTotalMoney.getText();
				row[2] = bankMoney;				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				row[3] = dateFormat.format(date);
				row[4] = textArea.getText();			
				tblModel.addRow(row);
				txtTotalMoney.setText(""+(Double.parseDouble(txtTotalMoney.getText())+Double.parseDouble(bankMoney)));}
				else{
					JOptionPane.showMessageDialog(BankMoneyPanel.this, "Please Make Sure That The Banked Money is "
							+ "Correct","Warning",JOptionPane.WARNING_MESSAGE);
					txtBankedMoney.setText("");
			}}
		});
	
		button_2.setBounds(391, 216, 82, 82);
		panel.add(button_2);
		
		JLabel lblAdd = new JLabel("Add");
		lblAdd.setFont(new Font("Script MT Bold", Font.BOLD, 14));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setBounds(408, 192, 58, 25);
		panel.add(lblAdd);
		
		JLabel lblRemove = new JLabel("Remove Last");
		lblRemove.setFont(new Font("Script MT Bold", Font.BOLD, 14));
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setBounds(391, 309, 91, 25);
		panel.add(lblRemove);
		
		JLabel lblSave = new JLabel("Save");
		lblSave.setFont(new Font("Script MT Bold", Font.BOLD, 14));
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblSave.setBounds(71, 442, 58, 25);
		panel.add(lblSave);
		
		JLabel lblClear = new JLabel("Clear");
		lblClear.setFont(new Font("Script MT Bold", Font.BOLD, 14));
		lblClear.setHorizontalAlignment(SwingConstants.CENTER);
		lblClear.setBounds(240, 442, 58, 25);
		panel.add(lblClear);
		
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setFont(new Font("Script MT Bold", Font.BOLD, 14));
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setBounds(408, 442, 58, 25);
		panel.add(lblCancel);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(BankMoneyPanel.this,e);
						return;
					}
				}
		    	JOptionPane.showMessageDialog(BankMoneyPanel.this, "The Data Saved Successfully(^_^)!!! ","Info",JOptionPane.INFORMATION_MESSAGE);
		    	try {
					updateTotalMoney();
			    	f2.dispose();

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(BankMoneyPanel.this,e);
				}
			}else
		    	JOptionPane.showMessageDialog(BankMoneyPanel.this, "You Don't Make Any Operations to save ","Info",JOptionPane.WARNING_MESSAGE);

				}
		});
		button.setBounds(57, 466, 82, 82);
		button.setIcon(new ImageIcon(getClass().getResource("Save.jpg")));
		panel.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtBankedMoney.setText("");
				textArea.setText("");
			}
		});
		button_1.setBounds(226, 466, 82, 82);
		button_1.setIcon(new ImageIcon(getClass().getResource("Clear.jpg")));
		panel.add(button_1);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f2.dispose();
			}
		});
		button_3.setBounds(391, 466, 82, 82);
		button_3.setIcon(new ImageIcon(getClass().getResource("Cancel.jpg")));
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				totalMoney = tblModel.getValueAt(table.getRowCount()-1, 1).toString();	
				if(table.getRowCount()>0 && totalMoney.length()!=0){	
					txtTotalMoney.setText(totalMoney);
				tblModel.removeRow(table.getRowCount()-1);
				}
			}
		});
		button_4.setBounds(391, 336, 82, 82);
		button_4.setIcon(new ImageIcon(getClass().getResource("Remove.jpg")));
		panel.add(button_4);	
	}
	Vector<String> getHeaders(){
		try {
			PreparedStatement stat = LoginFrame.conn.prepareStatement("Select * from bank_money where `username` = ? ");
			stat.setString(1, LoginFrame.username);
			ResultSet res = stat.executeQuery();
			return DAL.getHeaders(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private void insertIntoDB(String[] row) throws Exception {
		PreparedStatement stat = LoginFrame.conn.prepareStatement("INSERT INTO `atm_db`.`bank_money` (`username`, `total money`, `banked money`, `last date`, `notes`) VALUES (?,?,?,?,?);");
	    stat.setString(1, (String) row[0]);
	    stat.setDouble(2, Double.parseDouble(row[1]) );
	    stat.setDouble(3, Double.parseDouble(row[2]));
	    stat.setString(4, (String) row[3]);
	    stat.setString(5, (String) row[4]);
	    stat.execute();
}
	public void updateTotalMoney() throws SQLException {
		PreparedStatement stat = LoginFrame.conn.prepareStatement("update login set `total money` = ? where `username`=?");   
	    stat.setDouble(1, Double.parseDouble(txtTotalMoney.getText()));
	    stat.setString(2, txtUsername.getText());
	    LoginFrame.totalMoney =BankMoneyPanel.txtTotalMoney.getText();
	    BankMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    DrawMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    CheckAccountPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    stat.execute();
	}
}
