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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
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
                            <a href="doctorList.jsp">Doctor List</a>
                        </li>
                        <li>
                            <a href="patientList.jsp">Patient List</a>
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
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_ALL %>">Patients</a>
                        </li>
                    <% } %>
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
            <h1>Patients</h1>
            <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<%= QueryServlet.PATIENTS_SEARCH %><% if (request.getParameter("doctor_id") != null) { %>&doctor_id=<%= request.getParameter("doctor_id") %><% } %>">
                <div class="form-group">
                    <input class="form-control" placeholder="Patient Search" type='text' name='patient_query'/></br>
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
                    <th>Address</th>
                    <th>Current Health</th>
                    <th>OHIP</th>
                    <th>Phone</th>
                    <th>SIN</th>
                </tr>
                <%
                    for (Patient p : patientList) {
                %>
                <tr>
                    <td><a href="QueryServlet?qnum=6&patient_id=<%= p.getId()%>"><%= p.getId()%></a>
                            </td>
                    <td><%= p.getFirstName()%></td>
                    <td><%= p.getLastName()%></td>
                    <td><%= p.getEmail()%></td>
                    <td><%= p.getAddress()%></td>
                    <td><%= p.getCurrentHealth() %></td>
                    <td><%= p.getOHIP()%></td>
                    <td><%= p.getPhone()%></td>
                    <td><%= p.getSIN()%></td>
                </tr>
                <%
                    }
                %>
            </table>
            <a href="new_patient.jsp">Create new patient</a>
            <%
                } else {
            %>
            <p>Empty List</p>
            <%
                }
            %>      
        </div>
    </body>
</html>
