package drone.monitiring.service;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.repo.*;

@Service
@Transactional(readOnly = true)
public class DronesParkServiceImpl implements DronesParkService {
	
	private static Logger LOG = LoggerFactory.getLogger(DronesParkServiceImpl.class);
	private static final int BATATTERY_CAPACITY = 100;
	private static final int MIN_BATTERY_CAPACITY = 25;
	
	private static Map<Long, List<Medication>> loadedMedications = new HashMap<>();
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;

	@Override
	@Transactional
	public Drone registerDrone(DroneModel dto) {
		
		Drone drone = new Drone(dto.serialNumber, Model.getModel(dto.weightLimit), 
				dto.weightLimit, BATATTERY_CAPACITY, State.IDLE.name());
		drone = droneRepo.save(drone);
		LOG.debug("*park-service* register new drone: {}", drone.toString());
		return drone;
	}

	@Override
	@Transactional
	public boolean loadDrone(Map<Long, Integer> medications) {
		List<Medication> medicationList = medicationRepo.findAllById(medications.keySet());
		LOG.debug("*park-service* given medications amount: {}, found medications amount: {}", 
				medications.size(), medicationList.size());
		if(medications.size() != medications.size()) {
			LOG.error("*park-service* some of given medications was not found");
			//TODO
			//throw new Exception();
		}
		
		int totalWeight = medicationList.stream()
		        .mapToInt(e -> medications.get(e.getId()) * e.getWeight())
		        .sum();
		LOG.debug("*park-service* total weight for given medications: {}", totalWeight);
		Drone drone = droneRepo.getAvailableDrone(totalWeight, MIN_BATTERY_CAPACITY, State.IDLE.name());
		if(drone == null) {
			LOG.error("*park-service* there is no available drones for medication weight: {}", 
					totalWeight);
			//TODO
			// waiting list
			return false;
		} 
		drone.setState(State.LOADING.name());
		droneRepo.save(drone);
		//TOFIX
		loadedMedications.put(drone.getId(), medicationList);
		
		
		List<DeliveryItem> deliveryItems = new ArrayList<>();
		Delivery delivery = deliveryRepo.save(new Delivery(totalWeight, drone, deliveryItems));
		medicationList.stream().forEach(e -> {
			int amount = medications.get(e.getId());
			deliveryItems.add(new DeliveryItem(e, delivery, amount));
		});	
		delivery.setMedications(deliveryItems);
		deliveryRepo.save(delivery);
		LOG.info("*park-service* medications was loaded to drone");
		drone.setState(State.LOADED.name());
		droneRepo.save(drone);
		return true;
	}
	

	@Override
	public List<Medication> getLoadedMedications(long droneId) {
		List<Medication> medications= loadedMedications.get(droneId);
		LOG.debug("*park-service* droan: {}, loadedMedications: {}", droneId, medications);
		return medications;
	}

	@Override
	public Drone[] getAvailableDrones() {
		Drone[] drones = droneRepo.findAvailableDrones(MIN_BATTERY_CAPACITY, State.IDLE.name());
		LOG.debug("*park-service* found available drones: {}", drones.toString());
		return drones;
	}

	@Override
	public int getBatteryLevel(long droneId) {
		
		Drone drone = droneRepo.findById(droneId).orElse(null);
		if(drone == null) {
			LOG.error("*park-service* drone with id: {} was not found", droneId);
			return -1;
		}
		LOG.debug("*park-service* found drone: {}", drone.toString());
		return drone.getBatteryCapacity();
	}

	
}
