package ShoppingSystem.Products;

import java.util.List;

import ShoppingSystem.Cart;
import UserSystem.User;
import jakarta.persistence.*;

@Entity
@Table(name = "RequestProduct")
public class RequestProduct {
	
	@Id
	@GeneratedValue
	long id;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	int amount;
	
	@OneToMany(mappedBy = "parent",
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	List<RequestProductComponent> req_products;
	
	public RequestProduct() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product new_product) {
		product = new_product;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart new_cart) {
		cart = new_cart;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int new_amount) {
		amount = new_amount;
	}
	
}
