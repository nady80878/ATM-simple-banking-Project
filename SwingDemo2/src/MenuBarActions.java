import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBarActions {
	
	public void loginAction(){
		new LoginFrame();
	}
	public void logoutAction(){
		MenuBarPanel.tasks.setEnabled(false);
		MenuBarPanel.settings.setEnabled(false);
		MenuBarPanel.howTo.setEnabled(false);
		MenuBarPanel.login.setEnabled(true);

	}
	public boolean exitAction(){
		if(JOptionPane.showConfirmDialog(null,"Do You Want To Confirm Closing ?","Confirm Closing",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
		{System.exit(0); return false;}
		return true;
	}
	public void drawMoneyAction(){
		setComponents.desktop.add(new DrawMoneyFrame(),BorderLayout.CENTER,0);
	}
	public void bankMoneyAction(){
		setComponents.desktop.add(new BankMoneyFrame(),BorderLayout.CENTER,0);
	}

	public void checkAccountAction(){
		setComponents.desktop.add(new CheckAccountFrame(),BorderLayout.CENTER,0);
	}
	public void changeBackGrourdAction(){
		final JFileChooser fileChooser = new JFileChooser();
		 FileNameExtensionFilter j1 = new FileNameExtensionFilter("Png", "png");
		 FileNameExtensionFilter j2 = new FileNameExtensionFilter("jpg", "jpg");
		 FileNameExtensionFilter j3 = new FileNameExtensionFilter("JPEG", "JPEG");
		 fileChooser.addChoosableFileFilter(j1);
		 fileChooser.addChoosableFileFilter(j2);
		 fileChooser.addChoosableFileFilter(j3);
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 JButton preview = new JButton("preview");
		 preview.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(fileChooser.getSelectedFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 
		 });
		fileChooser.setAccessory(preview);
		if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
		{   try{
			File file = fileChooser.getSelectedFile();
			setComponents.lbl.setIcon(new ImageIcon(file.getAbsolutePath()));
			String sql = "update login set `image` = ? where `username` = ? and `password` = ? ";
			PreparedStatement stat = LoginFrame.conn.prepareStatement(sql);
			stat.setBlob(1, convertImgToBlob(file));
			stat.setString(2, LoginFrame.username);
			stat.setString(3, LoginFrame.txtPassWord.getText());
			if(stat.execute())
				JOptionPane.showMessageDialog(null, "setting background photo is correct");
		       }
		 catch(Exception e){
				setComponents.lbl.setIcon(new ImageIcon(getClass().getResource("atmlogo.jpg")));
				 JOptionPane.showMessageDialog(null, e);
		 }
		}	
	}
	
	public void makeBackUpAction(){
		JFileChooser file = new JFileChooser();
		
		FileNameExtensionFilter j1 = new FileNameExtensionFilter("sql", "sql");
		 FileNameExtensionFilter j2 = new FileNameExtensionFilter("SQL", "SQL");	
		 file.addChoosableFileFilter(j1);
		 file.addChoosableFileFilter(j2);
		 if(file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			try {
				String command ;
				if(file.getSelectedFile().getAbsolutePath().contains(".sql") || file.getSelectedFile().getAbsolutePath().contains(".SQL"))
				 command = "C:\\xampp\\mysql\\bin\\mysqldump -u root atm_db > "+file.getSelectedFile().getPath();
				else
			   command = "C:\\xampp\\mysql\\bin\\mysqldump -u root atm_db > "+file.getSelectedFile().getPath()+".sql";
				
				  Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", command});

			       int processComplete = runtimeProcess.waitFor();

			       if(processComplete == 0)
			       {
			    	   JOptionPane.showMessageDialog(null, "Backup Created Successfully !","Success",JOptionPane.INFORMATION_MESSAGE);
			       }
			       else
			       {
			    	   JOptionPane.showMessageDialog(null, "Backup didn\'t Created Successfully !","warning",JOptionPane.WARNING_MESSAGE);
			       }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}
	public void restoreDataAction(){
JFileChooser file = new JFileChooser();
		
		FileNameExtensionFilter j1 = new FileNameExtensionFilter("sql", "sql");
		 FileNameExtensionFilter j2 = new FileNameExtensionFilter("SQL", "SQL");	
		 file.addChoosableFileFilter(j1);
		 file.addChoosableFileFilter(j2);
		 file.setAcceptAllFileFilterUsed(false);
		 if(file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			try {
				Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c","C:\\xampp\\mysql\\bin\\mysql -u root atm_db < "+file.getSelectedFile().getAbsolutePath() });

			       int processComplete = runtimeProcess.waitFor();

			       if(processComplete == 0)
			       {
			    	   JOptionPane.showMessageDialog(null, "Restore Created Successfully !","Success",JOptionPane.INFORMATION_MESSAGE);
			       }
			       else
			       {
			    	   JOptionPane.showMessageDialog(null, "Restore didn\'t Created Successfully !","warning",JOptionPane.WARNING_MESSAGE);
			       }
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		 	}
		 }
	public void restoreInfoAction(){
		new Howto(getClass().getResource("HowToRestore.png")).frame.setVisible(true);
	}
	
	public void changeThemeInfoAction(){
			new Howto(getClass().getResource("HowToChangeTheme.png")).frame.setVisible(true);
	}
	
	public void backUpInfoAction(){
			new Howto(getClass().getResource("HowToBackup.png")).frame.setVisible(true);
	}
	public void aboutAction(){
		JOptionPane.showMessageDialog(null, "Software : ATM software \n Version : beta 1.0 \n Programmer : nady shalaby \n Year : 2014/2015 \n Copy Rights © Saved "
				+ "\n Taekunger(^_^)Software ","About",JOptionPane.INFORMATION_MESSAGE);
	}
	private InputStream convertImgToBlob(File file){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fis;
	}
}
