package ShoppingSystem.Products;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderedProductComponent")
public class OrderedProductComponent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private OrderedProduct parent;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Product child;

    private int quantity;
    
    public OrderedProductComponent() {
    	
    }
    
    public OrderedProduct getParent() {
    	return parent;
    }
    
    
    public void setParent(OrderedProduct op) {
    	parent = op;
    }
    
    public void setChild(Product p) {
    	child = p;
    }
    
    public void setAmount(int amount) {
    	quantity = amount;
    }
    
    public Long GetId() {
    	return id;
    }
    
    public Product getChild() {
    	return this.child;
    }
    
    public double getPrice() {
    	return 0;
    }
    
    public int getQuantity() {
    	return quantity;
    }
}