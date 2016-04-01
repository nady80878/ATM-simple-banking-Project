
import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class DrawMoneyFrame extends JInternalFrame{
	public DrawMoneyFrame() {
		super("Draw Money");
		setClosable(true);
        setDefaultCloseOperation(2);
        setVisible(true);
        pack();
        init();
        }

	private void init() {
		setLayout(new BorderLayout());
		add(new DrawMoneyPanel(this),BorderLayout.CENTER);
	}
	

}
