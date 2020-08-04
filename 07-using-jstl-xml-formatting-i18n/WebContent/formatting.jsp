<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<strong>Formatting the date:</strong>
	<c:set var="date" value="<%=new java.util.Date() %>" />
	<br>Unformatted Date: <c:out value="${date}"/>
	<br>Formatted Date: <fmt:formatDate value="${date}" type="date" />
	<br>Time: <fmt:formatDate value="${date}" type="time" />
	<br>Date and Time: <fmt:formatDate value="${date}" type="both" />
	
	<br><br><strong>Formatting numbers:</strong>
	<c:set var="theNumber" value="1024.6789" />
	<br>The Number: 1024.6789
	<br>Max Integer: <fmt:formatNumber value="${theNumber}" type="number" maxIntegerDigits="4" />
	<br>Max Fraction: <fmt:formatNumber value="${theNumber}" type="number" maxFractionDigits="2" />
	<br>Integer Part: <fmt:formatNumber value="${theNumber}" type="number" maxFractionDigits="0" />
	<br>Decimal Part: <fmt:formatNumber value="${theNumber}" type="number" maxIntegerDigits="0" />
	
	<br><br><strong>Formatting Currencies:</strong>
	<br>Currency: <fmt:formatNumber value="${theNumber}" type="currency" />
	
	<fmt:setLocale value="en_US" />
		<br>Currency (United States): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="en_IN" />
		<br>Currency (India): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="en_GB" />
		<br>Currency (Great Britain): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="de_DE" />
		<br>Currency (Germany): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="fr_FR" />
		<br>Currency (France): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="es_ES" />
		<br>Currency (Spain): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="ja_JP" />
		<br>Currency (Japan): <fmt:formatNumber value="${theNumber}" type="currency" />
	<fmt:setLocale value="zh_CH" />
		<br>Currency (China): <fmt:formatNumber value="${theNumber}" type="currency" />
	
</body>
</html>