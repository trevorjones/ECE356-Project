<%-- 
    Document   : records
    Created on : 24-Mar-2014, 8:37:06 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.VisitationRecord"%>
<%@page import="models.Prescription"%>
<%@page import="models.Doctor"%>
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
        <title>Visitation Records</title>
    </head>
    
    <% ArrayList<VisitationRecord> recordlist = (ArrayList<VisitationRecord>) request.getAttribute("visitation_record_list"); %>
    <% String puserid = (String) request.getAttribute("patient_id"); %>
    <% String assignedDoctorID = (String) request.getAttribute("assigned_doctor_id"); %>
    <% boolean isAssignedDoctor = user.getType().equals("doctor") && user.getId().equals(assignedDoctorID);%>
    <% ArrayList<Doctor> doctorsWithoutPermission = (ArrayList<Doctor>) request.getAttribute("doctors_without_permission"); %>
    <% ArrayList<Doctor> doctorsWithPermission = (ArrayList<Doctor>) request.getAttribute("doctors_with_permission"); %>
    
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
                        <li>
                            <a href="QueryServlet?query=<%= QueryServlet.PATIENTS_BY_DOCTOR %>&doctor_id=<%= user.getId()%>">Patients</a>
                        </li>
                        <li class="active">
                            <a style="text-transform:capitalize;"><%= puserid %></a>
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
                            <a style="text-transform:capitalize;"><%= puserid %></a>
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
        
        <div class="container" style="padding-top:40px;">
            <% if (isAssignedDoctor) { %>
                <h2>Record a Visit</h2>
                <% ArrayList<Prescription> prescriptionlist = (ArrayList<Prescription>) request.getAttribute("prescription_list"); %>
                <% VisitationRecord vr = (VisitationRecord) request.getAttribute("current_visitation_record"); %>

                <form class="form-inline" method="post" action="CreateVisitationRecord?doctor_id=<%= user.getId() %>&patient_id=<%= puserid %>">
                    <div class="form-group" style="width:250px;">
                        <label for="visit_date" class="col-sm-2 control-label" style="width:200px;">Visit Date</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" style="width:200px;" id="visit_date" name="visit_date" <% if (vr != null) { %>value="<%= vr.getVisitDateFormated() %>"<% } %>>
                        </div>
                    </div>
                    <div class="form-group" style="width:250px;">
                        <label for="visit_time" class="col-sm-2 control-label" style="width:200px;">Visit Start</label>
                        <div class="col-sm-10">
                            <input type="time" class="form-control" style="width:200px;" id="visit_time" name="visit_time" <% if (vr != null) { %>value="<%= vr.getVisitTimeFormated() %>"<% } %>>
                        </div>
                    </div>
                    <div class="form-group" style="width:250px;">
                        <label for="length_of_visit" class="col-sm-2 control-label" style="width:200px;">Visit Length</label>
                        <div class="col-sm-10">
                            <input type="time" class="form-control" style="width:200px;" id="length_of_visit" name="length_of_visit" <% if (vr != null) { %>value="<%= vr.getLengthTimeFormated()%>"<% } %>>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="width:250px;">
                        <label for="scheduling_of_treatment_date" class="col-sm-2 control-label" style="width:200px;">Treatment Date</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" style="width:200px;" id="scheduling_of_treatment_date" name="scheduling_of_treatment_date" <% if (vr != null) { %>value="<%= vr.getSchedulingDateFormated()%>"<% } %>>
                        </div>
                    </div>
                    <div class="form-group" style="width:250px;">
                        <label for="scheduling_of_treatment_time" class="col-sm-2 control-label" style="width:200px;">Treatment Time</label>
                        <div class="col-sm-10">
                            <input type="time" class="form-control" style="width:200px;" id="scheduling_of_treatment_time" name="scheduling_of_treatment_time" <% if (vr != null) { %>value="<%= vr.getSchedulingTimeFormated()%>"<% } %>>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="width:250px;">
                        <label for="procedure" class="col-sm-2 control-label" style="width:200px;">Procedure</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" style="width:200px;" id="procedure" name="procedure" <% if (vr != null) { %>value="<%= vr.getProcedure()%>"<% } %>>
                        </div>
                    </div>
                    <div class="form-group" style="width:250px;">
                        <label for="surgery_performed" class="col-sm-2 control-label" style="width:200px;">Surgery Performed</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" style="width:200px;" id="surgery_performed" name="surgery_performed" <% if (vr != null) { %>value="<%= vr.getSurgeryPerformed()%>"<% } %>>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="width:250px;">
                        <label for="diagnosis" class="col-sm-2 control-label" style="width:200px;">Diagnosis</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" style="width:200px;" id="diagnosis" name="diagnosis" <% if (vr != null) { %>value="<%= vr.getDiagnosis()%>"<% } %>>
                        </div>
                    </div>
                    <div class="form-group" style="width:250px;">
                        <label for="prescription_name" class="col-sm-2 control-label" style="width:200px;">Prescription</label>
                        <div class="col-sm-10"> 
                            <select class="form-control" style="width:200px;" name="prescription_name">
                                <option value="none" <% if (vr == null || vr.getPrescriptionName().equals("none")) { %>selected="selected"<% } %>>None</option>
                                <% for (Prescription p : prescriptionlist) { %>
                                    <option value="<%= p.getName() %>" <% if (vr != null && vr.getPrescriptionName().equals(p.getName())) { %>selected="selected"<% } %>><%= p.getName() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="width:250px;">
                        <label for="freeform_comments" class="col-sm-2 control-label" style="width:200px;">Comments</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" style="width:200px;" id="freeform_comments" name="freeform_comments" <% if (vr != null) { %>value="<%= vr.getFreeformComments()%>"<% } %>>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group" style="width:250px;">
                        <div class="col-sm-10">
                            <input class="form-control btn btn-success" style="width:200px;" type='submit' name="submit" value='Add Record'/>
                        </div>
                    </div>
                </form>
                
                <% if (doctorsWithPermission.size() != 0) { %>
                    <h2>Doctors with Viewing Permissions for this Patient's Records</h2>
                    <form method="post" action="UpdateDoctorPermission?patient_id=<%= puserid %>&doctor_id=<%= user.getId() %>">
                        <table class="table table-striped">
                            <tr><th></th><th>User ID</th><th>First Name</th><th>Last Name</th></tr>
                            <% for (Doctor d : doctorsWithPermission) { %>
                                <tr>
                                        <td><input type="checkbox" name="delete_permission" value="<%= d.getId() %>"/></td>
                                        <td><%= d.getId() %></td>
                                        <td><%= d.getFirstName()%></td>
                                        <td><%= d.getLastName()%></td>
                                </tr>
                            <% } %>
                        </table>
                        <input class="form-control btn btn-danger" style="width:250px;" type='submit' name="submit" value='Remove Permission of Selected'/>
                    </from>
                <% } %>
                
                <% if (doctorsWithoutPermission.size() != 0) { %>
                    <h2>Add Permission to Doctor</h2>
                    <form class="form-horizontal" method="post" action="UpdateDoctorPermission?patient_id=<%= puserid %>&doctor_id=<%= user.getId() %>" >
                        <div class="form-group">
                            <label for="add_permission" class="col-sm-2 control-label" style="width:140px;">Add permission to</label>
                            <div class="col-sm-10">
                                <select class="form-control" style="width:200px;" name="add_permission">
                                    <% for (Doctor d : doctorsWithoutPermission) { %>
                                        <option value="<%= d.getId() %>"><%= d.getFirstName() %> <%= d.getLastName() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="width:140px;"></label>
                            <div class="col-sm-10">
                                <input class="form-control btn btn-primary" style="width:200px;" type='submit' name="submit" value='Add'/>
                            </div>
                        </div>
                    </form>
                <% } %>
                <br/>
                
            <% } %>
            
            <h2>Visitation Record</h2>
            <% if (recordlist != null) { %>
                <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<% if (user.getType().equals("doctor")) { %><%= QueryServlet.RECORDS_SEARCH_AS_DOCTOR %>&doctor_id=<%= user.getId() %><% } else if (user.getType().equals("staff")) { %><%= QueryServlet.RECORDS_SEARCH_AS_STAFF %>&staff_id=<%= user.getId() %><% } else { %><%= QueryServlet.RECORDS_SEARCH_AS_PATIENT %><% } %>&patient_id=<%= puserid %>">
                    <div class="form-group">
                        <input class="form-control" placeholder="Records Search" type='text' name='record_query'/></br>
                    </div>
                    <div class="form-group">
                        <input class="form-control btn btn-default" type='submit' value='Submit Query'/>
                    </div>
                </form>

                <table class="table table-striped">
                    <tr>
                        <th>Patient ID</th>
                        <th>Doctor ID</th>
                        <th>Visit Date</th>
                        <th>Length of Visit</th>
                        <th>Procedure</th>
                        <th>Scheduling of Treatment</th>
                        <% if (!user.getType().equals("patient")) { %><th>Freeform Comments</th><% } %>
                        <th>Surgery Performed</th>
                        <th>Diagnosis</th>
                        <th>Prescription</th>
                        <% if (isAssignedDoctor) { %><th>Edit</th><% } %>
                    </tr>
                    <%
                        for (VisitationRecord rl : recordlist) {
                    %>
                    <tr>
                        <td><%= rl.getPatientId() %></td>
                        <td><%= rl.getDoctorId() %></td>
                        <td><%= rl.getVisitDate() %></td>                
                        <td><%= rl.getLengthOfVisit() %></td>
                        <td><%= rl.getProcedure() %></td>
                        <td><%= rl.getSchedulingOfTreatment() %></td>
                        <% if (!user.getType().equals("patient")) { %><td><%= rl.getFreeformComments() %></td><% } %>
                        <td><%= rl.getSurgeryPerformed() %></td>
                        <td><%= rl.getDiagnosis() %></td>
                        <td><%= rl.getPrescriptionName() %></td>
                        <% if (isAssignedDoctor) { %><td><a href="QueryServlet?query=<%= QueryServlet.RECORDS_BY_PATIENT_AS_DOCTOR %>&doctor_id=<%= rl.getDoctorId() %>&patient_id=<%= rl.getPatientId() %>&visit_date=<%= rl.getVisitDate() %>&updated_at=<%= rl.getUpdatedAt() %>">Edit</a></td><% } %>
                    </tr>
                    <%
                        }
                    %>
                </table>
            <% } else { %>
                <p>FAILED</p>
            <% } %>  
        </div>
    </body>
</html>
<% } %>
