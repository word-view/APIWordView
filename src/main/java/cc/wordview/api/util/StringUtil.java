package cc.wordview.api.util;

public class StringUtil {
        public static String cutString(String input, String delimiter) {
                int index = input.indexOf(delimiter);
                if (index != -1) {
                        return input.substring(index);
                }
                return "";
        }
}
