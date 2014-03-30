<%-- 
    Document   : doctorVisits
    Created on : 29-Mar-2014, 1:06:50 PM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !user.getType().equals("financial officer")) {
    response.sendRedirect("home.jsp");
} else { %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Doctor Information</title>
    </head>
    
    <%! ArrayList<Doctor> doctorList;%>
    <% doctorList = (ArrayList<Doctor>) request.getAttribute("doctorList");%>
    
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="home.jsp">Home</a>
                    </li>
                    <% if (user.getType().equals("financial officer")) { %>
                        <li class="active">
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
            <h2>Doctors</h2>
            <small>Search number of patients seen by a doctor between an (optional) start and end date</small>
            <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<%= QueryServlet.DOCTORS_SEARCH_BY_FO %>">
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:80px; padding-top:5px;">Doctor: </label>
                    <input class="form-control" placeholder="Doctor Search" type='text' name='doctor_query'/></br>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:110px; padding-top:5px;">Start Date: </label>
                    <input type='date' name='start_date'/></br>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:100px; padding-top:5px;">End Date: </label>
                    <input type='date' name='end_date'/></br>
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
                </div>
            </form>
            <table class="table table-striped">
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Specialization</th>
                    <th>Total Patients Seen</th>
                </tr>
                <% for (Doctor d : doctorList) { %>
                <tr>
                    <td><%= d.getId()%></td>
                    <td><%= d.getFirstName()%></td>
                    <td><%= d.getLastName()%></td>
                    <td><%= d.getEmail() %></td>
                    <td><%= d.getSpecialization()%></td>
                    <td><%= d.getNumberOfPatientsSeen() %></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
<% } %>
