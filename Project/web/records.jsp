<%-- 
    Document   : records
    Created on : 24-Mar-2014, 8:37:06 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.VisitationRecord"%>
<%@page import="models.Prescription"%>
<%@page import="servlets.QueryServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="user" class="models.User" scope="session"/>
        <link href="bootstrap-3.1.1-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="icon" href="resources/favicon.ico"/>
        <title>Visitation Records</title>
    </head>
    
    <% ArrayList<VisitationRecord> recordlist = (ArrayList<VisitationRecord>) request.getAttribute("visitation_record_list"); %>
    <% String puserid = (String) request.getAttribute("patient_id"); %>
    <% String assignedDoctorID = (String) request.getAttribute("assigned_doctor_id"); %>
    
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
            <% if (user.getType().equals("doctor") && user.getId().equals(assignedDoctorID)) { %>
                <h2>Create/Update Visitation Record for Patient <%= puserid %></h2>
                <% ArrayList<Prescription> prescriptionlist = (ArrayList<Prescription>) request.getAttribute("prescription_list"); %>
                <% VisitationRecord vr = (VisitationRecord) request.getAttribute("current_visitation_record"); %>

                <form method="post" action="CreateVisitationRecord?doctor_id=<%= user.getId() %>&patient_id=<%= puserid %>">
                    <table border=1>
                        <tr>
                            <th>Visit Date</th>
                            <th>Length of Visit</th>
                            <th>Procedure</th>
                            <th>Scheduling of Treatment</th>
                            <th>Freeform Comments</th>
                            <th>Surgery Performed</th>
                            <th>Diagnosis</th>
                            <th>Prescription</th>
                        </tr>
                        <tr>
                            <td><input type="date" name ="visit_date" ><input type="time" name ="visit_time" ></td>
                            <td><input type="time" name ="length_of_visit" ></td>
                            <td><input type="text" name ="procedure" ></td>
                            <td><input type="date" name ="scheduling_of_treatment_date" ><input type="time" name ="scheduling_of_treatment_time" ></td>
                            <td><input type="text" name ="freeform_comments" ></td>
                            <td><input type="text" name ="surgery_performed" ></td>
                            <td><input type="text" name ="diagnosis" ></td>
                            <td>
                                <select class="form-control" style="width:200px;" name="prescription_name">
                                    <option value="none" <% if (vr == null || vr.getPrescriptionName().equals("none")) { %>selected="selected"<% } %>>None</option>
                                    <% for (Prescription p : prescriptionlist) { %>
                                        <option value="<%= p.getName() %>" <% if (vr != null && vr.getPrescriptionName().equals(p.getName())) { %>selected="selected"<% } %>><%= p.getName() %></option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input class="form-control btn btn-default" type='submit' value='Submit Record'/>
                </form>
            <% } %>
            
            <h2>Past Visitation Records for Patient <%= puserid %></h2>
            <% if (recordlist != null) { %>
                <form class="form-inline" style="padding-bottom:15px;" role="form" method="post" action="QueryServlet?query=<% if (user.getType().equals("doctor")) { %><%= QueryServlet.RECORDS_SEARCH_AS_DOCTOR %>&doctor_id=<% } else { %><%= QueryServlet.RECORDS_SEARCH_AS_STAFF %>&staff_id=<% } %><%= user.getId() %>&patient_id=<%= puserid %>">
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
                        <th>Freeform Comments</th>
                        <th>Surgery Performed</th>
                        <th>Diagnosis</th>
                        <th>Prescription</th>
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
                        <td><%= rl.getFreeformComments() %></td>
                        <td><%= rl.getSurgeryPerformed() %></td>
                        <td><%= rl.getDiagnosis() %></td>
                        <td><%= rl.getPrescriptionName() %></td>
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
