package drone.monitiring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import drone.monitiring.models.*;
import drone.monitiring.service.ModelService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("model")
@Validated
public class ModelController {

	private static Logger LOG = LoggerFactory.getLogger(ModelController.class);
	@Autowired
	private ModelService service;
	
	@PostMapping("/drone")
	public String addDrone(@RequestBody @Valid DroneModel drone) {
		LOG.info("*** request to add drone");
	
		return null;
	}
	
	@GetMapping("/drone/{id}")
	public String getDrone(@PathVariable long id) {
		LOG.info("*** request to get drone by id: {}", id);
		
		return null;
	}
	
	@PutMapping("/drone")
	public String updateDrone(@RequestBody @Valid DroneModel drone) {
		LOG.info("*** request to update drone with id: {}", drone.getId());
		
		return null;
	}
	
	@DeleteMapping("/drone/{id}")
	public String deleteDrone(@PathVariable long id) {
		LOG.info("*** request to remove drone by id: {}", id);
		
		return null;
	}
	
	@PostMapping("/medication")
	public String addMedication(@RequestBody @Valid MedicationModel medication) {
		LOG.info("*** request to add medication");
	
		return null;
	}
	
	@GetMapping("/medication/{id}")
	public String getMedication(@PathVariable long id) {
		LOG.info("*** request to get medication by id: {}", id);
		
		return null;
	}
	
	@PutMapping("/medication")
	public String updateMedication(@RequestBody @Valid MedicationModel medication) {
		LOG.info("*** request to update medication with id: {}", medication.getId());
		
		return null;
	}
	
	@DeleteMapping("/medication/{id}")
	public String deleteMedication(@PathVariable long id) {
		LOG.info("*** request to remove medication by id: {}", id);
		
		return null;
	}
	
	@PostMapping("/delivery")
	public String addDelivery(@RequestBody @Valid DeliveryModel delivery) {
		LOG.info("*** request to add delivery");
		
		return null;
	}
	
	@GetMapping("/delivery/{id}")
	public String getDelivery(@PathVariable long id) {
		LOG.info("*** request to get delivery by id: {}", id);
		
		return null;
	}
	
	@PutMapping("/delivery")
	public String updateDelivery(@RequestBody @Valid DeliveryModel delivery) {
		LOG.info("*** request to update delivery with id: {}", delivery.getId());
		
		return null;
	}
	
	
	@DeleteMapping("/delivery/{id}")
	public String deleteDelivery(@PathVariable long id) {
		LOG.info("*** request to remove delivery by id: {}", id);
		
		return null;
	}
}
