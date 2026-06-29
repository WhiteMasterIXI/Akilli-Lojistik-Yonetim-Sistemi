package ShoppingSystem.Products;


import java.util.List;

import ShoppingSystem.Order;
import jakarta.persistence.*;

@Entity
@Table(name = "OrderedProduct")
public class OrderedProduct {
	
	@Id
	@GeneratedValue
	int id;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
    @ManyToOne
    @JoinColumn(name = "order_id")
	Order order;
    
	@OneToMany(mappedBy = "parent",cascade = CascadeType.ALL, orphanRemoval = true)
	List<OrderedProductComponent> ord_products;
    
	int amount;
	
	public OrderedProduct() {
		
	}
	
	public List<OrderedProductComponent> getComponents(){
		return ord_products;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product new_product) {
		product = new_product;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order new_order) {
		order = new_order;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int new_amount) {
		amount = new_amount;
	}
}
