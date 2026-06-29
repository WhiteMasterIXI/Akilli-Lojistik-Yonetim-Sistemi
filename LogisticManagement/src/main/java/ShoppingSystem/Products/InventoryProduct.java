package ShoppingSystem.Products;

import BuildingSystem.CompanyDepartments.WareHouse;
import jakarta.persistence.*;

@Entity
@Table(name = "InventoryProduct")
public class InventoryProduct {
	
	@Id
	@GeneratedValue
	long id;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id",nullable = true)
	private WareHouse depo;

	@ManyToOne
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;
	
	int amount;
	
	public long getId() {
		return id;
	}
	
	public InventoryProduct() {
		
	}
	
	public Product getProduct() {
		return product;
	}
	
	public WareHouse getWareHouse() {
		return depo;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setProduct(Product new_product) {
		product = new_product;
	}
	
	public void setWareHouse(WareHouse new_WareHouse) {
		depo = new_WareHouse;
	}
	
	public void setAmount(int new_amount) {
		amount = new_amount;
	}
}
