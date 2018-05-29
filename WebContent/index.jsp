<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Todoリスト</title>
	<jsp:include page="include.jsp"></jsp:include>

</head>
<body>

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
			<tr>
			<td>1</td>
			<td><a href="update.html">テスト</a></td>
			<td>★</td>
			<td>2015/06/20</td>
			</tr>
			<tr>
			<td>2</td>
			<td><a href="update.html">テストテスト</a></td>
			<td>★★</td>
			<td>2015/06/20</td>
			</tr>
			<tr>
			<td>3</td>
			<td><a href="update.html">テストテストテスト</a></td>
			<td>★★★</td>
			<td>2015/06/20</td>
			</tr>
			<tr>
			<td>4</td>
			<td><a href="update.html">テストテストテストテスト</a></td>
			<td>★★</td>
			<td>2015/06/20</td>
			</tr>
			</tbody>
			</table>
		</div>
		<a class="btn btn-primary" href="entry.html">追加</a>

	</div>
	<!-- end main -->

</body>
</html>