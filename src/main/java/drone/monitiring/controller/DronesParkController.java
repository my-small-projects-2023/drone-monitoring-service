package drone.monitiring.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.service.DronesParkService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("park")
@Validated
public class DronesParkController {
	
	private static Logger LOG = LoggerFactory.getLogger(DronesParkController.class);
	@Autowired
	private DronesParkService service;

	@PostMapping("/drone")
	public Drone registerNewDrone(@RequestBody @Valid DroneModel drone) {
		LOG.info("*park-controller* request to register a new drone");
		return service.registerDrone(drone);
	}
	
	@PostMapping("/drone/load")
	public String loadMedicationtoDrone(@RequestBody @Valid Map<Long, Integer> medications) {
		LOG.info("*park-controller* request to load medication to drone");
		String res = "medications was not loaded to drone";
		boolean isLoaded = service.loadDrone(medications);
		if(isLoaded) {
			res = "medications was loaded to a drone";
		}
		return res;
	}
	
	@GetMapping("/medications/loaded/")
	public List<Medication> getLoadedMedications(@RequestParam long droneId) {
		LOG.info("*park-controller* request to get loadet medications from drone: {droneId}");
		
		return service.getLoadedMedications(droneId);
	}
	
	@GetMapping("/drones/available")
	public Drone[] getAvailableDrones() {
		LOG.info("*park-controller* request to get available drons in the park");
		
		return service.getAvailableDrones();
	}
	
	@GetMapping("/drone/battery/")
	public int getBatteryLevel(@RequestParam long droneId) {
		LOG.info("*park-controller* request to get battery level of drone: {droneId}");
		
		return service.getBatteryLevel(droneId);
	}
}
