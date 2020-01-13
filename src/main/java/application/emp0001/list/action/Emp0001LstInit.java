package application.emp0001.list.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.emp0001.list.Emp0001LstConstants;

public class Emp0001LstInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		req.setAttribute("employeeId", "");
		req.setAttribute("employeeName", "");
		req.setAttribute("sex", "");
		req.setAttribute("joinedYmd", "");
		req.setAttribute("retiredYmd", "");
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001LstConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
