package lib.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.common.Constants;

public class ContentsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getRequestURI().replace(((HttpServletRequest)request).getContextPath(), Constants.CONTENTS_ROOT);
		InputStream is = request.getServletContext().getResourceAsStream(path);
		if (is == null) {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			response.getOutputStream().write(is.readAllBytes());
		}
	}
}
