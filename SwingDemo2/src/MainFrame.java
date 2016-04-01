import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class MainFrame {

	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
			    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			    	        if ("Nimbus".equals(info.getName())) {
			    	            UIManager.setLookAndFeel(info.getClassName());
			    	          break;
			    	        }
			    	    }
			    	   }
			     catch (Exception e) {
			        System.out.println("can't set look and feel");
			     }
			new LoginFrame();
		}
	});

	}
}
