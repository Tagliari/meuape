package com.meuap.tags.templates;

import java.util.HashMap;
import java.util.Stack;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class GetTag extends TagSupport {
	private static final long serialVersionUID = 2890199789849949188L;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public int doStartTag() throws JspException {
		Stack stack = (Stack) pageContext.getAttribute("template-stack", PageContext.REQUEST_SCOPE);

		if (stack == null) {
			throw new JspException("GetTag.doStartTag(): " + "NO STACK");
		}

		HashMap params = (HashMap) stack.peek();

		if (params == null) {
			throw new JspException("GetTag.doStartTag(): " + "NO HASHTABLE");
		}

		PageParameter param = (PageParameter) params.get(name);

		if (param != null) {
			String content = param.getContent();

			if (param.isDirect()) {
				try {
					pageContext.getOut().print(content);
				} catch (java.io.IOException ex) {
					throw new JspException(ex);
				}
			} else {
				try {
					pageContext.getOut().flush();
					pageContext.include(content);
				} catch (Exception ex) {
					throw new JspException(ex);
				}
			}
		}
		return SKIP_BODY;
	}

	public void release() {
		name = null;
	}
}
