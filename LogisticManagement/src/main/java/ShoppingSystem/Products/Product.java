package ShoppingSystem.Products;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(
		name = "Products",
				uniqueConstraints = {
				        @UniqueConstraint(columnNames = "name")
				    }
		)
public class Product {
	
	@OneToMany(
		    mappedBy = "parent",
		    fetch = FetchType.EAGER,
		    cascade = CascadeType.ALL,
		    orphanRemoval = true
		)
	List<AllowedComponent> components;
	
	@Id
	@GeneratedValue
	long id;
	String name;
	int amount;
	float mass;
	double price;
	
	public Product() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public float getMass() {
		return mass;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double d) {
		price = d;
	}
	
	public void setMass(float new_mass) {
		mass = new_mass;
	}
	
	public void setAmount(int new_Amount) {
		amount = new_Amount;
	}
	
	public void setName(String new_name) {
		name = new_name;
	}
	
	public  void BilgileriGoruntule() {
		
	}
	
    public List<AllowedComponent> GetAllowedComponents() {
        return components;
    }
	
	public Product(String Name,int Amount,float Price) {
		name = Name;
		amount = Amount;
		price = Price;
	}
	
	// eğer Product Partials da takılabilecek parca varsa yap
	public void ParcaEkle(int id, int miktar) {
		
	}
	
	public void ParcaCikart(int ReqPro_id) {
		
	}
	
}
