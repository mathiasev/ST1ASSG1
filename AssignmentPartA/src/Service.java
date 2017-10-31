
public class Service {
	private String sName, sLaunchVehicle;
	private boolean needDrako;
	

	public Service(String sName) {
		this.sName = sName;
	}

	public String getName() {
		return this.sName;
	}

	public String getsLaunchVehicle() {
		return sLaunchVehicle;
	}

	public void setsLaunchVehicle(String sLaunchVehicle) {
		this.sLaunchVehicle = sLaunchVehicle;
	}

	public boolean isNeedDrako() {
		return needDrako;
	}

	public void setNeedDrako(boolean needDrako) {
		this.needDrako = needDrako;
	}
}
