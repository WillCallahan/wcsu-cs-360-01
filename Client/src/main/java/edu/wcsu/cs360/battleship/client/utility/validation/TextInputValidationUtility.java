package edu.wcsu.cs360.battleship.client.utility.validation;

import edu.wcsu.cs360.battleship.client.domain.enumeration.CssClass;
import javafx.scene.control.TextInputControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Common {@link TextInputControl} utility methods
 */
public class TextInputValidationUtility {
	
	//region hasText Validation
	
	public static boolean hasText(TextInputControl textInputControl) {
		if (textInputControl == null)
			throw new IllegalArgumentException("Null TextInputControl provided!");
		return StringValidationUtility.hasText(textInputControl.getText());
	}
	
	public static List<TextInputControl> hasText(TextInputControl... textInputControlArray) {
		List<TextInputControl> textInputControlList = new ArrayList<>();
		for (TextInputControl textInputControl : textInputControlArray) {
			if (!hasText(textInputControl))
				textInputControlList.add(textInputControl);
		}
		return textInputControlList;
	}
	
	public static boolean hasTextOrApplyCss(TextInputControl textInputControl, String css) {
		if (!hasText(textInputControl)) {
			textInputControl.getStyleClass().addAll(css);
			return false;
		}
		return true;
	}
	
	public static boolean hasTextOrApplyCss(TextInputControl textInputControl) {
		return hasTextOrApplyCss(textInputControl, CssClass.INVALID.valueOf());
	}
	
	public static List<TextInputControl> hasTextOrApplyCss(TextInputControl... textInputControlArray) {
		List<TextInputControl> textInputControlList = new ArrayList<>();
		for (TextInputControl textInputControl : textInputControlArray) {
			if (!hasTextOrApplyCss(textInputControl))
				textInputControlList.add(textInputControl);
		}
		return textInputControlList;
	}
	
	//endregion
	
	//region hasEmailAddress Validation
	
	public static boolean hasEmailAddress(TextInputControl textInputControl) {
		if (textInputControl == null)
			throw new IllegalArgumentException("Null TextInputControl provided!");
		return StringValidationUtility.hasEmail(textInputControl.getText());
	}
	
	public static boolean hasEmailAddressOrApplyCss(TextInputControl textInputControl) {
		if (!hasEmailAddress(textInputControl)) {
			textInputControl.getStyleClass().addAll(CssClass.INVALID.valueOf());
			return false;
		}
		return true;
	}
	
	public static List<TextInputControl> hasEmailAddressOrApplyCss(TextInputControl... textInputControlArray) {
		List<TextInputControl> textInputControlList = new ArrayList<>();
		for (TextInputControl textInputControl : textInputControlArray) {
			if (!hasEmailAddressOrApplyCss(textInputControl))
				textInputControlList.add(textInputControl);
		}
		return textInputControlList;
	}
	
	public static boolean hasEmailAddressOrApplyCss(TextInputControl textInputControl, String css) {
		if (!hasEmailAddress(textInputControl)) {
			textInputControl.getStyleClass().addAll(css);
			return false;
		}
		return true;
	}
	
	//endregion
	
}