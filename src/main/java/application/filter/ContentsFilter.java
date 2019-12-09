package application.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentsFilter implements Filter {

	private static String CONTENTS_ROOT = "/WEB-INF";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println(((HttpServletRequest)request).getContextPath());
//		System.out.println(((HttpServletRequest)request).getRequestURI());
		String path = ((HttpServletRequest)request).getRequestURI().replace(((HttpServletRequest)request).getContextPath(), CONTENTS_ROOT);
//		System.out.println(path);
		InputStream is = request.getServletContext().getResourceAsStream(path);
		if (is == null) {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			response.getOutputStream().write(is.readAllBytes());
		}
	}
}
