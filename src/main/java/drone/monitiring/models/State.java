package drone.monitiring.models;


public enum State {

	IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;
	
	public static String getNextState(int ordinal) {
		
		return State.values()[(ordinal + 1) % State.values().length].name();
	}
}
