<%-- 
    Document   : home
    Created on : 23-Mar-2014, 6:07:53 PM
    Author     : william
--%>
<%@page import="servlets.QueryServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Welcome <%= user.getFirstName() %> <%= user.getLastName() %></title>
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
                            <a href="QueryServlet?query=<%= QueryServlet.STAFF_QUERY %>&doctor_id=<%= user.getId()%>">Staff Members</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PRESCRIPTIONS_ALL %>">Prescription List</a>
                        </li>
                    <% } %>
                    <li>
                        <a href="LogoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container" style="padding-top:40px;">
            <h1>Profile Information</h1>
            <table class="table">
                <tr>
                   <th>User ID</th>
                   <th>First Name</th>
                   <th>Last Name</th>
                   <th>Type</th>
                   <th>Email</th>
                </tr>
                <tr>
                   <td><%= user.getId() %></td>
                   <td><%= user.getFirstName() %></td>
                   <td><%= user.getLastName() %></td>
                   <td><%= user.getType() %></td>
                   <td><%= user.getEmail() %></td>
                </tr>
            </table>
        </div>
    </body>
</html>
