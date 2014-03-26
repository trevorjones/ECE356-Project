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
import models.Doctor;
import models.Staff;

/**
 *
 * @author william
 */
public class DoctorStaffController {
    
    public static void create(Connection con, String doctor_id, String staff_id, boolean permission) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Doctor_Staff VALUES (?,?,?)");
        ps.setString(1, doctor_id);
        ps.setString(2, staff_id);
        ps.setInt(3, permission ? 1 : 0);
        ps.execute();
        ps.close();
    }
    
    public static void update(Connection con, String doctor_id, String staff_id, boolean permission) throws SQLException {
        PreparedStatement ps = con.prepareStatement("update Doctor_Staff set permission=? where doctor_user_id=? and staff_user_id=?");
        ps.setInt(1, permission ? 1 : 0);
        ps.setString(2, doctor_id);
        ps.setString(3, staff_id);
        ps.execute();
        ps.close();
    }
    
    public static void delete(Connection con, String doctor_id, String staff_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Doctor_Staff WHERE doctor_user_id=? AND staff_user_id=?");
        ps.setString(1, doctor_id);
        ps.setString(2, staff_id);
        ps.execute();
        ps.close();
    }
    
    public static void deleteAllFromDoctor(Connection con, String doctor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Doctor_Staff WHERE doctor_user_id=?");
        ps.setString(1, doctor_id);
        ps.execute();
        ps.close();
    }
    
    public static ArrayList<Staff> queryStaffNotWorkingForDoctor(Connection con, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Staff> ret = null;
        try {
            pstmt = con.prepareStatement("SELECT * from Doctor_Staff,User WHERE Doctor_Staff.staff_user_id=User.user_id AND Doctor_Staff.staff_user_id NOT IN (SELECT staff_user_id FROM Doctor_Staff WHERE doctor_user_id=?)");
            pstmt.setString(1, doctor_id);
            
            ResultSet resultSet = pstmt.executeQuery();
            ret = new ArrayList<Staff>();
            while (resultSet.next()) {
                ret.add(createStaff(resultSet));
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public static ArrayList<Staff> queryByDoctor(Connection con, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Staff> ret;

        try {
            //Build SQL Query
            String query = "SELECT * FROM Doctor_Staff, User "
                    + "WHERE Doctor_Staff.doctor_user_id = ? "
                    + "AND Doctor_Staff.staff_user_id = User.user_id";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, doctor_id);

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Staff>();
            while (resultSet.next()) {
                ret.add(createStaff(resultSet));
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    public static ArrayList<Doctor> queryByStaff(Connection con, String staff_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Doctor> ret;

        try {
            pstmt = con.prepareStatement("SELECT * FROM Doctor_Staff,User,Doctor WHERE Doctor_Staff.staff_user_id = ? AND Doctor_Staff.doctor_user_id = User.user_id AND User.user_id = Doctor.user_id");
            pstmt.setString(1, staff_id);

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Doctor>();
            while (resultSet.next()) {
                ret.add(createDoctor(resultSet));
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    private static Staff createStaff(ResultSet rs) throws SQLException {
        boolean permission = rs.getInt("Doctor_Staff.permission") == 1;
        Staff a = new Staff(
                rs.getString("User.user_id"),
                rs.getString("User.first_name"),
                rs.getString("User.last_name"),
                rs.getString("User.email"),
                permission);
        return a;
    }
    
    private static Doctor createDoctor(ResultSet rs) throws SQLException {
        Doctor d = new Doctor(
                rs.getString("User.user_id"),
                rs.getString("User.first_name"),
                rs.getString("User.last_name"),
                rs.getString("User.email"),
                rs.getString("Doctor.specialization"));
        return d;
    }
    
}
