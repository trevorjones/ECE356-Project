<%-- 
    Document   : new_patient
    Created on : 25-Mar-2014, 12:52 AM
    Author     : Lei Wu/Michael Orschel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Prescription"%>
<%@page import="ece356.Patient_detail"%>
<%@page import="ece356.NewPatient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%! String successflag;%>
    <% successflag =(String) request.getAttribute("insertflag");%>
    <%
            if (successflag == "1") {
                %> <p>Patient Created.</p>
                <%
            }
    %>
    <body>
        <form method="post" action="QueryServlet?qnum=12">
        <h1>Add a New Patient</h1>

        <table border=1>
            
            <tr><th>Username</th><td><input type="text" name="username"></td></tr>
            <tr><th>First Name</th><td><input type="text" name="firstname"></td><th>Last Name</th><td><input type="text" name="lastname" ></td></tr>
            <tr><th>Address</th><td><input type="text" name="address" ></td><th>Email</th><td><input type="text" name="email" ></td></tr>
            <tr><th>Current Health</th><td><input type="text" name="currenthealth" ></td><th>OHIP Number</th><td><input type="text" name="ohip" ></td></tr>
            <tr><th>Phone Number</th><td><input type="number" min="0" name="phone" ></td><th>SIN Number</th><td><input type="number" min="0" name="sin" ></td></tr>    
            
        </table>     
            <input type='submit' value='Submit'/>
        </form>
        <a href = "QueryServlet?qnum=4">Back to Patient List</a>
    </body>
</html>