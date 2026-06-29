package Services;

import java.sql.SQLException;

import Model.User;
import dao.UserDao;

public class UserServices {
	
	public static User LoginUser(String Email,String password) throws SQLException {
		return UserDao.getUser(Email, password);
	}
}
