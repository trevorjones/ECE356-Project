<%-- 
    Document   : doctor
    Created on : Mar 23, 2014, 3:57:44 PM
    Author     : slouli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    
    <%! ArrayList<Appointment> apptList;%>
    <% apptList = (ArrayList<Appointment>) request.getAttribute("apptList");%>
    
    <body>
        <%
            if (apptList != null) {
        %>
        <h1>Appointments</h1>
        <table border=1>
            <tr><th>Patient</th><th>Doctor</th><th>Appointment Start</th><th>Appointment End</th><th>Appt Status</th><th>Procedure</th></tr>
            <%
                for (Appointment a : apptList) {
            %>
            <tr>
                <td><%= a.getPatientId()%></a></td>
                <td><%= a.getDoctorId()%></td>
                <td><%= a.getApptStart()%></td>
                <td><%= a.getApptEnd()%></td>
                <td><%= a.getStatus()%></td>
                <td><%= a.getProc()%></td>
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

