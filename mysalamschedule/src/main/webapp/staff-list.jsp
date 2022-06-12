<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>MasjidSalam Schedule Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #95ac80"">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> MasjidSalam </a>
			</div>

			<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Staffs</a></li>
			
				
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Staffs</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Staff</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Staff Name</th>
						<th>Staff Address</th>
						<th>Staff Phone Number</th>
						<th>Staff Email</th>
						<th>Staff Role</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="staff" items="${listStaff}">

						<tr>
							<td><c:out value="${staff.id}" /></td>
							<td><c:out value="${staff.name}" /></td>
							<td><c:out value="${staff.address}" /></td>
							<td><c:out value="${staff.phone}" /></td>
							<td><c:out value="${staff.email}" /></td>
							<td><c:out value="${staff.role}" /></td>
							<td><a href="edit?id=<c:out value='${staff.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${staff.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>