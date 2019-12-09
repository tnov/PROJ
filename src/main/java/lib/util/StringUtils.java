package lib.util;

import java.util.Base64;
import java.util.UUID;

public class StringUtils {

	public static void main(String[] args) {

	}

	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(String str) {
		return isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return isEmpty(str);
	}

	public static String trim(String str) {
		if (str == null) {
			return "";
		} else {
			return leftTrim(rightTrim(str));
		}
	}

	public static boolean isHalfLengthOnly(String str) {
		// 空白以外で判定
		String halfAlphabetNumber = "^[0-9a-zA-Z]+$";
		String halfAlphabetNumberSymbol = "^[\\p{Alnum}|\\p{Punct}]*$";
		if (isEmpty(str)) {
			return true;
		} else {
			return str.matches(halfAlphabetNumberSymbol);
		}
	}

	public static boolean isFullLengthOnly(String str) {
		String halfAlphabetNumber = "^[0-9a-zA-Z]+$";
		String halfAlphabetNumberSymbol = "^[\\p{Alnum}|\\p{Punct}]*$";

		int len = str.length();
		for (int i = 0 ; i < len ; i++) {
			if (str.substring(i, len + 1).matches(halfAlphabetNumberSymbol)) {
				return false;
			}
		}
		return true;
	}

	public static String rightTrim(String str) {
		if (str == null) {
			return null;
		} else {
			int len = str.length();
			char[] value = str.toCharArray();
			while ((0<len) && (value[len-1] == ' ') || (value[len-1] == '　')) {
				len--;
			}
			return str.substring(0,len);
		}
	}

	public static String leftTrim(String str) {
		if (str == null) {
			return null;
		} else {
			int len = str.length();
			int cur = 0;
			char[] value = str.toCharArray();
			while ((cur<len) && (value[cur] == ' ') || (value[cur] == '　')) {
				cur++;
			}
			return str.substring(cur,len);
		}
	}

	public static String leftPadding(String str,int size,CharSequence padding) {
		StringBuilder sb = new StringBuilder(size);
		int len = str.length();
		sb.append(padding, 0, size-len);
		sb.append((str==null?"":str));
		return sb.toString();
	}

	public static String rightPadding(String str,int size,CharSequence padding) {
		StringBuilder sb = new StringBuilder(size);
		int len = str.length();
		sb.append((str==null?"":str));
		sb.append(padding, size-len, size);
		return sb.toString();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static String toBase64(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}
}
