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
    
    public static void delete(Connection con, String doctor_id, String patient_id, String scheduled_date) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList ret = null;
        
        try {
            pstmt = con.prepareStatement("DELETE FROM Appointment "
                    + "WHERE scheduled_date = ?, doctor_user_id=?, patient_user_id=?");
            pstmt.setString(1, scheduled_date);
            pstmt.setString(2, doctor_id);
            pstmt.setString(3, patient_id);
 
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
                        resultSet.getTimestamp("Appointment.start_date"),
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
    
    public static int sanityCheck(Connection con, String datetime_start, String datetime_end) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;

        try {
            //Build SQL Query
            String query = "SELECT COUNT(*) FROM Appointment "
                    + "WHERE (Appointment.scheduled_date <= ? AND Appointment.end_date > ?) "
                    + "OR (Appointment.scheduled_date < ? AND Appointment.end_date >= ?) "
                    + "OR (Appointment.scheduled_date >= ? AND Appointment.end_date <= ?)";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, datetime_start);
            pstmt.setString(2, datetime_start);
            pstmt.setString(3, datetime_end);
            pstmt.setString(4, datetime_end);
            pstmt.setString(5, datetime_start);
            pstmt.setString(6, datetime_end);

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
            
            resultSet.next();
            return resultSet.getInt(1);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }  
    } 
}
