package application.emp0001.detail.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CheckUtil;
import application.CommonUtil;
import application.emp0001.Emp0001DataBean;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.proj.dao.MstEmployeeDao;
import application.proj.entity.MstEmployee;

public class Emp0001DtlInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramEmployeeId = req.getParameter("paramEmployeeId");
		Emp0001DtlForm form = null;
		// 初期処理
		if (CheckUtil.isEmpty(paramEmployeeId)) {
			form = createEmployee();
		} else {
			form = updateEmployee(paramEmployeeId);
		}
		req.setAttribute("form", form);
		// JSPの読み込み
		CommonUtil.dispReturn(req, resp, Emp0001DtlConstants.CONTENTS_PATH);
	}

	private Emp0001DtlForm createEmployee() {
		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setParamEmployeeId("");
		form.setEmployeeId("");
		form.setEmployeeName("");
		form.setBirthYmd("");
		form.setSex("1");
		form.setZipCode("");
		form.setAddress("");
		form.setJoinedYmd("");
		form.setRetireYmd("");
		form.setDepartmentId("");
		form.setAuthorized("");
		form.setDepartmentMap(CommonUtil.getDepartment());
		form.setMode(Emp0001DtlConstants.MODE_CREATE);
		return form;
	}

	private Emp0001DtlForm updateEmployee(String employeeId) {
		Emp0001DtlForm form = new Emp0001DtlForm();
		form.setParamEmployeeId(employeeId);
		// 検索処理
		Emp0001DataBean result = search(form);
		form.setParamEmployeeId(result.getEmployeeId());
		form.setEmployeeId(result.getEmployeeId());
		form.setEmployeeName(result.getEmployeeName());
		form.setBirthYmd(result.getBirthYmd());
		form.setSex(result.getSex());
		form.setZipCode(result.getZipCode());
		form.setAddress(result.getAddress());
		form.setJoinedYmd(result.getJoinedYmd());
		form.setRetireYmd(result.getRetireYmd());
		form.setDepartmentId(result.getDepartmentId());
		form.setAuthorized(result.getAuthorized());
		form.setDepartmentMap(CommonUtil.getDepartment());
		form.setMode(Emp0001DtlConstants.MODE_UPDATE);
		return form;
	}


	// 検索処理
	private Emp0001DataBean search(Emp0001DtlForm form) {
		Emp0001DataBean result = null;
		MstEmployee entity = new MstEmployee();
		entity.setEmployeeId(form.getParamEmployeeId());
		MstEmployeeDao dao = new MstEmployeeDao();
		entity = dao.getMstEmployee(entity);
		if (entity != null) {
			result = new Emp0001DataBean();
			result.setEmployeeId(entity.getEmployeeId());
			result.setEmployeeName(entity.getEmployeeName());
			result.setBirthYmd(entity.getBirthYmd());
			result.setSex(entity.getSex());
			result.setZipCode(entity.getZipCode());
			result.setAddress(entity.getAddress());
			result.setJoinedYmd(entity.getJoinedYmd());
			result.setRetireYmd(entity.getRetireYmd());
			result.setDepartmentId(entity.getDepartmentId());
			result.setAuthorized(entity.getAuthorized());
		}
		return result;
	}
}
