package Controller;

import java.util.List;

import BuildingSystem.CompanyDepartments.WareHouse;
import Services.WareHouseService;
import dao.WareHouseDao;

public class WareHouseController {
	public static boolean CreateWareHouse(WareHouse new_warehouse) {
		if(!WareHouseService.CreateWareH(new_warehouse)) return false;
		
		return true;
	}
	
	public static boolean DeleteWareHouse(long id) {
		if(!WareHouseService.DeleteWareH(id)) return false;
		
		return true;
	}
	
	
	public static boolean UpdateWareHouse(WareHouse new_warehouse) {
		if(!WareHouseService.UpdateWareH(new_warehouse)) return false;
		
		return true;
	}
	
	
	public static void createBasicWareHouse() {

	    WareHouse anaDepo = new WareHouse();

	    anaDepo.setLocation("Sakarya Merkez Depo");
	    anaDepo.setPhone("0555 123 45 67");
	    anaDepo.setMinUrunSayisi(4);

	    CreateWareHouse(anaDepo);
	}
	
	public static WareHouse getWareHouseByUserId(long id) {
		return WareHouseDao.findByUser(id);
	}
	
	public static List<WareHouse> getAll(){
		return WareHouseDao.findAll();
	}
}
