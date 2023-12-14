package drone.monitiring.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import drone.monitiring.entities.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long>   {

	
	@Query(value = "select * from drones where state = :state and battery_capacity >= :minBetteryCapacity", 
			nativeQuery = true) 
	Drone[] findAvailableDrones(int minBetteryCapacity, String state);

	@Query(value = "select * from drones where state = :state and battery_capacity >= :minBetteryCapacity and weigth_limit >= :totalWeight", 
			nativeQuery = true) 
	Drone getAvailableDrone(int totalWeight, int minBetteryCapacity, String state);

}
