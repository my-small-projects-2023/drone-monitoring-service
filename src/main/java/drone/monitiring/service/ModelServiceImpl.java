package drone.monitiring.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.repo.*;


@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService{

	private static Logger LOG = LoggerFactory.getLogger(ModelServiceImpl.class);
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;
	
	@Override
	@Transactional
	public boolean addDrone(DroneModel dto) {
		droneRepo.save(new Drone(dto.serialNumber, dto.model, dto.weigthLimit, 
				dto.batteryCapacity, dto.state));
		return true;
	}

	@Override
	@Transactional
	public boolean addMedication(MedicationModel dto) {
		medicationRepo.save(new Medication(dto.name, dto.weight, dto.code, dto.imageUrl));
		return true;
	}

	@Override
	@Transactional
	public boolean addDelivery(DeliveryModel dto) {
		boolean res = true;
		Drone drone = droneRepo.findById(dto.droneId).orElse(null);
		if(drone == null) {
			LOG.debug("*********** drone == null");
			//TODO
			//throw new Exception();
		}
		int[] weight = new int[] {0};
		List<Medication> medications = medicationRepo.findAllById(dto.medications.keySet());
		if(medications.size() != dto.medications.size()) {
			LOG.debug("*********** some of given medications does not exist");
			//TODO
			//throw new Exception();
		}
		medications.stream().forEach(e -> {
			int amount = dto.medications.get(e.getId());
			weight[0] += e.getWeight() * amount;
		});
		
		List<DeliveryItem> deliveryItems = new ArrayList<>();
		if(weight[0] <= drone.getWeigthLimit()) {
			Delivery delivery = deliveryRepo.save(new Delivery(weight[0], drone, deliveryItems));
			LOG.debug("***weight: {}", weight[0]);
			LOG.debug("*** delivery: {}", delivery.toString());
			medications.stream().forEach(e -> {
				int amount = dto.medications.get(e.getId());
				deliveryItems.add(new DeliveryItem(e, delivery, amount));
			});	
			delivery.setMedications(deliveryItems);
			deliveryRepo.save(delivery);
		}
		return res;
	}



}
