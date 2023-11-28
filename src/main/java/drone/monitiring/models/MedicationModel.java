package drone.monitiring.models;

import jakarta.validation.constraints.*;

public class MedicationModel {
	
	private long id;
	@Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "allowed only letters, numbers, ‘-‘, ‘_’")
	public String name;
	@Min(0) @Max(500)
	public int weight;
	@Pattern(regexp = "^[A-Z0-9_]*$", message = "allowed only upper case letters, underscore and numbers")
	public String code;
	@Pattern(regexp = ".*\\\\.(png|jpe?g|gif|bmp|webp)$", message = "string should be in image url format: https://example.com/image.jpg")
	public String imageUrl;

	public MedicationModel(long id, String name, int weight, String code, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.imageUrl = imageUrl;
	}
	
	public MedicationModel() {
		
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MedicationModel [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", imageUrl="
				+ imageUrl + "]";
	}

	
}
