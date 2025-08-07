<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<%--
	<%
		String sid = (String) session.getAttribute("sid");
	
	%>
	--%>
	<%	// 로그인 하지 않은 유저 보내기
		if(session.getAttribute("sid") == null) {
			response.sendRedirect("login.do");
		};
	%>
	
	<h2>로그인 성공</h2>
	<hr>
	<h3>[${mid }]님 환영합니다!</h3> <br><br>
	<hr>
	<a href="freeboard.do">게시판 보기</a>
	<hr>
	<a href="logout.do">로그아웃 하기</a>
	<hr>
	<a href="memberList.do">회원 리스트 보기</a>
</body>
</html>