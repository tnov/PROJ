package application.emp0001;

public class Emp0001Constants {
	public static final String SQL_SELECT = "SELECT employee_id, employee_name, birth_ymd, sex, zip_code, address, joined_ymd, retire_ymd, department_id, authorized, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_employee";
	public static final String SQL_PRIMARY_KEY = "WHERE employee_id = ?";
	public static final String SQL_ORDER = " ORDER BY employee_id";

	public static final String SQL_SELECT_PK = "SELECT employee_id, employee_name, birth_ymd, sex, zip_code, address, joined_ymd, retire_ymd, department_id, authorized, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_employee WHERE employee_id = ?";
	public static final String SQL_INSERT = "INSERT INTO mst_employee (employee_id, employee_name, birth_ymd, sex, zip_code, address, joined_ymd, retire_ymd, department_id, authorized, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_UPDATE = "UPDATE mst_employee set employee_id = ? , employee_name = ? , birth_ymd = ? , sex = ? , zip_code = ? , address = ? , joined_ymd = ? , retire_ymd = ? , department_id = ? , authorized = ? , delete_flg = ? , create_module_id = ? , create_user_id = ? , create_ymd = ? , update_module_id = ? , update_user_id = ? , update_ymd = ? WHERE employee_id = ?";
	public static final String SQL_DELETE = "DELETE FROM mst_employee WHERE employee_id = ?";
}
