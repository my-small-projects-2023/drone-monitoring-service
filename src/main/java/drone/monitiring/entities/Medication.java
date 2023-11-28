package drone.monitiring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "medications")
public class Medication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int weight;
	private String code;
	private String imageUrl;

	public Medication(String name, int weight, String code, String imageUrl) {
		super();
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.imageUrl = imageUrl;
	}
	
	public Medication() {
		
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
		return "Medication [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", imageUrl="
				+ imageUrl + "]";
	}

}
