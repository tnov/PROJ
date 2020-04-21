package application.emp0001;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import application.CommonConstants;

public class Emp0001Constants {

	public static final Map<String,String> SELECTION_MAP;
	static {
		Map<String,String> map = new HashMap<>();
		map.put(CommonConstants.SEX_MALE, CommonConstants.SEX_MALE_NAME);
		map.put(CommonConstants.SEX_FEMALE, CommonConstants.SEX_FEMALE_NAME);
		SELECTION_MAP = Collections.unmodifiableMap(map);
	}

	public static final String SQL_SELECT = "SELECT employee_id, employee_name, sex, birth_ymd, zip_code, address, tel, joined_ymd, retire_ymd, department_id, authorized, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_employee";
	public static final String SQL_PRIMARY_KEY = "WHERE employee_id = ?";
	public static final String SQL_ORDER = " ORDER BY employee_id";

	// 正規表現パターン 半角英数字
	public static final String REGEX_HALF_ALPHANUMERIC = "^[0-9a-zA-Z]+$";
	// 正規表現パターン 半角数字
	public static final String REGEX_NUMBER = "^[0-9]+$";

}
