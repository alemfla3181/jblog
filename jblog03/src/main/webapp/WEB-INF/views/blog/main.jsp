<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
pageContext.setAttribute("newLine", "\n");
pageContext.setAttribute("newSpace", " ");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">										
					<h4>${postvo.title }</h4>
					<p>
						${fn:replace(fn:replace(postvo.contents, newSpace, '&nbsp;'), newLine, '<br />')}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items='${postlist }' var='vo' varStatus='status'>
						<li><a href="${pageContext.request.contextPath}/${id}/${vo.category_no }/${vo.no}">${vo.title }</a> </li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.logo}">
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/navigation.jsp" />		
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />
	</div>
</body>
</html>