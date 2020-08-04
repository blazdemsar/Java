<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Use of include directive and jsp:include tag</title>
</head>
<body>

<%@ include file="File1.txt" %>
<%@ include file="File2.txt" %>

<jsp:include page="date.jsp" />

</body>
</html>