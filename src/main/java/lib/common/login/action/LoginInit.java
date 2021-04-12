package lib.common.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.common.login.LoginConstants;

public class LoginInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// セッションが残っている場合は破棄
		HttpSession session = req.getSession();
		if (session != null) {
		    session.invalidate();
		}
		// JSPの読み込み＆初期処理
		req.setAttribute("userId", "");
		req.setAttribute("password", "");
		req.setAttribute("token", "");
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(LoginConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
