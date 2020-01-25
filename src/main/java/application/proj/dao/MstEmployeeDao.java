package application.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import application.CommonConstants;
import application.emp0001.Emp0001Constants;
import application.proj.entity.MstEmployee;

public class MstEmployeeDao {

	public MstEmployee getMstEmployee(MstEmployee entity) {
		MstEmployee result = null;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
		    query.append(Emp0001Constants.SQL_SELECT_PK);
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
						// birth_ymd
						result.setBirthYmd(resultSet.getString(3));
						// sex
						result.setSex(resultSet.getString(4));
						// zip_code
						result.setZipCode(resultSet.getString(5));
						// address
						result.setAddress(resultSet.getString(6));
						// joined_ymd
						result.setJoinedYmd(resultSet.getString(7));
						// retire_ymd
						result.setRetireYmd(resultSet.getString(8));
						// department_id
						result.setDepartmentId(resultSet.getString(9));
						// authorized
						result.setAuthorized(resultSet.getString(10));
						// delete_flg
						result.setDeleteFlg(resultSet.getString(11));
						// create_module_id
						result.setCreateModuleId(resultSet.getString(12));
						// create_user_id
						result.setCreateUserId(resultSet.getString(13));
						// create_ymd
						result.setCreateYmd(resultSet.getString(14));
						// update_module_id
						result.setUpdateModuleId(resultSet.getString(15));
						// update_user_id
						result.setUpdateUserId(resultSet.getString(16));
						// update_ymd
						result.setUpdateYmd(resultSet.getString(17));
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
	    	query.append(Emp0001Constants.SQL_INSERT);
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
		    	// joined_ymd
				statement.setString(i++, entity.getJoinedYmd());
		    	// retire_ymd
				statement.setString(i++, entity.getRetireYmd());
		    	// department_id
				statement.setString(i++, entity.getDepartmentId());
		    	// authorized
				statement.setString(i++, entity.getAuthorized());
		    	// delete_flg
				statement.setString(i++, CommonConstants.DELETE_FLG_OFF);
		    	// create_module_id
				statement.setString(i++, entity.getCreateModuleId());
		    	// create_user_id
				statement.setString(i++, entity.getCreateUserId());
		    	// create_ymd
				statement.setString(i++, entity.getCreateYmd());
		    	// update_module_id
				statement.setString(i++, entity.getUpdateModuleId());
		    	// update_user_id
				statement.setString(i++, entity.getUpdateUserId());
		    	// update_ymd
				statement.setString(i++, entity.getUpdateYmd());
				// SQL実行
				return statement.execute();
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
	    	query.append(Emp0001Constants.SQL_UPDATE);
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
		    	// joined_ymd
				statement.setString(i++, entity.getJoinedYmd());
		    	// retire_ymd
				statement.setString(i++, entity.getRetireYmd());
		    	// department_id
				statement.setString(i++, entity.getDepartmentId());
		    	// authorized
				statement.setString(i++, entity.getAuthorized());
		    	// delete_flg
				statement.setString(i++, entity.getDeleteFlg());
		    	// create_module_id
				statement.setString(i++, entity.getCreateModuleId());
		    	// create_user_id
				statement.setString(i++, entity.getCreateUserId());
		    	// create_ymd
				statement.setString(i++, entity.getCreateYmd());
		    	// update_module_id
				statement.setString(i++, entity.getUpdateModuleId());
		    	// update_user_id
				statement.setString(i++, entity.getUpdateUserId());
		    	// update_ymd
				statement.setString(i++, entity.getUpdateYmd());
		    	// employee_id
				statement.setString(i++, key.getEmployeeId());
				// SQL実行
				return statement.execute();
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
