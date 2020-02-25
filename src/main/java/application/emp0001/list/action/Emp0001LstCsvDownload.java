package application.emp0001.list.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Emp0001LstCsvDownload extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/csv;charset=UTF8");

		// 実装方法模索中 大槻
		String[] fooParams = req.getParameterValues("checklist");


		String filename = URLEncoder.encode("ファイル名.csv", "UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
		try (OutputStream os = resp.getOutputStream()) {
			os.write("テスト".getBytes());
		}
	}
}
