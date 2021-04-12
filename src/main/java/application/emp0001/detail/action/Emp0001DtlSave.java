package application.emp0001.detail.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CheckUtil;
import application.CommonUtil;
import application.MessageManager;
import application.emp0001.Emp0001Util;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import lib.common.Constants;

public class Emp0001DtlSave extends HttpServlet {

	private MessageManager message = MessageManager.getInstance();

	private Emp0001Util util = new Emp0001Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setLoginUserId((String)req.getSession().getAttribute("userId"));
		form.setModuleId(this.getClass().getSimpleName());
		// プルダウン設定
		form.setDepartmentMap(CommonUtil.getDepartment());
		// リクエストの取得
		form.setParamEmployeeId(req.getParameter("paramEmployeeId"));
		form.setEmployeeId(req.getParameter("employeeId"));
		form.setEmployeeName(req.getParameter("employeeName"));
		form.setBirthYmd(req.getParameter("birthYmd"));
		form.setSex(req.getParameter("sex"));
		form.setZipCode(req.getParameter("zipCode"));
		form.setAddress(req.getParameter("address"));
		form.setJoinedYmd(req.getParameter("joinedYmd"));
		form.setRetireYmd(req.getParameter("retireYmd"));
		form.setTel(req.getParameter("tel"));
		form.setDepartmentId(req.getParameter("departmentId"));
		form.setPassword(req.getParameter("password"));
		form.setPasswordChk(req.getParameter("passwordChk"));

		form.setMode(req.getParameter("mode"));

		// 入力チェック処理
		List<String> messages = util.inputCheck(form);
		if (CheckUtil.isNotEmpty(messages)) {
			form.setPassword("");
			form.setPasswordChk("");
			req.setAttribute("form", form);

			// チェック処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, messages,form);
			// 元画面遷移
			CommonUtil.dispReturn(req, resp, Emp0001DtlConstants.CONTENTS_PATH);
			return;
		}
		// 保存処理
		if (!util.save(form)) {
			form.setPassword("");
			form.setPasswordChk("");
			req.setAttribute("form", form);
			// 保存処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE),form);
			return;
		}
		form.setMode(Emp0001DtlConstants.MODE_UPDATE);
		req.setAttribute("form", form);

		setMessage(req, resp, Constants.MESSAGE_TYPE_INFO, message.getMessage(Emp0001DtlConstants.MESSAGE_INFO_MST_EMPLOYEE_SAVE),form);
		// 画面遷移
		CommonUtil.dispReturn(req, resp, Emp0001DtlConstants.CONTENTS_PATH);
	}


	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String messages, Emp0001DtlForm form) throws ServletException, IOException {
		List<String> messagesList = new ArrayList<String>();
		messagesList.add(messages);
		setMessage(req,resp,type,messagesList,form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type,  List<String> messages, Emp0001DtlForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		req.setAttribute(type, messages);
	}
}
