package application.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import application.CommonConstants;
import application.proj.entity.MstEmployee;

public class MstEmployeeDao {

	public static final String SQL_SELECT_PK = "SELECT employee_id, employee_name, sex, birth_ymd, zip_code, address, tel, joined_ymd, retire_ymd, department_id, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_employee WHERE employee_id = ?";
	public static final String SQL_INSERT = "INSERT INTO mst_employee (employee_id, employee_name, sex, birth_ymd, zip_code, address, tel, joined_ymd, retire_ymd, department_id, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, CURRENT_TIMESTAMP)";
	public static final String SQL_UPDATE = "UPDATE mst_employee set employee_id = ? , employee_name = ? , birth_ymd = ? , sex = ? , zip_code = ? , address = ? , tel = ? , joined_ymd = ? , retire_ymd = ? , department_id = ? , update_module_id = ? , update_user_id = ? , update_ymd = CURRENT_TIMESTAMP WHERE employee_id = ?";
	public static final String SQL_DELETE = "UPDATE mst_employee set delete_flg = '1' , update_module_id = ? , update_user_id = ? , update_ymd = CURRENT_TIMESTAMP WHERE employee_id = ?";

	public MstEmployee getMstEmployee(MstEmployee entity) {
		MstEmployee result = null;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
		    query.append(SQL_SELECT_PK);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				// パラメータ指定(社員ＩＤ)
				statement.setString(i++, entity.getEmployeeId());
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						result = new MstEmployee();
						// employee_id
						result.setEmployeeId(resultSet.getString(1));
						// employee_name
						result.setEmployeeName(resultSet.getString(2));
						// sex
						result.setSex(resultSet.getString(3));
						// birth_ymd
						result.setBirthYmd(resultSet.getString(4));
						// zip_code
						result.setZipCode(resultSet.getString(5));
						// address
						result.setAddress(resultSet.getString(6));
						// tel
						result.setTel(resultSet.getString(7));
						// joined_ymd
						result.setJoinedYmd(resultSet.getString(8));
						// retire_ymd
						result.setRetireYmd(resultSet.getString(9));
						// department_id
						result.setDepartmentId(resultSet.getString(10));
						// delete_flg
						result.setDeleteFlg(resultSet.getString(11));
						// create_module_id
						result.setCreateModuleId(resultSet.getString(12));
						// create_user_id
						result.setCreateUserId(resultSet.getString(13));
						// create_ymd
						result.setCreateYmd(resultSet.getTimestamp(14));
						// update_module_id
						result.setUpdateModuleId(resultSet.getString(15));
						// update_user_id
						result.setUpdateUserId(resultSet.getString(16));
						// update_ymd
						result.setUpdateYmd(resultSet.getTimestamp(17));
					}
				}
			} catch (SQLException e) {
				throw e;
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	// 登録処理
	public boolean insert(MstEmployee entity) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
	    	// 新規
	    	query.append(SQL_INSERT);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
		    	// employee_id
				statement.setString(i++, entity.getEmployeeId());
		    	// employee_name
				statement.setString(i++, entity.getEmployeeName());
		    	// sex
				statement.setString(i++, entity.getSex());
		    	// birth_ymd
				statement.setString(i++, entity.getBirthYmd());
		    	// zip_code
				statement.setString(i++, entity.getZipCode());
		    	// address
				statement.setString(i++, entity.getAddress());
		    	// tel
				statement.setString(i++, entity.getTel());
		    	// joined_ymd
				statement.setString(i++, entity.getJoinedYmd());
		    	// retire_ymd
				statement.setString(i++, entity.getRetireYmd());
		    	// department_id
				statement.setString(i++, entity.getDepartmentId());
		    	// delete_flg
				statement.setString(i++, CommonConstants.DELETE_FLG_OFF);
		    	// create_module_id
				statement.setString(i++, entity.getCreateModuleId());
		    	// create_user_id
				statement.setString(i++, entity.getCreateUserId());
		    	// update_module_id
				statement.setString(i++, entity.getUpdateModuleId());
		    	// update_user_id
				statement.setString(i++, entity.getUpdateUserId());
				// SQL実行
				result = statement.executeUpdate() == 1 ? true : false;
			} catch (SQLException e) {
				throw e;
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	// 更新処理
	public boolean update(MstEmployee entity,MstEmployee key) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
	    	// 新規
	    	query.append(SQL_UPDATE);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
		    	// employee_id
				statement.setString(i++, entity.getEmployeeId());
		    	// employee_name
				statement.setString(i++, entity.getEmployeeName());
		    	// birth_ymd
				statement.setString(i++, entity.getBirthYmd());
		    	// sex
				statement.setString(i++, entity.getSex());
		    	// zip_code
				statement.setString(i++, entity.getZipCode());
		    	// address
				statement.setString(i++, entity.getAddress());
				// tel
				statement.setString(i++, entity.getTel());
		    	// joined_ymd
				statement.setString(i++, entity.getJoinedYmd());
		    	// retire_ymd
				statement.setString(i++, entity.getRetireYmd());
		    	// department_id
				statement.setString(i++, entity.getDepartmentId());
		    	// update_module_id
				statement.setString(i++, entity.getUpdateModuleId());
		    	// update_user_id
				statement.setString(i++, entity.getUpdateUserId());
		    	// employee_id
				statement.setString(i++, key.getEmployeeId());
				// SQL実行
				result = statement.executeUpdate() == 1 ? true : false;
			} catch (SQLException e) {
				throw e;
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	// 削除処理
	public boolean delete(MstEmployee entity) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
	    	// 新規
	    	query.append(SQL_DELETE);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
		    	// update_module_id
				statement.setString(i++, entity.getUpdateModuleId());
		    	// update_user_id
				statement.setString(i++, entity.getUpdateUserId());
		    	// employee_id
				statement.setString(i++, entity.getEmployeeId());
				// SQL実行
				result = statement.executeUpdate() == 1 ? true : false;
			} catch (SQLException e) {
				throw e;
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

}
