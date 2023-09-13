<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//배열값이 null일 경우 jsp에서 해당 MTML 코드를 비활성화 시키는 방법
	int total = (int)request.getAttribute("total");	//전체가입자 수
	List<ArrayList<String>> member_data = (ArrayList<ArrayList<String>>)request.getAttribute("member_data");
	int many = member_data.size();
	String part = (String)request.getAttribute("part");
	//null 값을 조건에서 1순위로 체크 후 해당 데이터를 어떻게 처리할지를 코드로 작성
	String s = null;
	if(part != null && part.equals("tel")){
		s="selected";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
</head>
<body>

<p>회원가입 고객 리스트 가입자수 : <%=total %>명</p>
<p>검색한 고객 수 : <%=many %>명</p>
	<table border="1" width="1000">
		<thead>
			<tr>
				<td width="5%">번호</td>
				<td width="20%">아이디</td>
				<td width="30%">이메일</td>
				<td width="20%">연락처</td>
				<td width="5%">나이</td>
				<td width="20%">가입일자</td>
			</tr>
		</thead>
		<tbody>
		<!-- database에 내용 출력 바트 -->
		<!-- 배열값 중 첫번째 아이디값을 확인하여 값이 없을 경우에 대한 조건 -->
		<%if(member_data.get(0).get(1) == "null" ) {%>
		<tr align="center">
			<td colspan="6">검색한 내용을 찾을 수 없습니다.</td>
		</tr>
		<%
		}else{	//아이디 값이 null이 아닐 경우
		int w =0;
		do{
		%>
			<tr align="center">
				<td><%=total-- %></td>	<!-- 전체인원수 빼기 w -->
				<td><%=member_data.get(w).get(1)%></td>
				<td><%=member_data.get(w).get(2)%></td>
				<td><%=member_data.get(w).get(3)%></td>
				<td><%=member_data.get(w).get(4)%></td>
				<td><%=member_data.get(w).get(5).substring(0,10)%></td>
			</tr>
		<%
		w++;
		}while(w < many);
		}
		%>
		</tbody>
	</table>
	<br><br>
	<form id="f" method="get" action="./spring06ok.do" onsubmit="return idsearch()" enctype="application/x-www-form-urlencoded">
	
	검색 : 
	<!-- 검색시 분류 설정을 하여 검색 되도록 함 -->
	<select name="part">
		<option value="id" selected>아이디</option>
		<option value="tel" <%=s %>>연락처</option>
	</select>
	
	<input type="text" name="search">
	
	<input type="submit" value="검색">
	<input type="button" value="전체목록" onclick="alldata()">
	</form>
</body>
<script>
//검색한 단어를 입력값에 유지시키는 스크립트코드
	

	var wd = window.location.search;
	if(wd != ""){
		var sh = wd.split("&search=");
		f.search.value = sh[1];
	}
	
	function alldata(){
		location.href="./spring06ok.do"
	}

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