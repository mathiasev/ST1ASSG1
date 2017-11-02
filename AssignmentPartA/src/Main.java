import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
		}
		//PartA partA = new PartA();
		//partA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//partA.setVisible(true);
		
		//PartB partB = new PartB();
		//partB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//partB.setVisible(true);
		
		PartC partC = new PartC();
		partC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		partC.setVisible(true);
	}

}
