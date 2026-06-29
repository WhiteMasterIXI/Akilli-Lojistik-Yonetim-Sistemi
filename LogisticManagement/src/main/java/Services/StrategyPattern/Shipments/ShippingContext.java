package Services.StrategyPattern.Shipments;

import BuildingSystem.Supplier;

public class ShippingContext {
	
	boolean is_Fragile = false;
	boolean is_secure = false;
	Supplier supplier;
	

    private IShippingStrategy strategy;
    
    public ShippingContext() {
    	
    }
    
    public void setFragile(boolean fragile) {
    	is_Fragile = fragile;
    }
    
    public boolean getFragile() {
    	return is_Fragile;
    }
    
    public void setSecure(boolean secure) {
    	is_secure = secure;
    }
    
    public boolean getSecure() {
    	return is_secure;
    }
    
    public void setSupplier(Supplier sup) {
    	supplier = sup;
    }
    
    public Supplier getSupplier() {
    	return supplier;
    }

    public ShippingContext(IShippingStrategy strategy) {
        this.strategy = strategy;
    }
    
    
    
    public void setShippingStrategy(IShippingStrategy ship) {
    	strategy = ship;
    }

public double calculatePrice(Supplier supplier,
        double weight,
        double distance
        ) {
        return strategy.calculatePrice(supplier,weight,distance,is_secure,is_Fragile);
    }
}