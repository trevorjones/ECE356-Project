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
        <title>Doctor Information</title>
    </head>
    
    <%! ArrayList<Doctor> doctorList;%>
    <% doctorList = (ArrayList<Doctor>) request.getAttribute("doctorList");%>
    
    <body>
        <h2>Doctors</h2>
        <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<%= QueryServlet.DOCTORS_SEARCH_BY_FO %>">
            <div class="form-group">
                <input class="form-control" placeholder="Doctor Search" type='text' name='doctor_query'/></br>
            </div>
            <div class="form-group">
                Patients seen in between the following dates (can be left empty):</br>
                <input type='date' name='start_date'/></br>
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
    </body>
</html>
<% } %>
