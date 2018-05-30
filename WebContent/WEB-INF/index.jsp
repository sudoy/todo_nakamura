<%@ page contentType="text/html; charset=UTF-8" import="java.sql.*" %>
<%
	ResultSet rs = (ResultSet) request.getAttribute("rs");
%>
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
				<%
					while (rs.next()) {
				%>
				<tr>
					<td><%=rs.getString("id")%></td>
					<td><a href="update.html?id=<%=rs.getString("id")%>"><%=rs.getString("title")%></a></td>
					<td><%=rs.getString("priority")%></td>
					<td><%=rs.getString("limit_day")%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			</table>
		</div>
		<a class="btn btn-primary" href="entry.html">追加</a>

	</div>
	<!-- end main -->

<jsp:include page="include_footer.jsp"></jsp:include>