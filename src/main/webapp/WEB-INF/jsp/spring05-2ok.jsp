<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 표현식을 쓰려면 밑에 라이브러리 두개가 필요. tomcat사이트 확인 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring 3일차 - Controller -> View로 배열값 출력(표현식)</title>
</head>
<body>
<!-- 
	:set(표현식 변수 생성) var 변수명을 생성해서 함수를 이용하여 값을 받는 형태 
	:forEach 배열을 반복시킬 때 사용
	:if 조건문을 생성. 조건형태는 test 이름으로 조건형태를 생성
-->
	
	<c:set var="delete" value="${person_delete}"></c:set>
	<p>고객명 리스트</p>
	<c:set var="ea" value="${fn:length(person_list)}"></c:set>
	<p>가입자 수 : ${ea}</p>
	<p>탈퇴 인원 수 : ${delete}</p>
	<ul>
		<!-- 
			forEach문 items
			varStatus : 순차번호, 배열의 첫번째 값, 배열의 마지막값, 갯수
			st.index : 배열 번호
		-->
		<c:set var="person_ea" value="${ea-1}"></c:set>
		<c:forEach var="username" items="${person_list}" varStatus="st">
			<c:if test="${username !='강감찬'}">	<!-- 여기서 test는 조건식 -->
			<li>번호 : ${person_ea+1} 	이름 : ${username}</li>
			</c:if>
			<c:set var="person_ea" value="${person_ea-1}"></c:set>
		</c:forEach>
	</ul>
	
	<!-- for문 begin, end, step -->
	<c:forEach var="w" begin="1" end="5" step="1">
		${w}
	</c:forEach>
</body>
</html>