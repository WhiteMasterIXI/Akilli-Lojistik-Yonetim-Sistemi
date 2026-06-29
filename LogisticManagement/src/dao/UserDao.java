package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;
import Model.Users.Customer;
import Model.Users.Employee;
import Model.Users.Manager;
import Model.Users.PurchasingManager;
import Model.Users.StoreManager;
import Model.Users.TransporterEmployee;
import Services.Notification;
import Utils.DBConnection;
import Utils.HashUtil;
import enums.Authority;

public class UserDao {

	private static User DetectUserRole(int type,ResultSet rs) throws SQLException {
		String rol = " ";
		User kullanici = null;
		switch (type) {
		case 1:
			rol = "Yonetici";
			kullanici = new Manager(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
		case 2:
			rol = "Urun Yoneticisi";
			kullanici = new PurchasingManager(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
			break;
		case 3:
			rol = "Depo Yoneticisi";
			kullanici = new StoreManager(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
			break;
		case 4:
			rol = "Kargo Calisani";
			kullanici = new TransporterEmployee(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
			break;
		case 5:
			rol = "Musteri";
			kullanici = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
			break;
		case 6:
			rol = "Calisan";
			kullanici = new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("telephone"),rs.getString("email"),rs.getString("password"),rol);
			break;
		default:
			kullanici = null;
		}
		
		return kullanici;
	}
	
	public static User getUser(String Email,String Password) throws SQLException{
		String Query = "SELECT * FROM users WHERE email = ? ";
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			
			Password = HashUtil.bcryptHash(Password);
			
			ps.setString(1, Password);
			ps.setString(2, Email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int type = rs.getInt("type");
				return DetectUserRole(type,rs);
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean isExist(String Email) {
		String Query = "SELECT * FROM users WHERE email = ?";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setString(1,Email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<User> GetUsers() throws SQLException{
		List<User> kullanicilar = new ArrayList<>();
		String Query = "SELECT id,name,surname,address,telephone,email.type FROM users";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ResultSet rs = ps.executeQuery();
			int type = -1;
	// public User(int id,String isim,String soyisim,String adres,String telefon,String email,String hashPass,List<Authority> yetkiler)
			while(rs.next()) {
				type = rs.getInt("type");
				kullanicilar.add(DetectUserRole(type,rs));
			}
			return kullanicilar;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String DeleteUser(int user_id) throws SQLException{
		String Query = "DELETE FROM users WHERE id = ?";
		
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(Query)){
				ps.setInt(1, user_id);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return "Kisi basariyla silindi.";
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Kisi silinemedi";
	}
	
	public static String UpdateUser(int user_id,String Ad,String Soyad,String Adres,String telefon_no,String Email,String password,int type) throws SQLException {
		String Query = "UPDATE users SET name = ?,surname = ?,address = ?,telephone = ?,email = ?,password = ?,type = ? WHERE id = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setString(1, Ad);
			ps.setString(2, Soyad);
			ps.setString(3, Adres);
			ps.setString(4, telefon_no);
			ps.setString(5, Email);
			ps.setString(6, password);
			ps.setInt(7, type);
			ps.setInt(8, user_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return (Ad + " adli kisi basariyla güncellendi");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Kisi güncellenemedi.";
	}
	
	
	public static String AddUser(String Ad,String Soyad,String Adres,String telefon_no,String Email,String password,int type) throws SQLException {
		String Query = "INSERT INTO users name,surname,address,telephone,email,password,type VALUES (?,?,?,?,?,?,?)";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(Query)){
			ps.setString(1, Ad);
			ps.setString(2, Soyad);
			ps.setString(3, Adres);
			ps.setString(4, telefon_no);
			ps.setString(5, Email);
			ps.setString(6, password);
			ps.setInt(7, type);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return (Ad + " adli basariyla eklendi.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Kisi ekleme basarisiz.";
	}
}
