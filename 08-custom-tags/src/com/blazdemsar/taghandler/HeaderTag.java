package com.blazdemsar.taghandler;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HeaderTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws IOException {
		
		JspWriter out = getJspContext().getOut();
		
		out.println("<div align=\"center\">");
		out.println("<h3>Learning Custom Tags</h3>");
		out.println("</div><br/>");
	}
}
