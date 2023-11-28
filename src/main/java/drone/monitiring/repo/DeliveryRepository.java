package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import drone.monitiring.entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
