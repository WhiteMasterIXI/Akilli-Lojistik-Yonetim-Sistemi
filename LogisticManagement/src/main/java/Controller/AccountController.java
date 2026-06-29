package Controller;

import PaymentSystem.Account;
import Services.AccountService;
import dao.AccountDao;

public class AccountController {
	public static boolean UpdateAccount(Account acc) {
		return AccountService.UpdateAccount(acc);
	}
	
	public static boolean DeleteAccount(Account acc) {
		return AccountService.DeleteAccount(acc);
	}
	
	public static boolean AddAccount(Account acc) {
		return AccountService.CreateAccount(acc);
	}
}
