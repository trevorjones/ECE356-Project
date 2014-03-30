<%--
    Document   : user_edit
    Created on : 28-Mar-2014, 7:00:24 PM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="models.Doctor"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Information</title>
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
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
          <h2>Edit Profile</h2>
          <form method="post" action="UpdateUser?user_id=<%= user.getId() %>&user_type=<%= user.getType() %>">
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
                     <td><input type="text" id="first_name" name="first_name" value="<%= user.getFirstName() %>"></td>
                     <td><input type="text" id="last_name" name="last_name" value="<%= user.getLastName() %>"></td>
                     <td><input type="text" id="email" name="email" value="<%= user.getEmail() %>"></td>
                     <% if (user.getType().equals("doctor")) { %>
                          <td><input type="text" id="specialization" name="specialization" value="<%= ((Doctor) user).getSpecialization() %>"></td>
                     <% } else if (user.getType().equals("patient")) { %>
                          <td><input type="text" id="address" name="address" value="<%= ((Patient) user).getAddress()%>"></td>
                          <td><input type="tel" id="phone" name="phone" value="<%= ((Patient) user).getPhone()%>"></td>
                     <% } %>
                  </tr>
              </table>
              <input class="form-control btn btn-primary" style="width:140px;" type="submit" name="submit" value="Save Changes" />
          </form>
        </div>
    </body>
</html>
<% } %>
