<%-- 
    Document   : patientVisits
    Created on : 29-Mar-2014, 12:30:27 PM
    Author     : william
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <title>Patient Visits</title>
    </head>
    
    <% ArrayList<String[]> patientList = (ArrayList<String[]>) request.getAttribute("patientList"); %>
    
    <body>
        <h2>Number of Visits per Patient</h2>
        <table>
            <tr>
                <th>Patient ID</th>
                <th>Number of Visits</th>
            </tr>
            <tr>
                <% for (String[] p : patientList) { %>
                    <td><%= p[0] %></td>
                    <td><%= p[1] %></td>
                <% } %>
            </tr>
        </table>
        
    </body>
</html>
