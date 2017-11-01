import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PartB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ActionListener actionListener_Button;
	private JButton jButton_OrderPlanButton, jButton_ActionPlanButton, jButton_ActionPlanFileButton,
			jButton_SelectFileButton;
	private JLabel jLabel_FileName;
	private JFileChooser fileChooser, saveChooser;
	private JTextArea jTextArea_Output;
	private OrderPlan orderPlan;
	private ActionPlan actionPlan;
	private Services Services = new Services();
	private boolean bValidFile;

	public PartB() {
		setTitle("Part B");
		setSize(800, 400);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		addActions();

		for (Component comp : GUIelements()) {
			getContentPane().add(comp);
		}
		pack();

	}

	private void addActions() {

		actionListener_Button = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				switch (arg0.getActionCommand()) {

				case "selectFile":
					fileChooser = new JFileChooser();
					int returnVal = fileChooser.showOpenDialog(rootPane);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						jLabel_FileName.setText(fileChooser.getSelectedFile().getName());
						jButton_OrderPlanButton.setEnabled(true);
						jButton_ActionPlanButton.setEnabled(true);
						jButton_ActionPlanFileButton.setEnabled(true);
					}
					break;

				case "orderPlan":
					createOrderPlan(fileChooser.getSelectedFile());
					break;

				case "actionPlan":
					createActionPlan(fileChooser.getSelectedFile());
					break;

				case "actionPlanFile":
					saveChooser = new JFileChooser();

					int returnVal2 = saveChooser.showSaveDialog(rootPane);
					if (returnVal2 == JFileChooser.APPROVE_OPTION) {
						createActionPlan(fileChooser.getSelectedFile(), saveChooser.getSelectedFile());
					}
				}
			}
		};
	}

	private void createActionPlan(File selectedFile) {
		actionPlan = new ActionPlan(readOrderFile(selectedFile));
		actionPlan.generateActionPlan();
		jTextArea_Output.setText(actionPlan.actionPlanString());
	}

	private void createActionPlan(File selectedFile, File saveFile) {

		if (!saveFile.getAbsolutePath().endsWith(".txt")) {
			saveFile = new File(saveFile + ".txt");
		}

		actionPlan = new ActionPlan(readOrderFile(selectedFile));
		actionPlan.generateActionPlan();
		if (bValidFile) {
			jTextArea_Output.setText(actionPlan.actionPlanString());

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));

				// Split new line to writer new lines
				for (String ln : actionPlan.actionPlanString().split("\n")) {
					bw.write(ln);
					bw.newLine();
				}

				bw.close();
				JOptionPane.showMessageDialog(rootPane, "Action Plan saved to file.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(rootPane, "Error saving file.", "Error", JOptionPane.ERROR);
				e.printStackTrace();
			}
		}
	}

	private void createOrderPlan(File selectedFile) {
		orderPlan = new OrderPlan();
		List<Order> Orders = readOrderFile(selectedFile);
		if (Orders != null) {
		boolean first = true;
		for (Order order : Orders) {
			if (first)
				orderPlan.setDate(order.getDate());
			first = false;
			orderPlan.addNitrogen(order.isbNitrogen() ? 1 : 0);
			orderPlan.addVehicle(order.getService());
			if (order.isbInsurance())
				orderPlan.addInsurance(order.getdValue(), order.getsLaunchID(), order.getsDate());
			if (order.getsOrbit().equalsIgnoreCase("CSO") || order.getsOrbit().equalsIgnoreCase("GTO"))
				orderPlan.addNESA(order.getsLaunchID(), order.getsDate());
			jTextArea_Output.setText(orderPlan.generatePlan());
		}
		}
	}

	private List<Order> readOrderFile(File selectedFile) {
		List<Order> tempOrders = new ArrayList<Order>();
		try {
			// https://stackoverflow.com/a/21974043
			//System.out.println(selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1));
			if (selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1).equalsIgnoreCase("txt")) {
				BufferedReader br = new BufferedReader(new FileReader(selectedFile));

				String sLine;
				while ((sLine = br.readLine()) != null) {
					String[] sLineArr = sLine.split(",");
					Order tempOrder = new Order();
					tempOrder.setsDate(sLineArr[0].trim());
					tempOrder.setsClientName(sLineArr[1].trim());
					tempOrder.setService(Services.getService(sLineArr[2]));
					tempOrder.setsLaunchID(sLineArr[3]);
					tempOrder.setsOrbit(sLineArr[4]);
					tempOrder.setbNitrogen(getBooleanYN(sLineArr[5]));
					tempOrder.setbInsurance(getBooleanYN(sLineArr[6]));
					tempOrder.setdValue(getDouble(sLineArr[7]));
					tempOrder.setsComment((sLineArr.length == 9) ? sLineArr[8] : "");
					tempOrders.add(tempOrder);
				}
				bValidFile= true;

				br.close();
			} else throw new IOException();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(rootPane, "Failed to read file. Please try again.", "FILE ERROR",
					JOptionPane.ERROR_MESSAGE);
			bValidFile = false;
			e.printStackTrace();

		}

		if (bValidFile)
			return tempOrders;
		return null;
	}

	private double getDouble(String string) {
		double tempDouble = 0;

		try {
			tempDouble = Double.parseDouble(string);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(rootPane, "Error reading Insurance Value");
			e.printStackTrace();
		}
		return tempDouble;
	}

	private boolean getBooleanYN(String string) {
		boolean tempBool = string.equalsIgnoreCase("y") ? true : false;
		return tempBool;
	}

	private List<Component> GUIelements() {
		List<Component> lsComp = new ArrayList<Component>();

		jButton_SelectFileButton = new JButton("Select File");
		jButton_SelectFileButton.setActionCommand("selectFile");
		jButton_SelectFileButton.addActionListener(actionListener_Button);
		lsComp.add(jButton_SelectFileButton);

		jLabel_FileName = new JLabel();
		lsComp.add(jLabel_FileName);

		jButton_OrderPlanButton = new JButton("Create Order Plan");
		jButton_OrderPlanButton.setActionCommand("orderPlan");
		jButton_OrderPlanButton.addActionListener(actionListener_Button);
		jButton_OrderPlanButton.setEnabled(false);
		lsComp.add(jButton_OrderPlanButton);

		jButton_ActionPlanButton = new JButton("Create Action Plan");
		jButton_ActionPlanButton.setActionCommand("actionPlan");
		jButton_ActionPlanButton.addActionListener(actionListener_Button);
		jButton_ActionPlanButton.setEnabled(false);
		lsComp.add(jButton_ActionPlanButton);

		jButton_ActionPlanFileButton = new JButton("Create Action Plan File");
		jButton_ActionPlanFileButton.setActionCommand("actionPlanFile");
		jButton_ActionPlanFileButton.addActionListener(actionListener_Button);
		jButton_ActionPlanFileButton.setEnabled(false);
		lsComp.add(jButton_ActionPlanFileButton);

		jTextArea_Output = new JTextArea();
		jTextArea_Output.setEditable(false);
		jTextArea_Output.setRows(10);
		JScrollPane jScrollPane = new JScrollPane(jTextArea_Output);
		lsComp.add(jScrollPane);

		return lsComp;
	}

}
