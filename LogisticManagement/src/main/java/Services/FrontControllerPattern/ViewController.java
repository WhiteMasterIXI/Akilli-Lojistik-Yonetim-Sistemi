package Services.FrontControllerPattern;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Services.ViewSwitcherPattern.Navigator;
import View.HomeView;
import View.InventoryListView;
import View.InventoryView;
import View.LogView;
import View.LoginView;
import View.NotificationsView;
import View.ProductListView;
import View.RegisterView;
import View.SupplierListView;
import View.SupplierShipmentView;
import View.UserOrderView;
import View.UserView;
import View.WareHouseShipmentView;


public class ViewController {
	public static Navigator nav = null;
	private static final Map<Route, Supplier<JPanel>> routes = new HashMap<>();
	
	static {
	    routes.put(Route.PRODUCT, () -> ProductListView.createProductListPanel(nav));
	    routes.put(Route.INVENTORY, () -> InventoryView.createInventoryPanel());
	    routes.put(Route.INVENTORYLIST, () -> InventoryListView.createInventoryListPanel());
	    routes.put(Route.USER, () -> UserView.createUserPanel());
	    routes.put(Route.HOME, () -> HomeView.createHomePanel());
	    routes.put(Route.LOGIN, () -> LoginView.createLoginPanel(nav));
	    routes.put(Route.REGISTER, () -> RegisterView.createRegisterPanel(nav));
	    routes.put(Route.CUSTOMER_ORDER, () -> UserOrderView.createOrderPanel());
	    routes.put(Route.NOTIFICATION, () -> NotificationsView.createNotificationPanel());
	    routes.put(Route.LOGRECORDS, () -> LogView.createLogPanel());
	    routes.put(Route.SHIPMENT_SP, () -> SupplierShipmentView.createShipmentPanel());
	    routes.put(Route.SHIPMENT_WH, () -> WareHouseShipmentView.createShipmentPanel());
	    routes.put(Route.SUPPLIERLIST, () -> SupplierListView.createSupplierPanel());
	}
	
	public static void setNavigator(Navigator new_naw) {
		nav = new_naw;
	}
	
	// paneli silip tekrar koyuyor bunu ilerde düzelt
	public static void RefreshContentPanel(Route route) {

	    if(nav == null) {
	        JOptionPane.showMessageDialog(null, "navigasyon setlenmemiş");
	        return;
	    }
	    nav.RemovePanel(route.name());
	    Supplier<JPanel> creator = routes.get(route);
	    if(creator == null) return;
	    JPanel panel = creator.get();
	    nav.AddPanel(panel, route.name());
	    nav.ShowPanel(route.name());
	}
	
	public static void AddPanel(Route route) {
		 Supplier<JPanel> creator = routes.get(route);
		JPanel panel = creator.get();
		nav.AddPanel(panel, route.name());
	}
	
	public static void ShowPanel(Route route) {
	    nav.ShowPanel(route.name());
	}

}
