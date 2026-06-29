package ShoppingSystem;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BuildingSystem.Supplier;
import ShoppingSystem.orderStatus;
import ShoppingSystem.Products.OrderedProduct;
import ShoppingSystem.Products.Product;
import UserSystem.User;
import jakarta.persistence.*;


@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	int id;	

	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
	List<OrderedProduct> Urunler;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	Supplier supp;
	
	double price;
	
	String recievernote;
	
	LocalDateTime orderdate;
	
	LocalDateTime deliverydate;
	
	@Enumerated(EnumType.STRING)
	orderStatus status;
	
	String contactaddress;
	
	String deliveryaddress;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	public Order() {
		Urunler = new ArrayList<>();
	}
	
	public void setSupplier(Supplier new_sup) {
		supp = new_sup;
	}
	
	public Supplier getSupplier() {
		return supp;
	}
	
	public String BilgileriGoruntule() {
		String Mesaj = "Teslim Adresi: " + deliveryaddress + " Siparis Notu: " + recievernote;
		return Mesaj;
	}
	
	public int getId() {
	    return id;
	}
	
	public void AddProduct(OrderedProduct orderproduct) {
		Urunler.add(orderproduct);
	}
	
	public void removeProduct(OrderedProduct orderproduct) {
		Urunler.remove(orderproduct);
	}

	public List<OrderedProduct> getUrunler() {
	    return Urunler;
	}

	public void setUrunler(List<OrderedProduct> urunler) {
	    Urunler = urunler;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}

	public String getRecievernote() {
	    return recievernote;
	}

	public void setRecievernote(String recievernote) {
	    this.recievernote = recievernote;
	}

	public LocalDateTime getOrderdate() {
	    return orderdate;
	}

	public void setOrderdate(LocalDateTime orderdate) {
	    this.orderdate = orderdate;
	}

	public LocalDateTime getDeliverydate() {
	    return deliverydate;
	}

	public void setDeliverydate(LocalDateTime deliverydate) {
	    this.deliverydate = deliverydate;
	}

	public orderStatus getStatus() {
	    return status;
	}

	public void setStatus(orderStatus status) {
	    this.status = status;
	}

	public String getContactaddress() {
	    return contactaddress;
	}

	public void setContactaddress(String contactaddress) {
	    this.contactaddress = contactaddress;
	}

	public String getDeliveryaddress() {
	    return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
	    this.deliveryaddress = deliveryaddress;
	}

	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}

}
