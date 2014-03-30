<%-- 
    Document   : patient_detail
    Created on : 24-Mar-2014, 3:57:24 PM
    Author     : Lei Wu
--%>

<%@page import="servlets.QueryServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page import="models.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !user.getType().equals("staff")) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Patient Details</title>
    </head>
    <% Patient p = (Patient) request.getAttribute("patient");%>
    <% String curDoc = (String) request.getAttribute("curDocID");%>
    <% ArrayList<Doctor> doctors = (ArrayList<Doctor>) request.getAttribute("doctors");%>
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
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li class="active">
                            <a style="text-transform:capitalize;"><%= p.getId()%></a>
                        </li>
                        <li>
                            <a href="register.jsp">Register a New User</a>
                        </li>
                    <% } else if (user.getType().equals("patient")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_AS_PATIENT %>&patient_id=<%= user.getId()%>">Visitation Records</a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.APPOINTMENTS_FOR_PATIENT %>&patient_id=<%= user.getId()%>">Appointments</a>
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
            <%! String successflag;%>
            <% successflag =(String) request.getAttribute("insertflag");%>
            <%
                    if (successflag == "1") {
                        %> <p>Patient Modified.</p>
                        <%
                    }
            %>
            <%
                if (p != null) {
            %>
            <h2>Patient Information</h2>
            <form class="form-horizontal" method="post" action="UpdatePatient?staff_id=<%= user.getId() %>">

                <div class="form-group">
                    <label for="patient_id" class="col-sm-2 control-label" style="width:140px;">Patient ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="patient_id" name="patient_id" value=<%= p.getId()%> readonly></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="first_name" class="col-sm-2 control-label" style="width:140px;">First Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="first_name" name="first_name" value=<%= p.getFirstName()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="last_name" class="col-sm-2 control-label" style="width:140px;">Last Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="last_name" name="last_name" value=<%= p.getLastName()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label" style="width:140px;">Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="address" name="address" value=<%= p.getAddress()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label" style="width:140px;">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="email" name="email" value=<%= p.getEmail()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="current_health" class="col-sm-2 control-label" style="width:140px;">Current Health</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="current_health" name="current_health" value=<%= p.getCurrentHealth()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ohip" class="col-sm-2 control-label" style="width:140px;">OHIP</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="ohip" name="ohip" value=<%= p.getOHIP()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label" style="width:140px;">Phone Number</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="phone" name="phone" value=<%= p.getPhone()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sin" class="col-sm-2 control-label" style="width:140px;">SIN</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width:200px;" id="sin" name="sin" value=<%= p.getSIN()%>></input>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:140px;"></label>
                    <div class="col-sm-10">
                        <input class="form-control btn btn-primary" style="width:200px;" type='submit' name="submit" value='Save Changes'/>
                    </div>
                </div>
            </form>
            <h2>Assigned Doctor</h2>
            <form class="form-horizontal" method="post" action="UpdateAssignedDoctor?patient_id=<%= p.getId() %>&curdoc_id=<%= curDoc %>" >
                <div class="form-group">
                    <label for="assigned_doctor" class="col-sm-2 control-label" style="width:140px;">Assigned Dr</label>
                    <div class="col-sm-10">
                        <select class="form-control" style="width:200px;" name="assigned_doctor">
                            <option value="none" <% if (curDoc == null) { %>selected="selected"<% } %>>None</option>
                            <% for (Doctor d : doctors) { %>
                                <option value="<%= d.getId() %>" <% if (d.getId().equals(curDoc)) { %>selected="selected"<% } %>><%= d.getFirstName() %> <%= d.getLastName() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" style="width:140px;"></label>
                    <div class="col-sm-10">
                        <input class="form-control btn btn-primary" style="width:200px;" type='submit' name="submit" value='Save Changes'/>
                    </div>
                </div>
            </form>
            <%
                } else {
            %>
            <p>FAILED</p>
            <%
                }
            %> 
        </div>
    </body>
</html>
<% } %>