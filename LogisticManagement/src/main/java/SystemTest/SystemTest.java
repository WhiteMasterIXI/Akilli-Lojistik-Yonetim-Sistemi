package SystemTest;

import java.util.List;
import java.util.Set;

import BuildingSystem.Supplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import Controller.ProductController;
import Controller.ReqProComController;
import Controller.SupplierController;
import Controller.WareHouseController;
import RoleSystem.Authority;
import RoleSystem.Role;
import Services.UserService;
import Services.StrategyPattern.Shipments.Shipments;
import ShoppingSystem.Products.AllowedComponent;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.RequestProductComponent;
import UserSystem.User;
import dao.ProductDao;
import dao.RoleDao;
import dao.UserDao;

public class SystemTest {
	public static void InitSystemData() {
	    CreateBasicRoles();
	    AddBasicUsers();
	    CreateBasicWareHouses();
	    CreateBasicSuppliers();
	    CreateBasicProducts();
	    CreateBasicProductComponents();
	}
	
public static void CreateBasicProducts() {
		
		List<WareHouse> depolar = WareHouseController.getAll();
		WareHouse ilk_depo = depolar.getFirst();
		
		System.out.print(ilk_depo.getId());
		
		// eğer zaten varsa ekleme
		if(ProductDao.findByName("Bilgisayar") != null)return;

	    Product bilgisayar = new Product();
	    bilgisayar.setAmount(20);
	    bilgisayar.setMass(15);
	    bilgisayar.setName("Bilgisayar");
	    bilgisayar.setPrice(15000);
	    
	    ProductController.AddProduct(bilgisayar,ilk_depo);
	    
	    Product klavye = new Product();
	    klavye.setAmount(50);
	    klavye.setMass(2);
	    klavye.setName("Mekanik Klavye");
	    klavye.setPrice(2500);
	    
	    ProductController.AddProduct(klavye,ilk_depo);

	    Product mouse = new Product();
	    mouse.setAmount(70);
	    mouse.setMass(1);
	    mouse.setName("Oyuncu Mouse");
	    mouse.setPrice(1200);
	    
	    ProductController.AddProduct(mouse,ilk_depo);

	    Product monitor = new Product();
	    monitor.setAmount(15);
	    monitor.setMass(8);
	    monitor.setName("27 Inch Monitor");
	    monitor.setPrice(8500);
	    
	    ProductController.AddProduct(monitor,ilk_depo);

	    Product kulaklik = new Product();
	    kulaklik.setAmount(40);
	    kulaklik.setMass(1);
	    kulaklik.setName("Kablosuz Kulaklık");
	    kulaklik.setPrice(3200);
	    
	    ProductController.AddProduct(kulaklik,ilk_depo);

	    Product ram = new Product();
	    ram.setAmount(100);
	    ram.setMass(1);
	    ram.setName("16GB DDR5 RAM");
	    ram.setPrice(1800);
	    
	    ProductController.AddProduct(ram,ilk_depo);

	    Product ssd = new Product();
	    ssd.setAmount(60);
	    ssd.setMass(1);
	    ssd.setName("1TB SSD");
	    ssd.setPrice(2700);
	    
	    ProductController.AddProduct(ssd,ilk_depo);

	    Product ekranKarti = new Product();
	    ekranKarti.setAmount(10);
	    ekranKarti.setMass(4);
	    ekranKarti.setName("RTX 4070");
	    ekranKarti.setPrice(32000);
	    
	    ProductController.AddProduct(ekranKarti,ilk_depo);

	    Product islemci = new Product();
	    islemci.setAmount(25);
	    islemci.setMass(1);
	    islemci.setName("Intel i7 İşlemci");
	    islemci.setPrice(14000);
	    
	    ProductController.AddProduct(islemci,ilk_depo);

	    Product anakart = new Product();
	    anakart.setAmount(18);
	    anakart.setMass(3);
	    anakart.setName("Gaming Anakart");
	    anakart.setPrice(9500);
	    
	    ProductController.AddProduct(anakart,ilk_depo);
	    

	}
	
	
public static void CreateBasicRoles() {

		if(RoleDao.findByName("Admin") != null)return;

        String[] roleNames = {
                "Admin",
                "Musteri",
                "Sirket_Sahibi",
                "Depo_Yoneticisi",
                "Urun_Yoneticisi",
                "Kargo_Yetkilisi"
        };
        

        for (String name : roleNames) {

            Role role = new Role();
            role.SetName(name);

            // ROLE AUTHORITY MAPPING (basit başlangıç)
            if (name.equals("Admin")) {
                role.setAuthorities(Set.of(
                		Authority.BILGILERIMI_GORUNTULE
                ));
            }

            else if (name.equals("Musteri")) {
                role.setAuthorities(Set.of(
                		Authority.MUSTERISIPARIS_GORUNTULE,
                        Authority.URUNLERI_GORUNTULE,
                        Authority.SEPET_GORUNTULE,
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.URUN_SATINAL
                ));
            }

            else if (name.equals("Urun_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.URUN_YONET,
                        Authority.URUNLERI_GORUNTULE
                ));
            }

            else if (name.equals("Depo_Yoneticisi")) {
                role.setAuthorities(Set.of(
                        Authority.DEPO_YONET,
                        Authority.URUN_KABULETME
                ));
            }

            else if (name.equals("Kargo_Yetkilisi")) {
                role.setAuthorities(Set.of(
                        Authority.KURYESIPARIS_GORUNTULE
                ));
            }

            else if(name.equals("Sirket_Sahibi")) {
                role.setAuthorities(Set.of(
                        Authority.BILGILERIMI_GORUNTULE,
                        Authority.DEPOLARI_GORUNTULE
                ));
            }

            RoleDao.save(role);
        }
        }

