package Controller;
import java.sql.SQLException;
import java.util.List;

import BuildingSystem.CompanyDepartments.WareHouse;
import RoleSystem.Role;
import Services.UserService;
import UserSystem.User;
import dao.RoleDao;
import dao.UserDao;

public class UserController {
	
	public static User Login(String Email,String Password) throws SQLException {
		User user = UserService.LoginUser(Email, Password);
		return user;
	}
	
	public static User getUserByEmail(String email) {
		return UserDao.findByEmail(email);
	}
	
	public static String Register(User user) {
		if(UserService.RegisterUser(user))
		return "Kayit Basariyla Olusturuldu.";
		return "Bir hata meydana geldi, Lutfen bir süre sonra tekrar deneyiniz.";
	}
	
	public static boolean ChangeUserInfo(User user) {
		return UserService.UpdateUserInfo(user);
	}
	
	public static User GetUser(int id){
		return UserService.GetUser(id);
	}
	
	public static List<User> ListUsers(){
		return UserService.GetAllUser();
	}
	
	public static List<User> getUsersByRoleId(long role_id){
		return UserService.getUsersByRoleId(role_id);
	}
	
	public static String DeleteUser(int id) {
		if(UserService.DeleteUser(id))
			return "Kisi Basariyla Silindi.";
		return "Islem Basarisiz, lutfen daha sonra tekrar deneyiniz.";
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
}
