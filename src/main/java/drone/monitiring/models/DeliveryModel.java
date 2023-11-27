package drone.monitiring.models;

import java.util.Arrays;

public class DeliveryModel {
	
	private long id;
	private int medicationsWeight;
	private long droneId;
	private long[] medicationsId;

	public DeliveryModel(long id, int medicationsWeight, long droneId, long[] medicationsId) {
		super();
		this.id = id;
		this.medicationsWeight = medicationsWeight;
		this.droneId = droneId;
		this.medicationsId = medicationsId;
	} 
	
	public DeliveryModel() {
		
	}

	public int getMedicationsWeight() {
		return medicationsWeight;
	}

	public void setMedicationsWeight(int medicationsWeight) {
		this.medicationsWeight = medicationsWeight;
	}

	public long getDroneId() {
		return droneId;
	}

	public void setDroneId(long droneId) {
		this.droneId = droneId;
	}

	public long[] getMedicationsId() {
		return medicationsId;
	}

	public void setMedicationsId(long[] medicationsId) {
		this.medicationsId = medicationsId;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DeliveryModel [id=" + id + ", medicationsWeight=" + medicationsWeight + ", droneId=" + droneId
				+ ", medicationsId=" + Arrays.toString(medicationsId) + "]";
	}
	
	
}
