<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<link rel="stylesheet" href="./css/loginForm.css">

<body>
	<div id="container">
		<form class="box" action="./login.do" method="post">
		 	<h1>login</h1>
		 	<div>
		 		<input type="text" name="id" id="id">
		 		<input type="button" value="중복 검사" id="idChk" onclick="idChk(event)">
		 	</div>
		 	<input type="password" name="password">
		 	<input type="submit" value="로그인">
		 	<input type="button" value="회원가입" onclick="location='./singIn.do'">
		 </form>
		 <div id="notMember"></div>
	 </div>

</body>
</html>