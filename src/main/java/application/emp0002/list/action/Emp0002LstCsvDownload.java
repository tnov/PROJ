package application.emp0002.list.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

import application.CommonConstants;
import application.CommonUtil;
import application.DateUtil;
import application.emp0001.Emp0001Constants;
import application.emp0001.Emp0001DataBean;
import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;
import lib.common.Constants;

public class Emp0002LstCsvDownload extends HttpServlet {

	String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/csv;charset=UTF8");

		Emp0001LstForm form =null;

		// チェックボックス状態取得
		String[] checklist = req.getParameterValues("checklist");

		// 出力対象未チェックの場合
		if(checklist == null) {

			HttpSession session = req.getSession(false);
			form = (Emp0001LstForm)session.getAttribute("Emp0001LstSearch");

			req.setAttribute("form", form);

			// メッセージの設定
			List<String> messages = new ArrayList<String>();

			// TODO エラーメッセージ定数化
			messages.add("出力対象にチェックを入れてください");
			req.setAttribute(Constants.MESSAGE_TYPE_ERROR, messages);
			CommonUtil.dispReturn(req, resp, Emp0001LstConstants.CONTENTS_PATH);
			return;
		}

		// CSV出力
		String filename = URLEncoder.encode("社員一覧_"+ DateUtil.getCurrentTimestamp(DATE_FORMAT_YYYYMMDDHHMMSS)  +".csv", "UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
		try (PrintWriter pw = resp.getWriter()) {

			List<Emp0001DataBean> eployeeList = searchEployee(checklist);

			StringBuilder sb = new StringBuilder();
			for(Emp0001DataBean employee :eployeeList) {
				sb.append(CommonConstants.CSV_QUALIFIER + employee.getEmployeeId() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getEmployeeName() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getSex() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getBirthYmd() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getZipCode() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getAddress() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getTel() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getJoinedYmd() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getRetireYmd() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getDepartmentId() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_SEPARATOR)
					.append(CommonConstants.CSV_QUALIFIER + employee.getDeleteFlg() + CommonConstants.CSV_QUALIFIER)
					.append(CommonConstants.CSV_DEMILITER);
			}
			pw.write(sb.toString());
		}
	}

	/**
	 * 社員検索処理
	 *
	 * @param form
	 * @return
	 */
	private List<Emp0001DataBean> searchEployee(String[] checklist) {
		List<Emp0001DataBean> resultList = null;
		try {
			// JNDIからDBデータソース取得
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
			// QUERY作成
			StringBuilder query = new StringBuilder();
			query.append(Emp0001Constants.SQL_SELECT);
			query.append(" WHERE employee_id IN ( ");
			query.append(createInSQL(checklist.length));
			query.append(" )");
			query.append(Emp0001Constants.SQL_ORDER);

			// コネクションの取得
			// SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				for(String check : checklist) {
					statement.setString(i++, check);
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
						result.setBirthYmd(resultSet.getString(4));
						result.setZipCode(resultSet.getString(5));
						result.setAddress(resultSet.getString(6));
						result.setTel(resultSet.getString(7));
						result.setJoinedYmd(resultSet.getString(8));
						result.setRetireYmd(resultSet.getString(9));
						result.setDepartmentId(resultSet.getString(10));
						result.setDeleteFlg(resultSet.getString(11));

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

	/**
	 * IN句生成
	 *
	 * @param form
	 * @return
	 */
	private static String createInSQL(int length) {
		StringBuilder inSql = new StringBuilder();
		for (int i = 0; i < length;) {
			inSql.append("?");
			if (++i < length) {
				inSql.append(",");
			}
		}
		return inSql.toString();
	}
}
