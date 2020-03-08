package lib.common.password.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.common.password.PasswordConstants;

public class PasswordInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		// セッションからユーザＩＤを取得
		String userId = (String)req.getSession(false).getAttribute("user_id");
		req.setAttribute("userId", "userId");
		req.setAttribute("password", "");
		req.setAttribute("newPassword", "");
		req.setAttribute("confirmPassword", "");
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(PasswordConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
