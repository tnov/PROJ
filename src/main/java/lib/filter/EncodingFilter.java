package lib.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lib.constants.ServletConstants;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		System.out.println(((HttpServletRequest) request).getRequestURL() + "：" + new java.util.Date());
		request.setCharacterEncoding(ServletConstants.SERVLET_DEFAULT_ENCODING);
		response.setCharacterEncoding(ServletConstants.SERVLET_DEFAULT_ENCODING);
		filter.doFilter(request, response);
	}
}
