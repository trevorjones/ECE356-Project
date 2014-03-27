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
    <body style="background-image: url('resources/hospital.jpg'); background-size: 2000px auto; background-position-y: -500px;">
        <div class="container" style="max-width: 330px;">
            <form class="form-signin" method="post" action="LoginServlet">
                <h2 class="form-signin-heading" style="text-shadow: 1px 1px white;">Sign In</h2>
                <input class="form-control" placeholder="User ID" type="text" name="user_id" required autofocus/></br>
                <input class="form-control" placeholder="Password" type="password" name="password" required/></br>
                <input class="btn btn-lg btn-default btn-block" type="submit" value="Login"/>
            </form>
            <div style="padding-top:20px;">
                <a href="register.jsp">
                    <button class="btn btn-lg btn-primary btn-block">Register</button>
                </a>
            </div>
        </div>
    </body>
</html>
