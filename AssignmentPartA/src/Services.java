import java.util.ArrayList;
import java.util.List;


public class Services {

	private List<Service> ServiceList = new ArrayList<Service>();

	public Services() {
		Service ORB1 = new Service("ORB1");
		ORB1.setsLaunchVehicle("Hawk-9");
		ORB1.setNeedDrako(false);
		
		Service ORBH = new Service("ORBH");
		ORBH.setsLaunchVehicle("Hawk Heavy");
		ORBH.setNeedDrako(false);
		
		Service ORBVH = new Service("ORBVH");
		ORBVH.setsLaunchVehicle("BFR");
		ORBVH.setNeedDrako(false);
		
		Service ISS5 = new Service("ISS5");
		ISS5.setsLaunchVehicle("Hawk-9");
		ISS5.setNeedDrako(true);
		
		Service MOON2 = new Service("MOON2");
		MOON2.setsLaunchVehicle("Hawk Heavy");
		MOON2.setNeedDrako(true);
		
		Service MARS1 = new Service("MARS1");
		MARS1.setsLaunchVehicle("BFR");
		MARS1.setNeedDrako(false);
		
		Service MARS2 = new Service("MARS2");
		MARS2.setsLaunchVehicle("BFR");
		MARS2.setNeedDrako(false);
		
		
		ServiceList.add(ORB1);
		ServiceList.add(ORBH);
		ServiceList.add(ORBVH);
		ServiceList.add(ISS5);
		ServiceList.add(MOON2);
		ServiceList.add(MARS1);
		ServiceList.add(MARS2);
	}
	
	
	public Service getService(String string) {
		Service tempService = null;
		for(Service sService : ServiceList) {
		if(sService.getName().equals(string))
			return sService;
		}
		return tempService;
	}

}
