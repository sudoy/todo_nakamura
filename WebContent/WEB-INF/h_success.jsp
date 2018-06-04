<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${success.size() > 0}">
	<div class="alert alert-success alert-dismissible fade in" role="alert">
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">×</span>
		</button>
		<h4>
			<strong>完了しました！</strong>
		</h4>
		<ul>
			<c:forEach var="success" items="${success}">
				<li>${success}</li>
			</c:forEach>
		</ul>
	</div>

	<%
		session.setAttribute("success", null);
	%>

</c:if>