<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap3.3.2.min.css">
</head>
<jsp:useBean id="customer" class="com.bean.Customer"></jsp:useBean>
<body>
	<div class="container">
		<table class="table table-condensed" id="customerForm">
			<caption>人员列表</caption>
			<thead>
				<tr>
					<th>姓名</th>
					<th>水平</th>
					<th>操作</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listCustomer}" var="list">
					<tr>
						<td style="display:none">${list.id }</td>
						<td>${list.name }</td>
						<td>${list.level }</td>
						<td><button type="button" class="btn btn-primary"
								onclick="update_btn(this)">修改</button></td>
						<td><button type="button" class="btn btn-primary"
								onclick="delete_btn(this)">删除</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="container">
			<div class="row">
				<div class="col-xs-1">
					<button type="button" class="btn btn-primary" onclick="add()"
						id="add_btn">添加</button>
				</div>
			</div>
		</div>

		<div class="container" style="display:none" id="editform">
			<p class="">
			<h2>编辑框</h2>
			</p>
			<div class="row">
				<div class="col-xs-4">
					<form role='form' id="customerform">
						<div class='form-group' id="form_group">
							<label for="name" id="namelabel">姓名</label> <input type='text'
								class="form-control" placeholder="输入姓名" name="customerName">
							<label for="level">level</label> <input type='text'
								class="form-control" placeholder="输入数字" name="level">
						</div>
						<button type="button" class="btn btn-primary" id="enter_btn">确认</button>
						<button type="button" class="btn btn-primary"
							onclick="returnbtn()">返回</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/secondPage.js"></script>
</body>
</html>