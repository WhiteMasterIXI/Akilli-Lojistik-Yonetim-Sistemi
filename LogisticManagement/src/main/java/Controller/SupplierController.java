package Controller;

import java.util.List;

import BuildingSystem.Supplier;
import dao.SupplierDao;

public class SupplierController {


    public static boolean CreateSupplier(Supplier supplier) {
        return SupplierDao.save(supplier);
    }

    public static boolean UpdateSupplier(Supplier supplier) {
        return SupplierDao.update(supplier);
    }

    public static boolean DeleteSupplier(long supplier_id) {
        return SupplierDao.delete(supplier_id);
    }


    public static Supplier GetSupplierById(long supplier_id) {
        return SupplierDao.findById(supplier_id);
    }

    public static List<Supplier> GetAllSuppliers() {
        return SupplierDao.findAll();
    }

    public static List<Supplier>GetSuppliersByWareHouseId(long warehouse_id) {
        return SupplierDao.findByWareHouseId(warehouse_id);
    }

    public static Supplier GetSupplierByName(String supplier_name) {
        return SupplierDao.getByName(supplier_name);
    }
}