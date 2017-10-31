import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Order {

	private Date date;
	private String sClientName, sLaunchID, sOrbit, sComment;
	private boolean bNitrogen, bInsurance;
	private double dValue;
	private Service sService;

	private SimpleDateFormat inputDate = new SimpleDateFormat("d/M/yy");
	private SimpleDateFormat outputDate = new SimpleDateFormat("dd/M/yy");

	public Order() {
	}

	public boolean isbInsurance() {
		return bInsurance;
	}

	public void setbInsurance(boolean bInsurance) {
		this.bInsurance = bInsurance;
	}

	public String getsComment() {
		return sComment;
	}

	public void setsComment(String sComment) {
		this.sComment = sComment;
	}

	public boolean isbNitrogen() {
		return bNitrogen;
	}

	public void setbNitrogen(boolean bNitrogen) {
		this.bNitrogen = bNitrogen;
	}

	public String getsOrbit() {
		return sOrbit;
	}

	public void setsOrbit(String sOrbit) {
		this.sOrbit = sOrbit;
	}

	public String getsLaunchID() {
		return sLaunchID;
	}

	public void setsLaunchID(String sLaunchID) {
		this.sLaunchID = sLaunchID;
	}

	public Service getService() {
		return sService;
	}

	public void setService(Service sService) {
		this.sService = sService;
	}

	public String getsClientName() {
		return sClientName;
	}

	public void setsClientName(String sClientName) {
		this.sClientName = sClientName;
	}

	public String getsDate() {
		return outputDate.format(date);

	}

	public Date getDate() {
		return date;
	}

	public void setsDate(String sDate) {
		try {
			this.date = inputDate.parse(sDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Failed to convert date", "Failed to convert date",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public double getdValue() {
		// Round to nearest 1 million

		return Math.ceil(dValue / 1000000) * 1000000;
	}

	public void setdValue(double dValue) {
		this.dValue = dValue;
	}

	public boolean fuelLOX(String sLaunchVehicle) {
		
		return true;
	}

	public boolean fuelRP1(String sLaunchVehicle) {
		
		if(sLaunchVehicle.equalsIgnoreCase("Hawk Heavy") ||
				sLaunchVehicle.equalsIgnoreCase("Hawk-9"))
			return true;
		else return false;
	}
	
	public boolean fuelMethane(String sLaunchVehicle) {
		return (sLaunchVehicle.equalsIgnoreCase("BFR"))?true:false;
	}
}