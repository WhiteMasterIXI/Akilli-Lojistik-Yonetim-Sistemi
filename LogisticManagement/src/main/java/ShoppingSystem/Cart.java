package ShoppingSystem;
import java.util.ArrayList;
import java.util.List;

import Controller.CartController;
import Controller.RequestProductController;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.RequestProduct;
import UserSystem.User;
import jakarta.persistence.*;


@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(
    	    mappedBy = "cart",
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    private List<RequestProduct> req_product;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    
    public Cart() {
    	this.req_product = new ArrayList<>();
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
	public int getId() {
	    return id;
	}
    
    public List<RequestProduct> getReqProducts(){
    	return req_product;
    }
    
	public void setUrunler(List<RequestProduct> new_products) {
		req_product = new_products;
	}
	
	public RequestProduct addProduct(Product product,int amount) {
		RequestProduct new_product = new RequestProduct();
		new_product.setAmount(amount);
		new_product.setCart(this);
		new_product.setProduct(product);
		
		req_product.add(new_product);
		return new_product;
	}
	// fonksiyon çalışmıyor.
	public void removeProductById(long id) {
	    this.req_product.removeIf(rp -> rp.getId() == id);
	}
	
}
