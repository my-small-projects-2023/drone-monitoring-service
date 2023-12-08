package drone.monitiring;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import drone.monitiring.entities.Delivery;
import drone.monitiring.entities.Drone;
import drone.monitiring.entities.Medication;
import drone.monitiring.repo.*;
import jakarta.annotation.PostConstruct;

@Component
public class RandomDBCreation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(RandomDBCreation.class);
	@Value("${spring.jpa.hibernate.ddl-auto: create}")
	String ddlAutoProp;
	
	@Autowired
	private DroneRepository droneRepo;
	@Autowired
	private MedicationRepository medicationRepo;
	@Autowired
	private DeliveryRepository deliveryRepo;
	
	
	// drone
	private static final int DRONES_AMOUNT = 10;
	private static final String[] DRONE_MODEL = {"Lightweight", "Middleweight", "Cruiserweight", "Heavyweight"};
	private static final String[] DRONE_STATE = {"IDLE", "LOADING", "LOADED", "DELIVERING", "DELIVERED", "RETURNING"};
	private static final int MIN_WEIGHT_LIMIT = 260;
	private static final int MAX_WEIGHT_LIMIT = 500;
	private static final int MIN_BATTERY_CAPACITY = 25;
	private static final int MAX_BATTERY_CAPACITY = 100;
	// medication
	private static final String[] MEDICATION_NAMES = {"Tranquilizra", "Cardiofluxin", "Neurocalmex", 
			"Dermaviva", "SinuRelief", "OsteoFlexidol", "PulmoEase", "DigestiCare", "OptiVisionix", 
			"ImmunoGuardia"};
	private static final int MIN_WEIGHT = 20;
	private static final int MAX_WEIGHT = 220;
	private static final int MIN_CODE = 1000;
	private static final int MAX_CODE = 9999;
	
	@PostConstruct
	void createDB() {
		if(ddlAutoProp.equals("create")) {
			addDrons();
			addMedications();
			addDeliveries();
			LOG.info("*random db* new db was created with drones amount: {}", DRONES_AMOUNT);
		} else {
			LOG.warn("*random db* new db was not created");
		}
	}

	private void addDeliveries() {
		IntStream.range(0, DRONES_AMOUNT - 1).forEach(e -> {
			Drone drone = droneRepo.findById((long) e).orElse(null);
			Medication medication = medicationRepo.findById((long)e).orElse(null);
			//TODO
		});
		
	}


	private void addMedications() {
		Stream.of(MEDICATION_NAMES).forEach(e -> {
			medicationRepo.save(new Medication(e, getRandomNumber(MIN_WEIGHT, MAX_WEIGHT),
					"1" + getRandomNumber(MIN_CODE, MAX_CODE),"http://..."));
		});
	}

	private void addDrons() {
		IntStream.range(0, DRONES_AMOUNT).forEach(e -> {
			droneRepo.save(new Drone("SEar" + 1, DRONE_MODEL[getRandomNumber(0, DRONE_MODEL.length - 1)],
					getRandomNumber(MIN_WEIGHT_LIMIT, MAX_WEIGHT_LIMIT), getRandomNumber(MIN_BATTERY_CAPACITY, MAX_BATTERY_CAPACITY),
					DRONE_STATE[getRandomNumber(0, DRONE_STATE.length - 1)]));
		});
		
	}
	
	private int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	

}
