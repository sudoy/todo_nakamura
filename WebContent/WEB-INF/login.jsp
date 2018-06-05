<%@ page contentType="text/html; charset=UTF-8"
	import="todo.utils.HTMLUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include_header.jsp" />

<!-- start main -->
<div class="container form-signin">
	<h4>ログイン</h4>
	<jsp:include page="h_errors.jsp" />
	<jsp:include page="h_success.jsp" />

	<!-- password -->
	<form class="form-horizontal" method="post" action="login.html">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="email">メールアドレス</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" name="email" id="email"
					placeholder="メールアドレス" value="${param.email}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label" for="pass">パスワード</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="pass" id="pass"
					placeholder="パスワード" value="">
			</div>
		</div>
		<br>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" name="update" class="btn btn-primary pull-right" value="ログイン">
			</div>
		</div>
	</form>
</div>




<jsp:include page="include_footer.jsp" />