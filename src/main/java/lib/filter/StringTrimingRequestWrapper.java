package lib.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lib.util.StringUtils;

public class StringTrimingRequestWrapper extends HttpServletRequestWrapper {

	@Override
	public String getParameter(String name) {
		return StringUtils.trimToNull(super.getParameter(name));
	}

	public StringTrimingRequestWrapper(HttpServletRequest request) {
		super(request);
	}
}
