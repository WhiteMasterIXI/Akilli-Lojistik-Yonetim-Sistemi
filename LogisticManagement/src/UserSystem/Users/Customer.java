package Model.Users;

import java.util.List;

import Model.User;
import Model.Orders.Cart;
import enums.Authority;

public class Customer extends User {
	 List<Cart> Sepet;
	 public static List<Authority> yetkiler = List.of(Authority.URUNSATINAL,Authority.SEPETGORUNTULE,Authority.PROFILGORUNTULE,Authority.BILDIRIMLERIGORUNTULE);
		
	    public Customer(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
	    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
	    }
	    
	    public void UrunleriGoruntule(){}
	    public void UrunEkle() {}
	    public void UrunCikar() {}
	    public void SiparisEt() {}
	    public void SepetGoruntule() {}
	    public void SiparisleriListele() {}
	    public void SiparisGoruntule(int Siparis_id) {}
	    public void SiparisIptalEt(int Siparis_id) {}
	
}
