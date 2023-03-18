<%@page import="com.dao.UserDao"%>
<%@page import="com.dao.SpecialistDao"%>
<%@page import="com.dao.AppointmentDao"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<%@include file="../component/all_css.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp" %>
	<c:if test="${ empty adminObj }">
		<c:redirect url="../admin_login.jsp"></c:redirect>
	</c:if>

	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>
		<c:if test="${not empty errorMsg}">
			<p class="fs-3 text-center text-danger">${errorMsg}</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>
		<c:if test="${not empty succMsg}">
			<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
			<c:remove var="succMsg" scope="session" />
		</c:if>
		
		<%DoctorDao d = new DoctorDao(DBConnect.getConn()); %>
		<%AppointmentDao ap = new AppointmentDao(DBConnect.getConn()); %>
		<%SpecialistDao sp = new SpecialistDao(DBConnect.getConn()); %>
		<%UserDao user = new UserDao(DBConnect.getConn()); %>
		
		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-danger">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">Doctor <br> <%=d.countDoctor() %> </p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-danger">
						<i class="fas fa-user-circle fa-3x"></i><br>
						<p class="fs-4 text-center">User <br><%=user.countUser() %></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-danger">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">Total Appointment <br><%=ap.countAppointment() %> </p>
					</div>
				</div>
			</div>
			<div class="col-md-4 mt-2">
				<div class="card paint-card " data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<div class="card-body text-center text-danger">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">Specialist <br><%=sp.countSpecialist() %></p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Specialist</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="../addSpecialist" method="post">
						<div class="form-group">
							<label>Enter Specialist Name</label> <input type="text" name="specName" class="form-control" required>
						</div>
						<div class="text-center mt-3">
							<button type="submit" class="btn btn-primary">Add</button>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>