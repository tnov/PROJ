package lib.common.password.action;

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
import lib.common.password.PasswordConstants;
import lib.util.StringUtils;

public class PasswordChange extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		// バリデーション
		// パスワード形式チェック
		if (StringUtils.isEmpty(userId)) {
			// ユーザＩＤを入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_USER_ID_NOT_INPUT);
			return;
		}
		if (StringUtils.isEmpty(password)) {
			// パスワードを入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT);
			return;
		}
		if (StringUtils.isHalfLengthOnly(newPassword)) {
			// 新パスワードは半角文字を入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_HALF_CHARACTERS);
			return;
		}
		if (StringUtils.length(newPassword) < 8) {
			// 新パスワードは8桁以上で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_SHORT_CHARACTERS);
			return;
		}
		if (StringUtils.length(newPassword) > 20) {
			// 新パスワードは20桁以内で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_LARGE_CHARACTERS);
			return;
		}
		if (StringUtils.isHalfLengthOnly(confirmPassword)) {
			// 新パスワード確認用は半角文字を入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_CONFIRM_PASSWORD_INPUT_HALF_CHARACTERS);
			return;
		}
		if (StringUtils.length(confirmPassword) < 8) {
			// 新パスワード確認用は8桁以上で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_SHORT_CHARACTERS);
			return;
		}
		if (StringUtils.length(confirmPassword) > 20) {
			// 新パスワード確認用は20桁以内で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_LARGE_CHARACTERS);
			return;
		}
		if (newPassword.contentEquals(confirmPassword)) {
			// 新パスワードと新パスワード確認用が一致しません。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_NO_MATCH);
			return;
		}
		if (newPassword.contentEquals(password)) {
			// 新パスワードが前回パスワードと一致しています。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_DUPLICATE);
			return;
		}
		// 認証チェック
		if (isLogin(userId,password)) {
			// 認証チェック：ＯＫ
			// パスワードの変更
			changePassword(userId,password,newPassword);
		} else {
			// 認証チェック：ＮＧ（エラーメッセージ）
			// ユーザＩＤまたは、パスワードが正しくありません。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, PasswordConstants.MESSAGE_ERROR_AUTHORIZED_FAILD);
			return;
		}
	}

	private void setForward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// メニュー遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(PasswordConstants.DISPATCH_PATH);
		dispatcher.forward(req, resp);
	}

	// メッセージ出力(TOOD 共通化)
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
		setForward(req, resp);
	}

	// 認証チェック(TOOD 共通化)
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

	// パスワードの変更
	private boolean changePassword(String userId,String password,String newPassword) {
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
