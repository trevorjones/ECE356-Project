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
import java.sql.Statement;
import java.util.ArrayList;
import models.Doctor;
import models.Log;

/**
 *
 * @author william
 */
public class DoctorController {
    
    public static void create(Connection con, String user_id, String specialization) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Doctor VALUES(?,?)");
        ps.setString(1, user_id);
        ps.setString(2, specialization);
        ps.execute();
        ps.close();
    }
    
    public static void update(Connection con, String user_id, String first_name, String last_name, String email, String specialization) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE Doctor,User SET User.first_name = ?, User.last_name = ?, User.email = ?, Doctor.specialization = ? WHERE Doctor.user_id = User.user_id AND User.user_id = ?");
        ps.setString(1, first_name);
        ps.setString(2, last_name);
        ps.setString(3, email);
        ps.setString(4, specialization);
        ps.setString(5, user_id);
        ps.execute();
        ps.close();
        
        //Logging
        Doctor d = new Doctor();
        
        d.setId(user_id);
        d.setFirstName(first_name);
        d.setLastName(last_name);
        d.setEmail(email);
        d.setSpecialization(specialization);
        
        Log log = new Log(d);
        log.Update();
    }
    
    public static ArrayList<Doctor> getAll(Connection con) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ArrayList<Doctor> ret = null;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM User,Doctor "
                    + "WHERE User.user_id = Doctor.user_id");
            ret = new ArrayList<Doctor>();
            while (resultSet.next()) {
                ret.add(new Doctor(resultSet));
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static ArrayList<Doctor> query(Connection con, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Doctor> ret;

        try {
            //Build SQL Query
            String query = "SELECT * FROM User, Doctor "
                    + "WHERE User.user_id = Doctor.user_id "
                    + "AND Doctor.user_id LIKE ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%"+doctor_id+"%");
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Doctor>();
            while (resultSet.next()) {
                Doctor d = new Doctor(
                        resultSet.getString("User.user_id"),
                        resultSet.getString("User.first_name"),
                        resultSet.getString("User.last_name"),
                        resultSet.getString("User.email"),
                        resultSet.getString("Doctor.specialization"));
                ret.add(d);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }            
    }
    
    public static ArrayList<Doctor> getAllWithNumberOfPatientsSeen(Connection con) throws SQLException{
        PreparedStatement ps = con.prepareStatement("SELECT user_id,specialization,first_name,last_name,email,COUNT(DISTINCT patient_user_id) AS number_of_patients_seen FROM (SELECT Doctor.user_id,Doctor.specialization,User.first_name,User.last_name,User.email FROM Doctor,User WHERE Doctor.user_id = User.user_id) AS q1 LEFT OUTER JOIN (SELECT * FROM VisitationRecord) AS q2 ON q2.doctor_user_id = q1.user_id GROUP BY user_id ORDER BY last_name");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Doctor> ret = new ArrayList<Doctor>();
        while(rs.next()) {
            ret.add(new Doctor(rs, true));
        }
        
        return ret;
    }
    
    public static ArrayList<Doctor> queryWithNumberOfPatientsSeen(Connection con, String id, String startDate, String endDate) throws SQLException{
        if (startDate == null || startDate.equals("")) {
            startDate = "1970-01-01";
        }
        if (endDate == null || endDate.equals("")) {
            endDate = "3000-01-01";
        }
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT Doctor.user_id,Doctor.specialization,User.first_name,User.last_name,User.email, COUNT(DISTINCT VisitationRecord.patient_user_id) AS number_of_patients_seen FROM Doctor,User,VisitationRecord WHERE Doctor.user_id = User.user_id AND VisitationRecord.doctor_user_id = User.user_id AND VisitationRecord.visit_date >= ? AND VisitationRecord.visit_date <= ? GROUP BY Doctor.user_id) AS q1"
                                                  + " WHERE user_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR specialization LIKE ?");
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ps.setString(3, "%"+id+"%");
        ps.setString(4, "%"+id+"%");
        ps.setString(5, "%"+id+"%");
        ps.setString(6, "%"+id+"%");
        ps.setString(7, "%"+id+"%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Doctor> ret = new ArrayList<Doctor>();
        while(rs.next()) {
            ret.add(new Doctor(rs, true));
        }
        
        return ret;
    }
}
