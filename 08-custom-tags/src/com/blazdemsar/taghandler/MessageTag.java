package com.blazdemsar.taghandler;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MessageTag extends SimpleTagSupport {
	
	private String from;
	private String to;
	private String message;
	
	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		
		out.println("<br>The message<br>From :" + from + "<br>To :" + to + "<br>Message : " + message);;

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
