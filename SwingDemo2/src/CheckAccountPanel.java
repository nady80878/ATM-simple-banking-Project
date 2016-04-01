import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;


public class CheckAccountPanel extends JPanel {
	private JTable tblBankMoney;
	private JTable tblDrawMoney;
	private JTextField txtUsername;
	private JTextField txtPassword;
	public static JTextField txtTotalMoney;
    private CheckAccountFrame f2;
	/**
	 * Create the panel.
	 */
	
	@SuppressWarnings("deprecation")
	public CheckAccountPanel(CheckAccountFrame f1) {
		f2 = f1;
		setBackground(new Color(0, 191, 255));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 153), new Color(204, 0, 153), new Color(51, 0, 255), new Color(204, 0, 255)), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Banked Money Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 128))));
		scrollPane.setBounds(660, 11, 617, 522);
		add(scrollPane);
		
		tblBankMoney = new JTable(DAL.getData(getData("bank_money")));
		tblBankMoney.setEnabled(false);
		scrollPane.setViewportView(tblBankMoney);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 153), new Color(204, 0, 153), new Color(51, 0, 255), new Color(204, 0, 255)), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "User Drawn Money Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 128))));
		scrollPane_1.setBounds(10, 11, 617, 522);
		add(scrollPane_1);
		
		tblDrawMoney = new JTable(DAL.getData(getData("money")));
		tblDrawMoney.setEnabled(false);
		scrollPane_1.setViewportView(tblDrawMoney);
		
		JLabel lblNewLabel = new JLabel("Username : ");
		lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setBounds(42, 561, 91, 34);
		add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblPassword.setForeground(new Color(255, 0, 255));
		lblPassword.setBounds(353, 561, 91, 34);
		add(lblPassword);
		
		JLabel lblTotalMoney = new JLabel("Total Money : ");
		lblTotalMoney.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblTotalMoney.setForeground(new Color(255, 0, 255));
		lblTotalMoney.setBounds(658, 561, 103, 34);
		add(lblTotalMoney);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtUsername.setForeground(new Color(255, 0, 0));
		txtUsername.setEnabled(false);
		txtUsername.setBounds(131, 565, 175, 27);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtPassword.setForeground(new Color(255, 0, 0));
		txtPassword.setEnabled(false);
		txtPassword.setColumns(10);
		txtPassword.setBounds(435, 568, 175, 27);
		add(txtPassword);
		
		txtTotalMoney = new JTextField();
		txtTotalMoney.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtTotalMoney.setForeground(new Color(255, 0, 0));
		txtTotalMoney.setEnabled(false);
		txtTotalMoney.setColumns(10);
		txtTotalMoney.setBounds(761, 568, 175, 27);
		add(txtTotalMoney);
		
		final JButton btnEdit = new JButton("Edit");
		final JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				btnSave.setEnabled(true);
				btnEdit.setEnabled(false);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnSave.setEnabled(false);
					btnEdit.setEnabled(true);
					txtUsername.setEnabled(false);
					txtPassword.setEnabled(false);
					updateData();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);	
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(getClass().getResource("Edit.png")));
		btnEdit.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEdit.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnEdit.setForeground(new Color(255, 140, 0));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBounds(968, 568, 89, 27);
		add(btnEdit);
		btnSave.setIcon(new ImageIcon(getClass().getResource("Save2.png")));
		btnSave.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnSave.setForeground(new Color(255, 140, 0));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setBounds(1077, 568, 89, 27);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f2.dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(getClass().getResource("Cancel2.png")));
		btnCancel.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnCancel.setForeground(new Color(255, 140, 0));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(1188, 568, 89, 27);
		add(btnCancel);
		txtUsername.setText(LoginFrame.username);
		txtPassword.setText(LoginFrame.txtPassWord.getText());
		txtTotalMoney.setText(LoginFrame.totalMoney);
	}
	
	private ResultSet getData(String s){
		try {
			PreparedStatement stat = LoginFrame.conn.prepareStatement("Select * from "+s+" where `username`= ?;");
			stat.setString(1, LoginFrame.username);
			ResultSet resultSet = stat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);	
		}
		return null;
		
	}
	public void updateData() throws SQLException {
		PreparedStatement stat = LoginFrame.conn.prepareStatement("update login set `username` = ? ,`password`= ? where `username`=?");   
	    stat.setString(1, txtUsername.getText());
	    stat.setString(2, txtPassword.getText());
	    stat.setString(3, LoginFrame.username);
	    stat.execute();
	    stat = LoginFrame.conn.prepareStatement("update money set `username` = ? where `username`=?"); 
	    stat.setString(1, txtUsername.getText());
	    stat.setString(2, LoginFrame.username);
	    stat.execute();
	    stat = LoginFrame.conn.prepareStatement("update bank_money set `username` = ? where `username`=?"); 
	    stat.setString(1, txtUsername.getText());
	    stat.setString(2, LoginFrame.username);
	    stat.execute();
	    LoginFrame.username = txtUsername.getText();
	    LoginFrame.txtPassWord.setText(txtPassword.getText());
	    BankMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    DrawMoneyPanel.txtTotalMoney.setText( LoginFrame.totalMoney);
	    tblBankMoney.setModel(DAL.getData(getData("bank_money")));
	    tblDrawMoney.setModel(DAL.getData(getData("money")));
	    JOptionPane.showMessageDialog(null, "Changes Applied Successfully (^_^)!!");
	}
}
