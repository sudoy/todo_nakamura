<%@ page contentType="text/html; charset=UTF-8" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

		<!-- start header -->
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Todoリスト</a>
		</div>
	</div>
	</nav>
	<!-- end header -->

		<!-- start main -->
	<div class="container">

		<!-- alert area -->
		<div class="alert alert-success alert-dismissible fade in" role="alert">
		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span></button>
			<h4><strong>完了しました！</strong></h4>
			<ul>
			<li>No.27 のTodoを更新しました。
			</ul>
		</div>
		<div class="alert alert-danger alert-dismissible fade in" role="alert">
		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span></button>
			<h4><strong>エラーが発生しました！</strong></h4>
			<ul>
			<li>題名は必須入力です。
			<li>題名は100文字以内にして下さい。
			<li>期限は「YYYY/MM/DD」形式で入力して下さい。
			</ul>
		</div>

	</div>