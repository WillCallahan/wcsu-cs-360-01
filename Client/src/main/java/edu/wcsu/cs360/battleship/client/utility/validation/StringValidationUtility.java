package edu.wcsu.cs360.battleship.client.utility.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class StringValidationUtility {
	
	public static boolean hasText(String text) {
		return !(text == null || text.length() <= 0);
	}
	
	public static boolean hasEmail(String email) {
		try {
			if (!hasText(email))
				return false;
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			return true;
		} catch (AddressException e) {
			return false;
		}
	}
	
}
