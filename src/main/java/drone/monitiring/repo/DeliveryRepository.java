package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import drone.monitiring.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

//	@Query() //TODO
//	Delivery findLastDeliveryByDroneId(long droneId);

}
