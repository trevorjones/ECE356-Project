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
import models.Patient;

/**
 *
 * @author william
 */
public class DoctorPatientController {
    
    public static ArrayList<Patient> queryByDoctor(Connection con, String doctor_id) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = null;
        ArrayList<Patient> ret;

        try {
            //Build SQL Query
            String query = "SELECT * FROM Doctor_Patient, Patient, User "
                    + "WHERE Doctor_Patient.doctor_user_id = ? "
                    + "AND Doctor_Patient.patient_user_id = Patient.user_id AND User.user_id = Patient.user_id";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, doctor_id);

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
