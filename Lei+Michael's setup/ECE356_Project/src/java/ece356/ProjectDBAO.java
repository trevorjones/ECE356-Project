package ece356;

import java.sql.*;
import java.util.ArrayList;

public class ProjectDBAO {
    public static final String url = "jdbc:mysql://localhost:3306/";
    //public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String user = "root";
    public static final String pwd = "root";
    
    public static void testConnection()
            throws ClassNotFoundException, SQLException {
        Statement stmt;
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pwd);
        stmt = con.createStatement();
        con.close();
    }
    
        public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pwd);
        Statement stmt = null;
        try {
            con.createStatement();
            stmt = con.createStatement();
            stmt.execute("USE project");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return con;
    }
        
    public static ArrayList<Prescription> getPrescriptions()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Prescription> ret = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Prescription");
            ret = new ArrayList<Prescription>();
            while (resultSet.next()) {
                Prescription p = new Prescription(
                        resultSet.getString("name"),
                        resultSet.getString("alias"),
                        resultSet.getString("description"));
                ret.add(p);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }        
    
    public static ArrayList<Doctor> getDoctors()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Doctor> ret = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM User,Doctor "
                    + "WHERE User.user_id = Doctor.user_id");
            ret = new ArrayList<Doctor>();
            while (resultSet.next()) {
                Doctor d = new Doctor(
                        resultSet.getString("User.user_id"),
                        resultSet.getString("User.first_name"),
                        resultSet.getString("User.last_name"),
                        resultSet.getString("Doctor.specialization"));
                ret.add(d);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<Doctor> queryDoctor(String doctor_id)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Doctor> ret;

        try {
            con = getConnection();
            
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
                        resultSet.getString("Doctor.specialization"));
                ret.add(d);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }            
    }
    public static ArrayList<Patient> getPatients()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Patient> ret = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * FROM User,Patient "
                    + "WHERE User.user_id = Patient.user_id");
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                Patient p = new Patient(
                        resultSet.getString("User.user_id"),
                        resultSet.getString("User.first_name"),
                        resultSet.getString("User.last_name"));
                ret.add(p);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public static ArrayList<Patient> queryPatient(String patient_id)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            con = getConnection();
            
        //Build SQL Query
            String query = "SELECT * FROM User, Patient "
                    + "WHERE User.user_id = Patient.user_id "
                    + "AND Patient.user_id LIKE ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%"+patient_id+"%");
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                Patient p = new Patient(
                        resultSet.getString("User.user_id"),
                        resultSet.getString("User.first_name"),
                        resultSet.getString("User.last_name"));
                ret.add(p);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }            
    }
    
    public static ArrayList<Patient_detail> getPatient_detail(String patient_id)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ArrayList<Patient_detail> ret = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM User, Patient "
                    + "WHERE User.user_id = Patient.user_id "
                    + "AND Patient.user_id = '"+ patient_id+"'";
            
            pstmt = con.prepareStatement(query);
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
            ret = new ArrayList<Patient_detail>();
            while (resultSet.next()) {
                Patient_detail pd = new Patient_detail(
                        resultSet.getString("User.user_id"),
                        resultSet.getString("User.first_name"),
                        resultSet.getString("User.last_name"),
                        resultSet.getString("User.email"),
                        resultSet.getString("Patient.address"),
                        resultSet.getString("Patient.current_health"),
                        resultSet.getString("Patient.ohip"),
                        resultSet.getInt("Patient.phone"),
                        resultSet.getInt("Patient.sin"));
                        ret.add(pd);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static String newPatient_detail(
            String patient_id, String first_name, 
            String last_name, String address, 
            String current_health, String ohip,
            int phone, int sin, String email)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        Statement stmt = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String query1 = "INSERT INTO User "
                    + "VALUES ( ? , ? , ? , 'newpass' , 'patient' , ? )";
            String query2 = " INSERT INTO Patient "
                    + "VALUES (?,?,?,?,?,?) ";
            
            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, patient_id);
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();

            pstmt2 = con.prepareStatement(query2);
            pstmt2.setString(1, patient_id);
            pstmt2.setString(2, address);
            pstmt2.setString(3, current_health);
            pstmt2.setString(4, ohip);
            pstmt2.setInt(5, phone);
            pstmt2.setInt(6, sin);
            pstmt2.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            
            return "1";
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static String updatePatient_detail(
            String patient_id, String first_name, 
            String last_name, String address, 
            String current_health, String ohip,
            int phone, int sin, String email)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        Statement stmt = null;
        try {
            con = getConnection();
            String query = "UPDATE User,Patient SET "
                    + "User.first_name = ? , "
                    + "User.last_name = ? , "
                    + "User.email = ? , "
                    + "Patient.address = ? , "
                    + "Patient.current_health = ? , "
                    + "Patient.ohip = ? , "
                    + "Patient.phone = ? , "
                    + "Patient.sin = ?  "
                    + "WHERE User.user_id = Patient.user_id "
                    + "AND Patient.user_id = ? ";
            
            pstmt = con.prepareStatement(query);;
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, current_health);
            pstmt.setString(6, ohip);
            pstmt.setInt(7, phone);
            pstmt.setInt(8, sin);
            pstmt.setString(9, patient_id);
            pstmt.executeUpdate();
            
            return "1";
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<Record_list> getRecords(String patient_id)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Record_list> ret;

        try {
            con = getConnection();
            
        //Build SQL Query
            String query = "SELECT * FROM Patient, visitationrecord "
                    + "WHERE visitationrecord.patient_user_id = Patient.user_id "
                    + "AND Patient.user_id = '" + patient_id+"'";
            
            pstmt = con.prepareStatement(query);
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Record_list>();
            while (resultSet.next()) {
                Record_list rl = new Record_list(
                        resultSet.getString("visitationrecord.patient_user_id"),
                        resultSet.getString("visitationrecord.doctor_user_id"),
                        resultSet.getDate("visitationrecord.visit_date"));
                ret.add(rl);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }            
    }
    public static ArrayList<Record_list> queryRecords(String patient_id, String visit_date)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Record_list> ret;

        try {
            con = getConnection();
            
        //Build SQL Query
            String query = "SELECT * FROM Patient, visitationrecord "
                    + "WHERE visitationrecord.patient_user_id = Patient.user_id "
                    + "AND Patient.user_id = '" +patient_id+"' "
                    + "AND visitationrecord.visit_date LIKE ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%"+visit_date+"%");
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
 
            ret = new ArrayList<Record_list>();
            while (resultSet.next()) {
                Record_list rl = new Record_list(
                        resultSet.getString("visitationrecord.patient_user_id"),
                        resultSet.getString("visitationrecord.doctor_user_id"),
                        resultSet.getDate("visitationrecord.visit_date"));
                ret.add(rl);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }            
    }
    
    
    public static ArrayList<Record_detail> getRecord_detail(String patient_id, String visit_date)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ArrayList<Record_detail> ret = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM Patient, Visitationrecord "
                    + "WHERE visitationrecord.patient_user_id = Patient.user_id "
                    + "AND Patient.user_id = '" +patient_id+"' "
                    + "AND visitationrecord.visit_date='"+visit_date+"'";
            
            pstmt = con.prepareStatement(query);
            
            ResultSet resultSet;
            resultSet = pstmt.executeQuery();
            ret = new ArrayList<Record_detail>();
            while (resultSet.next()) {
                Record_detail rd = new Record_detail(
                        resultSet.getString("visitationrecord.patient_user_id"),
                        resultSet.getString("visitationrecord.doctor_user_id"),
                        resultSet.getDate ("visitationrecord.visit_date"),
                        resultSet.getTimestamp("visitationrecord.updated_at"),
                        resultSet.getTime("visitationrecord.length_of_visit"),
                        resultSet.getString("visitationrecord.proc"),
                        resultSet.getDate("visitationrecord.scheduling_of_treatment"),
                        resultSet.getString("visitationrecord.freeform_comments"),
                        resultSet.getString("visitationrecord.surgery_performed"),
                        resultSet.getString("visitationrecord.diagnosis"),
                        resultSet.getString("visitationrecord.prescription_name"));
                        
                        ret.add(rd);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
