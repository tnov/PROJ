package application.emp0002.detail.action;

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
import application.emp0002.Emp0002Constants;
import application.emp0002.Emp0002Util;
import application.emp0002.detail.Emp0002DtlConstants;
import application.emp0002.detail.Emp0002DtlForm;
import lib.common.Constants;

public class Emp0002DtlSave extends HttpServlet {

	/** メッセージマネージャ */
	MessageManager message = MessageManager.getInstance();

	private Emp0002Util util = new Emp0002Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Emp0002DtlForm form = new Emp0002DtlForm();
		// プルダウン設定
		form.setAgreeMap(Emp0002Constants.AGREE_STATUS_MAP);
		// リクエストの取得
		form.setCustomerId(req.getParameter("customerId"));
		form.setCustomerName(req.getParameter("customerName"));
		form.setZipCode(req.getParameter("zipCode"));
		form.setAddress(req.getParameter("address"));
		form.setLiaison(req.getParameter("liaison"));
		form.setStaff(req.getParameter("staff"));
		form.setTel(req.getParameter("tel"));
		form.setDevelopmentFlg(req.getParameter("developmentFlg"));
		form.setMaintenanceFlg(req.getParameter("maintenanceFlg"));
		form.setOperationFlg(req.getParameter("operationFlg"));
		form.setInfrastructureFlg(req.getParameter("infrastructureFlg"));
		form.setAgreeYmd(req.getParameter("agreeYmd"));
		form.setAgreeStatus(req.getParameter("agreeStatus"));

		form.setMode(req.getParameter("mode"));

		// 入力チェック処理
		List<String> messages = util.inputCheck(form);
		if (CheckUtil.isNotEmpty(messages)) {
			req.setAttribute("form", form);

			// チェック処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, messages,form);
			// 元画面遷移
			CommonUtil.dispReturn(req, resp, Emp0002DtlConstants.CONTENTS_PATH);
			return;
		}
		// 保存処理
		if (!util.save(form)) {
			req.setAttribute("form", form);
			// 保存処理エラー
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(Emp0002DtlConstants.MESSAGE_ERROR_MST_CUSTOMER_NOT_SAVE),form);
			// 元画面遷移
			CommonUtil.dispReturn(req, resp, Emp0002DtlConstants.CONTENTS_PATH);
			return;
		}
		req.setAttribute("form", form);

		setMessage(req, resp, Constants.MESSAGE_TYPE_INFO, message.getMessage(Emp0002DtlConstants.MESSAGE_INFO_MST_CUSTOMER_SAVE),form);
		// 元画面遷移
		CommonUtil.dispReturn(req, resp, Emp0002DtlConstants.CONTENTS_PATH);
	}


	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String messages, Emp0002DtlForm form) throws ServletException, IOException {
		List<String> messagesList = new ArrayList<String>();
		messagesList.add(messages);
		setMessage(req,resp,type,messagesList,form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type,  List<String> messages, Emp0002DtlForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		req.setAttribute(type, messages);
	}
}
