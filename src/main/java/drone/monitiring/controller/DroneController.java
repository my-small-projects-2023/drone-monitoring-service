package drone.monitiring.controller;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.service.DroneServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("park")
@Validated
public class DroneController {
	
	private static Logger LOG = LoggerFactory.getLogger(DroneController.class);
	@Autowired
	private DroneServiceImpl service;

	@PostMapping("/drone")
	public String registerNewDrone(@RequestBody @Valid DroneModel drone) {
		LOG.info("*** request to register a new drone");
		
		return null;
	}
	
	@PostMapping("/drone/load")
	public String loadMedicationtoDrone(@RequestParam ("medications") @Valid List<MedicationModel> medications) {
		LOG.info("*** request to load medication to drone");
		
		return null;
	}
	
	@GetMapping("/medications/loaded/{droneId}")
	public Medication[] getLoadedMedications(long droneId) {
		LOG.info("*** request to get loadet medications from drone: {droneId}");
		
		return null;
	}
	
	@GetMapping("/drones/available")
	public Drone[] getAvailableDrones() {
		LOG.info("*** request to get available drons in the park");
		
		return null;
	}
	
	@GetMapping("/drone/battery")
	public String getBatteryLevel(long droneId) {
		LOG.info("*** request to get battery level of drone: {droneId}");
		
		return null;
	}
}
