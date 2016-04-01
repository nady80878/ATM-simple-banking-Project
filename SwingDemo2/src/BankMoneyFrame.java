
import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class BankMoneyFrame extends JInternalFrame{
	public BankMoneyFrame() {
		super("Bank Money");
		setClosable(true);
        setDefaultCloseOperation(2);
        setVisible(true);
        
        init();
        }

	private void init() {
		setLayout(new BorderLayout());
		add(new BankMoneyPanel(this),BorderLayout.CENTER);
	}
	

}
