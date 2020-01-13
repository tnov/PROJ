package application.emp0001.list.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;

public class Emp0001LstSearch extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0001LstForm form = new Emp0001LstForm();
		form.setEmployeeId(req.getParameter("employeeId"));
		form.setEmployeeName(req.getParameter("employeeName"));
		form.setSex(req.getParameter("sex"));
		form.setJoinedYmd(req.getParameter("joinedYmd"));
		form.setRetiredYmd(req.getParameter("retiredYmd"));
		form.setDepartmentId(req.getParameter("departmentId"));
		// チェック処理
		// 検索処理
		List<Emp0001DataBean> resultList = search(form);
		form.setResultList(resultList);
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retiredYmd", form.getRetiredYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		req.setAttribute("list", form.getResultList());
		// 画面遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001LstConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}

	// 検索処理
	private List<Emp0001DataBean> search(Emp0001LstForm form) {
		List<Emp0001DataBean> resultList = null;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    boolean where = false;
		    StringBuilder query = new StringBuilder();
		    query.append(Emp0001Constants.SQL_SELECT);
		    if (CheckUtil.isNotEmpty(form.getEmployeeId())) {
		    	query.append(where?" AND employee_id = ?":" WHERE employee_id = ?");
		    	where = true;
		    }
		    if (CheckUtil.isNotEmpty(form.getEmployeeName())) {
		    	query.append(where?" AND employee_name = ?":" WHERE employee_name = ?");
		    	where = true;
		    }
		    query.append(Emp0001Constants.SQL_ORDER);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				if (CheckUtil.isNotEmpty(form.getEmployeeId())) {
					// パラメータ指定(社員ＩＤ)
					statement.setString(i++, form.getEmployeeId());
				}
				if (CheckUtil.isNotEmpty(form.getEmployeeName())) {
					// パラメータ指定(社員名)
					statement.setString(i++, form.getEmployeeName());
				}
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					resultList = new ArrayList<>();
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						Emp0001DataBean result = new Emp0001DataBean();
						result.setEmployeeId(resultSet.getString(1));
						result.setEmployeeName(resultSet.getString(2));
						result.setSex(resultSet.getString(4));
						result.setJoinedYmd(resultSet.getString(7));
						resultList.add(result);
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
		return resultList;
	}
}
