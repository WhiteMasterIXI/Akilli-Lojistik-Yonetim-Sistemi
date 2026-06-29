package Services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BuildingSystem.Supplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import Controller.NotificationController;
import Controller.ReqProComController;
import Controller.RequestProductController;
import Controller.SupplierController;
import Controller.WareHouseController;
import NotifySystem.Notification;
import NotifySystem.notificationStatus;
import Services.SingletonPattern.LogService;
import Services.StrategyPattern.PaymentStrategy.IPaymentStrategy;
import Services.StrategyPattern.Shipments.ShippingContext;
import ShoppingSystem.Cart;
import ShoppingSystem.Order;
import ShoppingSystem.orderStatus;
import ShoppingSystem.Products.OrderedProduct;
import ShoppingSystem.Products.OrderedProductComponent;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.RequestProduct;
import ShoppingSystem.Products.RequestProductComponent;
import UserSystem.User;
import dao.CartDao;
import dao.OrderDao;
import dao.OrderedProductDao;
import dao.ProductDao;
import dao.ReqProductDao;

public class CartService {
	
	public static boolean CardToOrder(Cart cart, User user,String SiparisNotu,String TeslimAdresi,ShippingContext shippingContext,IPaymentStrategy payment) {
		

	    Cart managedCart = CartDao.findById(cart.getId());
	    managedCart.setUrunler(RequestProductController.getRequestProductsByCartId(managedCart.getId()));
	    
	    Order order = new Order();
	    order.setUser(user);
	    order.setContactaddress(user.getTelephone());
	    order.setDeliveryaddress(TeslimAdresi);
	    order.setOrderdate(LocalDateTime.now());
	    order.setRecievernote(SiparisNotu);
	    order.setStatus(orderStatus.HAZIRLANIYOR);
	    
	    List<RequestProduct> urunler = managedCart.getReqProducts();
	    if(urunler.size() == 0) return false;
	    
	    double Toplam_Fiyat = 0;
	    double ToplamAgirlik = 0;

	    for (RequestProduct p : urunler) {
	    	
	        OrderedProduct op = new OrderedProduct();
	        
	        Product product = p.getProduct();
	        
	        Toplam_Fiyat += product.getPrice() * p.getAmount();
	        ToplamAgirlik += product.getMass() * p.getAmount();
	        op.setOrder(order);
	        op.setProduct(product);
	        op.setAmount(p.getAmount());
	        order.AddProduct(op);
	        
	        product.setAmount(product.getAmount() - p.getAmount());
	        
	        WareHouse depo = WareHouseController.getAll().getFirst();
	        if(product.getAmount() < depo.getMinProductSize()) {
	        	List<User> users = new ArrayList<>();
	        	users.add(depo.getManager());
	        	
	        	Notification alert_notify = new Notification();
	        	alert_notify.setMesaj(product.getName() + " adlı ürününüz eşik değerin altına düşmüştür");
	        	alert_notify.setNotificationStatus(notificationStatus.OKUNMADI);
	        	alert_notify.setReceiever(depo.getManager());
	        	alert_notify.setSender("System");
	        	alert_notify.setTarih(LocalDate.now());
	        	NotificationController.SendNotificationToUsers(alert_notify, users);
	        	LogService.getInstance().log(product.getName() + " adlı ürününüz eşik değerin altına düşmüştür");
	        }
	        ProductDao.update(product);

	        List<RequestProductComponent> parts =
	                ReqProComController.getByRequesProductId(p.getId());

	        for (RequestProductComponent parca : parts) {
	        	Product child_product = parca.getChild();	
	        	
	        	Toplam_Fiyat += parca.getQuantity() * child_product.getPrice();
	        	ToplamAgirlik += parca.getQuantity() * child_product.getMass();
	            OrderedProductComponent yeni = 
	                    new OrderedProductComponent();
	            
	            yeni.setParent(op);
	            yeni.setChild(parca.getChild());
	            yeni.setAmount(parca.getQuantity());
	            
	            child_product.setAmount(child_product.getAmount() - parca.getQuantity());
	            
		        if(child_product.getAmount() < depo.getMinProductSize()) {
		        	List<User> users = new ArrayList<>();
		        	users.add(depo.getManager());
		        	
		        	Notification alert_notify = new Notification();
		        	alert_notify.setMesaj(child_product.getName() + " adlı ürününüz eşik değerin altına düşmüştür");
		        	alert_notify.setNotificationStatus(notificationStatus.OKUNMADI);
		        	alert_notify.setReceiever(depo.getManager());
		        	alert_notify.setSender("System");
		        	alert_notify.setTarih(LocalDate.now());
		        	NotificationController.SendNotificationToUsers(alert_notify, users);
		        	LogService.getInstance().log(child_product.getName() + " adlı ürününüz eşik değerin altına düşmüştür");
		        }
		        
	            ProductDao.update(child_product);
	            op.getComponents().add(yeni);
	        }
	    }
	    
	    Supplier supp = shippingContext.getSupplier();
	    
        double shippingPrice =
                shippingContext.calculatePrice(
                		supp,
                        ToplamAgirlik,
                        35
                );
	    
        Toplam_Fiyat += shippingPrice;
        order.setSupplier(supp);
	    order.setPrice(Toplam_Fiyat);
	    
	    payment.Pay(Toplam_Fiyat);
	    OrderDao.save(order);

	    // cart temizle
	    for (RequestProduct p : managedCart.getReqProducts()) {
	        ReqProductDao.delete(p.getId());
	    }
	    
	    return true;
	}
    

}
