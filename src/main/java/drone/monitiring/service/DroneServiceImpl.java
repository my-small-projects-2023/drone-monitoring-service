package drone.monitiring.service;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.repo.*;

@Service
@Transactional(readOnly = true)
public class DroneServiceImpl implements DroneService {
	
	private static Logger LOG = LoggerFactory.getLogger(DroneServiceImpl.class);
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;

	@Override
	@Transactional
	public Drone registerDrone(DroneModel dto) {
		
		Drone drone = new Drone(dto.serialNumber, dto.model, dto.weigthLimit, dto.batteryCapacity, dto.state);
		droneRepo.save(drone);
		return drone;
	}

	@Override
	@Transactional
	public boolean loadDrone(List<MedicationModel> medications) {
		List<Medication> medicationsList = medicationRepo.findAllById(medications.stream().map(e -> e.getId()).toList());
		if(medicationsList.size() == 0) {
			return false;
		}
		int medicationsWeigth = medicationsList.stream().mapToInt(e -> e.getWeight()).sum();
		
		Drone drone = droneRepo.findAvailableDrone(medicationsWeigth);
		if(drone == null) {
			return false;
		}

		deliveryRepo.save(new Delivery(medicationsWeigth, drone, medicationsList));
		return true;
	}

	@Override
	public List<Medication> getLoadedMedications(long droneId) {
		Delivery delivery = deliveryRepo.findLastDeliveryByDroneId(droneId);
		return delivery.getMedications();
	}

	@Override
	public Drone[] getAvailableDrones() {
		
		return droneRepo.findAvailableDrones();
	}

	@Override
	public int getBatteryLevel(long droneId) {
		
		Drone drone = droneRepo.findById(droneId).orElse(null);
		if(drone == null) {
			return -1;
		}
		//TODO
		return drone.getBatteryCapacity();
	}

	
}
