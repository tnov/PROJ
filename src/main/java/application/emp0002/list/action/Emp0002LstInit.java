package application.emp0002.list.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.CommonUtil;
import application.emp0002.list.Emp0002LstConstants;
import application.emp0002.list.Emp0002LstForm;

public class Emp0002LstInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		Emp0002LstForm form = new Emp0002LstForm();
		form.setCustomerId("");
		form.setCustomerName("");
		form.setDevelopment("1");
		form.setMaintenance("1");
		form.setOperation("1");
		form.setInfrastructure("1");
		form.setAgreeStatus(null);
		form.setAgreeYmdFrom("");
		form.setAgreeYmdTo("");
		form.setDepartmentMap(CommonUtil.getDepartment());
		form.setCurrentPage("0");
		form.setPageSize("0");
		form.setLineLimit("1000");
		form.setLineSize("5");
		req.setAttribute("form", form);

		// 検索結果破棄
		HttpSession session = req.getSession(false);
		session.removeAttribute("Emp0002LstSearch");
		CommonUtil.dispReturn(req, resp, Emp0002LstConstants.CONTENTS_PATH);
	}
}
