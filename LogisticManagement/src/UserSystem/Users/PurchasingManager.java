package Model.Users;

import java.util.List;

import Model.Product;
import Model.User;
import Model.Firms.Supplier;
import Model.Orders.Order;
import enums.Authority;

public class PurchasingManager extends User {
	int DepoId;
	List<Order> GelenUrunler;
	static List<Authority> yetkiler = List.of(Authority.URUNONAYLAMA,Authority.URUNYONET,Authority.BILDIRIMLERIGORUNTULE);
	
    public PurchasingManager(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
    }
    
	public void UrunAlveYonlendir() {}
	public void FaturaOlustur(String Konum,Supplier Tedarikci,Product Urun) {}
}
