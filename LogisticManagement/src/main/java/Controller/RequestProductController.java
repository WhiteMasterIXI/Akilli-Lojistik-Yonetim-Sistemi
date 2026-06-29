package Controller;

import java.util.List;

import BuildingSystem.CompanyDepartments.WareHouse;
import Services.ProductService;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.InventoryProduct;
import ShoppingSystem.Products.RequestProduct;
import dao.InventoryProductDao;
import dao.ProductDao;
import dao.ReqProductDao;

public class RequestProductController {
	public static boolean DeleteReqProduct(long id) {
		return ReqProductDao.delete(id);
	}
	
	public static boolean AddReqProduct(RequestProduct product) {
	   if(!ReqProductDao.save(product))return false;
	   
	   
	   return true;
	}
	
	public static boolean UpdateReqProduct(RequestProduct product) {
		return ReqProductDao.update(product);
	}
	
	public static List<RequestProduct> getRequestProductsByCartId(long cart_id){
		return ReqProductDao.findByCartId(cart_id);
	}
	
}
