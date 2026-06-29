package Model.Users;

import java.util.List;

import Model.User;
import Model.Orders.Cart;
import enums.Authority;

public class Employee extends User {
	 public static List<Authority> yetkiler = List.of(Authority.PROFILGORUNTULE,Authority.BILDIRIMLERIGORUNTULE);
		
	    public Employee(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
	    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
	    }
}
