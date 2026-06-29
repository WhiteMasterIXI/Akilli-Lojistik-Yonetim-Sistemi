package Controller;

import java.util.List;

import ShoppingSystem.Products.RequestProductComponent;
import dao.RequestProductComponentDao;

public class ReqProComController {
	public static boolean save(RequestProductComponent rpc) {
		if(!RequestProductComponentDao.save(rpc))return false;
		
		
		return true;
	}
	
	
	public static boolean delete(RequestProductComponent rpc) {
		if(!RequestProductComponentDao.delete(rpc.GetId()))return false;
		
		
		return true;
	}
	
	public static boolean update(RequestProductComponent rpc) {
		if(!RequestProductComponentDao.update(rpc))return false;
		
		
		return true;
	}
	
	public static List<RequestProductComponent> getByRequesProductId(long id){
		return RequestProductComponentDao.findByParent(id);
	}
}
