package lib.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.common.Constants;

public class SessionFilter implements Filter {

	private static final String REDIRECT_URL = Constants.LOGIN_PAGE;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		// ログインページ以外の場合
		if (!Constants.LOGIN_PAGE.equals(((HttpServletRequest)request).getRequestURI())
				 && !Constants.LOGIN_ACTION.equals(((HttpServletRequest)request).getRequestURI())) {
			// ログインページ以外の場合
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if (session == null) {
				// セッションタイムアウト
				((HttpServletResponse)response).sendRedirect(REDIRECT_URL);
				return;
			} else {
				// セッションに退避した情報と比較(リモートＩＰ、ユーザＩＤ、トークン)
				String ip = request.getRemoteAddr();
				String host = request.getRemoteHost();
				String token = (String)request.getParameter("token");
				String sessionIp = (String)session.getAttribute("ip");
				String sessionHost = (String)session.getAttribute("host");
				String sessionToken = (String)session.getAttribute("token");
//				if (!ip.equals(sessionIp) || !host.equals(sessionHost) || !token.equals(sessionToken)) {
				if (!ip.equals(sessionIp) || !token.equals(sessionToken)) {
					// セッション不一致(セッション破棄)
					session.invalidate();
					((HttpServletResponse)response).sendRedirect(REDIRECT_URL);
					return;
				}
			}
		}
		filter.doFilter(request, response);
	}
}
