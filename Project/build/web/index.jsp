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
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Welcome</title>
    </head>
    <body>
        <div class="container" style="max-width: 330px;">
            <form class="form-signin" method="post" action="LoginServlet">
                <h2 class="form-signin-heading">Sign In</h2>
                <input class="form-control" placeholder="User ID" type="text" name="user_id" required autofocus/></br>
                <input class="form-control" placeholder="Password" type="password" name="password" required/></br>
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login"/>
            </form>
            <a href="register.jsp">Register</a>
        </div>
    </body>
</html>
