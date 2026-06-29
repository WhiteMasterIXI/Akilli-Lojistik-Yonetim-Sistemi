package Model;

public abstract class Product {
	int Id;
	String Isim;
	int Miktar;
	float AdetFiyati;
	
	public Product(int id,String isim,int miktar,float fiyat) {
		Id = id;
		Isim = isim;
		Miktar = miktar;
		AdetFiyati = fiyat;
	}
	
	public abstract void BilgileriGoruntule();
	public abstract float FiyatHesapla();
}
