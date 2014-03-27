<%-- 
    Document   : patient
    Created on : 24-Mar-2014, 11:16:09 AM
    Author     : Lei Wu
--%>
<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Patient List</title>
    </head>
    
    <%! ArrayList<Patient> patientList;%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="home.jsp">Home</a>
                    </li>
                    <% if (user.getType().equals("financial officer")) { %>
                        <li>
                            <a href="doctorList.jsp">Doctor List</a>
                        </li>
                        <li>
                            <a href="patientList.jsp">Patient List</a>
                        </li>
                    <% } else if (user.getType().equals("doctor")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_DOCTOR %>&doctor_id=<%= user.getId()%>">Schedule</a>
                        </li>
                        <li class="active">
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.STAFF_QUERY %>&doctor_id=<%= user.getId()%>">Staff Members</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PRESCRIPTIONS_ALL %>">Prescription List</a>
                        </li>
                    <% } else if (user.getType().equals("staff")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.DOCTORS_QUERY_BY_STAFF %>&staff_id=<%= user.getId()%>">Associated Doctors</a>
                        </li>
                        <li class="active">
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Patients</a>
                        </li>
                    <% } %>
                    <li>
                        <a href="LogoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container" style="padding-top:40px;">
            <% if (user.getType().equals("staff")) { %>
                <h2>Add a New Patient</h2>
                <form class="form-horizontal" method="post" action="CreatePatientServlet">

                   <div class="form-group">
                       <label for="user_id" class="col-sm-2 control-label" style="width:140px;">Patient ID</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="user_id" name="user_id"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="address" class="col-sm-2 control-label" style="width:140px;">Address</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="address" name="address"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="currenthealth" class="col-sm-2 control-label" style="width:140px;">Current Health</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="currenthealth" name="currenthealth"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="ohip" class="col-sm-2 control-label" style="width:140px;">OHIP</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="ohip" name="ohip"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="phone" class="col-sm-2 control-label" style="width:140px;">Phone Number</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="phone" name="phone"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="sin" class="col-sm-2 control-label" style="width:140px;">SIN</label>
                       <div class="col-sm-10">
                           <input type="text" class="form-control" style="width:200px;" id="sin" name="sin"></input>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-2 control-label" style="width:140px;"></label>
                       <div class="col-sm-10">
                           <input class="form-control btn btn-success" style="width:200px;" type='submit' name="submit" value='Create Patient'/>
                       </div>
                   </div>
               </form>    
            <% } %>
            <%
                if (patientList != null) {
            %>
            <h2>Patients</h2>
            <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<% if (user.getType().equals("doctor")) { %><%= QueryServlet.PATIENTS_SEARCH_BY_DOCTOR %>&doctor_id=<% } else { %><%= QueryServlet.PATIENTS_SEARCH_BY_STAFF %>&staff_id=<% } %><%= user.getId() %>">
                <div class="form-group">
                    <input class="form-control" placeholder="Patient Search" type='text' name='patient_query'/></br>
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
                </div>
            </form>
            <% if (user.getType().equals("doctor")) { %>
                <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Show All</a>
            <% } else { %>
                <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Show All</a>
            <% } %>
            <table class="table table-striped">
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Current Health</th>
                    <th>OHIP</th>
                    <th>Phone</th>
                    <th>SIN</th>
                    <th>Default Doctor ID</th>
                    <th>Visitation Record</th>
                </tr>
                <%
                    for (Patient p : patientList) {
                %>
                <tr>
                    <% if (user.getType().equals("staff")) { %>
                        <td><a href="QueryServlet?query=<%= QueryServlet.PATIENT_DETAILS %>&patient_id=<%= p.getId()%>"><%= p.getId()%></a></td>
                    <% } else { %>
                        <td><%= p.getId()%></td>
                    <% } %>
                    <td><%= p.getFirstName()%></td>
                    <td><%= p.getLastName()%></td>
                    <td><%= p.getEmail()%></td>
                    <td><%= p.getAddress()%></td>
                    <td><%= p.getCurrentHealth() %></td>
                    <td><%= p.getOHIP()%></td>
                    <td><%= p.getPhone()%></td>
                    <td><%= p.getSIN()%></td>
                    <td><%= p.getDefaultDoctorID() %></td>
                    <td>
                        <% if (user.getType().equals("doctor")) { %>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_BY_PATIENT_AS_DOCTOR %>&patient_id=<%= p.getId() %>&doctor_id=<%= user.getId() %>">View</a>
                        <% } else if ((user.getType().equals("staff") && p.getPermission())) { %>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_BY_PATIENT_AS_STAFF %>&patient_id=<%= p.getId() %>&staff_id=<%= user.getId() %>">View</a>
                        <% } else { %>
                            N/A
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
        <p>Empty List</p>
        <%
            }
        %>        
    </body>
</html>
