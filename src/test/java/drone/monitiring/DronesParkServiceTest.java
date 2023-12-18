package drone.monitiring;

import static org.junit.jupiter.api.Assertions.*;
//import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.repo.*;
import drone.monitiring.service.DronesParkService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DronesParkServiceTest {
	
	static Logger LOG = LoggerFactory.getLogger(DronesParkServiceTest.class);
	@Autowired
	DronesParkService service;
	@Autowired
	DroneRepository dronRepo;
	@Autowired
	MedicationRepository medicationRepo;
	@Autowired
	DeliveryRepository deliveryRepo;
	
	
	private static final int WEIGHT_LIMIT = 450;
	private static final int BATATTERY_CAPACITY = 100;
	private static final Integer MEDICATION_AMOUNT = 2;
	private static final long DRONE_ID = 1;
	private static final long MEDICATION_ID = 1;
	
	DroneModel droneDto = new DroneModel(1, "AAHW2", WEIGHT_LIMIT);
	MedicationModel medicationDto = new MedicationModel(1, "m-name", 50, "m-code", "m-image");

	@Test
	@Order(1)
	void registerDroneTest() {
		LOG.debug("***TEST register new drone");
		Drone drone = service.registerDrone(droneDto);
		assertEquals(DRONE_ID, drone.getId());
		assertEquals(droneDto.serialNumber, (drone.getSerialNumber()));
	}
	
	@Test
	@Order(2)
	void getBatteryLevelTest() {
		LOG.debug("***TEST get battery capacity");
		int bLevel = service.getBatteryLevel(DRONE_ID);
		assertEquals(BATATTERY_CAPACITY, bLevel);
	}
	
	@Test
	@Order(3)
	void getAvailableDrones() {
		LOG.debug("***TEST get available drones");
		Drone[] expected = {dronRepo.findById(DRONE_ID).orElse(null)};
		Drone[] drones = service.getAvailableDrones();
		assertEquals(expected[0].getModel(), drones[0].getModel());
		assertEquals(expected[0].getId(), drones[0].getId());
		assertEquals(expected.length, drones.length);
	}
	
	@Test
	@Order(5)
	void addMedicationTest() {
		LOG.debug("***TEST add medication");
		Medication medication = service.addMedication(medicationDto);
		assertEquals(MEDICATION_ID, medication.getId());
		assertEquals(medicationDto.name, (medication.getName()));
	}
	
	@Test
	@Order(5)
	void loadDroneTest() {
		LOG.debug("***TEST load medications");
		Map<Long, Integer> medications = new HashMap<>();
		medications.put(MEDICATION_ID, MEDICATION_AMOUNT);
		assertTrue(service.loadDrone(medications));
		assertFalse(service.loadDrone(medications));
	}
	
	@Test
	@Order(6)
	void getLoadedMedications() {
		LOG.debug("***TEST get loaded medications");
		Medication[] expected = {medicationRepo.findById(MEDICATION_ID).orElse(null)};
		Medication[] medications = service.getLoadedMedications(DRONE_ID);
		assertEquals(expected[0].getName(), medications[0].getName());
		assertEquals(expected[0].getId(), medications[0].getId());
		assertEquals(expected.length, medications.length);
	}
	
	


}
