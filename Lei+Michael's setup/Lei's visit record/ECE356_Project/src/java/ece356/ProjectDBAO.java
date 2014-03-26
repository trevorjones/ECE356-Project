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
    public static void updateRecord_detail(String patient_id, String visit_date, String length_of_visit, String proc, 
                String scheduling_of_treatment, String freeform_comments, String surgery_performed, String diagnosis, String prescription)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;

        try {
            con = getConnection();
            String update ="UPDATE project.Visitationrecord"
                    + "SET length_of_visit = ?, "
                    + "proc = ?, "
                    + "scheduling_of_treatment = ?, "
                    + "freeform_comments = 'this has changed'"
                    + "surgery_performed = ?, "
                    + "diagnosis = ?"
                    + "prescription_name = ?"
                    + "WHERE visitationrecord.patient_user_id = '" +patient_id+"' "
                    + "AND visitationrecord.visit_date='"+visit_date+"'";

                    
            
            pstmt = con.prepareStatement(update);

            pstmt.setString(1, "'"+length_of_visit+"'");
            pstmt.setString(2, "'"+proc+"'");
            pstmt.setString(3, "'"+scheduling_of_treatment+"'");
            pstmt.setString(4, "'"+freeform_comments+"'");
            pstmt.setString(5, "'"+surgery_performed+"'");
            pstmt.setString(6, "'"+diagnosis+"'");
            pstmt.setString(7, "'"+prescription+"'");
            
            pstmt.executeUpdate();
            /*while (resultSet.next()) {
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
            }*/
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
