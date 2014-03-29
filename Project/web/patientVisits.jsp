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
        <title>Patient Visits</title>
    </head>
    
    <% ArrayList<String[]> patientList = (ArrayList<String[]>) request.getAttribute("patientList"); %>
    
    <body>
        <h2>Number of Visits per Patient</h2>
        <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<%= QueryServlet.PATIENTS_SEARCH_BY_FO %>">
            <div class="form-group">
                <input class="form-control" placeholder="Patient Search" type='text' name='patient_query'/></br>
            </div>
            <div class="form-group">
                <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
            </div>
        </form>
        <table>
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
        
    </body>
</html>
<% } %>
