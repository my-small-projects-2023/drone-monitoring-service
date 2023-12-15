package drone.monitiring.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import drone.monitiring.entities.Drone;
import drone.monitiring.models.State;
import drone.monitiring.repo.DroneRepository;

@Component
@Transactional(readOnly = true)
public class DroneImitatorServiceImpl implements DroneImitatorService {

	private static Logger LOG = LoggerFactory.getLogger(DroneImitatorService.class);
	private static final int TIMEOUT = 15;
	private static final int MAX_BATTERY_CAPACITY = 100;
	private static final int CAPACITY_TO_REDUSE = 20;
	
	@Autowired
	private DroneRepository droneRepo;
	
	@Override
	@Transactional
	public void changeState(Drone drone) {
		LOG.debug("*drone-imitatr-service* get drone: {} with state: {}", drone.getId(),drone.getState());
		CompletableFuture.delayedExecutor(TIMEOUT, TimeUnit.SECONDS).execute(() -> {
				drone.setState(State.getNextState(State.valueOf(drone.getState()).ordinal()));
				droneRepo.save(drone);
				LOG.debug("*drone-imitatr-service* set new state: {} to drone: {}", drone.getState(), drone.getId());
			});
		
	}

	
	@Scheduled(fixedDelay = 5000)
	@Transactional
	protected void imitateDeliveries() {
		LOG.debug("*drone-imitatr-service* delivery imitator start");
		long[] ids = droneRepo.findAll().stream().mapToLong(e -> (long)e.getId()).toArray();
		int random = ThreadLocalRandom.current().nextInt(0, ids.length - 1);
		Drone drone = droneRepo.findById((long) random).orElse(null);
		if(drone != null) {
			drone.setState(State.getNextState(State.valueOf(drone.getState()).ordinal()));
			LOG.debug("*drone-imitatr-service* state was changed for drone: {} to: {}", drone.getId(), drone.getState());
			if(drone.getBatteryCapacity() <= 0) {
				drone.setBatteryCapacity(MAX_BATTERY_CAPACITY);
				LOG.debug("*drone-imitatr-service* battery for drone was fully recharged: {} to: {}%", drone.getId(), drone.getBatteryCapacity());
			} else {
				drone.setBatteryCapacity(drone.getBatteryCapacity() - CAPACITY_TO_REDUSE);
			}
			
			droneRepo.save(drone);
			LOG.debug("*drone-imitatr-service* changed battery for drone: {} to: {}%", drone.getId(), drone.getBatteryCapacity());
		} else {
			LOG.debug("*drone-imitatr-service* drone with id: {} does not exist", random);
		}
	}
	


	
}
