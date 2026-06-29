package Controller;

import java.util.List;

import ShoppingSystem.Products.OrderedProduct;
import ShoppingSystem.Products.RequestProduct;
import dao.OrderedProductDao;
import dao.ReqProductDao;

public class OrderedProductController {
	public static boolean DeleteReqProduct(long id) {
		return OrderedProductDao.delete(id);
	}
	
	public static boolean AddOrderedProduct(OrderedProduct product) {
	   if(!OrderedProductDao.save(product))return false;
	   
	   
	   return true;
	}
	
	public static boolean UpdateOrderedProduct(OrderedProduct product) {
		return OrderedProductDao.update(product);
	}
	
	public static List<OrderedProduct> getOrderedProductsByUserId(long order_id){
		return OrderedProductDao.findByOrderId(order_id);
	}
}
