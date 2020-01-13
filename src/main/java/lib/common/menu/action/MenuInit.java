package lib.common.menu.action;

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

import application.CommonConstants;
import lib.common.Constants;
import lib.common.menu.MenuBean;
import lib.common.menu.MenuConstants;

public class MenuInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		String userId = (String)req.getSession().getAttribute("userId");
		// メニュー情報取得
		List<MenuBean> menuInfoList =  getMenuInfoList(userId);
		if (menuInfoList != null) {
			req.setAttribute("list", menuInfoList);
			// 画面遷移
			ServletContext ctx = getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher(MenuConstants.CONTENTS_PATH);
			dispatcher.forward(req, resp);
		} else {
			// データ有無チェック：ＮＧ（エラーメッセージ）
			setMessage(req, resp,Constants.MESSAGE_TYPE_WARNING, MenuConstants.MESSAGE_ERROR_MENU_NOT_FOUND);
		}
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("userId", req.getParameter("userId"));
		// メッセージの設定
		List<String> messages = (List<String>)req.getAttribute(type);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
		req.setAttribute(type, messages);
		// メニュー遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(MenuConstants.INIT_PATH);
		dispatcher.forward(req, resp);
	}

	// メニュー情報取得
	private List<MenuBean> getMenuInfoList(String userId) {
		List<MenuBean> menuInfoList = new ArrayList<>();
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select mst_menu.hierarchy, mst_function.function_id, mst_function.function_name, mst_function.function_path from mst_menu left join mst_function on mst_menu.function_id = mst_function.function_id and mst_function.delete_flg = '0' where mst_menu.delete_flg = '0' order by mst_menu.hierarchy, mst_menu.function_order")
					) {
				// TODO パラメータ指定(ユーザID)
				// メニュー情報を検索
				if (!statement.execute()) {
					return null;
				} else {
					ResultSet resultSet = statement.getResultSet();
					int i = 1;
					while (resultSet.next()) {
						MenuBean bean = new MenuBean();
						// 画面番号
						bean.setMenuNo(String.valueOf(i));
						// 画面ID
						bean.setMenuId(resultSet.getString(2));
						// 画面名称
						bean.setMenuName(resultSet.getString(3));
						// 画面URL
						bean.setMenuPath(resultSet.getString(4));
						menuInfoList.add(bean);
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
		if (menuInfoList == null || menuInfoList.isEmpty()) {
			return null;
		} else {
			return menuInfoList;
		}
	}
}