public static void AddBasicUsers() {
	
	// admin eklendiyse bir daha ekleme 
	if(UserDao.findByEmail("admin@gmail.com") != null)return;

    Role adminRole = RoleDao.findByName("Admin");
    Role customerRole = RoleDao.findByName("Musteri");
    Role ownerRole = RoleDao.findByName("Sirket_Sahibi");
    Role warehouseRole = RoleDao.findByName("Depo_Yoneticisi");
    Role productRole = RoleDao.findByName("Urun_Yoneticisi");
    Role cargoRole = RoleDao.findByName("Kargo_Yetkilisi");

    // Admin
    User admin = new User();
    admin.setName("Emre");
    admin.setSurname("Koca");
    admin.setEmail("admin@gmail.com");
    admin.setPassword("123456");
    admin.setTelephone("05550000001");
    admin.setRole(adminRole);

    Register(admin);

    // Musteri
    User customer = new User();
    customer.setName("Ahmet");
    customer.setSurname("Yilmaz");
    customer.setEmail("musteri@gmail.com");
    customer.setPassword("123456");
    customer.setTelephone("05550000002");
    customer.setRole(customerRole);

    Register(customer);

    // Sirket Sahibi
    User owner = new User();
    owner.setName("Mehmet");
    owner.setSurname("Demir");
    owner.setEmail("sahip@gmail.com");
    owner.setPassword("123456");
    owner.setTelephone("05550000003");
    owner.setRole(ownerRole);

    Register(owner);

    // Depo Yoneticisi
    
    User warehouseManager = new User();
    warehouseManager.setName("Ayse");
    warehouseManager.setSurname("Kara");
    warehouseManager.setEmail("depo@gmail.com");
    warehouseManager.setPassword("123456");
    warehouseManager.setTelephone("05550000004");
    warehouseManager.setRole(warehouseRole);
    
    List<WareHouse> depolar = WareHouseController.getAll();
    WareHouse depo = depolar.getFirst();
    
    Register(warehouseManager);
    
    User new_user = UserDao.findByEmail("depo@gmail.com");
    
    depo.setManager(new_user);
    WareHouseController.UpdateWareHouse(depo);

    

    // Urun Yoneticisi
    User productManager = new User();
    productManager.setName("Zeynep");
    productManager.setSurname("Tas");
    productManager.setEmail("urun@gmail.com");
    productManager.setPassword("123456");
    productManager.setTelephone("05550000005");
    productManager.setRole(productRole);

    Register(productManager);

    // Kargo Yetkilisi
    User cargoUser = new User();
    cargoUser.setName("Ali");
    cargoUser.setSurname("Can");
    cargoUser.setEmail("kargo@gmail.com");
    cargoUser.setPassword("123456");
    cargoUser.setTelephone("05550000006");
    cargoUser.setRole(cargoRole);

    Register(cargoUser);
}

public static void CreateBasicWareHouses() {

    List<WareHouse> existing = WareHouseController.getAll();
    if (existing != null && !existing.isEmpty()) return;

    WareHouse w1 = new WareHouse();
    w1.setLocation("İstanbul Depo");

    WareHouse w2 = new WareHouse();
    w2.setLocation("Ankara Depo");

    WareHouseController.CreateWareHouse(w1);
    WareHouseController.CreateWareHouse(w2);
}

private static String Register(User user) {
	if(UserService.RegisterUser(user))
	return "Kayit Basariyla Olusturuldu.";
	return "Bir hata meydana geldi, Lutfen bir süre sonra tekrar deneyiniz.";
}

public static void CreateBasicSuppliers() {

    if (!SupplierController.GetAllSuppliers().isEmpty()) return;

    Supplier aras = new Supplier();
    aras.setName("Aras Kargo");
    aras.setBasePrice(50);
    aras.setPricePerKg(10);
    aras.setDistanceMultiplier(1.2);
    aras.setActive(true);
    aras.setShipment(Shipments.ArasKargo);

    SupplierController.CreateSupplier(aras);

    Supplier yurtici = new Supplier();
    yurtici.setName("Yurtiçi Kargo");
    yurtici.setBasePrice(45);
    yurtici.setPricePerKg(9);
    yurtici.setDistanceMultiplier(1.1);
    yurtici.setActive(true);
    yurtici.setShipment(Shipments.YurticiKargo);

    SupplierController.CreateSupplier(yurtici);
}

public static void CreateBasicProductComponents() {

    Product bilgisayar = ProductDao.findByName("Bilgisayar");
    Product ram = ProductDao.findByName("16GB DDR5 RAM");
    Product ssd = ProductDao.findByName("1TB SSD");
    Product islemci = ProductDao.findByName("Intel i7 İşlemci");

    if (bilgisayar == null) return;

    // CPU
    AllowedComponent cpu = new AllowedComponent();
    cpu.setParent(bilgisayar);
    cpu.setChild(islemci);
    ProductController.AddComponentRule(cpu);

    // RAM
    AllowedComponent ramComp = new AllowedComponent();
    ramComp.setParent(bilgisayar);
    ramComp.setChild(ram);
    ProductController.AddComponentRule(ramComp);

    // SSD
    AllowedComponent ssdComp = new AllowedComponent();
    ssdComp.setParent(bilgisayar);
    ssdComp.setChild(ssd);
    ProductController.AddComponentRule(ssdComp);
}
}
