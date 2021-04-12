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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import application.CheckUtil;
import application.CommonConstants;
import application.CommonUtil;
import application.DateUtil;
import application.emp0001.Emp0001Constants;
import application.emp0001.Emp0001DataBean;
import application.emp0001.Emp0001Util;
import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;
import lib.common.Constants;

public class Emp0001LstSearch extends HttpServlet {

	private Emp0001Util util = new Emp0001Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0001LstForm form = null;
		if ("1".equals(req.getParameter("backMode"))) {
			// 戻るボタン遷移時
			// 検索結果を戻す
			HttpSession session = req.getSession(false);
			form = (Emp0001LstForm)session.getAttribute("Emp0001LstSearch");

			// 未検索の場合
			if(form == null) {
				form = new Emp0001LstForm();
				form.setEmployeeId("");
				form.setEmployeeName("");
				form.setSex(null);
				form.setJoinedYmdFrom("");
				form.setJoinedYmdTo("");
				form.setRetiredYmd("");
				form.setDepartmentId("");
				form.setDepartmentMap(CommonUtil.getDepartment());
				form.setCurrentPage("0");
				form.setPageSize("0");
				form.setLineLimit("1000");
				form.setLineSize("5");
				req.setAttribute("form", form);
				// 画面遷移
				CommonUtil.dispReturn(req, resp, Emp0001LstConstants.CONTENTS_PATH);
				return;
			}
		} else {
			// リクエストの取得
			form = new Emp0001LstForm();
			form.setEmployeeId(req.getParameter("employeeId"));
			form.setEmployeeName(req.getParameter("employeeName"));
			form.setSex(req.getParameterValues("sex"));
			form.setJoinedYmdFrom(req.getParameter("joinedYmdFrom"));
			form.setJoinedYmdTo(req.getParameter("joinedYmdTo"));
			form.setRetiredYmd(req.getParameter("retiredYmd"));
			form.setDepartmentId(req.getParameter("departmentId"));
			form.setDepartmentMap(CommonUtil.getDepartment());
			form.setMovePath(req.getParameter("movePath"));
			form.setCurrentPage(req.getParameter("currentPage"));
			form.setLineLimit(req.getParameter("lineLimit"));
			form.setLineSize(req.getParameter("lineSize"));
			form.setPageSize(req.getParameter("pageSize"));
		}
		// チェック処理
		List<String> messages = util.searchCheck(form);
		if (CheckUtil.isNotEmpty(messages)) {
			req.setAttribute("form", form);

			// チェック処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, messages,form);
			// 元画面遷移
			CommonUtil.dispReturn(req, resp, Emp0001LstConstants.CONTENTS_PATH);
			return;
		}
		// 検索処理
		List<Emp0001DataBean> resultList = search(form);

		// 検索結果保持
		HttpSession session = req.getSession(false);
		session.setAttribute("Emp0001LstSearch", form);

