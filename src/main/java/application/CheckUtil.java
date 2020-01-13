package application;

import java.util.List;

public class CheckUtil {
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}
}
