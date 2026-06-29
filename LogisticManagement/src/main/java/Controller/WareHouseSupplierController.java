package Controller;

import java.util.List;

import BuildingSystem.Supplier;
import BuildingSystem.WarehouseSupplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import dao.WareHouseSupplierDao;

public class WareHouseSupplierController {

    public static boolean Save(WarehouseSupplier ws) {
        return WareHouseSupplierDao.save(ws);
    }

    public static boolean Update(WarehouseSupplier ws) {
        return WareHouseSupplierDao.update(ws);
    }

    public static boolean Delete(long id) {
        return WareHouseSupplierDao.delete(id);
    }

    public static WarehouseSupplier GetById(long id) {
        return WareHouseSupplierDao.findById(id);
    }

    public static List<WarehouseSupplier> GetAll() {
        return WareHouseSupplierDao.findAll();
    }

    // Depoya ait tedarikçiler

    public static List<Supplier>
    GetSuppliersByWarehouse(long warehouse_id) {

        return WareHouseSupplierDao
                .findByWarehouseId(warehouse_id);
    }

    // Tedarikçiye ait depolar

    public static List<WareHouse>
    GetWarehousesBySupplier(long supplier_id) {

        return WareHouseSupplierDao
                .findBySupplierId(supplier_id);
    }
}