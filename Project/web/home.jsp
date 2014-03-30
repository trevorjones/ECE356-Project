<%--
    Document   : home
    Created on : 23-Mar-2014, 6:07:53 PM
    Author     : william
--%>
<%@page import="servlets.QueryServlet"%>
<%@page import="models.Doctor"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null) {
    response.sendRedirect("index.jsp");
} else { %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Welcome <%= user.getFirstName() %> <%= user.getLastName() %></title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
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
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_ALL %>">Visitation Records</a>
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

        <div class="container">
            <h2><span style="text-transform:capitalize;"><%= user.getType()%> <%= user.getFirstName() %> <%= user.getLastName() %></span>'s Profile</h2>
            <table class="table">
                <tr>
                   <th>User ID</th>
                   <th>First Name</th>
                   <th>Last Name</th>
                   <th>Email</th>
                   <% if (user.getType().equals("doctor")) { %>
                        <th>Specialization</th>
                   <% } else if (user.getType().equals("patient")) { %>
                        <th>Address</th>
                        <th>Phone Number</th>
                   <% } %>
                </tr>
                <tr>
                   <td><%= user.getId() %></td>
                   <td><%= user.getFirstName() %></td>
                   <td><%= user.getLastName() %></td>
                   <td><%= user.getEmail() %></td>
                   <% if (user.getType().equals("doctor")) { %>
                        <td><%= ((Doctor) user).getSpecialization() %></td>
                   <% } else if (user.getType().equals("patient")) { %>
                        <td><%= ((Patient) user).getAddress()%></td>
                        <td><%= ((Patient) user).getPhone()%></td>
                   <% } %>
                </tr>
            </table>
            <a href='user_edit.jsp'>Edit Profile Information</a>
        </div>
    </body>
</html>
<% } %>
