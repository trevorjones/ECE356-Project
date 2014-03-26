<%-- 
    Document   : doctor
    Created on : Mar 23, 2014, 3:57:44 PM
    Author     : slouli
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Doctor List</title>
    </head>
    
    
    <%! ArrayList<Doctor> doctorList;%>
    <% doctorList = (ArrayList<Doctor>) request.getAttribute("doctorList");%>
    
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
                        <li class="active">
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
            <h2>Associated Doctors</h2>
            <%
                if (doctorList != null) {
            %>
            <table class="table table-striped">
                <tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Specialization</th></tr>
                <%
                    for (Doctor d : doctorList) {
                %>
                <tr>
                    <td><a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_DOCTOR %>&doctor_id=<%= d.getId() %>"><%= d.getId()%></a></td>
                    <td><%= d.getFirstName()%></td>
                    <td><%= d.getLastName()%></td>
                    <td><%= d.getEmail() %></td>
                    <td><%= d.getSpecialization()%></td>
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
        </div>
        
    </body>
</html>

