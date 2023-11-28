package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import drone.monitiring.entities.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long>   {

	
	@Query() //TODO
	Drone[] findAvailableDrones();

	@Query() //TODO
	Drone findAvailableDrone(int medicationsWeigth);

}
