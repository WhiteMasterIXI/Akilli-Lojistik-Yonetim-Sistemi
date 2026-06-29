package Model.Orders;

import java.sql.Date;
import java.util.List;

import Model.Product;
import enums.orderStatus;

public class Order {
	List<Product> Urunler;
	int id;
	double Fiyat;
	String alici_notu;
	String gonderen_notu;
	Date SiparisTarihi;
	Date TeslimTarihi;
	orderStatus Durum;
	String IletisimAdresi;
	String TeslimAdresi;
	
	public String BilgileriGoruntule() {
		String Mesaj = "Teslim Adresi: " + TeslimAdresi + " Siparis Notu: " + alici_notu;
		return Mesaj;
	}
	public void DurumGuncelle(orderStatus durum) {}
	
	// ilk veritabanına gönderilecek ondan sonra burdan çekilecek.
	public Order(int id, String alici_not,String gonderen_not,Date SiparisTarihi,Date TeslimTarihi, orderStatus durum,String IletisimAdresi,String TeslimAdresi) {
		this.id =id;
		alici_notu = alici_not;
		gonderen_notu = gonderen_not;
		this.SiparisTarihi = SiparisTarihi;
		this.TeslimTarihi = TeslimTarihi;
		Durum = durum;
		this.IletisimAdresi = IletisimAdresi;
		this.TeslimAdresi = TeslimAdresi;
	}
}
