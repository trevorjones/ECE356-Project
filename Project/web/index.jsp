<%-- 
    Document   : index
    Created on : 19-Mar-2014, 8:32:03 AM
    Author     : william
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
    </head>
    <body>
        <h1>Menu</h1>
        <form method="post" action="LoginServlet">
            User ID <input type="text" name="user_id"/></br>
            Password <input type="password" name="password"/></br>
            <input type="submit" value="Login"/>
        </form>
        <a href="register.jsp">Register</a>
    </body>
</html>
