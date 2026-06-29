package Model.Products;

import Model.Product;

public class Basic extends Product {

	public Basic(int id,String isim,int miktar,float fiyat) {
		super(id,isim,miktar,fiyat);
	}

	@Override
	public void BilgileriGoruntule() {
		
	}

	@Override
	public float FiyatHesapla() {
		return 0;
	}

}
