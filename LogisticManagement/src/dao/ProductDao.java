package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Product;
import Model.Products.Basic;
import Model.Products.Complex;
import Utils.DBConnection;

public class ProductDao {
	
	public static ProductDao DaoPro = new ProductDao();
	
	public String AddProduct(String isim,int miktar, float fiyat) throws SQLException {
		String Query = "INSERT INTO products (name,amount,price) VALUES (?,?,?)";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setString(1, isim);
			ps.setInt(2, miktar);
			ps.setFloat(3, fiyat);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
					return "Islem Basarili" + isim + " adli urununuz olusturuldu.";
			}
		}
		
		return "Islem Basarisiz Urun Olusturulamadi";
	}
	
	public String DeleteProduct(int urun_id) throws SQLException{
		String Query = "DELETE FROM products WHERE id = ?";
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setInt(1, urun_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return "Urun Basariyla silindi.";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Islem Basarisiz Urununuz Silinemedi.";
		}
		
		return "Islem Basarisiz Urununuz Silinemedi.";
	}
	
	public String UpdateProduct(int urun_id,String yeni_isim,int yeni_miktar,float yeni_fiyat) throws SQLException{
		String Query = "UPDATE products SET name = ?,amount = ?,price = ? WHERE id = ?";
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setString(1, yeni_isim);
			ps.setInt(2,yeni_miktar);
			ps.setFloat(3,yeni_fiyat);
			ps.setInt(4, urun_id);
			
			int rs = ps.executeUpdate();
			
			if(rs != 0) {
				return "Urun basariyla guncellendi.";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Islem basarisiz urununuz guncellenemedi.";
		}
		
		return "Islem basarisiz urununuz guncellenemedi.";
	}
	
	public boolean IncreaseProduct(int urun_id,int artirilacak_miktar) {
		String Query = "UPDATE products SET amount = amount + ? WHERE id = ?";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setInt(1, artirilacak_miktar);
			ps.setInt(2, urun_id);
			
			int rs = ps.executeUpdate();
			
			if(rs != 0) {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	public boolean DecraseProduct(int urun_id ,int azaltilacak_miktar) throws SQLException {
		String Query = "SELECT * FROM products WHERE id = ?";
		int urun_miktari = 0;
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setInt(1,urun_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			
			urun_miktari = rs.getInt("amount") - azaltilacak_miktar;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if(urun_miktari < 0) {
			return false;
		}
		
		String decreaseQuery = "UPDATE products SET amount = ? WHERE id = ?";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(decreaseQuery)){
				ps.setInt(1,urun_miktari);
				ps.setInt(2,urun_id);
				
				int rs = ps.executeUpdate();
				
				if(rs == 0) {
					return false;
				}

			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		
		return true;
		
	}
	
	
	public List<Product> GetProducts(int depo_id) throws SQLException{
		
		List<Product> Urunler = new ArrayList<Product>();
		
		String Query = "SELECT * FROM products WHERE wareid = ?";
		try(Connection conn = DBConnection.getConnection();
		PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setInt(1, depo_id);
			
			ResultSet rs = ps.executeQuery(Query);
			
			while(rs.next()) {
				// public Product(int id,String isim,int miktar,double fiyat)
				// 0 basic 1 complex urun complex ürüne parca eklenebiliyor.
				int type = rs.getInt("type");
				Product urun = null;
				if(type == 0) {
					 urun = new Basic(rs.getInt("id"),rs.getString("name"),rs.getInt("amount"),rs.getFloat("price"));
				}
				else if(type == 1){
					 urun = new Complex(rs.getInt("id"),rs.getString("name"),rs.getInt("amount"),rs.getFloat("price"));
				}
				if(urun != null)
				Urunler.add(urun);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
