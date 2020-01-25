package application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtil {

	public static String getUserId(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (String)session.getAttribute("userId");
	}
}
