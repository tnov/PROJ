package application.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import application.CommonConstants;
import application.database.entity.MstCustomer;

public class MstCustomerDao {

	public static final String SQL_SELECT_PK = "SELECT customer_id, customer_name, zip_code, address, liaison, staff,  tel, development_flg, maintenance_flg, operation_flg, infrastructure_flg, agree_ymd, agree_status, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd FROM mst_customer WHERE customer_id = ?";
	public static final String SQL_INSERT = "INSERT INTO mst_customer(customer_id, customer_name, zip_code, address, liaison, staff, tel, development_flg, maintenance_flg, operation_flg, infrastructure_flg, agree_ymd, agree_status, delete_flg, create_module_id, create_user_id, create_ymd, update_module_id, update_user_id, update_ymd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, CURRENT_TIMESTAMP)";
	public static final String SQL_UPDATE = "UPDATE mst_customer set customer_name = ? , zip_code = ? , address = ? , liaison = ? , staff = ? , tel = ? , development_flg = ? , maintenance_flg = ? , operation_flg = ? , infrastructure_flg = ? , agree_ymd = ? , agree_status = ? , update_module_id = ? , update_user_id = ? , update_ymd = CURRENT_TIMESTAMP WHERE customer_id = ?";
	public static final String SQL_DELETE = "UPDATE mst_customer set delete_flg = '1' , update_module_id = ? , update_user_id = ? , update_ymd = CURRENT_TIMESTAMP WHERE customer_id = ?";

	public MstCustomer getMstCustomer(MstCustomer entity) {
		MstCustomer result = null;
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
				// パラメータ指定(顧客ＩＤ)
				statement.setString(i++, entity.getCustomerId());
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						result = new MstCustomer();
						result.setCustomerId(resultSet.getString(1));
						result.setCustomerName(resultSet.getString(2));
						result.setZipCode(resultSet.getString(3));
						result.setAddress(resultSet.getString(4));
						result.setLiaison(resultSet.getString(5));
						result.setStaff(resultSet.getString(6));
						result.setTel(resultSet.getString(7));
						result.setDevelopmentFlg(resultSet.getString(8));
						result.setMaintenanceFlg(resultSet.getString(9));
						result.setOperationFlg(resultSet.getString(10));
						result.setInfrastructureFlg(resultSet.getString(11));
						result.setAgreeYmd(resultSet.getString(12));
						result.setAgreeStatus(resultSet.getString(13));
						result.setDeleteFlg(resultSet.getString(14));
						result.setCreateModuleId(resultSet.getString(15));
						result.setCreateUserId(resultSet.getString(16));
						result.setCreateYmd(resultSet.getTimestamp(17));
						result.setUpdateModuleId(resultSet.getString(18));
						result.setUpdateUserId(resultSet.getString(19));
						result.setUpdateYmd(resultSet.getTimestamp(20));
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
	public boolean insert(MstCustomer entity) {
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
				statement.setString(i++, entity.getCustomerId());
				statement.setString(i++, entity.getCustomerName());
				statement.setString(i++, entity.getZipCode());
				statement.setString(i++, entity.getAddress());
				statement.setString(i++, entity.getLiaison());
				statement.setString(i++, entity.getStaff());
				statement.setString(i++, entity.getTel());
				statement.setString(i++, entity.getDevelopmentFlg());
				statement.setString(i++, entity.getMaintenanceFlg());
				statement.setString(i++, entity.getOperationFlg());
				statement.setString(i++, entity.getInfrastructureFlg());
				statement.setString(i++, entity.getAgreeYmd());
				statement.setString(i++, entity.getAgreeStatus());
				statement.setString(i++, CommonConstants.DELETE_FLG_OFF);
				statement.setString(i++, entity.getCreateModuleId());
				statement.setString(i++, entity.getCreateUserId());
				statement.setString(i++, entity.getUpdateModuleId());
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
	public boolean update(MstCustomer entity) {
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
				statement.setString(i++, entity.getCustomerName());
				statement.setString(i++, entity.getZipCode());
				statement.setString(i++, entity.getAddress());
				statement.setString(i++, entity.getLiaison());
				statement.setString(i++, entity.getStaff());
				statement.setString(i++, entity.getTel());
				statement.setString(i++, entity.getDevelopmentFlg());
				statement.setString(i++, entity.getMaintenanceFlg());
				statement.setString(i++, entity.getOperationFlg());
				statement.setString(i++, entity.getInfrastructureFlg());
				statement.setString(i++, entity.getAgreeYmd());
				statement.setString(i++, entity.getAgreeStatus());
				statement.setString(i++, entity.getUpdateModuleId());
				statement.setString(i++, entity.getUpdateUserId());
				statement.setString(i++, entity.getCustomerId());
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
	public boolean delete(MstCustomer entity) {
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
		    	// employee_id
				statement.setString(i++, entity.getCustomerId());
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
