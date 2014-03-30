<%-- 
    Document   : doctor
    Created on : Mar 23, 2014, 3:57:44 PM
    Author     : slouli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page import="models.Appointment"%>
<%@page import="servlets.QueryServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<jsp:useBean id="user" class="models.User" scope="session"/>
<% if (user == null || user.getType() == null || !(user.getType().equals("staff") || user.getType().equals("doctor") || user.getType().equals("patient"))) {
    response.sendRedirect("home.jsp");
} else { %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Schedule</title>
    </head>


    <%! ArrayList<Appointment> apptList;%>
    <%! ArrayList<Patient> patientList;%>
    <%! Integer conflicts;%>
    <% apptList = (ArrayList<Appointment>) request.getAttribute("apptList");%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    <% conflicts = (Integer) request.getAttribute("conflicts");%>

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
                        <li class="active">
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
                        <li class="active">
                            <a><%=request.getParameter("doctor_id")%></a>
                        </li>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_STAFF %>&staff_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li>
                            <a href="register.jsp">Register a New User</a>
                        </li>
                    <% } else if (user.getType().equals("patient")) { %>
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.RECORDS_AS_PATIENT %>&patient_id=<%= user.getId()%>">Visitation Records</a>
                        </li>
                        <li class="active">
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
            <%
                if (apptList != null) {
            %>
            <%
                if (conflicts != null) {
                    if (conflicts != 0) {
            %>
            <p>Failed to insert due to scheduling conflict.  Please select a different time.</p>
            <%
                    }
                }
            %>
            <% if (user.getType().equals("staff")) { %>
                <div>
                    <form class="form-horizontal" method="post" action="UpdateAppointmentServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                        <h2>Add Appointment</h2>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="width:120px;">Patient</label>
                            <div class="col-sm-10">
                                <select class="form-control" style="width:200px;" name="patient_id">
                                    <%
                                        for (Patient p : patientList) {
                                    %>
                                    <option value="<%=p.getId()%>"><%=p.getId()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="date" class="col-sm-2 control-label" style="width:120px;">Date</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" style="width:200px;" id="date" name="date">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="start_time" class="col-sm-2 control-label" style="width:120px;">Start Time</label>
                            <div class="col-sm-10">
                                <input type="time" class="form-control" style="width:200px;" id="start_time" name="start_time">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="end_time" class="col-sm-2 control-label" style="width:120px;">End Time</label>
                            <div class="col-sm-10">
                                <input type="time" class="form-control" style="width:200px;" id="end_time" name="end_time">
                            </div>
                        </div>
                        <!--Appt status -->
                        <div class="form-group">
                            <label for="procedure" class="col-sm-2 control-label" style="width:120px;">Procedure</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" style="width:200px;" id="procedure" placeholder="Procedure" name="procedure">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="width:120px;"></label>
                            <div class="col-sm-10">
                                <input class="form-control btn btn-success" style="width:200px;" type='submit' name="submit" value='Add Appointment'/>
                            </div>
                        </div>
                    </form>
                </div>
            <% } %>
                                
            <div>
                <form method="post" action="UpdateAppointmentServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                    <h2>Scheduled Appointments</h2>
                    <table class="table table-striped">
                        <tr>
                            <% if (user.getType().equals("staff")) { %>
                                <th><input type="checkbox" name="delAllAppt"/></th>
                            <% } %>
                            <th>Patient</th>
                            <th>Doctor</th>
                            <th>Date</th>
                            <th>Appointment Start</th>
                            <th>Appointment End</th>
                            <th>Appt Status</th>
                            <th>Procedure</th>
                        </tr>
                                <%
                                    for (Appointment a : apptList) {
                                %>
                        <tr>
                            <% if (user.getType().equals("staff")) { %>
                                <td><input type="checkbox" name="delAppt" value="<%=a.getApptDate() + " " + a.getApptStart()%>&<%= a.getPatientId() %>"/></td>
                            <% } %>
                            <td><%= a.getPatientId()%></td>
                            <td><%= a.getDoctorId()%></td>
                            <td><%= a.getApptDate()%></td>
                            <td><%= a.getApptStart()%></td>
                            <td><%= a.getApptEnd()%></td>
                            <td><%= a.getStatus()%></td>
                            <td><%= a.getProc()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <% if (user.getType().equals("staff")) { %>
                        <input class="form-control btn btn-danger" style="width:150px;" type='submit' name="submit" value='Remove'/>
                    <% } %>
                </form>
            </div>
            <%
            } else {
            %>
            <p>Empty List</p>
            <%
                }
            %>        
        </div>
    </body>
</html>
<% } %>

