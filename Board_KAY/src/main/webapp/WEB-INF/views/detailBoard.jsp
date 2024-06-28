<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	
	<div id="container">
		<table class="table table-condensed">
			<tbody>
				<tr>
					<td class="info">아이디</td>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<td class="info">제목</td>
					<td>${dto.title}</td>
				</tr>
				<tr>
					<td class="info">내용</td>
					<td>${dto.content }</td>
				</tr>
				<tr>
					<td class="info">등록일</td>
					<fmt:parseDate value="${dto.createat}" var="fmtDate" pattern="yyyy-MM-dd"/>
					<fmt:formatDate var="newDate" value="${fmtDate}" pattern="yyyy년 MM월 dd일"/>
					<td>${newDate}</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
					<form class="btn-group btn-group-justified">
						<input type="hidden" name="seq" value="${dto.seq}">
						<c:if test="${loginDto.auth eq 'ADMIN' || loginDto.id eq dto.id}">
							<div class="btn-group">
								<button class="btn btn-danger" onclick="del(event)">삭제</button>
							</div>
						</c:if>
						<c:if test="${loginDto.id eq dto.id}">
							<div class="btn-group">
								<button class="btn btn-info" onclick="modify(event)">내 글 수정</button>
							</div>
						</c:if>
					</form>
					</td>
				</tr>
				<tr>
					<td colspan="2"><button type="button" class="btn btn-info"onclick="location.href='./allSelect.do'" >메인화면</button> </td>
				</tr>
			</tfoot>
		</table>
	</div>
	
<%@ include file="./footer.jsp" %>	
</body>
</html>