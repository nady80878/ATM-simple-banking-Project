import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
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
import com.sun.glass.events.KeyEvent;


public class MenuBarPanel extends JMenuBar {

	public static JMenu main , tasks , help ,settings ,howTo , changeTheme;
	public static JMenuItem login;
	private JMenuItem  logout , exit , drawMoney , bankMoney , checkAccount
	,restoreData , makeBackUp , changeBackGround , about , restoreInfo , backUpInfo , changeThemeInfo;
	private JRadioButtonMenuItem nimbus , acryl , aero , aluminuim , bernstein , fast , graphite , hifi , luna , mcwin , mint , noire , smart , texture ;
	
	public MenuBarPanel(){
		//---------------------radio buttons----------------
		nimbus  = new JRadioButtonMenuItem("Nimbus");
		acryl = new JRadioButtonMenuItem("Acryl");
		aero = new JRadioButtonMenuItem("Aero");
		aluminuim = new JRadioButtonMenuItem("Aluminuim");
		bernstein = new JRadioButtonMenuItem("Bernstein");
		fast = new JRadioButtonMenuItem("Fast");
		graphite = new JRadioButtonMenuItem("Graphite");
		hifi = new JRadioButtonMenuItem("HIFI");
		luna = new JRadioButtonMenuItem("Luna");
		mcwin = new JRadioButtonMenuItem("McWin");
		mint = new JRadioButtonMenuItem("Mint");
		noire = new JRadioButtonMenuItem("Noire");
		smart  = new JRadioButtonMenuItem("Smart");
		texture = new JRadioButtonMenuItem("Texture");
		//--------------------- menus----------------------
		main = new JMenu("Main");
		tasks = new JMenu("Tasks");
		help = new JMenu("Help");
		settings = new JMenu("Settings");
		howTo = new JMenu("How To");
		changeTheme = new JMenu("Choose Theme");
		//---------------------set Mnemonics for menus----------------------
				main.setMnemonic(KeyEvent.VK_M);
				tasks.setMnemonic(KeyEvent.VK_T);
				settings.setMnemonic(KeyEvent.VK_S);
				howTo.setMnemonic(KeyEvent.VK_H);
		//---------------------menu items-------------------
		login = new JMenuItem("Login");
		MenuBarPanel.login.setEnabled(false);
		login.setIcon(new ImageIcon(getClass().getResource("login.jpg")));
        logout = new JMenuItem("Logout");
		restoreData = new JMenuItem("Restore Previous Data");
		makeBackUp = new JMenuItem("Make Back Up");
		exit = new JMenuItem("Exit");
		drawMoney = new JMenuItem("Draw Money");
		bankMoney = new JMenuItem("Bank Money");
		changeBackGround = new JMenuItem("Change Back Ground");
		checkAccount = new JMenuItem("Check Account"); 
		about  = new JMenuItem("About");
		restoreInfo  = new JMenuItem("Restore Data ?");
		backUpInfo  = new JMenuItem("Make Back Up For Program ?");
		changeThemeInfo = new JMenuItem("Change Theme ?");
		//---------------set Accelerators for menu items----------------------
		login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		restoreData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
		makeBackUp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		drawMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		bankMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.CTRL_MASK));
		changeBackGround.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));
		checkAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK)); 
		//---------------------set tool tips for menus----------------------
				main.setToolTipText(main.getActionCommand());
				tasks.setToolTipText(tasks.getActionCommand());
				help.setToolTipText(help.getActionCommand());
				settings.setToolTipText(settings.getActionCommand());
				howTo.setToolTipText(howTo.getActionCommand());
				changeTheme.setToolTipText(howTo.getActionCommand());
				login.setToolTipText(login.getActionCommand());
		        logout.setToolTipText(logout.getActionCommand());
				exit.setToolTipText(exit.getActionCommand());
		        restoreData .setToolTipText(restoreData.getActionCommand());
				makeBackUp.setToolTipText(changeBackGround.getActionCommand());
				changeBackGround.setToolTipText(restoreInfo.getActionCommand());
				drawMoney.setToolTipText(drawMoney.getActionCommand());
				bankMoney .setToolTipText(bankMoney.getActionCommand());
				checkAccount.setToolTipText(checkAccount.getActionCommand());
				about.setToolTipText(howTo.getActionCommand());
				restoreInfo.setToolTipText(howTo.getActionCommand()+" "+restoreInfo.getActionCommand());
				backUpInfo.setToolTipText(howTo.getActionCommand()+" "+backUpInfo.getActionCommand());
				changeThemeInfo.setToolTipText(howTo.getActionCommand()+" "+changeThemeInfo.getActionCommand());
		//---------------------add listeners to menu items-------------------
				Listener listener = new Listener();
				RadListener radlistener = new RadListener();
				login.addActionListener(listener);
		        logout.addActionListener(listener);
				restoreData.addActionListener(listener);
				makeBackUp.addActionListener(listener);
				exit.addActionListener(listener);
				drawMoney.addActionListener(listener);
				bankMoney.addActionListener(listener);
				changeBackGround.addActionListener(listener);
				checkAccount.addActionListener(listener); 
				changeTheme.addActionListener(listener);
				about.addActionListener(listener);
				restoreInfo.addActionListener(listener);
				backUpInfo.addActionListener(listener);
				changeThemeInfo.addActionListener(listener);
				nimbus.addActionListener(radlistener);
				acryl .addActionListener(radlistener);
				aero.addActionListener(radlistener);
				aluminuim.addActionListener(radlistener);
				bernstein.addActionListener(radlistener);
				fast.addActionListener(radlistener);
				graphite.addActionListener(radlistener);
				hifi.addActionListener(radlistener);
				luna.addActionListener(radlistener);
				mcwin.addActionListener(radlistener);
				mint.addActionListener(radlistener);
				noire.addActionListener(radlistener);
				smart.addActionListener(radlistener);
				texture.addActionListener(radlistener);
				//----------------------------set action command ------------------
				nimbus.setActionCommand("0");
				acryl .setActionCommand("1");
				aero.setActionCommand("2");
				aluminuim.setActionCommand("3");
				bernstein.setActionCommand("4");
				fast.setActionCommand("5");
				graphite.setActionCommand("6");
				hifi.setActionCommand("7");
				luna.setActionCommand("8");
				mcwin.setActionCommand("9");
				mint.setActionCommand("10");
				noire.setActionCommand("11");
				smart.setActionCommand("12");
				texture.setActionCommand("13");
		//--------------------add menu and menu items -------------------
		add(main);
		add(tasks);
		add(settings);
		add(help);
		main.add(login);
		main.addSeparator();
		main.add(logout);
		main.addSeparator();
		main.add(exit);
		tasks.add(drawMoney);
		tasks.addSeparator();
		tasks.add(bankMoney);
		tasks.addSeparator();
		tasks.add(checkAccount);
		settings.add(changeBackGround);
		settings.addSeparator();
		settings.add(restoreData);
		settings.addSeparator();
		settings.add(makeBackUp);
		settings.addSeparator();
		settings.add(changeTheme);
		help.add(about);
		help.addSeparator();
		help.add(howTo);
		howTo.add(restoreInfo);
		howTo.addSeparator();
		howTo.add(backUpInfo);
		howTo.addSeparator();
		howTo.add(changeThemeInfo);
		 ButtonGroup group = new ButtonGroup();
			group.add(nimbus);
			group.add(acryl);
			group.add(aero);
			group.add(aluminuim);
			group.add(bernstein);
			group.add(fast);
			group.add(graphite);
			group.add(graphite);
			group.add(hifi);
			group.add(luna);
			group.add(mcwin);
			group.add(mint);
			group.add(noire);
			group.add(smart);
			group.add(texture);
		changeTheme.add(nimbus);
		changeTheme.add(acryl);
		changeTheme.add(aero);
		changeTheme.add(aluminuim);
		changeTheme.add(bernstein);
		changeTheme.add(fast);
		changeTheme.add(graphite);
		changeTheme.add(graphite);
		changeTheme.add(hifi);
		changeTheme.add(luna);
		changeTheme.add(mcwin);
		changeTheme.add(mint);
		changeTheme.add(noire);
		changeTheme.add(smart);
		changeTheme.add(texture);
	}
}
 		//-----------------------Actions For Menus-------------------
	   class Listener implements ActionListener{
        private MenuBarActions ob ;
		@Override
		public void actionPerformed(ActionEvent e) {
			ob = new MenuBarActions();
		JMenuItem menuItem = (JMenuItem) e.getSource();
		String check = menuItem.getActionCommand();
		switch(check){
		case "Exit":
			ob.exitAction();
			break;
		case "Login":
			ob.loginAction();
			break;
		case "Logout":
			ob.logoutAction();
			break;
		case "Restore Previous Data":
			ob.restoreDataAction();
			break;
		case "Make Back Up":
			ob.makeBackUpAction();
			break;
		case"Draw Money":
			ob.drawMoneyAction();
			break;
		case "Bank Money":
			ob.bankMoneyAction();
			break;
		case "Change Back Ground":
			ob.changeBackGrourdAction();
			break;
		case "About":
			ob.aboutAction();
			break;
		case "Check Account":
			ob.checkAccountAction();
			break;
		case "Restore Data ?":
			ob.restoreInfoAction();
			break;
		case "Make Back Up For Program ?":
			ob.backUpInfoAction();
			break;
		case "Change Theme ?":
			ob.changeThemeInfoAction();
			break;
		}
	}
	
}
	 //----------------------actions for radio buttons--------------
	class RadListener implements ActionListener{
      
	@Override
	public void actionPerformed(final ActionEvent radio){
		LookAndFeel [] laf = new LookAndFeel[] {new NimbusLookAndFeel(),new AcrylLookAndFeel(),new AeroLookAndFeel(),new AluminiumLookAndFeel(),
				new BernsteinLookAndFeel(),new FastLookAndFeel(),new GraphiteLookAndFeel(),new HiFiLookAndFeel(),new LunaLookAndFeel(),new McWinLookAndFeel(),new MintLookAndFeel()
		,new SmartLookAndFeel(),new NoireLookAndFeel(),new TextureLookAndFeel()};
				try {
					setComponents.frame.dispose();
					UIManager.setLookAndFeel(laf[Integer.parseInt(radio.getActionCommand())]);
					SwingUtilities.updateComponentTreeUI(setComponents.frame);					
					new setComponents();
					setComponents.bar.setBorder(BorderFactory.createTitledBorder("Operations"));
					setComponents.bar.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,65));
					setComponents.bar.setBackground(Color.green);
					String sql = "update login set `Theme` = ? where `username` = ? and `password` = ? ";
					PreparedStatement stat = LoginFrame.conn.prepareStatement(sql);
					stat.setInt(1,Integer.parseInt(radio.getActionCommand()));
					stat.setString(2, LoginFrame.txtLogin.getText());
					stat.setString(3, LoginFrame.txtPassWord.getText());
					stat.execute();
		    	   }
		     catch (Exception e) {
		        System.out.println("can't set look and feel");
		        e.printStackTrace();
		     }
		
	}
		
	}