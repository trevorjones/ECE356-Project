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
        <title>Doctor List</title>
    </head>
    
    
    <%! ArrayList<Doctor> doctorList;%>
    <% doctorList = (ArrayList<Doctor>) request.getAttribute("doctorList");%>
    
    <body>
        <h1>Doctors Associated with You</h1>
        <%
            if (doctorList != null) {
        %>
        <table border=1>
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
        
    </body>
</html>

