<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Todoリスト</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/navbar_costom.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>

<body>
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