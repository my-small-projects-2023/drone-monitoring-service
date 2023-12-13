package drone.monitiring.models;

import java.util.*;

import jakarta.validation.constraints.*;

public class DeliveryModel {
	
	private long id;
	@Min(0) @Max(500)
	public int medicationsWeight;
	@Min(0)
	public long droneId;
	@NotEmpty(message = "medications list can not be empty")
	public Map<Long, Integer> medications;

	public DeliveryModel(long id, long droneId, Map<Long, Integer> medications) {
		super();
		this.id = id;
		//this.medicationsWeight = medicationsWeight;
		this.droneId = droneId;
		this.medications = medications;
	} 
	
	public DeliveryModel() {
		
	}


	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DeliveryModel [id=" + id + ", droneId=" + droneId
				+ "]";
		//TODO map to string
		//", medicationsId=" + Arrays.toString(medications.toArray()) + 
	}
	
	
}
