 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<%@ include file="./header.jsp" %>
<body>

	<div id="container">
	<div style="font-size: 20px; text-align: center;">
		<ul class="pagination pagination-sm">
				<c:if test="${page.page > page.countPage}">
					<li>
						<a href="./allSelect.do?page=1">&lt;&lt;</a>
					</li>	
				</c:if>
				<c:if test="${page.page > 1}">
					<c:choose>
						<c:when test="${(page.stagePage - page.countPage) < 0}">
							<li>
								<a href="./allSelect.do?page=1">&lt;</a>
							</li>
						</c:when>	
						<c:otherwise>
							<li>
								<a href="./allSelect.do?page=${page.stagePage - page.countPage}">&lt;</a>
							</li>		
						</c:otherwise>
					</c:choose>	
				</c:if>
			
				<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
					<li ${i == page.page ? "class='active'" : ""}><a href="./allSelect.do?page=${i}">${i}</a></li>
				</c:forEach>
				<fmt:parseNumber var="num1" integerOnly="true" value="${(page.totalPage-1) / page.countPage}" />
				<fmt:parseNumber var="num2" integerOnly="true" value="${(page.page-1) / page.countPage}" />
				<c:if test="${num1 > num2}">
					<li>
						<a href="./allSelect.do?page=${page.stagePage + page.countPage}">&gt;</a>
					</li>
				</c:if>
				<c:if test="${page.endPage < page.totalPage}">
					<li>
						<a href="./allSelect.do?page=${page.totalPage}">&gt;&gt;</a>
					</li>
				</c:if>
			</ul>
	</div>
	<form>
		<c:if test="${loginDto.auth eq 'ADMIN'}">
			<input type="submit" value="삭제" id ="allChk" style="margin-right: 20px;" onclick="adminDel(event)">
		</c:if>
			<input type="button" value="새 글 작성" onclick="location.href='./insertBoard.do'">
			<table class="table table-hover">
				<thead>
					<tr>
					<c:if test="${loginDto.auth eq 'ADMIN'}">
						<th><input type="checkbox" id="allchk" onclick="chkAll(this.checked)"> </th>
					</c:if>
						<th>연번</th>
						<th>작성자</th>
						<th>제목</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(list) == 0}">
						<tr>
							<td colspan="5" style="color: blue; font-size: 14px; text-align: center;">-- 작성된 글이 없습니다 --</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(list) != 0}">
					<c:forEach var="dto" items="${list}" varStatus="vs">
						<tr>
						<c:if test="${loginDto.auth eq 'ADMIN'}">
							<td><input type="checkbox" class="chk" name="chk" value="${dto.seq}"></td>
						</c:if>
							<td>${page.totalCount - (page.page -1) * page.countList - vs.index}</td>
							<td>${dto.id}</td>
							<td>
								<c:choose>
									<c:when test="${dto.delflag eq 'Y'}">
										--삭제된 글 입니다--
									</c:when>
									<c:otherwise>
										<a href="./detailSelect.do?seq=${dto.seq}">${dto.title}</a>
									</c:otherwise>
								</c:choose>	
								
							</td>
							<fmt:parseDate value="${dto.createat}" var="fmtDate" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="newDate" value="${fmtDate}" pattern="yyyy년 MM월 dd일"/>
							<td>${newDate}</td>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
		</form>
	</div>

<%@ include file="./footer.jsp" %>	
</body>
</html>