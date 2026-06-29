package Model.Users;

import java.util.List;

import Model.User;
import Model.Orders.Order;
import enums.Authority;

public class StoreManager extends User {
		int DepoId;
		List<Order> GelenUrunler;
		static List<Authority> yetkiler = List.of(Authority.URUNONAYLAMA,Authority.PROFILGORUNTULE,Authority.BILDIRIMLERIGORUNTULE);
		
	    public StoreManager(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
	    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
	    }
	    
	    public void GonderilenUrunleriListele() {}
	    public void UrunEkle(int Miktar) {}
	    public void UrunCikart(int Miktar) {}
	    public void UrunGuncelle(int Miktar) {}
	    public void UrunKabulEt() {}
	    
}
