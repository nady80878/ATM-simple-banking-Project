
import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class CheckAccountFrame extends JInternalFrame{
	public CheckAccountFrame() {
		super("Check Account");
		setClosable(true);
        setDefaultCloseOperation(2);
        setVisible(true);
        
        init();
        }

	private void init() {
		setLayout(new BorderLayout());
		add(new CheckAccountPanel(this));
	}
	

}
