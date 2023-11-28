package drone.monitiring.models;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.constraints.*;

public class DeliveryModel {
	
	private long id;
	@Min(0) @Max(500)
	public int medicationsWeight;
	@Min(0)
	public long droneId;
	@NotEmpty(message = "medications list can not be empty")
	public List<Long> medications;

	public DeliveryModel(long id, int medicationsWeight, long droneId, List<Long> medications) {
		super();
		this.id = id;
		this.medicationsWeight = medicationsWeight;
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
		return "DeliveryModel [id=" + id + ", medicationsWeight=" + medicationsWeight + ", droneId=" + droneId
				+ ", medicationsId=" + Arrays.toString(medications.toArray()) + "]";
	}
	
	
}
