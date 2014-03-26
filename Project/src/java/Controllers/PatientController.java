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
    
    public static ArrayList<Patient> queryPatient(Connection con, String query, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            String stmt = "SELECT * FROM User, Patient, Doctor_Patient "
                    + "WHERE User.user_id = Patient.user_id AND Doctor_Patient.patient_user_id = User.user_id "
                    + "AND (User.user_id LIKE ? OR User.first_name LIKE ? OR User.last_name LIKE ? OR User.email LIKE ? OR Patient.address LIKE ? OR Patient.current_health LIKE ? OR Patient.ohip LIKE ? OR Patient.phone LIKE ? OR Patient.sin LIKE ?)";
            
            if (doctor_id != null) {
                stmt += " AND Doctor_Patient.doctor_user_id = ?";
            }
            
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
            
            if (doctor_id != null) {
                pstmt.setString(10, doctor_id);
            }
            
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
