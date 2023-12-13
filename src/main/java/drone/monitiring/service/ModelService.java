package drone.monitiring.service;

import drone.monitiring.models.*;

public interface ModelService {

	boolean addDrone(DroneModel dto);
	
	boolean addMedication(MedicationModel dto);
	
	boolean addDelivery(DeliveryModel dto);
}
