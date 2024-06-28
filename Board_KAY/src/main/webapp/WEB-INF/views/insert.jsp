<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<script type="text/javascript">
//뒤로 가기, 새로고침 방지
function notReload(event) {
    if ((event.ctrlKey && (event.keyCode === 78 || event.keyCode === 82)) || event.keyCode === 116) {
        event.preventDefault();
        event.stopPropagation();
        alert("새로고침키를 사용할 수 없습니다.");
    }
}
document.addEventListener('keydown', notReload);
</script>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
	<form class="form-horizontal" >
		<table class="table table-bordereed form-group">
			<tbody class="form-group">
				<tr class="form-group">
					<th>아이디</th>
					<td class="form-control">${loginDto.id}</td>
				</tr>
				<tr class="form-group">
					<th>제목</th>
					<td><input type="text" name="title" id="title" class="form-control"> </td>
				</tr>
				<tr class="form-group">
					<th>내용</th>
					<td>
						<textarea rows="5" name="content" id="content" class="form-control"></textarea>
					</td>
				</tr>
				<tr class="form-group">
					<td><input type="submit" class="btn btn-primary" value="작성" onclick="insert(event)"></td>
					<td><input type="reset" class="btn btn-primary" value="다시 입력"></td>
					<td><input type="button" value="돌아가기" onclick="location.href='./allSelect.do'"></td>
				</tr>
			</tbody>
		</table>	
	</form>
	</div>
<%@ include file="./footer.jsp" %>
</body>
</html>