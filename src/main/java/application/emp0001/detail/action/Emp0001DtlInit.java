package application.emp0001.detail.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import application.CheckUtil;
import application.CommonConstants;
import application.emp0001.Emp0001Constants;
import application.emp0001.Emp0001DataBean;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;

public class Emp0001DtlInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setEmployeeId(req.getParameter("paramEmployeeId"));
		// 検索処理
		Emp0001DataBean result = search(form);
		form.setEmployeeId(result.getEmployeeId());
		form.setEmployeeName(result.getEmployeeName());
		form.setSex(result.getSex());
		form.setJoinedYmd(result.getJoinedYmd());
		req.setAttribute("paramEmployeeId", form.getParamEmployeeId());
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retiredYmd", form.getRetiredYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}


	// 検索処理
	private Emp0001DataBean search(Emp0001DtlForm form) {
		Emp0001DataBean result = null;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    boolean where = false;
		    StringBuilder query = new StringBuilder();
		    query.append(Emp0001Constants.SQL_SELECT);
		    if (CheckUtil.isNotEmpty(form.getParamEmployeeId())) {
		    	query.append(where?" AND employee_id = ?":" WHERE employee_id = ?");
		    	where = true;
		    }
		    query.append(Emp0001Constants.SQL_ORDER);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				if (CheckUtil.isNotEmpty(form.getParamEmployeeId())) {
					// パラメータ指定(社員ＩＤ)
					statement.setString(i++, form.getParamEmployeeId());
				}
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						result = new Emp0001DataBean();
						result.setEmployeeId(resultSet.getString(1));
						result.setEmployeeName(resultSet.getString(2));
						result.setSex(resultSet.getString(4));
						result.setJoinedYmd(resultSet.getString(7));
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
}
