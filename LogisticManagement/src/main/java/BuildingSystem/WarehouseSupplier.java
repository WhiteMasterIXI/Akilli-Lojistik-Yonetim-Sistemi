package BuildingSystem;

import jakarta.persistence.*;
import BuildingSystem.CompanyDepartments.WareHouse;
import ShoppingSystem.Order;

// ürünleri tedarik eden nesne
@Entity
@Table(name = "warehouse_supplier")
public class WarehouseSupplier {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse saglayici_deposu;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier tedarikci;

    // yapılan anlaşmanın fiyatı kaç gün devam edeceği ve kontrat detayları
    private double price;
    private int deliveryDays;
    private String contractDetails;
}
