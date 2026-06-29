package BuildingSystem;

import Services.StrategyPattern.Shipments.Shipments;
import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Aras, Sürat, Yurtiçi

    private double basePrice; // sabit başlangıç ücreti

    private double pricePerKg; // kg başına ücret

    private double distanceMultiplier; // mesafe çarpanı

    private boolean active;
    
    @Enumerated(EnumType.STRING)
    private Shipments type;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User kargocu;
    
    public long getId() {
    	return id;
    }
    
    public Shipments getShipment() {
    	return type;
    }
    
    public void setShipment(Shipments shp) {
    	type = shp;
    }
    
	public String getName() {
		return name;
	}
	
	public User getManager() {
		return kargocu;
	}
	
	public void setManager(User user) {
		this.kargocu = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public double getDistanceMultiplier() {
		return distanceMultiplier;
	}

	public void setDistanceMultiplier(double distanceMultiplier) {
		this.distanceMultiplier = distanceMultiplier;
	}
	
	public String toString() {
	    return name;
	}

}