package lib.common.login.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import application.DateUtil;
import lib.common.Constants;
import lib.common.login.LoginConstants;
import lib.util.MessageManager;
import lib.util.SessionUtil;
import lib.util.StringUtils;

public class LoginCheck extends HttpServlet {

	private MessageManager message = MessageManager.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		// 画面項目セット
		req.setAttribute("userId", userId);
		// バリデーション
		// ユーザＩＤ・パスワード未入力ＮＧ
		if (StringUtils.isEmpty(userId)) {
			// バリデーションエラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(LoginConstants.MESSAGE_ERROR_USER_ID_NOT_INPUT));
			dispReturn(req, resp, LoginConstants.CONTENTS_PATH);
			return;
		}
		if (StringUtils.isEmpty(password)) {
			// バリデーションエラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(LoginConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT));
			dispReturn(req, resp, LoginConstants.CONTENTS_PATH);
			return;
		}
		// 認証チェック
		if (isLogin(userId,password)) {
			// 認証チェック：ＯＫ
			// セッションへ認証結果保持
			HttpSession session = req.getSession(true);
			session.setAttribute("ip", req.getRemoteAddr());
			session.setAttribute("host", req.getRemoteHost());
			// トークンはユーザＩＤとタイムスタンプ、ランダム文字列から生成
			// キー値はログイン時に生成し、セッションで保持
			session.setAttribute("userId", userId);
			// キー値はログイン時に生成し、セッションで保持
			session.setAttribute("token", SessionUtil.getToken(userId, DateUtil.getCurrentTimestamp(DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSMILLS)));
			// 前回ログインから所定機関経過した場合、パスワード変更へ
			if (isInvalid(userId)) {
				// パスワード変更へ
				dispReturn(req, resp, LoginConstants.DISPATCH_PASSWORD);
				return;
			}
			// メニュー遷移
			dispReturn(req, resp, LoginConstants.DISPATCH_PATH);
		} else {
			// 認証チェック：ＮＧ（エラーメッセージ）
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(LoginConstants.MESSAGE_ERROR_AUTHORIZED_FAILD));
			dispReturn(req, resp, LoginConstants.CONTENTS_PATH);
		}
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message) throws ServletException, IOException {
		// メッセージの設定
		List<String> messages = (List<String>)req.getAttribute(type);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
		req.setAttribute(type, messages);
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
						result = count == 1;
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

	// 有効期間チェック
	private boolean isInvalid(String userId) {
		boolean result = false;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select last_update_ymd from authorized_user where user_id = ?")
					) {
				// パラメータ指定(ユーザID)
				statement.setString(1, userId);
				// ログイン情報を検索
				if (!statement.execute()) {
					return false;
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						// 件数が1件の場合 true：正常
						// 件数が1件以外の場合 false：異常
						String lastUpdateYmd = resultSet.getString(1);
						if (DateUtil.getInterval(DateUtil.getCurrentTimestamp(DateUtil.DATE_FORMAT_YYYYMMDD)  ,DateUtil.formatString(DateUtil.add(DateUtil.toTimestamp(lastUpdateYmd, DateUtil.DATE_FORMAT_YYYYMMDD), Calendar.YEAR, LoginConstants.VALID_INTERVAL), DateUtil.DATE_FORMAT_YYYYMMDD) , DateUtil.DATE_FORMAT_YYYYMMDD) < 0) {
							return false;
						} else {
							return true;
						}
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

	private void dispReturn(HttpServletRequest req,HttpServletResponse resp,String dispatchUrl) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(dispatchUrl);
		dispatcher.forward(req, resp);
	}

}
