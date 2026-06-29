package Services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.helpers.Util;

import UserSystem.User;
import Utils.HashUtil;
import Utils.Session;
import dao.UserDao;
import Utils.HashUtil;

public class UserService {
	
	public static User LoginUser(String Email,String password) {
		User user = null;
		try {
			user = UserDao.findByEmail(Email);
		}catch(Exception e){
			e.printStackTrace();
			Session.FailMessage = "SQL hatası meydana geldi.";
			return null;
		}
		
		if(user == null) {
			Session.FailMessage = "Böyle bir eposta bulunamadi";
			return null;
			}	
		
		System.out.println(password);
		System.out.println(user.GetPass());
		
		if(HashUtil.bcryptCheck(password, user.GetPass())) {
			Session.SuccessMessage = "Hos Geldiniz " + user.getName() + " " + user.getSurname();	
			Session.isLogged = true;
					return user;
		}
		else {
			Session.FailMessage = "Sifreniz eslesmedi lutfen dogru girdiginizden emin olunuz.";
			return null;
		}
	}
	
	public static boolean RegisterUser(User user) {
		user.setPassword(Utils.HashUtil.bcryptHash(user.GetPass()));
		Session.SuccessMessage = "Kisi basariyla olusturuldu.";
		return UserDao.save(user);
	}
	
	public static User GetUser(int id) {
		return UserDao.findById(id);
	}
	
	public static List<User> GetAllUser(){
		return UserDao.findAll();
	}
	
	public static boolean UpdateUserInfo(User user) {
		return UserDao.update(user);
	}
	
	public static boolean DeleteUser(int id) {
		return UserDao.delete(id);
	}
	
	public static List<User> getUsersByRoleId(long role_id){
		return UserDao.findByRoleId(role_id);
	}
}