		form.setResultList(resultList);
		// 件数取得
		form.setPageSize(getPageSize(form));
		if (form.getPageSize().equals("0")) {
			form.setCurrentPage("0");
		}
		req.setAttribute("form", form);
		// 画面遷移
		CommonUtil.dispReturn(req, resp, Emp0001LstConstants.CONTENTS_PATH);
	}
	// 検索処理
	private String getPageSize(Emp0001LstForm form) {
		int rowcount = 0;
		try {
			// JNDIからDBデータソース取得
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
			// QUERY生成
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) FROM (");
			query.append(Emp0001Constants.SQL_SELECT);
			query.append(createQueryWhere(form));
			query.append(Emp0001Constants.SQL_ORDER);
			query.append(") AS ROWSIZE");
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
					statement.setString(i++, "%" + form.getEmployeeName() + "%");
				}
				if (form.getSex() != null) {
					if(form.getSex().length == 1) {
						// パラメータ指定(性別)
						statement.setString(i++, form.getSex()[0]);
					}
				}
				if (CheckUtil.isNotEmpty(form.getDepartmentId())) {
					// パラメータ指定(部署名)
					statement.setString(i++, form.getDepartmentId());
				}
				if (CheckUtil.isNotEmpty(form.getJoinedYmdFrom())) {
					// パラメータ指定(入社年月日From)
					statement.setString(i++, DateUtil.unformatDateString(form.getJoinedYmdFrom(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
				}
				if (CheckUtil.isNotEmpty(form.getJoinedYmdTo())) {
					// パラメータ指定(入社年月日To)
					statement.setString(i++, DateUtil.unformatDateString(form.getJoinedYmdTo(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
				}

				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						rowcount = resultSet.getInt(1);
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
		return String.valueOf(rowcount/Integer.parseInt(form.getLineSize()) + (rowcount%Integer.parseInt(form.getLineSize()) > 0 ? 1 : 0)) ;
	}

	// 検索処理
	private List<Emp0001DataBean> search(Emp0001LstForm form) {
		List<Emp0001DataBean> resultList = null;
		try {
			// JNDIからDBデータソース取得
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
			// QUERY生成
			StringBuilder query = new StringBuilder();
			query.append(Emp0001Constants.SQL_SELECT);
			query.append(createQueryWhere(form));
			query.append(Emp0001Constants.SQL_ORDER);
			if (CheckUtil.isNotEmpty(form.getLineSize())) {
				query.insert(0, "select * from (");
				query.append(") AS LIST LIMIT ? OFFSET ?");
			}
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
					statement.setString(i++, "%" + form.getEmployeeName() + "%");
				}
				if (form.getSex() != null) {
					if(form.getSex().length == 1) {
						// パラメータ指定(性別)
						statement.setString(i++, form.getSex()[0]);
					}
				}
				if (CheckUtil.isNotEmpty(form.getDepartmentId())) {
					// パラメータ指定(部署名)
					statement.setString(i++, form.getDepartmentId());
				}
				if (CheckUtil.isNotEmpty(form.getJoinedYmdFrom())) {
					// パラメータ指定(入社年月日From)
					statement.setString(i++, DateUtil.unformatDateString(form.getJoinedYmdFrom(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
				}
				if (CheckUtil.isNotEmpty(form.getJoinedYmdTo())) {
					// パラメータ指定(入社年月日To)
					statement.setString(i++, DateUtil.unformatDateString(form.getJoinedYmdTo(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
				}
				if (CheckUtil.isNotEmpty(form.getLineSize())) {
					// パラメータ指定(開始行、末尾行)
					statement.setInt(i++, Integer.parseInt(form.getLineSize()));
					statement.setInt(i++, (Integer.parseInt(form.getCurrentPage())-1) * Integer.parseInt(form.getLineSize()));
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
						result.setSex(resultSet.getString(3));
						result.setJoinedYmd(DateUtil.formatDateString(resultSet.getString(8), DateUtil.DATE_FORMAT_YYYYMMDD_SEPARATE));
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

	private StringBuilder createQueryWhere(Emp0001LstForm form) {

		boolean where = false;
		StringBuilder query = new StringBuilder();

		// SQL条件：社員ID
		if (CheckUtil.isNotEmpty(form.getEmployeeId())) {
			query.append(where?" AND employee_id = ?":" WHERE employee_id = ?");
			where = true;
		}
		// SQL条件：社員名
		if (CheckUtil.isNotEmpty(form.getEmployeeName())) {
			query.append(where?" AND employee_name LIKE ?":" WHERE employee_name LIKE ?");
			where = true;
		}
		// SQL条件：性別
		if (form.getSex() != null) {
			if(form.getSex().length == 1) {
				query.append(where?" AND sex = ?":" WHERE sex = ?");
				where = true;
			}else if(form.getSex().length == 0) {
				query.append(where?" AND 1=2 ":" WHERE 1=2 ");
				where = true;
			}
		}
		// SQL条件：部署名
		if (CheckUtil.isNotEmpty(form.getDepartmentId())) {
			query.append(where?" AND department_id = ?":" WHERE department_id = ?");
			where = true;
		}
		// SQL条件：入社年月日From
		if (CheckUtil.isNotEmpty(form.getJoinedYmdFrom())) {
			query.append(where?" AND joined_ymd >= ?":" WHERE joined_ymd >= ?");
			where = true;
		}
		// SQL条件：入社年月日To
		if (CheckUtil.isNotEmpty(form.getJoinedYmdTo())) {
			query.append(where?" AND joined_ymd <= ?":" WHERE joined_ymd <= ?");
		}
		return query;

	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String messages, Emp0001LstForm form) throws ServletException, IOException {
		List<String> messagesList = new ArrayList<String>();
		messagesList.add(messages);
		setMessage(req,resp,type,messagesList,form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type,  List<String> messages, Emp0001LstForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		req.setAttribute(type, messages);
	}
}
