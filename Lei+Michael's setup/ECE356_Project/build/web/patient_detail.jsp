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
        <%! String successflag;%>
        <% successflag =(String) request.getAttribute("insertflag");%>
        <%
                if (successflag == "1") {
                    %> <p>Patient Modified.</p>
                    <%
                }
        %>
        <%
            if (patient_detail != null) {
        %>
        <form method="post" action="QueryServlet?qnum=13">
            <h1>Patients</h1>
            <%
                    for (Patient_detail pd : patient_detail) {
                %>
            <table border=1>

                <tr><th>Patient Record</th></tr> <input type="hidden" name="patient_id" value=<%= pd.getUserid()%>>
                <tr><th>First Name</th><td><input type="text" name="firstname" value=<%= pd.getFname()%>></td><th>Last Name</th><td><input type="text" name="lastname" value=<%= pd.getLname()%>></td></tr>
                <tr><th>Address</th><td><input type="text" name="address" value=<%= pd.getAddress()%>></td><th>Email</th><td><input type="text" name="email" value=<%= pd.getEmail()%>></td></tr>
                <tr><th>Current Health</th><td><input type="text" name="currenthealth" value=<%= pd.getCurrent_health()%>></td><th>OHIP Number</th><td><input type="text" name="ohip" value=<%= pd.getOHIP()%>></td></tr>
                <tr><th>Phone Number</th><td><input type="number" name="phone" value=<%= pd.getPhone()%>></td><th>SIN Number</th><td><input type="number" name="sin" value=<%= pd.getSIN()%>></td></tr>
                <tr><td><a href="QueryServlet?qnum=7&patient_id=<%= pd.getUserid()%>">Click here to view list of patient's visitation record</a></td>
                <td><input type='submit' value='Submit Changes'/></td></tr>
                    
            </table>
                <%
                    }
                %>
        </form>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
    </body>
    <a href = "QueryServlet?qnum=4">Back to Patient List</a>
</html>