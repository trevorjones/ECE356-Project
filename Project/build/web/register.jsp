<%--
    Document   : Register
    Created on : 23-Mar-2014, 11:54:50 AM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !user.getType().equals("staff")) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Register</title>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="home.jsp">Home</a>
                    </li>
                    <% if (user.getType().equals("financial officer")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_BY_FO %>">Doctors</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_FO %>">Patients</a>
                        </li>
                    <% } else if (user.getType().equals("doctor")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_DOCTOR %>&doctor_id=<%= user.getId()%>">Schedule</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.STAFF_QUERY %>&doctor_id=<%= user.getId()%>">Staff Members</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PRESCRIPTIONS_ALL %>">Prescription List</a>
                        </li>
                    <% } else if (user.getType().equals("staff")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_QUERY_BY_STAFF %>&staff_id=<%= user.getId()%>">Associated Doctors</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li>
                            <a href="register.jsp">Register a New User</a>
                        </li>
                    <% } else if (user.getType().equals("patient")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_AS_PATIENT %>&patient_id=<%= user.getId()%>">Visitation Records</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_PATIENT %>&patient_id=<%= user.getId()%>">Appointments</a>
                        </li>
                    <% } %>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="LogoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container" style="padding-top:40px;">
            <form class="form-horizontal" method="post" action="RegisterServlet">
                <h2 class="form-signin-heading" style="text-shadow: 1px 1px white;">Register</h2>
                <div class="form-group">
                    <label for="first_name" class="col-sm-2 control-label" style="width:120px;">First Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="first_name" name="first_name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="last_name" class="col-sm-2 control-label" style="width:120px;">Last Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="last_name" name="last_name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label" style="width:120px;">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" style="width:200px;" id="email" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="user_id" class="col-sm-2 control-label" style="width:120px;">User ID   </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="user_id" name="user_id">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label" style="width:120px;">Password</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="password" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:120px;">User Type</label>
                    <div class="col-sm-10">
                      <select class="form-control" name="type" style="width:200px;" required>
                          <option value="patient">Patient</option>
                          <option value="doctor">Doctor</option>
                          <option value="Staff">Staff</option>
                          <option value="financial officer">Financial Officer</option>
                      </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:120px;"></label>
                    <div class="col-sm-10">
                      <input class="form-control btn btn-success" style="width:200px;" type="submit" value="Add User"/>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
<% } %>
