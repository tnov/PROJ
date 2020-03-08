package application.emp0001.upload.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import application.CheckUtil;
import application.CommonUtil;
import application.emp0001.Emp0001Util;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.emp0001.upload.Emp0001UplConstants;
import application.emp0001.upload.Emp0001UplForm;
import lib.common.Constants;

public class Emp0001UplCsvUpload extends HttpServlet {

	enum col {EMPLOYEE_ID
		,EMPLOYEE_NAME
		,BIRTH_YMD
		,SEX
		,ZIP_CODE
		,ADDRESS
		,JOINED_YMD
		,RETIRE_YMD
		,DEPARTMENT_ID
		,AUTHORIZED};

		public Emp0001Util util = new Emp0001Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエストの取得
		Emp0001UplForm form = new Emp0001UplForm();
		Part part = req.getPart("file");
		String fileName = part.getSubmittedFileName();
		if (CheckUtil.isEmpty(fileName)) {
			// ファイル未設定
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, Emp0001UplConstants.MESSAGE_ERROR_FILE_NOT_SELECT,form);
		}
		List<String> errors = new ArrayList<>();
		try (InputStream is = part.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br =  new BufferedReader(isr)) {
			boolean existsHeader = false;
			while (br.ready()) {
				String line = br.readLine();
				if (existsHeader) {
					existsHeader = false;
					continue;
				}
				String[] item = line.split(",");
				if (item.length == 10) {
					String employeeId = item[col.EMPLOYEE_ID.ordinal()].substring(1,item[col.EMPLOYEE_ID.ordinal()].length()-1);
					String employeeName = item[col.EMPLOYEE_NAME.ordinal()].substring(1,item[col.EMPLOYEE_NAME.ordinal()].length()-1);
					String birthYmd = item[col.BIRTH_YMD.ordinal()].substring(1,item[col.BIRTH_YMD.ordinal()].length()-1);
					String sex = item[col.SEX.ordinal()].substring(1,item[col.SEX.ordinal()].length()-1);
					String zipCode = item[col.ZIP_CODE.ordinal()].substring(1,item[col.ZIP_CODE.ordinal()].length()-1);
					String address = item[col.ADDRESS.ordinal()].substring(1,item[col.ADDRESS.ordinal()].length()-1);
					String joinedYmd = item[col.JOINED_YMD.ordinal()].substring(1,item[col.JOINED_YMD.ordinal()].length()-1);
					String retireYmd = item[col.RETIRE_YMD.ordinal()].substring(1,item[col.RETIRE_YMD.ordinal()].length()-1);
					String departmentId = item[col.DEPARTMENT_ID.ordinal()].substring(1,item[col.DEPARTMENT_ID.ordinal()].length()-1);
					String authorized = item[col.AUTHORIZED.ordinal()].substring(1,item[col.AUTHORIZED.ordinal()].length()-1);
					Emp0001DtlForm dtlForm = new Emp0001DtlForm();
					dtlForm.setEmployeeId(employeeId);
					dtlForm.setEmployeeName(employeeName);
					dtlForm.setBirthYmd(birthYmd);
					dtlForm.setSex(sex);
					dtlForm.setZipCode(zipCode);
					dtlForm.setAddress(address);
					dtlForm.setJoinedYmd(joinedYmd);
					dtlForm.setRetireYmd(retireYmd);
					dtlForm.setDepartmentId(departmentId);
					dtlForm.setAuthorized(authorized);
					// チェック処理
					List<String> messages = util.check(dtlForm);
					if (CheckUtil.isNotEmpty(messages)) {
						errors.addAll(messages);
					}
					// 保存処理
					if (!util.save(dtlForm)) {
						// 保存処理エラー
						setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE,form);
						return;
					}
				}
			}
		}
		CommonUtil.dispReturn(req, resp, Emp0001UplConstants.CONTENTS_PATH);
	}


	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, List<String> messages, Emp0001UplForm form) throws ServletException, IOException {
		StringJoiner sj = new StringJoiner("\r\n");
		for (String message : messages) {
			sj.add(message);
		}
		setMessage(req,resp,type,sj.toString(),form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message, Emp0001UplForm form) throws ServletException, IOException {
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
		CommonUtil.dispReturn(req, resp, Emp0001UplConstants.CONTENTS_PATH);
	}
}
