package drone.monitiring.service;

import java.util.Map;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;

public interface DronesParkService {

	Drone registerDrone(DroneModel dto);
	
	boolean loadDrone(Map<Long, Integer> medications);
	
	Medication[] getLoadedMedications(long droneId);
	
	Drone[] getAvailableDrones();
	
	int getBatteryLevel(long droneId);
	
	Medication addMedication(MedicationModel dto);
	
}
