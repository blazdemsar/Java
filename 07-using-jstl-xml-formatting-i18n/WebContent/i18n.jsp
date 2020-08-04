<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I18N - Internationalization</title>
</head>
<body>
	<p>I18N - <strong>I</strong>nternationalizatio<strong>n</strong></p>
	<br/><a href="?locale=en_US">English(US)</a>
	<br/><a href="?locale=es_ES">Spanish(Spain)</a>
	<br/><a href="?locale=de_DE">German(Germany)</a>
	<br/><a href="?locale=zh_CN">Chinese-Simplified(China)</a>
	<br/><a href="?locale=fr_FR">French(France)</a>
	<br/><a href="?locale=hi_IN">Hindi(India)</a>
	<br/><a href="?locale=vi_VN">Vietnamese(Vietnam)</a>
	<br/><a href="?locale=th_TH">Thai(Thailand)</a>
	
	<fmt:setLocale value="${param.locale}"/>
	<fmt:bundle basename="i18n/message" >
		<br><fmt:message key="txtHello" />
		<br><fmt:message key="txtWelcome" />
		<br><fmt:message key="txtThanks"/>
	</fmt:bundle>
	<br><br>Locale: ${param.locale}
</body>
</html>