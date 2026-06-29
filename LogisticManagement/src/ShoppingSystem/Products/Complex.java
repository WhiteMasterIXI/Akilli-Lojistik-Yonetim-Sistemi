package Model.Products;

import java.util.List;

import Model.Product;

public class Complex extends Product {
	List<Product> parcalar;
	
	public Complex(int id,String isim,int miktar,float fiyat) {
		super(id,isim,miktar,fiyat);
	}

	@Override
	public void BilgileriGoruntule() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public float FiyatHesapla() {
		return 0;
	}
}
