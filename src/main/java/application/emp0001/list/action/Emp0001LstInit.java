package application.emp0001.list.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.CommonUtil;
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
		form.setSex(null);
		form.setJoinedYmdFrom("");
		form.setJoinedYmdTo("");
		form.setRetiredYmd("");
		form.setDepartmentId("");
		form.setDepartmentMap(CommonUtil.getDepartment());
		form.setCurrentPage("0");
		form.setPageSize("0");
		form.setLineLimit("1000");
		form.setLineSize("5");
		req.setAttribute("form", form);

		// 検索結果破棄
		HttpSession session = req.getSession(false);
		session.removeAttribute("Emp0001LstSearch");
		CommonUtil.dispReturn(req, resp, Emp0001LstConstants.CONTENTS_PATH);
	}
}
