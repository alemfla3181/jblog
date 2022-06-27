<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
<a href="${pageContext.request.contextPath}/${blogvo.blog_id}"><h1 class="logo">${blogvo.title }</h1></a>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
					<c:if test="${blogvo.blog_id == authUser.id}">
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">블로그 관리</a></li>
					</c:if>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>