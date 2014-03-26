package ece356;

import java.sql.*;
import java.util.ArrayList;
//Test commit

public class ProjectDBAO {

    public static final String url = "jdbc:mysql://localhost:3306/";
    //public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String user = "root";
    public static final String pwd = "";

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

    public static ArrayList<Prescription> queryPrescription(String prescription_id)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Prescription> ret;

        try {
            con = getConnection();

            //Build SQL Query
            String query = "SELECT * FROM Prescription "
                    + "WHERE Prescription.name LIKE ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + prescription_id + "%");

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Prescription>();
            while (resultSet.next()) {
                Prescription p = new Prescription(
                        resultSet.getString("Prescription.name"),
                        resultSet.getString("Prescription.alias"),
                        resultSet.getString("Prescription.description"));
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
            pstmt.setString(1, "%" + doctor_id + "%");

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

    public static ArrayList<Appointment> queryDoctorAppt(String doctor_id)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Appointment> ret;

        try {
            con = getConnection();

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
            if (con != null) {
                con.close();
            }
        }
    }

    public static ArrayList<Patient> queryDoctorPatient(String doctor_id)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            con = getConnection();

            //Build SQL Query
            String query = "SELECT * FROM Doctor_Patient, Patient "
                    + "WHERE Doctor_Patient.doctor_user_id = ? "
                    + "AND Doctor_Patient.patient_user_id = Patient.user_id";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, doctor_id);

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                Patient a = new Patient(
                        resultSet.getString("Patient.user_id"),
                        resultSet.getString("Patient.address"),
                        resultSet.getString("Patient.current_health"),
                        resultSet.getString("Patient.ohip"),
                        resultSet.getString("Patient.phone"),
                        resultSet.getInt("Patient.sin"));
                ret.add(a);
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

    public static void addAppointment(Appointment appt)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList ret = null;
        try {
            con = getConnection();
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
            if (con != null) {
                con.close();
            }
        }
    }
    
     public static void delAppointment(String start_date)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList ret = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement("DELETE FROM Appointment "
                    + "WHERE start_date = ?");
            pstmt.setString(1, start_date);
 
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }   
     
    public static int sanityCheckAppt(String datetime_start, String datetime_end)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            //Build SQL Query
            String query = "SELECT COUNT(*) FROM Appointment "
                    + "WHERE (Appointment.start_date <= ? AND Appointment.end_date > ?) "
                    + "OR (Appointment.start_date < ? AND Appointment.end_date >= ?) "
                    + "OR (Appointment.start_date >= ? AND Appointment.end_date <= ?)";

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
            if (con != null) {
                con.close();
            }
        }  
    } 
}
