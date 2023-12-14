package drone.monitiring.service;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;

public interface ModelService {

	void addDrone(DroneModel dto);
	
	Drone getDrone(long id);
	
	Drone updateDrone(DroneModel dto);
	
	Drone removeDrone(long id);
	
	void addMedication(MedicationModel dto);
	
	Medication getMedication(long id);
	
	Medication updateMedication(Medication dto);
	
	Medication removeMedication(long id);
	
	void addDelivery(DeliveryModel dto);
	
	Delivery getDelivery(long id);
	
	Delivery updateDelivery(DeliveryModel dto);
	
	Delivery removeDelivery(long id);
	
	
	
}
