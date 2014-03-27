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
    
    public static ArrayList<VisitationRecord> queryByPatientAsStaff(Connection con, String patient_id, String staff_id) throws SQLException {
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
    
    public static ArrayList<VisitationRecord> queryByPatientAsDoctor(Connection con, String patient_id, String doctor_id) throws SQLException {
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
