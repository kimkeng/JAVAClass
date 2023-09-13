<!--View-->
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//9
int ea = (int)request.getAttribute("listea");
ArrayList<ArrayList<String>> plist = (ArrayList<ArrayList<String>>)request.getAttribute("plist"); 
//숫자를 자동으로 천단위당 자동 콤마 적용하는 class
DecimalFormat df = new DecimalFormat ("###,###");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품페이지</title>
</head>
<body>
<!-- 10 -->
<p>등록된 상품 갯수<%=ea%>
<table border="1" width="1000" align="center">
<thead>
	<tr>
	<th width="10%">번호</th>
	<th width="20%">상품코드</th>
	<th width="30%">상품명</th>
	<th width="20%">상품가격</th>
	<th width="10%">수정/삭제</th>
	</tr>
</thead>
<tbody>
	<%
		int w = 0 ;
		do{
	%>
		<tr align="center">
			<td><%=ea-w%></td>
			<td><%=plist.get(w).get(1)%></td>
			<td align="left"><%=plist.get(w).get(2)%></td>
			<td><%=df.format(Integer.parseInt(plist.get(w).get(3)))%> 원</td> <!-- .getClass() : type알기 빨간불은 무시하자 -->
			<td>
			<input type="button" value="수정" onclick="modify_pd('<%=plist.get(w).get(0)%>')">
			<input type="button" value="삭제" onclick="delete_pd('<%=plist.get(w).get(0)%>')">
			</td>
		</tr>
<%
		w++;
		} while(w < plist.size());
%>		
</tbody>
</body>
<script>
	function modify_pd(idx){
		location.href='./product_modify.do?idx='+idx;
	}

	function delete_pd(idx){
		if(confirm("해당 데이터를 삭제 하시겠습니다? 절대 복구 안됩니다.")){
			location.href='./product_delete.do?idx='+idx;  //-> controller 만들자 		
		}
		else{
			alert("삭제 취소하셨습니다.");
			return false;
		}
	}
</script>
</html>