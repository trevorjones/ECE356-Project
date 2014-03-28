<%-- 
    Document   : user_edit
    Created on : 28-Mar-2014, 7:00:24 PM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="models.Doctor"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <title>Edit Profile Information</title>
    </head>
    <body>
        <h2>Edit Your Profile Information</h2>
        <form method="post" action="UpdateUser?user_id=<%= user.getId() %>&user_type=<%= user.getType() %>">
            <table>
                <tr>
                   <th>First Name</th>
                   <th>Last Name</th>
                   <th>Email</th>
                   <% if (user.getType().equals("doctor")) { %>
                        <th>Specialization</th>
                   <% } else if (user.getType().equals("patient")) { %>
                        <th>Address</th>
                        <th>Phone Number</th>
                   <% } %>
                </tr>
                <tr>
                   <td><input type="text" id="first_name" name="first_name" value="<%= user.getFirstName() %>"></td>
                   <td><input type="text" id="last_name" name="last_name" value="<%= user.getLastName() %>"></td>
                   <td><input type="text" id="email" name="email" value="<%= user.getEmail() %>"></td>
                   <% if (user.getType().equals("doctor")) { %>
                        <td><input type="text" id="specialization" name="specialization" value="<%= ((Doctor) user).getSpecialization() %>"></td>
                   <% } else if (user.getType().equals("patient")) { %>
                        <td><input type="text" id="address" name="address" value="<%= ((Patient) user).getAddress()%>"></td>
                        <td><input type="tel" id="phone" name="phone" value="<%= ((Patient) user).getPhone()%>"></td>
                   <% } %>
                </tr>
            </table>
            <input type="submit" name="submit" value="Save Changes" />
        </form>
    </body>
</html>
