package cc.wordview.api.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestValidation {
	public static boolean hasSpecialCharacters(String string) {
		String padrao = "[a-zA-Z\\s\\u00C0-\\u00FF]+";
		Pattern pattern = Pattern.compile(padrao);
		Matcher matcher = pattern.matcher(string);

		return !matcher.matches();
	}

	public static boolean invalidEmail(String email) {
		String padrao = "[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook|yahoo|tutanota)\\.(com|br|net)";
		Pattern pattern = Pattern.compile(padrao);
		Matcher matcher = pattern.matcher(email);

		return !matcher.matches();
	}
}
