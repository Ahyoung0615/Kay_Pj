<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<style type="text/css">

#container{
		width: 800px;
		margin: 300px auto;
		margin-left: 600px;
		height: 70vh; 
	}
</style>
</head>
<body>
	<form>
		<div id="container">
			<table border="1">
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="id" id="id"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="password" id="password"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<th>핸드폰 번호</th>
						<td><input type="text" name="phone" id="phone"></td>
					</tr>
					<tr>
						<th>프로필 사진</th>
						<td><input type="file" name="filename" id="filename"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr style="text-align: center;">
						<td colspan="2"><input type="button" value="가입" onclick="singIn(event)"> </td>
					</tr>
				</tfoot>
			</table>
		</div>	
	</form>
</body>
<script type="text/javascript">
	function singIn(event){
		event.preventDefault();
		
		var frm = document.forms[0];
		
		var id = document.getElementById("id");
		var password = document.getElementById("password");
		var name = document.getElementById("name");
	
		if(id.value == "" || password.value == "" || name.value == "") {
			alert("내용을 필수로 입력해주세요");
		} else if(id.value != ""){
			alert("회원가입 완료");
			frm.action = "./singIn.do";
			frm.method = "post";
			frm.enctype="multipart/form-data"
			frm.submit();
		}
	}
</script>
</html>