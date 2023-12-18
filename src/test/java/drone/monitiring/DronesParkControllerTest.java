package drone.monitiring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import drone.monitiring.entities.*;
import drone.monitiring.models.*;
import drone.monitiring.service.DronesParkService;

@SpringBootTest
@AutoConfigureMockMvc
class DronesParkControllerTest {
	
	private static final Integer BATATTERY_CAPACITY = 100;
	private static final int WEIGHT_LIMIT = 450;
	static Logger LOG = LoggerFactory.getLogger(DronesParkControllerTest.class);
	@Autowired
	MockMvc mockMvc;
	@MockBean
	DronesParkService service;
	ObjectMapper mapper = new ObjectMapper();


	DroneModel droneDto = new DroneModel(0, "AAHW2", WEIGHT_LIMIT);
	Drone drone = new Drone("AAHW2", Model.getModel(WEIGHT_LIMIT), 
			WEIGHT_LIMIT, BATATTERY_CAPACITY, State.IDLE.name());
	MedicationModel medicationDto = new MedicationModel(0, "m-name", 50, "m-code", "m-image");
	Medication medication = new Medication("m-name", 50, "m-code", "m-image");
	private static final Integer MEDICATION_AMOUNT = 2;
	Map<Long, Integer> medicationMap;

	

	@BeforeEach
	void serviceMocking() {
		medicationMap = new HashMap<>();
		medicationMap.put(medicationDto.getId(), MEDICATION_AMOUNT);
		Medication[] medicationArr = {medication};
		Drone[] droneArr = {drone};
		when(service.registerDrone(any())).thenReturn(drone);
		when(service.loadDrone(medicationMap)).thenReturn(true);
		when(service.getLoadedMedications(drone.getId())).thenReturn(medicationArr);
		when(service.getAvailableDrones()).thenReturn(droneArr);
		when(service.getBatteryLevel(drone.getId())).thenReturn(BATATTERY_CAPACITY);
	}
	

	@Test
	void registerDroneTest() throws Exception {
		LOG.debug("***TEST register new drone");
		String droneJson = mapper.writeValueAsString(droneDto);
		String res = mockMvc.perform(post("http://localhost:8080/park/drone")
				.contentType(MediaType.APPLICATION_JSON)
				.content(droneJson))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		LOG.debug("***TEST Response: {}", res);
		LOG.debug("*TEST: {}", service.registerDrone(droneDto));
		LOG.debug("***TEST {}", res);
		assertTrue(res.contains("AAHW2"));
	}
	
	@Test
	void loadDroneTest() throws Exception {
		LOG.debug("***TEST load drone");
		String medicationJson = mapper.writeValueAsString(medicationMap);
		String res = mockMvc.perform(post("http://localhost:8080/park/drone/load")
				.contentType(MediaType.APPLICATION_JSON)
				.content(medicationJson))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		assertTrue(res.equals("medications was loaded to a drone"));
	}
	
	@Test
	void getLoadedMedications() throws Exception {
		LOG.debug("***TEST get loaded medications");
		String res = mockMvc.perform(get("http://localhost:8080/park/medications/loaded/?droneId=0"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		assertTrue(res.contains("m-code"));
	}
	
	@Test 
	void getAvailableDronesTest() throws Exception{
		LOG.debug("***TEST get available drones");
		String res = mockMvc.perform(get("http://localhost:8080/park/drones/available"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		assertTrue(res.contains("AAHW2"));
	}
	
	
	@Test
	void getDroneBattery() throws Exception {
		LOG.debug("***TEST get available drones");
		String res = mockMvc.perform(get("http://localhost:8080/park/drone/battery/?droneId=0"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(res.equals(BATATTERY_CAPACITY.toString()));
	}

}
