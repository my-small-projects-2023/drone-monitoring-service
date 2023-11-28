package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import drone.monitiring.entities.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long>   {

}
