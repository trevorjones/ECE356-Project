<%-- 
    Document   : doctor
    Created on : Mar 23, 2014, 3:57:44 PM
    Author     : slouli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Prescription"%>
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
        <%
            if (doctorList != null) {
        %>
        <h1>Doctors</h1>
        <form method="post" action="QueryServlet?qnum=2">
            Doctor Search: <input type='text' name='doctor_query'/></br>
        <input type='submit' value='Submit Query'/>
        </form>
        <table border=1>
            <tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Specialization</th></tr>
            <%
                for (Doctor d : doctorList) {
            %>
            <tr>
                <td><a href="QueryServlet?qnum=3&doctor_id=<%= d.getId()%>"><%= d.getId()%></a></td>
                <td><%= d.getFirstName()%></td>
                <td><%= d.getLastName()%></td>
                <td><%= d.getSpecialization()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
        
    </body>
</html>

