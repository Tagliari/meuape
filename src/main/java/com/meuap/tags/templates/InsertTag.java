package com.meuap.tags.templates;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class InsertTag extends TagSupport {
	private static final long serialVersionUID = 661226872806522710L;
	private String template;
	private Stack<HashMap<String, PageParameter>> stack;

	public void setTemplate(String template) {
		this.template = template;
	}

	public int doStartTag() throws JspException {
		stack = getStack();
		stack.push(new HashMap<String, PageParameter>());
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			if (!pageContext.getResponse().isCommitted()) {
				pageContext.include(template);
			}
		} catch (IOException ex) {
			throw new JspException(ex);
		} catch (ServletException ex) {
			throw new JspException(ex);
		}
		stack.pop();
		return EVAL_PAGE;
	}

	public void release() {
		template = null;
		stack = null;
	}

	@SuppressWarnings({ "unchecked" })
	public Stack<HashMap<String, PageParameter>> getStack() {
		Stack<HashMap<String, PageParameter>> s = (Stack<HashMap<String, PageParameter>>) pageContext.getAttribute(
				"template-stack", PageContext.REQUEST_SCOPE);
		if (s == null) {
			s = new Stack<HashMap<String, PageParameter>>();
			pageContext.setAttribute("template-stack", s, PageContext.REQUEST_SCOPE);
		}
		return s;
	}
}
