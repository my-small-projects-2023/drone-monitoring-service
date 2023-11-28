package drone.monitiring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "drones")
public class Drone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String serialNumber;
	private String model;
	private int weigthLimit;
	private int batteryCapacity;
	private String state;

	public Drone(String serialNumber, String model, int weigthLimit, int batteryCapacity, String state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weigthLimit = weigthLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public Drone() {
		
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeigthLimit() {
		return weigthLimit;
	}

	public void setWeigthLimit(int weigthLimit) {
		this.weigthLimit = weigthLimit;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weigthLimit="
				+ weigthLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}

}
