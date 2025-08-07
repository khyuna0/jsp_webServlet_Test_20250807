<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style type="text/css">
	table {
		width: 600px;
		border-collapse: collapse;
	}
	th, td {
		border: 1px solid; #dddddd;
		padding: 10px;
		text-align: center;
	}
	th {
		background-color: #f5f5f5;

	}

</style>
</head>
<body>

	<%	// 로그인 하지 않은 유저 보내기
		if(session.getAttribute("sid") == null) {
			response.sendRedirect("login.do");
		};
	%>
	
	<h2>회원 목록</h2>
		<hr>
		<table>
			<tr>
				<th>No.</th>
				<th>회원 아이디</th>
				<th>회원 이름</th>
				<th>회원 나이</th>
				<th>회원 등록일</th>
			</tr>
			
			<c:forEach var="MemberDto" items="${memberList}" varStatus="status"> <!-- items 에 배열 또는 컬렉션만 가능 -->
				<tr>
					<td>${status.count}</td>
					<td>${MemberDto.id}</td>
					<td>${MemberDto.mname}</td>
					<td>${MemberDto.mage}</td>
					<td>${MemberDto.mdate}</td>
				</tr>
			</c:forEach>
		</table>
		
		<a href="welcome.do">환영 페이지로 다시 가기</a> <br><br>
		<!-- 뒤로 가기 -->
		<a href="javascript:history.go(-1);">뒤로 가기</a> <br><br>
		<!-- 뒤로 가기 버튼-->
		<input type="button" value="뒤로가기" onclick ="javascript:history.go(-1)">
</body>
</html>