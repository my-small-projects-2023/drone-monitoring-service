package drone.monitiring.service;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.repo.*;

@Service
@Transactional(readOnly = false)
public class DronesParkServiceImpl implements DronesParkService {
	
	private static Logger LOG = LoggerFactory.getLogger(DronesParkServiceImpl.class);
	private static final int BATATTERY_CAPACITY = 100;
	private static final int MIN_BATTERY_CAPACITY = 25;
	private static final int MAX_WEIGHT = 500;
	private Queue<Map<Long, Integer>> queue = new LinkedList<>();
	
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;
	@Autowired
	private DroneImitatorService imitatorService;

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
		if(totalWeight > MAX_WEIGHT) {
			LOG.error("*park-service* weight: {} is not allowed for service, max weight is: {}", 
					totalWeight, MAX_WEIGHT);
			//TODO
			// waiting list
			return false;
		}
		Drone drone = droneRepo.getAvailableDrone(totalWeight, MIN_BATTERY_CAPACITY, State.IDLE.name());
		if(drone == null) {
			LOG.error("*park-service* there is no available drones for medication weight: {}", 
					totalWeight);
			setToWaitingList(medications);
			return false;
		} 
		drone.setState(State.LOADING.name());
		droneRepo.save(drone);	
		
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
		imitatorService.changeState(drone);
		return true;
	}
	

	@Override
	public Medication[] getLoadedMedications(long droneId) {
		Medication[] medications = medicationRepo.getLastDelivery(droneId);
		LOG.debug("*park-service* droan: {}, loadedMedications: {}", droneId, Arrays.toString(medications));
		return medications;
	}

	@Override
	public Drone[] getAvailableDrones() {
		Drone[] drones = droneRepo.findAvailableDrones(MIN_BATTERY_CAPACITY, State.IDLE.name());
		LOG.debug("*park-service* found available drones: {}", Arrays.toString(drones));
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

	@Override
	@Transactional
	public Medication addMedication(MedicationModel dto) {
		Medication medication = medicationRepo
				.save(new Medication(dto.name, dto.weight, dto.code, dto.imageUrl));
		LOG.debug("*park-service* new medication was added: {}", medication.toString());
		return medication;
	}
	
	
	
//********* Waiting list service
	
	public void setToWaitingList(Map<Long, Integer> medications) {
		LOG.info("*waiting-list* medications with id: {}, was added to the waiting lint", medications.keySet());
		queue.add(medications);
		
	}

	@Scheduled(fixedDelay = 15000)
	public void executeFromList() {
		if(queue.size() != 0 && !queue.isEmpty()) {
			Map<Long, Integer> medications = queue.poll();
			if(loadDrone(medications)) {
				LOG.debug("*waiting-list* medications from waiting list was loaded to drone");
			} else {
				LOG.debug("*waiting-list* medications from waiting list was not loaded to drone");
			}
		} else {
			LOG.debug("*waiting-list* waiting list is empty");
		}
		
	}

	
}
