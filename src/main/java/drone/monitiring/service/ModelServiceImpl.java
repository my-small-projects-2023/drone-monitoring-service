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
	private static final int BATATTERY_CAPACITY = 100;
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;
	
	@Override
	@Transactional
	public void addDrone(DroneModel dto) {
		
		droneRepo.save(new Drone(dto.serialNumber, Model.getModel(dto.weightLimit), 
				dto.weightLimit, BATATTERY_CAPACITY, State.IDLE.name()));
	}
	
	@Override
	public Drone getDrone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Drone updateDrone(DroneModel dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Drone removeDrone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addMedication(MedicationModel dto) {
		
		medicationRepo.save(new Medication(dto.name, dto.weight, dto.code, dto.imageUrl));
	}
	
	@Override
	public Medication getMedication(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Medication updateMedication(Medication dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Medication removeMedication(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addDelivery(DeliveryModel dto) {
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
	}

	@Override
	public Delivery getDelivery(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Delivery updateDelivery(DeliveryModel dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Delivery removeDelivery(long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
