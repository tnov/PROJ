package application.emp0001.upload.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CommonUtil;
import application.emp0001.upload.Emp0001UplConstants;
import application.emp0001.upload.Emp0001UplForm;

public class Emp0001UplInit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// JSPの読み込み＆初期処理
		Emp0001UplForm form = new Emp0001UplForm();

		req.setAttribute("form", form);
		CommonUtil.dispReturn(req, resp, Emp0001UplConstants.CONTENTS_PATH);
	}
}
