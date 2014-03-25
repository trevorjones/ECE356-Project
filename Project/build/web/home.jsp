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
        <title>Welcome <%= user.getFirstName() %> <%= user.getLastName() %></title>
    </head>
    <body>
        <h1>Menu</h1>
        <form method="post" action="LogoutServlet">
            <input type="submit" value="Logout">
        </form>
        <a href="home.jsp">Home</a><br/>
        <% if (user.getType().equals("financial officer")) { %>
            <a href="doctorList.jsp">Doctor List</a><br/>
            <a href="patientList.jsp">Patient List</a>
        <% } else if (user.getType().equals("doctor")) { %>
            <a href="QueryServlet?query=<%= QueryServlet.PRESCRIPTIONS_ALL %>">Prescription List</a>
        <% } %>
        
        <h1>Profile Information</h1>
        <table border="1" width="100%">
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
               <td><%= user.getEamil() %></td>
            </tr>
        </table>
    </body>
</html>
