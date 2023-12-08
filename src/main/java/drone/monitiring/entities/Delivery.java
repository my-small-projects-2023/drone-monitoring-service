package drone.monitiring.entities;

import java.util.*;
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
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DeliveryItem> items = new ArrayList<>();

	public Delivery(int medicationsWeight, Drone drone, List<DeliveryItem> items) {
		super();
		this.medicationsWeight = medicationsWeight;
		this.drone = drone;
		this.items = items;
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

	public List<DeliveryItem>  getMedications() {
		return items;
	}

	public void setMedications(List<DeliveryItem>  items) {
		this.items = items;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", medicationsWeight=" + medicationsWeight + ", drone=" + drone
				+ ", medicationsId=" + Arrays.toString(items.toArray()) + "]";
	}


}
