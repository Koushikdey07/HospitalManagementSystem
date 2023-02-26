<%@page import="com.entity.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor List</title>
<%@include file="../component/all_css.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@include file="navbar.jsp" %>
			<div class="container-fluid p-3">
				<div class="row">
								<p class="fs-3 text-center">Doctor List</p>
								
								<c:if test="${not empty errorMsg}">
									<p class="fs-3 text-center text-danger">${errorMsg}</p>
									<c:remove var="errorMsg" scope="session" />
								</c:if>
								<c:if test="${not empty succMsg}">
									<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
									<c:remove var="succMsg" scope="session" />
								</c:if>
								<table class="table table-striped table-hover">
		  						<thead>
		    					<tr>
								      <th scope="col">Full Name</th>
								      <th scope="col">Date Of Birth</th>
								      <th scope="col">Qualification</th>
								      <th scope="col">Specialist</th>
								      <th scope="col">Email</th>
								      <th scope="col">Contact No</th>
								      <th scope="col">Action</th>
		    					</tr>
		  						</thead>
								<tbody>
									<%
									DoctorDao dao = new DoctorDao(DBConnect.getConn());
									List<Doctor> list = dao.getAllDoctor();
									for(Doctor d:list)
									{%>
										<tr>
								      		<td><%=d.getFullName() %></td>
								      		<td><%=d.getDob() %></td>
								      		<td><%=d.getQualification() %></td>
								      		<td><%=d.getSpecialist() %></td>
								      		<td><%=d.getEmail() %></td>
								      		<td><%=d.getContactNo() %></td>	
								      		<td>
												<a href="edit_doctor.jsp?id=<%=d.getId() %>" class="btn btn-sm btn-success">Edit</a>		
												<a href="../deleteDoctor?id=<%=d.getId()%>" class="btn btn-sm btn-danger">Delete</a>						      		
								      		</td>	
								      	</tr>	
									<%}
									%>
								</tbody>
								</table>
						
					
				</div>
			</div>
</body>
</html>