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
    
    public static ArrayList<VisitationRecord> queryByPatient(Connection con, String patient_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM VisitationRecord WHERE patient_user_id = ?");
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
