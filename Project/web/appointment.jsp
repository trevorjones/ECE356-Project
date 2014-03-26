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
        <title>Schedule</title>
    </head>


    <%! ArrayList<Appointment> apptList;%>
    <%! ArrayList<Patient> patientList;%>
    <%! Integer conflicts;%>
    <% apptList = (ArrayList<Appointment>) request.getAttribute("apptList");%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    <% conflicts = (Integer) request.getAttribute("conflicts");%>

    <body>
        <%
            if (apptList != null) {
        %>
        <h1>Appointments</h1>
        <%
            if (conflicts != null) {
                if (conflicts != 0) {
        %>
        <p>Failed to insert due to scheduling conflict.  Please select a different time.</p>
        <%
                }
            }
        %>
        <div>
            <form method="post" action="UpdateAppointmentServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
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
                Appt. Date: <input type='text' name='date'/><br/>
                Appt. Start: <input type='text' name='start_time'/><br/>
                Appt. End: <input type='text' name='end_time'/><br/>
                <!--Appt status -->
                Procedure: <input type='text' name='procedure'/><br/>
                <input type='submit' name="submit" value='Insert_Appt'/>
            </form>
        </div>
        <div>
            <form method="post" action="UpdateAppointmentServlet?doctor_id=<%=request.getParameter("doctor_id")%>">
                <table border=1>
                    <tr><th><input type="checkbox" name="delAllAppt"/></th><th>Patient</th><th>Doctor</th><th>Date</th><th>Appointment Start</th><th>Appointment End</th><th>Appt Status</th><th>Procedure</th></tr>
                            <%
                                for (Appointment a : apptList) {
                            %>
                    <tr>
                        <td><input type="checkbox" name="delAppt" value="<%=a.getApptDate() + " " + a.getApptStart()%>&<%= a.getPatientId() %>"/></td>
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
                <input type='submit' name="submit" value='Delete_Appt'/>
            </form>
        </div>
        <%
        } else {
        %>
        <p>Empty List</p>
        <%
            }
        %>        

    </body>
</html>

