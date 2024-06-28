<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 글 조회</title>
</head>
<%@ include file="./header.jsp" %>
<body>
	<div id="container">
	<div style="font-size: 20px; text-align: center;">
		<ul class="pagination pagination-sm">
				<c:if test="${page.page > page.countPage}">
					<li>
						<a href="./selectMyPost.do?page=1">&lt;&lt;</a>
					</li>	
				</c:if>
				<c:if test="${page.page > 1}">
					<c:choose>
						<c:when test="${(page.stagePage - page.countPage) < 0}">
							<li>
								<a href="./selectMyPost.do?page=1">&lt;</a>
							</li>
						</c:when>	
						<c:otherwise>
							<li>
								<a href="./selectMyPost.do?page=${page.stagePage - page.countPage}">&lt;</a>
							</li>		
						</c:otherwise>
					</c:choose>	
				</c:if>
				<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
					<li ${i == page.page ? "class='active'" : ""}><a href="./selectMyPost.do?page=${i}">${i}</a></li>
				</c:forEach>
				<fmt:parseNumber var="num1" integerOnly="true" value="${(page.totalPage-1) / page.countPage}" />
				<fmt:parseNumber var="num2" integerOnly="true" value="${(page.page-1) / page.countPage}" />
				<c:if test="${num1 > num2}">
					<li>
						<a href="./selectMyPost.do?page=${page.stagePage + page.countPage}">&gt;</a>
					</li>
				</c:if>
				<c:if test="${page.endPage < page.totalPage}">
					<li>
						<a href="./selectMyPost.do?page=${page.totalPage}">&gt;&gt;</a>
					</li>
				</c:if>
			</ul>
	</div>
	<form>
      <input type='hidden' id="clickSeq" name='seq'/>
      <table class="table table-condensed">
         <thead>
            <tr>
               <th>연번</th>
               <th>제목</th>
               <th>작성일</th>
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
            <c:if test="${dto.delflag eq 'N'}">
               <tbody>
                  <tr>
                     <td>${dto.id} ${dto.seq}</td>
                     <td><a href="./detailSelect.do?seq=${dto.seq}">${dto.title}</a></td>
                     <td>${dto.content }</td>
                     <fmt:parseDate value="${dto.createat}" var="fmtDate" pattern="yyyy-MM-dd"/>
                     <fmt:formatDate var="newDate" value="${fmtDate}" pattern="yyyy년 MM월 dd일"/>
                     <td>${newDate}</td>
                     <td><button class="btn btn-danger" value="${dto.seq}" onclick="del(event, this.value)">삭제</button> </td>
                  </tr>
               </tbody>
               </c:if>
            </c:forEach>
         </c:if>
      </table>
   </form>
   </div>
<%@ include file="./footer.jsp" %>
</body>
<script type="text/javascript">

function del(event, seq){
   event.preventDefault();
   
   var frm = document.forms[0];
   var clickSeq = document.getElementById('clickSeq');
   clickSeq.value = seq;


   if(confirm("글을 삭제하시겠습니까?")){
      frm.action = "./deleteBoard.do";
      frm.method = "post";
      frm.submit();
   } else{
      alert("삭제가 취소되었습니다");
   }
}

</script>
</html>