<%-- 
    Document   : patient_detail
    Created on : 24-Mar-2014, 3:57:24 PM
    Author     : Lei Wu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Prescription"%>
<%@page import="ece356.Patient_detail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%! ArrayList<Patient_detail> patient_detail;%>
    <% patient_detail = (ArrayList<Patient_detail>) request.getAttribute("patient_detail");%>
    <body>
        <%
            if (patient_detail != null) {
        %>
        <h1>Patients</h1>
        <%
                for (Patient_detail pd : patient_detail) {
            %>
        <table border=1>
            
            <tr><th>Patient Record</th></tr>
            <tr><th>First Name</th><td><%= pd.getFname()%></td><th>Last Name</th><td><%= pd.getLname()%></td></tr>
            <tr><th>Address</th><td><%= pd.getAddress()%></td></tr>
            <tr><th>Current Health</th><td><%= pd.getCurrent_health()%></td><th>OHIP Number</th><td><%= pd.getOHIP()%></td></tr>
            <tr><th>Phone Number</th><td><%= pd.getPhone()%></td><th>SIN Number</th><td><%= pd.getSIN()%></td></tr>
            <tr><td><a href="QueryServlet?qnum=7&patient_id=<%= pd.getUserid()%>">Click here to view list of patient's visitation record</a></td></tr>
                
            
        </table>
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