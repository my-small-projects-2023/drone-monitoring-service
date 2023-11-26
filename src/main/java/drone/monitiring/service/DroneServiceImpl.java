package drone.monitiring.service;

import drone.monitiring.entities.Drone;
import drone.monitiring.entities.Medication;
import drone.monitiring.models.DroneModel;
import drone.monitiring.models.MedicationModel;

public class DroneServiceImpl implements DroneService {

	@Override
	public Drone registerDrone(DroneModel drone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadDrone(MedicationModel[] medications) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Medication[] getLoadedMedications(long sroneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drone[] getAvailableDrones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBatteryLevel(long droneId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
