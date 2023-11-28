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
	private List<Medication> medicationsId;

	public Delivery(int medicationsWeight, Drone drone, List<Medication> medicationsId) {
		super();
		this.medicationsWeight = medicationsWeight;
		this.drone = drone;
		this.medicationsId = medicationsId;
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

	public List<Medication> getMedicationsId() {
		return medicationsId;
	}

	public void setMedicationsId(List<Medication> medicationsId) {
		this.medicationsId = medicationsId;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", medicationsWeight=" + medicationsWeight + ", drone=" + drone
				+ ", medicationsId=" + Arrays.toString(medicationsId.toArray()) + "]";
	}


}
