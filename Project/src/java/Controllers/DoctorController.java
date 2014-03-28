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
}
