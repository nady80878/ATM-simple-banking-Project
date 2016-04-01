import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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


public class setComponents{
	public static JFrame frame ;
	public static JLabel lbl;
	public static JDesktopPane desktop;
	public static JMenuBar bar = new MenuBarPanel();
    public setComponents(){
		
	// MainFrame properties
	desktop = new JDesktopPane();
	frame = new JFrame("ATM Sofware");
	frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("atm.png")));
	desktop.setLayout(new BorderLayout());
	if(LoginFrame.img == null)
	lbl = new JLabel(new ImageIcon(getClass().getResource("atmlogo.jpg")));
	else
	lbl = new JLabel(new ImageIcon(LoginFrame.img));
	desktop.add(new JPanel().add(lbl),BorderLayout.CENTER);
	frame.add(desktop);
	frame.show();
	frame.setLocationRelativeTo(null);
	frame.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e) {
		if(new MenuBarActions().exitAction())
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}	
	});
	
	// menu bar Panel

	frame.setJMenuBar(bar);
	
	// JInternalFrames
	
	
}
}
