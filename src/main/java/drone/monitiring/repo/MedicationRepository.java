package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import drone.monitiring.entities.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

	@Query(value = "select m.* from medications as m join delivery_items as di "
			+ "on m.id = di.medication_id join deliveries as d "
			+ "on d.id = di.delivery_id "
			+ "where d.drone_id = :droneId and d.id = (select max(id) from deliveries "
			+ "where drone_id = :droneId) order by d.id"
			, nativeQuery = true)
	Medication[] getLastDelivery(long droneId);
	
}
