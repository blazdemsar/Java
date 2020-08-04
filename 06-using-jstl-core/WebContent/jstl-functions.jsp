<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSTL Functions Tag Library</title>
</head>
<body>
<%
int[] numbers = {100, 200, 300, 400, 500};
%>
<c:set var="intArray" value="<%=numbers%>" />
<p>The length of numbers[]: ${fn:length(intArray)}</p>
<br>
<p><strong>toUpperCase("I am learning JSTL"): </strong>${fn:toUpperCase("I am learning JSTL")}</p>

<br>
<p><strong>toLowerCase("I am learning JSTL"): </strong>${fn:toLowerCase("I am learning JSTL")}</p>

<br>
<p><strong>substring("I am learning JSTL", 2, 5): </strong>${fn:substring("I am learning JSTL", 2, 5)}</p>

<br>
<p><strong>substringAfter("I am learning JSTL", "learning"): </strong>${fn:substringAfter("I am learning JSTL", "learning")}</p>

<br>
<p><strong>substringBefore("I am learning JSTL", "learning"): </strong>${fn:substringBefore("I am learning JSTL", "learning")}</p>

<br>
<p><strong>trim("    I   am learning    JSTL         "): </strong>${fn:trim("    I   am learning    JSTL         ")}</p>

<br>
<p><strong>replace("I am learning JSTL", "learning", "studying"): </strong>${fn:replace("I am learning JSTL", "learning", "studying")}</p>

<br>
<p><strong>replace("   I am learning JSTL   ", " ", "_"): </strong>${fn:replace("   I am learning JSTL   ", " ", "_")}</p>

<br>
<p><strong>indexOf("I am learning JSTL", "l"): </strong>${fn:indexOf("I am learning JSTL", "l")}</p>

<c:set var="theCompany" value="SynergisticIT Comupsoft Private Limited" />
<p>var theCompany = "SynergisticIT Comupsoft Private Limited"</p>
<br>1.
<strong>indexOf(theCompany, "p"): </strong>${fn:indexOf(theCompany, "p")}
<br>2.
<strong>startsWith(theCompany, "Compu"): </strong>${fn:startsWith(theCompany, "p")}
<br>3.
<strong>contains(theCompany, "Compu"): </strong>${fn:contains(theCompany, "p")}
<br>4.
<strong>containsIgnoreCase(theCompany, "compu"): </strong>${fn:containsIgnoreCase(theCompany, "p")}
<br>5.
<strong>endsWith(theCompany, "ted"): </strong>${fn:endsWith(theCompany, "ted")}
<br>6.
<strong>split(theCompany, " "): </strong>
<c:set var="tokens" value="${fn:split(theCompany, ' ')}" />
<c:forEach var="token" items="${tokens}" >
<br>${token}
</c:forEach>

<br>7. ${fn.join(tokens, "*")}
<br>8. ${fn.join(tokens, "==")}
<br>9. ${fn.join(tokens, "$$$")}

<br>10.
<c:set var="CompanyNameInHtmlTags" value="<b><i>SynergisticIT Comupsoft Private Limited</i></b>" />
Using html/xml: ${CompanyNameInHtmlTags}
<br>using escapeXml function: ${fn:escapeXml(CompanyNameInHtmlTags)}


</body>
</html>