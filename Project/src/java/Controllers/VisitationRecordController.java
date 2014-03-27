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
    
    public static ArrayList<VisitationRecord> getAllByPatientAsStaff(Connection con, String patient_id, String staff_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord,Doctor_Staff WHERE patient_user_id = ? AND Doctor_Staff.doctor_user_id = VisitationRecord.doctor_user_id AND Doctor_Staff.staff_user_id = ? AND permission = 1");
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
    
    public static ArrayList<VisitationRecord> queryAsDoctor(Connection con, String patient_id, String doctor_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord,Doctor_Patient WHERE VisitationRecord.patient_user_id = ? AND VisitationRecord.patient_user_id = Doctor_Patient.patient_user_id AND Doctor_Patient.doctor_user_id = ?");
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
    
    public static ArrayList<VisitationRecord> queryAsStaff(Connection con, String patient_id, String staff_id, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord,Doctor_Staff WHERE patient_user_id = ? AND Doctor_Staff.doctor_user_id = VisitationRecord.doctor_user_id AND Doctor_Staff.staff_user_id = ? AND permission = 1"
                                                  + " AND (VisitationRecord.patient_user_id LIKE ? OR VisitationRecord.doctor_user_id LIKE ? OR VisitationRecord.visit_date LIKE ? OR VisitationRecord.length_of_visit LIKE ? OR VisitationRecord.proc LIKE ? OR VisitationRecord.scheduling_of_treatment LIKE ? OR VisitationRecord.freeform_comments LIKE ? OR VisitationRecord.surgery_performed LIKE ? OR VisitationRecord.diagnosis LIKE ? OR VisitationRecord.prescription_name LIKE ?)");
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
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord,Doctor_Patient WHERE VisitationRecord.patient_user_id = ? AND VisitationRecord.patient_user_id = Doctor_Patient.patient_user_id AND Doctor_Patient.doctor_user_id = ?");
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
}
