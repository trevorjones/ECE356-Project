<%-- 
    Document   : Register
    Created on : 23-Mar-2014, 11:54:50 AM
    Author     : william
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Form</title>
    </head>
    <body>
        <h1>Register Form</h1>
        <form method="post" action="RegisterServlet">
            User ID: <input type="text" name="user_id"/></br>
            Password: <input type="password" name="password"/></br>
            First Name <input type="text" name="first_name"/></br>
            Last Name: <input type="text" name="last_name"/></br>
            Type: <select name="type">
                <option value="patient">Patient</option>
                <option value="doctor">Doctor</option>
                <option value="Staff">Staff</option>
                <option value="financial officer">Financial Officer</option>
            </select></br>
            Email: <input type="text" name="email"/></br>
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>
