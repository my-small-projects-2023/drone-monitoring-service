package drone.monitiring.models;

public enum Model {
	
	LIGHTWEIGHT(300), MIDDLEWEIGHT(390), CRUISERWEIGHT(430), HEAVYWEIGHT(500);
	
	private final int maxCapacity;
	
	Model(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	


}
