<%-- 
    Document   : addStaff
    Created on : 25-Mar-2014, 7:11:53 PM
    Author     : william
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Staff Member</title>
    </head>
    
    <%! ArrayList<Staff> staffList;%>
    <% staffList = (ArrayList<Staff>) request.getAttribute("staffList");%>
    
    <body>
        <h1>Add a Staff Member</h1>
        
        <% if (staffList != null) { %>
            <form method="post" action="CreateStaffServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                <table border=1>
                    <tr><th></th><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>
                    <% for (Staff staff : staffList) { %>
                        <tr>
                                <td><input type="checkbox" name="add" value="<%= staff.getId() %>"/></td>
                                <td><%= staff.getId() %></td>
                                <td><%= staff.getFirstName()%></td>
                                <td><%= staff.getLastName()%></td>
                                <td><%= staff.getEmail()%></td>
                        </tr>
                    <% } %>
                </table>
                <input type='submit' name="submit" value='Add Staff'/>
            </form>
        <% } else { %>
            <p>Empty List</p>
        <% } %>
    </body>
</html>
