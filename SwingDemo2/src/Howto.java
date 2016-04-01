import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class Howto {

	public JFrame frame;
	private URL path;

	public Howto(URL url) {
		this.path = url;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 883, 578);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(path));
		frame.getContentPane().add(label, BorderLayout.CENTER);
	}

}
