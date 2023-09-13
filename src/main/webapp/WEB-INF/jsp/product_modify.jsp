<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 내용 출력 및 수정파트</title>
</head>
<body>
	<form id="f" method="post" action="./product_modifyok.do">
		상품코드 : <input type="text" name="pcode" value="${data.get(1)}" readonly="readonly"><br>
		<input type="hidden" name="pidx" value="${data.get(0) }">
		상품명 : <input type="text" name="pname" value="${data.get(2)}"><br>
		상품금액 : <input type="text" name="pmoney" value="${data.get(3) }"><br>
		상품 이미지 : <img src="${data.get(4)}" style="width:150px;"><br> 
		할인율 : <input type="text" name="psale" value="${data.get(5) }"> %<br>
		사용 유/무 :
		<input type="radio" name="puse" value="Y" ${data.get(6) eq "Y" ? "checked" : ""}>사용
		<input type="radio" name="puse" value="N" ${data.get(6) eq "N" ? "checked" : ""}>미사용
		<br><br>
		<input type="button" value="수정완료" onclick="modifyok()">
	</form>
</body>
<script>
	function modifyok(){
		f.submit();
	}
</script>
</html>