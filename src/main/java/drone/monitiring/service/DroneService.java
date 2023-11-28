package drone.monitiring.service;

import drone.monitiring.entities.Drone;
import drone.monitiring.entities.Medication;
import drone.monitiring.models.DroneModel;
import drone.monitiring.models.MedicationModel;

public interface DroneService {

	Drone registerDrone(DroneModel dto);
	
	boolean loadDrone(MedicationModel[] medications);
	
	Medication[] getLoadedMedications(long droneId);
	
	Drone[] getAvailableDrones();
	
	int getBatteryLevel(long droneId);
	
}
