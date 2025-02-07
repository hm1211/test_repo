<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KaKao Login</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<c:if test="${!loginStatus }">
		<h1>Login</h1>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=3d78a9f235b0ea1c45d65b98fe4deb52&redirect_uri=http://localhost:8090/authResult&response_type=code">
			<img alt="카카오 로그인 버튼" src="/resources/images/kakao_login.png">
		</a>
	</c:if>
	
	<c:if test="${loginStatus }">
		<h1>Logout</h1>
		<a href="https://kauth.kakao.com/oauth/logout?client_id=3d78a9f235b0ea1c45d65b98fe4deb52&logout_redirect_uri=http://localhost:8090/authResult">
			<img alt="카카오 로그아웃 버튼" src="/resources/images/kakao_logout.png">
		</a>
	</c:if>
</body>
</html>