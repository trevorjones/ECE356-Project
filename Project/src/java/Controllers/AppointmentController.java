/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Appointment;

/**
 *
 * @author william
 */
public class AppointmentController {
    
    public static void create(Connection con, String patient_id, String doctor_id, String start_time, String end_time, String status, String procedure) throws ClassNotFoundException, SQLException {
        Appointment appt = new Appointment(patient_id, doctor_id, start_time, end_time, status, procedure);
        PreparedStatement pstmt = null;
        ArrayList ret = null;
        
        try {
            pstmt = con.prepareStatement("INSERT INTO Appointment VALUES(?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, appt.getPatientId());
            pstmt.setString(2, appt.getDoctorId());
            pstmt.setString(3, appt.getApptStart());
            pstmt.setString(4, appt.getApptEnd());
            pstmt.setString(5, appt.getStatus());
            pstmt.setString(6, appt.getProc());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public static ArrayList<Appointment> queryDoctorAppt(Connection con, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Appointment> ret;

        try {
            //Build SQL Query
            String query = "SELECT * FROM Appointment "
                    + "WHERE doctor_user_id = ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, doctor_id);

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Appointment>();
            while (resultSet.next()) {
                Appointment a = new Appointment(
                        resultSet.getString("Appointment.patient_user_id"),
                        resultSet.getString("Appointment.doctor_user_id"),
                        resultSet.getTimestamp("Appointment.scheduled_date"),
                        resultSet.getTimestamp("Appointment.end_date"),
                        resultSet.getString("Appointment.status"),
                        resultSet.getString("Appointment.proc"));
                ret.add(a);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    
}
