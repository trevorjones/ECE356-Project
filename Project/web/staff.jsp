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
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !user.getType().equals("doctor")) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Staff List</title>
    </head>
    
    <% ArrayList<Staff> staffRemoveList = (ArrayList<Staff>) request.getAttribute("staffRemoveList");%>
    <% ArrayList<Staff> staffAddList = (ArrayList<Staff>) request.getAttribute("staffAddList");%>
    
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="home.jsp">Home</a>
                    </li>
                    <% if (user.getType().equals("financial officer")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_BY_FO %>">Doctors</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_FO %>">Patients</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_ALL %>">Visitation Records</a>
                        </li>
                    <% } else if (user.getType().equals("doctor")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_DOCTOR %>&doctor_id=<%= user.getId()%>">Schedule</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li class="active">
                            <a href="QueryServlet?query=<%= QueryServlet.STAFF_QUERY %>&doctor_id=<%= user.getId()%>">Staff Members</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PRESCRIPTIONS_ALL %>">Prescription List</a>
                        </li>
                    <% } else if (user.getType().equals("staff")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_QUERY_BY_STAFF %>&staff_id=<%= user.getId()%>">Associated Doctors</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Patients</a>
                        </li>
                    <% } %>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="LogoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div>
                <h2>Add Staff</h2>
                <% if (staffAddList != null) { %>
                    <form method="post" action="CreateStaffServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                        <table class="table table-striped">
                            <tr><th></th><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>
                            <% for (Staff staff : staffAddList) { %>
                                <tr>
                                        <td><input type="checkbox" name="add" value="<%= staff.getId() %>"/></td>
                                        <td><%= staff.getId() %></td>
                                        <td><%= staff.getFirstName()%></td>
                                        <td><%= staff.getLastName()%></td>
                                        <td><%= staff.getEmail()%></td>
                                </tr>
                            <% } %>
                        </table>
                        <input class="form-control btn btn-success" style="width:150px;" type='submit' name="submit" value='Add Staff'/>
                    </form>
                <% } else { %>
                    <p>Empty List</p>
                <% } %>
            </div>
            <div>
                <h2>Associated Staff</h2>
                <% if (staffRemoveList != null) { %>
                    <form method="post" action="DeleteStaffServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                        <table class="table table-striped">
                            <tr><th><input type="checkbox" name="deleteAll"/></th><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Permission</th></tr>
                            <% for (Staff staff : staffRemoveList) { %>
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
                        <input class="form-control btn btn-danger" style="width:150px;" type='submit' name="submit" value='Remove'/>
                    </from>
                <% } else { %>
                    <p>Empty List</p>
                <% } %>
            </div>
        </div>
    </body>
</html>
<% } %>
