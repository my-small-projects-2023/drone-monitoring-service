package drone.monitiring.models;

public class DroneModel {
	
	private long id;
	//max 100 char
	private String serialNumber;
	private Model model;
	//max 500gr
	private int weigthLimit;
	//percentage
	private int batteryCapacity;
	private State state;

	public DroneModel(long id, String serialNumber, Model model, int weigthLimit, int batteryCapacity, State state) {
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
