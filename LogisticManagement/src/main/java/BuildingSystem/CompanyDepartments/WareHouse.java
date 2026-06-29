package BuildingSystem.CompanyDepartments;

import java.util.List;

import BuildingSystem.Supplier;
import ShoppingSystem.Products.InventoryProduct;
import ShoppingSystem.Products.Product;
import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "WareHouse",
uniqueConstraints = @UniqueConstraint(columnNames = "Konum"))

public class WareHouse {
	@Id
	@GeneratedValue
	long id;
	String Konum;
	String Telefon;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	private User yonetici;
	
	@OneToMany(mappedBy = "depo")
	List<InventoryProduct> Urunler;
	
	int min_urun_sayisi;
	@ManyToMany
	@JoinTable(
	    name = "warehouse_supplier",
	    joinColumns = @JoinColumn(name = "warehouse_id"),
	    inverseJoinColumns = @JoinColumn(name = "supplier_id")
	)
	List<Supplier> supplier;
	
	public String getLocation() {
		return Konum;
	}
	
	public List<Supplier> getSupplier(){
		return supplier;
	}
	
	public List<InventoryProduct> getProducts() {
		return Urunler;
	}
	
	public int getMinProductSize() {
		return min_urun_sayisi;
	}
	
	public String getPhone() {
		return Telefon;
	}
	
	public User getManager() {
		return yonetici;
	}
	
	public long getId() {
		return id;
	}
	
	public void setManager(User user) {
		yonetici = user;
	}
	
	public void setLocation(String Konum) {
		this.Konum = Konum;
	}
	
	public void setPhone(String phone) {
		Telefon = phone;
	}
	
	public void setMinUrunSayisi(int min_miktar) {
		min_urun_sayisi = min_miktar;
	}
	
}
