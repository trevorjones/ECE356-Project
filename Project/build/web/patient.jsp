<%-- 
    Document   : patient
    Created on : 24-Mar-2014, 11:16:09 AM
    Author     : Lei Wu
--%>
<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !(user.getType().equals("staff") || user.getType().equals("doctor"))) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Patient List</title>
    </head>
    
    <%! ArrayList<Patient> patientList;%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    
    <body>
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li>
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
                        <li class="active">
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
                        <li class="active">
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
            <%
                if (patientList != null) {
            %>
            <h2>Patients</h2>
            <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<% if (user.getType().equals("doctor")) { %><%= QueryServlet.PATIENTS_SEARCH_BY_DOCTOR %>&doctor_id=<% } else { %><%= QueryServlet.PATIENTS_SEARCH_BY_STAFF %>&staff_id=<% } %><%= user.getId() %>">
                <div class="form-group">
                    <input class="form-control" placeholder="Patient Search" type='text' name='patient_query'/></br>
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
                </div>
            </form>
            <% if (user.getType().equals("doctor")) { %>
                <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Show All</a>
            <% } else { %>
                <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Show All</a>
            <% } %>
            <table class="table table-striped">
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Current Health</th>
                    <th>OHIP</th>
                    <th>Phone</th>
                    <th>SIN</th>
                    <th>Default Doctor ID</th>
                    <th>Visitation Record</th>
                </tr>
                <%
                    for (Patient p : patientList) {
                %>
                <tr>
                    <% if (user.getType().equals("staff")) { %>
                        <td><a href="QueryServlet?query=<%= QueryServlet.PATIENT_DETAILS %>&patient_id=<%= p.getId()%>"><%= p.getId()%></a></td>
                    <% } else { %>
                        <td><%= p.getId()%></td>
                    <% } %>
                    <td><%= p.getFirstName()%></td>
                    <td><%= p.getLastName()%></td>
                    <td><%= p.getEmail()%></td>
                    <td><%= p.getAddress()%></td>
                    <td><%= p.getCurrentHealth() %></td>
                    <td><%= p.getOHIP()%></td>
                    <td><%= p.getPhone()%></td>
                    <td><%= p.getSIN()%></td>
                    <td><%= p.getDefaultDoctorID() %></td>
                    <td>
                        <% if (user.getType().equals("doctor")) { %>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_BY_PATIENT_AS_DOCTOR %>&patient_id=<%= p.getId() %>&doctor_id=<%= user.getId() %>">View</a>
                        <% } else if ((user.getType().equals("staff") && p.getPermission())) { %>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_BY_PATIENT_AS_STAFF %>&patient_id=<%= p.getId() %>&staff_id=<%= user.getId() %>">View</a>
                        <% } else { %>
                            N/A
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
        <p>Empty List</p>
        <%
            }
        %>        
    </body>
</html>
<% } %>
