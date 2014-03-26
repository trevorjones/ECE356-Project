<%-- 
    Document   : record_edit
    Created on : 25-Mar-2014, 8:05:34 PM
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
        <h1>Edit Visitation Record</h1>
        <%
                for (Record_detail rd : record_detail) {
            %>
            <form method="post" action="QueryServlet?qnum=11">
                <input type ="hidden" name ="patient_id" value ="<%=rd.getPuserid()%>">
                <input type ="hidden" name ="visit_date" value ="<%=rd.getVisit_date()%>">
        <table border=1>
            
            <tr><th>Visitation Record</th></tr>
            <tr><th>Patient ID</th><td><%= rd.getPuserid()%></td><th>Doctor ID</th><td><%= rd.getDuserid()%></td></tr>
            <tr><th>Visit Date</th><td><%=rd.getVisit_date()%></td><th>length of visit</th><td><input type="text" name ="length_of_visit" value="<%=rd.getLength_of_visit()%>"></td></tr>
            <tr><th>Procedure</th><td><input type="text" name ="proc" value="<%= rd.getProc()%>"></td></tr>
            <tr><th>Scheduling of Treatment</th><td><input type="text" name ="scheduling_of_treatment" value="<%=rd.getScheduling_of_treatment()%>"></td></tr>
            <tr><th>Comments</th><td><input type="text" name ="freeform_comments" value="<%= rd.getFreeform_comments()%>"></td></tr>
            <tr><th>Surgery Performed</th><td><input type="text" name ="surgery_performed" value="<%=rd.getSurgery_performed()%>"></td><th>Diagnosis</th><td><input type="text" name ="diagnosis" value="<%= rd.getDiagnosis()%>"></td>
                <th>Prescription</th><td><input type="text" name ="prescription" value="<%= rd.getPrescription()%>"></td></tr>

           
                
            
        </table>
            
            <input type="submit" value="Submit Changes">
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