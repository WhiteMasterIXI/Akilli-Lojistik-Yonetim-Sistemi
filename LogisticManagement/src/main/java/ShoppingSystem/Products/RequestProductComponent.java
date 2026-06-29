package ShoppingSystem.Products;

import jakarta.persistence.*;

@Entity
@Table(name = "RequestProductComponent")
public class RequestProductComponent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private RequestProduct parent;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Product child;

    private int quantity;
    
    public RequestProductComponent() {
    	
    }
    
    public RequestProduct getParent() {
    	return parent;
    }
    
    public void setParent(RequestProduct op) {
    	parent = op;
    }
    
    public void setChild(Product p) {
    	child = p;
    }
    
    public void setQuantity(int quant) {
    	quantity = quant;
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
