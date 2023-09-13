<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring1.html에 대한 결과값 출력 (view)</title>
<script>
	var data1 = "${code}";
	var data2 = "${name}";
	if(data1 == "" || data2 == ""){
		alert("올바른 접근 방식 아닙니다.");
		history.go(-1);
	}
</script>
</head>
<body>
	상품코드 : ${code} <br>
	상품명 : ${name}
</body>
</html>