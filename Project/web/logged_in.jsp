<%-- 
    Document   : logged_in
    Created on : 23-Mar-2014, 6:07:53 PM
    Author     : william
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <title>Welcome <%= user.getFirstName() %> <%= user.getLastName() %></title>
    </head>
    <body>
        <form method="post" action="LogoutServlet">
            <input type="submit" value="Logout">
        </form>
    </body>
</html>
