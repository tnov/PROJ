package application.emp0002;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import application.CommonConstants;

public class Emp0002Constants {

	public static final Map<String,String> AGREE_STATUS_MAP;
	static {
		Map<String,String> map = new HashMap<>();
		map.put(CommonConstants.AGREE_STATUS_NO_AGREE, CommonConstants.AGREE_STATUS_NO_AGREE_NAME);
		map.put(CommonConstants.AGREE_STATUS_AGREE, CommonConstants.AGREE_STATUS_AGREE_NAME);
		map.put(CommonConstants.AGREE_STATUS_AGREE_FIN, CommonConstants.AGREE_STATUS_AGREE_FIN_NAME);
		AGREE_STATUS_MAP = Collections.unmodifiableMap(map);
	}

	public static final String SQL_SELECT = "SELECT customer_id, customer_name, zip_code, address, liaison, staff, tel, development_flg, maintenance_flg, operation_flg, infrastructure_flg, agree_ymd, agree_status, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_customer ";
	public static final String SQL_PRIMARY_KEY = "WHERE customer_id = ?";
	public static final String SQL_ORDER = " ORDER BY customer_id";

	// 正規表現パターン 半角英数字
	public static final String REGEX_HALF_ALPHANUMERIC = "^[0-9a-zA-Z]+$";
	// 正規表現パターン 半角数字
	public static final String REGEX_NUMBER = "^[0-9]+$";

}
