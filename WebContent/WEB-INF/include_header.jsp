<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<a class="navbar-brand" href="index.html">Todoリスト</a>
			</div>
		</div>
	</nav>
	<!-- end header -->

	<!-- start main -->
	<div class="container">

		<!-- alert area -->
		<jsp:include page="h_success.jsp" />
		<jsp:include page="h_errors.jsp" />

	</div>