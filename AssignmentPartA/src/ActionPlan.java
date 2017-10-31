import java.util.Date;
import java.util.List;

public class ActionPlan {

	private List<Order> Orders;
	private Date prevDate;
	private StringBuilder sb = new StringBuilder();

	public ActionPlan(List<Order> Orders) {
		this.Orders = Orders;
	}

	public String generateActionPlan() {

		/*
		 * TODO Generate plan differently,
		 * 
		 * # Organise into Days # For each order Split into days Test if more than two
		 * per days -> produce error
		 * 
		 * # Organise Plans # For each day if first is BFR, allocate KSC else allocate
		 * LC40
		 * 
		 */

		for (Order order : Orders) {
			String pad = "";
			boolean KSCfree = true;
			if (!order.getDate().equals(prevDate)) {
				// MORNING PLAN
				prevDate = order.getDate();
				// Start Roll Out

				if (order.getService().getsLaunchVehicle().equalsIgnoreCase("BFR")) {
					pad = "KSC";
					KSCfree = false;
				} else {
					pad = "LC40";
					KSCfree = true;
				}
				sb.append(String.format("%s 06:00	Start vehicle rollout of %s on pad %s for %s\n", order.getsDate(),
						order.getService().getsLaunchVehicle(), pad, order.getsLaunchID()));

				// Fuel
				if (order.fuelLOX(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 07:00	Fuel rocket with LOX for %s\n", order.getsDate(),
							order.getsLaunchID()));
				if (order.fuelRP1(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 08:00	Fuel rocket with RP1 for %s\n", order.getsDate(),
							order.getsLaunchID()));
				if (order.fuelMethane(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 08:00	Fuel rocket with Methane for %s\n", order.getsDate(),
							order.getsLaunchID()));

				if (order.isbNitrogen())
					sb.append(String.format("%s 10:00	Nitrogen flush %s\n", order.getsDate(), order.getsLaunchID()));

				sb.append(String.format("%s 10:50	GO/NOGO for %s\n", order.getsDate(), order.getsLaunchID()));

				sb.append(String.format("%s 11:00	Launch %s for %s %s\n", order.getsDate(), order.getsLaunchID(),
						order.getsClientName(), order.getsComment()));

			} else {
				// AFTERNOON PLAN
				pad = (KSCfree) ? "KSC" : "LC40";
				if (order.getService().getsLaunchVehicle().equalsIgnoreCase("BFR")) {
					pad = "KSC";
					KSCfree = false;
				} else {
					pad = "LC40";
					KSCfree = true;
				}

				sb.append(String.format("%s 11:30	Start vehicle rollout of %s on pad %s for %s\n", order.getsDate(),
						order.getService().getsLaunchVehicle(), pad, order.getsLaunchID()));

				// Fuel
				if (order.fuelLOX(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 12:30	Fuel rocket with LOX for %s\n", order.getsDate(),
							order.getsLaunchID()));
				if (order.fuelRP1(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 14:00	Fuel rocket with RP1 for %s\n", order.getsDate(),
							order.getsLaunchID()));
				if (order.fuelMethane(order.getService().getsLaunchVehicle()))
					sb.append(String.format("%s 14:00	Fuel rocket with Methane for %s\n", order.getsDate(),
							order.getsLaunchID()));

				if (order.isbNitrogen())
					sb.append(String.format("%s 15:00	Nitrogen flush %s\n", order.getsDate(), order.getsLaunchID()));

				sb.append(String.format("%s 15:30	GO/NOGO for %s\n", order.getsDate(), order.getsLaunchID()));

				sb.append(String.format("%s 15:40	Launch %s for %s %s\n", order.getsDate(), order.getsLaunchID(),
						order.getsClientName(), order.getsComment()));

			}
		}
		return sb.toString();
	}
}
