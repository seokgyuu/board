<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>
	<div align="center">
		<table class="boardTable">
			<tr>
				<th>번호</th>
				<th>글제목</th>
				<th>아이디</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:choose>
				<c:when test="${list == null }">
					<tr>
						<td colspan="5" align="center">
							<span style="font-weight: bold;">작성한 글이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.seq }</td>
							<td><a href="/MyHome/board/content.brd?seq=${dto.seq }">${dto.title }</a></td>
							<td>${dto.id }</td>
							<td>${dto.logtime }</td>
							<td>${dto.hit }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<c:if test="${login != null }">
			<div class="btnBox">
				<button onclick="document.location.href='/MyHome/board/writeForm.brd'">글쓰기</button>
			</div>
		</c:if>
		<c:if test="${nowPage > 2 }">
			<a href="/MyHome/board/list.brd?start=${start - 10 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="/MyHome/board/list.brd?start=${start - 5 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="/MyHome/board/list.brd?start=${start + 5 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="/MyHome/board/list.brd?start=${start + 10 }">[다음]</a>
		</c:if>
	</div>
<%@ include file="/layout/footer.jsp" %>





















