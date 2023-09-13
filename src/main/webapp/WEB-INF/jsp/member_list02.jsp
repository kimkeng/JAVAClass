<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//배열값이 null일 경우 jsp에서 해당 MTML 코드를 비활성화 시키는 방법
String aa = null;
try{
	List<ArrayList<String>> member_data = (ArrayList<ArrayList<String>>)request.getAttribute("member_data");
	int many = member_data.size();
	aa="ok";
}catch(Exception e){
	out.print("오류발생");
	aa = (String)request.getAttribute("member_data");
}
%>
<%if(aa != null) {%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
</head>
<body>

	<br><br>
	<form id="f" method="get" action="./spring06ok.do" onsubmit="return idsearch()" enctype="application/x-www-form-urlencoded">
	검색 : <input type="text" name="search">
	<input type="submit" value="검색">
	</form>
</body>
<%} %>
<script>
/*
 trim() => 앞, 뒤 공백만 삭제
 replace(,) => 첫번째 관련 단어만 변경
 replaceAll(,) => 모든 단어를 확인 하여 해당 단어로 변경
 */
 /*
var word = "장바구니의 정보를 출력하는 페이지 꼰데 꼰데";
var word2 = word.trim();	//앞, 뒤 공백 제거
var word2 = word.replace("꼰데","!@#$%")	//단어 전체중에 첫번째 공백만 제거 or 수정
var word2 = word.replaceAll("꼰데","!@#$%")	//모든 공백 제거 or 수정
console.log(word2);
*/

	function idsearch(){
	//입력에 따른 공백을 제거 후 조건문으로 재확인
		f.search.value = f.search.value.replaceAll(" ","");	//모든 공백 제거
		if(f.search.value == ""){
			alert("검색할 단어를 입력하세요!!");
			return false;
		}else{
			return;
		}
	}
</script>
</html>