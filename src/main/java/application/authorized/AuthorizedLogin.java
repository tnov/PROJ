package application.authorized;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.database.ConnectionManager;
import lib.util.StringUtils;

public class AuthorizedLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		// バリデーション
		// ユーザＩＤ・パスワード未入力ＮＧ
		if (StringUtils.isEmpty(userId)) {
			// バリデーションエラー
			error(req, resp, AuthorizedConstants.MESSAGE_ERROR_USER_ID_NOT_INPUT);
			return;
		}
		if (StringUtils.isEmpty(password)) {
			// バリデーションエラー
			error(req, resp, AuthorizedConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT);
			return;
		}
		// 認証チェック
		if (isLogin(userId,password)) {
			// 認証チェック：ＯＫ
			// セッションへ認証結果保持
			HttpSession session = req.getSession(true);
			session.setAttribute("userId", userId);
			// メニュー遷移
			ServletContext ctx = getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher(AuthorizedConstants.DISPATCH_PATH);
			dispatcher.forward(req, resp);
		} else {
			// 認証チェック：ＮＧ（エラーメッセージ）
			error(req, resp, AuthorizedConstants.MESSAGE_ERROR_AUTHORIZED_FAILD);
		}
	}

	private void error(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("userId", req.getParameter("userId"));
		// エラーメッセージの設定
		req.setAttribute("message", AuthorizedConstants.MESSAGE_ERROR_AUTHORIZED_FAILD);
		// メニュー遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(AuthorizedConstants.CONTEXT_PATH);
		dispatcher.forward(req, resp);
	}

	// 認証チェック
	private boolean isLogin(String userId,String password) {
		// コネクション取得
		Connection connection = ConnectionManager.getInstance().getConnection("empdb");

		// ログイン情報を検索
		try (PreparedStatement statement = connection.prepareStatement("select count(*) from authorized_user where user_id = ? and user_password = ?")) {
			statement.setString(1, userId);
			statement.setString(2, password);
			if (!statement.execute()) {
				// TODO エラー
				return false;
			} else {
				ResultSet resultSet = statement.getResultSet();
				int count = resultSet.getInt(1);
				return count == 1 ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
