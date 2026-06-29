package Model.Users;

import java.util.List;

import Model.User;
import Model.Orders.Order;
import enums.Authority;
import enums.orderStatus;

public class TransporterEmployee extends User {
	List<Order> Siparisler;
	 public static List<Authority> yetkiler = List.of(Authority.PROFILGORUNTULE,Authority.BILDIRIMLERIGORUNTULE,Authority.TUMSIPARISLERIGORUNTULE);
		
	    public TransporterEmployee(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
	    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
	    }
	
	public void SiparisDurum(orderStatus Durum, Order siparis) {
		siparis.DurumGuncelle(Durum);
		// veri tabanındada update et
	}
	public void SiparisleriGoruntule() {
		for(Order siparis : Siparisler) {
			siparis.BilgileriGoruntule();
		}
	}
	public void SipIcerikListele(Order siparis) {
		siparis.BilgileriGoruntule();
	}
	public void SiparisNotEkle(Order siparis) {}
	
}
