package lib.common.menu.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import lib.common.menu.HierarchyBean;
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
		List<HierarchyBean> menuInfoList =  getMenuInfoList(userId);
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
	private List<HierarchyBean> getMenuInfoList(String userId) {
		List<HierarchyBean> menuInfoList = new ArrayList<>();
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select mst_menu.hierarchy_id, mst_hierarchy.hierarchy_name, mst_menu.function_id, mst_function.function_name, mst_function.function_path from mst_hierarchy left join mst_menu on mst_hierarchy.hierarchy_id = mst_menu.hierarchy_id left join mst_function on mst_menu.function_id = mst_function.function_id and mst_function.delete_flg = '0' where mst_hierarchy.delete_flg = '0' and  mst_menu.delete_flg = '0' and  mst_function.delete_flg = '0' order by mst_hierarchy.hierarchy_order , mst_menu.function_order , mst_hierarchy.hierarchy_id , mst_menu.function_id")
					) {
				// メニュー情報を検索
				if (!statement.execute()) {
					return null;
				} else {
					ResultSet resultSet = statement.getResultSet();
					Set<String> keyset = new HashSet<String>();
					while (resultSet.next()) {
						HierarchyBean hierarchyBean = null;
						String hierarchyId = resultSet.getString(1);
						String hierarchyName = resultSet.getString(2);
						if (!keyset.contains(hierarchyId)) {
							// 新規追加
							keyset.add(hierarchyId);
							hierarchyBean = new HierarchyBean();
							hierarchyBean.setHierarchyId(hierarchyId);
							hierarchyBean.setHierarchyName(hierarchyName);
							hierarchyBean.setMenuList(new ArrayList<MenuBean>());
							menuInfoList.add(hierarchyBean);
						}
						hierarchyBean = menuInfoList.get(menuInfoList.size()-1);
						MenuBean menuBean = new MenuBean();
						// 画面ＩＤ
						menuBean.setMenuId(resultSet.getString(3));
						// 画面名
						menuBean.setMenuName(resultSet.getString(4));
						// 画面URL
						menuBean.setMenuPath(resultSet.getString(5));
						hierarchyBean.getMenuList().add(menuBean);
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
