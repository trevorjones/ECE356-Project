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
        <title>Patient List</title>
    </head>
    
    <%! ArrayList<Patient> patientList;%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    
    <body>
        <h1>Patients</h1>
        <% if (user.getType().equals("staff")) { %>
            <a href="new_patient.jsp">Create new patient</a>    
        <% } %>
        <%
            if (patientList != null) {
        %>
            <form method="post" action="QueryServlet?query=<%= QueryServlet.PATIENTS_SEARCH %><% if (request.getParameter("doctor_id") != null) { %>&doctor_id=<%= request.getParameter("doctor_id") %><% } %>">
                Patient Search<input type='text' name='patient_query'/></br>
            <input type='submit' value='Submit Query'/>
            </form>
            <table border=1>
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
                    <% if (user.getType().equals("staff")) { %>
                        <td><a href="QueryServlet?query=<%= QueryServlet.PATIENT_DETAILS %>&patient_id=<%= p.getId()%>"><%= p.getId()%></a></td>
                    <% } else { %>
                        <td>To Record<a href="QueryServlet?qnum=6&patient_id=<%= p.getId()%>"><%= p.getId()%></a></td>
                    <% } %>
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
        <%
            } else {
        %>
        <p>Empty List</p>
        <%
            }
        %>        
    </body>
</html>
