package lib.common.login.action;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import application.CommonConstants;
import lib.common.Constants;
import lib.common.login.LoginConstants;
import lib.util.StringUtils;

public class LoginCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		// バリデーション
		// ユーザＩＤ・パスワード未入力ＮＧ
		if (StringUtils.isEmpty(userId)) {
			// バリデーションエラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, LoginConstants.MESSAGE_ERROR_USER_ID_NOT_INPUT);
			return;
		}
		if (StringUtils.isEmpty(password)) {
			// バリデーションエラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, LoginConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT);
			return;
		}
		// 認証チェック
		if (isLogin(userId,password)) {
			// 認証チェック：ＯＫ
			// セッションへ認証結果保持
			HttpSession session = req.getSession(true);
			session.setAttribute("ip", req.getRemoteAddr());
			session.setAttribute("host", req.getRemoteHost());
			// TODO トークンはユーザＩＤとタイムスタンプ、キー値(UID)から生成
			// キー値はログイン時に生成し、セッションで保持
			session.setAttribute("userId", "");
			// キー値はログイン時に生成し、セッションで保持
			session.setAttribute("token", "");
			// メニュー遷移
			ServletContext ctx = getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher(LoginConstants.DISPATCH_PATH);
			dispatcher.forward(req, resp);
		} else {
			// 認証チェック：ＮＧ（エラーメッセージ）
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, LoginConstants.MESSAGE_ERROR_AUTHORIZED_FAILD);
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
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(LoginConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}

	// 認証チェック
	private boolean isLogin(String userId,String password) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select count(*) from authorized_user where user_id = ? and user_password = ?")
					) {
				// パラメータ指定(ユーザID)
				statement.setString(1, userId);
				// パラメータ指定(パスワード)
				statement.setString(2, password);
				// ログイン情報を検索
				if (!statement.execute()) {
					return false;
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						// 件数が1件の場合 true：正常
						// 件数が1件以外の場合 false：異常
						int count = resultSet.getInt(1);
						result = count == 1 ? true : false;
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
