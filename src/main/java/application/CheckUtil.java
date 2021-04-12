package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.util.StringUtils;

public class CheckUtil {

	// 正規表現パターン 半角英数字
	private static final String REGEX_HALF_ALPHANUMERIC = "^[0-9a-zA-Z]+$";
	// 正規表現パターン 半角数字
	private static final String REGEX_NUMBER = "^[0-9]+$";
	// 正規表現パターン 日付
	private static final String REGEX_DATE = "^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$";

	/* チェック(empty) */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/* チェック(not empty) */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/* チェック(empty list) */
	public static boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	/* チェック(not empty list) */
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	/* チェック(string) */
	// 半角
	public static boolean isHalfWidthStringWithSymbol(String str) {
		// 未入力は無視(true)
		if (isEmpty(str)) {
			return true;
		}
		if (str.length() != str.getBytes().length) {
			return false;
		}
		return true;
	}
	// 正規表現ルール https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html
	// 半角数字
	public static boolean isNumberString(String str) {
		// 未入力は無視(true)
		if (isEmpty(str)) {
			return true;
		} else {
			return regrex(REGEX_NUMBER,str);
		}
	}

	// 半角英数字
	public static boolean isHalfWidthString(String str) {
		// 未入力は無視(true)
		if (isEmpty(str)) {
			return true;
		} else {
			return regrex(REGEX_HALF_ALPHANUMERIC,str);
		}
	}

	private static boolean regrex(String regex,String str) {
		// 未入力は無視(true)
		if (isEmpty(str)) {
			return true;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/* チェック(number) */
	public static boolean isLong(String str) {
		// 未入力は無視(true)
		if (isEmpty(str)) {
			return true;
		}
		try {
			Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	/* チェック(checkbox) */
	/* チェック(radio not select) */

	/**
	 * 日付妥当性チェック
	 *
	 * @param dateStr 日付文字列
	 * @param format 日付フォーマット
	 * @return 判定
	 */
	public static boolean dateValidChk(String dateStr,String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		if (StringUtils.isEmpty(dateStr)) {
			// 未入力時はチェック不要（常にtrue）
			return true;
		} else {
			try {
				dateFormat.parse(dateStr);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
	}


}
