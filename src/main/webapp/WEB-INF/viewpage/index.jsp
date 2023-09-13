<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Spring 기초1</title>
</head>
<body>
<header>
<%@ include file="./top.jsp" %>
</header>
<%=request.getAttribute("name") %><br> <!-- JSP전용코드 -->

<%-- 
${} // 표현식 코드 문법 (JSTL)
--%>  

고객명 : ${name}
</body>
</html>