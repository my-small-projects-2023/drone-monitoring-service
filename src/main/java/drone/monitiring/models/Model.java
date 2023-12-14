package drone.monitiring.models;

import java.util.List;
import java.util.stream.Stream;

public enum Model {

	Lightweight(200), Middleweight(300), Cruiserweight(400), Heavyweight(500);

	private final int maxValue;
	
	Model(int maxValue) {
		this.maxValue = maxValue;
	}
	
	int getMaxValue() {
		
		return this.maxValue;
	}
	
	public static List<String> getModelsForWeight(int weight) {
		
		return Stream.of(Model.values())
				.filter(m -> m.maxValue >= weight)
				.map(m -> m.name())
				.toList();
	}
	
	public static String getModel(int weight) {
		for(Model m : Model.values()) {
			if(m.maxValue >= weight) {
				return m.name();
			}
		}
		return null;
	}
	
	
}