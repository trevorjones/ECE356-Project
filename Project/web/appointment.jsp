<%-- 
    Document   : doctor
    Created on : Mar 23, 2014, 3:57:44 PM
    Author     : slouli
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Patient"%>
<%@page import="models.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment List</title>
    </head>


    <%! ArrayList<Appointment> apptList;%>
    <%! ArrayList<Patient> patientList;%>
    <% apptList = (ArrayList<Appointment>) request.getAttribute("apptList");%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>

    <body>
        <%
            if (apptList != null) {
        %>
        <h1>Appointments</h1>
        <div>
            <form method="post" action="CreateAppointment">
                <h2>Insert Appointment</h2>
                Patient: 
                <select name="patient_id">
                    <%
                        for (Patient p : patientList) {
                    %>
                    <option value="<%=p.getId()%>"><%=p.getId()%></option>
                    <%
                        }
                    %>
                </select><br/>
                Doctor id: <label name='doctor_id'><%=request.getParameter("doctor_id")%></label><br/>
                Appt. Start: <input type='text' name='start_time'/><br/>
                Appt. End: <input type='text' name='end_time'/><br/>
                <!--Appt status -->
                Procedure: <input type='text' name='procedure'/><br/>
                <input type='submit' value='Insert Appt'/>
            </form>
        </div>
        <table border=1>
            <tr><th>Patient</th><th>Doctor</th><th>Appointment Start</th><th>Appointment End</th><th>Appt Status</th><th>Procedure</th></tr>
                    <%
                        for (Appointment a : apptList) {
                    %>
            <tr>
                <td><%= a.getPatientId()%></a></td>
                <td><%= a.getDoctorId()%></td>
                <td><%= a.getApptStart()%></td>
                <td><%= a.getApptEnd()%></td>
                <td><%= a.getStatus()%></td>
                <td><%= a.getProc()%></td>
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

    </body>
</html>

