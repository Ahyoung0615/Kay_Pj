<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS파일 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.8/dist/sweetalert2.min.css" >

<!-- JS파일 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.8/dist/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="./js/commJs.js"></script>

<style type="text/css">

#container{
		width: 800px;
		margin: 50px auto;
		height: 70vh; 
	}
	
	header{
		background: #015937;
		height: 100px;
		color: #000000;
	}
	
	footer{
		background: #015937;
		text-align: center;
		line-height: 20px;
		padding: 20px;		
	}
	
	.navbar .navbar-inverse{
		margin-top : 25px; 
	}
</style>
</head>
<body>
	<header>
		<div style="padding: 30px;">
			<h3 style="display: inline;">
				<a href="./allSelect.do">게시판 구현</a>
			</h3>
			<div style="display: inline; float: right;">
				<span style="color: white;">아이디: ${loginDto.id}(${loginDto.auth eq 'ADMIN' ?"관리자":"사용자" })</span>
				<button class="btn btn-danger" onclick="logout()">Logout</button>
			</div>				
		</div>	
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="./allSelect.do">김아영 게시판 구현</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li><a href="./allSelect.do">게시판</a></li>
	        <li><a href="./insertBoard.do">글작성</a></li>
	        <li><a href="./selectMyPost.do">내 글 조회</a></li>
	        <c:if test="${loginDto.auth eq 'ADMIN'}">
	        	<li><a href="./memberInfo.do">회원 목록 조회</a></li>
	        	<li><a href="./memberSuccess.do">회원 가입 대기 목록</a></li>
	        </c:if>
	      </ul>
	    </div>
	  </div>
	</nav>
	</header>
</body>
</html>


