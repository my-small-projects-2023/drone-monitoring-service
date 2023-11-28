package drone.monitiring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import drone.monitiring.entities.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

}
