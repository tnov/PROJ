package lib.common.menu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CommonUtil;

public class MenuAction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		// 画面パスを取得
		String menuMovePath = req.getParameter("menuMovePath");
		// 画面遷移
		CommonUtil.dispReturn(req, resp, menuMovePath);
	}
}
