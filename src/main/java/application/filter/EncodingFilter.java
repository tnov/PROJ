package application.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lib.constants.ServletConstants;

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		request.setCharacterEncoding(ServletConstants.SERVLET_DEFAULT_ENCODING);
		response.setCharacterEncoding(ServletConstants.SERVLET_DEFAULT_ENCODING);
		filter.doFilter(request, response);
	}
}
