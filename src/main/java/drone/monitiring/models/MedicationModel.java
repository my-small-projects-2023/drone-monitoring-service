package drone.monitiring.models;

public class MedicationModel {
	
	private long id;
	//allowed only letters, numbers, ‘-‘, ‘_’
	private String name;
	private int weight;
	//allowed only upper case letters, underscore and numbers
	private String code;
	private String imageUrl;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
