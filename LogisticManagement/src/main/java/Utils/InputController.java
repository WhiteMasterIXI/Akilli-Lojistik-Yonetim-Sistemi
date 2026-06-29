package Utils;

import java.util.Scanner;

public class InputController {
	static Scanner okuyucu = new Scanner(System.in);
	
	public static Boolean isEmail(String Email) {
		if(Email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}(\\.[A-Za-z]{2,6})?$") ) {
			return true;
		}
		
		return false;
	}
	
	private static Boolean isString(String metin) {
		for(char c : metin.toCharArray()) {
			if(Character.isDigit(c)) return false;
		}
		return true;
	}
	
	private static Boolean isNumber(String metin) {
		for(char c : metin.toCharArray()) {
			if(!Character.isDigit(c)) return false;
		}
		return true;
	}
	
	
}
