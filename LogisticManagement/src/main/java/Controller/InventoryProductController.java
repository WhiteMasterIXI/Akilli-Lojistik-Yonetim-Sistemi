package Controller;

import java.util.List;

import ShoppingSystem.Products.InventoryProduct;
import dao.InventoryProductDao;

public class InventoryProductController {
	
	
	public static boolean AddInvProduct(InventoryProduct ınv_pro) {
		return InventoryProductDao.save(ınv_pro);
	}
	
	public static boolean DeleteInvProduct(long id) {
		return InventoryProductDao.delete(id);
	}
	
	public static List<InventoryProduct> getInvProductsByWareHouse(long warehouse_id){
		return InventoryProductDao.findByDepoId(warehouse_id);
	}
}
