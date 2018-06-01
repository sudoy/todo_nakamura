<%@ page contentType="text/html; charset=UTF-8"
	import="todo.utils.HTMLUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include_header.jsp"></jsp:include>

<div class="container">
	<!-- todo area -->
	<div class="table-responsive">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th class="col-md-1">#</th>
					<th class="col-md-6">題名</th>
					<th class="col-md-2">重要度</th>
					<th class="col-md-3">期限</th>
				</tr>
			</thead>
			<tbody>
				<!-- loop start -->
				<c:forEach var="todo" items="${list}">
					<tr>
						<td>${todo.id}</td>
						<td><a href="update.html?id=${todo.id}">${todo.title}</a></td>
						<td>${HTMLUtils.stars(todo.priority)}</td>
						<td>${HTMLUtils.formating(todo.limitDay)}</td>
					</tr>
				</c:forEach>
				<!-- loop end -->
			</tbody>
		</table>
	</div>
	<a class="btn btn-primary" href="entry.html">追加</a>

</div>
<!-- end main -->

<jsp:include page="include_footer.jsp"></jsp:include>