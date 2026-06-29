package Controller;


import java.util.List;

import BuildingSystem.CompanyDepartments.WareHouse;
import Services.ProductService;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.InventoryProduct;
import ShoppingSystem.Products.Product;
import dao.AllowedComponentDao;
import dao.InventoryProductDao;
import dao.ProductDao;

public class ProductController {
	
	public static boolean DeleteProduct(Product product) {
		// silmeden önce InventoryProductlarıda silelim
		
		List<InventoryProduct> inv_products = InventoryProductDao.findByProduct(product.getId());
		for(InventoryProduct prod : inv_products) {
			InventoryProductDao.delete(prod.getId());
		}
		
		return ProductService.DeleteProduct(product);
	}
	
	public static boolean AddProduct(Product product, WareHouse depo) {

	    Product urun = ProductDao.findByName(product.getName());

	    if (urun != null) {
	        urun.setAmount(urun.getAmount() + product.getAmount());
	        ProductService.UpdateProduct(urun);

	        InventoryProduct inv = new InventoryProduct();
	        inv.setAmount(product.getAmount());
	        inv.setProduct(urun);
	        inv.setWareHouse(depo);

	        InventoryProductDao.save(inv);

	        return true;
	    }

	    ProductService.AddProduct(product);

	    InventoryProduct inv = new InventoryProduct();
	    inv.setAmount(product.getAmount());
	    inv.setProduct(product);
	    inv.setWareHouse(depo);

	    InventoryProductDao.save(inv);

	    return true;
	}
	
	public static boolean AddComponentRule(AllowedComponent component) {
		return ProductService.AddComponent(component);
	}
	
	public static boolean UpdateProduct(Product product) {
		return ProductService.UpdateProduct(product);
	}
	
	public static List<Product> getProducts(){
		return ProductService.getProducts();
	}
	
	public static Product getProduct(long id) {
		return ProductService.getProduct(id);
	}
	
	
	public static void CreateBasicProducts() {
		
		List<WareHouse> depolar = WareHouseController.getAll();
		WareHouse ilk_depo = depolar.getFirst();
		
		System.out.print(ilk_depo.getId());
		
		// eğer zaten varsa ekleme
		if(ProductDao.findByName("Bilgisayar") != null)return;

	    Product bilgisayar = new Product();
	    bilgisayar.setAmount(20);
	    bilgisayar.setMass(15);
	    bilgisayar.setName("Bilgisayar");
	    bilgisayar.setPrice(15000);
	    
	    AddProduct(bilgisayar,ilk_depo);
	    
	    Product klavye = new Product();
	    klavye.setAmount(50);
	    klavye.setMass(2);
	    klavye.setName("Mekanik Klavye");
	    klavye.setPrice(2500);
	    
	    AddProduct(klavye,ilk_depo);

	    Product mouse = new Product();
	    mouse.setAmount(70);
	    mouse.setMass(1);
	    mouse.setName("Oyuncu Mouse");
	    mouse.setPrice(1200);
	    
	    AddProduct(mouse,ilk_depo);

	    Product monitor = new Product();
	    monitor.setAmount(15);
	    monitor.setMass(8);
	    monitor.setName("27 Inch Monitor");
	    monitor.setPrice(8500);
	    
	    AddProduct(monitor,ilk_depo);

	    Product kulaklik = new Product();
	    kulaklik.setAmount(40);
	    kulaklik.setMass(1);
	    kulaklik.setName("Kablosuz Kulaklık");
	    kulaklik.setPrice(3200);
	    
	    AddProduct(kulaklik,ilk_depo);

	    Product ram = new Product();
	    ram.setAmount(100);
	    ram.setMass(1);
	    ram.setName("16GB DDR5 RAM");
	    ram.setPrice(1800);
	    
	    AddProduct(ram,ilk_depo);

	    Product ssd = new Product();
	    ssd.setAmount(60);
	    ssd.setMass(1);
	    ssd.setName("1TB SSD");
	    ssd.setPrice(2700);
	    
	    AddProduct(ssd,ilk_depo);

	    Product ekranKarti = new Product();
	    ekranKarti.setAmount(10);
	    ekranKarti.setMass(4);
	    ekranKarti.setName("RTX 4070");
	    ekranKarti.setPrice(32000);
	    
	    AddProduct(ekranKarti,ilk_depo);

	    Product islemci = new Product();
	    islemci.setAmount(25);
	    islemci.setMass(1);
	    islemci.setName("Intel i7 İşlemci");
	    islemci.setPrice(14000);
	    
	    AddProduct(islemci,ilk_depo);

	    Product anakart = new Product();
	    anakart.setAmount(18);
	    anakart.setMass(3);
	    anakart.setName("Gaming Anakart");
	    anakart.setPrice(9500);
	    
	    AddProduct(anakart,ilk_depo);
	    

	}
	
	public static boolean deleteProducts() {
		
		if(!ProductService.deleteProducts())return false;
				
		
		return true;
	}
	
	
	public static List<AllowedComponent> getAllowedComponentByParent(long product_id){
		return AllowedComponentDao.findByParentProduct(product_id);
	}
	
	
	
}
