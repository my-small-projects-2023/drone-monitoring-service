package drone.monitiring.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_items")
public class DeliveryItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    
    private int amount;

	public DeliveryItem(Medication medication, Delivery delivery, int amount) {
		super();
		this.medication = medication;
		this.delivery = delivery;
		this.amount = amount;
	}
	
	public DeliveryItem() {
		
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DeliveryItem [id=" + id + ", medication=" + medication + ", delivery=" + delivery + ", amount=" + amount
				+ "]";
	}

}
