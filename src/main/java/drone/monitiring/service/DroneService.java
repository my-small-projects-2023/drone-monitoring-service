package drone.monitiring.service;

import java.util.List;

import drone.monitiring.entities.Drone;
import drone.monitiring.entities.Medication;
import drone.monitiring.models.DroneModel;
import drone.monitiring.models.MedicationModel;

public interface DroneService {

	Drone registerDrone(DroneModel dto);
	
	boolean loadDrone(List<MedicationModel> medications);
	
	List<Medication> getLoadedMedications(long droneId);
	
	Drone[] getAvailableDrones();
	
	int getBatteryLevel(long droneId);
	
}
