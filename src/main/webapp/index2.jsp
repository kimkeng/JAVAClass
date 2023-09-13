<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>페이지2 접근형태 - sessionStorage + getParameter</title>
</head>
<body>
<input type="button" value="클릭" onclick="abc()">
</body>
<script>
var today = "";
var data = "";

	today = Date.now();
	data = sessionStorage.setItem("time",today);
	console.log(today);

	function abc(){
		window.open("./index3.jsp?days="+today,"","");
		location.href="./login.html";
	}	
</script>
</html>