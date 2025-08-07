<%@page import="com.khyuna0.DTO.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
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

	<!-- 게시판 글 리스트 -->
	
	<h2>자유 게시판 목록</h2>
	<hr>
	<table>
		<tr>
			<th>No.</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
		</tr>
		
		<c:forEach var="boardDto" items="${boardList}" varStatus="status"> <!-- items 에 배열 또는 컬렉션만 가능 -->
			<tr>
				<td>${status.count}</td>
				<td>	
				<!-- 게시판 글 제목이 20자 이상일 때 20자 까지만 보여주고 ... 처리하기 -->				
				<c:choose>
					<c:when test="${fn:length(boardDto.btitle) > 20}">
					<a href="#">${fn:substring(boardDto.btitle,0,20)}...</a>
					</c:when>
					<c:otherwise>
					<a href="#">${boardDto.btitle}</a>
					</c:otherwise>
				</c:choose>
				</td>
				<td>${boardDto.bwriter}</td>
				<td>${boardDto.bdate}</td>
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>