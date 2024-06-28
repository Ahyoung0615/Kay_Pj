<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대기 회원</title>
</head>
<%@ include file="header.jsp" %>
<body>

	<div id="container">
		<div style="font-size: 20px; text-align: center;">
			<ul class="pagination pagination-sm">
					<c:if test="${page.page > page.countPage}">
						<li>
							<a href="./memberInfo.do?page=1">&lt;&lt;</a>
						</li>	
					</c:if>
					<c:if test="${page.page > 1}">
						<c:choose>
							<c:when test="${(page.stagePage - page.countPage) < 0}">
								<li>
									<a href="./memberInfo.do?page=1">&lt;</a>
								</li>
							</c:when>	
							<c:otherwise>
								<li>
									<a href="./memberInfo.do?page=${page.stagePage - page.countPage}">&lt;</a>
								</li>		
							</c:otherwise>
						</c:choose>	
					</c:if>
					<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
						<li ${i == page.page ? "class='active'" : ""}><a href="./memberInfo.do?page=${i}">${i}</a></li>
					</c:forEach>
					<fmt:parseNumber var="num1" integerOnly="true" value="${(page.totalPage-1) / page.countPage}"/>
					<fmt:parseNumber var="num2" integerOnly="true" value="${(page.page-1) / page.countPage}" />
					<c:if test="${num1 > num2}">
						<li>
							<a href="./memberInfo.do?page=${page.stagePage + page.countPage}">&gt;</a>
						</li>
					</c:if>
					<c:if test="${page.endPage < page.totalPage}">
						<li>
							<a href="./memberInfo.do?page=${page.totalPage}">&gt;&gt;</a>
						</li>
					</c:if>
				</ul>
		</div>
		<form>
			<input type="submit" value="삭제" style="margin-right: 20px;" onclick="del(event)">		
			<input type="submit" value="승인" style="margin-right: 20px;" onclick="success(event)">		
			<table class="table table-hover">
				<thead>
					<tr>
						<th><input type="checkbox" id="allchk" onclick="chkAll(this.checked)"></th>
						<th>연번</th>
						<th>프로필 사진</th>
						<th>아이디</th>
						<th>이름</th>
						<th>휴대폰 번호</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(list) == 0}">
						<tr>
							<td colspan="5" style="color: blue; font-size: 14px; text-align: center;">-- 가입 신청한 회원이 없습니다 --</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(list) != 0}">
						<c:forEach var="dto" items="${list}" varStatus="vs">
							<tr>
								<td><input type="checkbox" class="chk" name="chk" value="${dto.id}"></td>
								<td>${page.totalCount - (page.page -1) * page.countList - vs.index}</td>
								<td>
									<c:choose>
										<c:when test="${dto.filename eq null}">
											<img alt="프로필 사진" src="./upload/defalutImg.png" style="width: 70px; height: auto;">
										</c:when>
										<c:otherwise>
											<img alt="프로필 사진" src="./upload/${dto.filename}" style="width: 70px; height: auto;">
										</c:otherwise>
									</c:choose>
								</td>
								<td>${dto.id}</td>
								<td>${dto.name}</td>
								<td>${dto.phone}</td>
							</tr>
						</c:forEach>
						</c:if>
				</tbody>
			</table>
		</form>
	</div>
<%@ include file="footer.jsp" %>
</body>
<script type="text/javascript">
	function del(event){
		
		var frm = document.forms[0];
		
		if(chCheckedCnt() == 0) {
			event.preventDefault();
			alert("아무것도 선택되지 않았습니다.");
		} else{
			if(confirm("삭제 하시겠습니까?")){
				frm.action = "./memberDel.do";
				frm.method = "post";
				frm.submit();
			} else{
				alert("삭제가 취소되었습니다.");
			}
		}
	}
	
	function success(event){
		
		var frm = document.forms[0];
		
		if(chCheckedCnt() == 0) {
			event.preventDefault();
			alert("아무것도 선택되지 않았습니다.");
		} else{
			if(confirm("승인 하시겠습니까?")){
				frm.action = "./memberSuccess.do";
				frm.method = "post";
				frm.submit();
			} else{
				alert("삭제가 취소되었습니다.");
			}
		}
	}
	// 전체 체크
	function chkAll(bool){
		var chks = document.getElementsByName("chk");
		for(let i=0; i<chks.length; i++){
			chks[i].checked = bool;
		}
	}

	// 하위 체크 박스 갯수 확인
	var chCheckedCnt = function(){
		var chks = document.getElementsByName("chk");
		let cnt = 0;
		for(let i=0; i<chks.length; i++){
			if(chks[i].checked){
				cnt++;
			}
		}
		return cnt;
	}

	// 체크 상태에 따른 최상단 체크박스 상태 변경
	window.onload = function(){
		var chks = document.getElementsByName("chk");
		var allchk = document.getElementById("allchk");
		
		for(let i=0; i<chks.length; i++){
			chks[i].onclick = function(){
				if(chks.length == chCheckedCnt()){
					allchk.checked = true;
				} else{
					allchk.checked = false;
				}
			}
		}
	}
</script>
</html>