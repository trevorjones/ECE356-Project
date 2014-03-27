<%-- 
    Document   : records
    Created on : 24-Mar-2014, 8:37:06 PM
    Author     : Lei Wu
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.VisitationRecord"%>
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
        <%! ArrayList<VisitationRecord> recordlist;%>
    <% recordlist = (ArrayList<VisitationRecord>) request.getAttribute("visitation_record_list");%>
    <%  String puserid = request.getParameter("patient_id"); %>
    
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
                            <a> / <%= puserid %></a>
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
                            <a> / <%= puserid %></a>
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
            <h2>Visitation Record</h2>
            <% if (user.getType().equals("doctor")) { %>
                <a href="new_patient.jsp">Create new record</a>    
            <% } %>

            <%
                if (recordlist != null) {
            %>
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
