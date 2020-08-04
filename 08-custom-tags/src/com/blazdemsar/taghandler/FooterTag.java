package com.blazdemsar.taghandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FooterTag extends SimpleTagSupport {
	
	/* To create a custom tag, following components are required:
	   1) The tag handler class which should implement SimpleTagSupport/TagSupport/BodyTagSupport
	   2) A tag library descriptor (TLD) that describes the tag. TLD has the extension ".tld"
	   3) A jsp file where the custom tag can be used
	*/
	
	private String name;
	private String align;
	
	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
		
		out.println("<hr>");
		out.println("<div align=\"" + align + "\">Address: " + name + "</div>");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
	
	
}
