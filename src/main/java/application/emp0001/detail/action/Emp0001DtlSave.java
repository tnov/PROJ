package application.emp0001.detail.action;

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
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import lib.common.Constants;

public class Emp0001DtlSave extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setParamEmployeeId(req.getParameter("paramEmployeeId"));
		form.setEmployeeId(req.getParameter("employeeId"));
		form.setEmployeeName(req.getParameter("employeeName"));
		form.setSex(req.getParameter("sex"));
		form.setJoinedYmd(req.getParameter("joinedYmd"));
		form.setRetiredYmd(req.getParameter("retiredYmd"));
		form.setDepartmentId(req.getParameter("departmentId"));
		// チェック処理
		// 保存処理
		if (!save(form)) {
			// 保存処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE,form);
			return;
		}
		req.setAttribute("paramEmployeeId", form.getParamEmployeeId());
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retiredYmd", form.getRetiredYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		// 画面遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}

	// 検索処理
	private boolean save(Emp0001DtlForm form) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    boolean where = false;
		    StringBuilder query = new StringBuilder();
		    if (CheckUtil.isEmpty(form.getParamEmployeeId())) {
		    	// 新規
		    	query.append(Emp0001Constants.SQL_INSERT);
		    } else {
			    query.append(Emp0001Constants.SQL_UPDATE);
		    }
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
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						Emp0001DataBean bean = new Emp0001DataBean();
						bean.setEmployeeId(resultSet.getString(1));
						bean.setEmployeeName(resultSet.getString(2));
						bean.setSex(resultSet.getString(4));
						bean.setJoinedYmd(resultSet.getString(7));
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

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message, Emp0001DtlForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("paramEmployeeId", form.getParamEmployeeId());
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retiredYmd", form.getRetiredYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		// メッセージの設定
		List<String> messages = (List<String>)req.getAttribute(type);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
		req.setAttribute(type, messages);
		// メニュー遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
