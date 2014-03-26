<%-- 
    Document   : record_detail
    Created on : 24-Mar-2014, 11:00:53 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Record_detail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
            <%! ArrayList<Record_detail> record_detail;%>
    <% record_detail = (ArrayList<Record_detail>) request.getAttribute("record_detail");%>
    <body>
        <%
            if (record_detail != null) {
        %>
        <h1>Visitation Record Detail</h1>
        <%
                for (Record_detail rd : record_detail) {
            %>
        <table border=1>
            
            <tr><th>Visitation Record</th></tr>
            <tr><th>Patient ID</th><td><%= rd.getPuserid()%></td><th>Doctor ID</th><td><%= rd.getDuserid()%></td></tr>
            <tr><th>Visit Date</th><td><%=rd.getVisit_date()%></td><th>length of visit</th><td><%=rd.getLength_of_visit()%></td></tr>
            <tr><th>Procedure</th><td><%= rd.getProc()%></td></tr>
            <tr><th>Scheduling of Treatment</th><td><%=rd.getScheduling_of_treatment()%></td></tr>
            <tr><th>Comments</th><td><%= rd.getFreeform_comments()%></td></tr>
            <tr><th>Surgery Performed</th><td><%=rd.getSurgery_performed()%></td><th>Diagnosis</th><td><%= rd.getDiagnosis()%></td><th>Prescription</th><td><%= rd.getPrescription()%></td></tr>
            <tr><th>Last Update</th><td><%= rd.getUpdated_at()%></td></tr>
           
        </table>
            
                    
        <form method="post" action="QueryServlet?qnum=10">
             <input type ="hidden" name ="patient_id" value ="<%=rd.getPuserid()%>">
             <input type ="hidden" name ="visit_date" value ="<%=rd.getVisit_date()%>">
             <input type="submit" value ="Edit Record">
        </form>
            <%
                }
            %>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
    </body>
</html>
