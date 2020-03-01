package application.emp0001.detail.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CheckUtil;
import application.emp0001.Emp0001Util;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import lib.common.Constants;

public class Emp0001DtlSave extends HttpServlet {

	public Emp0001Util util = new Emp0001Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setParamEmployeeId(req.getParameter("paramEmployeeId"));
		form.setEmployeeId(req.getParameter("employeeId"));
		form.setEmployeeName(req.getParameter("employeeName"));
		form.setBirthYmd(req.getParameter("birthYmd"));
		form.setSex(req.getParameter("sex"));
		form.setZipCode(req.getParameter("zipCode"));
		form.setAddress(req.getParameter("address"));
		form.setJoinedYmd(req.getParameter("joinedYmd"));
		form.setRetireYmd(req.getParameter("retireYmd"));
		form.setDepartmentId(req.getParameter("departmentId"));
		form.setAuthorized(req.getParameter("authorized"));
		form.setMode(req.getParameter("mode"));
		// チェック処理
		List<String> messages = util.check(form);
		if (CheckUtil.isNotEmpty(messages)) {
			// チェック処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, messages,form);
			return;
		}
		// 保存処理
		if (!util.save(form)) {
			// 保存処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE,form);
			return;
		}
		req.setAttribute("form", form);
		// 画面遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}


	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, List<String> messages, Emp0001DtlForm form) throws ServletException, IOException {
		StringJoiner sj = new StringJoiner("\r\n");
		for (String message : messages) {
			sj.add(message);
		}
		setMessage(req,resp,type,sj.toString(),form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message, Emp0001DtlForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		// メッセージの設定
		List<String> messages = (List<String>)req.getAttribute(type);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
		req.setAttribute(type, messages);
		// メニュー遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
