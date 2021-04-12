package application.emp0002.list.action;

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
import application.emp0002.Emp0002Constants;
import application.emp0002.Emp0002DataBean;
import application.emp0002.Emp0002Util;
import application.emp0002.list.Emp0002LstConstants;
import application.emp0002.list.Emp0002LstForm;
import lib.common.Constants;

public class Emp0002LstSearch extends HttpServlet {

	private Emp0002Util util = new Emp0002Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0002LstForm form = null;
		if ("1".equals(req.getParameter("backMode"))) {
			// 戻るボタン遷移時
			// 検索結果を戻す
			HttpSession session = req.getSession(false);
			form = (Emp0002LstForm)session.getAttribute("Emp0002LstSearch");

			// 未検索の場合
			if(form == null) {
				form = new Emp0002LstForm();
				form.setCustomerId("");
				form.setCustomerName("");
				form.setDevelopment("1");
				form.setMaintenance("1");
				form.setOperation("1");
				form.setInfrastructure("1");
				String[] agreeStatusCheck = {"0","1","2"};
				form.setAgreeStatus(agreeStatusCheck);
				form.setCurrentPage("0");
				form.setPageSize("0");
				form.setLineLimit("1000");
				form.setLineSize("5");
				req.setAttribute("form", form);
				// 画面遷移
				CommonUtil.dispReturn(req, resp, Emp0002LstConstants.CONTENTS_PATH);
				return;
			}
		} else {
			// リクエストの取得
			form = new Emp0002LstForm();
			form.setCustomerId(req.getParameter("customerId"));
			form.setCustomerName(req.getParameter("customerName"));
			form.setDevelopment(req.getParameter("development"));
			form.setMaintenance(req.getParameter("maintenance"));
			form.setOperation(req.getParameter("operation"));
			form.setInfrastructure(req.getParameter("infrastructure"));
			form.setAgreeStatus(req.getParameterValues("agreeStatus"));
			form.setAgreeYmdFrom(req.getParameter("agreeYmdFrom"));
			form.setAgreeYmdTo(req.getParameter("agreeYmdTo"));
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
			CommonUtil.dispReturn(req, resp, Emp0002LstConstants.CONTENTS_PATH);
			return;
		}
		// 検索処理
		List<Emp0002DataBean> resultList = search(form);

		// 検索結果保持
		HttpSession session = req.getSession(false);
		session.setAttribute("Emp0002LstSearch", form);

