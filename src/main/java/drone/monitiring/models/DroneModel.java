package drone.monitiring.models;

import jakarta.validation.constraints.*;

public class DroneModel {
	
	private long id;
	@NotEmpty(message = "serial number cannot be empty") @Size(max = 100)
	public String serialNumber;
	@Min(0) @Max(500)
	public int weightLimit;
	
	public DroneModel(long id, String serialNumber, int weightLimit) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.weightLimit = weightLimit;
	}
	
	public DroneModel() {
		
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DroneModel [id=" + id + ", serialNumber=" + serialNumber + ", weigthLimit=" + weightLimit + "]";
	}

}
