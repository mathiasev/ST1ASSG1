import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ActionPlan {

	private List<Order> Orders;
	private static String[] AM_TIMES = { " 6:00", " 7:00", " 8:00", "10:00", "10:50", "11:00" };
	private static String[] PM_TIMES = { "11:30", "12:30", "14:00", "15:00", "15:30", "15:40" };
	private List<String> lines = new ArrayList<String>();

	public ActionPlan(List<Order> Orders) {
		this.Orders = Orders;
	}

	public void generateActionPlan() {
		List<List<Order>> Days = new ArrayList<List<Order>>();
		List<Order> tempDay = new ArrayList<Order>();
		
		// Break all orders into days
		tempDay.add(Orders.get(0));
		for (int i = 1; i < Orders.size(); i++) {
			if (Orders.get(i).getDate().equals(Orders.get(i-1).getDate())) {
				tempDay.add(Orders.get(i));
			} else {
				Days.add(tempDay);
				tempDay = new ArrayList<Order>();
				tempDay.add(Orders.get(i));
			}
		}

		// Loop through each day
		for (List<Order> day : Days) {
			System.out.println(day.get(0).getsDate());
			try {
				// If more than one two launches show error
				if (day.size() > 2) {
					JOptionPane.showMessageDialog(null, "More than 2");
					throw new MoreThan2Exception();
				}

				// Otherwise loop through each
				else {

					// Check if more than one launch
					if (day.size() > 1) {

						// If both are BFRs, throw exception, and add first
						if (day.get(0).getService().getsLaunchVehicle().equalsIgnoreCase("BFR")
								&& day.get(1).getService().getsLaunchVehicle().equalsIgnoreCase("BFR")) {

							// Throw exception
							throw new TwoBFRException(day.get(0),day.get(1));
						}

						// If first is a BFR, then use KSC pad
						// and add second to LC40
						else if (day.get(0).getService().getsLaunchVehicle().equalsIgnoreCase("BFR")) {
							addLaunch(day.get(0), "KSC", "AM");
							addLaunch(day.get(1), "LC40", "PM");
						}

						// Otherwise add first to LC40 and second to KSC
						else {
							addLaunch(day.get(0), "LC40", "AM");
							addLaunch(day.get(1), "KSC", "PM");
						}
					}

					// Single launch (use default LC40 pad and morning time)
					else {
						addLaunch(day.get(0), "LC40", "AM");
					}
				}
			}

			// Show error message when two BFRs launched on same day
			catch (TwoBFRException e) {
				JOptionPane.showMessageDialog(null, "2 BFR on same day");
			}

			// Show error message when more than two launches on a day
			catch (MoreThan2Exception e) {
				JOptionPane.showMessageDialog(null, "2 BFR on same day");
			}

			// End of day
			System.out.println("\n\n");
		}
	}

	private void addLaunch(Order order, String sLaunchPad, String sTime) {
		String[] times = sTime.equalsIgnoreCase("AM") ? AM_TIMES : PM_TIMES;

		lines.add(String.format("%s %s	Start vehicle rollout of %s on pad %s for %s", order.getsDate(), times[0],
				order.getService().getsLaunchVehicle(), sLaunchPad, order.getsLaunchID()));

		lines.add(String.format("%s %s	Fuel rocket with LOX for %s", order.getsDate(), times[1],
				order.getsLaunchID()));

		if (order.fuelRP1(order.getService().getsLaunchVehicle()))
			lines.add(String.format("%s %s	Fuel rocket with RP1 for %s", order.getsDate(), times[2],
					order.getsLaunchID()));

		if (order.fuelMethane(order.getService().getsLaunchVehicle()))
			lines.add(String.format("%s %s	Fuel rocket with Methane for %s", order.getsDate(), times[2],
					order.getsLaunchID()));

		if (order.isbNitrogen())
			lines.add(String.format("%s %s	Nitrogen flush %s", order.getsDate(), times[3], order.getsLaunchID()));

		lines.add(String.format("%s %s	GO/NOGO for %s", order.getsDate(), times[4], order.getsLaunchID()));

		lines.add(String.format("%s %s	Launch %s for %s %s", order.getsDate(), times[5], order.getsLaunchID(),
				order.getsClientName(), order.getsComment()));
	}

	public String actionPlanString() {
		if (!lines.isEmpty()) {
			String sLine = "";
			for (String s : lines) {
				sLine += s;
				sLine += "\n";
			}
			return sLine;
		}
		return null;
	}
}
