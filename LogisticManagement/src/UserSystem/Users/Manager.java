package Model.Users;

import java.util.List;

import Model.User;
import Model.CompanyUnits.WareHouse;
import Model.Firms.Company;
import enums.Authority;

public class Manager extends User {
	 static List<Authority> yetkiler = List.of(Authority.CALISANLARIYONET,Authority.DEPOYONET,Authority.KARGOFIRMASIYONET,Authority.KULLANICILARIYONET,Authority.TEDARIKCILERIYONET,Authority.BILDIRIMLERIGORUNTULE);
	
	    public Manager(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,String rol) {
	    	super(id,isim,soyisim,adres,telefon,email,hashPass,yetkiler,rol);
	    }
	    
	    public void DepoEkle(Company Sirket) {}
	    public void KisileriListele(WareHouse Depo) {}
	    public void TedarikciEkle() {}
	    public void CalisanEkle() {}
	    public void KargoFirEkle() {}
	    public void KullaniciSil(int id) {}
	    public void TedarikciSil(int id) {}
	    public void KargoFirSil(int id) {}
	    public void TedarikciListele() {}
	    public void KargoFirListele() {}
	    
}
