package application.emp0002.detail.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CheckUtil;
import application.CommonUtil;
import application.emp0002.Emp0002Constants;
import application.emp0002.Emp0002DataBean;
import application.emp0002.detail.Emp0002DtlConstants;
import application.emp0002.detail.Emp0002DtlForm;
import application.proj.dao.MstCustomerDao;
import application.proj.entity.MstCustomer;

public class Emp0002DtlInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramCustomerId = req.getParameter("paramCustomerId");
		Emp0002DtlForm form = null;
		// 初期処理
		if (CheckUtil.isEmpty(paramCustomerId)) {
			form = createCustomer();
		} else {
			form = updateCustomer(paramCustomerId);
		}
		form.setParamCustomerId(paramCustomerId);
		req.setAttribute("form", form);
		// JSPの読み込み
		CommonUtil.dispReturn(req, resp, Emp0002DtlConstants.CONTENTS_PATH);
	}

	private Emp0002DtlForm createCustomer() {
		Emp0002DtlForm form = new Emp0002DtlForm();
		form.setCustomerId("");
		form.setCustomerName("");
		form.setZipCode("");
		form.setAddress("");
		form.setLiaison("");
		form.setStaff("");
		form.setTel("");
		form.setDevelopmentFlg("0");
		form.setMaintenanceFlg("0");
		form.setOperationFlg("0");
		form.setInfrastructureFlg("0");
		form.setAgreeStatus("");
		form.setAgreeYmd("");
		form.setAgreeMap(Emp0002Constants.AGREE_STATUS_MAP);
		form.setMode(Emp0002DtlConstants.MODE_CREATE);
		return form;
	}

	private Emp0002DtlForm updateCustomer(String customerId) {
		Emp0002DtlForm form = new Emp0002DtlForm();
		form.setCustomerId(customerId);
		// 検索処理
		Emp0002DataBean result = search(form);

		form.setCustomerId(result.getCustomerId());
		form.setCustomerName(result.getCustomerName());
		form.setZipCode(result.getZipCode());
		form.setAddress(result.getAddress());
		form.setLiaison(result.getLiaison());
		form.setStaff(result.getStaff());
		form.setTel(result.getTel());
		form.setDevelopmentFlg(result.getDevelopment());
		form.setMaintenanceFlg(result.getMaintenance());
		form.setOperationFlg(result.getOperation());
		form.setInfrastructureFlg(result.getInfrastructure());
		form.setAgreeStatus(result.getAgreeStatus());
		form.setAgreeYmd(result.getAgreeYmd());
		form.setAgreeMap(Emp0002Constants.AGREE_STATUS_MAP);
		form.setMode(Emp0002DtlConstants.MODE_UPDATE);
		return form;
	}


	// 検索処理
	private Emp0002DataBean search(Emp0002DtlForm form) {
		Emp0002DataBean result = null;
		MstCustomer entity = new MstCustomer();
		entity.setCustomerId(form.getCustomerId());
		MstCustomerDao dao = new MstCustomerDao();
		entity = dao.getMstCustomer(entity);
		if (entity != null) {
			result = new Emp0002DataBean();
			result.setCustomerId(entity.getCustomerId());
			result.setCustomerName(entity.getCustomerName());
			result.setZipCode(entity.getZipCode());
			result.setAddress(entity.getAddress());
			result.setLiaison(entity.getLiaison());
			result.setStaff(entity.getStaff());
			result.setTel(entity.getTel());
			result.setDevelopment(entity.getDevelopmentFlg());
			result.setMaintenance(entity.getMaintenanceFlg());
			result.setOperation(entity.getOperationFlg());
			result.setInfrastructure(entity.getInfrastructureFlg());
			result.setAgreeYmd(entity.getAgreeYmd());
			result.setAgreeStatus(entity.getAgreeStatus());
		}
		return result;
	}
}
