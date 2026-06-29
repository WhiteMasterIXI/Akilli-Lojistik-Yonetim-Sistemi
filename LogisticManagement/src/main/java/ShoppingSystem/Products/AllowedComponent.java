package ShoppingSystem.Products;

import jakarta.persistence.*;

@Entity
@Table(name = "AllowedComponent",
uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"product_id", "component_id"}
        )
    })
public class AllowedComponent {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product parent;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Product child;
    
    public Long GetId() {
    	return id;
    }
    
    
    public double getPrice() {
    	return child.getPrice();
    }
    

    public Long getId() {
        return id;
    }

    public Product getParent() {
        return parent;
    }

    public Product getChild() {
        return child;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParent(Product parent) {
        this.parent = parent;
    }

    public void setChild(Product child) {
        this.child = child;
    }
}
