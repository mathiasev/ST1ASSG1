import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PartC extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener_Button;
	private ButtonGroup buttonGroup_Options = new ButtonGroup();
	private JComboBox<String> jCombo;
	private JList<String> jOptions1;
	private JTextArea jText_out;

	public PartC() {

		setTitle("Part C");
		setSize(800, 700);	
getContentPane().setLayout(null);
		//getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		addActions();

		for (Component comp : GUIelements()) {
			getContentPane().add(comp);
		}
		pack();

	}

	private ArrayList<Component> GUIelements() {
		ArrayList<Component> tempList = new ArrayList<Component>();

		JPanel jPanelButtons = new JPanel();

		jPanelButtons.setLayout(new BoxLayout(jPanelButtons, BoxLayout.Y_AXIS));
		jPanelButtons.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		JRadioButton addOption = new JRadioButton("Add");
		addOption.setActionCommand(addOption.getText());
		buttonGroup_Options.add(addOption);
		
		jPanelButtons.add(addOption);

		JRadioButton subtractOption = new JRadioButton("Subtract");
		subtractOption.setActionCommand(subtractOption.getText());
		buttonGroup_Options.add(subtractOption);
		jPanelButtons.add(subtractOption);

		JRadioButton interleaveOption = new JRadioButton("Interleave");
		interleaveOption.setActionCommand(interleaveOption.getText());
		buttonGroup_Options.add(interleaveOption);
		jPanelButtons.add(interleaveOption);

		jPanelButtons.setBounds(10,10,100,100);
		tempList.add(jPanelButtons);

		String[] option1Labels = { "10", "20", "30", "50", "100", "200", "300", "500", "BRAD" };
		jOptions1 = new JList<String>(option1Labels);
		JScrollPane jScroll = new JScrollPane(jOptions1);
		
		jScroll.setBounds(120,10,100,100);
		tempList.add(jScroll);

		String[] option2Labels = { "10", "20", "30", "50", "100", "200", "300", "500", "JANET" };
		jCombo = new JComboBox<String>(option2Labels);
		
		jCombo.setBounds(120,120,100,50);
		tempList.add(jCombo);

		JButton button = new JButton("Do it!");
		button.addActionListener(actionListener_Button);
		
		button.setBounds(240,10,100,50);
		tempList.add(button);

		jText_out = new JTextArea();
		jText_out.setEditable(false);
		jText_out.setRows(5);
		JScrollPane output = new JScrollPane(jText_out);
		
		output.setBounds(380,10,200,200);
		tempList.add(output);

		return tempList;
	}

	private void addActions() {

		actionListener_Button = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (buttonGroup_Options.getSelection() != null) {
					switch (buttonGroup_Options.getSelection().getActionCommand()) {
					case "Add":
						try {
							int num1 = Integer.parseInt(jOptions1.getSelectedValue());
							int num2 = Integer.parseInt((String) jCombo.getSelectedItem());
							jText_out.append(String.format("%d + %d = %d\n", num1, num2, (num1 + num2)));
						} catch (Exception e) {
							jText_out.append(String.format("%s + %s = %s\n", jOptions1.getSelectedValue(),
									(String) jCombo.getSelectedItem(),
									jOptions1.getSelectedValue() + (String) jCombo.getSelectedItem()));
						}
						break;

					case "Subtract":
						try {
							int num1 = Integer.parseInt(jOptions1.getSelectedValue());
							int num2 = Integer.parseInt((String) jCombo.getSelectedItem());
							jText_out.append(String.format("%d - %d = %d\n", num2, num1, (num2 - num1)));
						} catch (Exception e) {
							jText_out.append(String.format("Cannot subtract %s from %s\n",
									(String) jCombo.getSelectedItem(), jOptions1.getSelectedValue()));
						}
						break;

					case "Interleave":
						String s1 = jOptions1.getSelectedValue();
						String s2 = (String) jCombo.getSelectedItem();
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < s1.length(); i++) {
							sb.append(s1.charAt(i));
							if (i < s2.length()) {
								sb.append(s2.charAt(i));
							}
						}
						if (s2.length() > s1.length()) {
							sb.append(s2.substring(s1.length(), s2.length()));
						}
						jText_out.append(sb.toString() + "\n");
						break;

					}
				}
			}
		};
	}
}
