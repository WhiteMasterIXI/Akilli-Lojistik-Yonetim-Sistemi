package Services;

import PaymentSystem.Account;
import Utils.Session;
import dao.AccountDao;

public class AccountService {
	public static boolean CreateAccount(Account acc) {
		if(AccountDao.save(acc))return true;
			
		return false;
	}
	
	public static boolean UpdateAccount(Account acc) {
		if(AccountDao.update(acc))return true;
			
		return false;
	}
	
	public static boolean DeleteAccount(Account acc) {
		if(acc.getBalance() != 0) {
			Session.FailMessage = "Hesabinizdaki bakiye bulunmaktadır.";
			return false;
		}
		
		if(AccountDao.delete(acc.getId()))
			return true;
		
		return false;
	}
}
