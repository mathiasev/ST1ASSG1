import javax.swing.JOptionPane;

public class MoreThan2Exception extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public MoreThan2Exception(String sDate) {
	JOptionPane.showMessageDialog(
			null, 
			String.format("More than 2 launches on %s.",
					sDate
					),
			"MORE THAN 2 LAUNCHES",
			JOptionPane.ERROR_MESSAGE
			);
}
}
