import javax.swing.JOptionPane;

public class TwoBFRException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwoBFRException(Order order1, Order order2) {
		JOptionPane.showMessageDialog(
				null, 
				String.format("Attempting to launch two BFRs on same day.\n"
						+ "Order 1: %s for %s on %s\n"
						+ "Order 2: %s for %s on %s",
						order1.getsLaunchID(),
						order1.getsClientName(),
						order1.getsDate(),
						order2.getsLaunchID(),
						order2.getsClientName(),
						order2.getsDate()
						),
				"TWO BFRs ON SAME DAY",
				JOptionPane.ERROR_MESSAGE
				);
	}
}
