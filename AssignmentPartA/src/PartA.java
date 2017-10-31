import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PartA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener_Button;
	private JLabel	jLabel_FirstNumberLabel,
					jLabel_SecondNumberLabel,
					jLabel_Seperator,
					jLabel_Answer;
	private JTextField 	jText_FirstNumber,
						jText_SecondNumber;
	private JButton	jButton_AddButton,
					jButton_SubtractButton,
					jButton_ConcatenateButton;
	private String sAnswer = "";

	public PartA() {
		setTitle("Part A");
	    getContentPane().setLayout(new GridLayout(3, 2));

		addActions();
		
		for(Component comp : GUIelements())
		getContentPane().add(comp);
		
		
		
		pack();
		
		
	}

	private void addActions() {
		
		actionListener_Button = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					switch (arg0.getActionCommand()) {
					case "add":
						sAnswer = sum(jText_FirstNumber.getText(),jText_SecondNumber.getText() );
						jLabel_Answer.setText(sAnswer);
						break;
					
					case "subtract":
						sAnswer = difference(jText_FirstNumber.getText(),jText_SecondNumber.getText() );
						jLabel_Answer.setText(sAnswer);
						break;
						
					case "concatenate":
						sAnswer = jText_FirstNumber.getText() + jText_SecondNumber.getText();
						jLabel_Answer.setText(sAnswer);
					}
				}
			};
	}

	/**
	 * Calculates the difference of the first number minus the second
	 * @param number1
	 * @param number2
	 * @return difference of second number from the first
	 */
	private String difference(String number1, String number2) {
		String sDifference = "";
		try {
		double dNumber1 = Double.parseDouble(number1);
		double dNumber2 = Double.parseDouble(number2);
		double difference  = dNumber1 - dNumber2;
		sDifference = Double.toString(difference);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid number entered.", "Error",JOptionPane.ERROR_MESSAGE);
		}
		return sDifference;
	}

	/**
	 * Add two Strings
	 * @param number1
	 * @param number2
	 * @return sum of two numbers
	 */
	private String sum(String number1, String number2) {
		String sSum = "";
		try {
		double dNumber1 = Double.parseDouble(number1);
		double dNumber2 = Double.parseDouble(number2);
		double sum = dNumber1 + dNumber2;
		sSum =  Double.toString(sum);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invald number entered.","Error",JOptionPane.ERROR_MESSAGE);
		}
		return sSum;
	}

	private List<Component> GUIelements() {
		List<Component> lsComp = new ArrayList<Component>();
		
		jLabel_FirstNumberLabel = new JLabel("First Number");
		lsComp.add(jLabel_FirstNumberLabel);
		
		jText_FirstNumber = new JTextField();
		lsComp.add(jText_FirstNumber);
		
		jButton_AddButton = new JButton("Add");
		jButton_AddButton.setActionCommand("add");
		jButton_AddButton.addActionListener(actionListener_Button);
		lsComp.add(jButton_AddButton);
		
		jLabel_SecondNumberLabel = new JLabel("Second Number");
		lsComp.add(jLabel_SecondNumberLabel);
		
		 jText_SecondNumber = new JTextField();
		lsComp.add(jText_SecondNumber);
		
		jButton_SubtractButton = new JButton("Subtract");
		jButton_SubtractButton.setActionCommand("subtract");
		jButton_SubtractButton.addActionListener(actionListener_Button);
		lsComp.add(jButton_SubtractButton);
		
		jLabel_Seperator = new JLabel("");
		lsComp.add(jLabel_Seperator);
		
		jLabel_Answer = new JLabel("");
		lsComp.add(jLabel_Answer);
		
		jButton_ConcatenateButton = new JButton("Concatenate");
		jButton_ConcatenateButton.setActionCommand("concatenate");
		jButton_ConcatenateButton.addActionListener(actionListener_Button);
		lsComp.add(jButton_ConcatenateButton);
		
		
		return lsComp;
	}
	
	
}
