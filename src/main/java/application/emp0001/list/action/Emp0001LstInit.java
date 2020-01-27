package application.emp0001.list.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;

public class Emp0001LstInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		Emp0001LstForm form = new Emp0001LstForm();
		form.setEmployeeId("");
		form.setEmployeeName("");
		form.setSex("");
		form.setJoinedYmd("");
		form.setRetiredYmd("");
		form.setDepartmentId("");

		form.setCurrentPage("0");
		form.setLineLimit("1000");
		form.setLineSize("5");
		req.setAttribute("form", form);
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001LstConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
