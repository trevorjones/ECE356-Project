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
import models.VisitationRecord;

/**
 *
 * @author william
 */
public class VisitationRecordController {
    
    public static void create(Connection con, String patient_id, String doctor_id, String visit_date, String length_of_visit, String proc, String scheduling_of_treatment, String freeform_comments, String surgery_performed, String diagnosis, String prescription_name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO VisitationRecord VALUES(?,?,?,NULL,?,?,?,?,?,?,?)");
        ps.setString(1, patient_id);
        ps.setString(2, doctor_id);
        ps.setString(3, visit_date);
        ps.setString(4, length_of_visit);
        ps.setString(5, proc);
        ps.setString(6, scheduling_of_treatment);
        ps.setString(7, freeform_comments);
        ps.setString(8, surgery_performed);
        ps.setString(9, diagnosis);
        ps.setString(10, prescription_name);
        ps.execute();
        ps.close();
    }
    
    public static VisitationRecord get(Connection con, String patient_id, String doctor_id, String visit_date, String updated_at) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord WHERE patient_user_id = ? AND doctor_user_id = ? AND visit_date = ? AND updated_at = ?");
        ps.setString(1, patient_id);
        ps.setString(2, doctor_id);
        ps.setString(3, visit_date);
        ps.setString(4, updated_at);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new VisitationRecord(rs);
    }
    
    public static ArrayList<VisitationRecord> getAll(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT * FROM VisitationRecord ORDER BY updated_at DESC) AS q8 GROUP BY patient_user_id,doctor_user_id,visit_date ORDER BY visit_date DESC");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> queryAll(Connection con, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT * FROM VisitationRecord ORDER BY updated_at DESC) AS q8"
                                                + " WHERE (patient_user_id LIKE ? OR doctor_user_id LIKE ? OR visit_date LIKE ? OR length_of_visit LIKE ? OR proc LIKE ? OR scheduling_of_treatment LIKE ? OR freeform_comments LIKE ? OR surgery_performed LIKE ? OR diagnosis LIKE ? OR prescription_name LIKE ?) GROUP BY patient_user_id,doctor_user_id,visit_date ORDER BY visit_date DESC");
        ps.setString(1, "%"+query+"%");
        ps.setString(2, "%"+query+"%");
        ps.setString(3, "%"+query+"%");
        ps.setString(4, "%"+query+"%");
        ps.setString(5, "%"+query+"%");
        ps.setString(6, "%"+query+"%");
        ps.setString(7, "%"+query+"%");
        ps.setString(8, "%"+query+"%");
        ps.setString(9, "%"+query+"%");
        ps.setString(10, "%"+query+"%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> getAllByPatientAsStaff(Connection con, String patient_id, String staff_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord,Doctor_Staff WHERE patient_user_id = ? AND Doctor_Staff.doctor_user_id = VisitationRecord.doctor_user_id AND Doctor_Staff.staff_user_id = ? AND permission = 1  ORDER BY updated_at DESC) AS q8 GROUP BY patient_user_id,doctor_user_id,visit_date ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        ps.setString(2, staff_id);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> queryAsDoctor(Connection con, String query, String patient_id, String doctor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord,Doctor_Patient WHERE VisitationRecord.patient_user_id = ? AND VisitationRecord.patient_user_id = Doctor_Patient.patient_user_id AND Doctor_Patient.doctor_user_id = ? ORDER BY updated_at DESC) AS q1 GROUP BY q1.patient_user_id,doctor_user_id,visit_date) AS q6"
                                                  + " WHERE (patient_user_id LIKE ? OR doctor_user_id LIKE ? OR visit_date LIKE ? OR length_of_visit LIKE ? OR proc LIKE ? OR scheduling_of_treatment LIKE ? OR freeform_comments LIKE ? OR surgery_performed LIKE ? OR diagnosis LIKE ? OR prescription_name LIKE ?) ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        ps.setString(2, doctor_id);
        ps.setString(3, "%"+query+"%");
        ps.setString(4, "%"+query+"%");
        ps.setString(5, "%"+query+"%");
        ps.setString(6, "%"+query+"%");
        ps.setString(7, "%"+query+"%");
        ps.setString(8, "%"+query+"%");
        ps.setString(9, "%"+query+"%");
        ps.setString(10, "%"+query+"%");
        ps.setString(11, "%"+query+"%");
        ps.setString(12, "%"+query+"%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> queryAsStaff(Connection con, String patient_id, String staff_id, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord,Doctor_Staff WHERE patient_user_id = ? AND Doctor_Staff.doctor_user_id = VisitationRecord.doctor_user_id AND Doctor_Staff.staff_user_id = ? AND permission = 1  ORDER BY updated_at DESC) AS q8 GROUP BY patient_user_id,doctor_user_id,visit_date) as q10"
                                                  + " WHERE (patient_user_id LIKE ? OR doctor_user_id LIKE ? OR visit_date LIKE ? OR length_of_visit LIKE ? OR proc LIKE ? OR scheduling_of_treatment LIKE ? OR freeform_comments LIKE ? OR surgery_performed LIKE ? OR diagnosis LIKE ? OR prescription_name LIKE ?)  ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        ps.setString(2, staff_id);
        ps.setString(3, "%"+query+"%");
        ps.setString(4, "%"+query+"%");
        ps.setString(5, "%"+query+"%");
        ps.setString(6, "%"+query+"%");
        ps.setString(7, "%"+query+"%");
        ps.setString(8, "%"+query+"%");
        ps.setString(9, "%"+query+"%");
        ps.setString(10, "%"+query+"%");
        ps.setString(11, "%"+query+"%");
        ps.setString(12, "%"+query+"%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> getAllByPatientAsDoctor(Connection con, String patient_id, String doctor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord,Doctor_Patient WHERE VisitationRecord.patient_user_id = ? AND VisitationRecord.patient_user_id = Doctor_Patient.patient_user_id AND Doctor_Patient.doctor_user_id = ? ORDER BY updated_at DESC) AS q1 GROUP BY q1.patient_user_id,doctor_user_id,visit_date  ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        ps.setString(2, doctor_id);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> queryAsPatient(Connection con, String query, String patient_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord WHERE VisitationRecord.patient_user_id = ? ORDER BY updated_at DESC) AS q1 GROUP BY q1.patient_user_id,doctor_user_id,visit_date) AS q6"
                                                  + " WHERE (patient_user_id LIKE ? OR doctor_user_id LIKE ? OR visit_date LIKE ? OR length_of_visit LIKE ? OR proc LIKE ? OR scheduling_of_treatment LIKE ? OR freeform_comments LIKE ? OR surgery_performed LIKE ? OR diagnosis LIKE ? OR prescription_name LIKE ?) ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        ps.setString(2, "%"+query+"%");
        ps.setString(3, "%"+query+"%");
        ps.setString(4, "%"+query+"%");
        ps.setString(5, "%"+query+"%");
        ps.setString(6, "%"+query+"%");
        ps.setString(7, "%"+query+"%");
        ps.setString(8, "%"+query+"%");
        ps.setString(9, "%"+query+"%");
        ps.setString(10, "%"+query+"%");
        ps.setString(11, "%"+query+"%");
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
    
    public static ArrayList<VisitationRecord> getAllByPatient(Connection con, String patient_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM (SELECT VisitationRecord.patient_user_id,VisitationRecord.doctor_user_id,VisitationRecord.visit_date,VisitationRecord.updated_at,VisitationRecord.length_of_visit,VisitationRecord.proc,VisitationRecord.scheduling_of_treatment,VisitationRecord.freeform_comments,VisitationRecord.surgery_performed,VisitationRecord.diagnosis,VisitationRecord.prescription_name FROM VisitationRecord WHERE VisitationRecord.patient_user_id = ? ORDER BY updated_at DESC) AS q1 GROUP BY q1.patient_user_id,doctor_user_id,visit_date ORDER BY visit_date DESC");
        ps.setString(1, patient_id);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<VisitationRecord> ret = new ArrayList<VisitationRecord>();
        while (rs.next()) {
            ret.add(new VisitationRecord(rs));
        }
        ps.close();
        return ret;
    }
}
