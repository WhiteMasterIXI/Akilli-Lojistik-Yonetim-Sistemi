package Controller;


import java.util.ArrayList;
import java.util.List;

import Services.CartService;
import Services.StrategyPattern.PaymentStrategy.IPaymentStrategy;
import Services.StrategyPattern.Shipments.ShippingContext;
import ShoppingSystem.Cart;
import ShoppingSystem.Order;
import ShoppingSystem.Products.OrderedProduct;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.RequestProduct;
import UserSystem.User;
import dao.CartDao;
import dao.OrderDao;
import dao.OrderedProductDao;
import dao.ReqProductDao;

public class CartController {
	
	public static boolean AddCart(Cart cart) {
		return CartDao.save(cart);
	}
	
	public static boolean DeleteCard(Cart cart) {
		return CartDao.delete(cart.getId());
	}
	
	public static boolean UpdateCard(Cart cart) {
		return CartDao.update(cart);
	}
	
    public static boolean CardToOrder(Cart cart, User user,String SiparisNotu,String TeslimAdresi,ShippingContext shipping,IPaymentStrategy payment) {
    	return CartService.CardToOrder(cart, user,SiparisNotu,TeslimAdresi,shipping,payment);
    }
    
    public static Cart getCartByUserId(long id) {
    	return CartDao.findByUserId(id);
    }
    
//    public static boolean AddProductToCart(Cart cart,Product product,int amount) {
//    	return CartService.addProductToCart(cart, product, amount);
//    }
    
}
