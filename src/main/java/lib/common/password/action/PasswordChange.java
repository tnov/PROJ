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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import application.CommonConstants;
import application.CommonUtil;
import lib.common.Constants;
import lib.common.password.PasswordConstants;
import lib.util.MessageManager;
import lib.util.StringUtils;

public class PasswordChange extends HttpServlet {

	private MessageManager message = MessageManager.getInstance();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		String userId = (String)req.getSession().getAttribute("userId");
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		// セッションからユーザＩＤを取得
		req.setAttribute("userId", userId);
		req.setAttribute("password", "");
		req.setAttribute("newPassword", "");
		req.setAttribute("confirmPassword", "");
		// バリデーション
		// パスワード形式チェック
		if (StringUtils.isEmpty(userId)) {
			// ユーザＩＤを入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_USER_ID_NOT_INPUT));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.isEmpty(password)) {
			// パスワードを入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (!StringUtils.isHalfLengthOnly(newPassword)) {
			// 新パスワードは半角文字を入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_HALF_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.length(newPassword) < 8) {
			// 新パスワードは8桁以上で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_SHORT_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.length(newPassword) > 20) {
			// 新パスワードは20桁以内で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_INPUT_LARGE_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (!StringUtils.isHalfLengthOnly(confirmPassword)) {
			// 新パスワード確認用は半角文字を入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_CONFIRM_PASSWORD_INPUT_HALF_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.length(confirmPassword) < 8) {
			// 新パスワード確認用は8桁以上で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_SHORT_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.length(confirmPassword) > 20) {
			// 新パスワード確認用は20桁以内で入力してください。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_LARGE_CHARACTERS));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (!newPassword.equals(confirmPassword)) {
			// 新パスワードと新パスワード確認用が一致しません。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_NO_MATCH));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		if (newPassword.equals(password)) {
			// 新パスワードが前回パスワードと一致しています。
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_NEW_PASSWORD_DUPLICATE));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
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
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(PasswordConstants.MESSAGE_ERROR_AUTHORIZED_FAILD));
			// 画面遷移
			CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
			return;
		}
		// 認証チェック：OK（INFOメッセージ）
		// パスワードの保存に成功しました。
		setMessage(req, resp, Constants.MESSAGE_TYPE_INFO, message.getMessage(PasswordConstants.MESSAGE_INFO_NEW_PASSWORD_SAVE));
		// 画面遷移
		CommonUtil.dispReturn(req, resp, PasswordConstants.CONTENTS_PATH);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String messages) throws ServletException, IOException {
		List<String> messagesList = new ArrayList<String>();
		messagesList.add(messages);
		setMessage(req,resp,type,messagesList);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type,  List<String> messages) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute(type, messages);
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
				PreparedStatement statement = connection.prepareStatement("update authorized_user set user_password = ? , last_update_ymd = to_char(update_ymd,'YYYYMMDD') , last_update_password = user_password , update_module_id = ? , update_user_id = ? , update_ymd = CURRENT_TIMESTAMP where user_id = ? and user_password = ?")
					) {
				// パラメータ指定(パスワード)
				statement.setString(1, newPassword);
				// パラメータ指定(モジュールID)
				statement.setString(2, this.getClass().getSimpleName());
				// パラメータ指定(ユーザID)
				statement.setString(3, userId);
				// パラメータ指定(ユーザID)
				statement.setString(4, userId);
				// パラメータ指定(パスワード)
				statement.setString(5, password);
				// ログイン情報を検索
				result = statement.executeUpdate() == 1;
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
