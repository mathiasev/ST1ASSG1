import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPlan {

	private int iNitrogenQty = 0, iLOX = 0, iRP1 = 0, iMethane = 0, iHawk9Count = 0, iHawkHeavyCount = 0, iBFRCount = 0,
			iDrakoCount = 0;
	private List<String> Insurance = new ArrayList<String>();
	private List<String> NESA = new ArrayList<String>();
	private Date date;
	private SimpleDateFormat planDate = new SimpleDateFormat("MMMM yyyy");

	public OrderPlan() {

	}

	public void setDate(Date date) {

		this.date = date;
	}

	public void addNitrogen(int quantity) {
		this.iNitrogenQty += quantity * 3;
	}

	public void addVehicle(Service sService) {

		addFuel(sService.getsLaunchVehicle());

		iDrakoCount += sService.isNeedDrako() ? 1 : 0;

	}

	private void addFuel(String sLaunchVehicle) {
		switch (sLaunchVehicle) {
		case ("Hawk-9"):
			this.iHawk9Count++;
			this.iLOX += 275000;
			this.iRP1 += 120000;
			break;

		case ("Hawk Heavy"):
			this.iHawkHeavyCount++;
			this.iLOX += 810000;
			this.iRP1 += 353450;
			break;

		case ("BFR"):
			this.iBFRCount++;
			this.iLOX += 4280000;
			this.iMethane += 1870000;
			break;
		}
	}

	public void addInsurance(double getValue, String LaunchID, String Date) {
		String sString = String.format("Arrange $%,2.2f insurance for %s on %s", getValue, LaunchID, Date);
		Insurance.add(sString);
	}

	public void addNESA(String LaunchID, String Date) {
		String sString = String.format("Arrange NESA tracking for %s on %s", LaunchID, Date);
		NESA.add(sString);
	}

	public String generatePlan() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Order Plan for %s\n", planDate.format(this.date)));
		for (String s : Insurance) {
			sb.append(s + "\n");
		}
		for (String s : NESA) {
			sb.append(s + "\n");
		}

		if (iHawk9Count > 0)
			sb.append(String.format("Order %,d %s launch vehicles\n", iHawk9Count, "Hawk-9"));
		if (iHawkHeavyCount > 0)
			sb.append(String.format("Order %,d %s launch vehicles\n", iHawkHeavyCount, "Hawk Heavy"));
		if (iBFRCount > 0)
			sb.append(String.format("Order %,d %s launch vehicles\n", iBFRCount, "BFR"));
		if (iDrakoCount > 0)
			sb.append(String.format("Order %,d %s launch vehicles\n", iDrakoCount, "Drako"));

		if (iNitrogenQty > 0)
			sb.append(String.format("Order %,d cylinders of Nitrogen\n", iNitrogenQty));
		if (iLOX > 0)
			sb.append(String.format("Order %,dkg of LOX\n", iLOX));
		if (iRP1 > 0)
			sb.append(String.format("Order %,dkg of RP1\n", iRP1));
		if (iMethane > 0)
			sb.append(String.format("Order %,dkg of Methane\n", iMethane));
		return sb.toString();
	}

}
