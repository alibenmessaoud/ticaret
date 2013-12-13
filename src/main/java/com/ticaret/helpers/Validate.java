package com.ticaret.helpers;

import java.util.regex.Pattern;

public class Validate {

	public static boolean isEmailAddress(String editText, boolean required) {
		String regex = "^([^@\\s]+)@((?:[-a-z0-9]+\\.)+[a-z]{2,})$";
		return isValid(editText, regex, required);
	}

	public static boolean isPhoneNumber(String editText, boolean required) {
		String regex = "^\\(?\\d{3}[\\)?|-]\\d{3}[- ]\\d{4}((\\s=?)(ext\\.|x)\\d{1,4})?$";
		return isValid(editText, regex, required);
	}

	public static boolean isText(String editText, boolean required) {
		String regex = "^[-a-z0-9,; _-]+$";
		return isValid(editText, regex, required);
	}

	public static boolean isPostalCode(String editText, boolean required) {
		String regex = "^\\d{5}(-\\d{4})?$";
		return isValid(editText, regex, required);
	}

	public static boolean isValid(String editText, String regex,
			boolean required) {
		boolean validated = true;
		String text = editText.toString().trim();
		boolean hasText = hasText(editText);

		if (required && !hasText)
			validated = false;

		if (validated && hasText) {
			if (!Pattern.matches(regex, text)) {
				validated = false;
			}
		}

		return validated;
	}

	public static boolean hasText(String editText) {
		boolean validated = true;
		String text = editText.toString().trim();
		if (text.length() == 0) {
			validated = false;
		}
		return validated;
	}

}