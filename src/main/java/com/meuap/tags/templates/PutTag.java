package com.meuap.tags.templates;

import java.util.HashMap;
import java.util.Stack;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class PutTag extends BodyTagSupport {
	private static final long serialVersionUID = 3606683174912213025L;

	private String name;
	private String page;
	private String content;
	private String direct = "false";

	public void setPage(String s) {
		page = s;
	}

	public void setName(String s) {
		name = s;
	}

	public void setContent(String s) {
		content = s;
	}

	public void setDirect(String s) {
		direct = s;
	}

	public int doStartTag() throws JspException {
		int retCode = EVAL_BODY_BUFFERED;
		if (!Boolean.valueOf(direct).booleanValue()) {
			if (page != null) {
				content = page;
			} else {
				direct = "true";
			}
			getParams().put(name, new PageParameter(content, direct));
			retCode = SKIP_BODY;
		}

		return retCode;
	}

	@Override
	public int doAfterBody() throws JspException {
		if (Boolean.valueOf(direct).booleanValue()) {
			String body = getBodyContent().getString();
			getParams().put(name, new PageParameter(body, direct));
		}

		return super.doAfterBody();
	}

	private HashMap<String, PageParameter> getParams() throws JspException {
		HashMap<String, PageParameter> params;
		InsertTag parent = (InsertTag) getAncestor("com.meuap.tags.templates.InsertTag");
		if (parent == null) {
			throw new JspException("PutTag.doStartTag(): No InsertTag ancestor");
		}

		Stack<HashMap<String, PageParameter>> templateStack = parent.getStack();

		if (templateStack == null) {
			throw new JspException("PutTag: no template stack");
		}

		params = templateStack.peek();
		if (params == null) {
			throw new JspException("PutTag: no hashtable");
		}
		return params;
	}

	public void release() {
		name = null;
		content = null;
		direct = null;
	}

	@SuppressWarnings("rawtypes")
	private TagSupport getAncestor(String className) throws JspException {
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException ex) {
			throw new JspException(ex);
		}
		return (TagSupport) findAncestorWithClass(this, clazz);
	}
}
