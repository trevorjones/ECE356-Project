<%-- 
    Document   : patient
    Created on : 24-Mar-2014, 11:16:09 AM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Prescription"%>
<%@page import="ece356.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%! ArrayList<Patient> patientList;%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    <body>
        <%
            if (patientList != null) {
        %>
        <h1>Patients</h1>
        <form method="post" action="QueryServlet?qnum=5">
            Patient Search<input type='text' name='patient_query'/></br>
        <input type='submit' value='Submit Query'/>
        </form>
        <table border=1>
            <tr><th>User ID</th><th>First Name</th><th>Last Name</th></tr>
            <%
                for (Patient p : patientList) {
            %>
            <tr>
                <td><a href="QueryServlet?qnum=6&patient_id=<%= p.getUserid()%>"><%= p.getUserid()%></a>
                        </td>
                <td><%= p.getFname()%></td>
                <td><%= p.getLname()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <a href="new_patient.jsp">Create new patient</a>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
    </body>
</html>
