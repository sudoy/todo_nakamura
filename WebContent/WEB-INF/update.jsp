<%@ page contentType="text/html; charset=UTF-8" import="todo.utils.HTMLUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include_header.jsp"></jsp:include>

<div class="container">
	<!-- entry area -->

	<form class="form-horizontal" method="post" action="update.html">
		<h4 class="page-header">
			<strong>更新フォーム</strong>
		</h4>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="title">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="title" placeholder="題名"
					value="${param.title != null ? param.title : todo.title}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="detail">詳細</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="3" name="detail" placeholder="詳細">${param.detail != null ? param.detail : todo.detail}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">重要度</label>
			<div class="col-sm-10">
				<div class="radio">
					<label> <input type="radio" name="priority" value="3"
						${param.priority.equals("3") ? HTMLUtils.check(param.priority, '3') : HTMLUtils.check(todo.priority, '3')}>
						★★★
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="priority" value="2"
					${param.priority.equals("2") ? HTMLUtils.check(param.priority, '2') : HTMLUtils.check(todo.priority, '2')}>
						★★
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="priority" value="1"
					${param.priority.equals("1") ? HTMLUtils.check(param.priority, '1') : HTMLUtils.check(todo.priority, '1')}>
						★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="limitDay">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="limitDay"
					placeholder="期限" value="${param.limitDay != null ? param.limitDay : HTMLUtils.formating(todo.limitDay)}">
			</div>
		</div>
		<input type="hidden" name="id" value="${param.id}" />
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a class="btn btn-default" href="index.html" role="button">キャンセル</a>
				<input type="submit" name="update" class="btn btn-primary" value="更新">
				<input type="submit" name="delete" class="btn btn-danger pull-right" value="削除">
			</div>
		</div>
	</form>

</div>
<!-- end main -->

<jsp:include page="include_footer.jsp"></jsp:include>