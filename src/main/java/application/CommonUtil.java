package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class CommonUtil {

	public static String getUserId(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (String)session.getAttribute("userId");
	}


	/**
	 * 部署一覧を取得
	 *
	 * @return departmentMap 部署一覧Map
	 */
	public static HashMap<String, String> getDepartment() {

		HashMap<String, String> departmentMap = new HashMap<String, String>();

		try {
		// JNDIからDBデータソース取得
	    InitialContext initialContext = new InitialContext();
	    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);

	    StringBuilder query = new StringBuilder();
	    query.append("SELECT department_id,department_name FROM mst_department");

	    // コネクションの取得
	    // SQL実行
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query.toString())) {

			// ログイン情報を検索
			if (!statement.execute()) {
				// データなし
			} else {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					departmentMap.put(resultSet.getString(1),resultSet.getString(2));
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

		return departmentMap;
	}
}
