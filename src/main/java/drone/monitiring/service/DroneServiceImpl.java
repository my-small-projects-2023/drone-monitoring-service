package drone.monitiring.service;

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
	public boolean loadDrone(MedicationModel[] medications) {
		
		return false;
	}

	@Override
	public Medication[] getLoadedMedications(long droneId) {
		
		return null;
	}

	@Override
	public Drone[] getAvailableDrones() {
		
		return null;
	}

	@Override
	public int getBatteryLevel(long droneId) {
		
		return 0;
	}

	
}
