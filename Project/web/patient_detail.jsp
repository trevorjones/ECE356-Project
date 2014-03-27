<%-- 
    Document   : patient_detail
    Created on : 24-Mar-2014, 3:57:24 PM
    Author     : Lei Wu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <title>Patient Details</title>
    </head>
    <%! Patient p;%>
    <% p = (Patient) request.getAttribute("patient");%>
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
            if (p != null) {
        %>
        <form method="post" action="UpdatePatient?staff_id=<%= user.getId() %>">
            <h1>Patients</h1>
            <table border=1>

                <tr>
                    <th>Patient ID</th>
                    <td><input type="text" name="patient_id" value=<%= p.getId()%> style="background-color: lightgrey;" readonly></td>
                </tr>
                <tr>
                    <th>First Name</th>
                    <td><input type="text" name="first_name" value=<%= p.getFirstName()%>></td>
                    <th>Last Name</th>
                    <td><input type="text" name="last_name" value=<%= p.getLastName()%>></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><input type="text" name="address" value=<%= p.getAddress()%>></td>
                    <th>Email</th>
                    <td><input type="text" name="email" value=<%= p.getEmail()%>></td>
                </tr>
                <tr>
                    <th>Current Health</th>
                    <td><input type="text" name="current_health" value=<%= p.getCurrentHealth()%> <% if (user.getType().equals("patient")) { %>style="background-color: lightgrey;" readonly<% } %>></td>
                    <th>OHIP Number</th>
                    <td><input type="text" name="ohip" value=<%= p.getOHIP()%> <% if (user.getType().equals("patient")) { %>style="background-color: lightgrey;" readonly<% } %>></td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td><input type="text" name="phone" value=<%= p.getPhone()%>></td>
                    <th>SIN Number</th>
                    <td><input type="text" name="sin" value=<%= p.getSIN()%> <% if (user.getType().equals("patient")) { %>style="background-color: lightgrey;" readonly<% } %>></td>
                </tr>
                <tr>
                    <td><a href="QueryServlet?qnum=7&patient_id=<%= p.getId()%>">Click here to view list of patient's visitation record</a></td>
                    <td><input type='submit' value='Submit Changes'/></td>
                </tr>
                    
            </table>
        </form>
        <%
            } else {
        %>
        <p>FAILED</p>
        <%
            }
        %>        
    </body>
</html>