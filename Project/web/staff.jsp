<%-- 
    Document   : staff
    Created on : 25-Mar-2014, 4:38:02 PM
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
        <title>Staff List</title>
    </head>
    
    <%! ArrayList<Staff> staffList;%>
    <% staffList = (ArrayList<Staff>) request.getAttribute("staffList");%>
    
    <body>
        <h1>Staff associated with you</h1>
        <% if (staffList != null) { %>
            <form method="post" action="DeleteStaffServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                <table border=1>
                    <tr><th><input type="checkbox" name="deleteAll"/></th><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Permission</th></tr>
                    <% for (Staff staff : staffList) { %>
                        <tr>
                                <td><input type="checkbox" name="delete" value="<%= staff.getId() %>"/></td>
                                <td><%= staff.getId() %></td>
                                <td><%= staff.getFirstName()%></td>
                                <td><%= staff.getLastName()%></td>
                                <td><%= staff.getEmail()%></td>
                                <td>
                                    <% if (staff.getPermission()) { %>
                                        <a href="UpdateStaffServlet?permission=false&doctor_id=<%= request.getParameter("doctor_id") %>&staff_id=<%= staff.getId() %>">Remove</a>
                                    <% } else { %>
                                        <a href="UpdateStaffServlet?permission=true&doctor_id=<%= request.getParameter("doctor_id") %>&staff_id=<%= staff.getId() %>">Add</a>
                                    <% } %>
                                </td>
                        </tr>
                    <% } %>
                </table>
                <input type='submit' name="submit" value='Remove Staff'/>
            </from>
        <% } else { %>
            <p>Empty List</p>
        <% } %>
        </br><a href="QueryServlet?query=<%= QueryServlet.STAFF_NOT_ASSIGNED %>&doctor_id=<%= request.getParameter("doctor_id") %>">Add Staff Member</a>
    </body>
</html>
