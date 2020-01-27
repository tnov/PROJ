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
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.proj.dao.MstEmployeeDao;
import application.proj.entity.MstEmployee;
import lib.common.Constants;

public class Emp0001DtlSave extends HttpServlet {

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
		List<String> messages = check(form);
		if (CheckUtil.isNotEmpty(messages)) {
			// チェック処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, messages,form);
			return;
		}
		// 保存処理
		if (!save(form)) {
			// 保存処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE,form);
			return;
		}
		req.setAttribute("paramEmployeeId", form.getParamEmployeeId());
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("birthYmd", form.getBirthYmd());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("zipCode", form.getZipCode());
		req.setAttribute("address", form.getAddress());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retireYmd", form.getRetireYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		req.setAttribute("authorized", form.getAuthorized());
		req.setAttribute("mode", form.getMode());
		// 画面遷移
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001DtlConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}

	// チェック処理
	private List<String> check(Emp0001DtlForm form) {
		List<String> messages = new ArrayList<>();
		if (CheckUtil.isEmpty(form.getEmployeeId())) {
			messages.add(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_ID_NOT_INPUT);
		}
		if (CheckUtil.isEmpty(form.getEmployeeName())) {
			messages.add(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_NAME_NOT_INPUT);
		}
		if (CheckUtil.isEmpty(form.getBirthYmd())) {
			messages.add(Emp0001DtlConstants.MESSAGE_ERROR_BIRTH_YMD_NOT_INPUT);
		}
		if (CheckUtil.isEmpty(form.getSex())) {
			messages.add(Emp0001DtlConstants.MESSAGE_ERROR_SEX_NOT_INPUT);
		}
		if (CheckUtil.isEmpty(form.getAuthorized())) {
			messages.add(Emp0001DtlConstants.MESSAGE_ERROR_AUTHORIZED_NOT_SELECTED);
		}
		return messages;
	}

	// 検索処理
	private boolean save(Emp0001DtlForm form) {
		boolean result = false;
		MstEmployee data = null;
		MstEmployeeDao dao = new MstEmployeeDao();
		if (Emp0001DtlConstants.MODE_CREATE.equals(form.getMode())) {
			MstEmployee key = new MstEmployee();
			key.setEmployeeId(form.getEmployeeId());
			data = dao.getMstEmployee(key);
			if (data != null) {
				return false;
			}
			data = new MstEmployee();
			data.setEmployeeId(form.getEmployeeId());
			data.setEmployeeName(form.getEmployeeName());
			data.setBirthYmd(form.getBirthYmd());
			data.setSex(form.getSex());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setJoinedYmd(form.getJoinedYmd());
			data.setRetireYmd(form.getRetireYmd());
			data.setAuthorized(form.getAuthorized());
			data.setDepartmentId(form.getDepartmentId());
			result = dao.insert(data);
		} else if (Emp0001DtlConstants.MODE_UPDATE.equals(form.getMode())) {
			MstEmployee key = new MstEmployee();
			key.setEmployeeId(form.getParamEmployeeId());
			data = dao.getMstEmployee(key);
			if (data == null) {
				return false;
			}
			data.setEmployeeId(form.getEmployeeId());
			data.setEmployeeName(form.getEmployeeName());
			data.setBirthYmd(form.getBirthYmd());
			data.setSex(form.getSex());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setJoinedYmd(form.getJoinedYmd());
			data.setRetireYmd(form.getRetireYmd());
			data.setDepartmentId(form.getDepartmentId());
			data.setAuthorized(form.getAuthorized());
			result = dao.update(data, key);
		}
		return result;
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
		req.setAttribute("paramEmployeeId", form.getParamEmployeeId());
		req.setAttribute("employeeId", form.getEmployeeId());
		req.setAttribute("employeeName", form.getEmployeeName());
		req.setAttribute("birthYmd", form.getBirthYmd());
		req.setAttribute("sex", form.getSex());
		req.setAttribute("zipCode", form.getZipCode());
		req.setAttribute("address", form.getAddress());
		req.setAttribute("joinedYmd", form.getJoinedYmd());
		req.setAttribute("retireYmd", form.getRetireYmd());
		req.setAttribute("departmentId", form.getDepartmentId());
		req.setAttribute("authorized", form.getAuthorized());
		req.setAttribute("mode", form.getMode());
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
