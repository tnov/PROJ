package lib.util;

import java.util.UUID;

public class StringUtils {

	public static void main(String[] args) {

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
}
