<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<jsp:include page="include_css.jsp"></jsp:include>

<body>
	<jsp:include page="include_header.jsp"></jsp:include>

	<div class="container">
		<!-- entry area -->
		<form class="form-horizontal" method="post" action="index.html">
		<h4 class="page-header"><strong>登録フォーム</strong></h4>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="title">題名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" placeholder="題名" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="detail">詳細</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" id=detail placeholder="詳細"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="priority">重要度</label>
				<div class="col-sm-10">
					<div class="radio">
					<label>
					<input type="radio" name="priority" value="3" checked> ★★★
					</label>
					</div>
					<div class="radio">
					<label>
					<input type="radio" name="priority" value="2"> ★★
					</label>
					</div>
					<div class="radio">
					<label>
					<input type="radio" name="priority" value="1"> ★
					</label>
					</div>
				</div>
			</div>
			<div class="form-group">
			<label class="col-sm-2 control-label" for="limit_day">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="limit_day" placeholder="期限" value="">
			</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="キャンセル"  onclick="history.back()">
					<input type="submit" class="btn btn-primary" value="追加">
				</div>
			</div>
		</form>

	</div>
	<!-- end main -->

</body>
</html>