		form.setResultList(resultList);
		// 件数取得
		form.setPageSize(getPageSize(form));
		if (form.getPageSize().equals("0")) {
			form.setCurrentPage("0");
		}
		req.setAttribute("form", form);
		// 画面遷移
		CommonUtil.dispReturn(req, resp, Emp0002LstConstants.CONTENTS_PATH);
	}
	// 検索処理
	private String getPageSize(Emp0002LstForm form) {
		int rowcount = 0;
		try {
			// JNDIからDBデータソース取得
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
			// QUERY生成
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) FROM (");
			query.append(Emp0002Constants.SQL_SELECT);
			query.append(createQueryWhere(form));
			query.append(Emp0002Constants.SQL_ORDER);
			query.append(") AS ROWSIZE");
			// コネクションの取得
			// SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				if (CheckUtil.isNotEmpty(form.getCustomerId())) {
					// パラメータ指定(顧客ＩＤ)
					statement.setString(i++, form.getCustomerId());
				}
				if (CheckUtil.isNotEmpty(form.getCustomerName())) {
					// パラメータ指定(顧客名)
					statement.setString(i++, "%" + form.getCustomerName() + "%");
				}
				if (form.getAgreeStatus() != null) {
					// パラメータ指定(契約有無)
					for( int j = 0 ; j < form.getAgreeStatus().length; j++ ) {
						statement.setString(i++, form.getAgreeStatus()[j]);
					}
				}
				if (CheckUtil.isNotEmpty(form.getAgreeYmdFrom())) {
					// パラメータ指定(契約開始日From)
					statement.setString(i++, form.getAgreeYmdFrom().replace("-", ""));
				}
				if (CheckUtil.isNotEmpty(form.getAgreeYmdTo())) {
					// パラメータ指定(契約終了日To)
					statement.setString(i++, form.getAgreeYmdTo().replace("-", ""));
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
	private List<Emp0002DataBean> search(Emp0002LstForm form) {
		List<Emp0002DataBean> resultList = null;
		try {
			// JNDIからDBデータソース取得
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
			// QUERY生成
			StringBuilder query = new StringBuilder();
			query.append(Emp0002Constants.SQL_SELECT);
			query.append(createQueryWhere(form));
			query.append(Emp0002Constants.SQL_ORDER);
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
				if (CheckUtil.isNotEmpty(form.getCustomerId())) {
					// パラメータ指定(顧客ＩＤ)
					statement.setString(i++, form.getCustomerId());
				}
				if (CheckUtil.isNotEmpty(form.getCustomerName())) {
					// パラメータ指定(顧客名)
					statement.setString(i++, "%" + form.getCustomerName() + "%");
				}
				if (form.getAgreeStatus() != null) {
					// パラメータ指定(契約有無)
					for( int j = 0 ; j < form.getAgreeStatus().length; j++ ) {
						statement.setString(i++, form.getAgreeStatus()[j]);
					}
				}
				if (CheckUtil.isNotEmpty(form.getAgreeYmdFrom())) {
					// パラメータ指定(契約開始日From)
					statement.setString(i++, form.getAgreeYmdFrom().replace("-", ""));
				}
				if (CheckUtil.isNotEmpty(form.getAgreeYmdTo())) {
					// パラメータ指定(契約終了日To)
					statement.setString(i++, form.getAgreeYmdTo().replace("-", ""));
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
						Emp0002DataBean result = new Emp0002DataBean();
						result.setCustomerId(resultSet.getString(1));
						result.setCustomerName(resultSet.getString(2));
						result.setTel(resultSet.getString(7));
						result.setAgreeYmd(resultSet.getString(12));
						result.setAgreeStatus(resultSet.getString(13));
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

	private StringBuilder createQueryWhere(Emp0002LstForm form) {

		boolean where = false;
		StringBuilder query = new StringBuilder();

		// SQL条件：顧客ID
		if (CheckUtil.isNotEmpty(form.getCustomerId())) {
			query.append(where?" AND customer_id = ?":" WHERE customer_id = ?");
			where = true;
		}
		// SQL条件：社員名
		if (CheckUtil.isNotEmpty(form.getCustomerName())) {
			query.append(where?" AND customer_name LIKE  ?":" WHERE customer_name LIKE ?");
			where = true;
		}

		// 業務種別
		if(form.getDevelopment() != null || form.getMaintenance() != null
				|| form.getOperation() != null || form.getInfrastructure() != null) {
			query.append(where?" AND ( ":" WHERE ( ");

			// SQL条件：業務種別 開発
			if (form.getDevelopment() != null) {
				query.append(" development_flg = '1' OR");
			}
			// SQL条件：業務種別 保守
			if (form.getMaintenance() != null) {
				query.append(" maintenance_flg = '1' OR");
			}
			// SQL条件：業務種別 運用
			if (form.getOperation() != null) {
				query.append(" operation_flg = '1' OR");

			}
			// SQL条件：業務種別 インフラ
			if (form.getInfrastructure() != null) {
				query.append(" infrastructure_flg = '1' OR");
			}
			query.delete(query.length() -2,query.length());
			query.append(")");

		}else {
			query.append(where?" AND ":" WHERE ");
			query.append("( development_flg = '0' AND maintenance_flg = '0' AND operation_flg = '0' AND infrastructure_flg = '0') ");
		}

		// SQL条件：契約有無
		if (form.getAgreeStatus() != null) {

			query.append(" AND agree_status IN ( ");

			for( int i = 0 ; i < form.getAgreeStatus().length; i++ ) {
				query.append("?,");
			}
			query.deleteCharAt(query.length() -1);
			query.append(")");
			where = true;
		}else {
			query.append(" AND 1=2 ");
			where = true;
		}

		// SQL条件：契約開始日From
		if (CheckUtil.isNotEmpty(form.getAgreeYmdFrom())) {
			query.append(" AND agree_ymd >= ?");
			where = true;
		}
		// SQL条件：契約終了日To
		if (CheckUtil.isNotEmpty(form.getAgreeYmdTo())) {
			query.append(" AND agree_ymd <= ?");
		}
		return query;
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String messages, Emp0002LstForm form) throws ServletException, IOException {
		List<String> messagesList = new ArrayList<String>();
		messagesList.add(messages);
		setMessage(req,resp,type,messagesList,form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type,  List<String> messages, Emp0002LstForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		req.setAttribute(type, messages);
	}
}
