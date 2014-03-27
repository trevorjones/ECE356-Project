<%-- 
    Document   : records
    Created on : 24-Mar-2014, 8:37:06 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page import="models.VisitationRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visitation Records</title>
    </head>
        <%! ArrayList<VisitationRecord> recordlist;%>
    <% recordlist = (ArrayList<VisitationRecord>) request.getAttribute("visitation_record_list");%>
    <%  String puserid = recordlist.get(0).getPatientId(); %>
    
    <body>
        <h1>Visitation Records for Patient <%= puserid %></h1>
        <%
            if (recordlist != null) {
        %>
        
        <table border=1>
            <tr>
                <th>Patient ID</th>
                <th>Doctor ID</th>
                <th>Visit Date</th>
                <th>Length of Visit</th>
                <th>Procedure</th>
                <th>Scheduling of Treatment</th>
                <th>Freeform Comments</th>
                <th>Surgery Performed</th>
                <th>Diagnosis</th>
                <th>Prescription</th>
            </tr>
            <%
                for (VisitationRecord rl : recordlist) {
            %>
            <tr>
                <td><%= rl.getPatientId() %></td>
                <td><%= rl.getDoctorId() %></td>
                <td><%= rl.getVisitDate() %></td>                
                <td><%= rl.getLengthOfVisit() %></td>
                <td><%= rl.getProcedure() %></td>
                <td><%= rl.getSchedulingOfTreatment() %></td>
                <td><%= rl.getFreeformComments() %></td>
                <td><%= rl.getSurgeryPerformed() %></td>
                <td><%= rl.getDiagnosis() %></td>
                <td><%= rl.getPrescriptionName() %></td>
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
