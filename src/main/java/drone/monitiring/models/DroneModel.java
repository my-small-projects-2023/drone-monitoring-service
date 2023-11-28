package drone.monitiring.models;

import jakarta.validation.constraints.*;

public class DroneModel {
	
	private long id;
	@NotEmpty(message = "serial number cannot be empty") @Size(max = 100)
	public String serialNumber;
	@Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight", message="model can be either: Lightweight, Middleweight, Cruiserweight, Heavyweight")
	public String model;
	@Min(0) @Max(500)
	public int weigthLimit;
	@Min(0) @Max(100)
	public int batteryCapacity;
	@Pattern(regexp="IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING", message="model can be either: IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING")
	public String state;

	public DroneModel(long id, String serialNumber, String model, int weigthLimit, int batteryCapacity, String state) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.model = model;
		this.weigthLimit = weigthLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public DroneModel() {
		
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DroneModel [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weigthLimit="
				+ weigthLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}

}
