package application.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import application.CommonConstants;
import application.proj.entity.MstAuthorizedUser;

public class MstAuthorizedUserDao {

	public static final String SQL_SELECT_PK = "SELECT user_id, user_password, last_update_ymd, last_update_password, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM authorized_user WHERE user_id = ? ORDER BY user_id";
	public static final String SQL_INSERT = "INSERT INTO authorized_user(user_id, user_password, last_update_ymd, last_update_password, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String SQL_UPDATE = "UPDATE authorized_user SET user_password = ? , last_update_ymd = ? , last_update_password = ? , delete_flg = ? , create_module_id = ? , create_user_id = ? , create_ymd = ? , update_module_id = ? , update_user_id = ? , update_ymd = ? WHERE user_id = ?";
	public static final String SQL_DELETE = "DELETE FROM authorized_user WHERE user_id = ?";

	public MstAuthorizedUser getMstAuthorizedUser(MstAuthorizedUser entity) {
		MstAuthorizedUser result = null;
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
				// パラメータ指定(ユーザＩＤ)
				statement.setString(i++, entity.getUserId());
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						result = new MstAuthorizedUser();
						// user_id
						result.setUserId(resultSet.getString(1));
						// user_password
						result.setUserId(resultSet.getString(2));
						// last_update_ymd
						result.setUserId(resultSet.getString(3));
						// last_update_password
						result.setUserId(resultSet.getString(4));
						// delete_flg
						result.setDeleteFlg(resultSet.getString(5));
						// create_module_id
						result.setCreateModuleId(resultSet.getString(6));
						// create_user_id
						result.setCreateUserId(resultSet.getString(7));
						// create_ymd
						result.setCreateYmd(resultSet.getString(8));
						// update_module_id
						result.setUpdateModuleId(resultSet.getString(9));
						// update_user_id
						result.setUpdateUserId(resultSet.getString(10));
						// update_ymd
						result.setUpdateYmd(resultSet.getString(11));
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
	public boolean insert(MstAuthorizedUser entity) {
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
				// user_id
				statement.setString(i++, entity.getUserId());
				// user_password
				statement.setString(i++, entity.getUserPassword());
				// last_update_ymd
				statement.setString(i++, entity.getLastUpdateYmd());
				// last_update_password
				statement.setString(i++, entity.getLastUpdatePassword());
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
	public boolean update(MstAuthorizedUser entity,MstAuthorizedUser key) {
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
				// user_id
				statement.setString(i++, entity.getUserId());
				// user_password
				statement.setString(i++, entity.getUserPassword());
				// last_update_ymd
				statement.setString(i++, entity.getLastUpdateYmd());
				// last_update_password
				statement.setString(i++, entity.getLastUpdatePassword());
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
				statement.setString(i++, key.getUserId());
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
