package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class CommonUtil {

	public static String getUserId(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (String)session.getAttribute("userId");
	}

	public static void dispReturn(HttpServletRequest req,HttpServletResponse resp,String dispatchUrl) throws ServletException, IOException {
		req.setAttribute("userId", req.getSession().getAttribute("userId"));
		req.setAttribute("token", req.getSession().getAttribute("token"));
		ServletContext ctx = req.getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(dispatchUrl);
		dispatcher.forward(req, resp);
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
