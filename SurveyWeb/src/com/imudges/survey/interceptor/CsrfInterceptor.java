package com.imudges.survey.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CsrfInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);  
		if (request.getMethod().equals("GET")) {
			return invocation.invoke();
		}
		String tokenName = request.getParameter("struts.token.name");
		if (tokenName == null) {
			return "invalid.token";
		}
		String token = request.getParameter(tokenName);
		if (token == null) {
			return "invalid.token";
		}
		String correctToken = (String) request.getSession().getAttribute("struts.tokens." + tokenName);
		if (token.equals(correctToken)) {
			actionContext.getSession().put("struts.tokens" + tokenName , null);
			return invocation.invoke();
		}else {
			return "invalid.token";
		}
	}
}
