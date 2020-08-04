<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
	<!-- jsp expression -->
	<%=request.getParameterNames()%>
	<%
		Enumeration<String> e = request.getParameterNames();

	while (e.hasMoreElements()) {
		String paramName = e.nextElement();
		out.println("<br>" + paramName + " : " + request.getParameter(paramName));
	}
	%>
	<br>
	<br>Name:
	<%=request.getParameter("name")%>
	<br>Job:
	<%=request.getParameter("job")%>
	<br>Salary:
	<%=request.getParameter("salary")%>
	<br>HTTP Method:
	<%=request.getMethod()%>
	<br>Request URI:
	<%=request.getRequestURI()%>
	<br>Path:
	<%=request.getPathInfo()%>
	<br>Content Type:
	<%=request.getContentType()%>
	<br>Context Path:
	<%=request.getContextPath()%>
	<br>Local Address:
	<%=request.getLocalAddr()%>
	<br>Local Name:
	<%=request.getLocalName()%>
	<br>Local Port:
	<%=request.getLocalPort()%>

	<br>Remote Address:
	<%=request.getRemoteAddr()%>
	<br>Remote Name/Host:
	<%=request.getRemoteHost()%>
	<br>Remote Port:
	<%=request.getRemotePort()%>

	<br>Session ID:
	<%=request.getSession()%>
	<br>Remote Name/Host:
	<%=request.getRemoteHost()%>
	<br>Remote Port:
	<%=request.getRemotePort()%>
	<br>Query String:
	<%=request.getQueryString()%>

	<br>HTTP Status:
	<%=response.getStatus()%>
	<br>Content Type:
	<%=response.getContentType()%>
	<br>Header names:
	<%
		Collection<String> headerNames = response.getHeaderNames();
	out.println("headerNames size : " + headerNames.size());
	for (String headerName : headerNames) {
		out.println("<br>" + headerName);
	}
	%>

	<br><%=request.getHeader("User-Agent")%>

	<%
		out.println("<br>Company Name: " + session.getAttribute("company"));
	out.println("<br>Manager Name: " + application.getAttribute("manager"));

	// http://192.168.0.2:8081/05-jsp-implicit-objects2/
	// http://127.0.0.1:8081/05-jsp-implicit-objects2/
	// http://localhost:8081/05-jsp-implicit-objects2/
	%>

	<br>Servlet Init Param:
	<%=config.getInitParameter("trainer")%>
	<br>Servlet Name:
	<%=config.getServletName()%>
	<br>Servlet Context:
	<%=config.getServletContext()%>
	<br>Context Parameter(application scope):
	<%=application.getInitParameter("manager")%>
	<br>

	<%
		response.addCookie(new Cookie("manager", "Negendra"));
	Cookie[] cookies = request.getCookies();

	for (Cookie cookie : cookies) {
		out.println("<br>Cookie: " + cookie.getName() + " : " + cookie.getValue());
	}
	%>
</body>
</html>