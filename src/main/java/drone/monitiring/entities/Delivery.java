package drone.monitiring.entities;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int medicationsWeight;
	@ManyToOne
	@JoinColumn(name = "drone_id")
	private Drone drone;
	@ManyToOne
	@JoinColumn(name = "medication_id")
	private List<Medication> medications;

	public Delivery(int medicationsWeight, Drone drone, List<Medication> medications) {
		super();
		this.medicationsWeight = medicationsWeight;
		this.drone = drone;
		this.medications = medications;
	}
	
	public Delivery() {
		
	}

	public int getMedicationsWeight() {
		return medicationsWeight;
	}

	public void setMedicationsWeight(int medicationsWeight) {
		this.medicationsWeight = medicationsWeight;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", medicationsWeight=" + medicationsWeight + ", drone=" + drone
				+ ", medicationsId=" + Arrays.toString(medications.toArray()) + "]";
	}


}
