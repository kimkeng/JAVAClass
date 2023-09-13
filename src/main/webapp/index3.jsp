<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewpory" content="width=device-width">
<title>Insert title here</title>
</head>
<body>
페이지3
</body>
<script>

	var fdata = sessionStorage.getItem("time");	
	var times = window.location.search.split("?days=");

	if(fdata == null){
		alert("올바른 접근 방법이 아닙니다.");
	}else{
		if(fdata != times[1]){
			alert("올바른 접속 방법이 아닙니다.");
		}
	}
</script>
</html>