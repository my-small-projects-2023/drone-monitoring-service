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

import drone.monitiring.models.*;
import drone.monitiring.service.ModelService;
import jakarta.annotation.PostConstruct;

@Component
public class RandomDBCreation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(RandomDBCreation.class);
	@Value("${spring.jpa.hibernate.ddl-auto: create}")
	String ddlAutoProp;
	
	@Autowired
	private ModelService service;
	
	private static final int DRONES_AMOUNT = 10;
	private static final int MIN_WEIGHT_LIMIT = 260;
	private static final int MAX_WEIGHT_LIMIT = 500;

	private static final String[] MEDICATION_NAMES = {"Tranquilizra", "Cardiofluxin", "Neurocalmex", 
			"Dermaviva", "SinuRelief", "OsteoFlexidol", "PulmoEase", "DigestiCare", "OptiVisionix", 
			"ImmunoGuardia"};
	private static final int MIN_WEIGHT = 20;
	private static final int MAX_WEIGHT = 220;
	private static final int MIN_CODE = 1000;
	private static final int MAX_CODE = 9999;
	private static final int MIN_MEDICATIONS_AMOUNT = 1;
	private static final int MAX_MEDICATIONS_AMOUNT = 2;
	
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
		IntStream.range(1, DRONES_AMOUNT).forEach(e -> {
			Map<Long, Integer> medications = new HashMap<>();
			medications.put((long)getRandomNumber(1, MEDICATION_NAMES.length), getRandomNumber(MIN_MEDICATIONS_AMOUNT, MAX_MEDICATIONS_AMOUNT));
			medications.put((long)getRandomNumber(1, MEDICATION_NAMES.length), getRandomNumber(MIN_MEDICATIONS_AMOUNT, MAX_MEDICATIONS_AMOUNT));
			medications.put((long)getRandomNumber(1, MEDICATION_NAMES.length), getRandomNumber(MIN_MEDICATIONS_AMOUNT, MAX_MEDICATIONS_AMOUNT));
			service.addDelivery(new DeliveryModel((long)0, (long)e, medications));
		});
		
	}

	private void addMedications() {
		Stream.of(MEDICATION_NAMES).forEach(e -> {
			service.addMedication(new MedicationModel(0, e, getRandomNumber(MIN_WEIGHT, MAX_WEIGHT),
					"1" + getRandomNumber(MIN_CODE, MAX_CODE),"http://..."));
		});
		
	}

	private void addDrons() {
		IntStream.range(0, DRONES_AMOUNT).forEach(e -> {
			service.addDrone(new DroneModel(0, "SEar" + 1,
					getRandomNumber(MIN_WEIGHT_LIMIT, MAX_WEIGHT_LIMIT)));
		});
		
	}
	
	private int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	

}
