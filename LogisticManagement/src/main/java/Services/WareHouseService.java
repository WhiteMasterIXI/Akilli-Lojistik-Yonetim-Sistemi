package Services;

import BuildingSystem.CompanyDepartments.WareHouse;
import dao.WareHouseDao;

public class WareHouseService {
	
	public static boolean CreateWareH(WareHouse new_warehouse) {
		if(!WareHouseDao.save(new_warehouse)) return false;
		
		return true;
	}
	
	public static boolean UpdateWareH(WareHouse new_warehouse) {
		if(!WareHouseDao.update(new_warehouse)) return false;
		
		return true;
		}
	
	public static boolean DeleteWareH(long id) {
		if(!WareHouseDao.delete(id)) return false;
		
		return true;
		}
	public static WareHouse getByUserId(long id) {
		return WareHouseDao.findByUser(id);
	}

}
