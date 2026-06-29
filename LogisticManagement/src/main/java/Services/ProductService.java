package Services;

import java.util.List;

import PaymentSystem.Account;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.Product;
import Utils.Session;
import dao.AccountDao;
import dao.AllowedComponentDao;
import dao.ProductDao;

public class ProductService {
	
	public static boolean DeleteProduct(Product product) {
		List<AllowedComponent> components = product.GetAllowedComponents();
		if(components != null) {
			for(AllowedComponent prod : components) {
				if(!AllowedComponentDao.delete(prod.GetId())) return false;
			}
		}
		
		if(!ProductDao.delete(product.getId()))return false;
		
		return true;
	}
	
	public static boolean AddProduct(Product product) {
		if(!ProductDao.save(product)) return false;
		return true;
	}
	
	public static boolean AddComponent(AllowedComponent component) {
		if(!AllowedComponentDao.save(component))return false;
		return true;
	}
	
	public static boolean UpdateProduct(Product product) {
		if(!ProductDao.update(product)) return false;
		return true;
	}
	
	public static List<Product> getProducts(){
		return ProductDao.findAll();
	}
	
	public static Product getProduct(long id) {
		return ProductDao.findById(id);
	}
	
	
	public static boolean deleteProducts() {
		
		List<Product> all_products = getProducts();
		
		for(Product product : all_products) {
			if(!DeleteProduct(product)) return false;
		}
		
		return true;
	}
}
