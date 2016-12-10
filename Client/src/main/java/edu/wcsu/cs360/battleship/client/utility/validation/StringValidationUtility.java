package edu.wcsu.cs360.battleship.client.utility.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Common {@link String} utility methods
 */
public class StringValidationUtility {
	
	/**
	 * Checks whether or not the {@code text} has text
	 * @param text String to test
	 * @return Whether or not the {@code text} has text
	 */
	public static boolean hasText(String text) {
		return !(text == null || text.length() <= 0);
	}
	
	/**
	 * Checks if the {@code text} has a valid email address
	 * @param email String to test
	 * @return Whether or not the {@code text} has an email address
	 */
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
