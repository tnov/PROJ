package application.emp0001.list.action;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import application.CommonConstants;
import application.emp0001.Emp0001Constants;
import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;

public class Emp0001LstInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		Emp0001LstForm form = new Emp0001LstForm();
		form.setEmployeeId("");
		form.setEmployeeName("");
		form.setSex(null);
		form.setJoinedYmd("");
		form.setRetiredYmd("");
		form.setDepartmentId("");


		HashMap<String, String> departmentMap = new HashMap<String, String>();
		departmentMap.put("1", "aaa");
		departmentMap.put("2", "bbb");

		form.setDepartmentMap(departmentMap);
//		setDepartment();
		form.setCurrentPage("0");
		form.setLineLimit("1000");
		form.setLineSize("5");
		req.setAttribute("form", form);


		// 検索結果破棄
		HttpSession session = req.getSession(false);
		session.removeAttribute("Emp0001LstSearch");
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001LstConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}

	// 所属部署プルダウンリスト設定
	private HashMap<String, String> setDepartment() {

		HashMap<String, String> departmentMap = new HashMap<String, String>();

		try {
		// JNDIからDBデータソース取得
	    InitialContext initialContext = new InitialContext();
	    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);

	    StringBuilder query = new StringBuilder();
	    query.append(Emp0001Constants.SQL_SELECT_DEP);

	    // コネクションの取得
	    // SQL実行
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query.toString())) {
			ResultSet resultSet = statement.getResultSet();

			// ログイン情報を検索
			if (!statement.execute()) {
				// データなし
			} else {
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

	    // QUERY作成
		return departmentMap;
	}

}
