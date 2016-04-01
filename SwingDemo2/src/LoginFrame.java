import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;


public class LoginFrame {
	public static JFrame frame;
	public static JTextField txtLogin ;
	public static JPasswordField txtPassWord;
	public static String username,totalMoney;
	public static Connection conn;
	public static BufferedImage img = null ;
	public static int themeNum = 0;
	public static boolean flag = true;
	private JLabel lblLogin , lblPassWord;
	private JButton btnLogin , btnCancel;
	public LoginFrame() {
		frame = new JFrame("Login");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("login.jpg")));
		frame.setLocationRelativeTo(null);
		frame. show();
	    
	    init();
	}

	private void init() {	
		

		lblLogin = new JLabel("Username : ");
		lblPassWord = new JLabel("Password : ");
		
		txtLogin = new JTextField(10);
		txtLogin.setActionCommand("txtLogin");
		txtPassWord = new JPasswordField(10);
		
		btnLogin = new JButton("Login");
		btnCancel = new JButton("Cancel");
		
		DrawMoneyPanel.txtTotalMoney = new JTextField();
		BankMoneyPanel.txtTotalMoney = new JTextField();
		CheckAccountPanel.txtTotalMoney = new JTextField();
		// setting layout
		LayoutSetting();
		
		// add listeners to the components
		LoginListener listen = new LoginListener();
		txtLogin.addActionListener(listen);
		txtPassWord.addKeyListener(listen);
		btnLogin.addActionListener(listen);
		btnCancel.addActionListener(listen);

	}

	public void LayoutSetting() {
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
	
		gc.weighty = .009;
		gc.gridx = 0;
		gc.gridy = 0;
		frame.add(lblLogin,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		frame.add(txtLogin,gc);
		
	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 1;
		frame.add(lblPassWord,gc);	
		
	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		frame.add(txtPassWord,gc);
		
	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		frame.add(btnLogin,gc);	
	    
	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		frame.add(btnCancel,gc);
		
	}
	
}

class LoginListener implements ActionListener,KeyListener{
         
	public void actionPerformed(ActionEvent e) {
		String check = e.getActionCommand();
		switch(check){
		case "Login" :
			 openFrame();
			break;
		case "Cancel" :
			LoginFrame.frame.dispose();
			break;
		}
	}
	
	private void openFrame() {
		LookAndFeel [] laf = new LookAndFeel[] {new NimbusLookAndFeel(),new AcrylLookAndFeel(),new AeroLookAndFeel(),new AluminiumLookAndFeel(),
				new BernsteinLookAndFeel(),new FastLookAndFeel(),new GraphiteLookAndFeel(),new HiFiLookAndFeel(),new LunaLookAndFeel(),new McWinLookAndFeel(),new MintLookAndFeel()
		,new SmartLookAndFeel(),new NoireLookAndFeel(),new TextureLookAndFeel()};
	if(login()&&LoginFrame.flag){
			try {
				UIManager.setLookAndFeel(laf[LoginFrame.themeNum]);			
				new setComponents();
				setComponents.bar.setBorder(BorderFactory.createTitledBorder("Operations"));
				setComponents.bar.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,65));
				setComponents.bar.setBackground(Color.green);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		 LoginFrame.flag =false;
	}else if(login()){
		try {
			setComponents.frame.dispose();
			UIManager.setLookAndFeel(laf[LoginFrame.themeNum]);
			SwingUtilities.updateComponentTreeUI(setComponents.frame);					
			new setComponents();
			setComponents.bar.setBorder(BorderFactory.createTitledBorder("Operations"));
			setComponents.bar.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,65));
			setComponents.bar.setBackground(Color.green);
		} catch (Exception e1) {
		}
		MenuBarPanel.tasks.setEnabled(true);
		MenuBarPanel.settings.setEnabled(true);
		MenuBarPanel.howTo.setEnabled(true);
		MenuBarPanel.login.setEnabled(false);
		setComponents.lbl.setIcon(new ImageIcon(LoginFrame.img));
	}
	}

	public boolean login() {
		try {
		    LoginFrame. conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","");
			PreparedStatement stat = LoginFrame.conn.prepareStatement("select * from `login` where `username`=? and `password`=? ");
			stat.setString(1, LoginFrame.txtLogin.getText());
			stat.setString(2, LoginFrame.txtPassWord.getText());
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				LoginFrame.frame.dispose();
				LoginFrame.username = rs.getString("username");
				LoginFrame.totalMoney = ""+rs.getInt("total money");
				LoginFrame.themeNum = rs.getInt("Theme");
				InputStream is = rs.getBinaryStream("image");
				if(is != null)
				LoginFrame.img = ImageIO.read(is);
				return true;
			}
			else{
				JOptionPane.showMessageDialog(LoginFrame.frame, "Enter Correct Username And Password","Login Failed",JOptionPane.ERROR_MESSAGE);
				LoginFrame.txtPassWord.setText("");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(LoginFrame.frame,e);		
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyChar() == KeyEvent.VK_ENTER && e.getSource() == LoginFrame.txtLogin)
			 LoginFrame.txtPassWord.setFocusable(true);
		 else if(e.getKeyChar() == KeyEvent.VK_ENTER && e.getSource() == LoginFrame.txtPassWord)			
		{
			openFrame();
	     }
		
	}

	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
	}
	

