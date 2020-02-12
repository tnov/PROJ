package application.emp0001.upload.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import application.CheckUtil;
import application.emp0001.upload.Emp0001UplConstants;
import application.emp0001.upload.Emp0001UplForm;
import lib.common.Constants;

public class Emp0001UplCsvUpload extends HttpServlet {

	enum col {EMPLOYEE_ID,EMPLOYEE_NAME};

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
				if (item.length == 2) {
					String employeeId = item[col.EMPLOYEE_ID.ordinal()].substring(1,item[col.EMPLOYEE_ID.ordinal()].length()-1);
					String employeeName = item[col.EMPLOYEE_NAME.ordinal()].substring(1,item[col.EMPLOYEE_NAME.ordinal()].length()-1);
					System.out.println(employeeId + " / " + employeeName);
				}
			}
		}
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001UplConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
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
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher(Emp0001UplConstants.CONTENTS_PATH);
		dispatcher.forward(req, resp);
	}
}
