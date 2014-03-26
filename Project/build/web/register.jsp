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
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Register</title>
    </head>
    <body style="background-image: url('http://holykaw.alltop.com/wp-content/uploads/2013/03/foto-hospital.jpg'); background-size: 2000px auto; background-position-y: -500px;">
        <div class="container" style="max-width: 330px;">
            <form form class="form-signin" method="post" action="RegisterServlet">
                <h2 class="form-signin-heading">Register</h2>
                <input class="form-control" placeholder="First Name" type="text" name="first_name" required autofocus/></br>
                <input class="form-control" placeholder="Last Name" type="text" name="last_name" required/></br>
                <input class="form-control" placeholder="Email Address" type="email" name="email" required/></br>
                <input class="form-control" placeholder="User ID" type="text" name="user_id" required/></br>
                <input class="form-control" placeholder="Password" type="password" name="password" required/></br>
                <select class="form-control" name="type" required>
                    <option value="patient">Patient</option>
                    <option value="doctor">Doctor</option>
                    <option value="Staff">Staff</option>
                    <option value="financial officer">Financial Officer</option>
                </select></br>
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Register"/>
            </form>
        </div>
    </body>
</html>
