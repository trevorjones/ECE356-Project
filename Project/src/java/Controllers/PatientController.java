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
import models.Patient;

/**
 *
 * @author william
 */
public class PatientController {
    
    public static void create(Connection con, String patient_id, String address, String current_health, String ohip, String phone, int sin) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Patient VALUES (?,?,?,?,?,?)");
        pstmt.setString(1, patient_id);
        pstmt.setString(2, address);
        pstmt.setString(3, current_health);
        pstmt.setString(4, ohip);
        pstmt.setString(5, phone);
        pstmt.setInt(6, sin);
        pstmt.execute();
        pstmt.close();
    }
    
    public static void update(Connection con, String patient_id, String first_name, String last_name, String email, String address, String current_health, String ohip, String phone, int sin) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE User,Patient SET "
                    + "User.first_name = ? , "
                    + "User.last_name = ? , "
                    + "User.email = ? , "
                    + "Patient.address = ? , "
                    + "Patient.current_health = ? , "
                    + "Patient.ohip = ? , "
                    + "Patient.phone = ? , "
                    + "Patient.sin = ?  "
                    + "WHERE User.user_id = Patient.user_id "
                    + "AND Patient.user_id = ? ");
        ps.setString(1, first_name);
        ps.setString(2, last_name);
        ps.setString(3, email);
        ps.setString(4, address);
        ps.setString(5, current_health);
        ps.setString(6, ohip);
        ps.setString(7, phone);
        ps.setInt(8, sin);
        ps.setString(9, patient_id);
        ps.execute();
        ps.close();
    }
    
    public static ArrayList<Patient> getAll(Connection con) throws SQLException {
        ArrayList<Patient> ret = null;
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM User,Patient "
                    + "WHERE User.user_id = Patient.user_id");
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                ret.add(new Patient(resultSet));
            }
            
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public static ArrayList<Patient> getAllWithPermission(Connection con, String staff_id) throws SQLException {
        ArrayList<Patient> ret = null;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM (SELECT DISTINCT * FROM ((SELECT User.user_id,User.first_name,User.last_name,User.email,Patient.address,Patient.current_health,Patient.ohip,Patient.phone,Patient.sin FROM User,Patient WHERE User.user_id = Patient.user_id) AS q1 LEFT OUTER JOIN (SELECT Doctor_Patient.patient_user_id,Doctor_Staff.permission FROM Doctor_Staff, Doctor_Patient WHERE Doctor_Staff.staff_user_id = ? AND Doctor_Patient.doctor_user_id = Doctor_Staff.doctor_user_id AND Doctor_Patient.default_doctor = 1) AS q2 on q1.user_id = q2.patient_user_id)) AS q3 LEFT OUTER JOIN (SELECT * FROM Doctor_Patient WHERE Doctor_Patient.default_doctor = 1) AS q4 on q3.user_id = q4.patient_user_id;");
        stmt.setString(1, staff_id);
        
        try {
            ResultSet resultSet = stmt.executeQuery();
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                ret.add(new Patient(resultSet, true));
            }
            
            return ret;
        } finally {
            stmt.close();
        }
    }
    
    public static Patient getPatient(Connection con, String patient_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patient,User WHERE Patient.user_id = User.user_id AND Patient.user_id = ?");
        ps.setString(1, patient_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Patient(rs);
    }
    
    public static ArrayList<Patient> queryPatientByStaff(Connection con, String query, String staff_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            String stmt = "SELECT * FROM (SELECT DISTINCT * FROM ((SELECT User.user_id,User.first_name,User.last_name,User.email,Patient.address,Patient.current_health,Patient.ohip,Patient.phone,Patient.sin FROM User,Patient WHERE User.user_id = Patient.user_id) AS q1 LEFT OUTER JOIN (SELECT Doctor_Patient.patient_user_id,Doctor_Staff.permission FROM Doctor_Staff, Doctor_Patient WHERE Doctor_Staff.staff_user_id = ? AND Doctor_Patient.doctor_user_id = Doctor_Staff.doctor_user_id AND Doctor_Patient.default_doctor = 1) AS q2 on q1.user_id = q2.patient_user_id)) AS q3 LEFT OUTER JOIN (SELECT * FROM Doctor_Patient WHERE Doctor_Patient.default_doctor = 1) AS q4 on q3.user_id = q4.patient_user_id"
                        + "WHERE user_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR address LIKE ? OR current_health LIKE ? OR ohip LIKE ? OR phone LIKE ? OR sin LIKE ?";
            
            pstmt = con.prepareStatement(stmt);
            pstmt.setString(1, staff_id);
            pstmt.setString(2, "%"+query+"%");
            pstmt.setString(3, "%"+query+"%");
            pstmt.setString(4, "%"+query+"%");
            pstmt.setString(5, "%"+query+"%");
            pstmt.setString(6, "%"+query+"%");
            pstmt.setString(7, "%"+query+"%");
            pstmt.setString(8, "%"+query+"%");
            pstmt.setString(9, "%"+query+"%");
            pstmt.setString(10, "%"+query+"%");
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                ret.add(new Patient(resultSet, true));
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public static ArrayList<Patient> queryPatientByDoctor(Connection con, String query, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            String stmt = "SELECT * FROM User, Patient, Doctor_Patient "
                    + "WHERE User.user_id = Patient.user_id AND Doctor_Patient.patient_user_id = User.user_id "
                    + "AND (User.user_id LIKE ? OR User.first_name LIKE ? OR User.last_name LIKE ? OR User.email LIKE ? OR Patient.address LIKE ? OR Patient.current_health LIKE ? OR Patient.ohip LIKE ? OR Patient.phone LIKE ? OR Patient.sin LIKE ?) AND Doctor_Patient.doctor_user_id = ?";
            
            pstmt = con.prepareStatement(stmt);
            pstmt.setString(1, "%"+query+"%");
            pstmt.setString(2, "%"+query+"%");
            pstmt.setString(3, "%"+query+"%");
            pstmt.setString(4, "%"+query+"%");
            pstmt.setString(5, "%"+query+"%");
            pstmt.setString(6, "%"+query+"%");
            pstmt.setString(7, "%"+query+"%");
            pstmt.setString(8, "%"+query+"%");
            pstmt.setString(9, "%"+query+"%");
            pstmt.setString(10, doctor_id);
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                ret.add(new Patient(resultSet));
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
}
