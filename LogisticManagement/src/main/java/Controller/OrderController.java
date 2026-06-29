package Controller;

import java.util.List;

import ShoppingSystem.Order;
import ShoppingSystem.orderStatus;
import dao.OrderDao;

public class OrderController {
	
	public static boolean CreateOrder(Order order) {
		if(!OrderDao.save(order))
			return false;
		return true;
	}
	
	public static boolean updateOrder(Order order) {
		if(!OrderDao.update(order))
			return false;
		return true;
	}
	
	public static boolean deleteOrder(int order_id) {
		if(!OrderDao.delete(order_id))
			return false;
		return true;
	}
	
	public static List<Order> getOrderByUserId(long user_id){
		return OrderDao.findByUser(user_id);
	}
	
	public static List<Order> getAllOrder(){
		return OrderDao.findAll();
	}
	
	public static List<Order> getOrderByStatus(orderStatus status){
		return OrderDao.findByStatus(status);
	}

}
