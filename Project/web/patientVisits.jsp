<%-- 
    Document   : patientVisits
    Created on : 29-Mar-2014, 12:30:27 PM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !user.getType().equals("financial officer")) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Patient Visits</title>
    </head>
    
    <% ArrayList<String[]> patientList = (ArrayList<String[]>) request.getAttribute("patientList"); %>
    
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="home.jsp">Home</a>
                    </li>
                    <% if (user.getType().equals("financial officer")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_BY_FO %>">Doctors</a>
                        </li>
                        <li class="active">
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
            <h2>Number of Visits per Patient</h2>
            <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<%= QueryServlet.PATIENTS_SEARCH_BY_FO %>">
                <div class="form-group">
                    <input class="form-control" placeholder="Patient Search" type='text' name='patient_query'/></br>
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
                </div>
            </form>
            <table class="table table-striped">
                <tr>
                    <th>Patient ID</th>
                    <th>Number of Visits</th>
                </tr>

                <% for (String[] p : patientList) { %>
                    <tr>
                        <td><%= p[0] %></td>
                        <td><%= p[1] %></td>
                    </tr>
                <% } %>
            </table>
        <div>
    </body>
</html>
<% } %>
