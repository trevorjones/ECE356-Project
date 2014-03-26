<%-- 
    Document   : new_patient
    Created on : 25-Mar-2014, 12:52 AM
    Author     : Lei Wu/Michael Orschel
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Patient</title>
    </head>
    <%! String successflag;%>
    <% successflag =(String) request.getAttribute("insertflag");%>
    <%
            if (successflag != null && successflag.equals("1")) {
                %> <p>Patient Created.</p>
                <%
            }
    %>
    <body>
        <form method="post" action="CreatePatientServlet">
        <h1>Add a New Patient</h1>

        <table border=1>
            
            <tr><th>User ID</th><td><input type="text" name="user_id"></td><th>Address</th><td><input type="text" name="address" ></td></tr>
            <tr><th>Current Health</th><td><input type="text" name="currenthealth" ></td><th>OHIP Number</th><td><input type="text" name="ohip" ></td></tr>
            <tr><th>Phone Number</th><td><input type="text" min="0" name="phone" ></td><th>SIN Number</th><td><input type="text" min="0" name="sin" ></td></tr>    
            
        </table>     
            <input type='submit' value='Submit'/>
        </form>
    </body>
</html>