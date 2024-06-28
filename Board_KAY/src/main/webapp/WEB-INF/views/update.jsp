<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 화면</title>
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
		<form action="./updateBoard.do" method="post">
			<input type="hidden" name="seq" value="${dto.seq}">
			<table class="table table-condensed">
				<tr>
					<th class="info">아이디</th>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<th class="info">제목</th>
					<td>${dto.title}</td>
				</tr>
				<tr>
					<th class="info">내용</th>
					<td>
						<textarea rows="5" class="form-control" name="content" id="content"></textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;">
						<input type="submit" value="수정 완료" onclick="update(event)">
						<input type="button" value="메인화면" onclick="location.href='./allSelect.do'">
					</th>
				</tr>
			</table>
		</form>
	</div>
<%@ include file="./footer.jsp" %>
</body>
</html>